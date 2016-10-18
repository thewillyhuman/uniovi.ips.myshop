package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;

import java.awt.Color;
import javax.swing.JButton;

public class InformacionProductoPedidoPanel extends JPanel{
	public InformacionProductoPedidoPanel(Order p) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(new FlowLayout(FlowLayout.LEFT, 30, 4));
		getLblIdPedido().setText(p.getIdPedido());
		getLblPDni().setText(p.getCliente().getDni());
		getLblDir().setText(p.getCliente().getAddress()+"");
		int cantidad = 0;
		for(OrderDetail c : p.getProductos()){
			cantidad = cantidad + c.getCantidad();
		}
		getLblCant().setText(cantidad + "");
		getLblFecha().setText(p.getDate()+"");
		add(getLblIdPedido());
		add(getLblPDni());
		add(getLblDir());
		add(getLblCant());
		add(getLblFecha());
		add(getBtnEmpaquetar());
	}
	
	private static final long serialVersionUID = 1L;
	private JLabel lblIdPedido;
	private JLabel lblPDni;
	private JLabel lblDir;
	private JLabel lblCant;
	private JButton btnEmpaquetar;
	private JLabel lblFecha;
	private JLabel getLblIdPedido() {
		if (lblIdPedido == null) {
			lblIdPedido = new JLabel("Pedido ID");
		}
		return lblIdPedido;
	}
	private JLabel getLblPDni() {
		if (lblPDni == null) {
			lblPDni = new JLabel("dni");
		}
		return lblPDni;
	}
	private JLabel getLblDir() {
		if (lblDir == null) {
			lblDir = new JLabel("Direccion");
		}
		return lblDir;
	}
	private JLabel getLblCant() {
		if (lblCant == null) {
			lblCant = new JLabel("Cantidad");
		}
		return lblCant;
	}
	JButton getBtnEmpaquetar() {
		if (btnEmpaquetar == null) {
			btnEmpaquetar = new JButton("Empaquetar");
		}
		return btnEmpaquetar;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
		}
		return lblFecha;
	}
}
