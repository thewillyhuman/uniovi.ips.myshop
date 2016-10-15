package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.properties.Properties;

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
	 * @param orderID
	 *            is the reference of the order to search in the database.
	 * @return the order if it is in the database. null otherwise.
	 * @throws SQLException
	 */
	public Order getOrder(String orderID) throws SQLException {
		ResultSet rs = super.db.executeSQL(Properties.getString("sql.getOrderByID"), orderID);
		return new Order(rs.getString(1), new GetCustomers(db).getCustomer(rs.getString(4)), rs.getDate(2));
	}

	/**
	 * Gets a list of orders that are tagged with the state provided as a
	 * parameter.
	 * 
	 * @param status
	 *            is the current status of the order itself.
	 * @return a list of orders empty if none matches the status, with the
	 *         matching elements otherwise.
	 * @throws SQLException 
	 */
	public List<Order> getOrdersByStatus(Status status) throws SQLException {
		return getAllOrders().stream().filter(o -> o.getEstado() == status).collect(Collectors.toList());
	}

	/**
	 * Gets a list containing all the orders in the database.
	 * 
	 * @return a list with all the orders in the database.
	 * @throws SQLException 
	 */
	protected List<Order> getAllOrders() throws SQLException {
		List<Order> aux = new ArrayList<Order>();
		ResultSet rs = super.db.executeSQL("sql.getAllOrders");
		while(rs.next()) {
			Order order = new Order(rs.getString(1), new GetCustomers(db).getCustomer(rs.getString(4)), rs.getDate(2));
			//order.addProduct(new , quantity);
			aux.add(order);
		}
		return aux;
	}

	@Override
	protected void query() {
		// TODO Auto-generated method stub

	}
}
