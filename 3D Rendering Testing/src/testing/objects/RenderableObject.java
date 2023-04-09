package testing.objects;

import java.awt.Graphics;

import testing.main.Utils;
import testing.maths.Maths;
import testing.maths.Matrix4f;
import testing.maths.Vector3f;
import testing.maths.Vector4f;

public abstract class RenderableObject {
	
	// Im using column major or right-handed coords. OPENGL STYLE
	
	protected Vector4f loc;
	protected Vector4f rot;
	
	protected Vector3f centerPoint= new Vector3f(0,0,0);
	protected Vector3f masterRot = new Vector3f(0,0,0);

	
	protected Matrix4f translationMatrix;
	protected Matrix4f rotMatrixX;
	protected Matrix4f rotMatrixY;
	protected Matrix4f rotMatrixZ;
	protected Matrix4f transformationMatrix;
	protected Matrix4f worldMatrix;
	public Vector4f calcLoc = new Vector4f(0.0f,0.0f,0.0f,1.0f);
	int order = 0;

	protected Camera cam;
	
	/**
	 * 
	 * @param loc Location of the object
	 * @param rot Rotation of the Object
	 * @param cam Camera model of the render
	 */
	public RenderableObject(Vector4f loc, Vector4f rot, Camera cam) {
		this.loc = loc;
		this.rot = rot;
		this.cam = cam;
		
		translationMatrix = new Matrix4f(Utils.identityMatrix);
		worldMatrix = new Matrix4f(Utils.identityMatrix);

		rotMatrixX = new Matrix4f(Utils.identityMatrix);
		rotMatrixY = new Matrix4f(Utils.identityMatrix);
		rotMatrixZ = new Matrix4f(Utils.identityMatrix);
		
		
		
	}
	
	public void setRenderPriority(int priority) {
		this.order = priority;
	}
	
	public RenderableObject(Vector4f loc, Vector4f rot, Vector3f center, Camera cam) {
		this.loc = loc;
		this.rot = rot;
		this.cam = cam;
		this.centerPoint = center;
		translationMatrix = new Matrix4f(Utils.identityMatrix);
		worldMatrix = new Matrix4f(Utils.identityMatrix);
		rotMatrixX = new Matrix4f(Utils.identityMatrix);
		rotMatrixY = new Matrix4f(Utils.identityMatrix);
		rotMatrixZ = new Matrix4f(Utils.identityMatrix);
	}
	
	public void setCenter(Vector3f center) {
		this.centerPoint = center;
	}
	
	public void setMasterRot(Vector3f master) {
		this.masterRot = master;
	}
	
	public double getPriority() {
		return calcLoc.z;
	}
	
	public void rotateObject(float rotX, float rotY, float rotZ) 
	{
		rot.x += Math.toRadians(rotX);
		rot.y +=  Math.toRadians(rotY);
		rot.z +=  Math.toRadians(rotZ);
		
	}
	
	
	public void moveObject(float x, float y, float z) 
	{
		loc.x += x;
		loc.y += y;
		loc.z += z;
		
	}
	
	public void updateMatricies() {

		transformationMatrix = new Matrix4f(Utils.identityMatrix);
		
		translationMatrix.editSlot(3, 0, loc.x);
		translationMatrix.editSlot(3, 1, loc.y);
		translationMatrix.editSlot(3, 2, loc.z);
		
		worldMatrix.editSlot(3, 0, centerPoint.x- cam.location.x);
		worldMatrix.editSlot(3, 1, centerPoint.y - cam.location.y);
		worldMatrix.editSlot(3, 2, centerPoint.z - cam.location.z);
	
		float xRot = masterRot.x;
		float yRot = masterRot.y;
		float zRot = masterRot.z;
		
		rotMatrixX.editSlot(1, 1, (float) Math.cos(xRot));
		rotMatrixX.editSlot(2, 1, (float)-Math.sin(xRot));
		rotMatrixX.editSlot(1, 2, (float) Math.sin(xRot));
		rotMatrixX.editSlot(2, 2, (float) Math.cos(xRot));
		
		rotMatrixY.editSlot(0, 0, (float) Math.cos(yRot));
		rotMatrixY.editSlot(2, 0, (float) Math.sin(yRot));
		rotMatrixY.editSlot(0, 2, (float) -Math.sin(yRot));
		rotMatrixY.editSlot(2, 2, (float) Math.cos(yRot));
		
		rotMatrixZ.editSlot(0, 0, (float) Math.cos(zRot));
		rotMatrixZ.editSlot(1, 0, (float) -Math.sin(zRot));
		rotMatrixZ.editSlot(0, 1, (float) -Math.sin(zRot));
		rotMatrixZ.editSlot(1, 1, (float) Math.cos(zRot));
		

		transformationMatrix = Maths.multiplyMatricies(transformationMatrix, cam.projectionMatrix);
		
		transformationMatrix = Maths.multiplyMatricies(transformationMatrix, cam.rotMat);
		
		transformationMatrix = Maths.multiplyMatricies(transformationMatrix, worldMatrix);

		transformationMatrix = Maths.multiplyMatricies(transformationMatrix, rotMatrixX);
		transformationMatrix = Maths.multiplyMatricies(transformationMatrix, rotMatrixY);
		transformationMatrix = Maths.multiplyMatricies(transformationMatrix, rotMatrixZ);

		transformationMatrix = Maths.multiplyMatricies(transformationMatrix, translationMatrix);

//		Utils.printMatrix(rotMatrixX);

		
//		Vector4f locQ = new Vector4f(loc.x-cam.location.x, loc.y-cam.location.y, loc.z-cam.location.z, loc.w);

		
		calcLoc = Maths.multiplyMatrixVector(Utils.identityVector, transformationMatrix);
		
		
		
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();

}
