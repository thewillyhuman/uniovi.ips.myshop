package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import org.jvnet.substance.SubstanceLookAndFeel;

import es.uniovi.ips.myshop.connectors.AddCustomer;
import es.uniovi.ips.myshop.connectors.AddOrder;
import es.uniovi.ips.myshop.connectors.GetCustomers;
import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.people.Address;
import es.uniovi.ips.myshop.model.people.Customer;
import es.uniovi.ips.myshop.model.product.Inventory;
import es.uniovi.ips.myshop.model.product.Product;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JTextField;


public class VentanaProductosYCarrito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnCarrito;
	private JPanel pnProducts;
	private JScrollPane scpProducts;
	private JPanel pnCardBotones;
	private JScrollPane scpCarrito;
	private JPanel pnBotones;
	private JButton btAceptar;
	
	private Map<String,Integer> mapaProductos = new HashMap<String,Integer>();
	private JPanel pnCarritoDescripcion;
	private JLabel lbIndiceProducto;
	private JLabel lbIndicePrecio;
	private JLabel lbIndiceCantidad;
	private JLabel lbIndiceTotal;
	private JPanel pnProductosYCarrito;
	private JLabel lblNombre;
	private JTextField txNombre;
	private JLabel lblApellidos;
	private JTextField txApellidos;
	private JLabel lblDni;
	private JTextField txDni;
	private JButton btnRealizarPedido;
	private JLabel lblCalle;
	private JTextField txCalle;
	private JLabel lblEstado;
	private JTextField txEstado;
	private JLabel lblCiudad;
	private JTextField txCiudad;
	private JLabel lblCodigoZip;
	private JTextField txCodigoZip;
	private JPanel pnDatos;
	private JPanel pnBotonRecogida;
	private JPanel pnListaProductos;

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
					VentanaProductosYCarrito frame = new VentanaProductosYCarrito();
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
	public VentanaProductosYCarrito() throws SQLException {
		setTitle("Ventana de Products");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnProductosYCarrito(), "name_126431304774398");
		inicializarMap();
		cargarProductsEnLista();
		cargarProductosEnCarrito();
	}

	private JPanel getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new JPanel();
			pnCarrito.setLayout(new BorderLayout(0, 0));
			pnCarrito.add(getScpCarrito(), BorderLayout.CENTER);
			pnCarrito.add(getPnCarritoDescripcion(), BorderLayout.NORTH);
		}
		return pnCarrito;
	}
	private JPanel getPnProducts() {
		if (pnProducts == null) {
			pnProducts = new JPanel();
			pnProducts.setLayout(new CardLayout(0, 0));
			pnProducts.add(getScpProducts(), "name_176122379642644");
			pnProducts.add(getPnDatos(), "name_176436634060139");
		}
		return pnProducts;
	}
	private JScrollPane getScpProducts() {
		if (scpProducts == null) {
			scpProducts = new JScrollPane();
			scpProducts.setViewportView(getPnListaProductos());
		}
		return scpProducts;
	}
	private JPanel getPnCardBotones() {
		if (pnCardBotones == null) {
			pnCardBotones = new JPanel();
			pnCardBotones.setLayout(new CardLayout(0, 0));
			pnCardBotones.add(getPnBotones(), "name_176322717301298");
			pnCardBotones.add(getPnBotonRecogida(), "name_176344553122138");
		}
		return pnCardBotones;
	}
	private JScrollPane getScpCarrito() {
		if (scpCarrito == null) {
			scpCarrito = new JScrollPane();
		}
		return scpCarrito;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnBotones.add(getBtAceptar());
		}
		return pnBotones;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaDatosCliente();
				}
			});
			btAceptar.setEnabled(false);
		}
		return btAceptar;
	}
	
	private void mostrarVentanaDatosCliente(){
		getScpProducts().setVisible(false);
		getPnBotones().setVisible(false);
		getPnBotonRecogida().setVisible(true);
		getPnDatos().setVisible(true);
	}
	
	private void cargarProductsEnLista() throws SQLException {
		Container cont = new Container();
		for (Product c :  new Inventory().getAllProducts()) {
			ProductoListaPanel aux = new ProductoListaPanel(c);
			aux.getBtAniadir().setActionCommand(c.getIDProducto());
			aux.getBtAniadir().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						aniadirAlistaProductos(c);
						getBtAceptar().setEnabled(true);
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			aux.getBtBorrar().setActionCommand(c.getIDProducto());
			aux.getBtBorrar().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if(mapaProductos.get(c.getIDProducto())>0)
							borrarDelistaProductos(c);
						else{
							aux.getBtBorrar().setEnabled(false);
						}
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			aux.setPreferredSize(new Dimension(getScpProducts().getWidth(), 233));
			
			cont.add(aux);
		}

		cont.setLayout(new GridLayout( new Inventory().getAllProducts().size(), 1));

		revalidate();
		repaint();

		getScpProducts().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	
	private void cargarProductosEnCarrito() throws NumberFormatException, SQLException {
		Container cont = new Container();
		
		for (Product c :  new Inventory().getAllProducts()) {
			if( mapaProductos.get(c.getIDProducto())> 0){
				ProductoEnCarritoPanel aux = new ProductoEnCarritoPanel(c);
				aux.getSpCantidad().setValue(mapaProductos.get(c.getIDProducto()));
				int cantidad = Integer.parseInt(aux.getSpCantidad().getValue().toString());
				Double precio = Double.parseDouble(aux.getLbPrecio().getText());
				aux.getLbTotal().setText(cantidad*precio+"");
				int anterior = Integer.parseInt(aux.getSpCantidad().getValue().toString());
				
				aux.getSpCantidad().addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						try {
							int actual = Integer.parseInt(aux.getSpCantidad().getValue().toString());
							if(actual - anterior >0){
								aniadirAlistaProductos(c);
							}
							else{
								borrarDelistaProductos(c);
							}
						} catch (NumberFormatException | SQLException e1) {
							e1.printStackTrace();
						}
						aux.getLbTotal().setText(Integer.parseInt(aux.getSpCantidad().getValue().toString())
								* Double.parseDouble(aux.getLbPrecio().getText().toString()) + "");
					}
				});
				aux.setPreferredSize(new Dimension(getScpCarrito().getWidth(), 233));
				cont.add(aux);
			}
		}

		cont.setLayout(new GridLayout(mapaProductos.size(), 1));

		revalidate();
		repaint();

		getScpCarrito().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void aniadirAlistaProductos(Product p) throws NumberFormatException, SQLException{
		mapaProductos.put(p.getIDProducto(),mapaProductos.get(p.getIDProducto())+1);
		cargarProductosEnCarrito();
	}
	
	private void borrarDelistaProductos(Product p) throws NumberFormatException, SQLException{
		mapaProductos.put(p.getIDProducto(),mapaProductos.get(p.getIDProducto())-1);
		cargarProductosEnCarrito();
	}
	
	private JPanel getPnCarritoDescripcion() {
		if (pnCarritoDescripcion == null) {
			pnCarritoDescripcion = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnCarritoDescripcion.getLayout();
			flowLayout.setHgap(75);
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnCarritoDescripcion.add(getLbIndiceProducto());
			pnCarritoDescripcion.add(getLbIndicePrecio());
			pnCarritoDescripcion.add(getLbIndiceCantidad());
			pnCarritoDescripcion.add(getLbIndiceTotal());
		}
		return pnCarritoDescripcion;
	}
	private JLabel getLbIndiceProducto() {
		if (lbIndiceProducto == null) {
			lbIndiceProducto = new JLabel("Producto");
		}
		return lbIndiceProducto;
	}
	private JLabel getLbIndicePrecio() {
		if (lbIndicePrecio == null) {
			lbIndicePrecio = new JLabel("Precio");
		}
		return lbIndicePrecio;
	}
	private JLabel getLbIndiceCantidad() {
		if (lbIndiceCantidad == null) {
			lbIndiceCantidad = new JLabel("Cantidad");
		}
		return lbIndiceCantidad;
	}
	private JLabel getLbIndiceTotal() {
		if (lbIndiceTotal == null) {
			lbIndiceTotal = new JLabel("Total");
		}
		return lbIndiceTotal;
	}
	
	private void inicializarMap() throws SQLException{
		for(Product p :  new Inventory().getAllProducts()){
			mapaProductos.put(p.getIDProducto(), 0);
		}		
	}
	private JPanel getPnProductosYCarrito() {
		if (pnProductosYCarrito == null) {
			pnProductosYCarrito = new JPanel();
			pnProductosYCarrito.setLayout(new BorderLayout(0, 0));
			pnProductosYCarrito.add(getPnCarrito(), BorderLayout.EAST);
			pnProductosYCarrito.add(getPnProducts(), BorderLayout.CENTER);
			pnProductosYCarrito.add(getPnCardBotones(), BorderLayout.SOUTH);
		}
		return pnProductosYCarrito;
	}
	
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(10, 14, 55, 14);
		}
		return lblNombre;
	}
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setBounds(104, 11, 86, 20);
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos: ");
			lblApellidos.setBounds(222, 14, 55, 14);
		}
		return lblApellidos;
	}
	private JTextField getTxApellidos() {
		if (txApellidos == null) {
			txApellidos = new JTextField();
			txApellidos.setBounds(298, 11, 86, 20);
			txApellidos.setColumns(10);
		}
		return txApellidos;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(10, 52, 46, 14);
		}
		return lblDni;
	}
	private JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setBounds(104, 49, 86, 20);
			txDni.setColumns(10);
		}
		return txDni;
	}
	private JButton getBtnRealizarPedido() {
		if (btnRealizarPedido == null) {
			btnRealizarPedido = new JButton("Realizar Pedido");
			btnRealizarPedido.setBounds(288, 177, 125, 23);
			VentanaProductosYCarrito v = this;
			btnRealizarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comprobacion()){
						Address a = new Address(getTxCalle().getText(), getTxCiudad().getText(), getTxEstado().getText(), getTxCodigoZip().getText());
						Customer c = new Customer(getTxNombre().getText(), getTxApellidos().getText(), getTxDni().getText(), a);
						Order o = new Order(c, new Date());
						try {
							for(Product p : new Inventory().getAllProducts()){
								if(mapaProductos.get(p.getIDProducto())>0){
									o.addProduct(p, mapaProductos.get(p.getIDProducto()));
								}
							}
							mostrarVentanaInicio();
							new AddOrder(o);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}		
					else{
						JOptionPane.showMessageDialog(v, "No deje ningun "
								+ "campo sin rellenar \npor favor");
					}
				}
			});
		}
		return btnRealizarPedido;
	}
	
	private void mostrarVentanaInicio() throws NumberFormatException, SQLException{
		inicializarMap();
		cargarProductosEnCarrito();
		getPnBotonRecogida().setVisible(false);
		getPnDatos().setVisible(false);
		getScpProducts().setVisible(true);
		getPnBotones().setVisible(true);
		getTxNombre().setText("");
		getTxApellidos().setText("");
		getTxCalle().setText("");
		getTxCiudad().setText("");
		getTxDni().setText("");
		getTxEstado().setText("");
		getTxCodigoZip().setText("");
		
	}
	
	private boolean comprobacion(){
		if(getTxNombre().getText().equals("") || 
				getTxApellidos().getText().equals("")|| 
				getTxCalle().getText().equals("")||
				getTxCiudad().getText().equals("")||
				getTxCodigoZip().getText().equals("")|| 
				getTxDni().getText().equals("")||
				getTxEstado().getText().equals("")){
			return false;
		}
		return true;
	}
	private JLabel getLblCalle() {
		if (lblCalle == null) {
			lblCalle = new JLabel("Calle: ");
			lblCalle.setBounds(76, 91, 46, 14);
		}
		return lblCalle;
	}
	private JTextField getTxCalle() {
		if (txCalle == null) {
			txCalle = new JTextField();
			txCalle.setBounds(174, 88, 86, 20);
			txCalle.setColumns(10);
		}
		return txCalle;
	}
	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado: ");
			lblEstado.setBounds(76, 119, 46, 14);
		}
		return lblEstado;
	}
	private JTextField getTxEstado() {
		if (txEstado == null) {
			txEstado = new JTextField();
			txEstado.setBounds(174, 116, 86, 20);
			txEstado.setColumns(10);
		}
		return txEstado;
	}
	private JLabel getLblCiudad() {
		if (lblCiudad == null) {
			lblCiudad = new JLabel("Ciudad: ");
			lblCiudad.setBounds(76, 153, 46, 14);
		}
		return lblCiudad;
	}
	private JTextField getTxCiudad() {
		if (txCiudad == null) {
			txCiudad = new JTextField();
			txCiudad.setBounds(174, 150, 86, 20);
			txCiudad.setColumns(10);
		}
		return txCiudad;
	}
	private JLabel getLblCodigoZip() {
		if (lblCodigoZip == null) {
			lblCodigoZip = new JLabel("Codigo ZIP: ");
			lblCodigoZip.setBounds(76, 181, 69, 14);
		}
		return lblCodigoZip;
	}
	private JTextField getTxCodigoZip() {
		if (txCodigoZip == null) {
			txCodigoZip = new JTextField();
			txCodigoZip.setBounds(174, 178, 86, 20);
			txCodigoZip.setColumns(10);
		}
		return txCodigoZip;
	}
	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setLayout(null);
			pnDatos.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20));
			pnDatos.add(getLblNombre());
			pnDatos.add(getTxNombre());
			pnDatos.add(getLblApellidos());
			pnDatos.add(getTxApellidos());
			pnDatos.add(getLblDni());
			pnDatos.add(getTxDni());
			pnDatos.add(getLblCalle());
			pnDatos.add(getTxCalle());
			pnDatos.add(getLblEstado());
			pnDatos.add(getTxEstado());
			pnDatos.add(getLblCiudad());
			pnDatos.add(getTxCiudad());
			pnDatos.add(getLblCodigoZip());
			pnDatos.add(getTxCodigoZip());
		}
		return pnDatos;
	}
	private JPanel getPnBotonRecogida() {
		if (pnBotonRecogida == null) {
			pnBotonRecogida = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotonRecogida.getLayout();
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotonRecogida.add(getBtnRealizarPedido());
		}
		return pnBotonRecogida;
	}
	private JPanel getPnListaProductos() {
		if (pnListaProductos == null) {
			pnListaProductos = new JPanel();
			pnListaProductos.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnListaProductos;
	}
}
