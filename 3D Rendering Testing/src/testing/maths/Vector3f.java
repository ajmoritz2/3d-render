package testing.maths;

public class Vector3f extends Vectorf{
	
	public float x,y,z;

	public Vector3f(float[] data) {
		super(3);
		
		this.data = data;
		
		if (!isToSize()) {
			System.exit(0);
		}
		
		x = data[0];
		y = data[1];
		z = data[2];
		
	}
	
	public Vector3f(float x, float y, float z) {
		super(3);
		float[] data = new float[3];
		
		data[0] = x;
		data[1] = y;
		data[2] = z;
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.data = data;
	}

}
