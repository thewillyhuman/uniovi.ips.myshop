package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.OutBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
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
public class GetOrders extends Connector implements OutBoardConnector{
	
	/**
	 * Gets an order by its ID.
	 * 
	 * @param orderID
	 *            is the reference of the order to search in the database.
	 * @return the order if it is in the database. null otherwise.
	 * @throws SQLException
	 */
	public Order getOrder(String orderID) throws SQLException {
		new ConnectDatabase(super.getDatabase());
		ResultSet rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getOrderByID"), orderID);
		rs.next();
		Order aux = new Order(rs.getString(1), new GetCustomers().getCustomer(rs.getString(4)), rs.getDate(2));
		ResultSet rs2 = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getRPPRelation"), aux.getIdPedido());
		while(rs2.next()) {
			aux.addProduct(new GetProducts().getProduct(rs2.getString(2)), rs2.getInt(3));
		}
		//db.closeConnection();
		return aux;
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
	public List<Order> getAllOrders() throws SQLException {
		new ConnectDatabase(super.getDatabase());
		List<Order> aux = new ArrayList<Order>();
		ResultSet rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.allOrders"));
		while(rs.next()) {
			Order order = new Order(rs.getString(1), new GetCustomers().getCustomer(rs.getString(4)), rs.getDate(2));
			order.setStatus(Status.getValueOf(rs.getString(3)));
			ResultSet rs2 = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getRPPRelation"), order.getIdPedido());
			while(rs2.next()) {
				order.addProduct(new GetProducts().getProduct(rs2.getString(2)), rs2.getInt(3));
			}
			//order.addProduct(new , quantity);
			aux.add(order);
		}
		new DisconnectDatabase(super.getDatabase());
		return aux;
	}
}
