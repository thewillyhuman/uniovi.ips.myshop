package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.Container;

import org.jvnet.substance.SubstanceLookAndFeel;

import es.uniovi.ips.myshop.connectors.GetOrders;
import es.uniovi.ips.myshop.model.order.Order;
import es.uniovi.ips.myshop.model.order.OrderDetail;
import es.uniovi.ips.myshop.model.product.Product;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EmpaquetadoRecogidaPedidos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tbpnEmpaquetadoRecogida;
	private JPanel pnEmpaquetado;
	private JPanel pnRecogida;
	private JScrollPane scpEmpaquetado;
	private JPanel pnBotonesEmpaquetado;
	private JButton btImprimirFactura;
	private JScrollPane scpRecogida;
	private JPanel pnBotonesRecogida;
	private JButton btAceptar;
	private JPanel pnIndiceEmpaquetado;
	private JLabel lblIndicePedidoId;
	private JLabel lblIndiceDireccion;
	private JLabel lblIndiceDni;
	private JLabel lblIndiceCantidad;
	private JLabel lblIndiceFecha;
	private List<Order> listaPedidos = new ArrayList<Order>();
	private JPanel pnIndiceRecogida;
	private JLabel lblRecogidaPedidoId;
	private JLabel lblRecogidaPasillo;
	private JLabel lblRecogidaLado;
	private JLabel lblRecogidaPosicion;
	private JLabel lblRecogidaAltura;

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
		cargarProductos();
		cargarPedidosRecogida();
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
		}
		return scpRecogida;
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
	
	private void cargarProductos() {
		Container cont = new Container();
		listaPedidos = new GetOrders().getAllOrders();
		for (Order c : listaPedidos) {
			for(OrderDetail p : c.getProductos()){
				InformacionProductoPedidoPanel aux = new InformacionProductoPedidoPanel();
				aux.getBtnEmpaquetar().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						realizarEmpaquetado(c);
					}
				});
				cont.add(aux);
			}
		}

		cont.setLayout(new GridLayout(listaPedidos.size(), 1));

		revalidate();
		repaint();

		getScpEmpaquetado().getViewport().setView(cont);
		revalidate();
		repaint();
	}
	
	private void cargarPedidosRecogida() {
		Container cont = new Container();
		
		for (Order c : listaPedidos) {
			for(OrderDetail p : c.getProductos()){
				RecogidaPedidosPanel aux = new RecogidaPedidosPanel();
				aux.getBtnRecoger().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						realizarRecogida(p.getProducto());
					}
				});
				cont.add(aux);
			}
		}

		cont.setLayout(new GridLayout(listaPedidos.size(), 1));

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
}
