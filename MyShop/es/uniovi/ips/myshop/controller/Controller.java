package es.uniovi.ips.myshop.controller;

import es.uniovi.ips.myshop.database.client.Database;
import es.uniovi.ips.myshop.database.client.MySQLDatabase;
import es.uniovi.ips.myshop.properties.Properties;

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
	
	private static Database db = new MySQLDatabase(Database.PROTOCOL_JDBC, MySQLDatabase.MYSQL_VENDOR,
			Properties.getString("myshop.server"), Properties.getString("myshop.port"),
			Properties.getString("myshop.database.name"), Properties.getString("myshop.user"),
			Properties.getString("myshop.password"));
	
	public static Database getDatabase() {
		return db;
	}

}
