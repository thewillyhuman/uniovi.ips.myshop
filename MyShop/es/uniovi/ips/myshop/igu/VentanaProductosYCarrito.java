package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import org.jvnet.substance.SubstanceLookAndFeel;

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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;


public class VentanaProductosYCarrito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnCarrito;
	private JPanel pnProducts;
	private JScrollPane scpProducts;
	private JPanel pnDescripcion;
	private JScrollPane scpCarrito;
	private JPanel pnBotones;
	private JButton btAceptar;
	
	private Map<Product,Integer> mapaProductos = new HashMap<Product,Integer>();
	private JPanel pnCarritoDescripcion;
	private JLabel lbIndiceProducto;
	private JLabel lbIndicePrecio;
	private JLabel lbIndiceCantidad;
	private JLabel lbIndiceTotal;

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
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnCarrito(), BorderLayout.EAST);
		contentPane.add(getPnProducts(), BorderLayout.CENTER);
		contentPane.add(getPnDescripcion(), BorderLayout.SOUTH);
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
			pnProducts.setLayout(new BorderLayout(0, 0));
			pnProducts.add(getScpProducts(), BorderLayout.WEST);
		}
		return pnProducts;
	}
	private JScrollPane getScpProducts() {
		if (scpProducts == null) {
			scpProducts = new JScrollPane();
		}
		return scpProducts;
	}
	private JPanel getPnDescripcion() {
		if (pnDescripcion == null) {
			pnDescripcion = new JPanel();
			pnDescripcion.setLayout(new GridLayout(0, 1, 0, 0));
			pnDescripcion.add(getPnBotones());
		}
		return pnDescripcion;
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
		}
		return btAceptar;
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
						AniadirAlistaProductos(c);
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
						borrarDelistaProductos(c);
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
			if(mapaProductos.get(c) > 0){
				ProductoEnCarritoPanel aux = new ProductoEnCarritoPanel(c);
				int cantidad = Integer.parseInt(aux.getSpCantidad().getValue().toString());
				int precio = Integer.parseInt(aux.getLbPrecio().getText());
				aux.getLbTotal().setText(cantidad*precio+"");
				
				aux.getSpCantidad().addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						aux.getLbTotal().setText(Integer.parseInt(aux.getSpCantidad().
								getValue().toString())*Integer.parseInt(
										aux.getLbPrecio().toString())+"");
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
	
	private void AniadirAlistaProductos(Product p) throws NumberFormatException, SQLException{
		mapaProductos.put(p,mapaProductos.get(p)+1);
		cargarProductosEnCarrito();
	}
	
	private void borrarDelistaProductos(Product p) throws NumberFormatException, SQLException{
		mapaProductos.put(p,mapaProductos.get(p)-1);
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
			mapaProductos.put(p, 0);
		}
	}
}
