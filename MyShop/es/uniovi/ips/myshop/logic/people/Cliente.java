package es.uniovi.ips.myshop.logic.people;

public class Cliente extends Person{
	
	private String dni;
	private Address address;

	public Cliente(String id, String name, String surname, String dni, Address address) {
		super(id, name, surname);
		setDni(dni);
		setAddress(address);
	}
	
	public Cliente(String id, String name, String surname, String dni, String street, String city, String state, String zipCode) {
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
