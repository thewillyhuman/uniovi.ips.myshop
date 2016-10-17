package es.uniovi.ips.myshop.model.people;

/**
 * 
 * Person.java
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161125
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public abstract class Person {
	
	protected String ID, name, surname;
	
	public Person(String id, String name, String surname) {
		this.setId(id);
		this.setName(name);
		this.setSurname(surname);
	}
	
	public Person(String name, String surname) {
		this.setName(name);
		this.setSurname(surname);
	}
	
	/**
	 * Gets the name of the person.
	 * 
	 * @return the name of the person.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of the person.
	 * @param name of the person.
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname of the person.
	 * 
	 * @return the surname of the person.
	 */
	public String getSurname() {
		return this.surname;
	}
	
	/**
	 * Sets the surname of the person.
	 * 
	 * @param surname of the person.
	 */
	private void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * Gets the ID of the person.
	 * 
	 * @return the ID of the person.
	 */
	public String getId() {
		return this.ID;
	}
	
	/**
	 * Sets the ID for the person.
	 * 
	 * @param ID of the person
	 */
	private void setId(String ID) {
		this.ID = ID;
	}

	/**
	 * Gives a toString compulsory message.
	 */
	public abstract String toString();
}
