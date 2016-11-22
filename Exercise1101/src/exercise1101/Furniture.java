package exercise1101;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.geometry.Cylinder;

public class Furniture extends Group {
	
	public float height = 1f;
	
	public Furniture() {
		
		Material goldenMetal = new Material(
				new Color3f(0.24725f, 0.2245f, 0.0645f),
				new Color3f(0.34615f, 0.3143f, 0.0903f),
				new Color3f(0f, 0f, 0f),
				new Color3f(0.797357f, 0.723991f, 0.208006f),
				83.2f);
		
		Appearance furnitureAp = new Appearance();
		furnitureAp.setMaterial(goldenMetal);
		
		Cylinder furniture = new Cylinder(0.325f, height, furnitureAp);
		addChild(furniture);		
		
	}

}
