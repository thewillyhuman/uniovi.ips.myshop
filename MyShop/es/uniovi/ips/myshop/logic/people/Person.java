package es.uniovi.ips.myshop.logic.people;

public abstract class Person {
	
	protected String id, name;
	protected String surname;
	
	public Person(String id, String name, String surname) {
		this.setId(id);
		this.setName(name);
		this.setSurname(surname);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public abstract String toString();
}
