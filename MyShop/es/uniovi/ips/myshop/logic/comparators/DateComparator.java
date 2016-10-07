package es.uniovi.ips.myshop.logic.comparators;

import java.util.Comparator;

import es.uniovi.ips.myshop.logic.order.Pedido;

public class DateComparator implements Comparator<Pedido> {

	@Override
	public int compare(Pedido p1, Pedido p2) {
		return p1.getDate().compareTo(p2.getDate());
	}

}
