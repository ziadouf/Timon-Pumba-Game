import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


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
		circle = new Ellipse2D.Double(x, y, 10, 10) ;
		g.draw(circle);		
		
	}

	@Override
	protected Area getArea() {
		return new Area(new Ellipse2D.Double(x, y, 10, 10));
	}

	
}
