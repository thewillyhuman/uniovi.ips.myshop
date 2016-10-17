package es.uniovi.ips.myshop.model.order;

import java.sql.SQLException;
import java.util.List;

import es.uniovi.ips.myshop.connectors.AddOrder;
import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.model.order.Order.Status;

/**
 * 
 * Orders.java
 *
 * @author admin
 * @version 1010161125
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Orders {
	
	/**
	 * Adds an order to the database.
	 * 
	 * @param order to be added to the database.
	 * @throws SQLException if there's any error while writing in the database.
	 */
	public static void addOrder(Order order) throws SQLException {
		new AddOrder(order);
	}
	
	/**
	 * Gets a list of the orders that match with the given status.
	 * 
	 * @param status that orders must match.
	 * @return a list of orders that matches with the status
	 * @throws SQLException 
	 */
	public static List<Order> getOrdersByStatus(Status status) throws SQLException {
		return new GetOrders().getOrdersByStatus(status);
	}

}
