package es.uniovi.ips.myshop.model.people;

/**
 * 
 * Customer.java
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161124
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Customer extends Person{
	
	private String dni;
	private Address address;

	public Customer(String id, String name, String surname, String dni, Address address) {
		super(id, name, surname);
		setDni(dni);
		setAddress(address);
	}
	
	public Customer(String id, String name, String surname, String dni, String street, String city, String state, String zipCode) {
		super(id, name, surname);
		setDni(dni);
		setAddress(new Address(street, city, state, zipCode));
	}
	
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	private void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	private void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "> Almacenero >> " + name + ", " + surname + ". ID: " + ID;
	}
}
