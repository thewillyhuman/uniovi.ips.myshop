package es.uniovi.ips.myshop.igunew.renderers;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CenterRenderer {
	
	public DefaultTableCellRenderer get() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		return rightRenderer;
	}

}
