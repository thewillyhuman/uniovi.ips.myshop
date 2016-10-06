package es.uniovi.ips.myshop.logic.producto;

/**
 * 
 * Producto.java
 *
 * @author Guillermo Facundo Colunga
 * @version 61020161900
 * @since 6 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Producto {
	
	private String IDProd, descripcion;
	private double precio;
	
	/**
	 * Main constructor ready to get all the product basic data to represent one.
	 * 
	 * @param IDProd is the id of the product.
	 * @param descripcion of the product.
	 * @param precio of the product.
	 */
	public Producto(String IDProd, String descripcion, double precio) {
		setIDProducto(IDProd);
		setDescription(descripcion);
		setPrecio(precio);
	}
	
	/**
	 * Sets the id of the product to the given one.
	 * 
	 * @param IDProd is the product id to set.
	 */
	private void setIDProducto(String IDProd) {
		this.IDProd = IDProd;
	}
	
	public String getIDProducto() {
		return this.IDProd;
	}
	
	private void setDescription(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	private void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return this.precio;
	}

}
