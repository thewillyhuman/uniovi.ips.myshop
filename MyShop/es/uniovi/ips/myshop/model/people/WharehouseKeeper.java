package es.uniovi.ips.myshop.model.people;

import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;

public class WharehouseKeeper extends Person {
		
	public WharehouseKeeper(String id, String name, String surname) {
		super(id, name, surname);
	}
	
	public void asignarOT(WorkingPlan ot) {
		// do something...
	}

	@Override
	public String toString() {
		return "> Almacenero >> " + name + ", " + surname + ". ID: " + id;
	}
}
