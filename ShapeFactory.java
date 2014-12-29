import java.awt.Color;
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
		Color [] shapeColors = {Color.blue,Color.red,Color.green,Color.yellow};
		String [] shapeSuffix = {"blue","red","green","yellow"};
		char shapeType = shapeTypes[new Random().nextInt(shapeTypes.length)];
		int shapeColor = new Random().nextInt(shapeColors.length);
		int randomPosition = new Random().nextInt(Constants.BORDER_MAX_WIDTH-50);
		
		if(shapeType == 'C')
		{
			myshape = new Bug(randomPosition,0) ;
		}
		
		else if(shapeType == 'R')
		{
			myshape = new Bug(randomPosition,0) ;
		}
		
		myshape.setColor(shapeColors[shapeColor]);
		myshape.setSuffix(shapeSuffix[shapeColor]);
		return myshape ;
	}

	@Override
	public PooledObject<Shape> wrap(Shape s) {
		return new DefaultPooledObject<Shape>(s);
	}
	
	@Override
	public void passivateObject(PooledObject<Shape> s) throws Exception {
		s.getObject().reset();
		Game.getCircus1().shapes.remove(s.getObject());
		Game.getCircus2().shapes.remove(s.getObject());
	}
	
	@Override
	public void activateObject(PooledObject<Shape> s) throws Exception {
		s.getObject().extractFromPool();
	}
	
}
