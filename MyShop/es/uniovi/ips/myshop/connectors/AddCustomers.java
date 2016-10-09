package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.people.Customer;

/**
 * 
 * AddCustomers.java Adds a customer to the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161300
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class AddCustomers extends Connector {
	
	private Customer customer;

	/**
	 * Adds a single customer constructor. For a given customer object will add
	 * a record to the database.
	 * 
	 * @param customer to be added to the database.
	 * @throws SQLException if there is any error while connecting to the
	 *             database or executing the query.
	 */
	public AddCustomers(Customer customer) throws SQLException {
		super.run();
		this.customer = customer;
	}

	@Override
	protected void query() {
		super.sql = customer.toString();
	}

}
