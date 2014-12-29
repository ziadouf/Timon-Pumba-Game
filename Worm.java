import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class Worm extends Shape {

	Worm(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics2D g) {
		Image img = null;
		img = Toolkit.getDefaultToolkit().getImage(
				new File("img/worm_" + suffix + ".png").getAbsolutePath());
		g.drawImage(img, x, y,80,80, null);

	}

	protected Area getArea() {
		return new Area(new Rectangle2D.Double(x, y, Constants.RECTANGLE_WIDTH,
				Constants.RECTANGLE_HEIGHT));
	}

}
