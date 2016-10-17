package es.uniovi.ips.myshop.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import es.uniovi.ips.myshop.connectors.AddCustomer;
import es.uniovi.ips.myshop.connectors.AddOrder;
import es.uniovi.ips.myshop.connectors.GetCustomers;
import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.connectors.GetProducts;
import es.uniovi.ips.myshop.connectors.GetWarehouseKeepers;
import es.uniovi.ips.myshop.connectors.ModifyOrder;
import es.uniovi.ips.myshop.database.client.Database;
import es.uniovi.ips.myshop.database.client.MySQLDatabase;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.people.Address;
import es.uniovi.ips.myshop.model.people.Customer;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.model.product.Product;
import es.uniovi.ips.myshop.properties.Properties;

public class SimpleTest {

	@Test
	public void connectionTest() throws SQLException {
		Database bd = new MySQLDatabase(Database.PROTOCOL_JDBC, MySQLDatabase.MYSQL_VENDOR,
				Properties.getString("myshop.server"), Properties.getString("myshop.port"),
				Properties.getString("myshop.database.name"), Properties.getString("myshop.user"),
				Properties.getString("myshop.password"));
		bd.connect();
		bd.closeConnection();
	}
	
	@Test
	public void getAllProductsTest() throws SQLException {
		for(Product p : new GetProducts().getAllProducts()) {
			System.out.println(p.getIDProducto() + " " + p.getLocalizacion().getLado().toString());
		}
	} //Properties.getString("myshop.sql.getProductByID")
	
	@Test
	public void getProductTest() throws SQLException {
		System.out.println("Descripción: " + new GetProducts().getProduct("1").getDescripcion());
	}
	
	@Test
	public void simpleQuery() throws SQLException {
		Database bd = new MySQLDatabase(Database.PROTOCOL_JDBC, MySQLDatabase.MYSQL_VENDOR,
				Properties.getString("myshop.server"), Properties.getString("myshop.port"),
				Properties.getString("myshop.database.name"), Properties.getString("myshop.user"),
				Properties.getString("myshop.password"));
		bd.connect();
		System.out.println("CONNECTED");
		bd.executeSQL(Properties.getString("myshop.sql.getProductByID"), 2);
		System.out.println("EXECUTED");
		while(bd.getResultSet().next())
			System.out.println(bd.getResultSet().getString(1));
		
		
		bd.closeConnection();
	}
	
	@Test
	public void addClientTest() throws SQLException {
		Customer pepe = new Customer("Pepe", "Borrar", "45170929XJ", new Address("Alcalá de Enares 5", "Oviedo", "Asturias", "33007"));
		new AddCustomer(pepe);
	}
	
	@Test
	public void addClienteSimpleTest() {
		//"INSERT INTO ips_myshop_dev.Cliente (nombre, apellidos, dni) VALUES (?,?,?)"
		Database bd = new MySQLDatabase(Database.PROTOCOL_JDBC, MySQLDatabase.MYSQL_VENDOR,
				Properties.getString("myshop.server"), Properties.getString("myshop.port"),
				Properties.getString("myshop.database.name"), Properties.getString("myshop.user"),
				Properties.getString("myshop.password"));
		try {
			bd.connect();
		} catch (SQLException e1) {
			System.err.println("Error while connecting");
			e1.printStackTrace();
		}
		try {
			bd.executeUpdate(Properties.getString("myshop.sql.insertClient"), "pepe", "Borrar", "45170929X");
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			ResultSet rs2 = bd.executeSQL(Properties.getString("myshop.sql.lastInsertedID"));
			rs2.next();
			System.out.println(rs2.getString(1));
			bd.executeUpdate(Properties.getString("myshop.sql.insertAddress"), rs2.getString(1),"aa","bb","cc",44507);
			
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		
		bd.closeConnection();
		System.out.println("Added");
	}
	
	@Test
	public void AddOrderTest() {
		Order order = new Order(new GetCustomers().getCustomer("1"), new Date());
		order.setStatus(Status.SOLICITADO);
		try {
			order.addProduct(new GetProducts().getProduct("1"), 100);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			order.addProduct(new GetProducts().getProduct("2"), 400);
			System.out.println(new GetProducts().getProduct("2").getDescripcion());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(order.getCliente().getName());
		new AddOrder(order);
	}
	
	@Test
	public void getCustomerTest() {
		Customer p = new GetCustomers().getCustomer("2");
		System.out.println(p.getName());
	}
	
	@Test
	public void getOrderTest() {
		Order order = null;
		try {
			order = new GetOrders().getOrder("22");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Order id: " + order.getIdPedido() + " Customer: " + order.getCliente().getDni() + " Total: " + order.getTotalPrice());
		for(OrderDetail od : order.getProductos()) {
			System.out.println(od.getProducto().getIDProducto() +"\t"+od.getCantidad()+"\t"+od.getProducto().getPrecio() + "\t" + od.getCantidad()*od.getProducto().getPrecio());
		}
	}
	
	@Test
	public void changeOrderStatusTest() {
		try {
			new ModifyOrder(new GetOrders().getOrder("23"), Status.LISTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllOrdersTest() {
		try {
			for(Order o : new GetOrders().getOrdersByStatus(Status.PENDIENTE)) {
				System.out.println(o.getIdPedido() + " " +String.valueOf(o.getEstado()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllWarehouseKeepersTest() {
		List<WharehouseKeeper> aux = new GetWarehouseKeepers().getAll();
		for(WharehouseKeeper wk : aux) {
			System.out.println(wk.getId() + " " + wk.getName());
		}
	}

}
