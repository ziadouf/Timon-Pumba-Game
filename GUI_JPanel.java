import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class GUI_JPanel extends JPanel implements ActionListener {

	Controller play = Controller.getInstance();
	Timer timer = new Timer(25, this);
	MultiKeyListener keyListener;
	Font woodFont = null;
	MenuItem pauseMenuResume, pauseMenuSave, pauseMenuBack;
	MenuItem gameoverMenuPlay, gameoverMenuBack;
	MenuItem mainMenuNewGame, mainMenuContinue, mainMenuExit;
	int timonScoreAmount = 0 ;
	int pumbaScoreAmount = 0 ;
	JLabel  timonScore , pumbaScore , label1 , label2 ;

	public GUI_JPanel() {
		try {
			play.createGame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		keyListener = new MultiKeyListener(this);
		addKeyListener(keyListener);
		initiate();
		play.initializeGame();
		timer.start();
		
		label1 = new JLabel();
		label1.setText("TIMON");
		label1.setLocation(180, 50);
	    label1.setSize(40, 30);
		label1.setFont(woodFont);
	    label1.setForeground(new Color(255, 173, 101));
	    label1.setSize(label1.getPreferredSize());
		//add(label1);
	    
	    label2 = new JLabel();
		label2.setText("PUMBA");
		label2.setLocation(1000, 50);
	    label2.setSize(40, 30);
		label2.setFont(woodFont);
	    label2.setForeground(new Color(255, 173, 101));
	    label2.setSize(label2.getPreferredSize());
	    //add(label2);
		
		 timonScore = new JLabel(""+timonScoreAmount);
	     timonScore.setLocation(510, 90);
	     timonScore.setSize(40, 30);
	     timonScore.setHorizontalAlignment(0);
	     //add(timonScore);
	     
	     pumbaScore = new JLabel(""+pumbaScoreAmount);
	     pumbaScore.setLocation(769, 90);
	     pumbaScore.setSize(40, 30);
	     pumbaScore.setHorizontalAlignment(0);
	     //add(pumbaScore);
	     
	     timonScore.setFont(woodFont);
	     timonScore.setForeground(new Color(255, 173, 101));
	     timonScore.setSize(timonScore.getPreferredSize());
	     
	     pumbaScore.setFont(woodFont);
	     pumbaScore.setForeground(new Color(255, 173, 101));
	     pumbaScore.setSize(pumbaScore.getPreferredSize());
     
	}
	    
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g);

		hideLabels();
		// Draw Background
		Image background = null;
		background = Toolkit.getDefaultToolkit().getImage(
				new File("img/background6.jpg").getAbsolutePath());
		g2D.drawImage(background, 0, 0, Constants.BORDER_MAX_WIDTH,
				Constants.BORDER_MAX_HEIGHT, null);
		
		// g2D.drawString(myMessage, 50, 50);
		// Font font = new Font("Serif", Font.PLAIN, 96);
		// g2D.setFont(font);
		
		// Draw Game
		try {
			play.render(g2D);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Draw Menus
		Image cornerpic = null ;
		cornerpic = Toolkit.getDefaultToolkit().getImage(
				new File("img/brown3.png").getAbsolutePath());
		Image cornerpic2 = null ;
		cornerpic2 = Toolkit.getDefaultToolkit().getImage(
				new File("img/brown5.png").getAbsolutePath());
		if (play.getState().equals("Playing") || play.getState().equals("Paused"))
			g.drawImage(cornerpic, -20, -20, null);
		if (play.getState().equals("Playing") || play.getState().equals("Paused"))
			g.drawImage(cornerpic2, 888, -20, null);
		if (play.getState().equals("Paused"))
			drawPauseMenu(g2D);
		if (play.getState().equals("GameOver"))
			drawGameoverMenu(g2D);
		if (play.getState().equals("MainMenu"))
			drawMainMenu(g2D);
		
		Image scorepic = null;
		scorepic = Toolkit.getDefaultToolkit().getImage(
				new File("img/hakuna matata11.png").getAbsolutePath());
		
		if (play.getState().equals("Playing") || play.getState().equals("Paused"))
			g.drawImage(scorepic, 470, 0, null);
		if (play.getState().equals("Playing") || play.getState().equals("Paused")) {
			add(pumbaScore);
			add(timonScore);
		}
		else {
			remove(pumbaScore);
			remove(timonScore);
		}
		//if (play.getState().equals("Playing") || play.getState().equals("Paused")) add(label1) ;
		//if (play.getState().equals("Playing") || play.getState().equals("Paused")) add(label2) ;
	}

	private void drawPauseMenu(Graphics2D g) {
		g.drawImage(
				Toolkit.getDefaultToolkit().getImage(
						new File("img/semi_transparent_black.png")
								.getAbsolutePath()), 0, 0, null);
		for (MenuItem item : play.pauseMenu)
			item.getLbl().setVisible(true);
	}

	private void drawGameoverMenu(Graphics2D g) {
		g.drawImage(
				Toolkit.getDefaultToolkit().getImage(
						new File("img/semi_transparent_black.png")
								.getAbsolutePath()), 0, 0, null);
		for (MenuItem item : play.gameoverMenu)
			item.getLbl().setVisible(true);
	}

	private void drawMainMenu(Graphics2D g) {
		g.drawImage(
				Toolkit.getDefaultToolkit().getImage(
						new File("img/semi_transparent_black.png")
								.getAbsolutePath()), 0, 0, null);
		for (MenuItem item : play.mainMenu)
			item.getLbl().setVisible(true);
	}

	private void initiate() {
		try {
			woodFont = Font.createFont(Font.TRUETYPE_FONT,
					new File("woodcut.ttf")).deriveFont(30f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(
					"woodcut.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		JLabel lbl;
		// PAUSE MENU
		lbl = new JLabel(Constants.MENU_RESUME);
		lbl.setLocation(0, (int) (Constants.BORDER_MAX_HEIGHT / 2 - 5 * lbl
				.getPreferredSize().getHeight()));
		adjustLabel(lbl);
		pauseMenuResume = new MenuItem(lbl);

		lbl = new JLabel(Constants.MENU_SAVE);
		lbl.setLocation(0, Constants.BORDER_MAX_HEIGHT / 2);
		adjustLabel(lbl);
		pauseMenuSave = new MenuItem(lbl);

		lbl = new JLabel(Constants.MENU_BACK);
		lbl.setLocation(0, (int) (Constants.BORDER_MAX_HEIGHT / 2 + 5 * lbl
				.getPreferredSize().getHeight()));
		adjustLabel(lbl);
		pauseMenuBack = new MenuItem(lbl);

		pauseMenuResume.setPrevItem(pauseMenuResume);
		pauseMenuResume.setNextItem(pauseMenuSave);
		pauseMenuSave.setPrevItem(pauseMenuResume);
		pauseMenuSave.setNextItem(pauseMenuBack);
		pauseMenuBack.setPrevItem(pauseMenuSave);
		pauseMenuBack.setNextItem(pauseMenuBack);
		play.pauseMenu.add(pauseMenuResume);
		play.pauseMenu.add(pauseMenuSave);
		play.pauseMenu.add(pauseMenuBack);

		// GAMEOVER MENU
		lbl = new JLabel(Constants.MENU_PLAYAGAIN);
		lbl.setLocation(0, (int) (Constants.BORDER_MAX_HEIGHT / 2 - 1 * lbl
				.getPreferredSize().getHeight()));
		adjustLabel(lbl);
		gameoverMenuPlay = new MenuItem(lbl);

		lbl = new JLabel(Constants.MENU_BACK);
		lbl.setLocation(0, (int) (Constants.BORDER_MAX_HEIGHT / 2 + 5 * lbl
				.getPreferredSize().getHeight()));
		adjustLabel(lbl);
		gameoverMenuBack = new MenuItem(lbl);

		gameoverMenuPlay.setNextItem(gameoverMenuBack);
		gameoverMenuPlay.setPrevItem(gameoverMenuPlay);
		gameoverMenuBack.setNextItem(gameoverMenuBack);
		gameoverMenuBack.setPrevItem(gameoverMenuPlay);
		play.gameoverMenu.add(gameoverMenuPlay);
		play.gameoverMenu.add(gameoverMenuBack);

		// MAIN MENU
		lbl = new JLabel(Constants.MENU_NEWGAME);
		lbl.setLocation(0, (int) (Constants.BORDER_MAX_HEIGHT / 2 - 5 * lbl
				.getPreferredSize().getHeight()));
		adjustLabel(lbl);
		mainMenuNewGame = new MenuItem(lbl);

		lbl = new JLabel(Constants.MENU_CONTINUE);
		lbl.setLocation(0, Constants.BORDER_MAX_HEIGHT / 2);
		adjustLabel(lbl);
		mainMenuContinue = new MenuItem(lbl);

		lbl = new JLabel(Constants.MENU_EXIT);
		lbl.setLocation(0, (int) (Constants.BORDER_MAX_HEIGHT / 2 + 5 * lbl
				.getPreferredSize().getHeight()));
		adjustLabel(lbl);
		mainMenuExit = new MenuItem(lbl);

		mainMenuNewGame.setNextItem(mainMenuContinue);
		mainMenuNewGame.setPrevItem(mainMenuNewGame);
		mainMenuContinue.setNextItem(mainMenuExit);
		mainMenuContinue.setPrevItem(mainMenuNewGame);
		mainMenuExit.setNextItem(mainMenuExit);
		mainMenuExit.setPrevItem(mainMenuContinue);
		play.mainMenu.add(mainMenuNewGame);
		play.mainMenu.add(mainMenuContinue);
		play.mainMenu.add(mainMenuExit);
	}

	private void hideLabels() {
		for (MenuItem item : play.pauseMenu)
			item.getLbl().setVisible(false);
		for (MenuItem item : play.gameoverMenu)
			item.getLbl().setVisible(false);
		for (MenuItem item : play.mainMenu)
			item.getLbl().setVisible(false);
	}

	private void adjustLabel(JLabel lbl) {
		lbl.setFont(woodFont);
		lbl.setForeground(new Color(255, 173, 101));
		lbl.setSize(lbl.getPreferredSize());
		int posx = Constants.BORDER_MAX_WIDTH / 2 - lbl.getWidth() / 2;
		lbl.setLocation(posx, (int) lbl.getLocation().getY());
		add(lbl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			timonScore.setText(String.valueOf(Game.getCircus1().getScore().getScore()));
			pumbaScore.setText(String.valueOf(Game.getCircus2().getScore().getScore()));
			timonScore.setSize(timonScore.getPreferredSize());
			pumbaScore.setSize(pumbaScore.getPreferredSize());
			repaint();
		}
		
	}
}
