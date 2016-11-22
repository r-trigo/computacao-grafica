package exercise7801;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.PointLight;
import javax.media.j3d.Shape3D;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.swing.JApplet;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Exercise7801 extends JApplet implements MouseListener{
	
	PickCanvas spin;

	public static void main(String[] args) {
		new MainFrame(new Exercise7801(), 640,480);
	}
	
	public void init() {
		setLayout(new GridLayout(1, 2));
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		
		//View 1
		Canvas3D cv = new Canvas3D(gc);
		add(cv, BorderLayout.CENTER);
		SimpleUniverse su = new SimpleUniverse(cv);
		su.getViewingPlatform().setNominalViewingTransform();
		
		//View 2
		cv = new Canvas3D(gc);
		this.add(cv);
		BranchGroup bgView = createView(cv, new Point3d(1, 0, 0), new Point3d(0, 0, 0), new Vector3d(0, 1, 0));
		su.addBranchGraph(bgView);
		
		BranchGroup bg = createSceneGraph();
		bg.compile();
		
		// Picking
		spin = new PickCanvas(cv, bg);
		spin.setMode(PickTool.GEOMETRY);
		
		su.addBranchGraph(bg);
	}
	
	private BranchGroup createView(Canvas3D cv, Point3d eye, Point3d center, Vector3d vup) {
		View view = new View();
		view.setProjectionPolicy(View.PARALLEL_PROJECTION);
		ViewPlatform vp = new ViewPlatform();
		view.addCanvas3D(cv);
		view.attachViewPlatform(vp);
		view.setPhysicalBody(new PhysicalBody());
		view.setPhysicalEnvironment(new PhysicalEnvironment());
		Transform3D tr = new Transform3D();
		tr.lookAt(eye, center, vup);
		tr.invert();
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(vp);
		BranchGroup bgView = new BranchGroup();
		bgView.addChild(tg);
		return bgView;
	}

	
	private BranchGroup createSceneGraph() {
		BranchGroup root = new BranchGroup();		
		
		//Fundo
		Color celadon = new Color(47, 132, 124);
		Background fundo = new Background(new Color3f(celadon));
		BoundingSphere bounds = new BoundingSphere();
		fundo.setApplicationBounds(bounds);
		root.addChild(fundo);
		
		//Luz
		AmbientLight ambLight = new AmbientLight(true, new Color3f(Color.WHITE));
		ambLight.setInfluencingBounds(bounds);
		root.addChild(ambLight);
		
		Color3f mahogany = new Color3f(new Color(192, 64, 0));
		PointLight mahoganyLight = new PointLight(new Color3f(mahogany), new Point3f(2f, 2f, 2f), new Point3f(1f, 0f, 0f));
		mahoganyLight.setInfluencingBounds(bounds);
		root.addChild(mahoganyLight);
		
		PointLight lampLight = new PointLight(new Color3f(new Color(255, 255, 0)), new Point3f(-0.4f, 0.3f, 0.2f), new Point3f(1.0f, 0f, 0f));
		lampLight.setInfluencingBounds(bounds);
		lampLight.setCapability(PointLight.ALLOW_STATE_WRITE);
		root.addChild(lampLight);
		
		//Girar cadeira
		
		
		//Candeeiro
		ReadingLight candeeiro = new ReadingLight();
		Transform3D tr = new Transform3D();
		AxisAngle4d rodarParaEsquerda = new AxisAngle4d(0,1,0,Math.PI/4);
		tr.setRotation(rodarParaEsquerda);
		tr.setTranslation(new Vector3d(-0.4f, 0.3f, 0.2f));
		TransformGroup tgCandeeiro = new TransformGroup(tr);
		tgCandeeiro.addChild(candeeiro);
		root.addChild(tgCandeeiro);
		
		//Cadeira
		Chair cadeira = new Chair();
		cadeira.setCapability(Group.ALLOW_PICKABLE_READ);
		cadeira.setCapability(Group.ALLOW_PICKABLE_WRITE);
		tr = new Transform3D();
		tr.setScale(0.5);
		tr.setTranslation(new Vector3d(-0.3f,-0.4f,0f));
		tr.setRotation(rodarParaEsquerda);
		TransformGroup tgCadeira = new TransformGroup(tr);
	    tgCadeira.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tgCadeira.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tgCadeira.addChild(cadeira);
		root.addChild(tgCadeira);
		
		return root;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		spin.setShapeLocation(e);	
		PickResult result = spin.pickClosest();
		if(result != null){
			TransformGroup tg = (TransformGroup)(result.getNode(PickResult.TRANSFORM_GROUP));
			if(tg != null){
				Transform3D tr = new Transform3D();
				tg.getTransform(tr);
				
				Transform3D rot = new Transform3D();
				rot.rotY(Math.PI/8);
				
				tr.mul(rot);
				
				tg.setTransform(tr);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
