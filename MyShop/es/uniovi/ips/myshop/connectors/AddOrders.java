package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.properties.Properties;

/**
 * 
 * AddOrders.java Adds an order to the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161300
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class AddOrders extends Connector {
	
	private Order order;
	
	/**
	 * Will add the given order on to the database.
	 * 
	 * @param order to be added to the database.
	 * @throws SQLException if there is any error while connecting to the
	 *             database or executing the query.
	 */
	public AddOrders(Order order) throws SQLException {
		this.order = order;
		super.run();
	}

	@Override
	protected void query() throws SQLException {
		String status;
		if(order.getEstado() == Status.PENDIENTE)
			status = "PENDIENTE";
		else if(order.getEstado() == Status.SOLICITADO)
			status = "SOLICITADO";
		else
			status = "LISTO";
			
		String date = new SimpleDateFormat("yyyy-MM-dd").format(order.getDate());
		super.db.executeUpdate(Properties.getString("sql.addOrder"), date, status, order.getCliente().getId());
		for(OrderDetail od : order.getProductos()) {
			// Add the relation to RPP but waiting to fix RPP;
			od.toString();
		}
	}
}
