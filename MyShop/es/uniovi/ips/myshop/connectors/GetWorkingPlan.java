package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.OutBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;
import es.uniovi.ips.myshop.properties.Properties;

/**
 * 
 * GetWorkingPlan.java Helps to retrieve WorkingPlans from the database.
 *
 * @author Carlos Lopez de Juan
 * @version 0810161300
 * @since 19 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class GetWorkingPlan extends Connector implements OutBoardConnector {

	private WharehouseKeeper almacenero;
	
	/**
	 * Finds a workingPlan by WharehouseKeeper ID.
	 * 
	 * @param WharehouseKeeper
	 *            is the reference of the WharehouseKeeper to search in the database.
	 * @return true if there is one workingPlan with that WharehouseKeeper. null otherwise.
	 * @throws SQLException
	 */
	public boolean GetAnyWorkingPlan(WharehouseKeeper almacenero) throws SQLException{
		this.almacenero = almacenero;
		new ConnectDatabase(super.getDatabase());
		ResultSet rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getWorkingPlanByIDAlmacenero"), almacenero.getId());
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	/**
	 * Finds the id of a workingPplan by WorkingPlan.
	 * 
	 * @param WorkingPlan
	 *            is the reference of the WorkingPlan to search in the database.
	 * @return the id of the workingplan. null otherwise.
	 * @throws SQLException
	 */
	public String getIdWorkingPlan(WorkingPlan wp) throws SQLException{
		String id = "";
		new ConnectDatabase(super.getDatabase());
		ResultSet rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getIdWorkingPlan"),wp.getAlmacenero().getId(),wp.getOrder().getIdPedido());
		if(rs.next()){
			id = rs.getString(1);
		}
		return id;
	}
	
	/**
	 * Finds a workingPlan by WharehouseKeeper ID.
	 * 
	 * @param WharehouseKeeper
	 *            is the reference of the WharehouseKeeper to search in the database.
	 * @return the workingplan. null otherwise.
	 * @throws SQLException
	 */
	public WorkingPlan GetWorkingPlan(WharehouseKeeper almacenero) throws SQLException{
		this.almacenero = almacenero;
		WorkingPlan wp = null;
		new ConnectDatabase(super.getDatabase());
		ResultSet rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getWorkingPlanByIDAlmacenero"), almacenero.getId());
		if(rs.next()){
			wp = new WorkingPlan(new GetOrders().getOrder(rs.getString(1)),new GetWarehouseKeepers().getWarehouseKeeperById(rs.getString(2)));
		}
		return wp;

	}
	
	
	
	
}
