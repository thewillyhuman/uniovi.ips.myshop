package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.client.Database;
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
public class AddOrder {
	
	private Order order;
	private Database bd;
	
	/**
	 * Will add the given order on to the database.
	 * 
	 * @param order to be added to the database.
	 * @throws SQLException if there is any error while connecting to the
	 *             database or executing the query.
	 */
	public AddOrder(Database db, Order order) {
		this.order = order;
		this.bd = db;
		query();
	}

	protected void query() {
		try {
			bd.connect();
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		String status;
		if(order.getEstado() == Status.PENDIENTE)
			status = "PENDIENTE";
		else if(order.getEstado() == Status.SOLICITADO)
			status = "SOLICITADO";
		else
			status = "LISTO";
			
		String date = new SimpleDateFormat("yyyy-MM-dd").format(order.getDate());
		try {
			bd.executeUpdate(Properties.getString("sql.addOrder"), date, status, order.getCliente().getId());
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = bd.executeSQL(Properties.getString("myshop.sql.lastInsertedID"));
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
			orderID = rs2.getString(2);
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		for(OrderDetail od : order.getProductos()) {
			// myshop.sql.addRPPRelation
			try {
				bd.executeUpdate(Properties.getString("myshop.sql.addRPPRelation"), orderID, od.getProducto().getIDProducto(), od.getCantidad());
				System.out.println("New P added");
			} catch (SQLException e) {
				System.out.println(e.getSQLState());
				e.printStackTrace();
			}
		}
		
		bd.closeConnection();
	}
}
