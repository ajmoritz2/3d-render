package testing.maths;

public abstract class Matrixf {
	
	protected float[][] data;
	private int sizeX, sizeY;
	
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
		return this.data[x][y];
	}
	
	public float[][] getRawVector(){
		return data;
	}

	
	public Matrixf(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

}
