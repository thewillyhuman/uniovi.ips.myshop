package es.uniovi.ips.myshop.connectors;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.people.Customer;

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

	/**
	 * Returns the matching customer with the customer ID provided.
	 * 
	 * @param customerID to look for in the database.
	 * @return the customer matching with the customer ID if founded. Null otherwise.
	 */
	public Customer getCustomer(String customerID) {
		return null;
	}

	@Override
	protected void query() {
		// TODO Auto-generated method stub
	}

}
