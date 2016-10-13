package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.people.Customer;
import es.uniovi.ips.myshop.properties.Properties;

/**
 * 
 * AddCustomers.java Adds a customer to the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161300
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class AddCustomer extends Connector {

	private Customer customer;

	/**
	 * Adds a single customer constructor. For a given customer object will add
	 * a record to the database.
	 * 
	 * @param customer
	 *            to be added to the database.
	 * @throws SQLException
	 *             if there is any error while connecting to the database or
	 *             executing the query.
	 */
	public AddCustomer(Customer customer) throws SQLException {
		this.customer = customer;
		super.run();
	}

	@Override
	protected void query() throws SQLException {
		super.db.executeUpdate(Properties.getString("sql.addClient"), customer.getName(), customer.getSurname(),
				customer.getDni());
		String id = db.executeSQL("sql.getCustomerIDByDNI", customer.getDni()).getString(1);
		super.db.executeUpdate(Properties.getString("sql.addClientAddress"), id, customer.getAddress().getStreet(),
				customer.getAddress().getCity(), customer.getAddress().getState(), customer.getAddress().getZipCode());
	}

}
