package es.uniovi.ips.myshop.model.people;

import java.util.List;

import es.uniovi.ips.myshop.connectors.GetWarehouseKeepers;

public class Employee {

	public List<WharehouseKeeper> getWharehouseKepers() {
		return new GetWarehouseKeepers().getAll();
	}
}
