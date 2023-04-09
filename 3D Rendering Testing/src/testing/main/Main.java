package testing.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import buffers.VAO;
import buffers.VBO;
import testing.maths.Vector3f;
import testing.maths.Vector4f;
import testing.objects.Camera;
import testing.objects.Face;
import testing.objects.RenderableObject;
import testing.render.MainFrame;
import testing.render.Renderer;

public class Main {

	public static MainFrame mainFrame;
	public Thread ticks;
	public Thread ticks2;

	public Renderer renderer;
	public Camera cam;

	public VBO vbo;
	public VAO vao;
	public Face face;

	public VBO vbo2;
	public VAO vao2;
	public Face face2;

	public static final double movement = 0.1;
	public static final double rotatespeed = 0.1;

	public List<RenderableObject> objList = new ArrayList<RenderableObject>();
	public List<RenderableObject> renderList;

	public static boolean isRunning = true;

	private long pastTime = 0;
	private int loopLength = 20;

	public Main() {
		mainFrame = new MainFrame();

		mainFrame.setSize(600, 400);
		mainFrame.createFrame(false);
		mainFrame.frame.addKeyListener(new Inputs());

		cam = new Camera(new Vector3f(0, 0, 10.0f), new Vector3f(0, 0, 0)); // Facing -z
		renderer = new Renderer(mainFrame, cam);

		vbo = new VBO(new int[] { 0, 1, 2 });
		vao = new VAO(mainFrame, cam, new Vector3f(-1.0f, 0.0f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f),
				new Vector3f(0.0f, 1.0f, 0.0f));
		
		vao.bindBuffer(vbo);
		face = new Face(new Vector4f(0.0f, -10.0f, -10.0f, 1.0f), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), cam, vao);

		vbo2 = new VBO(new int[] { 0, 1, 2 });
		vao2 = new VAO(mainFrame, cam, new Vector3f(10.0f, -3.0f, 1.0f), new Vector3f(10.0f, 0.0f, 0.0f),
				new Vector3f(-1.0f, 0.0f, 0.0f));
		vao2.bindBuffer(vbo2);
		face2 = new Face(new Vector4f(0.0f, 0.0f, -20.0f, 1.0f), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), cam, vao2);

		face.setRenderPriority(10);

		objList.add(face);
//		objList.add(face2);

		do {
			long currentTime = System.currentTimeMillis();
			if (currentTime - pastTime > loopLength) {
				face.rotateObject(1, 0f, 0.0f);
				objList.sort(Comparator.comparing(RenderableObject::getPriority).reversed());
				tick();
				render();
			}

		} while (!mainFrame.bs.contentsLost() && isRunning);
	}
	
	public void tick() {
		cam.configureViewMatrix();
		objList.forEach(obj -> {
			obj.tick();
		});
	}

	public void render() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - pastTime > loopLength) {
			checkForInputs();
			renderer.render(objList);
			pastTime = currentTime;

		}
	}

	private void checkForInputs() {
		if (Inputs.keys[0]) {
			cam.location.z -= Math.cos(cam.rotation.y) * movement;
			cam.location.x += Math.sin(cam.rotation.y) * movement;
		}
		if (Inputs.keys[1]) {
			cam.location.z -= Math.sin(cam.rotation.y) * movement;
			cam.location.x -= Math.cos(cam.rotation.y) * movement;
		}
		if (Inputs.keys[2]) {
			cam.location.z += Math.cos(cam.rotation.y) * movement;
			cam.location.x -= Math.sin(cam.rotation.y) * movement;
		}
		if (Inputs.keys[3]) {
			cam.location.z += Math.sin(cam.rotation.y) * movement;
			cam.location.x += Math.cos(cam.rotation.y) * movement;
		}
		if (Inputs.keys[4]) {
			cam.rotateCamera((float)rotatespeed, 0, 0);
		}
		if (Inputs.keys[5]) {
			cam.rotateCamera((float)-rotatespeed, 0, 0);
		}
		if (Inputs.keys[6]) {
			cam.rotateCamera(0,(float)-rotatespeed, 0);
		}
		if (Inputs.keys[7]) {
			cam.rotateCamera(0,(float)rotatespeed, 0);
		}

		if (Inputs.keys[8]) {
			cam.location.y += movement;
		}

		if (Inputs.keys[9]) {
			cam.location.y -= movement;
		}

	}

	private void moveForward() {

	}

	public static void main(String[] args) {
		new Main();
	}

}
