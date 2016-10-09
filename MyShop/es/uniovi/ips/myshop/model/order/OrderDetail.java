package es.uniovi.ips.myshop.model.order;

import es.uniovi.ips.myshop.model.product.Product;
import es.uniovi.ips.myshop.model.warehouse.incidences.Incidence;

public class OrderDetail {
	
	private Product producto;
	private int cantidad;
	public boolean recogido;
	public Incidence incidencia;
	
	public OrderDetail(Product producto, int cantidad) {
		this.producto = producto;
		setCantidad(cantidad);
	}
	
	public Product getProducto() {
		return this.producto;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
