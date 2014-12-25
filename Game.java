public class Game {
	private static Game instance = null;
	private Circus Circus1, Circus2;

	private Game() {
		Circus1 = new Circus(Constants.P2_CIRCUS1_TOPX,
				Constants.P2_CIRCUS1_TOPY, Constants.P2_CIRCUS1_BOTTOMX,
				Constants.P2_CIRCUS1_BOTTOMY);
		Circus2 = new Circus(Constants.P2_CIRCUS2_TOPX,
				Constants.P2_CIRCUS2_TOPY, Constants.P2_CIRCUS2_BOTTOMX,
				Constants.P2_CIRCUS2_BOTTOMY);
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public Circus getCircus1() {
		return Circus1;
	}

	public Circus getCircus2() {
		return Circus2;
	}

}
