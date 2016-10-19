package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;

import es.uniovi.ips.myshop.model.order.Order;

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
		getLblDniCliente().setText(o.getCliente().getDni());
		getLblDireccionCliente().setText(o.getCliente().getAddress().toString());
		getLblFecha().setText(o.getDate().toString());
		add(getLblPedidoId());
		add(getLblDniCliente());
		add(getLblDireccionCliente());
		add(getLblFecha());
		add(getBtnRecoger());
	}
	private static final long serialVersionUID = 1L;
	private JLabel lblPedidoId;
	private JButton btnRecoger;
	private JLabel lblDniCliente;
	private JLabel lblDireccionCliente;
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
	private JLabel getLblDniCliente() {
		if (lblDniCliente == null) {
			lblDniCliente = new JLabel("Dni cliente");
		}
		return lblDniCliente;
	}
	private JLabel getLblDireccionCliente() {
		if (lblDireccionCliente == null) {
			lblDireccionCliente = new JLabel("Direccion cliente");
		}
		return lblDireccionCliente;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
		}
		return lblFecha;
	}
}
