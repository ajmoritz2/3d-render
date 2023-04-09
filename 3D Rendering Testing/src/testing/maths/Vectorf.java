package testing.maths;

public abstract class Vectorf {

	protected float[] data;
	
	private int size;
	
	public Vectorf(int size) {
		this.size = size;
	}
	
	public boolean isToSize() {
		if (data.length != size) {
			System.err.println("VectorF doesn't have the correct dimension requirements!");
			return false;
		}
		return true;
	}
	
	public void editSlot(int x, float data) {
		this.data[x] = data;
	}
	
	public float getSlot(int x) {
		return this.data[x];
	}
	
	public float[] getRawVector(){
		return data;
	}

}
