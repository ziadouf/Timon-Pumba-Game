import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Controller {
	private static Controller instance = null;
	private Set<Integer> pressedKeys = new HashSet<Integer>();
	
	private Game game;
	private boolean isPaused = false;

	private Controller () {
	}
	
	public static Controller getInstance () {
		if (instance == null) instance = new Controller();
		return instance;
	}
	
	public void createGame () {
		try {
			game = Game.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render (Graphics2D g) {
		try {
			if (Game.shapesPool.getNumIdle() > 1) {
				Game.getCircus1().addShape(Game.shapesPool.borrowObject());
			}
			if (Game.shapesPool.getNumIdle() > 1) {
				Game.getCircus2().addShape(Game.shapesPool.borrowObject());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(Game.shapesPool.getNumActive() + " - " + Game.shapesPool.getNumIdle());
		Game.getCircus1().moveShapes();
		Game.getCircus2().moveShapes();
		Game.getCircus1().draw(g);
		Game.getCircus2().draw(g);
		moveClown();
	}
	
	public void handleKeyPress (int keyPressed) {
		if (keyPressed == KeyEvent.VK_SPACE) {
			if (isPaused) isPaused = false;
			else isPaused = true;
		}
		pressedKeys.add(keyPressed);
	}
	
	public void handleKeyRelease (int keyPressed) {
		pressedKeys.remove(keyPressed);
	}
	
	public void moveClown () {
		if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
			Game.getCircus2().moveClown(Constants.CLOWN_SPEED * -1);
		}
		else if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
			Game.getCircus2().moveClown(Constants.CLOWN_SPEED);
		}
		if (pressedKeys.contains(KeyEvent.VK_A)) {
			Game.getCircus1().moveClown(Constants.CLOWN_SPEED * -1);
		}
		else if (pressedKeys.contains(KeyEvent.VK_D)) {
			Game.getCircus1().moveClown(Constants.CLOWN_SPEED);
		}
	}
}
