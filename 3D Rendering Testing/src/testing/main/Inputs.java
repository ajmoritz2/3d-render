package testing.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Inputs implements KeyListener{

	public static boolean[] keys = new boolean[10];

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			keys[0] = true;
			break;
		case KeyEvent.VK_A:
			keys[1] = true;
			break;
		case KeyEvent.VK_S:
			keys[2] = true;
			break;
		case KeyEvent.VK_D:
			keys[3] = true;
			break;
		case KeyEvent.VK_UP:
			keys[4] = true;
			break;
		case KeyEvent.VK_DOWN:
			keys[5] = true;
			break;
		case KeyEvent.VK_LEFT:
			keys[6] = true;
			break;
		case KeyEvent.VK_RIGHT:
			keys[7] = true;
			break;
		case KeyEvent.VK_F:
			keys[8] = true;
			break;
		case KeyEvent.VK_C:
			keys[9] = true;
			break;
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			keys[0] = false;
			break;
		case KeyEvent.VK_A:
			keys[1] = false;
			break;
		case KeyEvent.VK_S:
			keys[2] = false;
			break;
		case KeyEvent.VK_D:
			keys[3] = false;
			break;
		case KeyEvent.VK_UP:
			keys[4] = false;
			break;
		case KeyEvent.VK_DOWN:
			keys[5] = false;
			break;
		case KeyEvent.VK_LEFT:
			keys[6] = false;
			break;
		case KeyEvent.VK_RIGHT:
			keys[7] = false;
			break;
		case KeyEvent.VK_F:
			keys[8] = false;
			break;
		case KeyEvent.VK_C:
			keys[9] = false;
			break;
		}
	}

}
