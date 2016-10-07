package es.uniovi.ips.myshop.logic.almacen;

import es.uniovi.ips.myshop.logic.producto.Producto;

public class LocalizacionProducto {

	public final static int N_PASILLOS = 2;
	public final static int N_POSICIONES = 10;
	public final static int N_ALTURAS = 5;

	public enum Lado {
		DERECHA, IZQUIERDA
	}
	
	private Producto prod;
	private int pasillo, posicion, altura;
	private Lado lado;
	
	
}
