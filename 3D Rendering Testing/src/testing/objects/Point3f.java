package testing.objects;

import java.awt.Color;
import java.awt.Graphics;

import testing.maths.Maths;
import testing.maths.Vector3f;
import testing.maths.Vector4f;
import testing.render.MainFrame;

public class Point3f extends RenderableObject{
	
	private MainFrame main;
	
	private Color color;
	
	public float rastX = 0, rastY = 0;
	
	public boolean rendered = false;
	
	public Point3f(float x, float y, float z, MainFrame main, Camera cam, Color color) {
		super(new Vector4f(x,y,z,1), new Vector4f(3.14f,0,0,1), cam);
		this.color = color;
		this.main = main;
	}
	
	public Point3f(float x, float y, float z, Vector3f center,MainFrame main, Camera cam, Color color) {
		super(new Vector4f(x,y,z,1), new Vector4f(3.14f,0,0,1), center, cam);
		this.color = color;
		this.main = main;
	}
	
	
	@Override
	public void tick() {
		float halfW = main.getSize().width/2;
		float halfH = main.getSize().height/2;
		
		rastX = (float) (((calcLoc.x/calcLoc.z))*halfW+halfW); //rasterization of points
		rastY = (float) (((calcLoc.y/calcLoc.z))*halfH+halfH);
		updateMatricies();
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		rendered = false;
		
		if (calcLoc.z > cam.near) {
			rendered = true;
			g.fillRect((int)rastX, (int)rastY, 2, 2);
		}
		
		
	}
}	