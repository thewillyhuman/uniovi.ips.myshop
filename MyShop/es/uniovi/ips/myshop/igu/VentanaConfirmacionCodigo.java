package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaConfirmacionCodigo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txCodigoProductoRecogida;
	private static String s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaConfirmacionCodigo dialog = new VentanaConfirmacionCodigo(s);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaConfirmacionCodigo(String s) {
		this.s = s;
		setResizable(false);
		setBounds(100, 100, 319, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		VentanaConfirmacionCodigo w = this;
		contentPanel.setLayout(null);
		{
			JLabel lblCodigoDelProducto = new JLabel("Codigo del producto a recoger: ");
			lblCodigoDelProducto.setBounds(50, 32, 195, 14);
			contentPanel.add(lblCodigoDelProducto);
		}
		{
			txCodigoProductoRecogida = new JTextField();
			txCodigoProductoRecogida.setBounds(50, 57, 156, 20);
			contentPanel.add(txCodigoProductoRecogida);
			txCodigoProductoRecogida.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(txCodigoProductoRecogida.getText().equals(s)){
							JOptionPane.showConfirmDialog(w, "El producto con el codigo " + s + "\nha sido recogido satisfactoriamente" );
							w.dispose();
						}
						else{
							JOptionPane.showMessageDialog(w, "El id que ha introducido es erroneo");
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						w.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
