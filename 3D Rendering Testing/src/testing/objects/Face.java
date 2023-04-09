package testing.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

import buffers.VAO;
import testing.maths.Vector3f;
import testing.maths.Vector4f;

public class Face extends RenderableObject {

	public VAO vao;
	Random rand = new Random();

	public Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

	public Face(Vector4f loc, Vector4f rot, Camera cam, VAO vao) {
		super(loc, rot, cam);
		this.vao = vao;
	}

	@Override
	public void render(Graphics g) {
		int[] xcoords = new int[3];
		int[] ycoords = new int[3];
		int i = 0;
		for (Point3f vertex : vao.getAllVerticies()) {
//			if (vertex.calcLoc.z > -cam.near || vertex.calcLoc.z < -cam.far) {
//				return;
//			}
			xcoords[i] = (int)vertex.rastX;
			ycoords[i] = (int)vertex.rastY;
			i++;
		}
		Polygon poly = new Polygon(xcoords, ycoords, 3);
		g.setColor(color);
		g.fillPolygon(poly);
		
		vao.getAllVerticies().forEach(vertex -> {vertex.render(g);});
	}

	@Override
	public void tick() {
		
		
		vao.getAllVerticies().forEach(vertex -> {
			vertex.tick();
//			vertex.rot.x += 0.1;
			vertex.setMasterRot(new Vector3f(this.rot.x, this.rot.y, this.rot.z));
			vertex.setCenter(new Vector3f(this.loc.x, this.loc.y, this.loc.z));
			vertex.updateMatricies();
			
		});
		updateMatricies();

	}

}
