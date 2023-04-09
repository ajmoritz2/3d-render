package testing.render;

import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class MainFrame {
	
	public JFrame frame;
	
	private String TITLE = "This is a JFrame!";
	
	public BufferStrategy bs;
	
	private Dimension size = new Dimension(800,500);

	public MainFrame() {
		frame = new JFrame();
	}
	
	public void setSize(int width, int height) {
		size = new Dimension(width, height);
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public void setTitle(String title) {
		this.TITLE = title;
	}
	
	public void createFrame(boolean resizable) {
		frame.setResizable(resizable);
		frame.setTitle(TITLE);
		frame.setSize(size);
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bs = frame.getBufferStrategy();
	}
	
	public void render() {
		
	}
	
	public Dimension getSize() {
		return size;
	}

}
