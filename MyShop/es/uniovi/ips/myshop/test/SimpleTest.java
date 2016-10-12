package es.uniovi.ips.myshop.test;

import java.sql.SQLException;

import org.junit.Test;

import es.uniovi.ips.myshop.connectors.GetProducts;
import es.uniovi.ips.myshop.database.client.Database;
import es.uniovi.ips.myshop.database.client.MySQLDatabase;
import es.uniovi.ips.myshop.model.product.Product;
import es.uniovi.ips.myshop.properties.Properties;

public class SimpleTest {

	@Test
	public void connectionTest() throws SQLException {
		Database bd = new MySQLDatabase(Database.PROTOCOL_JDBC, MySQLDatabase.MYSQL_VENDOR,
				Properties.getString("myshop.server"), Properties.getString("myshop.port"),
				Properties.getString("myshop.database.name"), Properties.getString("myshop.user"),
				Properties.getString("myshop.password"));
		bd.connect();
		bd.closeConnection();
	}
	
	@Test
	public void getAllProductsTest() throws SQLException {
		Database bd = new MySQLDatabase(Database.PROTOCOL_JDBC, MySQLDatabase.MYSQL_VENDOR,
				Properties.getString("myshop.server"), Properties.getString("myshop.port"),
				Properties.getString("myshop.database.name"), Properties.getString("myshop.user"),
				Properties.getString("myshop.password"));
		for(Product p : new GetProducts(bd).getAllProducts()) {
			System.out.println(p.getIDProducto() + " " + p.getLocalizacion().getLado().toString());
		}
	} //Properties.getString("myshop.sql.getProductByID")
	
	@Test
	public void getProductTest() throws SQLException {
		Database bd = new MySQLDatabase(Database.PROTOCOL_JDBC, MySQLDatabase.MYSQL_VENDOR,
				Properties.getString("myshop.server"), Properties.getString("myshop.port"),
				Properties.getString("myshop.database.name"), Properties.getString("myshop.user"),
				Properties.getString("myshop.password"));
		System.out.println("Descripción: " + new GetProducts(bd).getProduct("1").getDescripcion());
	}
	
	@Test
	public void simpleQuery() throws SQLException {
		Database bd = new MySQLDatabase(Database.PROTOCOL_JDBC, MySQLDatabase.MYSQL_VENDOR,
				Properties.getString("myshop.server"), Properties.getString("myshop.port"),
				Properties.getString("myshop.database.name"), Properties.getString("myshop.user"),
				Properties.getString("myshop.password"));
		bd.connect();
		System.out.println("CONNECTED");
		bd.executeSQL(Properties.getString("myshop.sql.getProductByID"), 2);
		System.out.println("EXECUTED");
		while(bd.getResultSet().next())
			System.out.println(bd.getResultSet().getString(1));
		
		
		bd.closeConnection();
	}

}
