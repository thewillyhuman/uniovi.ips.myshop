package es.uniovi.ips.myshop.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.ips.myshop.database.Connector;
import es.uniovi.ips.myshop.database.OutBoardConnector;
import es.uniovi.ips.myshop.database.client.ConnectDatabase;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;
import es.uniovi.ips.myshop.model.warehouse.incidences.Incidence;
import es.uniovi.ips.myshop.properties.Properties;

public class GetWorkingPlan extends Connector implements OutBoardConnector {


	public WorkingPlan get(WharehouseKeeper wk) {
		WorkingPlan aux = null;
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getWorkingPlanByWareHouseKeeper"),
					wk.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null && rs.next()) {
				aux = new WorkingPlan(new GetOrders().getOrder(rs.getString(1)), new GetWarehouseKeepers().getByID(rs.getInt(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aux;
	}
	
	public WorkingPlan getByOrder(Order order) {
		WorkingPlan aux = null;
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getWPByOrder"), order.getIdPedido());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs!=null && rs.next()) {
				try {
					aux = new WorkingPlan(rs.getString(1),order, new GetWarehouseKeepers().getByID(rs.getInt(2)));
					aux.setIncidence(new GetIncidence().get(aux));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}
	
	public List<WorkingPlan> getAllWP() {
		List<WorkingPlan> aux = new ArrayList<WorkingPlan>();
		try {
			new ConnectDatabase(super.getDatabase());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = super.getDatabase().executeSQL(Properties.getString("myshop.sql.getAllWP"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				aux.add(new WorkingPlan(rs.getString(1),new GetOrders().getOrder(rs.getString(3)), new GetWarehouseKeepers().getByID(rs.getInt(2))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}
	
	public boolean isOccupied(WharehouseKeeper wk) {
		for(WorkingPlan wp : getAllWP()) {
			System.out.println("WP "+wp.getID()+" id almacenero: " + wp.getAlmacenero().getId() + " Estado: " + wp.getOrder().getEstado());
			if(wp.getAlmacenero().getId().equals(wk.getId()) && wp.getOrder().getEstado().equals(Status.PENDIENTE))
					return true;
		} return false;
	}
	
	public Order getCurrent(WharehouseKeeper wk) {
		for(WorkingPlan wp : getAllWP()) {
			System.out.println("WP "+wp.getID()+" id almacenero: " + wp.getAlmacenero().getId() + " Estado: " + wp.getOrder().getEstado());
			if(wp.getAlmacenero().getId().equals(wk.getId()) && wp.getOrder().getEstado().equals(Status.PENDIENTE))
					return wp.getOrder();
		} return null;
	}
}
