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

import es.uniovi.ips.myshop.connectors.AddOrder;
import es.uniovi.ips.myshop.connectors.GetProducts;
import es.uniovi.ips.myshop.igunew.renderers.CenterRenderer;
import es.uniovi.ips.myshop.igunew.renderers.LeftRenderer;
import es.uniovi.ips.myshop.igunew.renderers.RightRenderer;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.people.Address;
import es.uniovi.ips.myshop.model.people.Customer;
import es.uniovi.ips.myshop.model.product.Product;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipalCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private Order order;
	private ModeloTablaNoEditableProductos modeloProductos;
	private ModeloTablaNoEditableCarrito modeloCarrito;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable tablaProductos;
	private JTable tablaCarrito;
	private JPanel panel_3;
	private JLabel lblTotal;
	private JLabel txtTotal;
	private JButton btnAadirACarrito;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblDni;
	private JLabel lblCalle;
	private JLabel lblCiudad;
	private JLabel lblProvincia;
	private JLabel lblCdigoPostal;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTextField txtCalle;
	private JTextField txtCiudad;
	private JTextField txtProvincia;
	private JTextField txtCP;
	private JButton btnFinalizarCompra;
	private JButton btnModificarCantidad;
	private JButton bntDelete;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipalCliente() {
		setResizable(false);
		setTitle("MyShop");
		setMinimumSize(new Dimension(1046, 703));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		contentPane.add(getPanel_1());
		contentPane.add(getPanel_3());
		contentPane.add(getLblTotal());
		contentPane.add(getTxtTotal());
		contentPane.add(getBtnFinalizarCompra());
		contentPane.add(getBtnAadirACarrito());
		contentPane.add(getBtnModificarCantidad());
		contentPane.add(getBntDelete());
		updateListaProductos();
		updateListaCarrito();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalCliente frame = new VentanaPrincipalCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Productos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(6, 6, 585, 437);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Carrito", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(603, 6, 437, 237);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return panel_1;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaProductos());
		}
		return scrollPane;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTablaCarrito());
		}
		return scrollPane_1;
	}

	private JTable getTablaProductos() {
		if (tablaProductos == null) {
			tablaProductos = new JTable();
			tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tablaProductos;
	}

	private JTable getTablaCarrito() {
		if (tablaCarrito == null) {
			tablaCarrito = new JTable();
			tablaCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tablaCarrito;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "Datos de Env\u00EDo y Facturaci\u00F3n", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel_3.setBounds(6, 455, 1034, 145);
			panel_3.setLayout(null);
			panel_3.add(getLblNombre());
			panel_3.add(getLblApellidos());
			panel_3.add(getLblDni());
			panel_3.add(getLblCalle());
			panel_3.add(getLblCiudad());
			panel_3.add(getLblProvincia());
			panel_3.add(getLblCdigoPostal());
			panel_3.add(getTxtNombre());
			panel_3.add(getTxtApellidos());
			panel_3.add(getTxtDNI());
			panel_3.add(getTxtCalle());
			panel_3.add(getTxtCiudad());
			panel_3.add(getTxtProvincia());
			panel_3.add(getTxtCP());
		}
		return panel_3;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total:");
			lblTotal.setVerticalAlignment(SwingConstants.BOTTOM);
			lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblTotal.setBounds(603, 362, 272, 78);
		}
		return lblTotal;
	}

	private JLabel getTxtTotal() {
		if (txtTotal == null) {
			txtTotal = new JLabel("0.00");
			txtTotal.setVerticalAlignment(SwingConstants.BOTTOM);
			txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			txtTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			txtTotal.setBounds(887, 365, 153, 78);
		}
		return txtTotal;
	}

	private JButton getBtnAadirACarrito() {
		if (btnAadirACarrito == null) {
			btnAadirACarrito = new JButton("Añadir a Carrito");
			btnAadirACarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getTablaProductos().getSelectedRow() == -1)
						JOptionPane.showMessageDialog(contentPane, "Primero debe seleccionar un producto.");
					else {
						Product p = modeloProductos.getProductAtRow(getTablaProductos().getSelectedRow());
						String imp = JOptionPane.showInputDialog(contentPane,
								"Seleccione la catidad de artículos que desea para el producto selecionado:");
						OrderDetail od = new OrderDetail(p, Integer.parseInt(imp));
						try {
							modeloCarrito.addRow(od);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(contentPane,
									"Este producto ya está en el carrito, puede modificar su cantidad.");
						}
					}
					updateTotal();
				}
			});
			btnAadirACarrito.setBounds(613, 255, 133, 64);
		}
		return btnAadirACarrito;
	}
	
	private void updateTotal() {
		getTxtTotal().setText(Double.toString(modeloCarrito.getTotal()));
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(41, 26, 61, 16);
		}
		return lblNombre;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidos.setBounds(285, 26, 74, 16);
		}
		return lblApellidos;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDni.setBounds(645, 26, 61, 16);
		}
		return lblDni;
	}

	private JLabel getLblCalle() {
		if (lblCalle == null) {
			lblCalle = new JLabel("Calle:");
			lblCalle.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCalle.setBounds(41, 66, 61, 16);
		}
		return lblCalle;
	}

	private JLabel getLblCiudad() {
		if (lblCiudad == null) {
			lblCiudad = new JLabel("Ciudad:");
			lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCiudad.setBounds(479, 66, 61, 16);
		}
		return lblCiudad;
	}

	private JLabel getLblProvincia() {
		if (lblProvincia == null) {
			lblProvincia = new JLabel("Provincia:");
			lblProvincia.setHorizontalAlignment(SwingConstants.RIGHT);
			lblProvincia.setBounds(694, 66, 74, 16);
		}
		return lblProvincia;
	}

	private JLabel getLblCdigoPostal() {
		if (lblCdigoPostal == null) {
			lblCdigoPostal = new JLabel("Código Postal:");
			lblCdigoPostal.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCdigoPostal.setBounds(6, 105, 103, 16);
		}
		return lblCdigoPostal;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(114, 21, 174, 26);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}

	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(371, 21, 268, 26);
		}
		return txtApellidos;
	}

	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setColumns(10);
			txtDNI.setBounds(718, 21, 130, 26);
		}
		return txtDNI;
	}

	private JTextField getTxtCalle() {
		if (txtCalle == null) {
			txtCalle = new JTextField();
			txtCalle.setColumns(10);
			txtCalle.setBounds(114, 61, 353, 26);
		}
		return txtCalle;
	}

	private JTextField getTxtCiudad() {
		if (txtCiudad == null) {
			txtCiudad = new JTextField();
			txtCiudad.setColumns(10);
			txtCiudad.setBounds(552, 61, 130, 26);
		}
		return txtCiudad;
	}

	private JTextField getTxtProvincia() {
		if (txtProvincia == null) {
			txtProvincia = new JTextField();
			txtProvincia.setColumns(10);
			txtProvincia.setBounds(780, 61, 130, 26);
		}
		return txtProvincia;
	}

	private JTextField getTxtCP() {
		if (txtCP == null) {
			txtCP = new JTextField();
			txtCP.setColumns(10);
			txtCP.setBounds(121, 100, 130, 26);
		}
		return txtCP;
	}

	private JButton getBtnFinalizarCompra() {
		if (btnFinalizarCompra == null) {
			btnFinalizarCompra = new JButton("Finalizar Compra");
			btnFinalizarCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (validarDatos()) {
						order = new Order(new Customer(getTxtNombre().getText(),
								getTxtApellidos().getText(), getTxtDNI().getText(), new Address(getTxtCalle().getText(),
										getTxtCiudad().getText(), getTxtProvincia().getText(), getTxtCP().getText())),
								new Date());
						for(OrderDetail od : modeloCarrito.getAllRows()) {
							order.addProduct(od.getProducto(), od.getCantidad());
						}

						new AddOrder(order);
						JOptionPane.showMessageDialog(contentPane, "Su pedido se ha realizado con éxito");
						contentPane.hide();
					}
				}
			});
			btnFinalizarCompra.setBounds(895, 612, 145, 64);
		}
		return btnFinalizarCompra;
	}
	
	private boolean validarDatos() {
		return true;
	}

	private void updateListaProductos() {
		Object[] columnNames = { "ID PRODUCTO", "DESCRIPCION", "PRECIO" };
		List<Product> aux = null;
		;
		try {
			aux = new GetProducts().getAllProducts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modeloProductos = new ModeloTablaNoEditableProductos(columnNames, 0, aux);
		for (Product p : aux) {
			modeloProductos.addRow(p);
		}
		getTablaProductos().setModel(modeloProductos);
		tablaProductos.getColumnModel().getColumn(2).setCellRenderer(new RightRenderer().get());
		tablaProductos.getColumnModel().getColumn(0).setCellRenderer(new LeftRenderer().get());
		tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaProductos.getColumnModel().getColumn(0).setMinWidth(10);
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(10);
		tablaProductos.getColumnModel().getColumn(2).setMinWidth(10);
	}

	private void updateListaCarrito() {
		Object[] columnNames = { "ID PRODUCTO", "CANTIDAD", "PRECIO UNIDAD", "PRECIO TOTAL" };
		List<OrderDetail> aux = new ArrayList<OrderDetail>();
		modeloCarrito = new ModeloTablaNoEditableCarrito(columnNames, 0, aux);
		tablaCarrito.setModel(modeloCarrito);
	}

	private JButton getBtnModificarCantidad() {
		if (btnModificarCantidad == null) {
			btnModificarCantidad = new JButton("Modificar Cantidad");
			btnModificarCantidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tablaCarrito.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(contentPane, "Seleccione una fila del carrito");
					} else {
						int nCant = Integer.parseInt(JOptionPane.showInputDialog(contentPane,
								"Introduzca la nueva contidad para este producto:"));
						System.out.println(nCant);
						if (nCant > 0) {
							modeloCarrito.getProductAtRow(tablaCarrito.getSelectedRow()).setQuantity(nCant);
							System.out.println(
									modeloCarrito.getProductAtRow(tablaCarrito.getSelectedRow()).getCantidad());
						}
						if (nCant == 0)
							modeloCarrito.removeRow(tablaCarrito.getSelectedRow());
						modeloCarrito.refresh();

					}
					updateTotal();
				}
			});
			btnModificarCantidad.setBounds(758, 255, 145, 64);
		}
		return btnModificarCantidad;
	}

	private JButton getBntDelete() {
		if (bntDelete == null) {
			bntDelete = new JButton("Eliminar Producto");
			bntDelete.setHorizontalTextPosition(SwingConstants.CENTER);
			bntDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
			bntDelete.setIcon(new ImageIcon(VentanaPrincipalCliente.class.getResource("/es/uniovi/ips/myshop/igunew/img/bin-smal.png")));
			bntDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tablaCarrito.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(contentPane, "Seleccione una fila del carrito");
					} else {
						if (JOptionPane.showConfirmDialog(contentPane,
								"Seguro que quiere eliminar este artículo de su carrito?", "Eliminar Producto?",
								JOptionPane.OK_CANCEL_OPTION, 1) == 0) {
							modeloCarrito.removeRow(modeloCarrito.getProductAtRow(getTablaCarrito().getSelectedRow()));
						}
					}
					updateTotal();
				}
			});
			bntDelete.setBounds(915, 255, 125, 64);
		}
		return bntDelete;
	}
}
