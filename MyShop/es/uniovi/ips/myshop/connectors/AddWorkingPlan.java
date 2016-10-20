package es.uniovi.ips.myshop.connectors;

import java.sql.SQLException;

import es.uniovi.ips.myshop.database.InBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.database.client.DisconnectDatabase;
import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;
import es.uniovi.ips.myshop.properties.Properties;

/**
 * 
 * AddWorkingPlan.java Adds a workingPlan to the database.
 *
 * @author Carlos Lopez de Juan
 * @version 0810161300
 * @since 19 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class AddWorkingPlan extends InBoardConnector {

	private WorkingPlan workingPlan;
	
	/**
	 * Will add the given workingPlan on to the database.
	 * 
	 * @param workingPlan to be added to the database.
	 * @throws SQLException if there is any error while connecting to the
	 *             database or executing the query.
	 */
	public AddWorkingPlan(WorkingPlan workingPlan){
		this.workingPlan = workingPlan;
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
					"myshop.sql.insertWorkingPlan"),workingPlan.getAlmacenero().getId()
					,workingPlan.getOrder().getIdPedido());
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}		
		new DisconnectDatabase(super.getDatabase());
	}
	
	

}
