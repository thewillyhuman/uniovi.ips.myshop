package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;
import es.uniovi.ips.myshop.properties.Properties;

public class AddNewIncidence extends InBoardConnector {

	private String message;
	private WorkingPlan wk;

	public AddNewIncidence(WorkingPlan wk, String message) {
		this.wk = wk;
		this.message = message;
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
			super.getDatabase().executeUpdate(Properties.getString("myshop.sql.addIncidence"), message, wk.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new DisconnectDatabase(super.getDatabase());
	}
}
