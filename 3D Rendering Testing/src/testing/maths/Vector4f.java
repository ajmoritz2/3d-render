package testing.maths;

public class Vector4f extends Vectorf{
	
	public float x,y,z,w;
	
	public Vector4f(float[] data) {
		super(4);
		this.data = data;

		if (!isToSize()) {
			System.exit(0);
		}

		
		x = data[0];
		y = data[1];
		z = data[2];
		w = data[3];
	}
	
	public Vector4f(float x, float y, float z, float w) {
		super(3);
		float[] data = new float[4];
		
		data[0] = x;
		data[1] = y;
		data[2] = z;
		data[3] = w;
		
		this.data = data;
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

}
