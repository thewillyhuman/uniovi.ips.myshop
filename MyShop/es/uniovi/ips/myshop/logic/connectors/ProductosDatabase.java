package es.uniovi.ips.myshop.logic.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.ips.myshop.logic.resourceBundle.Bundle;
import es.uniovi.ips.myshop.model.product.Product;

public class ProductosDatabase {

	private List<Product> productos;
	private MySQLDatabase database;
	private ResultSet rs;

	public ProductosDatabase(MySQLDatabase database) {
		productos = new ArrayList<Product>();
		this.database = database;
	}

	/**
	 * Gets all the products in the database as a list of products.
	 * 
	 * @return a list of all the products in the database.
	 * @throws SQLException
	 *             if there is any error while executing the SQL sentence or
	 *             loading the products into the array
	 */
	public List<Product> getAllProducts() throws SQLException {
		this.clearProductos();
		database.executeSQL(Bundle.getString("bundle.myshop.allProducts"));
		rs = database.getResultSet();
		while (rs.next()) {
			Product aux = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3));
			productos.add(aux);
		}
		return this.productos;
	}

	/**
	 * Clears the products list.
	 */
	private void clearProductos() {
		this.productos.clear();
	}
}
