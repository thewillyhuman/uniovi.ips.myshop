package es.uniovi.ips.myshop.controller;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.client.Database;
import es.uniovi.ips.myshop.database.client.DatabaseConnection;
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
	
	private static Database db = null;//new MySQLDatabase();
	
	public static Database getDatabase() {
		return db;
	}
	
	public static void openConnection() throws SQLException {
		new DatabaseConnection(db);
	}
	
	public static void closeConnection() throws SQLException {
		db.closeConnection();
	}
}
