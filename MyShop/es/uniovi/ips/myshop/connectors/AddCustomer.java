package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
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
public class AddCustomer extends InBoardConnector {

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
		this.query();
	}

	public void query() {
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e1) {
			System.err.println("Error while connecting");
			e1.printStackTrace();
		}
		try {
			super.getDatabase().executeUpdate(Properties.getString("myshop.sql.insertClient"), customer.getName(), customer.getSurname(),
					customer.getDni());
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			ResultSet rs2 = super.getDatabase().executeSQL(Properties.getString("myshop.sql.lastInsertedID"));
			rs2.next();
			super.getDatabase().executeUpdate(Properties.getString("myshop.sql.insertAddress"), rs2.getString(1),
					customer.getAddress().getStreet(), customer.getAddress().getCity(),
					customer.getAddress().getState(), customer.getAddress().getZipCode());

		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		new DisconnectDatabase(super.getDatabase());
	}

}
