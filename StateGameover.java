import java.awt.Graphics2D;


public class StateGameover extends State {

	public StateGameover() {
		this.state = Constants.GAMEOVER;
	}

	@Override
	public void draw(Graphics2D g) {
		Game.getCircus1().draw(g);
		Game.getCircus2().draw(g);
	}

}
