package exercise91001;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;

public class Arena extends Group {
	
	private float height = 0.5f;
	private float maxLenght = 3.0f;
	private float minLenght = 0.15f;
	
	public Arena(Appearance ap) {
		
		//North Outer Wall
		Box now = new Box(maxLenght+minLenght, height, minLenght, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, ap);
		Transform3D trNow = new Transform3D();
		trNow.setTranslation(new Vector3f(0f,height,-maxLenght));
		TransformGroup tgNow = new TransformGroup(trNow);
		tgNow.addChild(now);
		addChild(tgNow);
		
		//West Outer Wall
		Box wow = new Box(minLenght, height, maxLenght, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, ap);
		Transform3D trWow = new Transform3D();
		trWow.setTranslation(new Vector3f(-maxLenght,height,0f));
		TransformGroup tgWow = new TransformGroup(trWow);
		tgWow.addChild(wow);
		addChild(tgWow);
		
		//South Outer Wall
		Box sow = new Box(maxLenght+minLenght, height, minLenght, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, ap);		
		Transform3D trSow = new Transform3D();
		trSow.setTranslation(new Vector3f(0f,height,maxLenght));
		TransformGroup tgSow = new TransformGroup(trSow);
		tgSow.addChild(sow);
		addChild(tgSow);
		
		//East Outer Wall
		Box eow = new Box(minLenght, height, maxLenght, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, ap);		
		Transform3D trEow = new Transform3D();
		trEow.setTranslation(new Vector3f(maxLenght,height,0f));
		TransformGroup tgEow = new TransformGroup(trEow);
		tgEow.addChild(eow);
		addChild(tgEow);
		
		//North Inner Wall
		Box niw = new Box((maxLenght/3)/4, height, minLenght, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, ap);
		Transform3D trNiw = new Transform3D();
		trNiw.setTranslation(new Vector3f((maxLenght/3)+minLenght, height, (maxLenght/3)));
		TransformGroup tgNiw = new TransformGroup(trNiw);
		tgNiw.addChild(niw);
		addChild(tgNiw);
				
		//West Inner Wall
		Box wiw = new Box(minLenght, height, maxLenght/3, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, ap);
		Transform3D trWiw = new Transform3D();
		trWiw.setTranslation(new Vector3f((maxLenght/3)-minLenght, height, (maxLenght/3)*2-minLenght));
		TransformGroup tgWiw = new TransformGroup(trWiw);
		tgWiw.addChild(wiw);
		addChild(tgWiw);
		
	}
}