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

import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.connectors.GetWarehouseKeepers;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.Order.Status;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.people.WharehouseKeeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JLabel label_1;
	private JLabel lblEstado;
	private JLabel label_2;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getInicio(), "inicio");
		contentPane.add(getPanel_2_1(), "recogida");
	}

	private JLabel getImgCarrito() {
		if (imgCarrito == null) {
			imgCarrito = new JLabel("");
			imgCarrito.setBounds(43, 6, 128, 128);
			imgCarrito.setHorizontalAlignment(SwingConstants.CENTER);
			imgCarrito.setIcon(
					new ImageIcon(VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/recoger-productos.png")));
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
					String imp = JOptionPane.showInputDialog(contentPane, "Introduzca su id de empleado:");
					//WharehouseKeeper wk = new GetWarehouseKeepers().getByID(Integer.parseInt(imp));
					if (/*new GetWarehouseKeepers().isOccupies(wk)*/true) {
						int opt = JOptionPane.showConfirmDialog(contentPane,
								"Debe de terminar de recoger su pedido anterior antes de seleccionar otro nuevo.",
								"Aviso", JOptionPane.OK_CANCEL_OPTION);
						if (opt == 0) {
							((CardLayout) contentPane.getLayout()).show(contentPane, "recogida");
							try {
								setPanelRecogida(new GetOrders().getAllOrders().get(1)/*new GetWarehouseKeepers().getCurrentOrder()*/);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
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
					// Call to the next window.
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
			label.setIcon(
					new ImageIcon(VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/empaquetar-pedido.png")));
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
			recogida.add(getLabel_1());
			recogida.add(getLblEstado());
			recogida.add(getLabel_2());
			recogida.add(getScrollPane());
			recogida.add(getScrollPane_1());
			recogida.add(getLblProductosEnEl());
			recogida.add(getLblProductosRecogidos());
			recogida.add(getBtnIniciarProcesoDe());
			recogida.add(getLabel_3());
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

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("00001");
			label_1.setBounds(694, 20, 122, 16);
		}
		return label_1;
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

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("00001");
			label_2.setBounds(892, 20, 122, 16);
		}
		return label_2;
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
			TablaCompleta.setRowSelectionAllowed(false);
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
						String message = "Diríjase a: " + od.getProducto().getLocalizacion().getPasillo() + " "
								+ od.getProducto().getLocalizacion().getLado() + " "
								+ od.getProducto().getLocalizacion().getPosicion() + " "
								+ od.getProducto().getLocalizacion().getAltura();
						String id = JOptionPane.showInputDialog(contentPane, message);
						if (od.getProducto().getIDProducto().equals(id)) {
							// Do something...
						} else {
							message = "ERROR ESCANEANDO CÓDIGO Diríjase a: "
									+ od.getProducto().getLocalizacion().getPasillo() + " "
									+ od.getProducto().getLocalizacion().getLado() + " "
									+ od.getProducto().getLocalizacion().getPosicion() + " "
									+ od.getProducto().getLocalizacion().getAltura();
							id = JOptionPane.showInputDialog(contentPane, message);
						}
					}
				}
			});
			btnIniciarProcesoDe.setHorizontalTextPosition(SwingConstants.LEADING);
			btnIniciarProcesoDe
					.setIcon(new ImageIcon(VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/barcode-icon.png")));
			btnIniciarProcesoDe.setBounds(258, 65, 254, 29);
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
					((CardLayout) contentPane.getLayout()).show(contentPane, "inicio");
				}
			});
			label_3.setIcon(new ImageIcon(VentanaPrincipalAlmacenero.class.getResource("/es/uniovi/ips/myshop/igunew/img/back-arrow.png")));
			label_3.setBounds(6, 6, 62, 22);
		}
		return label_3;
	}

	private void setPanelRecogida(Order order) {
		Object[] columnNames = { "ID PRODUCTO", "PASILLO", "LADO", "POSICIÓN", "ALTURA" };
		modeloPorRecoger= new ModeloTablaNoEditable(columnNames, 0);
		modeloRecogido = new ModeloTablaNoEditable(columnNames, 0);
		this.getLblNDeArtculos().setText(Integer.toString(order.size()));
		this.getLblEstado().setText(String.valueOf(order.getEstado()));
		this.getLblNPedido().setText(order.getIdPedido());
		for(OrderDetail od : order.getProductos()) {
			if(!od.collected)
				modeloPorRecoger.addRow(od);
			else
				modeloRecogido.addRow(od);
		}
		
		getTablaCompleta().setModel(modeloPorRecoger);
		getTablaParcial().setModel(modeloRecogido);
	}
}
