package es.uniovi.ips.myshop.database.client;

import java.sql.Connection;
import java.sql.SQLException;

import es.uniovi.ips.myshop.properties.Properties;

/**
 * 
 * DatabaseConnection.java Holds the connection operations with the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 0810161300
 * @since 8 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class DatabaseConnection {

	private Database db;

	/**
	 * Receives a database as a parameter, if the database has any connection
	 * Opened will only hold it. Otherwise will open a new connection.
	 * 
	 * @param db
	 *            database to manage connections.
	 * @throws SQLException
	 *             if there's errors openning the connection.
	 */
	public DatabaseConnection(Database db) throws SQLException {
		this.db = db;
		if (db.conn.isClosed() || db.conn==null)
			db.connect();
	}
}
