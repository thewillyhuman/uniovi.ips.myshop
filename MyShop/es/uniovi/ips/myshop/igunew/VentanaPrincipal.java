package es.uniovi.ips.myshop.igunew;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel imgCarrito;
	private JPanel panel;
	private JLabel lblIrATienda;
	private JPanel panel_1;
	private JLabel label;
	private JLabel lblIrAAlmacn;
	private JLabel lblSelecioneLaVentana;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setResizable(false);
		setTitle("MyShop");
		setMinimumSize(new Dimension(1046, 703));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel_1());
		contentPane.add(getPanel());
		contentPane.add(getLabel_1_1());
	}
	private JLabel getImgCarrito() {
		if (imgCarrito == null) {
			imgCarrito = new JLabel("");
			imgCarrito.setBounds(43, 6, 128, 128);
			imgCarrito.setHorizontalAlignment(SwingConstants.CENTER);
			imgCarrito.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/igu/img/box-closed.png")));
		}
		return imgCarrito;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//Call to the next Window..
				}
			});
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel.setBorder(new LineBorder(new Color(192, 192, 192)));
			panel.setBounds(126, 197, 220, 224);
			panel.setLayout(null);
			panel.add(getImgCarrito());
			panel.add(getLblIrATienda());
		}
		return panel;
	}
	private JLabel getLblIrATienda() {
		if (lblIrATienda == null) {
			lblIrATienda = new JLabel("Ir a tienda");
			lblIrATienda.setHorizontalAlignment(SwingConstants.CENTER);
			lblIrATienda.setBounds(57, 192, 101, 16);
		}
		return lblIrATienda;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//Call to the next window.
				}
			});
			panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel_1.setBorder(new LineBorder(new Color(192, 192, 192)));
			panel_1.setLayout(null);
			panel_1.setBounds(644, 197, 220, 224);
			panel_1.add(getLabel());
			panel_1.add(getLblIrAAlmacn());
		}
		return panel_1;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/igu/img/almacen.png")));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(43, 6, 128, 128);
		}
		return label;
	}
	private JLabel getLblIrAAlmacn() {
		if (lblIrAAlmacn == null) {
			lblIrAAlmacn = new JLabel("Ir a almacén");
			lblIrAAlmacn.setHorizontalAlignment(SwingConstants.CENTER);
			lblIrAAlmacn.setBounds(57, 192, 101, 16);
		}
		return lblIrAAlmacn;
	}
	private JLabel getLabel_1_1() {
		if (lblSelecioneLaVentana == null) {
			lblSelecioneLaVentana = new JLabel("Selecione la ventana que desea abrir.");
			lblSelecioneLaVentana.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblSelecioneLaVentana.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelecioneLaVentana.setBounds(337, 539, 358, 51);
		}
		return lblSelecioneLaVentana;
	}
}
