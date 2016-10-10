package es.uniovi.ips.myshop.model.warehouse;

import es.uniovi.ips.myshop.model.product.Product;

/**
 * 
 * ProductLocation.java
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161154
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class ProductLocation {

	private Product prod;
	private int pasillo, posicion, altura;
	private Lado lado;
	
	public enum Lado {
		DERECHA, IZQUIERDA
	}
	
	public ProductLocation(Product prod, int pasillo, int posicion, int altura, Lado lado) {
		super();
		this.prod = prod;
		this.pasillo = pasillo;
		this.posicion = posicion;
		this.altura = altura;
		this.lado = lado;
	}

	/**
	 * @return the prod
	 */
	public Product getProd() {
		return prod;
	}

	/**
	 * @return the pasillo
	 */
	public int getPasillo() {
		return pasillo;
	}

	/**
	 * @return the posicion
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * @return the altura
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * @return the lado
	 */
	public Lado getLado() {
		return lado;
	}
	
	
}
