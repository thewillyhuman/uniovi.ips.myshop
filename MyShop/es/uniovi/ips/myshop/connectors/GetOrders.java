package es.uniovi.ips.myshop.connectors;

import java.util.List;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.Order.Status;

/**
 * 
 * GetOrders.java Helps to retrieve orders from the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161300
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class GetOrders extends Connector {

	/**
	 * Gets an order by its ID.
	 * 
	 * @param OrderID is the reference of the order to search in the database.
	 * @return the order if it is in the database. null otherwise.
	 */
	public Order getOrder(String OrderID) {
		return null;
	}

	/**
	 * Gets a list of orders that are tagged with the state provided as a
	 * parameter.
	 * 
	 * @param status is the current status of the order itself.
	 * @return a list of orders empty if none matches the status, with the
	 *         matching elements otherwise.
	 */
	public List<Order> getOrdersByStatus(Status status) {
		return null;
	}

	/**
	 * Gets a list containing all the orders in the database.
	 * 
	 * @return a list with all the orders in the database.
	 */
	protected List<Order> getAllOrders() {
		return null;
	}

	@Override
	protected void query() {
		// TODO Auto-generated method stub

	}
}
