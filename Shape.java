import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;


public abstract class Shape {

	private Color color ;
	protected int x ;
	protected int y ;
	protected String state ;
	
	public void setColor(Color color)
	{
		this.color = color ;
	}
	
	public Color getColor()
	{
		return color ;
	}
	
	public void moveShape(int dx , int dy)
	{
		x = x + dx ;
		y = y + dy ;
		
	}
	
	public void reset ()
	{
		state = "inPool";
	}
	
	public void extractFromPool ()
	{
		state = "outOfPool";
	}
	
	public boolean intersects(Shape shape) {
		Area shapeArea = shape.getArea();
		Area myArea = getArea();
		myArea.intersect(shapeArea);
		return !myArea.isEmpty();
	}
	
	abstract public void draw(Graphics2D g);
	
	abstract protected Area getArea ();
}
