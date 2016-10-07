package es.uniovi.ips.myshop.model.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.uniovi.ips.myshop.model.order.Order.Estado;

public class Orders {
	
	private static List<Order> pedidos = new ArrayList<Order>();
	
	public static void addPedido(Order pedido) {
		pedidos.add(pedido);
	}
	
	public static List<Order> getPedidosPorEstado(Estado estado) {
		List<Order> aux = new ArrayList<Order>();
		for(Order p : pedidos) {
			if(p.getEstado() == Estado.EN_PROCESO)
				aux.add(p);
		}
		Collections.sort(aux, Collections.reverseOrder());
		return aux;
	}

}
