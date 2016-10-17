package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import es.uniovi.ips.myshop.model.product.Product;

import java.awt.Color;

public class ProductoEnCarritoPanel extends JPanel{
	private Product p;
	public ProductoEnCarritoPanel(Product p) {
		this.p = p;
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT, 75, 7);
		setLayout(flowLayout);
		add(getLbProducto());
		add(getLbPrecio());
		add(getSpCantidad());
		add(getLbTotal());
	}

	private static final long serialVersionUID = 1L;
	private JLabel lbProducto;
	private JLabel lbPrecio;
	private JSpinner spCantidad;
	private JLabel lbTotal;

	private JLabel getLbProducto() {
		if (lbProducto == null) {
			lbProducto = new JLabel(p.getDescripcion());
		}
		return lbProducto;
	}
	JLabel getLbPrecio() {
		if (lbPrecio == null) {
			lbPrecio = new JLabel(p.getPrecio()+"");
		}
		return lbPrecio;
	}
	JSpinner getSpCantidad() {
		if (spCantidad == null) {
			spCantidad = new JSpinner();
			spCantidad.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spCantidad.setValue(1);
		}
		return spCantidad;
	}
	JLabel getLbTotal() {
		if (lbTotal == null) {
			lbTotal = new JLabel(p.getPrecio()+"");
		}
		return lbTotal;
	}
}
