import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUI_JPanel extends JPanel implements ActionListener {

	Controller play = Controller.getInstance();
	Timer timer = new Timer(25, this);
	MultiKeyListener keyListener;

	public GUI_JPanel() {
		try {
			play.createGame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keyListener = new MultiKeyListener(this);
		addKeyListener(keyListener);
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g);
		Image background = null;
		background = Toolkit.getDefaultToolkit().getImage(
				new File("img/background6.jpg").getAbsolutePath());
		g.drawImage(background, 0, 0, null);
		try {
			play.render(g2D);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			repaint();
		}
	}
}
