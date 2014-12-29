import java.awt.Color;

import javax.swing.JLabel;


public class MenuItem {
	private JLabel lbl;
	private MenuItem nextItem,prevItem;
	private boolean isSelected;
	
	public MenuItem (JLabel lbl) {
		this.lbl = lbl;
	}
	
	public void setNextItem(MenuItem nextItem) {
		this.nextItem = nextItem;
	}

	public void setPrevItem(MenuItem prevItem) {
		this.prevItem = prevItem;
	}

	public JLabel getLbl() {
		return lbl;
	}
	public MenuItem getNextItem() {
		return nextItem;
	}
	public MenuItem getPrevItem() {
		return prevItem;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		if (isSelected) lbl.setForeground(Constants.COL_SELECTED);
		else lbl.setForeground(Constants.COL_UNSELECTED);
	}
}
