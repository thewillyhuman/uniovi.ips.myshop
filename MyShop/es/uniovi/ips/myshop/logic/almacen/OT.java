package es.uniovi.ips.myshop.logic.almacen;

import java.util.List;

import es.uniovi.ips.myshop.logic.order.DetallePedido;
import es.uniovi.ips.myshop.logic.order.Pedido;
import es.uniovi.ips.myshop.logic.order.Pedido.Estado;
import es.uniovi.ips.myshop.logic.people.Almacenero;
import es.uniovi.ips.myshop.logic.producto.Producto;

public class OT {
	
	Almacenero almacenero;
	Pedido pedido;
	
	public OT(Pedido pedido, Almacenero almacenero) {
		this.almacenero = almacenero;
		this.pedido = pedido;
	}
	
	public List<Producto> getOTShorted() {
		return null;
	}
	
	public boolean recogerProducto(String id) {
		for(DetallePedido dp : pedido.getProductos()) {
			if(dp.getProducto().getIDProducto()==id) {
				dp.recogido = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean marcarParaEmpaquetado() {
		for(DetallePedido dp : pedido.getProductos()) {
			if(dp.recogido==false)
				return false;
			if(dp.incidencia.solved==false) {
				pedido.setEstado(Estado.INCIDENCIA);
				return false;
			}
		}
		pedido.setEstado(Estado.EMPAQUETANDO);
		return true;
	}
	
	public String getEtiquetaEnvio() {
		return null;
	}
	
	public String getAlbaranes() {
		return null;
	}

}
