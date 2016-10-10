package es.uniovi.ips.myshop.model.product;

import java.sql.SQLException;
import java.util.List;

import es.uniovi.ips.myshop.connectors.AddProducts;
import es.uniovi.ips.myshop.connectors.GetProducts;

/**
 * 
 * Inventory.java
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161142
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Inventory {
	
	/**
	 * Adds a product to the inventory.
	 * 
	 * @param product to be add.
	 * @throws SQLException if any problem while adding the product to the database.
	 */
	public void addProduct(Product product) throws SQLException {
		new AddProducts(product);
	}
	
	/**
	 * Gives a list with all the products in the database.
	 * 
	 * @return a list containning all the products in the database.
	 */
	public List<Product> getAllProducts() {
		return new GetProducts().getAllProducts();
	}
	
	/**
	 * Gives a single product that matches the productID in the database.
	 * 
	 * @param productID to look for in the database.
	 * @return the product if founded in the database. Null otherwise.
	 */
	public Product getProduct(String productID) {
		return new GetProducts().getProduct(productID);
	}

}
