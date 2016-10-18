package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;

import java.awt.Color;

public class PedidoParaAsignarPanel extends JPanel{
	public PedidoParaAsignarPanel(Order o) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(new FlowLayout(FlowLayout.LEFT, 70, 12));
		getLblIdPedido().setText(o.getIdPedido());
		int cantidad = 0;
		for(OrderDetail p : o.getProductos()){
			cantidad = cantidad + p.getCantidad();
		}
		getLblCantidad().setText(cantidad+ "");
		getLblFecha().setText(o.getDate() + "");;
		add(getLblIdPedido());
		add(getLblCantidad());
		add(getLblFecha());
	}

	private static final long serialVersionUID = 1L;
	private JLabel lblIdPedido;
	private JLabel lblCantidad;
	private JLabel lblFecha;

	JLabel getLblIdPedido() {
		if (lblIdPedido == null) {
			lblIdPedido = new JLabel("ID Pedido");
		}
		return lblIdPedido;
	}
	JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad");
		}
		return lblCantidad;
	}
	JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
		}
		return lblFecha;
	}
}
