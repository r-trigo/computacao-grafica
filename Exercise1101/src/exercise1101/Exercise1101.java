package exercise1101;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.net.URL;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.Billboard;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PointLight;
import javax.media.j3d.PositionPathInterpolator;
import javax.media.j3d.QuadArray;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.JApplet;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Exercise1101 extends JApplet {

	public static void main(String[] args) {
		new MainFrame(new Exercise1101(), 640, 480);
	}
	
	public void init() {
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D cv = new Canvas3D(gc);
		setLayout(new BorderLayout());
		add(cv, BorderLayout.CENTER);
		BranchGroup bg = createSceneGraph();
		bg.compile();
		SimpleUniverse su = new SimpleUniverse(cv);
		su.getViewingPlatform().setNominalViewingTransform();
		su.addBranchGraph(bg);
		
		//Orbit behaviour
		OrbitBehavior ob = new OrbitBehavior(cv);
		ob.setSchedulingBounds(new BoundingSphere());
		su.getViewingPlatform().setViewPlatformBehavior(ob);
	}
	
	private BranchGroup createSceneGraph() {
		BranchGroup root = new BranchGroup();
		
		//Bounds
		Point3d origin3D = new Point3d(0,0,0);
		BoundingSphere outerBounds = new BoundingSphere(origin3D, 5.0);
		BoundingSphere innerBounds = new BoundingSphere(origin3D, 3.0);
		
		//Lights
		Color3f white = new Color3f(Color.WHITE);
		AmbientLight ambLight = new AmbientLight(true, white);
		ambLight.setInfluencingBounds(outerBounds);
		root.addChild(ambLight);
				
		Point3f virtualCeiling = new Point3f(0,5,0);
		PointLight pointLight = new PointLight(true, white, virtualCeiling, new Point3f(0.1f, 0f, 0f));
		pointLight.setInfluencingBounds(outerBounds);
		root.addChild(pointLight);
		
		//Arena
		RealisticAppearance arenaAp = new RealisticAppearance("images/arena.jpg");
		Arena myArena = new Arena(arenaAp);
		myArena.setCollisionBounds(innerBounds);
		root.addChild(myArena);
		
		//Start mark
		Cylinder start = new Cylinder(0.5f, 0.01f, arenaAp);
		Transform3D trStart = new Transform3D();
		trStart.setTranslation(new Vector3f(-2f,0.01f,-2f));
		TransformGroup tgStart = new TransformGroup(trStart);
		tgStart.setCollidable(false);
		tgStart.addChild(start);
		root.addChild(tgStart);
		
		//Floor
		RealisticAppearance floorAp = new RealisticAppearance("images/floor.png");
		float floorHeight = 0.01f;
		Box floor = new Box(3.5f, floorHeight, 3.5f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, floorAp);
		floor.setCollidable(false); 
		root.addChild(floor);		
		
		//Furniture
		Furniture myFurniture = new Furniture();
		Transform3D trFurniture = new Transform3D();
		trFurniture.setTranslation(new Vector3d(1.5,myFurniture.height/2,-1.75));
		TransformGroup tgFurniture = new TransformGroup(trFurniture);
		tgFurniture.addChild(myFurniture);
		tgFurniture.setCollisionBounds(innerBounds);
		root.addChild(tgFurniture);
		
		//Robot
		RealisticAppearance headAp = new RealisticAppearance("images/head.jpg");
		RealisticAppearance bodyAp = new RealisticAppearance("images/body.jpg");
		RealisticAppearance wheelsAp = new RealisticAppearance("images/wheels.jpg");
		
		Firefighter myFirefighter = new Firefighter(headAp, bodyAp, wheelsAp);
		Transform3D trRobot = new Transform3D(); 
		trRobot.setTranslation(new Vector3d(-2,myFirefighter.bodyPartHeight,-2));
		TransformGroup tgRobot = new TransformGroup(trRobot);
		tgRobot.addChild(myFirefighter);		
		tgRobot.setCollisionBounds(innerBounds);
		//Permission to be moved
		tgRobot.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tgRobot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		root.addChild(tgRobot);
		
		//Candle		
		URL file = getClass().getClassLoader().getResource("images/candle.jpg");
		TextureLoader loader = new TextureLoader(file, this);
		Texture texture = loader.getTexture();		
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparencyMode(TransparencyAttributes.FASTEST);
		
		Appearance candleAp = new Appearance();
		candleAp.setTransparencyAttributes(ta);
		candleAp.setTexture(texture);
		Candle candleGeometry = new Candle();
		Shape3D candle = new Shape3D(candleGeometry, candleAp);

		TransformGroup tgBb = new TransformGroup();
		tgBb.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tgBb.addChild(candle);
		Billboard bb = new Billboard(tgBb, Billboard.ROTATE_ABOUT_POINT, new Point3f(0.0f, 0.0f, 0.0f));
		bb.setSchedulingBounds(outerBounds);

		Transform3D trCandle = new Transform3D();
		Vector3f candlePosition = new Vector3f(1.25f,floorHeight+0.5f,2.25f);
		trCandle.setTranslation(candlePosition);
		trCandle.setScale(0.5);
		TransformGroup tgCandle = new TransformGroup(trCandle);
		tgCandle.addChild(bb);
		tgCandle.addChild(tgBb);
		root.addChild(tgCandle);
		
		
		//Robot route
		Alpha alpha = new Alpha();
		alpha.setTriggerTime(1000);
		alpha.setIncreasingAlphaDuration(10000);
		alpha.setAlphaAtOneDuration(3000);
		
		float[] knots = {0.0f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
		
		Quat4f faceRight = new Quat4f();
		faceRight.set(new AxisAngle4f(0f, 1f, 0f, (float) Math.toRadians(0)));
		Quat4f faceDown = new Quat4f();
		faceDown.set(new AxisAngle4f(0f, 1f, 0f, (float) Math.toRadians(-90)));
		Quat4f faceLeft = new Quat4f();
		faceLeft.set(new AxisAngle4f(0f, 1f, 0f, (float) Math.toRadians(180)));				
		Quat4f[] rotations = {faceRight, faceRight, faceDown, faceDown, faceRight, faceRight, faceDown, faceDown, faceLeft, faceLeft};
		
		Point3f[] positions = {new Point3f(-2f, 0, -2f),
								new Point3f(0.5f, 0, -2f),
							new Point3f(0.5f, 0, -2f),
								new Point3f(0.5f, 0, 0.25f),
							new Point3f(0.5f, 0, 0.25f),
								new Point3f(2.25f, 0, 0.25f),
							new Point3f(2.25f, 0, 0.25f),
								new Point3f(2.25f, 0, 2.25f),
							new Point3f(2.25f, 0, 2.25f),
								new Point3f(2f, 0, 2.25f)};
		
		RotPosPathInterpolator robotRoute = new RotPosPathInterpolator(alpha, tgRobot, new Transform3D(), knots, rotations, positions);
		robotRoute.setSchedulingBounds(innerBounds);
		root.addChild(robotRoute);
		
		return root;
	}
}