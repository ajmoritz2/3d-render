package buffers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import testing.maths.Vector3f;
import testing.objects.Camera;
import testing.objects.Point3f;
import testing.render.MainFrame;

public class VAO {

	// Stores info for an object in general

	private List<Point3f> verticies;
	private int position = 0;

	private VBO vbo;
	
	public boolean bound = false;

	private List<Integer> buffer;
	
	/**
	 * 
	 * @param verticies
	 * @param vbo
	 */
	public VAO(List<Point3f> verticies, VBO vbo) {
		this.buffer = new ArrayList<Integer>();
		this.verticies = verticies;
		
		bindBuffer(vbo);
	}
	


	public VAO(MainFrame main, Camera cam, Vector3f... positions) {
		List<Point3f> points = new ArrayList<Point3f>();
		for (Vector3f vec : positions) {
			points.add(new Point3f(vec.x,vec.y,vec.z,main,cam, Color.YELLOW));
		}
		this.verticies = points;
		this.buffer = new ArrayList<Integer>();

	}
	
	public void bindBuffer(VBO bufferArray) {
		this.vbo = bufferArray;

		while (vbo.vbo.hasRemaining()) {
			buffer.add(vbo.next());
		}
		bound = true;
	}
	
	public List<Point3f> getAllVerticies(){
		return verticies;
	}
	
	public Point3f nextNeededVertex() {
		return verticies.get(next());
	}
	
	public void reset() {
		position = 0;
	}
	
	public int position() {
		return position;
	}
	
	public int next() {
		position ++;
		if (!bound)
			return 0;
		return (buffer.size() > position) ? buffer.get(position-1) : 0;
		
	}
	
	

}
