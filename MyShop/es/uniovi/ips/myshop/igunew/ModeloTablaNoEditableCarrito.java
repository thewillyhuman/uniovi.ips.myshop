package es.uniovi.ips.myshop.igunew;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.product.Product;

public class ModeloTablaNoEditableCarrito extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<OrderDetail> li = new ArrayList<OrderDetail>();
	private List<OrderDetail> p;

	/**
	 * Constructor intended to create a DefaultTableModel from its column names,
	 * its rowCount and a reserva object type.
	 * 
	 * @param columnNames are the names of each column.
	 * @param rowCount the number of rows the table holds.
	 * @param reserva the current reserva where the model is going to work.
	 */
	public ModeloTablaNoEditableCarrito(Object[] columnNames, int rowCount, List<OrderDetail> p) {
		super(columnNames, rowCount);
		this.p=p;
	}

	public List<OrderDetail> getOrder() {
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
	 * @throws Exception 
	 */
	public void addRow(OrderDetail c) throws Exception {
		if(contains(c))
			throw new Exception();
		Object[] newRow = new Object[getColumnCount()];

		newRow[0] = c.getProducto().getIDProducto();
		newRow[1] = c.getCantidad();
		newRow[2] = c.getProducto().getPrecio();
		newRow[3] = c.getCantidad()*c.getProducto().getPrecio();
		super.addRow(newRow);
		li.add(c);
	}

	private boolean contains(OrderDetail c) {
		for(OrderDetail od : li) {
			if (c.getProducto().getIDProducto().equals(od.getProducto().getIDProducto()))
				return true;
		} return false;
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
	public OrderDetail getProductAtRow(int row) {
		return li.get(row);

	}
	
	public void refresh() {
		List<OrderDetail> aux = getAllRows();
		removeAll();
		for(OrderDetail od : aux) {
			try {
				addRow(od);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fireTableDataChanged();
	}
	
	public double getTotal() {
		double aux = 0.0;
		for(OrderDetail od: li) {
			aux+=od.getCantidad()*od.getProducto().getPrecio();
		} return aux;
	}
}
