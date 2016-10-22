package es.uniovi.ips.myshop.model.warehouse.incidences;

/**
 * 
 * Incidence.java
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161149
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class Incidence {

	private String ID, description;
	public boolean solved = false;

	public Incidence(String id, String descripcion) {
		this.setId(id);
		this.setDescripcion(descripcion);
	}

	/**
	 * Gets the ID of the incidence.
	 * 
	 * @return the ID of the incidence.
	 */
	public String getId() {
		return ID;
	}

	/**
	 * Sets the ID for a given incidence.
	 * 
	 * @param ID of the incidence.
	 */
	private void setId(String ID) {
		this.ID = ID;
	}

	/**
	 * Gets the description of the Incidence.
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the incidence.
	 * @param description of the incidence.
	 */
	private void setDescripcion(String description) {
		this.description = description;
	}
}
