package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.ips.myshop.database.client.Database;
import es.uniovi.ips.myshop.model.product.Product;
import es.uniovi.ips.myshop.model.warehouse.ProductLocation;
import es.uniovi.ips.myshop.model.warehouse.ProductLocation.Lado;
import es.uniovi.ips.myshop.properties.Properties;

/**
 * 
 * GetProducts.java Helps to retrieve orders from the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 1210162148
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class GetProducts {
	
	private Database db;
	
	public GetProducts(Database db) {
		this.db = db;
	}

	/**
	 * Gets a list with all the products in the database.
	 * 
	 * @return a list with all the products contained in the database.
	 * @throws SQLException
	 */
	public List<Product> getAllProducts() throws SQLException {
		db.connect();
		List<Product> aux = new ArrayList<Product>();
		ResultSet rs = db.executeSQL(Properties.getString("myshop.sql.allProducts"));
		Product product;
		while (rs.next()) {
			ResultSet rs2 = db.executeSQL(Properties.getString("myshop.sql.getProductLocationByProductID"), rs.getString(1));
			rs2.next();
			Lado side;
			if (rs2.getString(3) == "DERECHA")
				side = Lado.DERECHA;
			else
				side = Lado.IZQUIERDA;
			product = new Product(rs.getString(1), rs.getString(4), rs.getDouble(2),
					new ProductLocation(rs2.getInt(2), rs2.getInt(4), rs2.getInt(5), side));
			aux.add(product);
		}
		db.closeConnection();
		return aux;
	}

	/**
	 * Gets an specific product from its database.
	 * 
	 * @param productID
	 *            is the reference to the product in the database.
	 * @return the product if founded, null otherwise.
	 * @throws SQLException
	 */
	public Product getProduct(String productID) throws SQLException {
		db.connect();
		ResultSet rs = db.executeSQL(Properties.getString("myshop.sql.getProductByID"), productID);
		rs.next();
		ResultSet rs2 = db.executeSQL(Properties.getString("myshop.sql.getProductLocationByProductID"), rs.getString(1));
		rs2.next();
		Lado side;
		if (rs2.getString(3) == "DERECHA")
			side = Lado.DERECHA;
		else
			side = Lado.IZQUIERDA;
		Product aux =  new Product(rs.getString(1), rs.getString(4), rs.getDouble(2),
				new ProductLocation(rs2.getInt(2), rs2.getInt(4), rs2.getInt(5), side));
		db.closeConnection();
		return aux;

	}
}
