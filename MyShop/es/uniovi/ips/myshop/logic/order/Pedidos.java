package es.uniovi.ips.myshop.logic.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.uniovi.ips.myshop.logic.order.Pedido.Estado;

public class Pedidos {
	
	private static List<Pedido> pedidos = new ArrayList<Pedido>();
	
	public static void addPedido(Pedido pedido) {
		pedidos.add(pedido);
	}
	
	public static List<Pedido> getPedidosPorEstado(Estado estado) {
		List<Pedido> aux = new ArrayList<Pedido>();
		for(Pedido p : pedidos) {
			if(p.getEstado() == Estado.EN_PROCESO)
				aux.add(p);
		}
		Collections.sort(aux, Collections.reverseOrder());
		return aux;
	}

}
