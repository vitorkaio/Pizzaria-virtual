package tsi.too.pv.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Cliente;
import tsi.too.pv.tipos.Funcionario;

@SuppressWarnings("serial")
public class JanelaPrincipalGui extends JFrame {

	private JPanel contentPane;
	private JButton funcionarioBotao;
	private JLabel funcionarioLabel;
	private JLabel clienteLabel;
	private JButton clienteBotao;
	private JLabel pizzaLabel;
	private JButton pizzaBotao;
	private JLabel ingredienteLabel;
	private JButton ingredienteBotao;
	private JLabel comprarLabel;
	private JButton comprarBotao;
	private JLabel pedidoLabel;
	private JButton pedidoBotao;
	private JLabel lblPizzariaVirtual;
	private static JLabel nomeFuncionarioLabel;
	private static JLabel tipoFuncionaLabel;

	private static Funcionario func;
	private JMenuItem logoutMenuItem;
	private JMenuItem ajudaMenuItem;
	private static JLabel dataLabel;
	private static JLabel horaLabel;

	/**
	 * Cria a janela principal do programa, com botões que levam aos outros módulos.
	 */
	public JanelaPrincipalGui(Funcionario funcionario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaPrincipalGui.class.getResource("/img/pizzaria.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		ImageIcon imgFuncionario = new ImageIcon(this.getClass().getResource("/img/funcionario.png"));
		ImageIcon imgCliente = new ImageIcon(this.getClass().getResource("/img/cliente.png"));
		ImageIcon imgPizza = new ImageIcon(this.getClass().getResource("/img/pizza.png"));
		ImageIcon imgIngrediente = new ImageIcon(this.getClass().getResource("/img/ingrediente.png"));
		ImageIcon imgComprar = new ImageIcon(this.getClass().getResource("/img/comprar.png"));
		ImageIcon imgPedido = new ImageIcon(this.getClass().getResource("/img/pedido.png"));
		ImageIcon imgSair = new ImageIcon(this.getClass().getResource("/img/sair.png"));
		ImageIcon imgHelp = new ImageIcon(this.getClass().getResource("/img/help.png"));



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

		func = funcionario;

		setTitle("Pizzaria Virtual");
		setBounds(100, 100, 889, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 148, 883, 276);
		panel.setBackground(new Color(230, 230, 250));

		funcionarioBotao = new JButton("Funcion\u00E1rio");
		funcionarioBotao.setMnemonic('f');
		funcionarioBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Verifica o tipo de funcionário.
				if(funcionario.getTipoUsuario() != 'A'){

					dispose();
					new FuncionarioAlterSenhaGui(funcionario);

				}

				else{
					dispose();
					new FuncionarioGui(funcionario);
				}
			}
		});

		funcionarioLabel = new JLabel("");
		funcionarioLabel.setToolTipText("");
		funcionarioLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		funcionarioLabel.setIcon(imgFuncionario);

		clienteLabel = new JLabel("");
		clienteLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		clienteLabel.setIcon(imgCliente);

		clienteBotao = new JButton("Cliente");
		clienteBotao.setMnemonic('c');
		clienteBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(funcionario.getTipoUsuario() == 'A'){

					EntradaESaida.msgErro("Administrador não possui acesso", "Erro");
					return;
				}

				else{
					dispose();
					new ClienteGui(funcionario);
				}
			}
		});

		pizzaLabel = new JLabel("");
		pizzaLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pizzaLabel.setIcon(imgPizza);

		pizzaBotao = new JButton("Pizza");
		pizzaBotao.setMnemonic('p');
		pizzaBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(funcionario.getTipoUsuario() == 'A'){

					EntradaESaida.msgErro("Administrador não possui acesso", "Erro");
					return;
				}

				else{
					dispose();
					new PizzaGui(funcionario);
				}
			}
		});

		ingredienteLabel = new JLabel("");
		ingredienteLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		ingredienteLabel.setIcon(imgIngrediente);

		ingredienteBotao = new JButton("Ingrediente");
		ingredienteBotao.setMnemonic('i');
		ingredienteBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(funcionario.getTipoUsuario() == 'A'){

					EntradaESaida.msgErro("Administrador não possui acesso", "Erro");
					return;
				}

				else{
					dispose();
					new IngredienteGui(funcionario);
				}
			}
		});

		comprarLabel = new JLabel("");
		comprarLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comprarLabel.setIcon(imgComprar);

		comprarBotao = new JButton("Comprar");
		comprarBotao.setMnemonic('o');
		comprarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(funcionario.getTipoUsuario() == 'A'){

					EntradaESaida.msgErro("Administrador não possui acesso", "Erro");
					return;
				}

				else{

					Cliente cliente = new ComprarLoginClienteGui().ver();

					if(cliente != null){
						dispose();
						new ComprarGui(funcionario, cliente);
					}
				}
			}
		});

		pedidoLabel = new JLabel("");
		pedidoLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pedidoLabel.setIcon(imgPedido);

		pedidoBotao = new JButton("Pedido");
		pedidoBotao.setMnemonic('e');
		pedidoBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(funcionario.getTipoUsuario() == 'A'){

					EntradaESaida.msgErro("Administrador não possui acesso", "Erro");
					return;
				}

				else{
					dispose();
					new PedidoGui(funcionario);
				}
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addContainerGap(17, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(funcionarioLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(funcionarioBotao, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(clienteLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(clienteBotao, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(pizzaLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(pizzaBotao, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(ingredienteLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(ingredienteBotao, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comprarLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(comprarBotao, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(pedidoLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(pedidoBotao, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addGap(14))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap(43, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(pedidoLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(pedidoBotao))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(comprarLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(comprarBotao))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(ingredienteLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(ingredienteBotao))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(pizzaLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(pizzaBotao))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(clienteLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(clienteBotao))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(funcionarioLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(funcionarioBotao)))
						.addGap(27))
				);
		panel.setLayout(gl_panel);
		contentPane.add(panel);

		lblPizzariaVirtual = new JLabel("              Pizzaria Virtual");
		lblPizzariaVirtual.setForeground(SystemColor.textHighlightText);
		lblPizzariaVirtual.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.textHighlightText));
		lblPizzariaVirtual.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblPizzariaVirtual.setBounds(318, 48, 257, 53);
		contentPane.add(lblPizzariaVirtual);

		nomeFuncionarioLabel = new JLabel("");
		nomeFuncionarioLabel.setForeground(SystemColor.textHighlightText);
		nomeFuncionarioLabel.setBounds(10, 48, 143, 16);
		contentPane.add(nomeFuncionarioLabel);

		tipoFuncionaLabel = new JLabel("");
		tipoFuncionaLabel.setForeground(SystemColor.textHighlightText);
		tipoFuncionaLabel.setBounds(10, 70, 143, 16);
		contentPane.add(tipoFuncionaLabel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 883, 23);
		contentPane.add(menuBar);

		JMenu menuOpces = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(menuOpces);

		logoutMenuItem = new JMenuItem("Logout", imgSair);
		logoutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		logoutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				EntradaESaida.somLogout();
				dispose();
				new LoginGui();

			}
		});

		ajudaMenuItem = new JMenuItem("Ajuda", imgHelp);
		ajudaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		ajudaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new AjudaGui();

			}
		});
		menuOpces.add(ajudaMenuItem);
		menuOpces.add(logoutMenuItem);

		dataLabel = new JLabel("");
		dataLabel.setForeground(SystemColor.textHighlightText);
		dataLabel.setBounds(757, 30, 120, 16);
		contentPane.add(dataLabel);

		horaLabel = new JLabel("");
		horaLabel.setBounds(757, 51, 120, 16);
		contentPane.add(horaLabel);

		setaInformacoesFuncionario();

		setVisible(true);
		setLocationRelativeTo(null);

	}// Fim do construtor.

	/** Seta o nome e o tipo do usuário na janela. */
	private static void setaInformacoesFuncionario(){

		// Seta o nome e o tipo do funcionario que está utilizando o programa.
		nomeFuncionarioLabel.setText("Nome: " + func.getNomeUsuario());

		if(func.getTipoUsuario() == 'A')
			tipoFuncionaLabel.setText("Acesso: Administrador");

		else if(func.getTipoUsuario() == 'C')
			tipoFuncionaLabel.setText("Acesso: Colaborador");

		else if(func.getTipoUsuario() == 'G')
			tipoFuncionaLabel.setText("Acesso: Gerente");

	}// setaInformacoesFunciona()

	/** Adiciona área de texto a permisão que o funcionário possui.*/
	/*private static void mostraPermissao(String modulo){

		if(modulo.equalsIgnoreCase("funcionario") == true){

			String tipo = tipoFuncionaLabel.getText();

			if(tipo.endsWith("Administrador")){

				permisaoTextArea.setText("                                        Permissões - Administrador\n"
						+ "\n - Cadastrar Funcionário\n - Consultar Funcionário"
						+ "\n - Listar Funcionário\n - Alterar Funcionário\n - Excluir Funcionário");

			}// Funcionario - Administrador

			else if(tipo.endsWith("Gerente")){

				permisaoTextArea.setText("                                        Permissões - Gerente\n"
						+ "\n - Alterar Senha");

			}// Funcionario - Gerente

			else if(tipo.endsWith("Colaborador")){

				permisaoTextArea.setText("                                        Permissões - Colaborador\n"
						+ "\n - Alterar Senha");

			}// Funcionario

		}// Funcionario


		else if(modulo.equalsIgnoreCase("cliente") == true){

			String tipo = tipoFuncionaLabel.getText();

			if(tipo.endsWith("Administrador")){

				permisaoTextArea.setText("                                        Permissões - Administrador\n"
						+ "\n - Nenhuma");

			}// Cliente - Administrador

			else if(tipo.endsWith("Gerente")){

				permisaoTextArea.setText("                                        Permissões - Gerente\n"
						+ "\n - Cadastrar Cliente\n - Consultar Cliente"
						+ "\n - Listar Cliente\n - Alterar Cliente\n - Excluir Cliente");

			}// Cliente - Gerente

			else if(tipo.endsWith("Colaborador")){

				permisaoTextArea.setText("                                        Permissões - Colaborador\n"
						+ "\n - Cadastrar Cliente\n - Consultar Cliente"
						+ "\n - Listar Cliente\n - Alterar Cliente");


			}// Cliente - Colaborador

		}// Cliente


		else if(modulo.equalsIgnoreCase("pizza") == true){

			String tipo = tipoFuncionaLabel.getText();

			if(tipo.endsWith("Administrador")){

				permisaoTextArea.setText("                                        Permissões - Administrador\n"
						+ "\n - Nenhuma");

			}// Pizza - Administrador

			else if(tipo.endsWith("Gerente")){

				permisaoTextArea.setText("                                        Permissões - Gerente\n"
						+ "\n - Cadastrar Pizza\n - Consultar Pizza"
						+ "\n - Listar Pizza\n - Alterar Pizza\n - Excluir Pizza");

			}// Pizza - Gerente

			else if(tipo.endsWith("Colaborador")){

				permisaoTextArea.setText("                                        Permissões - Colaborador\n"
						+ "\n - Cadastrar Pizza\n - Consultar Pizza"
						+ "\n - Listar Pizza\n - Alterar Pizza");


			}// Pizza - Colaborador

		}// Pizza


		else if(modulo.equalsIgnoreCase("ingredientes") == true){

			String tipo = tipoFuncionaLabel.getText();

			if(tipo.endsWith("Administrador")){

				permisaoTextArea.setText("                                        Permissões - Administrador\n"
						+ "\n - Nenhuma");

			}// Ingredientes - Administrador

			else if(tipo.endsWith("Gerente")){

				permisaoTextArea.setText("                                        Permissões - Gerente\n"
						+ "\n - Cadastrar Ingredientes\n - Consultar Ingredientes"
						+ "\n - Listar Ingredientes\n - Alterar Ingredientes\n - Excluir Ingredientes");

			}// Ingredientes - Gerente

			else if(tipo.endsWith("Colaborador")){

				permisaoTextArea.setText("                                        Permissões - Colaborador\n"
						+ "\n - Cadastrar Ingredientes\n - Consultar Ingredientes"
						+ "\n - Listar Ingredientes\n - Alterar Ingredientes");


			}// Ingredientes - Colaborador

		}// Ingredientes


		else if(modulo.equalsIgnoreCase("compras") == true){

			String tipo = tipoFuncionaLabel.getText();

			if(tipo.endsWith("Administrador")){

				permisaoTextArea.setText("                                        Permissões - Administrador\n"
						+ "\n - Nenhuma");

			}// Compras - Administrador

			else if(tipo.endsWith("Gerente")){

				permisaoTextArea.setText("                                        Permissões - Gerente\n"
						+ "\n - Executar Compras");

			}// Compras - Gerente

			else if(tipo.endsWith("Colaborador")){

				permisaoTextArea.setText("                                        Permissões - Colaborador\n"
						+ "\n - Executar Compras");

			}// Compras - Gerente

		}// compras


		else if(modulo.equalsIgnoreCase("pedido") == true){

			String tipo = tipoFuncionaLabel.getText();

			if(tipo.endsWith("Administrador")){

				permisaoTextArea.setText("                                        Permissões - Administrador\n"
						+ "\n - Nenhuma");

			}// Pedido - Administrador

			else if(tipo.endsWith("Gerente")){

				permisaoTextArea.setText("                                        Permissões - Gerente\n"
						+ "\n - Conlsutar Pedido\n - Listar Pedido\n - Fechar Pedido\n - Excluir Pedido\n - Relatório");

			}// Pedido - Gerente


			else if(tipo.endsWith("Colaborador")){

				permisaoTextArea.setText("                                        Permissões - Colaborador\n"
						+ "\n - Conlsutar Pedido\n - Listar Pedido\n - Fechar Pedido\n - Relatório");


			}// Pedido - Colaborador

		}// Compras



		else if(modulo.equals("") == true)
			permisaoTextArea.setText("");

	}*/

}// Fim da class JanelaPrincipalGui
