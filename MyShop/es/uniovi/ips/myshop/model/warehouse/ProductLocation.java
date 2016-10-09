package es.uniovi.ips.myshop.model.warehouse;

import es.uniovi.ips.myshop.model.product.Product;

public class ProductLocation {

	public final static int N_PASILLOS = 2;
	public final static int N_POSICIONES = 10;
	public final static int N_ALTURAS = 5;

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
