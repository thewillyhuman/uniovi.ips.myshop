package es.uniovi.ips.myshop.logic.almacen.incidencias;

public class Incidencia {

	private String id, descripcion;
	public boolean solved = false;

	public Incidencia(String id, String descripcion) {
		this.setId(id);
		this.setDescripcion(descripcion);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
