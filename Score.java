
public class Score extends Observer{
	
	private int score = 0;
	
	@Override
	public void update() {
		score += Constants.SCORE_INCREMENT;
	}
	
	public int getScore () {
		return score;
	}
}
