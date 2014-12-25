import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;


public class ShapePool extends GenericObjectPool<Shape>{

	public ShapePool(PooledObjectFactory<Shape> factory) {
		super(factory);
	}
	

}
