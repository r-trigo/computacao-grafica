package exercise7801;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;

public class Chair extends Group{
	
	public Chair() {
		
		float legHeight = 0.4f;
	    float legRadius = 0.05f;
	    float surfaceSize = 0.8f;
	    float surfaceThickness = 0.1f;
	    
		//Aspeto
		Appearance ap = new Appearance();
		ap.setMaterial(new Material());
		
		//Assento
		Box seat = new Box(surfaceSize/2, surfaceThickness/2, surfaceSize/2, ap);		
		Transform3D trSeat = new Transform3D();
		TransformGroup tgSeat = new TransformGroup(trSeat);
		tgSeat.addChild(seat);
		addChild(tgSeat);
		
		//Leg1
		Cylinder leg1 = new Cylinder(legRadius,legHeight,ap);
		Transform3D trLeg1 = new Transform3D();		
		trLeg1.setTranslation(new Vector3d(-(surfaceSize/2)+legRadius,
											-(legHeight/2)-(surfaceThickness/2),
											(surfaceSize/2)-(legRadius/2)));
		TransformGroup tgLeg1 = new TransformGroup(trLeg1);
		tgLeg1.addChild(leg1);
		addChild(tgLeg1);
		
		//Leg2
		Cylinder leg2 = new Cylinder(legRadius,legHeight,ap);
		Transform3D trLeg2 = new Transform3D();
		trLeg2.setTranslation(new Vector3d((surfaceSize/2)-legRadius,
											-(legHeight/2)-(surfaceThickness/2),
											(surfaceSize/2)-(legRadius/2)));
		TransformGroup tgLeg2 = new TransformGroup(trLeg2);
		tgLeg2.addChild(leg2);
		addChild(tgLeg2);
		
		//Leg3
		Cylinder leg3 = new Cylinder(legRadius,legHeight,ap);
		Transform3D trLeg3 = new Transform3D();
		trLeg3.setTranslation(new Vector3d(-(surfaceSize/2)+legRadius,
											-(legHeight/2)-(surfaceThickness/2),
											-(surfaceSize/2)+(legRadius/2)));
		TransformGroup tgLeg3 = new TransformGroup(trLeg3);
		tgLeg3.addChild(leg3);
		addChild(tgLeg3);
		
		//Leg4
		Cylinder leg4 = new Cylinder(legRadius,legHeight,ap);
		Transform3D trLeg4 = new Transform3D();
		trLeg4.setTranslation(new Vector3d((surfaceSize/2)-legRadius,
										-(legHeight/2)-(surfaceThickness/2),
										-(surfaceSize/2)+(legRadius/2)));
		TransformGroup tgLeg4 = new TransformGroup(trLeg4);
		tgLeg4.addChild(leg4);
		addChild(tgLeg4);
		
		//Back
		Box back = new Box(surfaceSize/2, surfaceSize/2, surfaceThickness/2, ap);
		Transform3D trBack = new Transform3D();
		trBack.setTranslation(new Vector3d(0f,
										surfaceSize/2 + surfaceThickness/2,
										-(surfaceSize/2)+(legRadius/2)));
		TransformGroup tgBack = new TransformGroup(trBack);
		tgBack.addChild(back);
		addChild(tgBack);
		
	}

}