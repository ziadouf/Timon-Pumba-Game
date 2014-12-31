
public class Caretaker {
	private Snapshot lastSnapshot;
	
	public void setSnapshot (Snapshot s) {
		lastSnapshot = s;
	}
	
	public Snapshot getSnapshot () {
		return lastSnapshot;
	}
}
