package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
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
public class AddOrder extends InBoardConnector {
	
	private Order order;
	
	/**
	 * Will add the given order on to the database.
	 * 
	 * @param order to be added to the database.
	 * @throws SQLException if there is any error while connecting to the
	 *             database or executing the query.
	 */
	public AddOrder(Order order) {
		this.order = order;
		query();
	}

	@Override
	protected void query() {
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		String status = String.valueOf(order.getEstado());			
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getDate());
		try {
			new AddCustomer(order.getCliente());
			ResultSet rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.lastInsertedID"));
			String id = "";
			if(rs.next()){
				id = rs.getString(1);
			}
			else{
				System.out.println("No tengo nada :D");
			}
			super.getDatabase().executeUpdate(Properties.getString("myshop.sql.addOrder"), date, status, id);
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = super.getDatabase().executeSQL(Properties.getString("myshop.sql.lastInsertedID"));
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			rs2.next();
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		String orderID = "";
		try {
			orderID = rs2.getString(1);
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		for(OrderDetail od : order.getProductos()) {
			try {
				super.getDatabase().executeUpdate(Properties.getString("myshop.sql.addRPPRelation"), orderID, od.getProducto().getIDProducto(), od.getCantidad());
			} catch (SQLException e) {
				System.out.println(e.getSQLState());
				e.printStackTrace();
			}
		}
		
		new DisconnectDatabase(super.getDatabase());
	}
}
