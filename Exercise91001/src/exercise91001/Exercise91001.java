package exercise91001;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PointLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JApplet;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Exercise91001 extends JApplet {

	public static void main(String[] args) {
		new MainFrame(new Exercise91001(), 640, 480);
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
		Point3d origin3D = new Point3d(0, 0, 0);
		BoundingSphere outerBounds = new BoundingSphere(origin3D, 5.0);
		BoundingSphere innerBounds = new BoundingSphere(origin3D, 3.0);

		//Lights
		Color3f white = new Color3f(Color.WHITE);
		AmbientLight ambLight = new AmbientLight(true, white);
		ambLight.setInfluencingBounds(outerBounds);
		root.addChild(ambLight);

		Point3f virtualCeiling = new Point3f(0, 5, 0);
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
		trStart.setTranslation(new Vector3f(-2f, 0.01f, -2f));
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
		trFurniture.setTranslation(new Vector3d(1.5, myFurniture.height / 2, -1.75));
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
		trRobot.setTranslation(new Vector3d(-2, myFirefighter.bodyPartHeight, -2));
		TransformGroup tgRobot = new TransformGroup(trRobot);
		tgRobot.addChild(myFirefighter);
		tgRobot.setCollisionBounds(innerBounds);
		//Permission to be moved
		tgRobot.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tgRobot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		root.addChild(tgRobot);

		//FireFighter Controls
		Controls kc = new Controls(tgRobot, myFirefighter);
		kc.setSchedulingBounds(outerBounds);
		root.addChild(kc);

		return root;
	}
}