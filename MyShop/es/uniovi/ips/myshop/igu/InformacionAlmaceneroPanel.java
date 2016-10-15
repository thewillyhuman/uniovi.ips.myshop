package es.uniovi.ips.myshop.igu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class InformacionAlmaceneroPanel extends JPanel{
	public InformacionAlmaceneroPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(null);
		add(getLbNombre());
		add(getLblFoto());
		add(getLbApellidos());
		add(getBtEsteSoyYo());
	}

	private static final long serialVersionUID = 1L;
	private JLabel lbNombre;
	private JLabel lblFoto;
	private JLabel lbApellidos;
	private JButton btEsteSoyYo;
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre");
			lbNombre.setBounds(180, 33, 108, 14);
		}
		return lbNombre;
	}
	private JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("Foto");
			lblFoto.setBounds(44, 16, 94, 92);
		}
		return lblFoto;
	}
	private JLabel getLbApellidos() {
		if (lbApellidos == null) {
			lbApellidos = new JLabel("Apellidos");
			lbApellidos.setBounds(180, 77, 108, 14);
		}
		return lbApellidos;
	}
	JButton getBtEsteSoyYo() {
		if (btEsteSoyYo == null) {
			btEsteSoyYo = new JButton("Este soy yo");
			btEsteSoyYo.setBounds(290, 51, 89, 23);
		}
		return btEsteSoyYo;
	}
}
