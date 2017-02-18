package tsi.too.pv.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import tsi.too.pv.controle.ConstantesClientes;
import tsi.too.pv.controle.ControleCliente;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Cliente;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Pizzaria;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JFormattedTextField;

public class ClienteGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField nomeCadInterTextField;
	private static JTextField logCadInterTextField;
	private static JTextField cidadeCadInterTextField;
	private static JTextField bairroCadInterTextField;
	private static JTextField numeroCadInterTextField;
	private static JTextField compleCadInterTextField;
	private static JButton cadCadInterBotao;
	private static JButton canceCadInterBotao;
	private static JTextField cpfConsuInternalTextField;
	private static JTextArea relConsuInternalTextArea;
	private static JScrollPane scrollPane;
	private static JTable tabelaInternalListar;
	private static JTextField cpfAltPesInterTextField;
	private static JTextField cpfAltInterTextField;
	private static JTextField nomeAltInterTextField;
	private static JTextField logAltInterTextField;
	private static JTextField cidadeAltInterTextField;
	private static JTextField bairroAltInterTextField;
	private static JTextField numeroAltInterTextField;
	private static JTextField compAltInterTextField;
	private static JTextField telAltInterTextField;
	private static JTextField celAltInterTextField;
	private static JButton cancelAltInterBotao;
	private static JButton alterarAltInterBotao;
	private static JLabel cpfAltInterLabel;
	private static JLabel nomeAltInterLabel;
	private static JLabel logAltInterLabel;
	private static JLabel cidadeAltInterLabel;
	private static JLabel bairroAltInterLabel;
	private static JLabel numeroAltInterLabel;
	private static JLabel compleAltInterLabel;
	private static JLabel telAltInterLabel;
	private static JLabel celAltInterLabel;
	private static JDesktopPane desktop;
	private static JInternalFrame cadastrarCliente;
	private static JInternalFrame listarCliente;
	private static JInternalFrame consultarCliente;
	private static JInternalFrame alterarCliente;
	private static JTextField nomeListaInterTextField;

	private static ControleCliente controleCliente;
	private static ArrayList<Pizzaria> listaCliente;;
	private static JMenu menuExcluir;
	private static JMenuItem menuItemExcluir;
	private static JInternalFrame excluirCliente;
	private static JLabel cpfPesExcluirInterLabel;
	private static JTextField cpfPesExcluirInterTextField;
	private static JButton cpfPesExcluirInterBotao;
	private static JTextArea cpfPesExcluirInterTextArea;
	private static JButton excluirExcluirInterBotao;
	private static JButton cancelarExcluirInterBotao;
	private static JPanel panel;
	private static JButton cpfAltInterBotao;
	private static JButton btnCancelar;
	private static JFormattedTextField telCadInterTextField;
	private static JFormattedTextField celCadInterTextField;
	private static JFormattedTextField cpfCadInterTextField;

	/**
	 * Cria o frame do gerenciamento do ingrediente.
	 * 
	 * @param tipo referente ao funcionário que cadastrou, A - administrador: não tem acesso, 
	 * se for C - colaborador: poderá utilizar todas as operações do gerenciamento de do ingrediente menos o excluir, 
	 * se for G - gerente: possui acesso irrestrito.
	 */
	public ClienteGui(Funcionario tipoFuncionario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteGui.class.getResource("/img/pizzaria.png")));

		ImageIcon imgGravar = new ImageIcon(this.getClass().getResource("/img/gravar.png"));
		ImageIcon imgAlterarEditar = new ImageIcon(this.getClass().getResource("/img/edita.png"));
		ImageIcon imgPesquisar = new ImageIcon(this.getClass().getResource("/img/pesquisa.png"));
		ImageIcon imgCancelar = new ImageIcon(this.getClass().getResource("/img/cancelar.png"));
		ImageIcon imgDelete = new ImageIcon(this.getClass().getResource("/img/delete.png"));
		ImageIcon imgCadastrar = new ImageIcon(this.getClass().getResource("/img/cadastrar.png"));
		ImageIcon imgListar = new ImageIcon(this.getClass().getResource("/img/lista.png"));
		ImageIcon imgSair = new ImageIcon(this.getClass().getResource("/img/sair.png"));

		//ImageIcon img = new ImageIcon(this.getClass().getResource("/img/background5.jpg"));


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

		// Se o tipoFuncionario for A - Administrador: Não possui acesso.
		if(tipoFuncionario.getTipoUsuario() == 'A'){

			EntradaESaida.msgErro("Administrador não possui acesso", "Erro");
			return;
		}

		controleCliente = new ControleCliente();
		listaCliente = new ArrayList<>();

		setTitle("Controle - Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JMenuBar barraDeMenu = new JMenuBar();
		barraDeMenu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		barraDeMenu.setForeground(SystemColor.info);
		barraDeMenu.setBackground(SystemColor.inactiveCaption);
		contentPane.add(barraDeMenu, BorderLayout.NORTH);

		JMenu menuOpcoes = new JMenu("Op\u00E7\u00F5es");
		barraDeMenu.add(menuOpcoes);

		JMenuItem sairMenuItem = new JMenuItem("Sair", imgSair);
		sairMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		sairMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cadastrarCliente.dispose();
				consultarCliente.dispose();
				listarCliente.dispose();
				alterarCliente.dispose();
				excluirCliente.dispose();
				// dispose na janela atual e chamar a tela de login.
				dispose();
				//EntradaESaida.msgInfo("logout feito com sucesso", "Login");
				new JanelaPrincipalGui(tipoFuncionario);
			}
		});
		menuOpcoes.add(sairMenuItem);

		JMenu menuCadastrar = new JMenu("Cadastrar");
		menuCadastrar.setMnemonic('c');
		barraDeMenu.add(menuCadastrar);

		JMenuItem cadastrarMenu = new JMenuItem("Cadastrar", imgCadastrar);
		cadastrarMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		cadastrarMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/* Libera ajanela interna excluirFuncionario.
				excluirFuncionario.dispose();

				// Libera a janela interna alterarFuncionario.
				alterarFuncionario.dispose();

				// Libera a janela interna consultarFuncionario.
				consultarFuncionario.dispose();
				// Zera os campos consultaInternal
				zeraCamposConsulta();

				// Libera a janela interna listaFuncionario
				listarFuncionario.dispose();*/

				cadastrarCliente.setVisible(true);

				// Zera os campos cadastrarInternal
				zeraCamposCadastrar();

				lista();
				geraTabelaListar(listaCliente);

			}
		});
		menuCadastrar.add(cadastrarMenu);

		JMenu menuConsultar = new JMenu("Consultar");
		menuConsultar.setMnemonic('o');
		barraDeMenu.add(menuConsultar);

		JMenuItem consultarMenuItem = new JMenuItem("Consultar", imgPesquisar);
		consultarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		consultarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/* Libera ajanela interna excluirFuncionario.
				excluirFuncionario.dispose();

				// Libera a janela interna alterarFuncionario.
				alterarFuncionario.dispose();

				// Libera a janela interna cadastrarFuncionario.
				cadastrarFuncionario.dispose();
				// Zera os campos cadastrarInternal
				zeraCamposCadastrar();

				// Libera a janela interna listaFuncionario
				listarFuncionario.dispose();*/

				// Ativa a janela interna consultar
				consultarCliente.setVisible(true);

				// Zera os campos consultaInternal
				zeraCamposConsulta();

			}
		});
		menuConsultar.add(consultarMenuItem);

		JMenuItem listarMenuItem = new JMenuItem("Listar", imgListar);
		listarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		listarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/* Libera ajanela interna excluirFuncionario.
				excluirFuncionario.dispose();

				// Libera a janela interna alterarFuncionario.
				alterarFuncionario.dispose();

				// Libera a janela interna cadastrarFuncionario.
				cadastrarFuncionario.dispose();
				// Zera os campos cadastrarInternal*/
				zeraCamposCadastrar();

				// Libera a janela interna consultarFuncionario.
				//consultarFuncionario.dispose();
				// Zera os campos consultaInternal
				zeraCamposConsulta();

				listarCliente.setVisible(true);

				// Gera a tabela.
				lista();
				geraTabelaListar(listaCliente);

			}
		});
		menuConsultar.add(listarMenuItem);

		JMenu menuAlterar = new JMenu("Alterar");
		menuAlterar.setMnemonic('a');
		barraDeMenu.add(menuAlterar);

		JMenuItem alterarMenuItem = new JMenuItem("Alterar", imgAlterarEditar);
		alterarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		alterarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Seta os valores na janela
				/* Libera ajanela interna excluirFuncionario.
				excluirFuncionario.dispose();

				// Libera a janela interna cadastrarFuncionario.
				cadastrarFuncionario.dispose();
				// Zera os campos cadastrarInternal*/
				//zeraCamposCadastrar();

				// Libera a janela interna consultarFuncionario.
				//consultarFuncionario.dispose();
				// Zera os campos consultaInternal
				//zeraCamposConsulta();

				// Libera a janela interna listaFuncionario
				//listarFuncionario.dispose();

				alterarCliente.setVisible(true);

				// Desativando os campos alterar.
				desativaCamposAlterar();

			}
		});
		menuAlterar.add(alterarMenuItem);

		menuExcluir = new JMenu("Excluir");
		menuExcluir.setMnemonic('e');
		barraDeMenu.add(menuExcluir);

		menuItemExcluir = new JMenuItem("Excluir", imgDelete);
		menuItemExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluirCliente.setVisible(true);
				cpfPesExcluirInterTextField.setEnabled(true);
				desativaCamposExcluir();

			}
		});
		menuExcluir.add(menuItemExcluir);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		desktop = new JDesktopPane();

		cadastrarCliente = new JInternalFrame("Cadastrar Cliente");
		cadastrarCliente.setIconifiable(true);
		cadastrarCliente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		cadastrarCliente.setBounds(5, 10, 354, 521);
		cadastrarCliente.getContentPane().setBackground(SystemColor.controlHighlight);
		cadastrarCliente.setBackground(SystemColor.control);
		cadastrarCliente.setClosable(true);
		desktop.add(cadastrarCliente);

		JLabel cpfCadInterLabel = new JLabel("Cpf");
		cpfCadInterLabel.setBounds(6, 6, 19, 16);

		JLabel nomeCadInterLabel = new JLabel("Nome");
		nomeCadInterLabel.setBounds(6, 62, 34, 16);

		nomeCadInterTextField = new JTextField();
		nomeCadInterTextField.setBounds(6, 84, 330, 28);
		nomeCadInterTextField.setColumns(10);

		JLabel logCadInterLabel = new JLabel("Logradouro");
		logCadInterLabel.setBounds(6, 118, 64, 16);

		logCadInterTextField = new JTextField();
		logCadInterTextField.setBounds(6, 140, 330, 28);
		logCadInterTextField.setColumns(10);

		JLabel cidadeCadInterLabel = new JLabel("Cidade");
		cidadeCadInterLabel.setBounds(6, 174, 40, 16);

		cidadeCadInterTextField = new JTextField();
		cidadeCadInterTextField.setBounds(6, 196, 330, 28);
		cidadeCadInterTextField.setColumns(10);

		JLabel bairroCadInterLabel = new JLabel("Bairro");
		bairroCadInterLabel.setBounds(6, 230, 33, 16);

		bairroCadInterTextField = new JTextField();
		bairroCadInterTextField.setBounds(6, 252, 190, 28);
		bairroCadInterTextField.setColumns(10);

		JLabel numeroCadInterLabel = new JLabel("N\u00FAmero");
		numeroCadInterLabel.setBounds(220, 230, 45, 16);

		numeroCadInterTextField = new JTextField();
		numeroCadInterTextField.setBounds(214, 252, 63, 28);
		numeroCadInterTextField.setColumns(10);

		JLabel compleCadInterLabel = new JLabel("Complemento");
		compleCadInterLabel.setBounds(6, 286, 79, 16);

		compleCadInterTextField = new JTextField();
		compleCadInterTextField.setBounds(6, 308, 330, 28);
		compleCadInterTextField.setColumns(10);

		JLabel telCadInterLabel = new JLabel("Telefone Fixo");
		telCadInterLabel.setBounds(6, 342, 73, 16);

		JLabel celCadInterLabel = new JLabel("Celular");
		celCadInterLabel.setBounds(6, 398, 40, 16);

		cadCadInterBotao = new JButton("Gravar", imgGravar);
		cadCadInterBotao.setBounds(69, 454, 98, 28);
		cadCadInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				insereCliente();
				
			}
		});

		canceCadInterBotao = new JButton("Cancelar", imgCancelar);
		canceCadInterBotao.setBounds(179, 454, 98, 28);
		canceCadInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				zeraCamposCadastrar();

			}
		});
		cadastrarCliente.getContentPane().setLayout(null);
		cadastrarCliente.getContentPane().add(nomeCadInterTextField);
		cadastrarCliente.getContentPane().add(cpfCadInterLabel);
		cadastrarCliente.getContentPane().add(nomeCadInterLabel);
		cadastrarCliente.getContentPane().add(logCadInterLabel);
		cadastrarCliente.getContentPane().add(logCadInterTextField);
		cadastrarCliente.getContentPane().add(cidadeCadInterLabel);
		cadastrarCliente.getContentPane().add(cidadeCadInterTextField);
		cadastrarCliente.getContentPane().add(bairroCadInterLabel);
		cadastrarCliente.getContentPane().add(bairroCadInterTextField);
		cadastrarCliente.getContentPane().add(numeroCadInterLabel);
		cadastrarCliente.getContentPane().add(numeroCadInterTextField);
		cadastrarCliente.getContentPane().add(compleCadInterLabel);
		cadastrarCliente.getContentPane().add(compleCadInterTextField);
		cadastrarCliente.getContentPane().add(telCadInterLabel);
		cadastrarCliente.getContentPane().add(celCadInterLabel);
		cadastrarCliente.getContentPane().add(cadCadInterBotao);
		cadastrarCliente.getContentPane().add(canceCadInterBotao);

		telCadInterTextField = new JFormattedTextField();
		try {
			MaskFormatter mask = new MaskFormatter("(##)####-####");
			mask.setValidCharacters("1234567890");
			telCadInterTextField.setFormatterFactory(new DefaultFormatterFactory(
					mask));
		} catch (Exception e) {
			e.printStackTrace();
		}
		telCadInterTextField.setBounds(6, 360, 330, 28);
		cadastrarCliente.getContentPane().add(telCadInterTextField);

		celCadInterTextField = new JFormattedTextField();
		try {
			MaskFormatter mask = new MaskFormatter("(##)9####-####");
			mask.setValidCharacters("1234567890");
			celCadInterTextField.setFormatterFactory(new DefaultFormatterFactory(
					mask));
		} catch (Exception e) {
			e.printStackTrace();
		}

		celCadInterTextField.setBounds(6, 415, 330, 27);
		cadastrarCliente.getContentPane().add(celCadInterTextField);

		cpfCadInterTextField = new JFormattedTextField();
		try {
			cpfCadInterTextField.setFormatterFactory(new DefaultFormatterFactory(
					new MaskFormatter("###.###.###-##")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		cpfCadInterTextField.setBounds(6, 24, 330, 28);
		cadastrarCliente.getContentPane().add(cpfCadInterTextField);

		consultarCliente = new JInternalFrame("Consultar Cliente");
		consultarCliente.setIconifiable(true);
		consultarCliente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		consultarCliente.setBounds(32, 300, 296, 376);
		desktop.add(consultarCliente);
		consultarCliente.getContentPane().setBackground(SystemColor.controlHighlight);
		consultarCliente.setBackground(SystemColor.control);
		consultarCliente.setClosable(true);

		JLabel cpfConsuInternalLabel = new JLabel("Cpf: ");
		cpfConsuInternalLabel.setBounds(6, 21, 25, 16);

		cpfConsuInternalTextField = new JTextField();
		cpfConsuInternalTextField.setBounds(37, 15, 241, 28);
		cpfConsuInternalTextField.setColumns(10);

		JButton pesConsuInternaBotao = new JButton("Pesquisar", imgPesquisar);
		pesConsuInternaBotao.setBounds(34, 52, 105, 28);
		pesConsuInternaBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Zera o campo de texto.
				relConsuInternalTextArea.setText("");

				// Pegando o nome do campo text field.
				String cpf = cpfConsuInternalTextField.getText();

				Cliente cliente = controleCliente.conCliente(cpf);

				if(cliente == null)
					cpfConsuInternalTextField.setText("");

				else{

					// Setando os valores do cliente na area de texto.
					relConsuInternalTextArea.setText(cliente.toString());

				}


			}
		});
		consultarCliente.getContentPane().setLayout(null);

		relConsuInternalTextArea = new JTextArea();
		relConsuInternalTextArea.setBounds(6, 92, 272, 247);
		relConsuInternalTextArea.setEditable(false);
		relConsuInternalTextArea.setLineWrap(true);
		relConsuInternalTextArea.setWrapStyleWord(true);
		consultarCliente.getContentPane().add(relConsuInternalTextArea);
		consultarCliente.getContentPane().add(cpfConsuInternalLabel);
		consultarCliente.getContentPane().add(cpfConsuInternalTextField);
		consultarCliente.getContentPane().add(pesConsuInternaBotao);

		btnCancelar = new JButton("Cancelar", imgCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				zeraCamposConsulta();

			}
		});
		btnCancelar.setBounds(152, 52, 105, 28);
		consultarCliente.getContentPane().add(btnCancelar);

		alterarCliente = new JInternalFrame("Alterar Cliente");
		alterarCliente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		alterarCliente.setIconifiable(true);
		alterarCliente.setBounds(376, 19, 630, 427);
		alterarCliente.getContentPane().setBackground(SystemColor.controlHighlight);
		alterarCliente.setBackground(SystemColor.control);
		alterarCliente.setClosable(true);
		desktop.add(alterarCliente);

		JLabel cpfAltPesInterLabel = new JLabel("Cpf:");
		cpfAltPesInterLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

		cpfAltPesInterTextField = new JTextField();
		cpfAltPesInterTextField.setColumns(10);

		cpfAltInterBotao = new JButton("Pesquisar", imgPesquisar);
		cpfAltInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pegando o nome do campo text field.
				String cpf = cpfAltPesInterTextField.getText();

				Cliente cliente = controleCliente.conCliente(cpf);

				if(cliente == null)
					;

				else{

					// Ativa os campos do alterar
					ativaCamposAlterar(cliente);

				}

			}
		});

		JLabel sepAltInterLabel = new JLabel("");
		sepAltInterLabel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));

		cpfAltInterLabel = new JLabel("CPF");

		cpfAltInterTextField = new JTextField();
		cpfAltInterTextField.setColumns(10);

		nomeAltInterLabel = new JLabel("Nome");

		nomeAltInterTextField = new JTextField();
		nomeAltInterTextField.setColumns(10);

		logAltInterLabel = new JLabel("Logradouro");

		logAltInterTextField = new JTextField();
		logAltInterTextField.setColumns(10);

		cidadeAltInterLabel = new JLabel("Cidade");

		cidadeAltInterTextField = new JTextField();
		cidadeAltInterTextField.setColumns(10);

		bairroAltInterLabel = new JLabel("Bairro");

		bairroAltInterTextField = new JTextField();
		bairroAltInterTextField.setColumns(10);

		numeroAltInterLabel = new JLabel("N\u00FAmero");

		numeroAltInterTextField = new JTextField();
		numeroAltInterTextField.setColumns(10);

		JLabel mainAltInterLabel = new JLabel("Alterar");
		mainAltInterLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

		compleAltInterLabel = new JLabel("Complemento");

		compAltInterTextField = new JTextField();
		compAltInterTextField.setColumns(10);

		telAltInterLabel = new JLabel("Telefone");

		telAltInterTextField = new JTextField();
		telAltInterTextField.setColumns(10);

		celAltInterLabel = new JLabel("Celular");

		celAltInterTextField = new JTextField();
		celAltInterTextField.setColumns(10);

		alterarAltInterBotao = new JButton("Alterar", imgAlterarEditar);
		alterarAltInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				alterarCliente();

			}
		});

		cancelAltInterBotao = new JButton("Cancelar", imgCancelar);
		cancelAltInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				desativaCamposAlterar();

			}
		});
		GroupLayout groupLayout_2 = new GroupLayout(alterarCliente.getContentPane());
		groupLayout_2.setHorizontalGroup(
				groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_2.createSequentialGroup()
						.addGap(151)
						.addComponent(cpfAltPesInterLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cpfAltPesInterTextField, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cpfAltInterBotao))
				.addComponent(sepAltInterLabel, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout_2.createSequentialGroup()
						.addGap(282)
						.addComponent(mainAltInterLabel))
				.addGroup(groupLayout_2.createSequentialGroup()
						.addGap(35)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout_2.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(compAltInterTextField, Alignment.LEADING)
										.addComponent(cidadeAltInterTextField, Alignment.LEADING)
										.addComponent(cpfAltInterLabel, Alignment.LEADING)
										.addComponent(cpfAltInterTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
										.addComponent(cidadeAltInterLabel, Alignment.LEADING))
								.addComponent(compleAltInterLabel))
						.addGap(34)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout_2.createParallelGroup(Alignment.LEADING, false)
										.addComponent(telAltInterTextField)
										.addComponent(bairroAltInterTextField)
										.addComponent(nomeAltInterLabel)
										.addComponent(nomeAltInterTextField, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
										.addComponent(bairroAltInterLabel))
								.addComponent(telAltInterLabel))
						.addGap(49)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(numeroAltInterLabel)
								.addComponent(logAltInterTextField, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
								.addComponent(logAltInterLabel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(numeroAltInterTextField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(celAltInterLabel)
								.addComponent(celAltInterTextField)))
				.addGroup(groupLayout_2.createSequentialGroup()
						.addGap(207)
						.addComponent(alterarAltInterBotao)
						.addGap(18)
						.addComponent(cancelAltInterBotao))
				);
		groupLayout_2.setVerticalGroup(
				groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_2.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(cpfAltPesInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cpfAltInterBotao)
								.addComponent(cpfAltPesInterLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(sepAltInterLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(mainAltInterLabel)
						.addGap(46)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(cpfAltInterLabel)
								.addComponent(nomeAltInterLabel)
								.addComponent(logAltInterLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.LEADING)
								.addComponent(cpfAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(nomeAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(logAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout_2.createSequentialGroup()
										.addComponent(cidadeAltInterLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cidadeAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout_2.createSequentialGroup()
										.addComponent(bairroAltInterLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(bairroAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout_2.createSequentialGroup()
										.addComponent(numeroAltInterLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(numeroAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout_2.createSequentialGroup()
										.addComponent(compleAltInterLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(compAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout_2.createSequentialGroup()
										.addComponent(telAltInterLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(telAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout_2.createSequentialGroup()
										.addComponent(celAltInterLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(celAltInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(40)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(alterarAltInterBotao)
								.addComponent(cancelAltInterBotao))
						.addGap(15))
				);
		alterarCliente.getContentPane().setLayout(groupLayout_2);

		listarCliente = new JInternalFrame("Listar Clientes");
		listarCliente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		listarCliente.setIconifiable(true);
		listarCliente.setBounds(741, 300, 281, 385);
		listarCliente.getContentPane().setBackground(SystemColor.controlHighlight);
		listarCliente.getContentPane().setLayout(null);
		desktop.add(listarCliente);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 44, 269, 311);
		listarCliente.getContentPane().add(scrollPane);

		nomeListaInterTextField = new JTextField();
		nomeListaInterTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String prefixo = nomeListaInterTextField.getText();
				geraTabelaListar(listaPersonalizada(listaCliente, prefixo));

			}
		});
		nomeListaInterTextField.setBounds(58, 11, 201, 28);
		listarCliente.getContentPane().add(nomeListaInterTextField);
		nomeListaInterTextField.setColumns(10);

		JLabel lblNome = new JLabel("Cpf:");
		lblNome.setBounds(19, 17, 29, 16);
		listarCliente.getContentPane().add(lblNome);
		listarCliente.setClosable(true);
		listarCliente.setBackground(SystemColor.control);

		excluirCliente = new JInternalFrame("Excluir Cliente");
		excluirCliente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		excluirCliente.setIconifiable(true);
		excluirCliente.setClosable(true);
		excluirCliente.getContentPane().setBackground(SystemColor.controlHighlight);

		cpfPesExcluirInterLabel = new JLabel("Cpf:");

		cpfPesExcluirInterTextField = new JTextField();
		cpfPesExcluirInterTextField.setColumns(10);

		cpfPesExcluirInterBotao = new JButton("Pesquisar", imgPesquisar);
		cpfPesExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluirSetaValores();

			}
		});

		cpfPesExcluirInterTextArea = new JTextArea();
		cpfPesExcluirInterTextArea.setWrapStyleWord(true);
		cpfPesExcluirInterTextArea.setEditable(false);
		cpfPesExcluirInterTextArea.setLineWrap(true);

		excluirExcluirInterBotao = new JButton("Excluir", imgDelete);
		excluirExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluiCliente();

			}
		});

		cancelarExcluirInterBotao = new JButton("Cancelar", imgCancelar);
		cancelarExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cpfPesExcluirInterTextField.setEnabled(true);
				desativaCamposExcluir();

			}
		});
		GroupLayout groupLayout_3 = new GroupLayout(excluirCliente.getContentPane());
		groupLayout_3.setHorizontalGroup(
				groupLayout_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_3.createSequentialGroup()
						.addContainerGap(83, Short.MAX_VALUE)
						.addComponent(excluirExcluirInterBotao)
						.addGap(18)
						.addComponent(cancelarExcluirInterBotao)
						.addGap(64))
				.addGroup(Alignment.LEADING, groupLayout_3.createSequentialGroup()
						.addGap(14)
						.addGroup(groupLayout_3.createParallelGroup(Alignment.LEADING)
								.addComponent(cpfPesExcluirInterTextArea, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout_3.createSequentialGroup()
										.addComponent(cpfPesExcluirInterLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cpfPesExcluirInterTextField, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cpfPesExcluirInterBotao)))
						.addContainerGap(10, Short.MAX_VALUE))
				);
		groupLayout_3.setVerticalGroup(
				groupLayout_3.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_3.createSequentialGroup()
						.addGap(21)
						.addGroup(groupLayout_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(cpfPesExcluirInterLabel)
								.addComponent(cpfPesExcluirInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cpfPesExcluirInterBotao))
						.addGap(18)
						.addComponent(cpfPesExcluirInterTextArea, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(excluirExcluirInterBotao)
								.addComponent(cancelarExcluirInterBotao))
						.addContainerGap())
				);
		excluirCliente.getContentPane().setLayout(groupLayout_3);
		excluirCliente.setBounds(360, 320, 359, 376);
		desktop.add(excluirCliente);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(desktop, GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(desktop, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE))
				);
		gl_panel.setAutoCreateGaps(true);
		panel.setLayout(gl_panel);

		// Se o tipoFuncionario for C - Colaborador: desabilite o menuExcluir.
		if(tipoFuncionario.getTipoUsuario() == 'C')
			menuExcluir.setEnabled(false);

		setVisible(true);
		setLocationRelativeTo(null);

	}// Fim do construtor


	/** Insere um novo cliente no sistema.*/
	private static void insereCliente(){

		String cpf = cpfCadInterTextField.getText();
		String nome = nomeCadInterTextField.getText();
		String log = logCadInterTextField.getText();
		String cidade = cidadeCadInterTextField.getText();
		String bairro = bairroCadInterTextField.getText();
		String number = numeroCadInterTextField.getText();
		String comple = compleCadInterTextField.getText();
		String tel = telCadInterTextField.getText();
		String cel = celCadInterTextField.getText();

		ControleCliente controle = new ControleCliente();

		if(controle.cadCliente(cpf, nome, log, cidade, bairro, number, comple, tel, cel) == true){

			zeraCamposCadastrar();
			lista();
			geraTabelaListar(listaCliente);
		}

	}// insereCliente()

	/* ************************ Métodos para zerar os campos das janelas internas ************************ */
	/** Zera os campos da janela interna consulta  */
	private void zeraCamposConsulta(){

		cpfConsuInternalTextField.setText("");
		relConsuInternalTextArea.setText("");

	}

	/** Zera os campos da janela interna cadastrar  */
	private static void zeraCamposCadastrar(){

		cpfCadInterTextField.setText("");
		nomeCadInterTextField.setText("");
		logCadInterTextField.setText("");
		cidadeCadInterTextField.setText("");
		bairroCadInterTextField.setText("");
		numeroCadInterTextField.setText("");
		compleCadInterTextField.setText("");
		telCadInterTextField.setText("");
		celCadInterTextField.setText("");

	}

	/** Desativa os campos da janela interna Alterar na parte de alterar novos campos.
	 * Também zera os campos.*/
	private void desativaCamposAlterar(){

		//cpfAltPesInterTextField.setText("");
		cpfAltPesInterTextField.setEnabled(true);
		cpfAltInterBotao.setEnabled(true);

		alterarAltInterBotao.setEnabled(false);

		cpfAltInterLabel.setEnabled(false);
		cpfAltInterTextField.setEnabled(false);
		cpfAltInterTextField.setText("");

		nomeAltInterLabel.setEnabled(false);
		nomeAltInterTextField.setEnabled(false);
		nomeAltInterTextField.setText("");

		logAltInterLabel.setEnabled(false);
		logAltInterTextField.setEnabled(false);
		logAltInterTextField.setText("");

		cidadeAltInterLabel.setEnabled(false);
		cidadeAltInterTextField.setEnabled(false);
		cidadeAltInterTextField.setText("");

		bairroAltInterLabel.setEnabled(false);
		bairroAltInterTextField.setEnabled(false);
		bairroAltInterTextField.setText("");

		numeroAltInterLabel.setEnabled(false);
		numeroAltInterTextField.setEnabled(false);
		numeroAltInterTextField.setText("");

		compleAltInterLabel.setEnabled(false);
		compAltInterTextField.setEnabled(false);
		compAltInterTextField.setText("");

		telAltInterLabel.setEnabled(false);
		telAltInterTextField.setEnabled(false);
		telAltInterTextField.setText("");

		celAltInterLabel.setEnabled(false);
		celAltInterTextField.setEnabled(false);
		celAltInterTextField.setText("");


		alterarAltInterBotao.setEnabled(false);
		cancelAltInterBotao.setEnabled(false);

	}

	/** Ativa os campos da janela interna Alterar na parte de alterar novos campos, e insere os dados nos campos que deseja alterar
	 * 
	 * @param Cliente objeto do tipo <code>Cliente</code>
	 * */
	private void ativaCamposAlterar(Cliente cliente){

		cpfAltPesInterTextField.setEnabled(false);
		cpfAltInterBotao.setEnabled(false);

		alterarAltInterBotao.setEnabled(true);

		cpfAltInterLabel.setEnabled(true);
		cpfAltInterTextField.setEnabled(true);
		cpfAltInterTextField.setText(cliente.getCpf());

		nomeAltInterLabel.setEnabled(true);
		nomeAltInterTextField.setEnabled(true);
		nomeAltInterTextField.setText(cliente.getNome());

		logAltInterLabel.setEnabled(true);
		logAltInterTextField.setEnabled(true);
		logAltInterTextField.setText(cliente.getLogradouro());

		cidadeAltInterLabel.setEnabled(true);
		cidadeAltInterTextField.setEnabled(true);
		cidadeAltInterTextField.setText(cliente.getCidade());

		bairroAltInterLabel.setEnabled(true);
		bairroAltInterTextField.setEnabled(true);
		bairroAltInterTextField.setText(cliente.getBairro());

		numeroAltInterLabel.setEnabled(true);
		numeroAltInterTextField.setEnabled(true);
		numeroAltInterTextField.setText(cliente.getNumero() + "");

		compleAltInterLabel.setEnabled(true);
		compAltInterTextField.setEnabled(true);
		compAltInterTextField.setText(cliente.getComplemento());

		telAltInterLabel.setEnabled(true);
		telAltInterTextField.setEnabled(true);
		telAltInterTextField.setText(cliente.getTelefoneFixo());

		celAltInterLabel.setEnabled(true);
		celAltInterTextField.setEnabled(true);
		celAltInterTextField.setText(cliente.getTelefoneMovel());


		alterarAltInterBotao.setEnabled(true);
		cancelAltInterBotao.setEnabled(true);

	}

	private void alterarCliente(){

		Cliente cliente;

		String cpf = cpfAltInterTextField.getText();
		String nome = nomeAltInterTextField.getText();
		String log = logAltInterTextField.getText();
		String cidade = cidadeAltInterTextField.getText();
		String bairro = bairroAltInterTextField.getText();
		String number = numeroAltInterTextField.getText();
		String comple = compAltInterTextField.getText();
		String tel = telAltInterTextField.getText();
		String cel = celAltInterTextField.getText();

		// Verifica a integridade do numero.
		int numero = 0;
		try{
			numero = Integer.parseInt(number);

			cliente = new Cliente(cpf, nome, log, comple, bairro, cidade, tel, cel, numero);

			if(controleCliente.altCliente(cpfAltPesInterTextField.getText(), cliente) == true){

				// Zera todos os campos.
				desativaCamposAlterar();

				// Atualiza a tabela listar.
				lista();
				geraTabelaListar(listaCliente);

			}
			else
				desativaCamposAlterar();


		}

		catch(NumberFormatException ex){

			// Executa o aviso sonoro de erro.

			EntradaESaida.msgErro("Número inválido", 
					ConstantesClientes.TITULO_CLIENTE_INSERIR.valor());

		}

	}// alterarCliente()

	/** Desativa os campos da janela interna Excluir na parte de excluir Funcionário.
	 * Também zera os campos.*/
	/*private void desativaCamposExcluir(){

		areaTextoInternalExcluir.setEnabled(false);
		areaTextoInternalExcluir.setText("");

		excluirInternalExcluirBotao.setEnabled(false);
		cancelarInternalExcluirBotao.setEnabled(false);

		nomeInternalExcluirTextField.setText("");

	}*/

	/** Ativa os campos da janela interna Excluir na parte de excluir Funcionário*/
	/*	private void ativaCamposExcluir(){

		areaTextoInternalExcluir.setEnabled(true);

		excluirInternalExcluirBotao.setEnabled(true);
		cancelarInternalExcluirBotao.setEnabled(true);

	}*/

	/** Gera uma tabela com todos os clientes vindo do banco de dados.
	 * 
	 * @param listaCliente2 possui os valores que serão setados na tabela.
	 * */
	private static void geraTabelaListar(ArrayList<Pizzaria> listaCliente2){

		if(listaCliente2 == null)
			System.out.println("assad");

		else{

			// Gera a tabela com todos os valores do banco de dados.
			String campos[] = {"N°", "Cpf", "Nome"};
			String t[][] = new String[listaCliente2.size()][3];

			for(int l = 0, x = 0; l < listaCliente2.size(); l++, x++)
				for(int c = 0; c < 3; c++){

					if(c == 0)
						t[l][c] = x + "";

					if(c == 1)
						t[l][c] = ((Cliente) listaCliente2.get(x)).getCpf();

					// Colocar ******* na senha.
					if(c == 2)
						t[l][c] = ((Cliente) listaCliente2.get(x)).getNome();


				}

			tabelaInternalListar = new JTable(t, campos);
			scrollPane.setViewportView(tabelaInternalListar);

		}

	}

	/* ****************************** Lista de clientes *****************************/
	/** Recebe uma lista com todos os clientes salvos no banco de dados. */
	private static void lista(){

		listaCliente = controleCliente.listaClientes();

	}// lista()

	/* ****************************** Personaliza lista clientes *****************************/
	/** Personaliza a lista de cliente apenas com os clientes cujo a descricação comece 
	 * com a string passada pelo usuário. 
	 * 
	 * @param listaCliente2 contendo os clientes.
	 * @param start uma string contendo o prefixo que será pesquisado na tabela.
	 * 
	 * @return lista com apenas os cliente começados com o prefixo passado pelo usuário.
	 * */

	private ArrayList<Pizzaria> listaPersonalizada(ArrayList<Pizzaria> listaCliente2, String start){

		if(listaCliente2 == null)
			return null;

		ArrayList<Pizzaria>listaPersonalizada = new ArrayList<>();

		for(int contador = 0; contador < listaCliente2.size(); contador++)
			if(((Cliente) listaCliente2.get(contador)).getCpf().toLowerCase().startsWith(start) == true)
				listaPersonalizada.add((Cliente) listaCliente2.get(contador));


		return listaPersonalizada;

	}// listaPersonalizada()


	/* ****************************** Excluir clientes *****************************/
	/** Pesquisa e seta os valores obtidos na consulta. */
	private void excluirSetaValores(){

		Cliente cliente = controleCliente.conCliente(cpfPesExcluirInterTextField.getText());

		if(cliente == null){

			// Se o cliente não for achado, desativa os campos.
			desativaCamposExcluir();
			return;
		}

		ativaCamposExcluir();

		// Desativa o campo de pesquisa para o usuário não fazer gracinha.
		cpfPesExcluirInterTextField.setEnabled(false);

		// Seta os valores antigos.
		cpfPesExcluirInterTextArea.setText(cliente.toString());


	}// excluirSetaValores()


	/** Exclui o cliente setado na área de texto.*/
	private void excluiCliente(){

		// Verifica se o campo de pesquisa se está vazio, caso sim, não faça nada.
		if(cpfPesExcluirInterTextField.getText().equalsIgnoreCase("") == true)
			return;

		if(controleCliente.excCliente(cpfPesExcluirInterTextField.getText()) == true){

			// Ativa o campo de pesquisa e limpa a área de texto.
			cpfPesExcluirInterTextField.setText("");
			cpfPesExcluirInterTextField.setEnabled(true);
			cpfPesExcluirInterTextArea.setText("");

			// Atualiza a tabela.
			lista();
			geraTabelaListar(listaCliente);
		}

		// Ativa o campo de pesquisa e limpa a área de texto.
		cpfPesExcluirInterTextField.setText("");
		cpfPesExcluirInterTextField.setEnabled(true);
		cpfPesExcluirInterTextArea.setText("");
		desativaCamposExcluir();

	}// excluiCliente()


	/**  Desativa os campos da janela interna excluirCliente.*/
	private void desativaCamposExcluir(){


		cpfPesExcluirInterTextField.setText("");

		cpfPesExcluirInterTextArea.setText("");
		cpfPesExcluirInterTextArea.setEnabled(false);

		excluirExcluirInterBotao.setEnabled(false);
		cancelarExcluirInterBotao.setEnabled(false);

	}// desativaCamposAlterar()

	/**  Ativa os campos da janela interna excluirCliente.*/
	private void ativaCamposExcluir(){


		cpfPesExcluirInterTextField.setEnabled(true);

		cpfPesExcluirInterTextArea.setEnabled(true);

		excluirExcluirInterBotao.setEnabled(true);
		cancelarExcluirInterBotao.setEnabled(true);

	}// AtivaCamposAlterar()
}
