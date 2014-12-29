import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.util.Random;


public abstract class Shape {

	private Color color ;
	protected int x ;
	protected int y ;
	protected String state ;
	protected String suffix;
	
	public void setSuffix(String suffix)
	{
		this.suffix = suffix ;
	}
	
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
		y = 0;
		x = new Random().nextInt(Constants.BORDER_MAX_WIDTH-50);
	}
	
	public void extractFromPool ()
	{
		state = "outOfPool";
	}
	
	public void addToStack () {
		state = "insideStack";
	}
	
	public boolean isInStack () {
		return (state.equals("insideStack"));
	}
	
	public void setPosition (int x , int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean intersects(Shape shape) {
		Area shapeArea = shape.getArea();
		Area myArea = getArea();
		myArea.intersect(shapeArea);
		return !myArea.isEmpty();
	}
	
	public boolean isOutOfBounds () {
		return (y > Constants.BORDER_MAX_HEIGHT || y < 0);
	}
	
	abstract public void draw(Graphics2D g);
	
	abstract protected Area getArea ();
}
