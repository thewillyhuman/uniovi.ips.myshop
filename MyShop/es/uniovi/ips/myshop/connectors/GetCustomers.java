package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.OutBoardConnector;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
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
public class GetCustomers extends Connector implements OutBoardConnector {

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
	public Customer getCustomer(String customerID) {
		this.customerID = customerID;
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		query();
		new DisconnectDatabase(super.getDatabase());
		return customer;
	}
	
	protected void query() {
		ResultSet rs = null;
		try {
			rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getCustomerByID"), customerID);
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getAddressByCustomerID"), customerID);
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			rs.next();
			if (rs2.next()) {
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new Address(rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5)));
			} 
			else {
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), null);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
