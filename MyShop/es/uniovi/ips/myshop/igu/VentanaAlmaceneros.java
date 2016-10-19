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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.SubstanceLookAndFeel;

import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.connectors.GetWarehouseKeepers;
import es.uniovi.ips.myshop.connectors.ModifyOrder;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JTabbedPane;

public class VentanaAlmaceneros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnEleccionAlmacenero;
	private JScrollPane scpEleccionAlmacenero;
	private JPanel pnDescripcion;
	private JLabel label;
	private JTabbedPane tbpnEmpaquetadoRecogida;
	private JPanel pnEmpaquetado;
	private JPanel pnRecogida;
	private JScrollPane scpEmpaquetado;
	private JScrollPane scpRecogida;
	private JPanel pnIndiceEmpaquetado;
	private JLabel lblIndicePedidoId;
	private JLabel lblIndiceDireccion;
	private JLabel lblIndiceDni;
	private JLabel lblIndiceCantidad;
	private JLabel lblIndiceFecha;
	private JPanel pnIndiceRecogida;
	private JLabel lblRecogidaPedidoId;
	private JLabel lblDniCliente;
	private JLabel lblDireccionCliente;
	private JLabel lblFechaPedido;
	private JPanel pnRecogidaPedidos;
	private JPanel pnEmpaquetadoPedidos;
	private JPanel pnEleccion;
	private JPanel pnRecogidaProductos;
	private JScrollPane scpRecogidaProductos;
	private JPanel pnRecogidaProductosLista;
	private JPanel pnDescripcionVentanaRecogidaProductos;
	private JLabel lblRecogidaDeProductos;
	private JTextField txRecogidaPedido;
	private JPanel pnEmpaquetadoPedido;
	private JButton btnAlbaran;
	private JButton btnEtiquetar;
	private JLabel lblPedido;
	private JTextField txPedido;
	private boolean etiquetado;
	private boolean albaranizado;
	private String etiqueta;
	private String albaran;
	private int productos;
	private JPanel pnBotonesRecogida;
	private JButton btnAceptar;
	private JButton btnIncidencia;
	private WharehouseKeeper almacenero;
	

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
		setBounds(100, 100, 507, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnEleccionAlmacenero(), "name_36282555469147");
		contentPane.add(getTbpnEmpaquetadoRecogida(), "name_38548177215768");
		contentPane.add(getTbpnEmpaquetadoRecogida(), BorderLayout.CENTER);
		contentPane.add(getPnRecogidaProductos(), "name_6580868539181");
		contentPane.add(getPnEmpaquetadoPedido(), "name_114896927377530");
		cargarAlmaceneros();
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
						almacenero = c;
						cargarSegundoPanel();
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
	
	
	private void cargarSegundoPanel() throws SQLException{
		getPnEleccionAlmacenero().setVisible(false);
		getTbpnEmpaquetadoRecogida().setVisible(true);
		cargarPedidosRecogida();
		cargarProductos();
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
			pnEmpaquetado.add(getPnIndiceEmpaquetado(), BorderLayout.NORTH);
		}
		return pnEmpaquetado;
	}
	private JPanel getPnRecogida() {
		if (pnRecogida == null) {
			pnRecogida = new JPanel();
			pnRecogida.setLayout(new BorderLayout(0, 0));
			pnRecogida.add(getScpRecogida(), BorderLayout.CENTER);
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
	
	private JScrollPane getScpRecogida() {
		if (scpRecogida == null) {
			scpRecogida = new JScrollPane();
			scpRecogida.setViewportView(getPnRecogidaPedidos());
		}
		return scpRecogida;
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
		for (Order c : new GetOrders().getOrdersByStatus(Status.EMPAQUETANDO)) {
			for(OrderDetail p : c.getProductos()){
				InformacionProductoPedidoPanel aux = new InformacionProductoPedidoPanel(c);
				aux.getBtnEmpaquetar().setActionCommand(p.getProducto().getIDProducto());
				aux.getBtnEmpaquetar().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//TODO new AddWorkingPlan(new WorkingPlan(c,almacenero));
						realizarEmpaquetado(c);
					}
				});
				aux.setPreferredSize(new Dimension(getScpEmpaquetado().getWidth(), 233));
				cont.add(aux);
			}
		}

		cont.setLayout(new GridLayout(new GetOrders().getOrdersByStatus(Status.EMPAQUETANDO).size(), 1));

		revalidate();
		repaint();

		getScpEmpaquetado().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void cargarPedidosRecogida() throws SQLException {
		Container cont = new Container();
		
		for (Order c : new GetOrders().getOrdersByStatus(Status.PENDIENTE)) {
			RecogidaPedidosPanel aux = new RecogidaPedidosPanel(c);
			aux.getBtnRecoger().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//TODO new AddWorkingPlan(new WorkingPlan(c,almacenero));
					realizarRecogida(c);
				}
			});
			aux.setPreferredSize(new Dimension(getScpRecogida().getWidth(), 233));
			cont.add(aux);
		}

		cont.setLayout(new GridLayout(new GetOrders().getOrdersByStatus(Status.PENDIENTE).size(), 1));

		revalidate();
		repaint();

		getScpRecogida().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void realizarRecogida(Order o){
		cargarProductosPedido(o);
		getTbpnEmpaquetadoRecogida().setVisible(false);
		getPnRecogidaProductos().setVisible(true);
	}
	
	private void realizarEmpaquetado(Order o){
		getTbpnEmpaquetadoRecogida().setVisible(false);
		getTxPedido().setText(o.getIdPedido());
		getPnEmpaquetadoPedido().setVisible(true);
	}
	
	private String albaran(Order o){
		return o.printBill();
	}
	
	private String etiqueta(Order o){
		return o.printShippingInfo();
	}
	
	private JPanel getPnIndiceRecogida() {
		if (pnIndiceRecogida == null) {
			pnIndiceRecogida = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnIndiceRecogida.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setHgap(40);
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnIndiceRecogida.add(getLblRecogidaPedidoId());
			pnIndiceRecogida.add(getLblDniCliente());
			pnIndiceRecogida.add(getLblDireccionCliente());
			pnIndiceRecogida.add(getLblFechaPedido());
		}
		return pnIndiceRecogida;
	}
	private JLabel getLblRecogidaPedidoId() {
		if (lblRecogidaPedidoId == null) {
			lblRecogidaPedidoId = new JLabel("Pedido ID");
		}
		return lblRecogidaPedidoId;
	}
	private JLabel getLblDniCliente() {
		if (lblDniCliente == null) {
			lblDniCliente = new JLabel("DNI");
		}
		return lblDniCliente;
	}
	private JLabel getLblDireccionCliente() {
		if (lblDireccionCliente == null) {
			lblDireccionCliente = new JLabel("Direccion");
		}
		return lblDireccionCliente;
	}
	private JLabel getLblFechaPedido() {
		if (lblFechaPedido == null) {
			lblFechaPedido = new JLabel("Fecha");
		}
		return lblFechaPedido;
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
			pnRecogidaProductos.add(getPnDescripcionVentanaRecogidaProductos(), BorderLayout.NORTH);
			pnRecogidaProductos.add(getPnBotonesRecogida(), BorderLayout.SOUTH);
		}
		return pnRecogidaProductos;
	}
	private JScrollPane getScpRecogidaProductos() {
		if (scpRecogidaProductos == null) {
			scpRecogidaProductos = new JScrollPane();
			scpRecogidaProductos.setViewportView(getPnRecogidaProductosLista());
		}
		return scpRecogidaProductos;
	}
	private JPanel getPnRecogidaProductosLista() {
		if (pnRecogidaProductosLista == null) {
			pnRecogidaProductosLista = new JPanel();
			pnRecogidaProductosLista.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnRecogidaProductosLista;
	}
	private JPanel getPnDescripcionVentanaRecogidaProductos() {
		if (pnDescripcionVentanaRecogidaProductos == null) {
			pnDescripcionVentanaRecogidaProductos = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnDescripcionVentanaRecogidaProductos.getLayout();
			flowLayout.setHgap(40);
			pnDescripcionVentanaRecogidaProductos.add(getLblRecogidaDeProductos());
			pnDescripcionVentanaRecogidaProductos.add(getTxRecogidaPedido());
		}
		return pnDescripcionVentanaRecogidaProductos;
	}
	private JLabel getLblRecogidaDeProductos() {
		if (lblRecogidaDeProductos == null) {
			lblRecogidaDeProductos = new JLabel("Recogida de productos del pedido: ");
		}
		return lblRecogidaDeProductos;
	}
	private JTextField getTxRecogidaPedido() {
		if (txRecogidaPedido == null) {
			txRecogidaPedido = new JTextField();
			txRecogidaPedido.setEditable(false);
			txRecogidaPedido.setColumns(10);
		}
		return txRecogidaPedido;
	}
	
	private void cargarProductosPedido(Order o) {
		Container cont = new Container();
		getTxRecogidaPedido().setText(o.getIdPedido());
		for (OrderDetail c : o.getProductos()) {
			productos = o.getProductos().size();
			PedidoListaPanel aux = new PedidoListaPanel(c);
			aux.getBtnRecoger().setActionCommand(c.getProducto().getIDProducto());
			
			aux.getBtnRecoger().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					aux.getBtnRecoger().setEnabled(false);
					productos--;
					mostrarConfirmacionCodigoProducto(aux.getBtnRecoger().getActionCommand());	
					if(productos==0){
						getBtnAceptar().setEnabled(true);
					}
				}
			});
			aux.setPreferredSize(new Dimension(getScpRecogidaProductos().getWidth(), 233));
			cont.add(aux);
		}

		cont.setLayout(new GridLayout(o.getProductos().size(), 1));

		revalidate();
		repaint();

		getScpRecogidaProductos().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void mostrarConfirmacionCodigoProducto(String s){
		VentanaConfirmacionCodigo vc = new VentanaConfirmacionCodigo(s);
		vc.setVisible(true);
		vc.setLocationRelativeTo(this);
	}
	private JPanel getPnEmpaquetadoPedido() {
		if (pnEmpaquetadoPedido == null) {
			pnEmpaquetadoPedido = new JPanel();
			pnEmpaquetadoPedido.setLayout(null);
			pnEmpaquetadoPedido.add(getBtnAlbaran());
			pnEmpaquetadoPedido.add(getBtnEtiquetar());
			pnEmpaquetadoPedido.add(getLblPedido());
			pnEmpaquetadoPedido.add(getTxPedido());
		}
		return pnEmpaquetadoPedido;
	}
	private JButton getBtnAlbaran() {
		if (btnAlbaran == null) {
			btnAlbaran = new JButton("Albaran");
			btnAlbaran.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(etiquetado){
						try {
							albaran = albaran(new GetOrders().getOrder(getTxPedido().getText()));
							Order o = new GetOrders().getOrder(getTxPedido().getText());
							new ModifyOrder(o, Status.LISTO);
							mostrarInicioEmpaquetado();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else{
						albaranizado = true;
						try {
							albaran = albaran(new GetOrders().getOrder(getTxPedido().getText()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						getBtnAlbaran().setEnabled(false);
					}
				}
			});
			btnAlbaran.setBounds(177, 111, 89, 23);
		}
		return btnAlbaran;
	}
	private JButton getBtnEtiquetar() {
		if (btnEtiquetar == null) {
			btnEtiquetar = new JButton("Etiquetar");
			btnEtiquetar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(albaranizado){
						try {
							etiqueta = etiqueta(new GetOrders().getOrder(getTxPedido().getText()));
							Order o = new GetOrders().getOrder(getTxPedido().getText());
							new ModifyOrder(o, Status.LISTO);
							mostrarInicioEmpaquetado();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else{
						etiquetado = true;
						try {
							etiqueta = etiqueta(new GetOrders().getOrder(getTxPedido().getText()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						getBtnEtiquetar().setEnabled(false);
					}
				}
			});
			btnEtiquetar.setBounds(177, 159, 89, 23);
		}
		return btnEtiquetar;
	}
	
	private void mostrarInicioEmpaquetado(){
		getPnEmpaquetadoPedido().setVisible(false);
		getPnEleccionAlmacenero().setVisible(true);;
		getBtnEtiquetar().setEnabled(true);
		getBtnAlbaran().setEnabled(true);
	}
	private JLabel getLblPedido() {
		if (lblPedido == null) {
			lblPedido = new JLabel("Pedido: ");
			lblPedido.setBounds(127, 59, 46, 14);
		}
		return lblPedido;
	}
	private JTextField getTxPedido() {
		if (txPedido == null) {
			txPedido = new JTextField();
			txPedido.setBounds(221, 56, 86, 20);
			txPedido.setColumns(10);
		}
		return txPedido;
	}
	private JPanel getPnBotonesRecogida() {
		if (pnBotonesRecogida == null) {
			pnBotonesRecogida = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotonesRecogida.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotonesRecogida.add(getBtnIncidencia());
			pnBotonesRecogida.add(getBtnAceptar());
		}
		return pnBotonesRecogida;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Order o = new GetOrders().getOrder(getTxRecogidaPedido().getText());
						new ModifyOrder(o, Status.EMPAQUETANDO);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					mostrarInicioRecogida();
				}
			});
			btnAceptar.setEnabled(false);
		}
		return btnAceptar;
	}
	
	private void mostrarInicioRecogida(){
		getPnRecogidaProductos().setVisible(false);
		getPnEleccionAlmacenero().setVisible(true);;
		getBtnAceptar().setEnabled(false);
	}
	private JButton getBtnIncidencia() {
		if (btnIncidencia == null) {
			btnIncidencia = new JButton("Incidencia");
			btnIncidencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//todo new AddIncidence(new Incidence(String));
				}
			});
		}
		return btnIncidencia;
	}
}
