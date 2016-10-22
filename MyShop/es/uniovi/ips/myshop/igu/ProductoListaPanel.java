package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.uniovi.ips.myshop.model.product.Product;

import javax.swing.JButton;

public class ProductoListaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lblPrecio;
	private JTextField txPrecio;
	private JTextField txNombreProducto;
	private JButton btBorrar;
	private JButton btAniadir;

	public ProductoListaPanel(Product p){
		setLayout(null);
		add(getLblPrecio());
		add(getTxPrecio());
		add(getTxNombreProducto());
		add(getBtBorrar());
		add(getBtAniadir());
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio: ");
			lblPrecio.setBounds(211, 35, 52, 14);
		}
		return lblPrecio;
	}
	private JTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JTextField();
			txPrecio.setEditable(false);
			txPrecio.setBounds(296, 32, 80, 20);
			txPrecio.setColumns(10);
		}
		return txPrecio;
	}
	private JTextField getTxNombreProducto() {
		if (txNombreProducto == null) {
			txNombreProducto = new JTextField();
			txNombreProducto.setEditable(false);
			txNombreProducto.setBounds(27, 32, 140, 20);
			txNombreProducto.setColumns(10);
		}
		return txNombreProducto;
	}
	JButton getBtBorrar() {
		if (btBorrar == null) {
			btBorrar = new JButton("Borrar");
			btBorrar.setBounds(310, 83, 89, 23);
		}
		return btBorrar;
	}
	JButton getBtAniadir() {
		if (btAniadir == null) {
			btAniadir = new JButton("A\u00F1adir");
			btAniadir.setBounds(211, 83, 89, 23);
		}
		return btAniadir;
	}

}
