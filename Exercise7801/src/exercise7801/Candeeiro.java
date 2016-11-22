package exercise7801;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cylinder;

public class Candeeiro extends Group {
	
	public Candeeiro() {
		
		//Aspeto
		Appearance ap = new Appearance();
		ap.setMaterial(new Material());
		
		//Pé
		Cylinder pe = new Cylinder(0.05f,0.75f, ap);
		Transform3D trPe = new Transform3D();
		trPe.setScale(0.5);		
		TransformGroup tgPe = new TransformGroup(trPe);
		tgPe.addChild(pe);
		addChild(tgPe);
		

		//Base
		Cylinder base = new Cylinder(0.2f, 0.05f, ap);
		Transform3D trBase = new Transform3D();
		trBase.setScale(0.4);
		trBase.setTranslation(new Vector3d (0f,-0.2f,0f));
		TransformGroup tgBase = new TransformGroup(trBase);
		tgBase.addChild(base);
		addChild(tgBase);
		
		
		//Abajur
		Cylinder abajur = new Cylinder(1.5f,2.0f, ap);
		Transform3D trAbajur = new Transform3D();
		trAbajur.setScale(0.1);
		trAbajur.setTranslation(new Vector3d(0f,0.2f,0f));
		TransformGroup tgAbajur = new TransformGroup(trAbajur);
		tgAbajur.addChild(abajur);
		addChild(tgAbajur);
		
		
	}
	
}
