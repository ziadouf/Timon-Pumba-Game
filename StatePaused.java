import java.awt.Graphics2D;


public class StatePaused extends State {

	public StatePaused() {
		this.state = Constants.PAUSED;
	}

	@Override
	public void draw(Graphics2D g) {
		Game.getCircus1().draw(g);
		Game.getCircus2().draw(g);
	}

}
