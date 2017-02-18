package tsi.too.pv.gui;

import java.awt.BorderLayout;
import java.awt.SystemColor;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AjudaGui extends JDialog {

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public AjudaGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AjudaGui.class.getResource("/img/pizzaria.png")));
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Menu Ajuda");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.controlHighlight);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea txtrMduloFuncionrio = new JTextArea();
		txtrMduloFuncionrio.setText("**************************** M\u00F3dulo Funcion\u00E1rio **************************** \r\n\r\nSe o tipo e acesso do usu\u00E1rio for:\r\n\r\nAdministrador - Permite cadastrar, consultar, alterar e excluir funcion\u00E1rios.\r\nColaborador - Permite trocar a sua pr\u00F3pria senha.\r\nGerente - Permite trocar a sua pr\u00F3pria senha.\r\n\r\n****************************** M\u00F3dulo Cliente ****************************** \r\n\r\nSe o tipo e acesso do usu\u00E1rio for:\r\n\r\nAdministrador - N\u00E3o possui acesso.\r\nColaborador - Permite cadastrar, consultar e alterar.\r\nGerente - Permite cadastrar, consultar, alterar e excluir.\r\n\r\n****************************** M\u00F3dulo Pizza ****************************** \r\n\r\nSe o tipo e acesso do usu\u00E1rio for:\r\n\r\nAdministrador - N\u00E3o possui acesso.\r\nColaborador - Permite cadastrar, consultar e alterar.\r\nGerente - Permite cadastrar, consultar, alterar e excluir.\r\n\r\n**************************** M\u00F3dulo Ingrediente **************************** \r\n\r\nSe o tipo e acesso do usu\u00E1rio for:\r\n\r\nAdministrador - N\u00E3o possui acesso.\r\nColaborador - Permite cadastrar, consultar e alterar.\r\nGerente - Permite cadastrar, consultar, alterar e excluir.\r\n\r\n****************************** M\u00F3dulo Compras ****************************** \r\n\r\nSe o tipo e acesso do usu\u00E1rio for:\r\n\r\nAdministrador - N\u00E3o possui acesso.\r\nColaborador - Permite cadastrar.\r\nGerente - Permite cadastrar.\r\n\r\n****************************** M\u00F3dulo Pedido ****************************** \r\n\r\nSe o tipo e acesso do usu\u00E1rio for:\r\n\r\nAdministrador - N\u00E3o possui acesso.\r\nColaborador - Permite consultar, encerrar e exibir relat\u00F3rio.\r\nGerente - Permite consultar, encerrar, excluir e exibir relat\u00F3rio.");
		txtrMduloFuncionrio.setEditable(false);
		txtrMduloFuncionrio.setLineWrap(true);
		txtrMduloFuncionrio.setWrapStyleWord(true);
		scrollPane.setViewportView(txtrMduloFuncionrio);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

}
