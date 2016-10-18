package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.uniovi.ips.myshop.model.product.Product;

import javax.swing.JButton;

public class ProductoListaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lblFotoProducto;
	private JLabel lblPrecio;
	private JLabel lblExistencias;
	private JTextField txPrecio;
	private JTextField txExistencias;
	private JTextField txNombreProducto;
	private JButton btBorrar;
	private JButton btAniadir;

	public ProductoListaPanel(Product p){
		setLayout(null);
		add(getLblFotoProducto());
		add(getLblPrecio());
		add(getLblExistencias());
		add(getTxPrecio());
		add(getTxExistencias());
		add(getTxNombreProducto());
		add(getBtBorrar());
		add(getBtAniadir());
	}
	private JLabel getLblFotoProducto() {
		if (lblFotoProducto == null) {
			lblFotoProducto = new JLabel("Foto producto");
			lblFotoProducto.setBounds(21, 39, 146, 125);
		}
		return lblFotoProducto;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio: ");
			lblPrecio.setBounds(220, 55, 52, 14);
		}
		return lblPrecio;
	}
	private JLabel getLblExistencias() {
		if (lblExistencias == null) {
			lblExistencias = new JLabel("Existencias: ");
			lblExistencias.setBounds(220, 94, 68, 14);
		}
		return lblExistencias;
	}
	private JTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JTextField();
			txPrecio.setEditable(false);
			txPrecio.setBounds(297, 52, 80, 20);
			txPrecio.setColumns(10);
		}
		return txPrecio;
	}
	private JTextField getTxExistencias() {
		if (txExistencias == null) {
			txExistencias = new JTextField();
			txExistencias.setEditable(false);
			txExistencias.setBounds(298, 91, 80, 20);
			txExistencias.setColumns(10);
		}
		return txExistencias;
	}
	private JTextField getTxNombreProducto() {
		if (txNombreProducto == null) {
			txNombreProducto = new JTextField();
			txNombreProducto.setEditable(false);
			txNombreProducto.setBounds(188, 11, 140, 20);
			txNombreProducto.setColumns(10);
		}
		return txNombreProducto;
	}
	JButton getBtBorrar() {
		if (btBorrar == null) {
			btBorrar = new JButton("Borrar");
			btBorrar.setBounds(311, 157, 89, 23);
		}
		return btBorrar;
	}
	JButton getBtAniadir() {
		if (btAniadir == null) {
			btAniadir = new JButton("A\u00F1adir");
			btAniadir.setBounds(199, 157, 89, 23);
		}
		return btAniadir;
	}

}
