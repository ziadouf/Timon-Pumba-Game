public class Game {
	private static Game instance = null;
	private static Circus Circus1, Circus2;
	public static ShapePool shapesPool = new ShapePool(new ShapeFactory());
	
	private Game() throws Exception {
		Circus1 = new Circus(Constants.P2_CIRCUS1_TOPX,
				Constants.P2_CIRCUS1_TOPY, Constants.P2_CIRCUS1_BOTTOMX,
				Constants.P2_CIRCUS1_BOTTOMY);
		Circus1.getClown().setImage("timon_clown3.png");
		Circus2 = new Circus(Constants.P2_CIRCUS2_TOPX,
				Constants.P2_CIRCUS2_TOPY, Constants.P2_CIRCUS2_BOTTOMX,
				Constants.P2_CIRCUS2_BOTTOMY);
		Circus2.getClown().setImage("pumbaa_clown4.png");
		shapesPool.setMaxTotal(Constants.POOL_SIZE);
		for (int i = 0; i < Constants.POOL_SIZE ; i++) {
			shapesPool.addObject();
		}
	}

	public static Game getInstance() throws Exception {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	public void newGame () {
		Circus1 = new Circus(Constants.P2_CIRCUS1_TOPX,
				Constants.P2_CIRCUS1_TOPY, Constants.P2_CIRCUS1_BOTTOMX,
				Constants.P2_CIRCUS1_BOTTOMY);
		Circus1.getClown().setImage("timon_clown3.png");
		Circus2 = new Circus(Constants.P2_CIRCUS2_TOPX,
				Constants.P2_CIRCUS2_TOPY, Constants.P2_CIRCUS2_BOTTOMX,
				Constants.P2_CIRCUS2_BOTTOMY);
		Circus2.getClown().setImage("pumbaa_clown4.png");
	}
	
	public static Circus getCircus1() {
		return Circus1;
	}

	public static Circus getCircus2() {
		return Circus2;
	}

}
