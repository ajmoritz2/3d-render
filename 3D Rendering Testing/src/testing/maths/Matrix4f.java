package testing.maths;

public class Matrix4f{
	
	public float[][] data;
	private int sizeX = 4, sizeY = 4;
	
	public boolean isToSize() {
		if (data.length != sizeX && data[0].length != sizeY) {
			System.err.println("VectorF doesn't have the correct dimension requirements!");
			return false;
		}
		return true;
	}
	
	public void editSlot(int x, int y, float data) {
		this.data[y][x] = data;
	}

	public float getSlot(int x, int y) {
		return this.data[y][x];
	}
	
	public float[][] getRawVector(){
		return data;
	}

	
	public Matrix4f(float[][] data) {
		this.data = new float[data.length][data[0].length];
		
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				this.data[x][y] = data[x][y];
			}
		}
	}		
}
