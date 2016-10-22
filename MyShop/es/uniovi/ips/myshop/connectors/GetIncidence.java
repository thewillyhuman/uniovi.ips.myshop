package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.OutBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;
import es.uniovi.ips.myshop.model.warehouse.incidences.Incidence;
import es.uniovi.ips.myshop.properties.Properties;

public class GetIncidence extends Connector implements OutBoardConnector {
	
	public Incidence get(Order order) {
		Incidence aux = null;
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			super.getDatabase().executeSQL(Properties.getString("myshop.sql.getIncidenceByOrder"), order.getIdPedido());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}
	
	public Incidence get(WorkingPlan wk) {
		Incidence aux = null;
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getIncidenceByWK"), wk.getID());
			if(rs.next()) {
				aux = new Incidence(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}

}
