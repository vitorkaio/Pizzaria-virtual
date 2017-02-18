package tsi.too.pv.gui;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import tsi.too.pv.controle.ControleCliente;
import tsi.too.pv.tipos.Cliente;

/** Mostra uma tela para entrar com o cpf do cliente. */
@SuppressWarnings("serial")
public class ComprarLoginClienteGui extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JFormattedTextField cpfClienteFormattedTextField;
	private JButton botaoOk;
	private static Cliente cl;

	public ComprarLoginClienteGui() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Login Cliente");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 286, 161);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.controlHighlight);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCpfDoCliente = new JLabel("Cpf do cliente: ");
		lblCpfDoCliente.setBounds(10, 30, 89, 14);
		contentPanel.add(lblCpfDoCliente);

		cpfClienteFormattedTextField = new JFormattedTextField();
		try {
			cpfClienteFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(
					new MaskFormatter("###.###.###-##")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cpfClienteFormattedTextField.setBounds(32, 52, 226, 28);
		contentPanel.add(cpfClienteFormattedTextField);

		botaoOk = new JButton("Ok");
		botaoOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				loginCliente();
				dispose();

			}
		});
		botaoOk.setBounds(42, 92, 89, 23);
		contentPanel.add(botaoOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCancelar.setBounds(143, 92, 89, 23);
		contentPanel.add(btnCancelar);

		setLocationRelativeTo(null);
		setVisible(true);
	}// Fim do construtor.
	
	private static Cliente loginCliente(){
		
		// Solicita o nome do usuário.
		String nomeCliente = cpfClienteFormattedTextField.getText();
		
		// Verifica se o nome passado existe no banco, se sim pega as informações se não fecha.
		ControleCliente controlePizzaCliente = new ControleCliente();
		Cliente cliente = controlePizzaCliente.conCliente(nomeCliente);
		cl = cliente;
		
		if(cliente == null)
			return cl;
		
		return cl;
		
	}// loginCliente()
	
	public Cliente ver(){
		
		return cl;
		
	} 
	
}// Fim da classe ComprarLoginCliente.
