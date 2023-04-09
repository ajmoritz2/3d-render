package testing.objects;

import testing.main.Utils;
import testing.maths.Maths;
import testing.maths.Matrix4f;
import testing.maths.Vector3f;

public class Camera {
	
	public float FOVx = (float) Math.toRadians(70);

	public final float FOVy =  (float) Math.toRadians(120);

	public final float ASPECT_RATIO = 1.5F;
	
	public final float far = 2000, near = 2f;
	
	
	public Vector3f location;
	public Vector3f rotation;
	
	public float[][] projectionMatrixData = new float[4][4];
	
	public Matrix4f projectionMatrix;
	
	public Matrix4f rotMatrixX, rotMatrixY, rotMatrixZ;
	
	public Matrix4f rotMat = new Matrix4f(Utils.identityMatrix);
	
	public Vector3f forward = new Vector3f(0.0f,0.0f,0.0f);
	public Vector3f right = new Vector3f(0.0f,0.0f,0.0f);
	public Vector3f up = new Vector3f(0.0f,0.0f,0.0f);
	
	
	/* ROTATION VALUES
	 * 
	 * X: PITCH
	 * Y: YAW
	 * Z: ROLL
	 */
	public Camera(Vector3f location, Vector3f rotation) {
		this.location = location;
		this.rotation = rotation;
		projectionMatrix = zeroMatrix();
		rotMatrixX = new Matrix4f(Utils.identityMatrix);
		rotMatrixY = new Matrix4f(Utils.identityMatrix);
		rotMatrixZ = new Matrix4f(Utils.identityMatrix);
		
		setViewMatrixValues();
		configureViewMatrix();

	}
	
	private void setViewMatrixValues() {
		float FocalLength = (float) Math.tan(FOVx / 2.0f);
		
		float X = 1.0f/ (ASPECT_RATIO * FocalLength);
		float Y = 1.0f / FocalLength;
		float Z = -((far + near) / (far - near));
		float A = -1.0f;
		float B = -((2.0f *far * near) / (far- near));
		
		projectionMatrix.editSlot(0, 0, X);
		projectionMatrix.editSlot(1, 1, Y);
		projectionMatrix.editSlot(2, 2, Z);
		projectionMatrix.editSlot(3, 2, A);
		projectionMatrix.editSlot(2, 3, B);
		
	}
	
	public void rotateCamera(float x, float y, float z) {
		rotation.x += x;
		rotation.y += y;
		rotation.z += z;
		configureViewMatrix();
	}
	
	public void configureViewMatrix() {
		rotMat = new Matrix4f(Utils.identityMatrix);
		rotMatrixX.editSlot(1, 1, (float) Math.cos(rotation.x));
		rotMatrixX.editSlot(2, 1, (float)-Math.sin(rotation.x));
		rotMatrixX.editSlot(1, 2, (float) Math.sin(rotation.x));
		rotMatrixX.editSlot(2, 2, (float) Math.cos(rotation.x));
		
		rotMatrixY.editSlot(0, 0, (float) Math.cos(rotation.y));
		rotMatrixY.editSlot(2, 0, (float) Math.sin(rotation.y));
		rotMatrixY.editSlot(0, 2, (float) -Math.sin(rotation.y));
		rotMatrixY.editSlot(2, 2, (float) Math.cos(rotation.y));
		
		rotMatrixZ.editSlot(0, 0, (float) Math.cos(rotation.z));
		rotMatrixZ.editSlot(1, 0, (float) -Math.sin(rotation.z));
		rotMatrixZ.editSlot(0, 1, (float) -Math.sin(rotation.z));
		rotMatrixZ.editSlot(1, 1, (float) Math.cos(rotation.z));
		
		rotMat = Maths.multiplyMatricies(rotMat, rotMatrixX);
		rotMat = Maths.multiplyMatricies(rotMat, rotMatrixY);
		rotMat = Maths.multiplyMatricies(rotMat, rotMatrixZ);

	}
	
	public void changeFOV(float FOV) {
		this.FOVx = FOV;
		setViewMatrixValues();
	}
	
	private Matrix4f zeroMatrix() {
		for (int x = 0; x< projectionMatrixData.length; x++) {
			for (int y = 0; y< projectionMatrixData[0].length; y++) {
				projectionMatrixData[x][y] = 0;
			}
		}
		Matrix4f matrix = new Matrix4f(projectionMatrixData);
		return matrix;
	}

}
