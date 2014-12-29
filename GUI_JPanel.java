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

	public GUI_JPanel() {
		try {
			play.createGame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label1 = new JLabel();
		label1.setText("SCORE");
		label1.setBounds(20, 10, 300, 50);
		label1.setFont(new Font("Serif", Font.BOLD, 52));
		label1.setForeground(new Color(255, 178, 102));
		// add(label1);

		keyListener = new MultiKeyListener(this);
		addKeyListener(keyListener);
		initiate();
		play.initializeGame();
		timer.start();
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
		// el satr el tany kan: g.drawImage(background,0,0,null);
		// Draw Game
		try {
			play.render(g2D);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Draw Menus
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
			repaint();
		}
	}
}
