import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import com.thoughtworks.xstream.XStream;

public class Controller {
	private static Controller instance = null;
	private Set<Integer> pressedKeys = new HashSet<Integer>();
	public ArrayList<MenuItem> mainMenu = new ArrayList<MenuItem>();
	public ArrayList<MenuItem> pauseMenu = new ArrayList<MenuItem>();
	public ArrayList<MenuItem> gameoverMenu = new ArrayList<MenuItem>();
	public static ArrayList<Class> classes = new ArrayList<Class>();
	private Game game;
	private String gameState;
	private long lastBorrow = 0;
	private MenuItem curSel;

	private Controller() {
		com.sun.tools.javac.Main.compile(new String[] {
	            "-classpath", "bin",
	            "-d", "bin",
	            "shapes/Bug2.java" });
	    File classesDir = new File("dynamic");
	    ClassLoader parentLoader = Shape.class.getClassLoader();
	    URLClassLoader loader1 = null;
		try {
			loader1 = new URLClassLoader(
			        new URL[] { classesDir.toURL() }, parentLoader);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Class cls1 = null;
		try {
			cls1 = loader1.loadClass("Bug2");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    classes.add(cls1);
	    
	    com.sun.tools.javac.Main.compile(new String[] {
	            "-classpath", "bin",
	            "-d", "bin",
	            "shapes/Worm2.java" });
	    classesDir = new File("dynamic");
	    parentLoader = Shape.class.getClassLoader();
	    try {
			loader1 = new URLClassLoader(
			        new URL[] { classesDir.toURL() }, parentLoader);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Class cls2 = null;
		try {
			cls2 = loader1.loadClass("Worm2");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    classes.add(cls2);	    
	}

	public static Controller getInstance() {
		if (instance == null)
			instance = new Controller();
		return instance;
	}

	public void initializeGame() {
		gameState = "MainMenu";
		for (MenuItem item : mainMenu) {
			item.setSelected(false);
		}
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

		} else if (gameState.equals("Playing")) {
			try {
				borrowShapes();
			} catch (Exception e) {
			}
			Game.getCircus1().moveShapes();
			Game.getCircus2().moveShapes();
			if (Game.getCircus1().checkGameOver()
					|| Game.getCircus2().checkGameOver()) {
				gameState = "GameOver";
				for (MenuItem item : gameoverMenu) {
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

	private void borrowShapes() {
		long timeNow = System.currentTimeMillis();
		if (timeNow - lastBorrow >= Constants.NUM_SECS_BETWEEN_FALLING * 1000) {
			if (Game.shapesPool.getNumIdle() == 0) {
				try {
					Game.shapesPool.addObject();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Shape newShape = null;
			try {
				newShape = Game.shapesPool.borrowObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Game.getCircus1().addShape(newShape);
			Game.getCircus2().addShape(newShape);
			lastBorrow = timeNow;
		}
	}

	public void handleKeyPress(int keyPressed) {
		if (gameState.equals("MainMenu")) {
			handleMainMenu(keyPressed);
		} else if (gameState.equals("Paused"))
			handlePauseMenu(keyPressed);
		else if (gameState.equals("GameOver")) {
			handleGameoverMenu(keyPressed);
		} else if (keyPressed == KeyEvent.VK_SPACE
				|| keyPressed == KeyEvent.VK_ESCAPE) {
			gameState = "Paused";
			for (MenuItem item : pauseMenu) {
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
				System.out.println("HERE");
				loadGame();
			} else if (sel.equals(Constants.MENU_EXIT)) {
				System.exit(0);
			}
		}
	}

	public void saveGame() {
		XStream xstream = new XStream();
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File("game.xml").getAbsolutePath());
			String xml = xstream.toXML(new Snapshot());
			out.println(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.close();
	}

	public void loadGame() {
		XStream xstream = new XStream();
		String xml = "";
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(new File("game.xml").getAbsolutePath()));
		} catch (FileNotFoundException e1) {
			game.newGame();
			return;
		}
		String line ;
		try {
			while (( line = in.readLine()) != null) 
			{
				xml += line ;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("JJJ");
		Snapshot s = (Snapshot) xstream.fromXML(xml);
		Game.loadGame(s);
		gameState = "Playing";
	}

	public String getState() {
		return gameState;
	}
}
