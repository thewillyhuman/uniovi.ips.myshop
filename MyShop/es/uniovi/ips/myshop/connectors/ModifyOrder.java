package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.properties.Properties;

public class ModifyOrder extends InBoardConnector{

	private Order order;
	private Status newStatus;
	
	public ModifyOrder(Order order, Status newStatus) throws SQLException {
		this.order=order;
		this.newStatus=newStatus;
		this.query();
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
			super.getDatabase().executeUpdate(Properties.getString("myShop.sql.updateOrderStatus"), String.valueOf(newStatus), order.getIdPedido());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DisconnectDatabase(super.getDatabase());
		
	}

}
