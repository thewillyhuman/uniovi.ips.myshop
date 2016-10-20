package es.uniovi.ips.myshop.model.order;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.uniovi.ips.myshop.model.people.Address;
import es.uniovi.ips.myshop.model.people.Customer;
import es.uniovi.ips.myshop.model.product.Product;

/**
 * 
 * Order.java represents an order in the system.
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161112
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Order {

	private String orderID;
	private Customer customer;
	private List<OrderDetail> products;
	private Date date;
	private Status status;

	public enum Status {
		PENDIENTE, SOLICITADO, LISTO, EMPAQUETANDO, INCIDENCIA;

		public static Status getValueOf(String string) {
			if(string.equals("PENDIENTE"))
				return PENDIENTE;
			else if(string.equals("SOLICITADO"))
				return SOLICITADO;
			else if(string.equals("LISTO"))
				return LISTO;
			else if(string.equals("INCIDENCIA"))
				return INCIDENCIA;
			else 
				return EMPAQUETANDO;
		}
	}

	/**
	 * Creates an empty order where we will be able to add and delete products.
	 * 
	 * @param orderID is the id of the order.
	 * @param customer who own the order.
	 * @param date where the order is taking place.
	 */
	public Order(String orderID, Customer customer, Date date) {
		setIdPedido(orderID);
		setCliente(customer);
		setDate(date);
		products = new ArrayList<OrderDetail>();
		status = Status.PENDIENTE;
	}
	
	/**
	 * Creates an empty order where we will be able to add and delete products.
	 * 
	 * @param customer who own the order.
	 * @param date where the order take place.
	 */
	public Order(Customer customer, Date date) {
		setCliente(customer);
		setDate(date);
		products = new ArrayList<OrderDetail>();
		status = Status.PENDIENTE;
	}
	
	public Order(String orderID, Customer customer, Date date, Status status) {
		setIdPedido(orderID);
		setCliente(customer);
		setDate(date);
		products = new ArrayList<OrderDetail>();
		this.status = status;
	}

	/**
	 * Sets the customer as the order owner.
	 * 
	 * @param customer to be set in the order.
	 */
	private void setCliente(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Sets an ID for the order.
	 * 
	 * @param idPedido is the order id, given by the database.
	 */
	private void setIdPedido(String idPedido) {
		this.orderID = idPedido;
	}

	/**
	 * Gives the id of the current order.
	 * 
	 * @return the id of the current order.
	 */
	public String getIdPedido() {
		return this.orderID;
	}

	/**
	 * Gets the customer who made the order.
	 * 
	 * @return the customer who made the order.
	 */
	public Customer getCliente() {
		return this.customer;
	}

	/**
	 * Adds a product to the order with its quantity.
	 * 
	 * @param product to be added to the order.
	 * @param quantity to be added of the given product.
	 */
	public void addProduct(Product product, int quantity) {
		products.add(new OrderDetail(product, quantity));
	}

	/**
	 * Changes the quantity of a given product.
	 * 
	 * @param product to change the quantity.
	 * @param newQuantity for the given product.
	 */
	public void modifyQuantity(Product product, int newQuantity) {
		for (OrderDetail dp : products) {
			if (dp.getProducto().equals(product))
				dp.setQuantity(newQuantity);
		}
	}

	/**
	 * Deletes a product from the order.
	 * 
	 * @param product to be be deleted.
	 */
	public void removeProduct(Product product) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProducto().getIDProducto() == product
					.getIDProducto()) {
				products.remove(i);
				break;
			}
		}
	}

	/**
	 * Gives a list with all the products in the order.
	 * 
	 * @return a list with all the products in the order.
	 */
	public List<OrderDetail> getProductos() {
		return this.products;
	}

	/**
	 * Gets the date where the order was completed.
	 * 
	 * @return the date where the order was completed.
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date for the order.
	 * 
	 * @param fecha
	 */
	private void setDate(Date fecha) {
		this.date = fecha;
	}

	/**
	 * Gets the total price for the order.
	 * 
	 * @return a double containing the total price of the order.
	 */
	public double getTotalPrice() {
		double res = 0.0;
		for (OrderDetail dp : products)
			res += dp.getProducto().getPrecio() * dp.getCantidad();
		return res;
	}

	/**
	 * Gets the size of the order.
	 * 
	 * @return the size of the order.
	 */
	public int size() {
		int res = 0;
		for (OrderDetail dp : products)
			res += dp.getCantidad();
		return res;
	}

	/**
	 * Sets the current status for the order.
	 * 
	 * @param status of the order.
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Gets the status of the order.
	 * 
	 * @return the status of the order.
	 */
	public Status getEstado() {
		return this.status;
	}

	/**
	 * Gives a string containing all the shipping info formatted.
	 * 
	 * @return the shipping info formatted.
	 */
	public String printShippingInfo() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("IMPRESORA--ETIQUETA-ENVIO.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(getCliente().getName() + " " + getCliente().getSurname()
				+ "\n");
		Address add = getCliente().getAddress();
		sb.append(add.getStreet() + "\n" + add.getCity() + "" + add.getState()
				+ " " + add.getZipCode() + "\n");
		writer.write(sb.toString());
		writer.close();
		return sb.toString();
	}

	/**
	 * Gives a string containing the bill.
	 * 
	 * @return a formatted string containing the bill.
	 */
	public String printBill() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("IMPRESORA--ALBARÁN.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("==== ALBARÁN DE COMPRA ====\n").append("MY SHOP\n\n").append(
				"PRODUCTO\t\tCANTIDAD\t\tPRECIO/U\t\tTOTAL\n");
		for (OrderDetail dp : products) {
			sb.append(dp.getProducto().getIDProducto() + "\t\t" + dp
					.getCantidad() + "\t\t" + dp.getProducto().getPrecio()
					+ "\t\t" + dp.getCantidad() * dp.getProducto().getPrecio()
					+ "\n");
		}
		writer.write(sb.toString());
		writer.close();
		return sb.toString();
	}
}
