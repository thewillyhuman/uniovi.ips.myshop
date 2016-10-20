package es.uniovi.ips.myshop.igu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uniovi.ips.myshop.connectors.AddIncidence;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaConfirmacionCodigo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txCodigoProductoRecogida;
	private static String s;
	private JButton okButton;

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
			contentPanel.add(getTxCodigoProductoRecogida());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				
				getRootPane().setDefaultButton(getOkButton());
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						w.dispose();
					}
				});
				buttonPane.add(getOkButton());
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	public JTextField getTxCodigoProductoRecogida(){
		if(txCodigoProductoRecogida == null){
			txCodigoProductoRecogida = new JTextField();
			txCodigoProductoRecogida.setBounds(50, 57, 156, 20);
			txCodigoProductoRecogida.setColumns(10);
		}
		return txCodigoProductoRecogida;
	}

	public JButton getOkButton() {
		if(okButton == null){
			okButton = new JButton("OK");
			okButton.setActionCommand("OK");
		}
		return okButton;
	}

}
