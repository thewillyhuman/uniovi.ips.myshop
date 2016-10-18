package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.SubstanceLookAndFeel;

import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.connectors.GetWarehouseKeepers;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.model.people.Person;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.model.product.Product;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JTabbedPane;

public class VentanaAlmaceneros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnAsignacionProductos;
	private JScrollPane scpAsignacion;
	private JPanel pnDatosAlmacenero;
	private JLabel lblAlmacenero;
	private JTextField txAlmacenero;
	private JLabel lblId;
	private JTextField txID;
	private JPanel pnAsignacion;
	private JPanel pnEleccionAlmacenero;
	private JScrollPane scpEleccionAlmacenero;
	private JPanel pnDescripcion;
	private JLabel label;
	private JPanel panel;
	private JLabel lblIdPedido;
	private JLabel lblTamao;
	private JLabel lblFecha;
	private JTabbedPane tbpnEmpaquetadoRecogida;
	private JPanel pnEmpaquetado;
	private JPanel pnRecogida;
	private JScrollPane scpEmpaquetado;
	private JPanel pnBotonesEmpaquetado;
	private JButton btImprimirFactura;
	private JScrollPane scpRecogida;
	private JPanel pnBotonesRecogida;
	private JButton btAceptar2;
	private JPanel pnIndiceEmpaquetado;
	private JLabel lblIndicePedidoId;
	private JLabel lblIndiceDireccion;
	private JLabel lblIndiceDni;
	private JLabel lblIndiceCantidad;
	private JLabel lblIndiceFecha;
	private JPanel pnIndiceRecogida;
	private JLabel lblRecogidaPedidoId;
	private JLabel lblRecogidaPasillo;
	private JLabel lblRecogidaLado;
	private JLabel lblRecogidaPosicion;
	private JLabel lblRecogidaAltura;
	private JPanel pnRecogidaPedidos;
	private JPanel pnEmpaquetadoPedidos;
	private JPanel pnEleccion;
	private JPanel pnRecogidaProductos;
	private JScrollPane scpRecogidaProductos;
	

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
					VentanaAlmaceneros frame = new VentanaAlmaceneros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VentanaAlmaceneros() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnEleccionAlmacenero(), "name_36282555469147");
		contentPane.add(getPnAsignacionProductos(), "name_36658960011270");
		contentPane.add(getTbpnEmpaquetadoRecogida(), "name_38548177215768");
		contentPane.add(getTbpnEmpaquetadoRecogida(), BorderLayout.CENTER);
		contentPane.add(getPnRecogidaProductos(), "name_6580868539181");
		cargarAlmaceneros();
	}
	
	private JPanel getPnAsignacionProductos() {
		if (pnAsignacionProductos == null) {
			pnAsignacionProductos = new JPanel();
			pnAsignacionProductos.setLayout(new BorderLayout(0, 0));
			pnAsignacionProductos.add(getPnAsignacion(), BorderLayout.CENTER);
			pnAsignacionProductos.add(getPnDatosAlmacenero(), BorderLayout.NORTH);
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
			scpEleccionAlmacenero.setViewportView(getPnEleccion());
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

		for (WharehouseKeeper c : new GetWarehouseKeepers().getAll()) {
			InformacionAlmaceneroPanel aux = new InformacionAlmaceneroPanel(c);
			aux.getBtEsteSoyYo().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						cargarSegundoPanel(c);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
			});
			aux.setPreferredSize(new Dimension(getScpEleccionAlmacenero().getWidth(), 233));
			cont.add(aux);
		}

		cont.setLayout(new GridLayout(new GetWarehouseKeepers().getAll().size(), 1));

		revalidate();
		repaint();

		getScpEleccionAlmacenero().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	

	private void cargarPedidos() throws SQLException {
		Container cont = new Container();

		for (Order c : new GetOrders().getOrdersByStatus(Status.PENDIENTE)) {
			PedidoParaAsignarPanel aux = new PedidoParaAsignarPanel(c);
			aux.getBtnAceptar().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						cargarTercerPanel();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			cont.add(aux);
		}

		cont.setLayout(new GridLayout(new GetOrders().getOrdersByStatus(Status.PENDIENTE).size(), 1));

		revalidate();
		repaint();

		getScpAsignacion().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void cargarSegundoPanel(WharehouseKeeper p) throws SQLException{
		getTxAlmacenero().setText(p.getName());
		getTxID().setText(p.getId());
		cargarPedidos();
		getPnAsignacionProductos().transferFocus();
		getPnEleccionAlmacenero().setVisible(false);
		getPnAsignacionProductos().setVisible(true);
	}
	
	private void cargarTercerPanel() throws SQLException{
		getPnAsignacionProductos().setVisible(false);
		getTbpnEmpaquetadoRecogida().setVisible(true);
		cargarPedidosRecogida();
		cargarProductos();
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
	private JTabbedPane getTbpnEmpaquetadoRecogida() {
		if (tbpnEmpaquetadoRecogida == null) {
			tbpnEmpaquetadoRecogida = new JTabbedPane(JTabbedPane.TOP);
			tbpnEmpaquetadoRecogida.addTab("Recogida", null, getPnRecogida(), null);
			tbpnEmpaquetadoRecogida.addTab("Empaquetado", null, getPnEmpaquetado(), null);
		}
		return tbpnEmpaquetadoRecogida;
	}
	private JPanel getPnEmpaquetado() {
		if (pnEmpaquetado == null) {
			pnEmpaquetado = new JPanel();
			pnEmpaquetado.setLayout(new BorderLayout(0, 0));
			pnEmpaquetado.add(getScpEmpaquetado(), BorderLayout.CENTER);
			pnEmpaquetado.add(getPnBotonesEmpaquetado(), BorderLayout.SOUTH);
			pnEmpaquetado.add(getPnIndiceEmpaquetado(), BorderLayout.NORTH);
		}
		return pnEmpaquetado;
	}
	private JPanel getPnRecogida() {
		if (pnRecogida == null) {
			pnRecogida = new JPanel();
			pnRecogida.setLayout(new BorderLayout(0, 0));
			pnRecogida.add(getScpRecogida(), BorderLayout.CENTER);
			pnRecogida.add(getPnBotonesRecogida(), BorderLayout.SOUTH);
			pnRecogida.add(getPnIndiceRecogida(), BorderLayout.NORTH);
		}
		return pnRecogida;
	}
	private JScrollPane getScpEmpaquetado() {
		if (scpEmpaquetado == null) {
			scpEmpaquetado = new JScrollPane();
			scpEmpaquetado.setViewportView(getPnEmpaquetadoPedidos());
		}
		return scpEmpaquetado;
	}
	private JPanel getPnBotonesEmpaquetado() {
		if (pnBotonesEmpaquetado == null) {
			pnBotonesEmpaquetado = new JPanel();
			FlowLayout fl_pnBotonesEmpaquetado = (FlowLayout) pnBotonesEmpaquetado.getLayout();
			fl_pnBotonesEmpaquetado.setHgap(15);
			fl_pnBotonesEmpaquetado.setAlignment(FlowLayout.RIGHT);
			pnBotonesEmpaquetado.add(getBtImprimirFactura());
		}
		return pnBotonesEmpaquetado;
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
			scpRecogida.setViewportView(getPnRecogidaPedidos());
		}
		return scpRecogida;
	}
	
	private JPanel getPnBotonesRecogida() {
		if (pnBotonesRecogida == null) {
			pnBotonesRecogida = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotonesRecogida.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotonesRecogida.add(getBtAceptar2());
		}
		return pnBotonesRecogida;
	}
	private JButton getBtAceptar2() {
		if (btAceptar2 == null) {
			btAceptar2 = new JButton("Aceptar");
		}
		return btAceptar2;
	}
	private JPanel getPnIndiceEmpaquetado() {
		if (pnIndiceEmpaquetado == null) {
			pnIndiceEmpaquetado = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnIndiceEmpaquetado.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setVgap(7);
			flowLayout.setHgap(30);
			pnIndiceEmpaquetado.add(getLblIndicePedidoId());
			pnIndiceEmpaquetado.add(getLblIndiceDni());
			pnIndiceEmpaquetado.add(getLblIndiceDireccion());
			pnIndiceEmpaquetado.add(getLblIndiceCantidad());
			pnIndiceEmpaquetado.add(getLblIndiceFecha());
		}
		return pnIndiceEmpaquetado;
	}
	private JLabel getLblIndicePedidoId() {
		if (lblIndicePedidoId == null) {
			lblIndicePedidoId = new JLabel("Pedido ID");
		}
		return lblIndicePedidoId;
	}
	private JLabel getLblIndiceDireccion() {
		if (lblIndiceDireccion == null) {
			lblIndiceDireccion = new JLabel("Direccion");
		}
		return lblIndiceDireccion;
	}
	private JLabel getLblIndiceDni() {
		if (lblIndiceDni == null) {
			lblIndiceDni = new JLabel("DNI");
		}
		return lblIndiceDni;
	}
	private JLabel getLblIndiceCantidad() {
		if (lblIndiceCantidad == null) {
			lblIndiceCantidad = new JLabel("Cantidad");
		}
		return lblIndiceCantidad;
	}
	private JLabel getLblIndiceFecha() {
		if (lblIndiceFecha == null) {
			lblIndiceFecha = new JLabel("Fecha");
		}
		return lblIndiceFecha;
	}
	
	private void cargarProductos() throws SQLException {
		Container cont = new Container();
		for (Order c : new GetOrders().getOrdersByStatus(Status.SOLICITADO)) {
			for(OrderDetail p : c.getProductos()){
				InformacionProductoPedidoPanel aux = new InformacionProductoPedidoPanel(c);
				aux.getBtnEmpaquetar().setActionCommand(p.getProducto().getIDProducto());
				aux.getBtnEmpaquetar().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						realizarEmpaquetado(c);
					}
				});
				aux.setPreferredSize(new Dimension(getScpEmpaquetado().getWidth(), 233));
				cont.add(aux);
			}
		}

		cont.setLayout(new GridLayout(new GetOrders().getOrdersByStatus(Status.SOLICITADO).size(), 1));

		revalidate();
		repaint();

		getScpEmpaquetado().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void cargarPedidosRecogida() throws SQLException {
		Container cont = new Container();
		
		for (Order c : new GetOrders().getOrdersByStatus(Status.PENDIENTE)) {
			for(OrderDetail p : c.getProductos()){
				RecogidaPedidosPanel aux = new RecogidaPedidosPanel(c,p);
				aux.getBtnRecoger().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						realizarRecogida(p.getProducto());
					}
				});
				aux.setPreferredSize(new Dimension(getScpRecogida().getWidth(), 233));
				cont.add(aux);
			}
		}

		cont.setLayout(new GridLayout(new GetOrders().getOrdersByStatus(Status.PENDIENTE).size(), 1));

		revalidate();
		repaint();

		getScpRecogida().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void realizarRecogida(Product p){
		JOptionPane.showMessageDialog(this, "El producto " + p.getDescripcion() + " \n ha sido recogido con exito");
	}
	
	private void realizarEmpaquetado(Order o){
		StringBuilder sb = new StringBuilder("Productos Empaquetados:\n");
		for(OrderDetail c : o.getProductos()){
			sb.append(c.getProducto().getDescripcion() + "\n");
		}
		JOptionPane.showMessageDialog(null, sb.toString());
	}
	
	private JPanel getPnIndiceRecogida() {
		if (pnIndiceRecogida == null) {
			pnIndiceRecogida = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnIndiceRecogida.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setHgap(40);
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnIndiceRecogida.add(getLblRecogidaPedidoId());
			pnIndiceRecogida.add(getLblRecogidaPasillo());
			pnIndiceRecogida.add(getLblRecogidaLado());
			pnIndiceRecogida.add(getLblRecogidaPosicion());
			pnIndiceRecogida.add(getLblRecogidaAltura());
		}
		return pnIndiceRecogida;
	}
	private JLabel getLblRecogidaPedidoId() {
		if (lblRecogidaPedidoId == null) {
			lblRecogidaPedidoId = new JLabel("Pedido ID");
		}
		return lblRecogidaPedidoId;
	}
	private JLabel getLblRecogidaPasillo() {
		if (lblRecogidaPasillo == null) {
			lblRecogidaPasillo = new JLabel("Pasillo");
		}
		return lblRecogidaPasillo;
	}
	private JLabel getLblRecogidaLado() {
		if (lblRecogidaLado == null) {
			lblRecogidaLado = new JLabel("Lado");
		}
		return lblRecogidaLado;
	}
	private JLabel getLblRecogidaPosicion() {
		if (lblRecogidaPosicion == null) {
			lblRecogidaPosicion = new JLabel("Posicion");
		}
		return lblRecogidaPosicion;
	}
	private JLabel getLblRecogidaAltura() {
		if (lblRecogidaAltura == null) {
			lblRecogidaAltura = new JLabel("Altura");
		}
		return lblRecogidaAltura;
	}
	private JPanel getPnRecogidaPedidos() {
		if (pnRecogidaPedidos == null) {
			pnRecogidaPedidos = new JPanel();
			pnRecogidaPedidos.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnRecogidaPedidos;
	}
	private JPanel getPnEmpaquetadoPedidos() {
		if (pnEmpaquetadoPedidos == null) {
			pnEmpaquetadoPedidos = new JPanel();
			pnEmpaquetadoPedidos.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnEmpaquetadoPedidos;
	}
	private JPanel getPnEleccion() {
		if (pnEleccion == null) {
			pnEleccion = new JPanel();
			pnEleccion.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnEleccion;
	}
	private JPanel getPnRecogidaProductos() {
		if (pnRecogidaProductos == null) {
			pnRecogidaProductos = new JPanel();
			pnRecogidaProductos.setLayout(new BorderLayout(0, 0));
			pnRecogidaProductos.add(getScpRecogidaProductos(), BorderLayout.CENTER);
		}
		return pnRecogidaProductos;
	}
	private JScrollPane getScpRecogidaProductos() {
		if (scpRecogidaProductos == null) {
			scpRecogidaProductos = new JScrollPane();
		}
		return scpRecogidaProductos;
	}
}
