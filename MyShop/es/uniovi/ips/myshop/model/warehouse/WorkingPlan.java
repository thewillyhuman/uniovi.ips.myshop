package es.uniovi.ips.myshop.model.warehouse;

import java.util.List;

import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.connectors.GetIncidence;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.model.product.Product;
import es.uniovi.ips.myshop.model.warehouse.incidences.Incidence;

/**
 * 
 * WorkingPlan.java
 *
 * @author Guillermo Facundo Colunga
 * @version 1010161155
 * @since 10 de oct. de 2016
 * @formatter Oviedo Computing Community
 */
public class WorkingPlan {
	
	private WharehouseKeeper almacenero;
	private Order order;
	private String id;
	private Incidence incidence;
	
	public WorkingPlan(Order order, WharehouseKeeper almacenero) {
		this.setAlmacenero(almacenero);
		this.order = order;
	}
	
	public WorkingPlan(String id, Order order, WharehouseKeeper almacenero) {
		this.id = id;
		this.setAlmacenero(almacenero);
		this.order = order;
	}
	
	public Incidence getIncidence() {
		return this.incidence;
	}
	
	public void setIncidence(Incidence inc) {
		this.incidence = inc;
	}
	
	public String getOrderID() {
		return this.order.getIdPedido();
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	/**
	 * Gets the current working plan sorted.
	 * 
	 * @return the working plan sorted.
	 */
	public List<Product> getWorkingPlanSorted() {
		return null;
	}
	
	/**
	 * Collects a single product.
	 * 
	 * @param ID of the product collected.
	 * @return true if the product has been collected. False otherwise.
	 */
	public boolean collectProduct(String ID) {
		for(OrderDetail dp : order.getProductos()) {
			if(dp.getProducto().getIDProducto()==ID) {
				dp.collected = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Mark the whole working plan for packaging.
	 * 
	 * @return true if possible. False otherwise.
	 */
	public boolean markForPackaging() {
		for(OrderDetail dp : order.getProductos()) {
			if(!dp.collected)
				return false;
			if(!dp.incidence.solved) {
				order.setStatus(Status.LISTO);
				return false;
			}
		}
		order.setStatus(Status.EMPAQUETANDO);
		return true;
	}
	
	/**
	 * Gets the shipping information.
	 * 
	 * @return the shipping information.
	 */
	public String getEtiquetaEnvio() {
		return order.printShippingInfo();
	}
	
	/**
	 * Gets the bill for the order.
	 * 
	 * @return the bill for the order.
	 */
	public String getAlbaranes() {
		return order.printBill();
	}

	/**
	 * Gives the warehouse keeper that has assigned this working plan.
	 * 
	 * @return the warehouse keeper assigned to this working plan.
	 */
	public WharehouseKeeper getAlmacenero() {
		return almacenero;
	}

	/**
	 * Sets the current warehouse keeper.
	 * 
	 * @param warehouseKepper assigned to this plan.
	 */
	public void setAlmacenero(WharehouseKeeper warehouseKepper) {
		this.almacenero = warehouseKepper;
	}

	public String getID() {
		return this.id;
	}

	public boolean hasIncidence() {
		return new GetIncidence().get(this)!=null;
	}

}
