package es.uniovi.ips.myshop.logic.order;

import es.uniovi.ips.myshop.logic.almacen.incidencias.Incidencia;
import es.uniovi.ips.myshop.logic.producto.Producto;

public class DetallePedido {
	
	private Producto producto;
	private int cantidad;
	public boolean recogido;
	public Incidencia incidencia;
	
	public DetallePedido(Producto producto, int cantidad) {
		this.producto = producto;
		setCantidad(cantidad);
	}
	
	public Producto getProducto() {
		return this.producto;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
