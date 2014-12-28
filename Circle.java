import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Circle extends Shape {

	private Ellipse2D circle ;
	
	Circle(int x , int y){
		this.x = x ;
		this.y = y ;
	}

	@Override
	public void draw(Graphics2D g) {
		if (state.equals("inPool")) return;
		g.setColor(getColor());
		circle = new Ellipse2D.Double(x, y, 2*Constants.CIRCLE_RADIUS, 2*Constants.CIRCLE_RADIUS) ;
//		g.fill(circle);
		Image img = null;
		img = Toolkit.getDefaultToolkit().getImage(
				new File("img/bug_red.png").getAbsolutePath());
		g.drawImage(img, x, y, 80, 80, null);		
	}

	@Override
	protected Area getArea() {
		return new Area(new Ellipse2D.Double(x, y, 2*Constants.CIRCLE_RADIUS, 2*Constants.CIRCLE_RADIUS));
	}

	
}
