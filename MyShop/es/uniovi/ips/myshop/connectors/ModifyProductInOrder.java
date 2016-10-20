package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.properties.Properties;

public class ModifyProductInOrder extends InBoardConnector {
	
	private Order order;
	private OrderDetail od;
	private int collected;
	
	public ModifyProductInOrder(Order order, OrderDetail od, int collected) {
		this.order = order;
		this.od = od;
		this.collected=collected;
		query();
	}

	@Override
	protected void query() {
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			super.getDatabase().executeUpdate(Properties.getString("myShop.sql.updateProductInOrder"), collected, order.getIdPedido(), od.getProducto().getIDProducto());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DisconnectDatabase(super.getDatabase());

	}

}
