package es.uniovi.ips.myshop.igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.SubstanceLookAndFeel;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnClientes;
	private JButton btnAlmaceneros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.TwilightSkin");
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnClientes());
		contentPane.add(getBtnAlmaceneros());
	}
	private JButton getBtnClientes() {
		if (btnClientes == null) {
			btnClientes = new JButton("Clientes");
			btnClientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						mostrarVentanaProductosYCarrito();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			btnClientes.setBounds(90, 109, 98, 23);
		}
		return btnClientes;
	}
	
	private void mostrarVentanaProductosYCarrito() throws SQLException{
		VentanaProductosYCarrito vp = new VentanaProductosYCarrito();
		vp.setVisible(true);
		vp.setLocationRelativeTo(this);
		this.dispose();
	}
	private JButton getBtnAlmaceneros() {
		if (btnAlmaceneros == null) {
			btnAlmaceneros = new JButton("Almaceneros");
			btnAlmaceneros.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						mostrarVentanaAlmaceneros();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnAlmaceneros.setBounds(246, 109, 107, 23);
		}
		return btnAlmaceneros;
	}
	
	private void mostrarVentanaAlmaceneros() throws SQLException{
		VentanaAlmaceneros va = new VentanaAlmaceneros();
		va.setVisible(true);
		va.setLocationRelativeTo(this);
		this.dispose();
	}
}
