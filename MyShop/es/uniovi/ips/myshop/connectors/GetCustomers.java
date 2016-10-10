package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
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
public class GetCustomers extends Connector {

	private String customerID;
	private Customer customer;

	/**
	 * Returns the matching customer with the customer ID provided.
	 * 
	 * @param customerID
	 *            to look for in the database.
	 * @return the customer matching with the customer ID if founded. Null
	 *         otherwise.
	 * @throws SQLException 
	 */
	public Customer getCustomer(String customerID) throws SQLException {
		this.customerID = customerID;
		new DatabaseConnection(db);
		query();
		new DatabaseConnection(db).closeConnection();
		return customer;
	}

	@Override
	protected void query() throws SQLException {
		ResultSet rs = super.db.executeSQL(Properties.getString("sql.getCustomerByID"), customerID);
		ResultSet rs2 = super.db.executeSQL(Properties.getString("sql.getAddressByCustomerID"), customerID);
		customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
				new Address(rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5)));
	}

}
