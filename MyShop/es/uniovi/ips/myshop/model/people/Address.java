package es.uniovi.ips.myshop.model.people;

/**
 * 
 * Address.java
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161123
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Address {
	
	private String street, city, state, zipCode;
	
	public Address(String street, String city, String state, String zipCode) {
		setStreet(street);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	private void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	private void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	private void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	private void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	

}
