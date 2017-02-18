package tsi.too.pv.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import tsi.too.pv.controle.ConstantesFuncionarios;
import tsi.too.pv.controle.ControleFuncionario;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Pizzaria;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class FuncionarioGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar barraDeMenu;
	private JMenu opcoesMenu;
	private JMenuItem sairMenuItem;
	private JMenu consultarMenu;
	private JMenu alterarMenu;
	private JMenu excluirMenu;
	private JMenu cadastrarMenu;
	private JMenuItem cadastrarMenuItem;
	private JMenuItem consultarMenuItem;
	private JMenuItem alterarMenuItem;
	private JMenuItem excluirMenuItem;
	private JInternalFrame cadastrarFuncionario;
	private JLabel nomeInternalLabel;
	private static JTextField nomeInternalTextField;
	private JLabel senhaInternalLabel;
	private static JPasswordField senhaInternalPwd;
	private static JComboBox<Object> tipoInternalComboBox;
	private JLabel tipoInternalLabel;
	private JButton cadastrarInternalBotao;
	private JButton cancelarInternalBotao;
	private JInternalFrame consultarFuncionario;
	private JTextField nomeInternalConsultaTextField;
	private JButton pesquisaInternalConsultaBotao;
	private JTextArea areaTextoConsultaInternal;
	private JMenuItem listarMenuItem;
	private JInternalFrame listarFuncionario;
	private static JTable tabelaInternalListar;
	private JInternalFrame alterarFuncionario;
	private JTextField nomeInternalAlterarTextField;
	private JButton editarInternalAlterarBotao;
	private JTextField nNomeInternalAlterarTextField;
	private JPasswordField nSenhaInternalAlterarPwd;
	private JComboBox<Object> tipoInternalAltearComboBox;
	private JButton alterarInternalAlterarBotao;
	private JButton cancelarInternalAlterarBotao;
	private JLabel nomeInternalAlterarLabel;
	private JLabel nNomeInternalAltearLabel;
	private JLabel nSenhaInternalAlterar;
	private JLabel sepInternalAlterarLabel;
	private JLabel sepAltInternalAlterarLabel;
	private JLabel tipoInternalAlterarLabel;
	private JInternalFrame excluirFuncionario;
	private static JTextField nomeInternalExcluirTextField;
	private static JButton pesquisarInternalExcluirBotao;
	private static JTextArea areaTextoInternalExcluir;
	private static JButton excluirInternalExcluirBotao;
	private static JButton cancelarInternalExcluirBotao;
	private static JScrollPane scrollPaneInteranlListar;
	private JDesktopPane desktop;
	private JTextField nomeListaTextField;
	private JLabel nomeListaLabel;

	private static ArrayList<Pizzaria>listaFuncionario;
	private static ControleFuncionario controleFuncionario;
	private JPanel panel_1;
	private JPanel panel;
	private static JLabel nomeInternalExcluirLabel;


	/**
	 * Create the frame.
	 */
	public FuncionarioGui(Funcionario tipoFuncionario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FuncionarioGui.class.getResource("/img/pizzaria.png")));

		ImageIcon imgGravar = new ImageIcon(this.getClass().getResource("/img/gravar.png"));
		ImageIcon imgAlterarEditar = new ImageIcon(this.getClass().getResource("/img/edita.png"));
		ImageIcon imgPesquisar = new ImageIcon(this.getClass().getResource("/img/pesquisa.png"));
		ImageIcon imgCancelar = new ImageIcon(this.getClass().getResource("/img/cancelar.png"));
		ImageIcon imgDelete = new ImageIcon(this.getClass().getResource("/img/delete.png"));
		ImageIcon imgCadastrar = new ImageIcon(this.getClass().getResource("/img/cadastrar.png"));
		ImageIcon imgListar = new ImageIcon(this.getClass().getResource("/img/lista.png"));
		//ImageIcon imgLogout = new ImageIcon(this.getClass().getResource("/img/logout.png"));
		ImageIcon imgSair = new ImageIcon(this.getClass().getResource("/img/sair.png"));


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
		listaFuncionario = new ArrayList<>();
		controleFuncionario = new ControleFuncionario();
		setTitle("Controle - Funcion\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 705);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		barraDeMenu = new JMenuBar();
		barraDeMenu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		barraDeMenu.setForeground(SystemColor.info);
		barraDeMenu.setBackground(SystemColor.inactiveCaption);
		contentPane.add(barraDeMenu, BorderLayout.NORTH);

		opcoesMenu = new JMenu("Op\u00E7\u00F5es");
		barraDeMenu.add(opcoesMenu);

		/* *************************** Evento relacionado ao menu sair  *************************** */
		sairMenuItem = new JMenuItem("Sair", imgSair);
		sairMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		sairMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cadastrarFuncionario.dispose();
				consultarFuncionario.dispose();
				listarFuncionario.dispose();
				alterarFuncionario.dispose();
				excluirFuncionario.dispose();
				// dispose na janela atual e chamar a tela de login.
				dispose();
				new JanelaPrincipalGui(tipoFuncionario);


			}
		});
		opcoesMenu.add(sairMenuItem);

		cadastrarMenu = new JMenu("Cadastrar");
		cadastrarMenu.setMnemonic('c');
		barraDeMenu.add(cadastrarMenu);

		/* *************************** Evento relacionado ao menu Cadastrar  *************************** */
		cadastrarMenuItem = new JMenuItem("Cadastrar", imgCadastrar);
		cadastrarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		cadastrarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

				cadastrarFuncionario.setVisible(true);
				// Zera os campos cadastrarInternal
				zeraCamposCadastrar();
			}
		});
		cadastrarMenu.add(cadastrarMenuItem);

		consultarMenu = new JMenu("Consultar");
		consultarMenu.setMnemonic('o');
		barraDeMenu.add(consultarMenu);

		/* *************************** Evento relacionado ao menu Consultar  *************************** */
		consultarMenuItem = new JMenuItem("Consultar", imgPesquisar);
		consultarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		consultarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
				consultarFuncionario.setVisible(true);

				// Zera os campos consultaInternal
				zeraCamposConsulta();

			}
		});
		consultarMenu.add(consultarMenuItem);

		/* *************************** Evento relacionado ao menu Listar  *************************** */
		listarMenuItem = new JMenuItem("Listar", imgListar);
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
				//zeraCamposCadastrar();

				// Libera a janela interna consultarFuncionario.
				//consultarFuncionario.dispose();
				// Zera os campos consultaInternal
				//zeraCamposConsulta();

				listarFuncionario.setVisible(true);

				listaFuncionario = controleFuncionario.listaFuncionarios();
				// Gera a tabela.
				geraTabelaListar(listaFuncionario);


			}
		});
		consultarMenu.add(listarMenuItem);

		alterarMenu = new JMenu("Alterar");
		alterarMenu.setMnemonic('a');
		barraDeMenu.add(alterarMenu);

		/* *************************** Evento relacionado ao menu Alterar  *************************** */
		alterarMenuItem = new JMenuItem("Alterar", imgAlterarEditar);
		alterarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		alterarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

				alterarFuncionario.setVisible(true);

				// Desativando os campos alterar.
				desativaCamposAlterar();

			}
		});
		alterarMenu.add(alterarMenuItem);

		excluirMenu = new JMenu("Excluir");
		excluirMenu.setMnemonic('e');
		barraDeMenu.add(excluirMenu);

		/* *************************** Evento relacionado ao menu Excluir  *************************** */
		excluirMenuItem = new JMenuItem("Excluir", imgDelete);
		excluirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		excluirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Libera a janela interna cadastrarFuncionario.
				//cadastrarFuncionario.dispose();
				// Zera os campos cadastrarInternal
				//zeraCamposCadastrar();

				// Libera a janela interna consultarFuncionario.
				//consultarFuncionario.dispose();
				// Zera os campos consultaInternal
				//zeraCamposConsulta();

				// Libera a janela interna listaFuncionario
				//listarFuncionario.dispose();

				// Libera a janela interna alterarFuncionario
				//alterarFuncionario.dispose();

				excluirFuncionario.setVisible(true);
				desativaCamposExcluir();


			}
		});
		excluirMenu.add(excluirMenuItem);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		desktop = new JDesktopPane();
		desktop.setLayout(null);

		cadastrarFuncionario = new JInternalFrame("Cadastar Funcion\u00E1rio");
		cadastrarFuncionario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		cadastrarFuncionario.setIconifiable(true);
		cadastrarFuncionario.setBounds(71, 6, 336, 290);
		desktop.add(cadastrarFuncionario);
		cadastrarFuncionario.getContentPane().setBackground(SystemColor.control);
		cadastrarFuncionario.setClosable(true);
		cadastrarFuncionario.setBackground(SystemColor.controlHighlight);
		cadastrarFuncionario.getContentPane().setLayout(null);

		nomeInternalLabel = new JLabel("Nome:");
		nomeInternalLabel.setBounds(44, 30, 43, 16);
		cadastrarFuncionario.getContentPane().add(nomeInternalLabel);

		nomeInternalTextField = new JTextField();
		nomeInternalTextField.setBounds(90, 24, 165, 28);
		cadastrarFuncionario.getContentPane().add(nomeInternalTextField);
		nomeInternalTextField.setColumns(10);

		senhaInternalLabel = new JLabel("Senha:");
		senhaInternalLabel.setBounds(44, 78, 43, 16);
		cadastrarFuncionario.getContentPane().add(senhaInternalLabel);

		senhaInternalPwd = new JPasswordField();
		senhaInternalPwd.setBounds(90, 72, 165, 28);
		cadastrarFuncionario.getContentPane().add(senhaInternalPwd);

		tipoInternalComboBox = new JComboBox<Object>();
		tipoInternalComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Administrador", "Colaborador", "Gerente"}));
		tipoInternalComboBox.setBounds(90, 121, 165, 26);
		cadastrarFuncionario.getContentPane().add(tipoInternalComboBox);

		tipoInternalLabel = new JLabel("Tipo:");
		tipoInternalLabel.setBounds(44, 126, 43, 16);
		cadastrarFuncionario.getContentPane().add(tipoInternalLabel);

		cadastrarInternalBotao = new JButton("Gravar", imgGravar);
		cadastrarInternalBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gravaFuncionario();

			}
		});
		cadastrarInternalBotao.setBounds(58, 173, 98, 28);
		cadastrarFuncionario.getContentPane().add(cadastrarInternalBotao);

		cancelarInternalBotao = new JButton("Cancelar", imgCancelar);
		cancelarInternalBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nomeInternalTextField.setText("");
				senhaInternalPwd.setText("");

			}
		});

		cancelarInternalBotao.setBounds(168, 173, 98, 28);
		cadastrarFuncionario.getContentPane().add(cancelarInternalBotao);
		// Cadastrar como a primeira janela

		excluirFuncionario = new JInternalFrame("Excluir Funcion\u00E1rio");
		excluirFuncionario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		excluirFuncionario.setIconifiable(true);
		excluirFuncionario.setBounds(855, 6, 336, 290);
		desktop.add(excluirFuncionario);
		excluirFuncionario.setClosable(true);
		excluirFuncionario.getContentPane().setLayout(null);

		nomeInternalExcluirLabel = new JLabel("Nome:");
		nomeInternalExcluirLabel.setBounds(6, 22, 42, 16);
		excluirFuncionario.getContentPane().add(nomeInternalExcluirLabel);

		nomeInternalExcluirTextField = new JTextField();
		nomeInternalExcluirTextField.setBounds(48, 16, 156, 28);
		excluirFuncionario.getContentPane().add(nomeInternalExcluirTextField);
		nomeInternalExcluirTextField.setColumns(10);

		pesquisarInternalExcluirBotao = new JButton("Pesquisar", imgPesquisar);
		pesquisarInternalExcluirBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pesquisaExclusao();

			}
		});
		pesquisarInternalExcluirBotao.setBounds(211, 16, 108, 28);
		excluirFuncionario.getContentPane().add(pesquisarInternalExcluirBotao);

		areaTextoInternalExcluir = new JTextArea();
		areaTextoInternalExcluir.setEditable(false);
		areaTextoInternalExcluir.setLineWrap(true);
		areaTextoInternalExcluir.setWrapStyleWord(true);
		areaTextoInternalExcluir.setBounds(6, 56, 313, 161);
		excluirFuncionario.getContentPane().add(areaTextoInternalExcluir);


		excluirInternalExcluirBotao = new JButton("Excluir", imgDelete);
		excluirInternalExcluirBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluirFuncionario();
			}
		});
		excluirInternalExcluirBotao.setBounds(58, 222, 90, 28);
		excluirFuncionario.getContentPane().add(excluirInternalExcluirBotao);

		cancelarInternalExcluirBotao = new JButton("Cancelar", imgCancelar);
		cancelarInternalExcluirBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				desativaCamposExcluir();
			}
		});
		cancelarInternalExcluirBotao.setBounds(165, 222, 98, 28);
		excluirFuncionario.getContentPane().add(cancelarInternalExcluirBotao);

		alterarFuncionario = new JInternalFrame("Alterar Funcion\u00E1rio");
		alterarFuncionario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		alterarFuncionario.setIconifiable(true);
		alterarFuncionario.setBounds(22, 305, 358, 333);
		desktop.add(alterarFuncionario);
		alterarFuncionario.getContentPane().setBackground(SystemColor.controlHighlight);
		alterarFuncionario.setClosable(true);
		alterarFuncionario.getContentPane().setLayout(null);

		nomeInternalAlterarLabel = new JLabel("Nome:");
		nomeInternalAlterarLabel.setBounds(28, 23, 42, 16);
		alterarFuncionario.getContentPane().add(nomeInternalAlterarLabel);

		nomeInternalAlterarTextField = new JTextField();
		nomeInternalAlterarTextField.setBounds(70, 17, 158, 28);
		alterarFuncionario.getContentPane().add(nomeInternalAlterarTextField);
		nomeInternalAlterarTextField.setColumns(10);

		editarInternalAlterarBotao = new JButton("Pesquisar", imgPesquisar);
		editarInternalAlterarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Seta valores nos campos da internalFrameAlt.
				alteraSetaValores();

			}
		});
		editarInternalAlterarBotao.setBounds(229, 17, 110, 28);
		alterarFuncionario.getContentPane().add(editarInternalAlterarBotao);

		nNomeInternalAltearLabel = new JLabel("Nome:");
		nNomeInternalAltearLabel.setBounds(28, 118, 42, 16);
		alterarFuncionario.getContentPane().add(nNomeInternalAltearLabel);

		nNomeInternalAlterarTextField = new JTextField();
		nNomeInternalAlterarTextField.setBounds(75, 112, 264, 28);
		alterarFuncionario.getContentPane().add(nNomeInternalAlterarTextField);
		nNomeInternalAlterarTextField.setColumns(10);

		nSenhaInternalAlterar = new JLabel("Senha:");
		nSenhaInternalAlterar.setBounds(28, 158, 42, 16);
		alterarFuncionario.getContentPane().add(nSenhaInternalAlterar);

		nSenhaInternalAlterarPwd = new JPasswordField();
		nSenhaInternalAlterarPwd.setBounds(75, 152, 264, 28);
		alterarFuncionario.getContentPane().add(nSenhaInternalAlterarPwd);

		sepInternalAlterarLabel = new JLabel("");
		sepInternalAlterarLabel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		sepInternalAlterarLabel.setBounds(6, 57, 578, 16);
		alterarFuncionario.getContentPane().add(sepInternalAlterarLabel);

		sepAltInternalAlterarLabel = new JLabel("ALTERAR");
		sepAltInternalAlterarLabel.setBounds(148, 71, 55, 16);
		alterarFuncionario.getContentPane().add(sepAltInternalAlterarLabel);

		tipoInternalAltearComboBox = new JComboBox<Object>();
		tipoInternalAltearComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Administrador", "Colaborador", "Gerente"}));
		tipoInternalAltearComboBox.setBounds(82, 194, 136, 26);
		alterarFuncionario.getContentPane().add(tipoInternalAltearComboBox);

		tipoInternalAlterarLabel = new JLabel("Tipo:");
		tipoInternalAlterarLabel.setBounds(38, 199, 32, 16);
		alterarFuncionario.getContentPane().add(tipoInternalAlterarLabel);

		alterarInternalAlterarBotao = new JButton("Alterar", imgGravar);
		alterarInternalAlterarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				alteraFuncionario();


			}
		});
		alterarInternalAlterarBotao.setBounds(70, 251, 90, 28);
		alterarFuncionario.getContentPane().add(alterarInternalAlterarBotao);
		cancelarInternalAlterarBotao = new JButton("Cancelar", imgCancelar);
		cancelarInternalAlterarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				desativaCamposAlterar();

			}
		});
		cancelarInternalAlterarBotao.setBounds(172, 251, 98, 28);
		alterarFuncionario.getContentPane().add(cancelarInternalAlterarBotao);


		consultarFuncionario = new JInternalFrame("Consultar Funcion\u00E1rio");
		consultarFuncionario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		consultarFuncionario.setIconifiable(true);
		consultarFuncionario.setBounds(497, 6, 319, 290);
		desktop.add(consultarFuncionario);
		consultarFuncionario.setBackground(SystemColor.controlHighlight);
		consultarFuncionario.setClosable(true);
		consultarFuncionario.getContentPane().setLayout(null);

		JLabel nomeInternalConsultaLabel = new JLabel("Nome:");
		nomeInternalConsultaLabel.setBounds(6, 12, 37, 16);
		consultarFuncionario.getContentPane().add(nomeInternalConsultaLabel);

		nomeInternalConsultaTextField = new JTextField();
		nomeInternalConsultaTextField.setBounds(55, 6, 246, 28);
		consultarFuncionario.getContentPane().add(nomeInternalConsultaTextField);
		nomeInternalConsultaTextField.setColumns(10);

		pesquisaInternalConsultaBotao = new JButton("Pesquisar", imgPesquisar);
		pesquisaInternalConsultaBotao.setMnemonic('p');
		pesquisaInternalConsultaBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				consultaFuncionario();
				
			}
		});
		pesquisaInternalConsultaBotao.setBounds(39, 40, 108, 28);
		consultarFuncionario.getContentPane().add(pesquisaInternalConsultaBotao);

		areaTextoConsultaInternal = new JTextArea();
		areaTextoConsultaInternal.setEditable(false);
		areaTextoConsultaInternal.setLineWrap(true);
		areaTextoConsultaInternal.setWrapStyleWord(true);
		areaTextoConsultaInternal.setBounds(6, 73, 295, 180);
		consultarFuncionario.getContentPane().add(areaTextoConsultaInternal);
		
		JButton btnCancelar = new JButton("Cancelar", imgCancelar);
		btnCancelar.setMnemonic('c');
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				zeraCamposConsulta();
				
			}
		});
		btnCancelar.setBounds(159, 40, 108, 28);
		consultarFuncionario.getContentPane().add(btnCancelar);

		listarFuncionario = new JInternalFrame("Listar Funcion\u00E1rios");
		listarFuncionario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		listarFuncionario.setIconifiable(true);
		listarFuncionario.setBounds(405, 308, 425, 316);
		desktop.add(listarFuncionario);
		listarFuncionario.setClosable(true);
		listarFuncionario.setBackground(SystemColor.controlHighlight);
		listarFuncionario.getContentPane().setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 54, 413, 270);
		listarFuncionario.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		scrollPaneInteranlListar = new JScrollPane();
		scrollPaneInteranlListar.setBounds(0, 0, 413, 230);
		panel_1.add(scrollPaneInteranlListar);

		nomeListaTextField = new JTextField();
		nomeListaTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				String prefixo = nomeListaTextField.getText();
				geraTabelaListar(listaPersonalizada(listaFuncionario, prefixo));

			}
		});
		nomeListaTextField.setBounds(128, 15, 216, 28);
		listarFuncionario.getContentPane().add(nomeListaTextField);
		nomeListaTextField.setColumns(10);

		nomeListaLabel = new JLabel("Nome: ");
		nomeListaLabel.setBounds(80, 20, 47, 16);
		listarFuncionario.getContentPane().add(nomeListaLabel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(desktop, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(desktop, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
				);
		panel.setLayout(gl_panel);

		/* ********************************** Cadastrar Funcionário ********************************** */

		/* ********************************** Botão Cancelar ********************************** */


		setLocationRelativeTo(null);
		setVisible(true);

	}// FuncionarioGui

	/* ************************ Métodos para zerar os campos das janelas internas ************************ */
	/** Zera os campos da janela interna consulta  */
	private void zeraCamposConsulta(){

		nomeInternalConsultaTextField.setText("");
		areaTextoConsultaInternal.setText("");

	}

	/** Zera os campos da janela interna cadastrar  */
	private void zeraCamposCadastrar(){

		nomeInternalTextField.setText("");
		senhaInternalPwd.setText("");

	}


	/** Seta valores do funcionário nos campos da janela interna responsável pela alteração.
	 * */
	private void alteraSetaValores(){

		// Pegando o nome do campo text field.
		String nome = nomeInternalAlterarTextField.getText();

		Funcionario func = controleFuncionario.conFunc(nome);

		if(func == null)
			return;

		else{

			// Ativa os campos do alterar
			ativaCamposAlterar();

			// Setando os valores do funcionário na area de texto.
			nNomeInternalAlterarTextField.setText(func.getNomeUsuario());
			nSenhaInternalAlterarPwd.setText(func.getSenha());

			// Setando o tipo de funcionário escolhido.
			if(func.getTipoUsuario() == 'A')
				tipoInternalAltearComboBox.setSelectedIndex(0);
			else if(func.getTipoUsuario() == 'C')
				tipoInternalAltearComboBox.setSelectedIndex(1);
			else if(func.getTipoUsuario() == 'G')
				tipoInternalAltearComboBox.setSelectedIndex(2);


		}

	}// alteraSetaValores()

	/** Altera as informações de um funcionário. */
	private void alteraFuncionario(){

		Funcionario func = new Funcionario();
		String nome = nNomeInternalAlterarTextField.getText();
		String senha = String.valueOf(nSenhaInternalAlterarPwd.getPassword());
		String tp = tipoInternalAltearComboBox.getSelectedItem() + "";
		tp = tp.substring(0, 1);

		func.setNomeUsuario(nome);
		func.setSenha(senha);
		func.setTipoUsuario(tp.charAt(0));

		if(controleFuncionario.altFunc(nomeInternalAlterarTextField.getText(), func) == true){

			// Zera todos os campos.
			desativaCamposAlterar();
			
			// Zera o campo de pesquisa.
			nomeInternalAlterarTextField.setText("");
			
			listaFuncionario = controleFuncionario.listaFuncionarios();
			geraTabelaListar(listaFuncionario);

			EntradaESaida.msgInfo("Alteração feita com sucesso", ConstantesFuncionarios.
					TITULO_FUNCIONARIO_ALTERAR.valor());

		}

		else{

			desativaCamposAlterar();

		}

	}// alteraFuncionario()

	/** Desativa os campos da janela interna Alterar na parte de alterar novos campos.
	 * Também zera os campos.*/
	private void desativaCamposAlterar(){

		nNomeInternalAltearLabel.setEnabled(false);
		nNomeInternalAlterarTextField.setEnabled(false);
		nSenhaInternalAlterar.setEnabled(false);
		nSenhaInternalAlterarPwd.setEnabled(false);
		tipoInternalAlterarLabel.setEnabled(false);
		tipoInternalAltearComboBox.setEnabled(false);
		alterarInternalAlterarBotao.setEnabled(false);
		cancelarInternalAlterarBotao.setEnabled(false);

		nNomeInternalAlterarTextField.setText("");
		nSenhaInternalAlterarPwd.setText("");
		tipoInternalAltearComboBox.setSelectedIndex(0);
		
		//nomeInternalAlterarTextField.setText("");

		nomeInternalAlterarLabel.setEnabled(true);
		nomeInternalAlterarTextField.setEnabled(true);
		editarInternalAlterarBotao.setEnabled(true);

	}

	/** Ativa os campos da janela interna Alterar na parte de alterar novos campos*/
	private void ativaCamposAlterar(){


		nomeInternalAlterarLabel.setEnabled(false);
		nomeInternalAlterarTextField.setEnabled(false);
		editarInternalAlterarBotao.setEnabled(false);

		nNomeInternalAltearLabel.setEnabled(true);
		nNomeInternalAlterarTextField.setEnabled(true);
		nSenhaInternalAlterar.setEnabled(true);
		nSenhaInternalAlterarPwd.setEnabled(true);
		tipoInternalAlterarLabel.setEnabled(true);
		tipoInternalAltearComboBox.setEnabled(true);
		alterarInternalAlterarBotao.setEnabled(true);
		cancelarInternalAlterarBotao.setEnabled(true);

	}

	/** Desativa os campos da janela interna Excluir na parte de excluir Funcionário.
	 * Também zera os campos.*/
	private void desativaCamposExcluir(){

		areaTextoInternalExcluir.setEnabled(false);
		areaTextoInternalExcluir.setText("");

		excluirInternalExcluirBotao.setEnabled(false);
		cancelarInternalExcluirBotao.setEnabled(false);

		nomeInternalExcluirTextField.setEnabled(true);
		pesquisarInternalExcluirBotao.setEnabled(true);
		nomeInternalExcluirLabel.setEnabled(true);
		nomeInternalExcluirTextField.setText("");

	}

	/** Ativa os campos da janela interna Excluir na parte de excluir Funcionário*/
	private static void ativaCamposExcluir(){

		// Desativa a consulta.
		nomeInternalExcluirTextField.setEnabled(false);
		pesquisarInternalExcluirBotao.setEnabled(false);
		nomeInternalExcluirLabel.setEnabled(false);
		
		
		areaTextoInternalExcluir.setEnabled(true);

		excluirInternalExcluirBotao.setEnabled(true);
		cancelarInternalExcluirBotao.setEnabled(true);

	}

	/** Gera uma tabela com todos os funcionários vindo do banco de dados
	 * @param arrayList */
	private static void geraTabelaListar(ArrayList<Pizzaria> arrayList){

		if(arrayList == null)
			return;

		else{

			// Gera a tabela com todos os valores do banco de dados.
			String campos[] = {"N°", "Nome", "Senha", "Tipo"};
			String t[][] = new String[arrayList.size()][4];

			for(int l = 0, x = 0; l < arrayList.size(); l++, x++)
				for(int c = 0; c < 4; c++){

					if(c == 0)
						t[l][c] = x + "";

					if(c == 1)
						t[l][c] = ((Funcionario) arrayList.get(x)).getNomeUsuario();

					// Colocar ******* na senha.
					if(c == 2)
						t[l][c] = ((Funcionario) arrayList.get(x)).getSenha();

					if(c == 3)
						t[l][c] = String.valueOf(((Funcionario) arrayList.get(x)).getTipoUsuario());

				}

			tabelaInternalListar = new JTable(t, campos);
			scrollPaneInteranlListar.setViewportView(tabelaInternalListar);

		}


	}// geraTabela()

	/** Grava um funcionário no banco de dados */
	private static void gravaFuncionario(){

		String nome = nomeInternalTextField.getText();
		String senha = String.valueOf(senhaInternalPwd.getPassword());
		String tp = tipoInternalComboBox.getSelectedItem() + "";
		tp = tp.substring(0, 1);
		ControleFuncionario controleFunc = new ControleFuncionario();

		if(controleFunc.cadFunc(nome, senha, tp) == true){

			nomeInternalTextField.setText("");
			senhaInternalPwd.setText("");
			// Gera a tabela.
			listaFuncionario = controleFuncionario.listaFuncionarios();
			geraTabelaListar(listaFuncionario);

		}

	}// gravaFuncionario()

	/** Pesquisa no banco de dados o Funcionário cujo o nome foi passado. */
	private static void pesquisaExclusao(){

		// Pegando o nome do campo text field.
		String nome = nomeInternalExcluirTextField.getText();

		ControleFuncionario controleFunc = new ControleFuncionario();
		Funcionario func = controleFunc.conFunc(nome);

		if(func == null)
			;

		else{

			// Ativa os campos do excluir
			ativaCamposExcluir();

			// Setando os valores do funcionário na area de texto.
			areaTextoInternalExcluir.setText(func.toString());

		}

	}// pesquisaExclusao()

	/** Exclui do banco de dados o funcionário setado na janela interna. */
	private void excluirFuncionario(){

		String nome = nomeInternalExcluirTextField.getText();

		if(controleFuncionario.excFunc(nome) == true){
			desativaCamposExcluir();
			listaFuncionario = controleFuncionario.listaFuncionarios();
			geraTabelaListar(listaFuncionario);
			
			// Zera os campos da janela interna alterar, para evitar que o usuário faça gracinha.
			// Tal como alterar um funcionário que foi deletado do sistema.
			//desativaCamposAlterar();
			//zeraCamposConsulta();
		}

		else
			desativaCamposExcluir();


	}// excluiFuncionario

	/* ****************************** Personaliza lista Funcionario *****************************/
	/** Personaliza a lista de Funcionario apenas com os ingredientes cujo a descricação comece 
	 * com a string passada pelo usuário. 
	 * 
	 * @param listaIngredientes2 contendo os ingredientes.
	 * @param start uma string contendo o prefixo que será pesquisado na tabela.
	 * 
	 * @return lista com apenas os ingrediente começados com o prefixo passado pelo usuário.
	 * */

	private ArrayList<Pizzaria> listaPersonalizada(ArrayList<Pizzaria> listaFuncionario2, String start){

		if(listaFuncionario2 == null)
			return null;

		ArrayList<Pizzaria>listaPersonalizada = new ArrayList<>();

		for(int contador = 0; contador < listaFuncionario2.size(); contador++)
			if(((Funcionario) listaFuncionario2.get(contador)).getNomeUsuario().toLowerCase().startsWith(start) == true)
				listaPersonalizada.add((Funcionario) listaFuncionario2.get(contador));


		return listaPersonalizada;

	}// listaPersonalizada()
	
	/** Faz a consulta de un funcionário no sistema. */
	private void consultaFuncionario(){
		
		String nome = nomeInternalConsultaTextField.getText();

		Funcionario func = controleFuncionario.conFunc(nome);

		if(func == null){
			//nomeInternalConsultaTextField.setText("");
			areaTextoConsultaInternal.setText("");
		}
		else{

			areaTextoConsultaInternal.setText(func.toString());

		}

		
	}// consultaFuncionario
	
}// Fim da classe FuncionarioGui
