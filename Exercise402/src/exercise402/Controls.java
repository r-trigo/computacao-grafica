package exercise402;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Controls implements KeyListener {
	
	public boolean up, down, left, right, print;
	
	public Controls () {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() ==  KeyEvent.VK_UP) {			
			up = true;
		} else if(e.getKeyCode() ==  KeyEvent.VK_DOWN) {			
			down = true;
		} else if(e.getKeyCode() ==  KeyEvent.VK_LEFT) {			
			left = true;
		} else if(e.getKeyCode() ==  KeyEvent.VK_RIGHT) {
			right = true;
		} else if(e.getKeyCode() == KeyEvent.VK_P) {
			print = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}