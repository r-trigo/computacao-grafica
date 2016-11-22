package exercise601;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleArray;
import javax.swing.JApplet;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Exercise601 extends JApplet{

	public static void main(String[] args) {
		new MainFrame(new Exercise601(),640,480);
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
		}
	
	private BranchGroup createSceneGraph() {
		BranchGroup root = new BranchGroup();								
		
		//Fundo
		Background bg = new Background();
		bg.setColor(new Color3f(Color.CYAN));
		BoundingSphere boundsphere = new BoundingSphere();
		bg.setApplicationBounds(boundsphere);
		//root.addChild(bg);
		
		//Luz
		AmbientLight aLgt = new AmbientLight(new Color3f(Color.WHITE));
		aLgt.setInfluencingBounds(boundsphere);
		//root.addChild(aLgt);
		
		// directional light
		DirectionalLight lgt1 = new DirectionalLight(new Color3f(Color.WHITE),new Vector3f(4.0f, -5.0f, -10.0f));
		lgt1.setInfluencingBounds(boundsphere);
		//root.addChild(lgt1);
		
		//Chão
		Appearance floorAp = new Appearance();
		Shape3D floor = new ChessFloor();
		floor.setAppearance(floorAp);
		Transform3D tr = new Transform3D();		
		tr.set(new Vector3f(0f, -0.40f, 0f));
		tr.setScale(0.2);
		TransformGroup tg = new TransformGroup();
		tg = new TransformGroup(tr);		
		tg.addChild(floor);
		root.addChild(tg);
		
		//Caixa		
		Appearance boxAp = new Appearance();
		ColoringAttributes colorAtt = new ColoringAttributes();
		colorAtt.setColor(new Color3f(Color.RED));
		boxAp.setColoringAttributes(colorAtt);
		PolygonAttributes polyAtt = new PolygonAttributes();
		polyAtt.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		boxAp.setPolygonAttributes(polyAtt);
		
		Box myBox = new Box(1.0f, 0.2f, 1.0f, boxAp);
		tr = new Transform3D();
		tr.setScale(0.3);
		tr.setTranslation(new Vector3d(0,0.3,0));
		tg = new TransformGroup(tr);
		tg.addChild(myBox);		
		root.addChild(tg);
				
		//Pirâmide quadrangular
		Appearance pyrAp = new Appearance();		
		colorAtt = new ColoringAttributes(new Color3f(Color.GREEN), ColoringAttributes.SHADE_GOURAUD);
		pyrAp.setColoringAttributes(colorAtt);
		//pyrAp.setPolygonAttributes(polyAtt);
		
		Shape3D myPyramid = new SquarePyramid();
		myPyramid.setAppearance(pyrAp);
		tr = new Transform3D();
		tr.setScale(0.5);
		tr.setRotation(new AxisAngle4d(0,1,0,Math.toRadians(20)));
		tg = new TransformGroup(tr);
		tg.addChild(myPyramid);
		//root.addChild(tg);

		//Spinning
		TransformGroup spin = new TransformGroup();
		spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		spin.addChild(tg);
	    Alpha alpha = new Alpha(-1, 8000);
	    RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
	    rotator.setSchedulingBounds(boundsphere);
	    spin.addChild(rotator);
	    root.addChild(spin);

		return root;
	}
	
}