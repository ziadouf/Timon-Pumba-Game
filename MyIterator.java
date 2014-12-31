import java.util.ArrayList;


public class MyIterator {
	ArrayList<?> list;
	int index = -1;
	
	public MyIterator (ArrayList<?> arr) {
		this.list = arr;
	}
	
	public boolean hasNext() {
		if (index == list.size()-1) return false;
		index++;
		return true;
	}
	
	public Object getItem() {
		return list.get(index);
	}
	
	public Object getFirst() {
		return list.get(0);
	}
}
