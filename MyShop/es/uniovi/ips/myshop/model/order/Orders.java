package es.uniovi.ips.myshop.model.order;

import java.sql.SQLException;
import java.util.List;

import es.uniovi.ips.myshop.connectors.AddOrders;
import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.model.order.Order.Status;

public class Orders {
	
	public static void addPedido(Order order) throws SQLException {
		new AddOrders(order);
	}
	
	public static List<Order> getPedidosPorEstado(Status estado) {
		return new GetOrders().getOrdersByStatus(estado);
	}

}
