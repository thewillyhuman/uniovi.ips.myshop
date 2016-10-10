package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Producto;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ProductoListaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lblFotoProducto;
	private JLabel lblPrecio;
	private JLabel lblExistencias;
	private JTextField txPrecio;
	private JTextField txExistencias;
	private JScrollPane scpDescripcionProducto;
	private JTextArea txaDescripcionProducto;
	private JTextField txNombreProducto;
	private JButton btBorrar;
	private JButton btAñadir;
	private Producto producto;

	public ProductoListaPanel(Producto producto){
		this.producto = producto;
		setLayout(null);
		add(getLblFotoProducto());
		add(getLblPrecio());
		add(getLblExistencias());
		add(getTxPrecio());
		add(getTxExistencias());
		add(getScpDescripcionProducto());
		add(getTxNombreProducto());
		add(getBtBorrar());
		add(getBtAñadir());
	}
	private JLabel getLblFotoProducto() {
		if (lblFotoProducto == null) {
			lblFotoProducto = new JLabel("Foto producto");
			lblFotoProducto.setBounds(10, 0, 146, 125);
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
	private JScrollPane getScpDescripcionProducto() {
		if (scpDescripcionProducto == null) {
			scpDescripcionProducto = new JScrollPane();
			scpDescripcionProducto.setBounds(20, 136, 420, 66);
			scpDescripcionProducto.setViewportView(getTxaDescripcionProducto());
		}
		return scpDescripcionProducto;
	}
	private JTextArea getTxaDescripcionProducto() {
		if (txaDescripcionProducto == null) {
			txaDescripcionProducto = new JTextArea();
		}
		return txaDescripcionProducto;
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
			btBorrar.setBounds(351, 213, 89, 23);
		}
		return btBorrar;
	}
	JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir");
			btAñadir.setBounds(239, 213, 89, 23);
		}
		return btAñadir;
	}
}
