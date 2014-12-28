import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI_JFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_JFrame frame = new GUI_JFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_JFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		updateDimensions();
		contentPane = new GUI_JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setFocusable(true);
		contentPane.setLayout(null);
		
//		Font customFont = null;
//		try {
//			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("woodcut.ttf")).deriveFont(48f);
//		} catch (FontFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        //register the font
//        try {
//			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("woodcut.ttf")));
//		} catch (FontFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    		
//		JLabel label = new JLabel("PAUSE");
//		label.setLocation(200, 200);
//		label.setSize(800, 200);
//		contentPane.add(label);
//		label.setFont(customFont);
		setContentPane(contentPane);
	}

	private void updateDimensions() {
		Constants.BORDER_MAX_WIDTH = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize().width;
		Constants.BORDER_MAX_HEIGHT = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize().height;
		Constants.P2_CIRCUS1_TOPX = 0;
		Constants.P2_CIRCUS1_TOPY = 0;
		Constants.P2_CIRCUS1_BOTTOMX = Constants.BORDER_MAX_WIDTH / 2
				- Constants.BORDER_TOLERANCE;
		Constants.P2_CIRCUS1_BOTTOMY = Constants.BORDER_MAX_HEIGHT;

		Constants.P2_CIRCUS2_TOPX = Constants.BORDER_MAX_WIDTH / 2
				+ Constants.BORDER_TOLERANCE;
		Constants.P2_CIRCUS2_TOPY = 0;
		Constants.P2_CIRCUS2_BOTTOMX = Constants.BORDER_MAX_WIDTH;
		Constants.P2_CIRCUS2_BOTTOMY = Constants.BORDER_MAX_HEIGHT;
	}

}
