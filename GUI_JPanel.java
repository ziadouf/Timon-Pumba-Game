import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GUI_JPanel extends JPanel {

	Controller play = Controller.getInstance();
	MultiKeyListener keyListener;
	
	public GUI_JPanel() {
		play.createGame();
		keyListener = new MultiKeyListener(this);
		addKeyListener(keyListener);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g);
		play.render(g2D);
	}
}
