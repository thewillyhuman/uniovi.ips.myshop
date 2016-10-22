package es.uniovi.ips.myshop.igunew;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.uniovi.ips.myshop.connectors.AddNewIncidence;
import es.uniovi.ips.myshop.connectors.AddWorkingPlan;
import es.uniovi.ips.myshop.connectors.GetWorkingPlan;
import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.connectors.GetWarehouseKeepers;
import es.uniovi.ips.myshop.connectors.ModifyOrderStatus;
import es.uniovi.ips.myshop.connectors.ModifyProductInOrder;
import es.uniovi.ips.myshop.connectors.RemoveIncidence;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;
import es.uniovi.ips.myshop.model.warehouse.WorkingPlan;
import es.uniovi.ips.myshop.model.warehouse.incidences.Incidence;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

public class VentanaPrincipalAlmacenero extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel imgCarrito;
	private JPanel panel;
	private JLabel lblIrATienda;
	private JPanel panel_1;
	private JLabel label;
	private JLabel lblIrAAlmacn;
	private JLabel lblSelecioneLaVentana;
	private JPanel inicio;
	private JPanel panel_3;
	private JPanel recogida;
	private JLabel lblPedidoN;
	private JLabel lblNPedido;
	private JLabel lblNDeArtculos;
	private JLabel labelNArticulos;
	private JLabel lblEstado;
	private JLabel labelEstado;
	private JScrollPane scrollPane;
	private JTable TablaCompleta;
	private JScrollPane scrollPane_1;
	private JTable TablaParcial;
	private JLabel lblProductosEnEl;
	private JLabel lblProductosRecogidos;
	private JButton btnIniciarProcesoDe;
	private JLabel label_3;
	private ModeloTablaNoEditable modeloPorRecoger;
	private ModeloTablaNoEditable modeloRecogido;
	private ModeloTablaNoEditableNoProcesado modeloNoProcesado;
	private JPanel empaquetado;
	private JTable tablaResumen;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTextField txtDireccion;
	private JLabel label_1;
	private JButton btnImprimirFactura;
	private JButton btnGenerarEtiquetasEnvio;
	private JButton btnGenerarIncidencia;
	private WorkingPlan wk;
	private JPanel ListaRegogida;
	private JLabel label_2;
	private JScrollPane scrollPane_3;
	private JTable tableListaNoProcesados;
	private JButton btnRecoger;
	private JPanel ListaEmpaquetado;
	private JScrollPane scrollPane_4;
	private JButton btnEmpaquetar;
	private JTable tableParaEmpaquetar;
	private JLabel label_4;
	private JButton btnMarcarListo;
	private WharehouseKeeper almacenero;
	private Order order;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalAlmacenero frame = new VentanaPrincipalAlmacenero();
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
	public VentanaPrincipalAlmacenero() {
		setResizable(false);
		setTitle("MyShop. Vista Almacenero");
		setMinimumSize(new Dimension(1046, 703));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getInicio(), "inicio");
		contentPane.add(getPanel_2_1(), "recogida");
		contentPane.add(getEmpaquetado(), "empaquetado");
		contentPane.add(getListaRegogida(), "lista-no-procesado");
		contentPane.add(getListaEmpaquetado(), "lista-empaquetado");
	}

	private JLabel getImgCarrito() {
		if (imgCarrito == null) {
			imgCarrito = new JLabel("");
			imgCarrito.setBounds(43, 6, 128, 128);
			imgCarrito.setHorizontalAlignment(SwingConstants.CENTER);
			imgCarrito.setIcon(new ImageIcon(VentanaPrincipalAlmacenero.class
					.getResource("/es/uniovi/ips/myshop/igunew/img/recoger-productos.png")));
		}
		return imgCarrito;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 220, 224);
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					while ((almacenero = new GetWarehouseKeepers().getByID(Integer.parseInt(
							JOptionPane.showInputDialog(contentPane, "Introduzca su id de empleado:")))) == null)
						;
					if (new GetWorkingPlan().isOccupied(almacenero)) {
						int opt = JOptionPane.showConfirmDialog(contentPane,
								"Debe de terminar de recoger su pedido anterior antes de seleccionar otro nuevo.",
								"Aviso", JOptionPane.OK_CANCEL_OPTION);
						if (opt == 0) {
							((CardLayout) contentPane.getLayout()).show(contentPane, "recogida");
							setPanelRecogida(new GetWorkingPlan().getCurrent(almacenero));
						}
					} else {
						((CardLayout) contentPane.getLayout()).show(contentPane, "lista-no-procesado");
						cargarVentalaListaNoProcesado();
					}

				}
			});
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel.setBorder(new LineBorder(new Color(192, 192, 192)));
			panel.setLayout(null);
			panel.add(getImgCarrito());
			panel.add(getLblIrATienda());
		}
		return panel;
	}

	private JLabel getLblIrATienda() {
		if (lblIrATienda == null) {
			lblIrATienda = new JLabel("Recoger Pedidos");
			lblIrATienda.setHorizontalAlignment(SwingConstants.CENTER);
			lblIrATienda.setBounds(43, 191, 128, 16);
		}
		return lblIrATienda;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBounds(518, 0, 220, 224);
			panel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) contentPane.getLayout()).show(contentPane, "lista-empaquetado");
					cargarVentanaParaEmpaquetar();

				}
			});
			panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel_1.setBorder(new LineBorder(new Color(192, 192, 192)));
			panel_1.setLayout(null);
			panel_1.add(getLabel());
			panel_1.add(getLblIrAAlmacn());
		}
		return panel_1;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(VentanaPrincipalAlmacenero.class
					.getResource("/es/uniovi/ips/myshop/igunew/img/empaquetar-pedido.png")));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(43, 6, 128, 128);
		}
		return label;
	}

	private JLabel getLblIrAAlmacn() {
		if (lblIrAAlmacn == null) {
			lblIrAAlmacn = new JLabel("Empaquetar Pedidos");
			lblIrAAlmacn.setHorizontalAlignment(SwingConstants.CENTER);
			lblIrAAlmacn.setBounds(43, 192, 128, 16);
		}
		return lblIrAAlmacn;
	}

	private JLabel getLabel_1_1() {
		if (lblSelecioneLaVentana == null) {
			lblSelecioneLaVentana = new JLabel("Selecione la acción que desea realizar.");
			lblSelecioneLaVentana.setBounds(211, 342, 358, 51);
			lblSelecioneLaVentana.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblSelecioneLaVentana.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSelecioneLaVentana;
	}

	private JPanel getInicio() {
		if (inicio == null) {
			inicio = new JPanel();
			inicio.setLayout(null);
			inicio.add(getPanel_3());
		}
		return inicio;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBounds(134, 166, 738, 393);
			panel_3.setLayout(null);
			panel_3.add(getPanel_1());
			panel_3.add(getPanel());
			panel_3.add(getLabel_1_1());
		}
		return panel_3;
	}

	private JPanel getPanel_2_1() {
		if (recogida == null) {
			recogida = new JPanel();
			recogida.setLayout(null);
			recogida.add(getLblPedidoN());
			recogida.add(getLblNPedido());
			recogida.add(getLblNDeArtculos());
			recogida.add(getLabelNArticulos());
			recogida.add(getLblEstado());
			recogida.add(getLabelEstado());
			recogida.add(getScrollPane());
			recogida.add(getScrollPane_1());
			recogida.add(getLblProductosEnEl());
			recogida.add(getLblProductosRecogidos());
			recogida.add(getBtnIniciarProcesoDe());
			recogida.add(getLabel_3());
			recogida.add(getBtnGenerarIncidencia());
		}
		return recogida;
	}

	private JLabel getLblPedidoN() {
		if (lblPedidoN == null) {
			lblPedidoN = new JLabel("Pedido nº:");
			lblPedidoN.setVerticalAlignment(SwingConstants.BOTTOM);
			lblPedidoN.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPedidoN.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblPedidoN.setBounds(374, 6, 92, 31);
		}
		return lblPedidoN;
	}

	private JLabel getLblNPedido() {
		if (lblNPedido == null) {
			lblNPedido = new JLabel("00001");
			lblNPedido.setBounds(478, 21, 122, 16);
		}
		return lblNPedido;
	}

	private JLabel getLblNDeArtculos() {
		if (lblNDeArtculos == null) {
			lblNDeArtculos = new JLabel("Nº de artículos:");
			lblNDeArtculos.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNDeArtculos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNDeArtculos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNDeArtculos.setBounds(560, 6, 122, 31);
		}
		return lblNDeArtculos;
	}

	private JLabel getLabelNArticulos() {
		if (labelNArticulos == null) {
			labelNArticulos = new JLabel("00001");
			labelNArticulos.setBounds(694, 20, 122, 16);
		}
		return labelNArticulos;
	}

	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado:");
			lblEstado.setVerticalAlignment(SwingConstants.BOTTOM);
			lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEstado.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblEstado.setBounds(795, 6, 85, 31);
		}
		return lblEstado;
	}

	private JLabel getLabelEstado() {
		if (labelEstado == null) {
			labelEstado = new JLabel("00001");
			labelEstado.setBounds(892, 20, 122, 16);
		}
		return labelEstado;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(26, 98, 488, 529);
			scrollPane.setViewportView(getTablaCompleta());
		}
		return scrollPane;
	}

	private JTable getTablaCompleta() {
		if (TablaCompleta == null) {
			Object[] columnaNames = { "ID PRODUCTO", "PASILLO", "LADO", "POSICIÓN", "ALTURA" };
			Object[][] data = {};
			TablaCompleta = new JTable(data, columnaNames);
			TablaCompleta.setShowVerticalLines(false);
		}
		return TablaCompleta;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(526, 98, 488, 529);
			scrollPane_1.setViewportView(getTablaParcial());
		}
		return scrollPane_1;
	}

	private JTable getTablaParcial() {
		if (TablaParcial == null) {
			Object[] columnaNames = { "ID PRODUCTO", "PASILLO", "LADO", "POSICIÓN", "ALTURA" };
			Object[][] data = {};
			TablaParcial = new JTable(data, columnaNames);
		}
		return TablaParcial;
	}

	private JLabel getLblProductosEnEl() {
		if (lblProductosEnEl == null) {
			lblProductosEnEl = new JLabel("Productos Por Recoger");
			lblProductosEnEl.setBounds(26, 70, 223, 16);
		}
		return lblProductosEnEl;
	}

	private JLabel getLblProductosRecogidos() {
		if (lblProductosRecogidos == null) {
			lblProductosRecogidos = new JLabel("Productos Recogidos");
			lblProductosRecogidos.setBounds(527, 70, 217, 16);
		}
		return lblProductosRecogidos;
	}

	private JButton getBtnIniciarProcesoDe() {
		if (btnIniciarProcesoDe == null) {
			btnIniciarProcesoDe = new JButton("Iniciar Proceso de Recogida");
			btnIniciarProcesoDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (OrderDetail od : modeloPorRecoger.getAllRows()) {
						String message = "Diríjase a: " + od.getProducto().getLocalizacion().toString();
						String id = JOptionPane.showInputDialog(contentPane, message);
						while (!od.getProducto().getIDProducto().equals(id) && id != null) {
							message = "ERROR ESCANEANDO CÓDIGO Diríjase a: "
									+ od.getProducto().getLocalizacion().toString();
							id = JOptionPane.showInputDialog(contentPane, message);
						}
						if (od.getProducto().getIDProducto().equals(id)) {
							od.collected = true;
							new ModifyProductInOrder(modeloPorRecoger.getOrder(), od, 0); // 0
																							// significa
																							// recogido
						}
						updateTablasRecogida(modeloPorRecoger.getOrder());
					}
					if (modeloPorRecoger.getOrder().allCollected()) {
						String message = "Enhorabuena, todos los productos han sido recogidos satisfactoriamente!!! ¿Que desea hacer ahora?";
						Object[] options = { "Ir a empaquetado", "Ir a inicio" };
						try {
							new ModifyOrderStatus(modeloRecogido.getOrder(), Status.EMPAQUETANDO);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int imp = JOptionPane
								.showOptionDialog(contentPane, message, "Finalozado", JOptionPane.YES_NO_OPTION,
										JOptionPane.INFORMATION_MESSAGE,
										new ImageIcon(VentanaPrincipalAlmacenero.class
												.getResource("/es/uniovi/ips/myshop/igunew/img/correct.png")),
										options, 1);
						if (imp == 1)
							((CardLayout) contentPane.getLayout()).show(contentPane, "inicio");
						else {
							setPanelEmpaquetado(modeloPorRecoger.getOrder());
							((CardLayout) contentPane.getLayout()).show(contentPane, "empaquetado");
						}
					}
				}
			});
			btnIniciarProcesoDe.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/barcode-icon.png")));
			btnIniciarProcesoDe.setBounds(285, 65, 229, 29);
		}
		return btnIniciarProcesoDe;
	}

	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Atrás");
			label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) contentPane.getLayout()).show(contentPane, "lista-no-procesado");
				}
			});
			label_3.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/back-arrow.png")));
			label_3.setBounds(6, 6, 62, 22);
		}
		return label_3;
	}

	private void setPanelRecogida(Order order) {
		inicializarModelos(order);
		wk = null;
		wk = new GetWorkingPlan().getByOrder(order);
		System.out.println("Order: " + modeloRecogido.getOrder().getIdPedido() + " Almacenero: " + almacenero.getId());
		if (wk == null) {
			System.out.println("WK IS NULL");
			System.out.println(
					"Order: " + modeloRecogido.getOrder().getIdPedido() + " Almacenero: " + almacenero.getId());
			wk = new WorkingPlan(modeloRecogido.getOrder(), almacenero);
			new AddWorkingPlan(almacenero, order);
			wk = new GetWorkingPlan().getByOrder(order);
			System.out.println("WK ID:" + wk.getID());
		}
		System.out.println("WK ID:" + wk.getID());
		updateTablasRecogida(order);
		checkForIncidences(wk);
	}

	private void inicializarModelos(Order order) {
		Object[] columnNames = { "ID PRODUCTO", "PASILLO", "LADO", "POSICIÓN", "ALTURA", "CANTIDAD" };
		modeloPorRecoger = new ModeloTablaNoEditable(columnNames, 0, order);
		modeloRecogido = new ModeloTablaNoEditable(columnNames, 0, order);
		this.getLabelNArticulos().setText(Integer.toString(order.size()));
		this.getLabelEstado().setText(String.valueOf(order.getEstado()));
		this.getLblNPedido().setText(order.getIdPedido());
		updateTablasRecogida(order);
		getTablaCompleta().setModel(modeloPorRecoger);
		getTablaParcial().setModel(modeloRecogido);
	}

	private void updateTablasRecogida(Order order) {
		modeloPorRecoger.removeAll();
		modeloRecogido.removeAll();
		for (OrderDetail od : order.getProductos()) {
			System.out.println(od.getProducto().getIDProducto()+" "+od.collected);
			if (!od.collected)
				modeloPorRecoger.addRow(od);
			else
				modeloRecogido.addRow(od);
		}
	}

	private void setPanelEmpaquetado(Order order) {
		Object[] columnNames = { "ID PRODUCTO", "PASILLO", "LADO", "POSICIÓN", "ALTURA", "CANTIDAD" };
		modeloPorRecoger = new ModeloTablaNoEditable(columnNames, 0, order);
		updateTablasRecogida(order);
		tablaResumen.setModel(modeloNoProcesado);
		this.order = order;
		getTxtNombre().setText(order.getCliente().getName());
		getTxtApellidos().setText(order.getCliente().getSurname());
		getTxtDNI().setText(order.getCliente().getDni());
		getTxtDireccion().setText(order.getCliente().getAddress().toString());
	}

	private void cargarVentalaListaNoProcesado() {
		Object[] columnNames = { "ID PEDIDO", "Nº ARTÍCULOS", "FECHA PEDIDO", "ESTADO" };
		List<Order> aux = null;
		;
		try {
			aux = new GetOrders().getOrdersByStatus(Status.PENDIENTE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			aux.addAll(new GetOrders().getOrdersByStatus(Status.INCIDENCIA));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modeloNoProcesado = new ModeloTablaNoEditableNoProcesado(columnNames, 0, aux);
		for (Order o : aux) {
			modeloNoProcesado.addRow(o);
		}
		getTableListaNoProcesados().setModel(modeloNoProcesado);
	}

	private void cargarVentanaParaEmpaquetar() {
		Object[] columnNames = { "ID PEDIDO", "Nº ARTÍCULOS", "FECHA PEDIDO", "ESTADO" };
		List<Order> aux = null;
		;
		try {
			aux = new GetOrders().getOrdersByStatus(Status.EMPAQUETANDO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modeloNoProcesado = new ModeloTablaNoEditableNoProcesado(columnNames, 0, aux);
		for (Order o : aux) {
			modeloNoProcesado.addRow(o);
		}
		getTableParaEmpaquetar().setModel(modeloNoProcesado);
		if (modeloNoProcesado.getOrders().size() == 0)
			getBtnEmpaquetar().setEnabled(false);
		else
			getBtnEmpaquetar().setEnabled(true);
	}

	private JTable getTablaResumen() {
		if (tablaResumen == null) {
			tablaResumen = new JTable();
			tablaResumen.setFocusTraversalKeysEnabled(false);
			tablaResumen.setFocusable(false);
			tablaResumen.setRowSelectionAllowed(false);
		}
		return tablaResumen;
	}

	private JPanel getEmpaquetado() {
		if (empaquetado == null) {
			empaquetado = new JPanel();
			empaquetado.setLayout(null);

			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Resumen Paquete",
					TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(30, 219, 967, 321);
			empaquetado.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane_2 = new JScrollPane();
			panel_2.add(scrollPane_2);
			scrollPane_2.setViewportView(getTablaResumen());

			JPanel panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(null, "Informaci\u00F3n de Envio", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel_4.setBounds(31, 56, 966, 151);
			empaquetado.add(panel_4);
			panel_4.setLayout(null);

			JLabel lblNombreCliente = new JLabel("Nombre:");
			lblNombreCliente.setBounds(17, 30, 109, 16);
			panel_4.add(lblNombreCliente);

			JLabel lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(288, 30, 109, 16);
			panel_4.add(lblApellidos);

			JLabel lblCalle = new JLabel("Direccion:");
			lblCalle.setBounds(17, 73, 109, 16);
			panel_4.add(lblCalle);

			JLabel lblClienteDNI = new JLabel("DNI:");
			lblClienteDNI.setBounds(628, 30, 72, 16);
			panel_4.add(lblClienteDNI);

			panel_4.add(getTxtNombre());
			panel_4.add(getTxtApellidos());

			panel_4.add(getTxtDNI());
			panel_4.add(getTxtDireccion());
			empaquetado.add(getLabel_1());
			empaquetado.add(getBtnImprimirFactura());
			empaquetado.add(getBtnGenerarEtiquetasEnvio());
			empaquetado.add(getBtnMarcarListo());
		}
		return empaquetado;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(84, 25, 177, 26);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}

	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setEditable(false);
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(364, 25, 252, 26);
		}
		return txtApellidos;
	}

	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setEditable(false);
			txtDNI.setColumns(10);
			txtDNI.setBounds(668, 25, 177, 26);
		}
		return txtDNI;
	}

	private JTextField getTxtDireccion() {
		if (txtDireccion == null) {
			txtDireccion = new JTextField();
			txtDireccion.setEditable(false);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(94, 68, 522, 26);
		}
		return txtDireccion;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Atrás");
			label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) contentPane.getLayout()).show(contentPane, "lista-empaquetar");
				}
			});
			label_1.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/back-arrow.png")));
			label_1.setBounds(6, 6, 62, 22);
		}
		return label_1;
	}

	private JButton getBtnImprimirFactura() {
		if (btnImprimirFactura == null) {
			btnImprimirFactura = new JButton("Imprimir Factura");
			btnImprimirFactura.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloRecogido.getOrder().printBill();
				}
			});
			btnImprimirFactura.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnImprimirFactura.setHorizontalTextPosition(SwingConstants.CENTER);
			btnImprimirFactura.setIcon(new ImageIcon(VentanaPrincipalAlmacenero.class
					.getResource("/es/uniovi/ips/myshop/igunew/img/printer-small.png")));
			btnImprimirFactura.setBounds(670, 552, 157, 101);
		}
		return btnImprimirFactura;
	}

	private JButton getBtnGenerarEtiquetasEnvio() {
		if (btnGenerarEtiquetasEnvio == null) {
			btnGenerarEtiquetasEnvio = new JButton("Generar Etiquetas Envio");
			btnGenerarEtiquetasEnvio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloRecogido.getOrder().printShippingInfo();
				}
			});
			btnGenerarEtiquetasEnvio.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/dhl.png")));
			btnGenerarEtiquetasEnvio.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnGenerarEtiquetasEnvio.setHorizontalTextPosition(SwingConstants.CENTER);
			btnGenerarEtiquetasEnvio.setBounds(840, 552, 157, 101);
		}
		return btnGenerarEtiquetasEnvio;
	}

	private JButton getBtnGenerarIncidencia() {
		if (btnGenerarIncidencia == null) {
			btnGenerarIncidencia = new JButton("Generar Incidencia");
			btnGenerarIncidencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new AddNewIncidence(wk, JOptionPane.showInputDialog("Escriba la descripción de la incidencia:"));
					try {
						new ModifyOrderStatus(modeloRecogido.getOrder(), Status.INCIDENCIA);
						checkForIncidences(wk);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnGenerarIncidencia.setHorizontalTextPosition(SwingConstants.RIGHT);
			btnGenerarIncidencia.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/warning.png")));
			btnGenerarIncidencia.setBounds(844, 65, 170, 29);
		}
		return btnGenerarIncidencia;
	}

	private void checkForIncidences(WorkingPlan wk) {
		if (modeloRecogido.getOrder().getEstado() == Status.INCIDENCIA) {
			getBtnIniciarProcesoDe().setEnabled(false);
			getBtnGenerarIncidencia().setText("Anular Incidencia");
			for (ActionListener al : getBtnGenerarIncidencia().getActionListeners()) {
				getBtnGenerarIncidencia().removeActionListener(al);
			}
			getBtnGenerarIncidencia().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarIncidencia();
				}
			});
		} else {
			getBtnIniciarProcesoDe().setEnabled(true);
			getBtnGenerarIncidencia().setText("Generar Incidencia");
			for (ActionListener al : getBtnGenerarIncidencia().getActionListeners()) {
				getBtnGenerarIncidencia().removeActionListener(al);
			}
			getBtnGenerarIncidencia().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aniadirIncidencia();
				}
			});
		}
	}

	private void eliminarIncidencia() {
		new RemoveIncidence(wk.getIncidence());
		try {
			new ModifyOrderStatus(modeloRecogido.getOrder(), Status.PENDIENTE);
			modeloRecogido.getOrder().setStatus(Status.PENDIENTE);
			cargarVentalaListaNoProcesado();
			setPanelRecogida(modeloRecogido.getOrder());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkForIncidences(wk);
	}

	public void aniadirIncidencia() {
		new AddNewIncidence(wk, JOptionPane.showInputDialog("Escriba la descripción de la incidencia:"));
		try {
			new ModifyOrderStatus(modeloRecogido.getOrder(), Status.INCIDENCIA);
			modeloRecogido.getOrder().setStatus(Status.INCIDENCIA);
			cargarVentalaListaNoProcesado();
			setPanelRecogida(modeloRecogido.getOrder());
			checkForIncidences(wk);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private JPanel getListaRegogida() {
		if (ListaRegogida == null) {
			ListaRegogida = new JPanel();
			ListaRegogida.setLayout(null);
			ListaRegogida.add(getLabel_2());
			ListaRegogida.add(getScrollPane_3());
			ListaRegogida.add(getBtnRecoger());
		}
		return ListaRegogida;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Atrás");
			label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) contentPane.getLayout()).show(contentPane, "inicio");
				}
			});
			label_2.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/back-arrow.png")));
			label_2.setBounds(6, 6, 62, 22);
		}
		return label_2;
	}

	private JScrollPane getScrollPane_3() {
		if (scrollPane_3 == null) {
			scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(6, 53, 1024, 495);
			scrollPane_3.setViewportView(getTableListaNoProcesados());
		}
		return scrollPane_3;
	}

	private JTable getTableListaNoProcesados() {
		if (tableListaNoProcesados == null) {
			tableListaNoProcesados = new JTable();
			tableListaNoProcesados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tableListaNoProcesados;
	}

	private JButton getBtnRecoger() {
		if (btnRecoger == null) {
			btnRecoger = new JButton("Recoger");
			btnRecoger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getTableListaNoProcesados().getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(contentPane, "Primero Selecciona una fila de la tabla.");
					}
					Order aux = modeloNoProcesado.getOrderAtRow(getTableListaNoProcesados().getSelectedRow());
					setPanelRecogida(aux);
					((CardLayout) contentPane.getLayout()).show(contentPane, "recogida");

				}
			});
			btnRecoger.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnRecoger.setHorizontalTextPosition(SwingConstants.CENTER);
			btnRecoger.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/barcode-big.png")));
			btnRecoger.setBounds(893, 571, 137, 94);
		}
		return btnRecoger;
	}

	private JPanel getListaEmpaquetado() {
		if (ListaEmpaquetado == null) {
			ListaEmpaquetado = new JPanel();
			ListaEmpaquetado.setLayout(null);
			ListaEmpaquetado.add(getScrollPane_4());
			ListaEmpaquetado.add(getBtnEmpaquetar());
			ListaEmpaquetado.add(getLabel_4());
		}
		return ListaEmpaquetado;
	}

	private JScrollPane getScrollPane_4() {
		if (scrollPane_4 == null) {
			scrollPane_4 = new JScrollPane();
			scrollPane_4.setBounds(6, 61, 1024, 495);
			scrollPane_4.setViewportView(getTableParaEmpaquetar());
		}
		return scrollPane_4;
	}

	private JButton getBtnEmpaquetar() {
		if (btnEmpaquetar == null) {
			btnEmpaquetar = new JButton("Empaquetar");
			btnEmpaquetar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Order aux = modeloNoProcesado.getOrderAtRow(getTableParaEmpaquetar().getSelectedRow());
					setPanelEmpaquetado(aux);
					((CardLayout) contentPane.getLayout()).show(contentPane, "empaquetado");
				}
			});
			btnEmpaquetar.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/dhl.png")));
			btnEmpaquetar.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnEmpaquetar.setHorizontalTextPosition(SwingConstants.CENTER);
			btnEmpaquetar.setBounds(893, 568, 137, 94);
		}
		return btnEmpaquetar;
	}

	private JTable getTableParaEmpaquetar() {
		if (tableParaEmpaquetar == null) {
			tableParaEmpaquetar = new JTable();
		}
		return tableParaEmpaquetar;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Atrás");
			label_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label_4.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) contentPane.getLayout()).show(contentPane, "inicio");
				}
			});
			label_4.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/back-arrow.png")));
			label_4.setBounds(6, 6, 62, 22);
		}
		return label_4;
	}

	private JButton getBtnMarcarListo() {
		if (btnMarcarListo == null) {
			btnMarcarListo = new JButton("Marcar Listo");
			btnMarcarListo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new ModifyOrderStatus(order, Status.LISTO);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnMarcarListo.setIcon(new ImageIcon(
					VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/tick-small.png")));
			btnMarcarListo.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnMarcarListo.setHorizontalTextPosition(SwingConstants.CENTER);
			btnMarcarListo.setBounds(501, 552, 157, 101);
		}
		return btnMarcarListo;
	}
}
