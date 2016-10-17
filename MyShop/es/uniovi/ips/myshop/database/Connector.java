package es.uniovi.ips.myshop.database;

import es.uniovi.ips.myshop.controller.Controller;
import es.uniovi.ips.myshop.database.client.Database;

/**
 * 
 * Connector.java Helps to retrieve orders from the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161320
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public abstract class Connector {

	private Database db;

	/**
	 * Assigns the database to hold.
	 */
	public Connector() {
		db = Controller.getDatabase();
	}
	
	/**
	 * Gets the database related to this controller.
	 * 
	 * @return the main database.
	 */
	public Database getDatabase() {
		return this.db;
	}
}
