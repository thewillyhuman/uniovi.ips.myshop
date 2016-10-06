package es.uniovi.ips.myshop.logic.order;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.ips.myshop.logic.people.Cliente;
import es.uniovi.ips.myshop.logic.producto.Producto;

public class Pedido {

	private String idPedido;
	private Cliente cliente;
	private List<Producto> productos;
	
	public Pedido(String idPedido, Cliente cliente) {
		setIdPedido(idPedido);
		setCliente(cliente);
		productos = new ArrayList<Producto>();
	}

	private void setCliente(Cliente cliente) {
		this.cliente = cliente;
		
	}

	private void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
		
	}
	
	public String getIdPedido() {
		return this.idPedido;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void addProducto(Producto producto) {
		productos.add(producto);
	}
	
	public List<Producto> getProductos() {
		return this.productos;
	}
}
