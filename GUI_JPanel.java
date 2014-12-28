import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
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
		BufferedImage background = null;
		try {
			background = ImageIO
					.read(new File("img/background6.jpg"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2D.drawImage(background,0,0,1366,780,null);
		
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
