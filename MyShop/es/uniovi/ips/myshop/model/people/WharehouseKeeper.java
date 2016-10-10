package es.uniovi.ips.myshop.model.people;

import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;

/**
 * 
 * WharehouseKeeper.java
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161137
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class WharehouseKeeper extends Person {
		
	public WharehouseKeeper(String id, String name, String surname) {
		super(id, name, surname);
	}
	
	/**
	 * Assign 
	 * @param workingPlan is the working plan the warehouse keeper autoasigns.
	 */
	public void assignOT(WorkingPlan workingPlan) {
		// do something...
	}

	@Override
	public String toString() {
		return "> Almacenero >> " + name + ", " + surname + ". ID: " + ID;
	}
}
