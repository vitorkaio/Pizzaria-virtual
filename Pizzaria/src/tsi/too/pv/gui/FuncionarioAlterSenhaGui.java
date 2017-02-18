package tsi.too.pv.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import tsi.too.pv.controle.ControleFuncionario;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Funcionario;
import java.awt.Toolkit;

/** 
 * Cria uma janela que permite os funcionários alterar a senha.
 * 
 * */
@SuppressWarnings("serial")
public class FuncionarioAlterSenhaGui extends JFrame {

	private JPanel contentPane;
	private JTextField nomeTextField;
	private JTextField tipoTextField;
	private JTextField senhaTextField;
	private JButton alterarBotao;

	/**
	 * O construtor recebe como parâmetro o funcionário e a suas informações.
	 */
	public FuncionarioAlterSenhaGui(Funcionario func) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FuncionarioAlterSenhaGui.class.getResource("/img/pizzaria.png")));
		setResizable(false);
		
		ImageIcon imgAlterarEditar = new ImageIcon(this.getClass().getResource("/img/edita.png"));
		ImageIcon imgCancelar = new ImageIcon(this.getClass().getResource("/img/cancelar.png"));
		//ImageIcon img = new ImageIcon(this.getClass().getResource("/img/background.png"));
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		setTitle("Controle - Senha do Funcion\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nomeLabel = new JLabel("Nome:");
		nomeLabel.setBounds(48, 32, 37, 14);
		contentPane.add(nomeLabel);
		
		nomeTextField = new JTextField();
		nomeTextField.setEnabled(false);
		nomeTextField.setBounds(92, 25, 194, 28);
		contentPane.add(nomeTextField);
		nomeTextField.setColumns(10);
		
		JLabel tipoLabel = new JLabel("Tipo:");
		tipoLabel.setBounds(48, 111, 27, 16);
		contentPane.add(tipoLabel);
		
		tipoTextField = new JTextField();
		tipoTextField.setEnabled(false);
		tipoTextField.setBounds(92, 103, 27, 28);
		contentPane.add(tipoTextField);
		tipoTextField.setColumns(10);
		
		JLabel senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(48, 68, 44, 16);
		contentPane.add(senhaLabel);
		
		senhaTextField = new JTextField();
		senhaTextField.setBounds(92, 64, 194, 28);
		contentPane.add(senhaTextField);
		senhaTextField.setColumns(10);
		
		// Setanndo os valores do funcionário nos campos.
		nomeTextField.setText(func.getNomeUsuario());
		senhaTextField.setText(func.getSenha());
		tipoTextField.setText(String.valueOf(func.getTipoUsuario()));
		
		/* ***************** Trata os eventos do botão alterar ***************** */
		alterarBotao = new JButton("Alterar", imgAlterarEditar);
		alterarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				// Verifica o tamanho da senha.
				if(senhaTextField.getText().length() < 6){
					
					EntradaESaida.msgErro("Valor inválido para a senha\n min 6 caracteres", 
							"Alterar Senha");
					
					return;
					
				}
				
				func.setSenha(senhaTextField.getText());
				ControleFuncionario controleFunc = new ControleFuncionario();
				
				if(controleFunc.altFunc(func.getNomeUsuario(), func) == true){
					
					EntradaESaida.msgInfo("Senha trocada", "Alterar Senha");
					dispose();
					new JanelaPrincipalGui(func);
					
				}
				
				else{
					
					EntradaESaida.msgErro("Não foi possível trocar a senha", "Alterar Senha");
					dispose();
					new JanelaPrincipalGui(func);
					
				}
			}
		});
		alterarBotao.setBounds(79, 152, 90, 28);
		contentPane.add(alterarBotao);
		
		JButton cancelarBotao = new JButton("Cancelar", imgCancelar);
		cancelarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				new JanelaPrincipalGui(func);
				
			}
		});
		cancelarBotao.setBounds(179, 152, 98, 28);
		contentPane.add(cancelarBotao);
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
