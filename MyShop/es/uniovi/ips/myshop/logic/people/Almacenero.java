package es.uniovi.ips.myshop.logic.people;

public class Almacenero extends Person {
		
	public Almacenero(String id, String name, String surname) {
		super(id, name, surname);
	}

	@Override
	public String toString() {
		return "> Almacenero >> " + name + ", " + surname + ". ID: " + id;
	}
}
