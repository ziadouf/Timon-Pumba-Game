import java.util.Random;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class ShapeFactory extends BasePooledObjectFactory<Shape> {

	String shapeType;

	@Override
	public Shape create() throws Exception {
		Shape myshape = null ;
		
		char[] shapeTypes = {'R','C'};
		char shapeType = shapeTypes[new Random().nextInt(shapeTypes.length)];
		int randomPosition = new Random().nextInt(Constants.BORDER_MAX_WIDTH);
		if(shapeType == 'C')
		{
			myshape = new Circle(randomPosition,0) ;
		}
		
		else if(shapeType == 'R')
		{
			myshape = new Rect(randomPosition,0) ;
		}
		
		return myshape ;
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
