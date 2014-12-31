
public class Originator {
	private Circus c1,c2;
	
	public void set (Circus c1 , Circus c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public Snapshot saveToSnapshot() {
		return new Snapshot(c1,c2);
	}
	
	public void restoreFromSnapshot (Snapshot s) {
		c1 = s.getC1();
		c2 = s.getC2();
	}
}
