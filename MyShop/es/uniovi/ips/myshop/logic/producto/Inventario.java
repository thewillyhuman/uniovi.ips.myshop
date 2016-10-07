package es.uniovi.ips.myshop.logic.producto;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
	
	List<Producto> inventario;
	
	public Inventario() {
		inventario = new ArrayList<Producto>();
	}
	
	public void addProduct(Producto producto) {
		inventario.add(producto);
	}
	
	public List<Producto> getAllProducts() {
		return this.inventario;
	}

}
