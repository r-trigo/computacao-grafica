package exercise501;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JApplet;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Exercise501 extends JApplet{

	public static void main(String[] args) {
		new MainFrame(new Exercise501(), 640, 480);
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

		
		//Cubo
		ColorCube cube = new ColorCube();
		
		//Fundo
		Color3f bluecolor = new Color3f(Color.BLUE);
		Background backg = new Background(bluecolor);
		BoundingSphere boundsphere = new BoundingSphere();
		backg.setApplicationBounds(boundsphere);
		root.addChild(backg);
		
		//Rotação
		Transform3D tr = new Transform3D();
		tr.setRotation(new AxisAngle4d(1,1,1,Math.toRadians(45)));
		tr.setScale(0.5);
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(cube);
		root.addChild(tg);		
		
		return root;
	}
}
