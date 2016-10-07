package es.uniovi.ips.myshop.logic.comparators;

import java.util.Comparator;

import es.uniovi.ips.myshop.model.order.Order;

public class DateComparator implements Comparator<Order> {

	@Override
	public int compare(Order p1, Order p2) {
		return p1.getDate().compareTo(p2.getDate());
	}

}
