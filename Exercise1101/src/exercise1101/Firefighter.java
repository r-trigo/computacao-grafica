package exercise1101;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;

public class Firefighter extends Group{
	
	private float bodyPartLenght = 0.4f;
	public float bodyPartHeight = 0.1f;
	private float wheelThickness = 0.1f;
	
	public Firefighter(Appearance headAp, Appearance bodyAp, Appearance wheelsAp) {
		
		//Wheels
		Cylinder wheelRB = new Cylinder(bodyPartHeight, wheelThickness, wheelsAp);
		Transform3D trWRB = new Transform3D();
		trWRB.setTranslation(new Vector3d(-bodyPartLenght/2, 0, bodyPartLenght/2));
		//rot is the cylinder 90 degree rotation to look like a wheel
		Transform3D rot = new Transform3D();
		rot.rotX(Math.PI/2);
		trWRB.mul(rot);		
		TransformGroup tgWRB = new TransformGroup(trWRB);
		tgWRB.addChild(wheelRB);
		addChild(tgWRB);
		
		Cylinder wheelRF = new Cylinder(bodyPartHeight, wheelThickness, wheelsAp);
		Transform3D trWRF = new Transform3D();
		trWRF.setTranslation(new Vector3d(bodyPartLenght/2, 0, bodyPartLenght/2));
		trWRF.mul(rot);		
		TransformGroup tgWRF = new TransformGroup(trWRF);
		tgWRF.addChild(wheelRF);
		addChild(tgWRF);
				
		Cylinder wheelLB = new Cylinder(bodyPartHeight, wheelThickness, wheelsAp);
		Transform3D trWLB = new Transform3D();
		trWLB.setTranslation(new Vector3d(-bodyPartLenght/2, 0, -bodyPartLenght/2));
		trWLB.mul(rot);		
		TransformGroup tgWLB = new TransformGroup(trWLB);
		tgWLB.addChild(wheelLB);
		addChild(tgWLB);		
		
		Cylinder wheelLF = new Cylinder(bodyPartHeight, wheelThickness, wheelsAp);
		Transform3D trWLF = new Transform3D();
		trWLF.setTranslation(new Vector3d(bodyPartLenght/2, 0, -bodyPartLenght/2));
		trWLF.mul(rot);		
		TransformGroup tgWLF = new TransformGroup(trWLF);
		tgWLF.addChild(wheelLF);
		addChild(tgWLF);
		
		
		//Lower body
		Box lowerBody = new Box(bodyPartLenght, bodyPartHeight/2, bodyPartLenght, bodyAp);
		Transform3D trLB = new Transform3D();
		trLB.setTranslation(new Vector3d(0,bodyPartHeight*1.5,0));
		TransformGroup tgLB = new TransformGroup(trLB);
		tgLB.addChild(lowerBody);
		addChild(tgLB);
		
		//Upper body
		Box upperBody = new Box(bodyPartLenght/3, (float) (bodyPartHeight*1.5), bodyPartLenght/3, bodyAp);
		Transform3D trUB = new Transform3D();
		trUB.setTranslation(new Vector3d(bodyPartLenght/2,bodyPartHeight*3.0,0));
		TransformGroup tgUB = new TransformGroup(trUB);
		tgUB.addChild(upperBody);
		addChild(tgUB);
		
		//Head
		Sphere head = new Sphere(bodyPartLenght/3, headAp);
		Transform3D trHead = new Transform3D();
		trHead.setTranslation(new Vector3d(bodyPartLenght/2,bodyPartHeight*5.5,0));
		TransformGroup tgHead = new TransformGroup(trHead);
		tgHead.addChild(head);
		addChild(tgHead);						
	}
}