import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class ShapeFactory extends BasePooledObjectFactory<Shape> {

	String shapeType;
	int x , y;
	
	public Shape makeShape(String newShape , int x , int y) throws Exception{
		shapeType = newShape;
		this.x = x;
		this.y = y;
		return create();
	}

	@Override
	public Shape create() throws Exception {
		Shape myshape = null ;
		return new Circle(400,0);
//		if(shapeType.equals("C"))
//		{
//			myshape = new Circle(x,y) ;
//		}
//		
//		else if(shapeType.equals("R"))
//		{
//			myshape = new Rect(x,y) ;
//		}
//		
//		return myshape ;
	}

	@Override
	public PooledObject<Shape> wrap(Shape s) {
		return new DefaultPooledObject<Shape>(s);
	}
	
	@Override
	public void passivateObject(PooledObject<Shape> s) throws Exception {
		s.getObject().reset();
		
	}
	
	@Override
	public void activateObject(PooledObject<Shape> s) throws Exception {
		s.getObject().extractFromPool();
		
	}
	
}
