package tsi.too.pv.gui;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import tsi.too.pv.bd.FuncionarioBd;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Funcionario;
import java.awt.Color;
import java.awt.Toolkit;

public class LoginGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel iconeLabel;
	private JLabel nomeLabel;
	private JTextField nomeTextField;
	private JLabel senhaLabel;
	private JPasswordField senhaPwd;
	private JButton entrarBotao;
	private JLabel fundoLabel;

	/**
	 * Create the frame.
	 */
	public LoginGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginGui.class.getResource("/img/pizzaria.png")));

		ImageIcon imgFuncionario = new ImageIcon(this.getClass().getResource("/img/login/login.png"));
		ImageIcon imgLogin = new ImageIcon(this.getClass().getResource("/img/login.png"));

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

		setResizable(false);
		setTitle("Login");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 299, 276);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		iconeLabel = new JLabel("");
		iconeLabel.setIcon(imgFuncionario);
		iconeLabel.setBounds(115, 16, 88, 82);
		contentPane.add(iconeLabel);

		nomeLabel = new JLabel("Nome:");
		nomeLabel.setBounds(10, 112, 47, 16);
		contentPane.add(nomeLabel);

		nomeTextField = new JTextField();
		nomeTextField.setBounds(67, 110, 197, 26);
		contentPane.add(nomeTextField);
		nomeTextField.setColumns(10);

		senhaLabel = new JLabel("Senha:");
		senhaLabel.setBounds(10, 155, 48, 16);
		contentPane.add(senhaLabel);

		senhaPwd = new JPasswordField();
		senhaPwd.setBounds(67, 153, 197, 26);
		contentPane.add(senhaPwd);

		/* ********************** Trata o evento do botão entrar ********************** */
		entrarBotao = new JButton("Entrar", imgLogin);
		entrarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Funcionario funcionario = logSistema();
				
				if(funcionario == null)
					;
				
				else{
					
					EntradaESaida.somLogin();
					dispose();
					new JanelaPrincipalGui(funcionario);
					
				}
				
			}
		});
		entrarBotao.setBounds(104, 196, 98, 26);
		contentPane.add(entrarBotao);

		fundoLabel = new JLabel("");
		fundoLabel.setForeground(new Color(70, 130, 180));
		fundoLabel.setBackground(new Color(70, 130, 180));
		fundoLabel.setBounds(0, 0, 293, 247);
		contentPane.add(fundoLabel);
		setLocationRelativeTo(null);
		setVisible(true);

	}// Fim do construtor.

	/** Retorna o funcionário logado no sistema.*/
	public Funcionario logSistema(){

		// Verifica se o nome e a senha condiz com os dados do banco de dados, é preciso ser A - Administrador tb.
		FuncionarioBd funcionarioBd = new FuncionarioBd();

		Funcionario func = (Funcionario) funcionarioBd.obtemObjeto(nomeTextField.getText());

		// Se func for null quer dizer que o nome passado não existe.
		if(func == null){
			EntradaESaida.msgErro("Funcionário inválido", "Login");
			return null;
		}

		// Verifica se a senha são a mesma.
		char pass[] = senhaPwd.getPassword();
		String senha = String.valueOf(pass);
		if(func.getSenha().trim().equals(senha.trim()) == false){
			//System.out.println(senha);
			EntradaESaida.msgErro("Senha inválida", "Login");
			return null;

		}
		
		return func;

	}// logSistema()

}
