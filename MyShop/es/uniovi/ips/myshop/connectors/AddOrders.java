package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.order.Order;

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
	
	/**
	 * Will add the given order on to the database.
	 * 
	 * @param order to be added to the database.
	 * @throws SQLException if there is any error while connecting to the
	 *             database or executing the query.
	 */
	public AddOrders(Order order) throws SQLException {
		super.run();
	}

	@Override
	protected void query() {
		// TODO Auto-generated method stub
		
	}
}
