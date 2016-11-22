package exercise601;

import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class ChessFloor extends Shape3D {

	public ChessFloor() {
		
		int m = 21;
		float a = -6f;
		float b = 6f;
		float divX = (b - a) / m;

		int n = 21;
		float c = -6f;
		float d = 6f;
		float divZ = (d - c) / m;

		int totalPts = m * n * 4;

		Point3f[] pts = new Point3f[totalPts];
		Color3f[] col = new Color3f[totalPts];

		int idx = 0;
		boolean black = true;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				float x = a + i * divX;
				float z = c + j * divZ;
				float y = 0f;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (black ? new Color3f(0f, 0f, 0f) : new Color3f(1f,
						1f, 1f));
				idx++;

				x = a + i * divX;
				z = c + (j + 1) * divZ;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (black ? new Color3f(0f, 0f, 0f) : new Color3f(1f,
						1f, 1f));
				idx++;

				x = a + (i + 1) * divX;
				z = c + (j + 1) * divZ;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (black ? new Color3f(0f, 0f, 0f) : new Color3f(1f,
						1f, 1f));
				idx++;

				x = a + (i + 1) * divX;
				z = c + j * divZ;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (black ? new Color3f(0f, 0f, 0f) : new Color3f(1f,
						1f, 1f));
				idx++;

				black = !black;
			}
		}

		QuadArray gi = new QuadArray(totalPts, QuadArray.COORDINATES | QuadArray.COLOR_3);
		gi.setCoordinates(0, pts);
		gi.setColors(0, col);
		
		setGeometry(gi);
		
	}
	
}
