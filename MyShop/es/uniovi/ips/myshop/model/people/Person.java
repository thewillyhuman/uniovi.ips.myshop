package es.uniovi.ips.myshop.model.people;

public abstract class Person {
	
	protected String id, name, surname;
	
	public Person(String id, String name, String surname) {
		this.setId(id);
		this.setName(name);
		this.setSurname(surname);
	}
	
	public String getName() {
		return this.name;
	}
	
	private void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}
	
	private void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getId() {
		return this.id;
	}
	
	private void setId(String id) {
		this.id = id;
	}

	public abstract String toString();
}
