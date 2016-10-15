package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.client.Database;
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
public class AddCustomer {

	private Customer customer;
	private Database db;

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
	public AddCustomer(Database db, Customer customer) throws SQLException {
		this.customer = customer;
		this.db = db;
		this.query();
	}

	protected void query() {
		/*
		 * System.out.println("Update start");
		 * db.executeUpdate(Properties.getString("myshop.sql.insertClient"),
		 * customer.getName(), customer.getSurname(), customer.getDni());
		 * System.out.println("Update end"); System.out.println("Load resultSet"
		 * ); ResultSet rs2 =
		 * db.executeSQL(Properties.getString("myshop.sql.lastInsertID"));
		 * rs2.next(); System.out.println("RS Loaded"); String clientID =
		 * rs2.getString(1); System.out.println("SecondQuery start");
		 * db.executeUpdate(Properties.getString("myshop.sql.insertAddress"),
		 * clientID, customer.getAddress().getStreet(),
		 * customer.getAddress().getCity(), customer.getAddress().getState(),
		 * customer.getAddress().getZipCode()); System.out.println(
		 * "SecondQuery end");
		 */
		try {
			db.connect();
		} catch (SQLException e1) {
			System.err.println("Error while connecting");
			e1.printStackTrace();
		}
		try {
			db.executeUpdate(Properties.getString("myshop.sql.insertClient"), customer.getName(), customer.getSurname(),
					customer.getDni());
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			ResultSet rs2 = db.executeSQL(Properties.getString("myshop.sql.lastInsertedID"));
			rs2.next();
			System.out.println(rs2.getString(1));
			db.executeUpdate(Properties.getString("myshop.sql.insertAddress"), rs2.getString(1),
					customer.getAddress().getStreet(), customer.getAddress().getCity(),
					customer.getAddress().getState(), customer.getAddress().getZipCode());

		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}

		db.closeConnection();
		System.out.println("Added");
	}

}
