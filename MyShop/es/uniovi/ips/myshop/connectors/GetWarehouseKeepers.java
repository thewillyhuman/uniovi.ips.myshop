package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.OutBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.properties.Properties;

public class GetWarehouseKeepers extends Connector implements OutBoardConnector {

	public List<WharehouseKeeper> getAll() {
		List<WharehouseKeeper> aux = new ArrayList<WharehouseKeeper>();
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet rs = super.getDatabase().executeSQL(Properties.getString("myShop.sql.getAllWarehouseKeeper"));
			while(rs.next()) {
				aux.add(new WharehouseKeeper(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DisconnectDatabase(super.getDatabase());
		
		return aux;
	}
	

	public WharehouseKeeper getByID(int parseInt) {
		return null;
	}
	
	public boolean isOccupies(WharehouseKeeper wk) {
		return false;
	}

	public Order getCurrentOrder() {
		// TODO Auto-generated method stub
		return null;
	}
}
