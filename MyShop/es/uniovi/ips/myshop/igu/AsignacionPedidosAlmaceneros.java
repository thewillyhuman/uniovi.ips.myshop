package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;

public class AsignacionPedidosAlmaceneros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnAsignacionProductos;
	private DefaultTableModel modeloTabla;
	private JScrollPane scpAsignacion;
	private JPanel pnDatosAlmacenero;
	private JLabel lblAlmacenero;
	private JTextField txAlmacenero;
	private JLabel lblId;
	private JTextField txID;
	private JPanel pnBotones;
	private JButton btAceptar;
	private JPanel pnAsignacion;
	private JPanel pnEleccionAlmacenero;

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
					AsignacionPedidosAlmaceneros frame = new AsignacionPedidosAlmaceneros();
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
	public AsignacionPedidosAlmaceneros() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnAsignacionProductos(), "name_14193795799254");
		contentPane.add(getPnEleccionAlmacenero(), "name_14207797347604");
		rellenarTabla();
	}
	private JPanel getPnAsignacionProductos() {
		if (pnAsignacionProductos == null) {
			pnAsignacionProductos = new JPanel();
			pnAsignacionProductos.setLayout(new BorderLayout(0, 0));
			pnAsignacionProductos.add(getScpAsignacion(), BorderLayout.CENTER);
			pnAsignacionProductos.add(getPnDatosAlmacenero(), BorderLayout.NORTH);
			pnAsignacionProductos.add(getPnBotones(), BorderLayout.SOUTH);
		}
		return pnAsignacionProductos;
	}
	private JScrollPane getScpAsignacion() {
		if (scpAsignacion == null) {
			scpAsignacion = new JScrollPane();
			scpAsignacion.setViewportView(getPnAsignacion());
		}
		return scpAsignacion;
	}
	private JPanel getPnDatosAlmacenero() {
		if (pnDatosAlmacenero == null) {
			pnDatosAlmacenero = new JPanel();
			pnDatosAlmacenero.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
			pnDatosAlmacenero.add(getLblAlmacenero());
			pnDatosAlmacenero.add(getTxAlmacenero());
			pnDatosAlmacenero.add(getLblId());
			pnDatosAlmacenero.add(getTxID());
		}
		return pnDatosAlmacenero;
	}
	private JLabel getLblAlmacenero() {
		if (lblAlmacenero == null) {
			lblAlmacenero = new JLabel("Almacenero: ");
		}
		return lblAlmacenero;
	}
	private JTextField getTxAlmacenero() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setEditable(false);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID:");
		}
		return lblId;
	}
	private JTextField getTxID() {
		if (txID == null) {
			txID = new JTextField();
			txID.setEditable(false);
			txID.setColumns(10);
		}
		return txID;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtAceptar());
		}
		return pnBotones;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
		}
		return btAceptar;
	}
	
	private void rellenarTabla() {
		// TODO Auto-generated method stub
	}
	private JPanel getPnAsignacion() {
		if (pnAsignacion == null) {
			pnAsignacion = new JPanel();
		}
		return pnAsignacion;
	}
	private JPanel getPnEleccionAlmacenero() {
		if (pnEleccionAlmacenero == null) {
			pnEleccionAlmacenero = new JPanel();
		}
		return pnEleccionAlmacenero;
	}
}
