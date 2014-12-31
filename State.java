import java.awt.Graphics2D;


public abstract class State {
	
	String state;
	
	public String getState () {
		return state;
	}
	
	public abstract void draw (Graphics2D g); 
}
