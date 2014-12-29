import java.awt.Graphics2D;
import java.util.ArrayList;

public class Circus {

	private int topX, topY, bottomX, bottomY;
	private Clown clown;
	private Score score;
	ArrayList<Shape> shapes = new ArrayList<Shape>();

	public Circus(int topX, int topY, int bottomX, int bottomY) {
		this.topX = topX;
		this.topY = topY;
		this.bottomX = bottomX;
		this.bottomY = bottomY;
		this.score = new Score();
		clown = new Clown(topX + Constants.CLOWN_XPOSITION_DIFF, topY
				+ Constants.CLOWN_YPOSITION_DIFF);
		clown.setLimitLeft(topX);
		clown.setLimitRight(bottomX);
		clown.setScore(score);
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < shapes.size(); i++)
			shapes.get(i).draw(g);
		clown.draw(g);
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void moveShapes() {
		for (int i = 0; i < shapes.size(); i++) {
			if (!shapes.get(i).isInStack()) {
				shapes.get(i).moveShape(Constants.FALLING_SHAPE_DX,
						Constants.FALLING_SHAPE_DY);
				if (clown.getStack1().contains(shapes.get(i))) {
					clown.getStack1().addShape(shapes.get(i));
				}
				if (i >= shapes.size()) continue;
				if (clown.getStack2().contains(shapes.get(i))) {
					clown.getStack2().addShape(shapes.get(i));
				}
			}
		}
	}

	public void moveClown(int dx) {
		clown.move(dx, 0 /* dy */);
	}

	public void checkOutOfCircus() {
		for (int i=0 ; i<shapes.size() ; i++) {
			if (shapes.get(i).isOutOfBounds()) {
				Game.shapesPool.returnObject(shapes.get(i));
				i--;
			}
		}
	}
	
	public boolean checkGameOver() {
		return (clown.getStack1().isOutOfBounds() || clown.getStack2().isOutOfBounds());
	}
	
	public Clown getClown () {
		return clown;
	}
	
	public Score getScore () {
		return score;
	}
}
