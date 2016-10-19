package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.uniovi.ips.myshop.model.product.Product;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;

public class ProductoListaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lblPrecio;
	private JTextField txPrecio;
	private JTextField txNombreProducto;
	private JButton btBorrar;
	private JButton btAniadir;

	public ProductoListaPanel(Product p){
		setBorder(new LineBorder(new Color(0, 0, 0)));
		getTxPrecio().setText(p.getPrecio()+"");
		getTxNombreProducto().setText(p.getDescripcion());
		setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));
		add(getTxNombreProducto());
		add(getLblPrecio());
		add(getTxPrecio());
		add(getBtAniadir());
		add(getBtBorrar());
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio: ");
		}
		return lblPrecio;
	}
	private JTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JTextField();
			txPrecio.setEditable(false);
			txPrecio.setColumns(10);
		}
		return txPrecio;
	}
	private JTextField getTxNombreProducto() {
		if (txNombreProducto == null) {
			txNombreProducto = new JTextField();
			txNombreProducto.setEditable(false);
			txNombreProducto.setColumns(10);
		}
		return txNombreProducto;
	}
	JButton getBtBorrar() {
		if (btBorrar == null) {
			btBorrar = new JButton("Borrar");
		}
		return btBorrar;
	}
	JButton getBtAniadir() {
		if (btAniadir == null) {
			btAniadir = new JButton("A\u00F1adir");
		}
		return btAniadir;
	}

}
