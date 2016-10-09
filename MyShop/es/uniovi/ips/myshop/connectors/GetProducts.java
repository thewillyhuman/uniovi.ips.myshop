package es.uniovi.ips.myshop.connectors;

import java.util.List;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.product.Product;

/**
 * 
 * GetProducts.java Helps to retrieve orders from the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161300
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class GetProducts extends Connector {
	
	/**
	 * Gets a list with all the products in the database.
	 * 
	 * @return a list with all the products contained in the database.
	 */
	public List<Product> getAllProducts() {
		return null;
	}
	
	/**
	 * Gets an specific product from its database.
	 * 
	 * @param productID is the reference to the product in the database.
	 * @return the product if founded, null otherwise.
	 */
	public Product getProduct(String productID) {
		return null;
	}

	@Override
	protected void query() {
		// TODO Auto-generated method stub
		
	} 
}
