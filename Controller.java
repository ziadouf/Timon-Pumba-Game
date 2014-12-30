import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;

public class Controller {
	private static Controller instance = null;
	private Set<Integer> pressedKeys = new HashSet<Integer>();
	public ArrayList<MenuItem> mainMenu = new ArrayList<MenuItem>();
	public ArrayList<MenuItem> pauseMenu = new ArrayList<MenuItem>();
	public ArrayList<MenuItem> gameoverMenu = new ArrayList<MenuItem>();
	private Game game;
	private String gameState;
	private long lastBorrow = 0;
	private MenuItem curSel;

	private Controller() {
	}

	public static Controller getInstance() {
		if (instance == null)
			instance = new Controller();
		return instance;
	}
	
	public void initializeGame() {
		gameState = "MainMenu";
		curSel = mainMenu.get(0);
		curSel.setSelected(true);
	}
	
	public void createGame() {
		try {
			game = Game.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(Graphics2D g) {
		if (gameState.equals("MainMenu")) {
			
		}
		else if (gameState.equals("Playing")) {
			try {
				borrowShapes();
			} catch (Exception e) {
			}
			Game.getCircus1().moveShapes();
			Game.getCircus2().moveShapes();
			if (Game.getCircus1().checkGameOver() || Game.getCircus2().checkGameOver()) {
				gameState = "GameOver";
				for (MenuItem item: gameoverMenu) {
					item.setSelected(false);
				}
				curSel = gameoverMenu.get(0);
				curSel.setSelected(true);
				return;
			} else {
				Game.getCircus1().checkOutOfCircus();
				Game.getCircus2().checkOutOfCircus();
			}
			Game.getCircus1().draw(g);
			Game.getCircus2().draw(g);
			moveClown();
		}
		
		else if (gameState.equals("Paused") || gameState.equals("GameOver")) {
			Game.getCircus1().draw(g);
			Game.getCircus2().draw(g);
		}
	}

	private void borrowShapes() throws Exception {
		long timeNow = System.currentTimeMillis();
		if (timeNow - lastBorrow >= Constants.NUM_SECS_BETWEEN_FALLING * 1000) {
			if (Game.shapesPool.getNumIdle() == 0)
				Game.shapesPool.addObject();
			Shape newShape = Game.shapesPool.borrowObject();
			Game.getCircus1().addShape(newShape);
			Game.getCircus2().addShape(newShape);
			lastBorrow = timeNow;
		}
	}

	public void handleKeyPress(int keyPressed) {
		if (gameState.equals("MainMenu")) {
			handleMainMenu(keyPressed);
		}
		else if (gameState.equals("Paused"))
			handlePauseMenu(keyPressed);
		else if (gameState.equals("GameOver")) {
			handleGameoverMenu(keyPressed);
		}
		else if (keyPressed == KeyEvent.VK_SPACE
				|| keyPressed == KeyEvent.VK_ESCAPE) {
			gameState = "Paused";
			for (MenuItem item: pauseMenu) {
				item.setSelected(false);
			}
			curSel = pauseMenu.get(0);
			curSel.setSelected(true);
		}
		pressedKeys.add(keyPressed);
	}

	public void handleKeyRelease(int keyPressed) {
		pressedKeys.remove(keyPressed);
	}

	public void moveClown() {
		if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
			Game.getCircus2().moveClown(Constants.CLOWN_SPEED * -1);
		} else if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
			Game.getCircus2().moveClown(Constants.CLOWN_SPEED);
		}
		if (pressedKeys.contains(KeyEvent.VK_A)) {
			Game.getCircus1().moveClown(Constants.CLOWN_SPEED * -1);
		} else if (pressedKeys.contains(KeyEvent.VK_D)) {
			Game.getCircus1().moveClown(Constants.CLOWN_SPEED);
		}
	}

	public void handlePauseMenu(int keyPressed) {
		if (keyPressed == KeyEvent.VK_DOWN) {
			curSel.setSelected(false);
			curSel = curSel.getNextItem();
			curSel.setSelected(true);
		} else if (keyPressed == KeyEvent.VK_UP) {
			curSel.setSelected(false);
			curSel = curSel.getPrevItem();
			curSel.setSelected(true);
		} else if (keyPressed == KeyEvent.VK_ENTER) {
			String sel = curSel.getLbl().getText();
			if (sel.equals(Constants.MENU_RESUME)) {
				gameState = "Playing";
			} else if (sel.equals(Constants.MENU_SAVE)) {
				saveGame();
			} else if (sel.equals(Constants.MENU_BACK)) {
				initializeGame();
				
			}
		}
	}
	
	public void handleGameoverMenu(int keyPressed) {
		if (keyPressed == KeyEvent.VK_DOWN) {
			curSel.setSelected(false);
			curSel = curSel.getNextItem();
			curSel.setSelected(true);
		} else if (keyPressed == KeyEvent.VK_UP) {
			curSel.setSelected(false);
			curSel = curSel.getPrevItem();
			curSel.setSelected(true);
		} else if (keyPressed == KeyEvent.VK_ENTER) {
			String sel = curSel.getLbl().getText();
			if (sel.equals(Constants.MENU_PLAYAGAIN)) {
				game.newGame();
				gameState = "Playing";
			} else if (sel.equals(Constants.MENU_BACK)) {
				initializeGame();
			}
		}
	}
	
	public void handleMainMenu(int keyPressed) {
		if (keyPressed == KeyEvent.VK_DOWN) {
			curSel.setSelected(false);
			curSel = curSel.getNextItem();
			curSel.setSelected(true);
		} else if (keyPressed == KeyEvent.VK_UP) {
			curSel.setSelected(false);
			curSel = curSel.getPrevItem();
			curSel.setSelected(true);
		} else if (keyPressed == KeyEvent.VK_ENTER) {
			String sel = curSel.getLbl().getText();
			if (sel.equals(Constants.MENU_NEWGAME)) {
				game.newGame();
				gameState = "Playing";
			} else if (sel.equals(Constants.MENU_CONTINUE)) {
				loadGame();
			} else if (sel.equals(Constants.MENU_EXIT)) {
				// TODO(ziadouf): Exit Game.
				System.exit(0);
			}
		}
	}

	public void saveGame() {
		// TODO(ziadouf): save to XML
	}
	
	public void loadGame() {
		// TODO(ziadouf): load from XML
	}

	public String getState() {
		return gameState;
	}
}
