import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Rect extends Shape {

	private Rectangle2D rect ;
	
	Rect(int x , int y){
		this.x = x ;
		this.y = y ;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(getColor());
		rect = new Rectangle2D.Double(x, y, Constants.RECTANGLE_WIDTH, Constants.RECTANGLE_HEIGHT) ;
		g.draw(rect);		
		
	}

	protected Area getArea() {
		return new Area(new Rectangle2D.Double(x, y, Constants.RECTANGLE_WIDTH, Constants.RECTANGLE_HEIGHT)) ;
	}

}
