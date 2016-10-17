package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class RecogidaPedidosPanel extends JPanel{
	public RecogidaPedidosPanel() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(getLblPedidoId());
		add(getLblPasillo());
		add(getLblLado());
		add(getLblPosicion());
		add(getLblAltura());
		add(getBtnRecoger());
	}
	private static final long serialVersionUID = 1L;
	private JLabel lblPedidoId;
	private JLabel lblPasillo;
	private JLabel lblLado;
	private JLabel lblPosicion;
	private JLabel lblAltura;
	private JButton btnRecoger;

	private JLabel getLblPedidoId() {
		if (lblPedidoId == null) {
			lblPedidoId = new JLabel("Pedido ID");
		}
		return lblPedidoId;
	}
	private JLabel getLblPasillo() {
		if (lblPasillo == null) {
			lblPasillo = new JLabel("Pasillo");
		}
		return lblPasillo;
	}
	private JLabel getLblLado() {
		if (lblLado == null) {
			lblLado = new JLabel("Lado");
		}
		return lblLado;
	}
	private JLabel getLblPosicion() {
		if (lblPosicion == null) {
			lblPosicion = new JLabel("Posicion");
		}
		return lblPosicion;
	}
	private JLabel getLblAltura() {
		if (lblAltura == null) {
			lblAltura = new JLabel("Altura");
		}
		return lblAltura;
	}
	JButton getBtnRecoger() {
		if (btnRecoger == null) {
			btnRecoger = new JButton("Recoger");
		}
		return btnRecoger;
	}
}
