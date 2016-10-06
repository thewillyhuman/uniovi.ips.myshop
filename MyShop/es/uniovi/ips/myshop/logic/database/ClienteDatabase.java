package es.uniovi.ips.myshop.logic.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.ips.myshop.logic.bundle.Bundle;
import es.uniovi.ips.myshop.logic.people.Address;
import es.uniovi.ips.myshop.logic.people.Cliente;

public class ClienteDatabase {

	private List<Cliente> clientes;
	private MySQLDatabase database;
	private ResultSet rs;

	public ClienteDatabase(MySQLDatabase database) {
		clientes = new ArrayList<Cliente>();
		this.database = database;
	}

	public Cliente getCliente(String id) throws SQLException {
		this.clearProductos();
		database.executeSQL(Bundle.getString("bundle.myshop.getClienteByID"),
				id);
		rs = database.getResultSet();
		rs.next();
		return new Cliente(rs.getString(1), rs.getString(2), rs.getString(3),
				rs.getString(4), new Address("", "", "", ""));
	}

	public void addCliente(Cliente cliente) throws SQLException {
		database.executeUpdate(Bundle.getString("bundle.myshop.createNew"),
				cliente.getName(), cliente.getSurname(), cliente.getDni(),
				cliente.getAddress().getStreet(),
				cliente.getAddress().getCity(),
				cliente.getAddress().getState(),
				cliente.getAddress().getZipCode());
	}

	/**
	 * Clears the products list.
	 */
	private void clearProductos() {
		this.clientes.clear();
	}

}
