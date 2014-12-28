import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Clown {

	private int posx, posy;
	private int limitLeft, limitRight;
	private ClownStack S1, S2;
	private BufferedImage clownImg = null;
	
	public Clown(int x, int y) {
		posx = x;
		posy = y;
		S1 = new ClownStack(x + Constants.STACK_LEFT_POSITION_DIFF, y
				+ Constants.STACK_TOP_POSITION_DIFF);
		S1.attachObserver(S1);
		// S1.attachObserver(Score);

		S2 = new ClownStack(x + Constants.STACK_RIGHT_POSITION_DIFF, y
				+ Constants.STACK_TOP_POSITION_DIFF);
		S2.attachObserver(S2);
		// S2.attachObserver(Score);
	}

	public void draw(Graphics2D g) {
		g.drawImage(clownImg, posx, posy, null);
		S1.draw(g);
		S2.draw(g);
	}
	
	public void setImage (String img) {
		try {
			clownImg = ImageIO
					.read(new File("img/" + img));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void move(int dx, int dy) {
		if ((S1.getX() - Constants.BORDER_TOLERANCE <= limitLeft && dx < 0)
				|| (S2.getX() + Constants.STACK_RECT_WIDTH
						+ Constants.BORDER_TOLERANCE >= limitRight && dx > 0)) {
			return;
		}
		posx += dx;
		posy += dy;
		S1.move(dx, dy);
		S2.move(dx, dy);
	}

	public void setLimitLeft(int d) {
		limitLeft = d;
	}

	public void setLimitRight(int d) {
		limitRight = d;
	}

	public ClownStack getStack1() {
		return S1;
	}

	public ClownStack getStack2() {
		return S2;
	}
}
