import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Stack;

public class ClownStack extends Observer {

	Stack<Shape> shapesStack;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	int posx, posy;

	public ClownStack(int x, int y) {
		posx = x;
		posy = y;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(posx, posy, Constants.STACK_RECT_WIDTH,
				Constants.STACK_RECT_HEIGHT);
		System.out.println(posx + " " + posy + " " + Constants.STACK_RECT_WIDTH);
	}

	public void addShape(Shape shape) {
		shapesStack.add(shape);
		if (checkWin())
			notifyAllObservers();
	}

	private boolean checkWin() {
		if (shapesStack.size() < 3)
			return false;
		Shape A = shapesStack.pop();
		Shape B = shapesStack.pop();
		Shape C = shapesStack.pop();
		shapesStack.add(C);
		shapesStack.add(B);
		shapesStack.add(A);
		return (A.getColor() == B.getColor() && A.getColor() == C.getColor());
	}
	
	public void attachObserver (Observer observer) {
		observers.add(observer);
	}
	
	private void notifyAllObservers() {
		for (int i=0 ; i<observers.size() ; i++) {
			observers.get(i).update();
		}
	}

	public boolean contains(Shape shape) {
		if (shapesStack.empty()) {
			Area shapeArea = shape.getArea();
			shapeArea.intersect(new Area(new Rectangle2D.Double(posx, posy, Constants.STACK_RECT_WIDTH,
					Constants.STACK_RECT_HEIGHT)));
			return !shapeArea.isEmpty();
		} else {
			return shape.intersects(shapesStack.peek());
		}
	}

	public void move(int dx, int dy) {
		posx += dx;
		posy += dy;
	}

	@Override
	public void update() {
		shapesStack.pop();
		shapesStack.pop();
		shapesStack.pop();
	}

}
