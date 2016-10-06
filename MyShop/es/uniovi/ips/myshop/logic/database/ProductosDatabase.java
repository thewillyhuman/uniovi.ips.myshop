package es.uniovi.ips.myshop.logic.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.ips.myshop.logic.bundle.Bundle;
import es.uniovi.ips.myshop.logic.producto.Producto;

public class ProductosDatabase {

	private List<Producto> productos;
	private MySQLDatabase database;
	private ResultSet rs;

	public ProductosDatabase(MySQLDatabase database) {
		productos = new ArrayList<Producto>();
		this.database = database;
	}

	/**
	 * Gets all the products in the database as a list of products.
	 * 
	 * @return a list of all the products in the database.
	 * @throws SQLException if there is any error while executing the SQL
	 *             sentence or loading the products into the array
	 */
	public List<Producto> getAllProducts() throws SQLException {
		this.clearProductos();
		database.executeSQL(Bundle.getString("bundle.myshop.allProducts"));
		rs = database.getResultSet();
		while (rs.next()) {
			Producto aux = new Producto(rs.getString(1), rs.getString(2),
					rs.getDouble(3));
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
