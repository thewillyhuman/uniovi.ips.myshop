package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;

import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class RecogidaPedidosPanel extends JPanel{
	public RecogidaPedidosPanel(Order o) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.LEFT);
		getLblPedidoId().setText(o.getIdPedido());
		int cantidad = 0;
		for(OrderDetail p : o.getProductos()){
			cantidad = cantidad + p.getCantidad();
		}
		getLblTamanio().setText(cantidad+"");
		getLblFecha().setText(o.getDate().toString());
		add(getLblPedidoId());
		add(getLblTamanio());
		add(getLblFecha());
		add(getBtnRecoger());
	}
	private static final long serialVersionUID = 1L;
	private JLabel lblPedidoId;
	private JButton btnRecoger;
	private JLabel lblTamanio;
	private JLabel lblFecha;

	private JLabel getLblPedidoId() {
		if (lblPedidoId == null) {
			lblPedidoId = new JLabel("Pedido ID");
		}
		return lblPedidoId;
	}
	JButton getBtnRecoger() {
		if (btnRecoger == null) {
			btnRecoger = new JButton("Recoger");
		}
		return btnRecoger;
	}
	private JLabel getLblTamanio() {
		if (lblTamanio == null) {
			lblTamanio = new JLabel("Dni cliente");
		}
		return lblTamanio;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
		}
		return lblFecha;
	}
}
