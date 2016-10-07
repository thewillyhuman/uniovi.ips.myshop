package es.uniovi.ips.myshop.model.people;

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
	public void setDni(String dni) {
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
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "> Almacenero >> " + name + ", " + surname + ". ID: " + id;
	}
}
