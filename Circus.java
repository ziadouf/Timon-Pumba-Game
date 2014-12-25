import java.awt.Graphics2D;
import java.util.ArrayList;

public class Circus {

	private int topX, topY, bottomX, bottomY;
	private Clown clown;
	// Background
	ArrayList<Shape> shapes = new ArrayList<Shape>();

	public Circus(int topX, int topY, int bottomX, int bottomY) {
		this.topX = topX;
		this.topY = topY;
		this.bottomX = bottomX;
		this.bottomY = bottomY;
		clown = new Clown(topX + Constants.CLOWN_XPOSITION_DIFF, topY
				+ Constants.CLOWN_YPOSITION_DIFF);
	}

	public void draw(Graphics2D g) {
		// TODO(ziadouf): draw background
		clown.draw(g);
		for (int i = 0; i < shapes.size(); i++)
			shapes.get(i).draw(g);
	}

	public void moveShapes() {
		for (int i = 0; i < shapes.size(); i++)
			shapes.get(i).move(Constants.FALLING_SHAPE_DX,
					Constants.FALLING_SHAPE_DY);
	}
	
	public void moveClown (int dx) {
		clown.move(dx, 0 /* dy */);
	}
	
	public void checkOutOfCircus() {
		// TODO(ziadouf): check shapes when falling to the ground and return
		// them to the pool
	}
}
