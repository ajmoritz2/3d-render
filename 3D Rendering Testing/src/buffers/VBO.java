package buffers;

import java.nio.Buffer;
import java.nio.IntBuffer;

public class VBO {
	
	// Stores the info for connecting verticies.
	
	public IntBuffer vbo;
	
	/**
	 * Create a VBO
	 * @param vbo
	 */
	public VBO(int[] vbo) {
		this.vbo = IntBuffer.wrap(vbo);
	}
	
	public Integer next() {
		return vbo.get();
	}
	
	public IntBuffer getVBO() {
		return vbo;
	}

}
