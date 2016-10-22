package es.uniovi.ips.myshop.igunew;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;

public class ModeloTablaNoEditableNoProcesado  extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<Order> li = new ArrayList<Order>();
	private List<Order> orders;

	/**
	 * Constructor intended to create a DefaultTableModel from its column names,
	 * its rowCount and a reserva object type.
	 * 
	 * @param columnNames are the names of each column.
	 * @param rowCount the number of rows the table holds.
	 * @param reserva the current reserva where the model is going to work.
	 */
	public ModeloTablaNoEditableNoProcesado(Object[] columnNames, int rowCount, List<Order> orders) {
		super(columnNames, rowCount);
		this.orders=orders;
	}

	public List<Order> getOrders() {
		return this.orders;
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
	public void addRow(Order c) {
		Object[] newRow = new Object[getColumnCount()];
		newRow[0] = c.getIdPedido();
		newRow[1] = c.size();
		newRow[2] = c.getDate();
		newRow[3] = c.getEstado();
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
	public void removeRow(Order c) {
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
	public List<Order> getAllRows() {
		List<Order> aux = new ArrayList<Order>();
		for (Order c : li)
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
	public Order getOrderAtRow(int row) {
		return li.get(row);
	}

}
