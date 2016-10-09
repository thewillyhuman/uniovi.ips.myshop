package es.uniovi.ips.myshop.model.product;

import java.sql.SQLException;
import java.util.List;

import es.uniovi.ips.myshop.connectors.AddProducts;
import es.uniovi.ips.myshop.connectors.GetProducts;

public class Inventary {
	
	public void addProduct(Product product) throws SQLException {
		new AddProducts(product);
	}
	
	public List<Product> getAllProducts() {
		return new GetProducts().getAllProducts();
	}
	
	public Product getProduct(String productID) {
		return new GetProducts().getProduct(productID);
	}

}
