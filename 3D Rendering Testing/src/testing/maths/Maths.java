package testing.maths;

public class Maths {

	public static Matrix4f multiplyMatricies(Matrix4f m1, Matrix4f m2) {
		// Matrix to store the result
		// The product matrix will
		// be of size row1 x col2

		float[][] mv = m1.getRawVector();
		float[][] m2v = m2.getRawVector();

		float C[][] = new float[mv.length][m2v[0].length];

		// Multiply the two matrices
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++)
					C[i][j] += mv[i][k] * m2v[k][j];
			}
		}

		return new Matrix4f(C);
	}

	public static Vector4f multiplyMatrixVector(Vector4f v1, Matrix4f m2) {
		float[] newV = new float[4];

		for (int i = 0; i < 4; i++) {
			float value = 0;
			for (int j = 0; j < 4; j++) {
				value += m2.getRawVector()[i][j] * v1.getRawVector()[j];
			}
			newV[i] = value;
		}
		return new Vector4f(newV);
	}

	public static double dotProduct(Vector3f v1, Vector3f v2) {
		double product = 0;

		// Loop for calculate dot product
		for (int i = 0; i < v1.getRawVector().length; i++) {

			product = product + v1.getRawVector()[i] * v2.getRawVector()[i];
		}
		return product;
	}

}
