import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;

public class Controller {
	private static Controller instance = null;
	private Set<Integer> pressedKeys = new HashSet<Integer>();
	private Game game;
	private boolean isPaused = false;
	private long lastBorrow = 0;

	private Controller() {
	}

	public static Controller getInstance() {
		if (instance == null)
			instance = new Controller();
		return instance;
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
		if (!isPaused) {
			try {
				borrowShapes();
			} catch (Exception e) {}
			Game.getCircus1().moveShapes();
			Game.getCircus2().moveShapes();
			Game.getCircus1().checkOutOfCircus();
			Game.getCircus2().checkOutOfCircus();
		}
		Game.getCircus1().draw(g);
		Game.getCircus2().draw(g);
		if (!isPaused) {
			moveClown();
		}
		if (isPaused) {
//			BufferedImage image = null;
//			try {
//				image = ImageIO.read(new URL("http://themusictest.files.wordpress.com/2010/06/paused.jpg"));
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			g.drawImage(image, 0 , 0 , null);
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
		if (keyPressed == KeyEvent.VK_SPACE) {
			if (isPaused)
				isPaused = false;
			else
				isPaused = true;
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
}
