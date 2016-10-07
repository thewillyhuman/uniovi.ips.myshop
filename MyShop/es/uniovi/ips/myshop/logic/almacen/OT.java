package es.uniovi.ips.myshop.logic.almacen;

import java.util.List;

import es.uniovi.ips.myshop.logic.producto.Producto;

public class OT {
	
	public enum Estado {
		EN_PROCESO, ASIGNADO, INCIDENCIA, COMPLETADO
	}
	
	public List<Producto> getOTShorted() {
		return null;
	}
	
	public boolean recogerProducto(String id) {
		return false;
	}
	
	public boolean marcarParaEmpaquetado() {
		return false;
	}
	
	public String getEtiquetaEnvio() {
		return null;
	}
	
	public String getAlbaranes() {
		return null;
	}

}
