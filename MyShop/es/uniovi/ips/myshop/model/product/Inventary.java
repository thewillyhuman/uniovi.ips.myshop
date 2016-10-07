package es.uniovi.ips.myshop.model.product;

import java.util.ArrayList;
import java.util.List;

public class Inventary {
	
	List<Product> inventario;
	
	public Inventary() {
		inventario = new ArrayList<Product>();
	}
	
	public void addProduct(Product producto) {
		inventario.add(producto);
	}
	
	public List<Product> getAllProducts() {
		return this.inventario;
	}

}
