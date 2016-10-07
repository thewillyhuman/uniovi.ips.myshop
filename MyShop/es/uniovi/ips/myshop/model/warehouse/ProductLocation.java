package es.uniovi.ips.myshop.model.warehouse;

import es.uniovi.ips.myshop.model.product.Product;

public class ProductLocation {

	public final static int N_PASILLOS = 2;
	public final static int N_POSICIONES = 10;
	public final static int N_ALTURAS = 5;

	public enum Lado {
		DERECHA, IZQUIERDA
	}
	
	private Product prod;
	private int pasillo, posicion, altura;
	private Lado lado;
	
	
}
