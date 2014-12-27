import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Stack;

public class ClownStack extends Observer {

	Stack<Shape> shapesStack = new Stack<Shape>();
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
	}

	public void addShape(Shape shape) {
		shapesStack.add(shape);
		shape.addToStack();
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
			Area stackArea = new Area(new Rectangle2D.Double(posx, posy, Constants.STACK_RECT_WIDTH, Constants.STACK_RECT_HEIGHT));
			shapeArea.intersect(stackArea);
			return !shapeArea.isEmpty();
		} else {
			return shape.intersects(shapesStack.peek());
		}
	}

	public void move(int dx, int dy) {
		posx += dx;
		posy += dy;
		for (Shape shape: shapesStack) {
			shape.moveShape(dx, dy);
		}
	}

	@Override
	public void update() {
		Game.shapesPool.returnObject(shapesStack.pop());
		Game.shapesPool.returnObject(shapesStack.pop());
		Game.shapesPool.returnObject(shapesStack.pop());
		
		// TODO(ziadouf): make a blink when removed.
	}
	
	public int getX () {
		return posx;
	}

}
