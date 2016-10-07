package es.uniovi.ips.myshop.logic.people;

import es.uniovi.ips.myshop.logic.almacen.OT;

public class Almacenero extends Person {
		
	public Almacenero(String id, String name, String surname) {
		super(id, name, surname);
	}
	
	public void asignarOT(OT ot) {
		
	}

	@Override
	public String toString() {
		return "> Almacenero >> " + name + ", " + surname + ". ID: " + id;
	}
}
