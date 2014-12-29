import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;


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
		 	JLabel label1 = new JLabel();
		    label1.setText("SCORE");
		    label1.setBounds(20, 10,300,50);
		    label1.setFont(new Font("Serif", Font.BOLD, 52));
		    label1.setForeground(new Color(255,178,102));
		   // add(label1);
		   
		keyListener = new MultiKeyListener(this);
		addKeyListener(keyListener);
		timer.start();
	}

	 /*private String myMessage = "SCORE";  
	  public void MyTestPanel(String s)  
	    {  
		  myMessage = s;  
	    } */ 
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g);
		Image background = null;
		background = Toolkit.getDefaultToolkit().getImage(
				new File("img/background6.jpg").getAbsolutePath());
		g.drawImage(background, 0, 0, null);
		
		Image scorepic = null ;
		scorepic = Toolkit.getDefaultToolkit().getImage(
				new File("img/hakuna matata11.png").getAbsolutePath());
		g.drawImage(scorepic, 470 , 0, null);
		
		//g2D.drawString(myMessage, 50, 50);
		//Font font = new Font("Serif", Font.PLAIN, 96);
		//g2D.setFont(font);
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
