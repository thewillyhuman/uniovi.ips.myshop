package es.uniovi.ips.myshop.igunew;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.*;

import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;

/**
 * This class extends the default model of the tables to create a totally
 * personalized which has an auxiliary list of cabins where, apart from in the
 * model, will save camarotes of which are displaying the data in the table .
 * 
 * @author Guillermo Facundo Colunga
 */
public class ModeloTablaNoEditable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<OrderDetail> li = new ArrayList<OrderDetail>();
	private Order order;

	/**
	 * Constructor intended to create a DefaultTableModel from its column names,
	 * its rowCount and a reserva object type.
	 * 
	 * @param columnNames are the names of each column.
	 * @param rowCount the number of rows the table holds.
	 * @param reserva the current reserva where the model is going to work.
	 */
	public ModeloTablaNoEditable(Object[] columnNames, int rowCount, Order order) {
		super(columnNames, rowCount);
		this.order=order;
	}

	public Order getOrder() {
		return this.order;
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
	public void addRow(OrderDetail c) {
		Object[] newRow = new Object[getColumnCount()];

		newRow[0] = c.getProducto().getIDProducto();
		newRow[1] = c.getProducto().getLocalizacion().getPasillo();
		newRow[2] = c.getProducto().getLocalizacion().getLado();
		newRow[3] = c.getProducto().getLocalizacion().getPosicion();
		newRow[4] = c.getProducto().getLocalizacion().getAltura();
		newRow[5] = c.getCantidad();
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
	public void removeRow(OrderDetail c) {
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
	public List<OrderDetail> getAllRows() {
		List<OrderDetail> aux = new ArrayList<OrderDetail>();
		for (OrderDetail c : li)
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
	public OrderDetail getOrderAtRow(int row) {
		return li.get(row);
	}

}
