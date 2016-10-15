package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.client.Database;
import es.uniovi.ips.myshop.database.client.DatabaseConnection;
import es.uniovi.ips.myshop.model.people.Address;
import es.uniovi.ips.myshop.model.people.Customer;
import es.uniovi.ips.myshop.properties.Properties;

/**
 * 
 * GetCustomers.java Helps to retrieve customers from the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161300
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class GetCustomers {

	private String customerID;
	private Customer customer;
	private Database db;
	
	public GetCustomers(Database db) {
		this.db = db;
	}

	/**
	 * Returns the matching customer with the customer ID provided.
	 * 
	 * @param customerID
	 *            to look for in the database.
	 * @return the customer matching with the customer ID if founded. Null
	 *         otherwise.
	 * @throws SQLException 
	 */
	public Customer getCustomer(String customerID) {
		this.customerID = customerID;
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query();
		db.closeConnection();
		return customer;
	}

	protected void query()  {
		ResultSet rs = null;
		try {
			rs = db.executeSQL(Properties.getString("myshop.sql.getCustomerByID"), customerID);
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = db.executeSQL(Properties.getString("myshop.sql.getAddressByCustomerID"), customerID);
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			rs.next();
			rs2.next();
			customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					new Address(rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
