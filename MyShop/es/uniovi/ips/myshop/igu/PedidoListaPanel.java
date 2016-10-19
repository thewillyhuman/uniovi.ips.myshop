package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.warehouse.ProductLocation;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PedidoListaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lblProducto;
	private JTextField txProducto;
	private JLabel lblPasillo;
	private JTextField txPasillo;
	private JLabel lblLado;
	private JTextField txLado;
	private JLabel lblPosicion;
	private JTextField txPosicion;
	private JLabel lblAltura;
	private JTextField txAltura;
	private JButton btnRecoger;
	private JLabel lblCantidad;
	private JTextField txCantidad;
	private JTextField txIdProducto;

	public PedidoListaPanel(OrderDetail p){
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(null);
		getTxProducto().setText(p.getProducto().getDescripcion());
		ProductLocation l = p.getProducto().getLocalizacion();
		getTxPasillo().setText(l.getPasillo()+ "");
		getTxLado().setText(l.getLado().toString());
		getTxPosicion().setText(l.getPosicion()+"");
		getTxAltura().setText(l.getAltura()+"");
		getTxCantidad().setText(p.getCantidad()+"");
		getTxIdProducto().setText(p.getProducto().getIDProducto());
		add(getLblProducto());
		add(getTxProducto());
		add(getLblPasillo());
		add(getTxPasillo());
		add(getLblLado());
		add(getTxLado());
		add(getLblPosicion());
		add(getTxPosicion());
		add(getLblAltura());
		add(getTxAltura());
		add(getBtnRecoger());
		add(getLblCantidad());
		add(getTxCantidad());
		add(getTxIdProducto());
	}
	private JLabel getLblProducto() {
		if (lblProducto == null) {
			lblProducto = new JLabel("Producto:");
			lblProducto.setBounds(23, 11, 65, 14);
		}
		return lblProducto;
	}
	private JTextField getTxProducto() {
		if (txProducto == null) {
			txProducto = new JTextField();
			txProducto.setEditable(false);
			txProducto.setBounds(113, 8, 318, 20);
			txProducto.setColumns(10);
		}
		return txProducto;
	}
	private JLabel getLblPasillo() {
		if (lblPasillo == null) {
			lblPasillo = new JLabel("Pasillo: ");
			lblPasillo.setBounds(23, 42, 46, 14);
		}
		return lblPasillo;
	}
	private JTextField getTxPasillo() {
		if (txPasillo == null) {
			txPasillo = new JTextField();
			txPasillo.setEditable(false);
			txPasillo.setBounds(113, 39, 86, 20);
			txPasillo.setColumns(10);
		}
		return txPasillo;
	}
	private JLabel getLblLado() {
		if (lblLado == null) {
			lblLado = new JLabel("Lado: ");
			lblLado.setBounds(23, 73, 46, 14);
		}
		return lblLado;
	}
	private JTextField getTxLado() {
		if (txLado == null) {
			txLado = new JTextField();
			txLado.setEditable(false);
			txLado.setBounds(113, 70, 86, 20);
			txLado.setColumns(10);
		}
		return txLado;
	}
	private JLabel getLblPosicion() {
		if (lblPosicion == null) {
			lblPosicion = new JLabel("Posicion: ");
			lblPosicion.setBounds(251, 42, 46, 14);
		}
		return lblPosicion;
	}
	private JTextField getTxPosicion() {
		if (txPosicion == null) {
			txPosicion = new JTextField();
			txPosicion.setEditable(false);
			txPosicion.setBounds(326, 39, 86, 20);
			txPosicion.setColumns(10);
		}
		return txPosicion;
	}
	private JLabel getLblAltura() {
		if (lblAltura == null) {
			lblAltura = new JLabel("Altura: ");
			lblAltura.setBounds(251, 73, 46, 14);
		}
		return lblAltura;
	}
	private JTextField getTxAltura() {
		if (txAltura == null) {
			txAltura = new JTextField();
			txAltura.setEditable(false);
			txAltura.setBounds(326, 70, 86, 20);
			txAltura.setColumns(10);
		}
		return txAltura;
	}
	JButton getBtnRecoger() {
		if (btnRecoger == null) {
			btnRecoger = new JButton("Recoger");
			btnRecoger.setBounds(323, 101, 89, 23);
		}
		return btnRecoger;
	}
	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad: ");
			lblCantidad.setBounds(23, 105, 65, 14);
		}
		return lblCantidad;
	}
	private JTextField getTxCantidad() {
		if (txCantidad == null) {
			txCantidad = new JTextField();
			txCantidad.setEditable(false);
			txCantidad.setBounds(113, 101, 86, 20);
			txCantidad.setColumns(10);
		}
		return txCantidad;
	}
	private JTextField getTxIdProducto() {
		if (txIdProducto == null) {
			txIdProducto = new JTextField();
			txIdProducto.setEditable(false);
			txIdProducto.setBounds(227, 102, 86, 20);
			txIdProducto.setColumns(10);
		}
		return txIdProducto;
	}
}
