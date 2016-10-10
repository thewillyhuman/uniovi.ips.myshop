package es.uniovi.ips.myshop.model.order;

import es.uniovi.ips.myshop.model.product.Product;
import es.uniovi.ips.myshop.model.warehouse.incidences.Incidence;

/**
 * 
 * OrderDetail.java An "order detail" is a product plus its quantity, collected
 * and incidences attributes.
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161113
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class OrderDetail {

	private Product product;
	private int quantity;
	public boolean collected;
	public Incidence incidence;

	/**
	 * Creates a new entry for a product and its quantity.
	 * 
	 * @param product to add to the order.
	 * @param quantity to be added of the given product.
	 */
	public OrderDetail(Product product, int quantity) {
		this.product = product;
		setQuantity(quantity);
	}

	/**
	 * Gives the product associated with this order detail.
	 * 
	 * @return the product of the order detail.
	 */
	public Product getProducto() {
		return this.product;
	}

	/**
	 * Gets the quantity of the product.
	 * 
	 * @return the quantity of the product.
	 */
	public int getCantidad() {
		return this.quantity;
	}

	/**
	 * Sets the quantity for the product.
	 * 
	 * @param quantity to be purchased of a given product.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
