package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.SubstanceLookAndFeel;

import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.people.Person;
import es.uniovi.ips.myshop.model.product.Product;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Container;

public class AsignacionPedidosAlmaceneros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnAsignacionProductos;
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
	private JScrollPane scpEleccionAlmacenero;
	private JPanel pnDescripcion;
	private JLabel label;
	private List<Person> listaAlmaceneros = new ArrayList<Person>();
	private List<Order> listaPedidos = new ArrayList<Order>();
	private JPanel panel;
	private JLabel lblIdPedido;
	private JLabel lblTamao;
	private JLabel lblFecha;

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
		contentPane.add(getPnEleccionAlmacenero(), "name_14207797347604");
		contentPane.add(getPnAsignacionProductos(), "name_14193795799254");
		cargarAlmaceneros();
	}
	private JPanel getPnAsignacionProductos() {
		if (pnAsignacionProductos == null) {
			pnAsignacionProductos = new JPanel();
			pnAsignacionProductos.setLayout(new BorderLayout(0, 0));
			pnAsignacionProductos.add(getPnAsignacion(), BorderLayout.CENTER);
			pnAsignacionProductos.add(getPnDatosAlmacenero(), BorderLayout.NORTH);
			pnAsignacionProductos.add(getPnBotones(), BorderLayout.SOUTH);
		}
		return pnAsignacionProductos;
	}
	private JScrollPane getScpAsignacion() {
		if (scpAsignacion == null) {
			scpAsignacion = new JScrollPane();
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

	private JPanel getPnAsignacion() {
		if (pnAsignacion == null) {
			pnAsignacion = new JPanel();
			pnAsignacion.setLayout(new BorderLayout(0, 0));
			pnAsignacion.add(getScpAsignacion());
			pnAsignacion.add(getPanel(), BorderLayout.NORTH);
		}
		return pnAsignacion;
	}
	private JPanel getPnEleccionAlmacenero() {
		if (pnEleccionAlmacenero == null) {
			pnEleccionAlmacenero = new JPanel();
			pnEleccionAlmacenero.setLayout(new BorderLayout(0, 0));
			pnEleccionAlmacenero.add(getScpEleccionAlmacenero(), BorderLayout.CENTER);
			pnEleccionAlmacenero.add(getPnDescripcion(), BorderLayout.NORTH);
		}
		return pnEleccionAlmacenero;
	}
	private JScrollPane getScpEleccionAlmacenero() {
		if (scpEleccionAlmacenero == null) {
			scpEleccionAlmacenero = new JScrollPane();
		}
		return scpEleccionAlmacenero;
	}
	private JPanel getPnDescripcion() {
		if (pnDescripcion == null) {
			pnDescripcion = new JPanel();
			pnDescripcion.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 12));
			pnDescripcion.add(getLabel());
		}
		return pnDescripcion;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Elija el almacenero que es usted para ver los pedidos que puede asignarse");
		}
		return label;
	}
	
	private void cargarAlmaceneros() {
		Container cont = new Container();

		for (Person c : listaAlmaceneros) {
			InformacionAlmaceneroPanel aux = new InformacionAlmaceneroPanel();
			aux.getBtEsteSoyYo().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					cargarSegundoPanel(c);	
				}
			});

			cont.add(aux);
		}

		cont.setLayout(new GridLayout(listaAlmaceneros.size(), 1));

		revalidate();
		repaint();

		getScpEleccionAlmacenero().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	

	private void cargarPedidos() {
		Container cont = new Container();

		for (Order c : listaPedidos) {
			PedidoParaAsignarPanel aux = new PedidoParaAsignarPanel();
			aux.getLblIdPedido().setText(c.getIdPedido());
			int cantidad = 0;
			for(OrderDetail p : c.getProductos()){
				cantidad = cantidad + p.getCantidad();
			}
			aux.getLblCantidad().setText(cantidad+ "");
			aux.getLblFecha().setText(c.getDate() + "");;

			cont.add(aux);
		}

		cont.setLayout(new GridLayout(listaAlmaceneros.size(), 1));

		revalidate();
		repaint();

		getScpAsignacion().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void cargarSegundoPanel(Person p){
		getTxAlmacenero().setText(p.getName());
		getTxID().setText(p.getId());
		getPnAsignacionProductos().transferFocus();
		cargarPedidos();		
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setHgap(70);
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel.add(getLblIdPedido());
			panel.add(getLblTamao());
			panel.add(getLblFecha());
		}
		return panel;
	}
	private JLabel getLblIdPedido() {
		if (lblIdPedido == null) {
			lblIdPedido = new JLabel("ID Pedido");
		}
		return lblIdPedido;
	}
	private JLabel getLblTamao() {
		if (lblTamao == null) {
			lblTamao = new JLabel("Tama\u00F1o");
		}
		return lblTamao;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
		}
		return lblFecha;
	}
}
