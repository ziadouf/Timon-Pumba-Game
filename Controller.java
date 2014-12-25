
public class Controller {
	
	public static final int LEFT_ARROW = 0;
	public static final int RIGHT_ARROW = 0;
	public static final int KEY_A = 0;
	public static final int KEY_D = 0;
	public static final int SPACE = 0;
	
	private Game game;
	private boolean isPaused = false;
	
	public void createGame () {
		game = Game.getInstance();
	}
	
	public void render () {
		game.getCircus1().moveShapes();
		game.getCircus2().moveShapes();
	}
	
	public void handleKeyPress (int keyPressed) {
		if (keyPressed == SPACE) {
			if (isPaused) isPaused = false;
			else isPaused = true;
		}
		if (!isPaused) moveClown(keyPressed);
	}
	
	public void moveClown (int keyPressed) {
		if (keyPressed == LEFT_ARROW) {
			game.getCircus2().moveClown(Constants.CLOWN_SPEED * -1);
		}
		else if (keyPressed == RIGHT_ARROW) {
			game.getCircus2().moveClown(Constants.CLOWN_SPEED);
		}
		else if (keyPressed == KEY_A) {
			game.getCircus1().moveClown(Constants.CLOWN_SPEED * -1);
		}
		else if (keyPressed == KEY_D) {
			game.getCircus1().moveClown(Constants.CLOWN_SPEED);
		}
	}
}
