package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.properties.Properties;

public class AddWorkingPlan extends InBoardConnector {
	
	Order wk;
	WharehouseKeeper wkep;
	
	public AddWorkingPlan(WharehouseKeeper wkep, Order order) {
		this.wk=order;
		this.wkep=wkep;
		query();
	}

	@Override
	protected void query() {
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			super.getDatabase().executeUpdate(Properties.getString("myshop.sql.addWorkingPlan"), wkep.getId(), wk.getIdPedido());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DisconnectDatabase(super.getDatabase());
	}

}
