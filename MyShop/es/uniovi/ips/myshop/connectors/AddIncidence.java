package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.warehouse.incidences.Incidence;
import es.uniovi.ips.myshop.properties.Properties;

public class AddIncidence  extends InBoardConnector {
	private Incidence incidencia;
	
	public AddIncidence(Incidence incidencia){
		this.incidencia = incidencia;
		query();
	}
	
	@Override
	protected void query() {
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			super.getDatabase().executeUpdate(Properties.getString(
					"myshop.sql.insertIncidence"),incidencia.getDescription(),incidencia.getId());
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}		
		new DisconnectDatabase(super.getDatabase());
	}

}
