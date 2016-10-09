package es.uniovi.ips.myshop.model.warehouse.incidences;

public class Incidence {

	private String id, descripcion;
	public boolean solved = false;

	public Incidence(String id, String descripcion) {
		this.setId(id);
		this.setDescripcion(descripcion);
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
