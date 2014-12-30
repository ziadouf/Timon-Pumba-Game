import java.awt.Color;

public class Constants {
	// MENU LABELS
	public static final String MENU_RESUME = "RESUME";
	public static final String MENU_SAVE = "SAVE";
	public static final String MENU_BACK = "BACK TO MAIN MENU";
	public static final String MENU_PLAYAGAIN = "PlAY AGAIN";
	public static final String MENU_NEWGAME = "NEW GAME";
	public static final String MENU_CONTINUE = "CONTINUE";
	public static final String MENU_EXIT = "EXIT";
	
	// MENU COLORS
	public static final Color COL_UNSELECTED = new Color(255,173,101);
	public static final Color COL_SELECTED = Color.WHITE;
	
	// GAME DIMENSIOMS (Initialized in JFrame.update())
	public static int BORDER_MAX_WIDTH = 1000;
	public static int BORDER_MAX_HEIGHT = 700;
	public static int P2_CIRCUS1_TOPX;
	public static int P2_CIRCUS1_TOPY;
	public static int P2_CIRCUS1_BOTTOMX;
	public static int P2_CIRCUS1_BOTTOMY;
	public static int P2_CIRCUS2_TOPX;
	public static int P2_CIRCUS2_TOPY;
	public static int P2_CIRCUS2_BOTTOMX;
	public static int P2_CIRCUS2_BOTTOMY;

	// CLOWN POSITION INSIDE CIRCUS
	public static final int CLOWN_XPOSITION_DIFF = 100;
	public static final int CLOWN_YPOSITION_DIFF = 480;

	// CLOWN STACK DIMENSIONS
	public static final int STACK_RECT_WIDTH = 80;
	public static final int STACK_RECT_HEIGHT = 5;
	public static final int BORDER_TOLERANCE = 20;	// To keep space between stack and game border.
	// CLOWN STACK POSITION
	public static final int STACK_LEFT_POSITION_DIFF = 0;
	public static final int STACK_RIGHT_POSITION_DIFF = 160;
	public static final int STACK_TOP_POSITION_DIFF = -20;
	
	// SHAPES DIMENSIONS
	public static final int CIRCLE_RADIUS = 20;
	public static final int RECTANGLE_WIDTH = 60;  //80
	public static final int RECTANGLE_HEIGHT = 40; //50
	
	// FALLING SHAPES SPEED
	public static final int FALLING_SHAPE_DX = 0;
	public static final int FALLING_SHAPE_DY = 3;

	// CLOWN MOVE SPEED
	public static final int CLOWN_SPEED = 5;

	public static final int POOL_SIZE = 100;
	public static final double NUM_SECS_BETWEEN_FALLING = 1;
	public static final int SCORE_INCREMENT = 5;
}
