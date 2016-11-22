package exercise601;

import java.awt.Color;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class SquarePyramid extends Shape3D {
	
	private Color3f green = new Color3f(Color.GREEN);
	private Color3f blue = new Color3f(Color.BLUE);
	private Color3f pink = new Color3f(Color.PINK);
	private Color3f orange = new Color3f(Color.ORANGE);
	private Color3f yellow = new Color3f(Color.YELLOW);
	private Color3f gray = new Color3f(Color.GRAY);
	
	public SquarePyramid() {

		TriangleArray pyramid = new TriangleArray(18, TriangleArray.COORDINATES | GeometryArray.COLOR_3);
		pyramid.setCapability(TriangleArray.ALLOW_COLOR_WRITE);
		
		//Vértices			
		Point3f NW= new Point3f(-1.0f,-1.0f,1.0f);
		Point3f NE = new Point3f(1.0f,-1.0f,1.0f);
		Point3f SE = new Point3f(1.0f,-1.0f,-1.0f);
		Point3f SW = new Point3f(-1.0f,-1.0f,-1.0f);
		Point3f TOP = new Point3f(0.0f, 0.5f, 0.0f);
	
		//LADO
		buildTriangle(pyramid, 0, NE, TOP, NW, green);
		buildTriangle(pyramid, 3, SE, TOP, NE, yellow);
		buildTriangle(pyramid, 6, SW, TOP, SE, blue);
		buildTriangle(pyramid, 9, NW, TOP, SW, orange);
		
		//BASE
		buildTriangle(pyramid, 12, SW, SE, NE, pink);
		buildTriangle(pyramid, 15, NE, NW, SW, gray);			
		
		setGeometry(pyramid);		
	}
	
	private void buildTriangle(TriangleArray pyramidGeometry, int index, Point3f coordinate1, Point3f coordinate2, Point3f coordinate3, Color3f color) {
    	pyramidGeometry.setCoordinate(index, coordinate1);
    	pyramidGeometry.setCoordinate(index+1, coordinate2);
    	pyramidGeometry.setCoordinate(index+2, coordinate3);
    	pyramidGeometry.setColor(index, color);
    	pyramidGeometry.setColor(index+1, color);
    	pyramidGeometry.setColor(index+2, color);
	}

}