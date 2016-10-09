package es.uniovi.ips.myshop.controller;

import es.uniovi.ips.myshop.database.client.Database;
import es.uniovi.ips.myshop.database.client.MySQLDatabase;

/**
 * 
 * Controller.java Holds the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161315
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Controller {
	
	private static Database db = new MySQLDatabase();
	
	public static Database getDatabase() {
		return db;
	}
}
