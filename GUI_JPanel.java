import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class GUI_JPanel extends JPanel {

	Controller play = new Controller();
	
	public GUI_JPanel() {
		play.createGame();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g);
		play.render(g2D);
		this.setFocusable(true);
		addKeyListener(new MultiKeyListener());
	}
	
	class MultiKeyListener implements KeyListener {
		
		public void keyPressed (KeyEvent e) {
			Controller.getInstance().handleKeyPress(e.getKeyCode());
			repaint();
		}
		
		public void keyReleased (KeyEvent e) {
			Controller.getInstance().handleKeyRelease(e.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		
	}

}
