import java.awt.Graphics2D;

public class Clown {

	private int posx, posy;
	private ClownStack S1, S2;

	public Clown(int x, int y) {
		posx = x;
		posy = y;
		S1 = new ClownStack(x + Constants.STACK_LEFT_POSITION_DIFF, y
				+ Constants.STACK_TOP_POSITION_DIFF);
		S2 = new ClownStack(x + Constants.STACK_RIGHT_POSITION_DIFF, y
				+ Constants.STACK_TOP_POSITION_DIFF);
	}

	public void draw(Graphics2D g) {
		// TODO(ziadouf): draw clown body
		S1.draw(g);
		S2.draw(g);
	}

	public void move(int dx, int dy) {
		posx += dx;
		posy += dy;
		S1.move(dx, dy);
		S2.move(dx, dy);
	}
}
