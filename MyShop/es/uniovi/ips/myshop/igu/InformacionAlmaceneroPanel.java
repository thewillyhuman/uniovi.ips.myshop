package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import es.uniovi.ips.myshop.model.people.Person;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;

import java.awt.Color;

public class InformacionAlmaceneroPanel extends JPanel{
	public InformacionAlmaceneroPanel(WharehouseKeeper p) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(null);
		getLbNombre().setText(p.getName());
		add(getLbNombre());
		getLbApellidos().setText(p.getSurname());
		add(getLbApellidos());
		add(getBtEsteSoyYo());
	}

	private static final long serialVersionUID = 1L;
	private JLabel lbNombre;
	private JLabel lbApellidos;
	private JButton btEsteSoyYo;
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel();
			lbNombre.setBounds(38, 36, 108, 14);
		}
		return lbNombre;
	}
	private JLabel getLbApellidos() {
		if (lbApellidos == null) {
			lbApellidos = new JLabel();
			lbApellidos.setBounds(170, 36, 108, 14);
		}
		return lbApellidos;
	}
	JButton getBtEsteSoyYo() {
		if (btEsteSoyYo == null) {
			btEsteSoyYo = new JButton("Este soy yo");
			btEsteSoyYo.setBounds(331, 32, 89, 23);
		}
		return btEsteSoyYo;
	}
}
