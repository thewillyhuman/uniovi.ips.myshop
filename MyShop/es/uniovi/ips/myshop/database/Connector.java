package es.uniovi.ips.myshop.database;

import java.sql.SQLException;

import es.uniovi.ips.myshop.controller.Controller;
import es.uniovi.ips.myshop.database.client.Database;
import es.uniovi.ips.myshop.database.client.DatabaseConnection;

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

	protected Database db;

	/**
	 * Assigns the database to hold.
	 */
	public Connector() {
		db = Controller.getDatabase();
	}

	/**
	 * Will open a new connection to the database, execute the query of the
	 * controller and finally close the connection. So only one connection at a
	 * time will be performed.
	 * 
	 * @throws SQLException if there's any error while connecting, disconnecting or executing the query in the database.
	 */
	protected void run() throws SQLException {

	}

	protected abstract void query() throws SQLException;
}
