import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Rect extends Shape {

	private Rectangle2D rect ;
	
	Rect(int x , int y){
		this.x = x ;
		this.y = y ;
	}
	
	@Override
	public boolean intersects(Shape shape) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		g.setColor(getColor());
		rect = new Rectangle2D.Double(x, y, 10, 10) ;
		g.draw(rect);		
		
	}

}
