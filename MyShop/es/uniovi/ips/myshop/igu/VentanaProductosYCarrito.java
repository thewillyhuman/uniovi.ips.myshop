package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.Container;

import org.jvnet.substance.SubstanceLookAndFeel;

import es.uniovi.ips.myshop.model.product.Product;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;


public class VentanaProductosYCarrito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnCarrito;
	private JPanel pnProducts;
	private JScrollPane scpProducts;
	private JPanel pnDescripcion;
	private JScrollPane scrollPane;
	private JTextArea txaCarrito;
	private JPanel pnBotones;
	private JButton btAñadir;
	private JButton btBorrar;
	private JButton btAceptar;
	
	private Map<Product,Integer> mapaProductos = new HashMap<Product,Integer>();
	private List<Product> listaProductos = new ArrayList<Product>();

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
	 */
	public VentanaProductosYCarrito() {
		setTitle("Ventana de Products");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnCarrito(), BorderLayout.CENTER);
		contentPane.add(getPnProducts(), BorderLayout.WEST);
		contentPane.add(getPnDescripcion(), BorderLayout.SOUTH);
		actualizarCarrito();
		cargarProductsEnLista();
	}

	private JPanel getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new JPanel();
			pnCarrito.setLayout(new BorderLayout(0, 0));
			pnCarrito.add(getScrollPane(), BorderLayout.CENTER);
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
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTxaCarrito());
		}
		return scrollPane;
	}
	private JTextArea getTxaCarrito() {
		if (txaCarrito == null) {
			txaCarrito = new JTextArea();
			txaCarrito.setText("Carrito de compra");
			txaCarrito.setEditable(false);
		}
		return txaCarrito;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnBotones.add(getBtAñadir());
			pnBotones.add(getBtBorrar());
			pnBotones.add(getBtAceptar());
		}
		return pnBotones;
	}
	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir");
		}
		return btAñadir;
	}
	private JButton getBtBorrar() {
		if (btBorrar == null) {
			btBorrar = new JButton("Borrar");
		}
		return btBorrar;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
		}
		return btAceptar;
	}
	
	private void cargarProductsEnLista() {
		Container cont = new Container();

		for (Product c : listaProductos) {
			ProductoListaPanel aux = new ProductoListaPanel(c);
			aux.getBtAñadir().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					añadirAlistaProductos(c);
				}
			});
			aux.getBtBorrar().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					borrarDelistaProductos(c);
				}
			});

			cont.add(aux);
		}

		cont.setLayout(new GridLayout(listaProductos.size(), 1));

		revalidate();
		repaint();

		getScpProducts().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void añadirAlistaProductos(Product p){
		mapaProductos.put(p,mapaProductos.get(p)+1);
		actualizarCarrito();
	}
	
	private void borrarDelistaProductos(Product p){
		mapaProductos.put(p,mapaProductos.get(p)-1);
		actualizarCarrito();
	}
	
	private void actualizarCarrito(){
		StringBuilder sb = new StringBuilder();
		sb.append("Carrito de compra:\n)");
		for(Product p : listaProductos){
			if(mapaProductos.get(p) > 0){
				sb.append(p.getDescripcion() + " Unids: " + mapaProductos.get(p) + "\n");
			}
		}
		getTxaCarrito().setText(sb.toString());
	}
}
