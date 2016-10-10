package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.model.product.Product;
import es.uniovi.ips.myshop.model.warehouse.ProductLocation;
import es.uniovi.ips.myshop.model.warehouse.ProductLocation.Lado;
import es.uniovi.ips.myshop.properties.Properties;

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
	 * @throws SQLException
	 */
	public List<Product> getAllProducts() throws SQLException {
		List<Product> aux = new ArrayList<Product>();
		ResultSet rs = super.db.executeSQL(Properties.getString("sql.getAllProducts"));
		Product product;
		while (rs.next()) {
			ResultSet rs2 = super.db.executeSQL(Properties.getString("sql.getLocationByProductID"), rs.getString(1));
			Lado side;
			if (rs2.getString(3) == "DERECHA")
				side = Lado.DERECHA;
			else
				side = Lado.IZQUIERDA;
			product = new Product(rs.getString(1), rs.getString(4), rs.getDouble(2),
					new ProductLocation(rs2.getInt(2), rs2.getInt(4), rs2.getInt(5), side));
			aux.add(product);
		}
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
		ResultSet rs = super.db.executeSQL(Properties.getString("sql.getProductByID"), productID);
		ResultSet rs2 = super.db.executeSQL(Properties.getString("sql.getLocationByProductID"), rs.getString(1));
		Lado side;
		if (rs2.getString(3) == "DERECHA")
			side = Lado.DERECHA;
		else
			side = Lado.IZQUIERDA;
		return new Product(rs.getString(1), rs.getString(4), rs.getDouble(2),
				new ProductLocation(rs2.getInt(2), rs2.getInt(4), rs2.getInt(5), side));

	}

	@Override
	protected void query() throws SQLException {
		// Not implemented.
	}
}
