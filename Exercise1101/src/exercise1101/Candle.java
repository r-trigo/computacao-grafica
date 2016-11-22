package exercise1101;

import javax.media.j3d.QuadArray;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

public class Candle extends QuadArray {
	
	public Candle() {
		super(4, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2);
		
		setCoordinate(0, new Point3f(-1, -1, 0));
		setCoordinate(1, new Point3f(1, -1, 0));
		setCoordinate(2, new Point3f(1, 1, 0));
		setCoordinate(3, new Point3f(-1, 1, 0));
		
		setTextureCoordinate(0, 0, new TexCoord2f(0f, 0f));
		setTextureCoordinate(0, 1, new TexCoord2f(1f, 0f));
		setTextureCoordinate(0, 2, new TexCoord2f(1f, 1f));
		setTextureCoordinate(0, 3, new TexCoord2f(0f, 1f));				
	}
}
