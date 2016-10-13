package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import org.jvnet.substance.SubstanceLookAndFeel;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class EmpaquetadoRecogidaPedidos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tbpnEmpaquetadoRecogida;
	private JPanel pnEmpaquetado;
	private JPanel pnRecogida;
	private JScrollPane scpEmpaquetado;
	private JTable tbEmpaquetado;
	private JPanel pnBotonesEmpaquetado;
	private JButton btEmpaquetar;
	private JButton btImprimirFactura;
	private DefaultTableModel modeloTabla1;
	private DefaultTableModel modeloTabla2;
	private JScrollPane scpRecogida;
	private JTable tbRecogida;
	private JPanel pnBotonesRecogida;
	private JButton btAceptar;

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
					EmpaquetadoRecogidaPedidos frame = new EmpaquetadoRecogidaPedidos();
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
	public EmpaquetadoRecogidaPedidos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTbpnEmpaquetadoRecogida(), BorderLayout.CENTER);
		rellenarTablaEmpaquetado();
		rellenarTablaRecogida();
	}

	private JTabbedPane getTbpnEmpaquetadoRecogida() {
		if (tbpnEmpaquetadoRecogida == null) {
			tbpnEmpaquetadoRecogida = new JTabbedPane(JTabbedPane.TOP);
			tbpnEmpaquetadoRecogida.addTab("Empaquetado", null, getPnEmpaquetado(), null);
			tbpnEmpaquetadoRecogida.addTab("Recogida", null, getPnRecogida(), null);
		}
		return tbpnEmpaquetadoRecogida;
	}
	private JPanel getPnEmpaquetado() {
		if (pnEmpaquetado == null) {
			pnEmpaquetado = new JPanel();
			pnEmpaquetado.setLayout(new BorderLayout(0, 0));
			pnEmpaquetado.add(getScpEmpaquetado(), BorderLayout.CENTER);
			pnEmpaquetado.add(getPnBotonesEmpaquetado(), BorderLayout.SOUTH);
		}
		return pnEmpaquetado;
	}
	private JPanel getPnRecogida() {
		if (pnRecogida == null) {
			pnRecogida = new JPanel();
			pnRecogida.setLayout(new BorderLayout(0, 0));
			pnRecogida.add(getScpRecogida(), BorderLayout.CENTER);
			pnRecogida.add(getPnBotonesRecogida(), BorderLayout.SOUTH);
		}
		return pnRecogida;
	}
	private JScrollPane getScpEmpaquetado() {
		if (scpEmpaquetado == null) {
			scpEmpaquetado = new JScrollPane();
			scpEmpaquetado.setViewportView(getTbEmpaquetado());
		}
		return scpEmpaquetado;
	}
	private JTable getTbEmpaquetado() {
		if (tbEmpaquetado == null) {
			String[] columnas = { "Nombre","Apellidos","DNI","Tlfn", "Direccion" };
			modeloTabla1 = new DefaultTableModel(columnas, 0) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbEmpaquetado = new JTable(modeloTabla1);
		}
		return tbEmpaquetado;
	}
	private JPanel getPnBotonesEmpaquetado() {
		if (pnBotonesEmpaquetado == null) {
			pnBotonesEmpaquetado = new JPanel();
			FlowLayout fl_pnBotonesEmpaquetado = (FlowLayout) pnBotonesEmpaquetado.getLayout();
			fl_pnBotonesEmpaquetado.setHgap(15);
			fl_pnBotonesEmpaquetado.setAlignment(FlowLayout.RIGHT);
			pnBotonesEmpaquetado.add(getBtImprimirFactura());
			pnBotonesEmpaquetado.add(getBtEmpaquetar());
		}
		return pnBotonesEmpaquetado;
	}
	private JButton getBtEmpaquetar() {
		if (btEmpaquetar == null) {
			btEmpaquetar = new JButton("Empaquetar");
		}
		return btEmpaquetar;
	}
	private JButton getBtImprimirFactura() {
		if (btImprimirFactura == null) {
			btImprimirFactura = new JButton("Imprimir Factura");
		}
		return btImprimirFactura;
	}
	
	private JScrollPane getScpRecogida() {
		if (scpRecogida == null) {
			scpRecogida = new JScrollPane();
			scpRecogida.setViewportView(getTbRecogida());
		}
		return scpRecogida;
	}
	private JTable getTbRecogida() {
		if (tbRecogida == null) {
			String[] columnas = { "Pedido","Pasillo","Lado","Posicion", "Altura" };
			modeloTabla2 = new DefaultTableModel(columnas, 0) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbRecogida = new JTable(modeloTabla2);
		}
		return tbRecogida;
	}
	
	private void rellenarTablaEmpaquetado(){
		//TODO
	}
	
	private void rellenarTablaRecogida(){
		//TODO
	}
	
	private JPanel getPnBotonesRecogida() {
		if (pnBotonesRecogida == null) {
			pnBotonesRecogida = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotonesRecogida.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotonesRecogida.add(getBtAceptar());
		}
		return pnBotonesRecogida;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
		}
		return btAceptar;
	}
}
