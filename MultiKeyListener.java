import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class MultiKeyListener implements KeyListener {
	
	private JPanel panel;
	public MultiKeyListener (JPanel panel) {
		this.panel = panel;
	}
	
	public void keyPressed (KeyEvent e) {
		Controller.getInstance().handleKeyPress(e.getKeyCode());
		panel.repaint();
	}
	
	public void keyReleased (KeyEvent e) {
		Controller.getInstance().handleKeyRelease(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}
