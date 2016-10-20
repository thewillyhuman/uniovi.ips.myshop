package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.warehouse.incidences.Incidence;
import es.uniovi.ips.myshop.properties.Properties;

public class RemoveIncidence extends InBoardConnector {
	
	private Incidence in;
	
	public RemoveIncidence(Incidence incidence) {
		this.in = incidence;
		query();
	}

	@Override
	protected void query() {
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			super.getDatabase().executeUpdate(Properties.getString("myshop.sql.removeIncidence"), in.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new DisconnectDatabase(super.getDatabase());
	}

}
