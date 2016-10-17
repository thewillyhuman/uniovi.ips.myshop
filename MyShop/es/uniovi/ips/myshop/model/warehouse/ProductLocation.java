package es.uniovi.ips.myshop.model.warehouse;

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

	private int pasillo, posicion, altura;
	private Lado lado;
	
	public enum Lado {
		DERECHA, IZQUIERDA
	}
	
	public ProductLocation(int pasillo, int posicion, int altura, Lado lado) {
		super();
		this.pasillo = pasillo;
		this.posicion = posicion;
		this.altura = altura;
		this.lado = lado;
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
