
public class Snapshot {
	private Circus c1,c2;
	private ShapePool pool;
	
	Snapshot () {
		c1 = Game.getCircus1();
		c2 = Game.getCircus2();
		//pool = Game.shapesPool;
	}

	public Circus getC1() {
		return c1;
	}

	public Circus getC2() {
		return c2;
	}

	public ShapePool getPool() {
		return pool;
	}
}
