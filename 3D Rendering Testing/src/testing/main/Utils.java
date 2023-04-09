package testing.main;

import testing.maths.Matrix4f;
import testing.maths.Vector4f;

public class Utils {

	public static final float[][] identityMatrix = {{1,0,0,0},
													{0,1,0,0},
													{0,0,1,0},
													{0,0,0,1}}; 
	public static final Vector4f identityVector = new Vector4f(1,1,1,1);
	
	public static void printMatrix(Matrix4f m) {
		for (int x = 0; x < m.getRawVector().length; x++) {
			for (int y = 0; y < m.getRawVector()[x].length; y++) {
				System.out.print(m.getRawVector()[x][y] + ", ");
			}
			System.out.println();
		}
		
		System.out.println("_______________________________________________");
	}

}
