package es.uniovi.ips.myshop.database.client;


public class DisconnectDatabase {

	/**
	 * Disconnects from the given Database.
	 * 
	 * @param db is the database to disconnect from.
	 */
	public DisconnectDatabase(Database db) {
			db.closeConnection();
	}
}
