package testing.main;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;

import testing.objects.RenderableObject;

public class Tick extends Thread {

	private List<RenderableObject> objList;

	private long pastTime = 0;
	private long loopLength = 10;

	public Tick(List<RenderableObject> objList) {
		this.objList = objList;

	}

	@Override
	public synchronized void run() {

		do {
			long currentTime = System.currentTimeMillis();

			if (currentTime - pastTime > loopLength) {
				synchronized (objList) {
					
					pastTime = currentTime;
				}

			}

		} while (!Main.mainFrame.bs.contentsLost() && Main.isRunning);

	}

}
