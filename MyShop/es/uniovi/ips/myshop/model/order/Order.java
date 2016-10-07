package es.uniovi.ips.myshop.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.uniovi.ips.myshop.model.people.Address;
import es.uniovi.ips.myshop.model.people.Customer;
import es.uniovi.ips.myshop.model.product.Product;

public class Order {

	private String idPedido;
	private Customer cliente;
	private List<OrderDetail> productos;
	private Date fecha;
	Estado estado;

	public enum Estado {
		EN_PROCESO, ASIGNADO, INCIDENCIA, EMPAQUETANDO
	}

	public Order(String idPedido, Customer cliente, Date fecha) {
		setIdPedido(idPedido);
		setCliente(cliente);
		setDate(fecha);
		productos = new ArrayList<OrderDetail>();
		estado = Estado.EN_PROCESO;
	}

	private void setCliente(Customer cliente) {
		this.cliente = cliente;
	}

	private void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public String getIdPedido() {
		return this.idPedido;
	}

	public Customer getCliente() {
		return this.cliente;
	}

	public void addProducto(Product producto, int cantidad) {
		productos.add(new OrderDetail(producto, cantidad));
	}
	
	public void modificarCantidad(Product producto, int nuevaCantidad) {
		for(OrderDetail dp : productos) {
			if(dp.getProducto().equals(producto))
				dp.setCantidad(nuevaCantidad);
		}
	}

	public void removeProducto(Product producto) {
		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).getProducto().getIDProducto() == producto
					.getIDProducto()) {
				productos.remove(i);
				break;
			}
		}
	}

	public List<OrderDetail> getProductos() {
		return this.productos;
	}

	public Date getDate() {
		return this.fecha;
	}

	public void setDate(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecioTotal() {
		double res = 0.0;
		for (OrderDetail dp : productos)
			res += dp.getProducto().getPrecio() * dp.getCantidad();
		return res;
	}

	public int size() {
		int res = 0;
		for (OrderDetail dp : productos)
			res += dp.getCantidad();
		return res;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public String printEtiquetaEnvio() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCliente().getName() + " " + getCliente().getSurname()
				+ "\n");
		Address add = getCliente().getAddress();
		sb.append(add.getStreet() + "\n" + add.getCity() + "" + add.getState()
				+ " " + add.getZipCode() + "\n");
		return sb.toString();
	}

	public String printAlbarán() {
		StringBuilder sb = new StringBuilder();
		sb.append("==== ALBARÁN DE COMPRA ====\n").append("MY SHOP\n\n")
				.append("PRODUCTO\t\tCANTIDAD\t\tPRECIO/U\t\tTOTAL\n");
		for (OrderDetail dp : productos) {
			sb.append(dp.getProducto().getDescripcion() + "\t\t"
					+ dp.getCantidad() + "\t\t" + dp.getProducto().getPrecio()
					+ "\t\t" + dp.getCantidad() * dp.getProducto().getPrecio()
					+ "\n");
		}

		return sb.toString();
	}
}
