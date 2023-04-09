package testing.render;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import testing.objects.Camera;
import testing.objects.Point3f;
import testing.objects.RenderableObject;

public class Renderer {

	private MainFrame main;

	Point3f point, point1;

	int x1 = 0, x2 = 0, y1 = 0, y2 = 0;

	private Camera cam;

	public Renderer(MainFrame main, Camera cam) {
		this.cam = cam;
		this.main = main;

	}

	synchronized List<RenderableObject> syncroSort(List<RenderableObject> list) {
		list.sort(Comparator.comparing(RenderableObject::getPriority));

		return list;
	}

	public void render(List<RenderableObject> objs) {
		Graphics g = main.bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, main.getSize().width, main.getSize().height);
		synchronized (objs) {
			for (RenderableObject obj : objs) {

				if (obj.calcLoc.z >= cam.near && obj.calcLoc.z <= cam.far) {
					obj.render(g);
				}

			}
		}
		g.dispose();
		main.bs.show();

	}

}
