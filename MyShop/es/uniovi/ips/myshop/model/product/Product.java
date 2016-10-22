package es.uniovi.ips.myshop.model.product;

import es.uniovi.ips.myshop.model.warehouse.ProductLocation;

/**
 * 
 * Producto.java
 *
 * @author Guillermo Facundo Colunga
 * @version 61020161900
 * @since 6 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Product {

	private String IDProd, descripcion;
	private double precio;
	private ProductLocation localizacion;

	/**
	 * Main constructor ready to get all the product basic data to represent
	 * one.
	 * 
	 * @param IDProd is the id of the product.
	 * @param descripcion of the product.
	 * @param precio of the product.
	 */
	public Product(String IDProd, String descripcion, double precio, ProductLocation location) {
		setIDProducto(IDProd);
		setDescription(descripcion);
		setPrecio(precio);
		localizacion = location;
	}

	/**
	 * Sets the id of the product to the given one.
	 * 
	 * @param IDProd is the product id to set.
	 */
	private void setIDProducto(String IDProd) {
		this.IDProd = IDProd;
	}

	/**
	 * Gets the Id of the product.
	 * 
	 * @return the id of the product
	 */
	public String getIDProducto() {
		return this.IDProd;
	}

	/**
	 * Sets the description for a given product.
	 * 
	 * @param descripcion of the product.
	 */
	private void setDescription(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the description of a given product as a string
	 * 
	 * @return the description of the product.
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Sets the price of the product.
	 * 
	 * @param precio id the price of the product.
	 */
	private void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Gets the price of the current product.
	 * 
	 * @return the price of the current product.
	 */
	public double getPrecio() {
		return this.precio;
	}
	
	/**
	 * Gets the place where a product is in the warehouse.
	 * 
	 * @return where a product is in the warehouse as a location.
	 */
	public ProductLocation getLocalizacion() {
		return this.localizacion;
	}

}
