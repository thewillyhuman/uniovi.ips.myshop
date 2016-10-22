package es.uniovi.ips.myshop.igunew;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.product.Product;

public class ModeloTablaNoEditableProductos extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<Product> li = new ArrayList<Product>();
	private List<Product> p;

	/**
	 * Constructor intended to create a DefaultTableModel from its column names,
	 * its rowCount and a reserva object type.
	 * 
	 * @param columnNames are the names of each column.
	 * @param rowCount the number of rows the table holds.
	 * @param reserva the current reserva where the model is going to work.
	 */
	public ModeloTablaNoEditableProductos(Object[] columnNames, int rowCount, List<Product> p) {
		super(columnNames, rowCount);
		this.p=p;
	}

	public List<Product> getOrder() {
		return this.p;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * Given a camarote as parameter this method will added to the model by its
	 * attributes while at the same time will add it to a list where it will be
	 * stored.
	 * 
	 * @param c is the camarote that will be added to the model.
	 */
	public void addRow(Product c) {
		Object[] newRow = new Object[getColumnCount()];

		newRow[0] = c.getIDProducto();
		newRow[1] = c.getDescripcion();
		newRow[2] = c.getPrecio();
		super.addRow(newRow);
		li.add(c);
	}

	/**
	 * Given a camarote will remove it from the model and from the auxiliary
	 * list.
	 * 
	 * @param c is the camarote to remove.
	 * @throws IllegalStateException if the camarote given as a parameter is not
	 *             in the model.
	 */
	public void removeRow(Product c) {
		if (!li.contains(c))
			throw new IllegalStateException("This room is not in the model");
		super.removeRow(li.indexOf(c));
		li.remove(c);
	}

	/**
	 * Gets a completely different list with all the camarotes.
	 * 
	 * @return a different list with all the camarotes.
	 */
	public List<Product> getAllRows() {
		List<Product> aux = new ArrayList<Product>();
		for (Product c : li)
			aux.add(c);
		return aux;
	}

	/**
	 * Removes all the elements in the model and in the auxiliary list.
	 */
	public void removeAll() {
		dataVector.removeAllElements();
		li.clear();
	}

	/**
	 * Given a row index will return the camarote that is being displayed at.
	 * 
	 * @param row is the index in the table.
	 * @return the camarote associated with that table row.
	 */
	public Product getProductAtRow(int row) {
		return li.get(row);
	}
}
