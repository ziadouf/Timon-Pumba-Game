import java.awt.Graphics2D;


public class StatePlaying extends State {
	public StatePlaying() {
		this.state = Constants.PLAYING;
	}

	@Override
	public void draw(Graphics2D g) {
		Game.getCircus1().moveShapes();
		Game.getCircus2().moveShapes();
		Game.getCircus1().draw(g);
		Game.getCircus2().draw(g);
	}
}
