package es.uniovi.ips.myshop.database.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase extends Database {

	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_VENDOR = "mysql";
	private String user, password, url;
	private boolean useSSL = false;
	private boolean relaxAutocommit = true;

	public MySQLDatabase(String protocol, String vendor, String server, String port, String databaseName, String user,
			String password) {
		this.user = user;
		this.password = password;
		url = protocol + ":" + vendor + "://" + server + ":" + port + "/" + databaseName + "?useSSL=" + useSSL
				+ "&relaxAutoCommit=" + relaxAutocommit;
		try {
			Class.forName(MYSQL_DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Error while registering MySQL Driver");
			e.printStackTrace();
		}
		try {
			connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Connection connect() throws SQLException {
		this.conn = DriverManager.getConnection(url, user, password);
		System.out.println("--> Connection " + this.conn.toString() + " has been oppened succesfully.");
		return this.conn;
	}
	
	@Override
	public Connection connectToPool() throws SQLException {
		this.conn = cp.getConnection();
		return this.conn;
	}
}
