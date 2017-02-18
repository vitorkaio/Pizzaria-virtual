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

import tsi.too.pv.controle.ControleIngrediente;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pizzaria;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class IngredienteGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleIngrediente controleIngrediente;
	private ArrayList<Pizzaria> listaIngredientes;

	private JPanel contentPane;
	private JInternalFrame cadastrarIngrediente;
	private JMenuItem menuItemCadastrar;
	private JTextField desCadInterTextField;
	private JTextField precoCadInterTextField;
	private JInternalFrame listarIngrediente;
	private JMenuItem menuItemListar;
	private JTable tabelaInternalListar;
	private JScrollPane scrollPane;
	private JInternalFrame consultarIngrediente;
	private JTextField desConInterTextField;
	private JTextField desListarInterTextField;
	private JTextArea desConInterTextArea;
	private JMenuItem menuItemConsultar;
	private JInternalFrame alterarIngrediente;
	private JTextField pesDesAlterarInterTextField;
	private JButton pesDesAlterarInterBotao;
	private JTextField desAlterarInterTextField;
	private JTextField precoAlterarInterTextField;
	private JLabel desAlterarInterLabel;
	private JLabel precoAlterarInterLabel;
	private JButton altAlterarInterBotao;
	private JButton canAlterarInterBotao;
	private JMenu menuExcluir;
	private JLabel pesDesExcluirInterLabel;
	private JTextField pesDesExcluirInterTextField;
	private JButton pesDesExcluirInterBotao;
	private JTextArea pesDesExcluirInterTextArea;
	private JButton excluirExcluirInterBotao;
	private JButton cancelarExcluirInterBotao;
	private JLabel lblNome;
	private JDesktopPane desktop;
	private JPanel panel;
	private static JInternalFrame excluirIngrediente;

	/**
	 * Cria o frame do gerenciamento do ingrediente.
	 * 
	 * @param tipo referente ao funcionário que cadastrou, A - administrador: não tem acesso, 
	 * se for C - colaborador: poderá utilizar todas as operações do gerenciamento de do ingrediente menos o excluir, 
	 * se for G - gerente: possui acesso irrestrito.
	 */
	public IngredienteGui(Funcionario tipoFuncionario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IngredienteGui.class.getResource("/img/pizzaria.png")));
		setTitle("Controle - Ingrediente");

		controleIngrediente = new ControleIngrediente();
		listaIngredientes = new ArrayList<>();

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		desktop = new JDesktopPane();

		cadastrarIngrediente = new JInternalFrame("Cadastrar Ingrediente");
		cadastrarIngrediente.setBounds(24, 36, 277, 216);
		cadastrarIngrediente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		cadastrarIngrediente.getContentPane().setBackground(SystemColor.controlHighlight);
		cadastrarIngrediente.setClosable(true);
		cadastrarIngrediente.setIconifiable(true);

		listarIngrediente = new JInternalFrame("Listar Ingredientes");
		listarIngrediente.setIconifiable(true);
		listarIngrediente.setClosable(true);
		listarIngrediente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		listarIngrediente.setBounds(378, 36, 304, 366);

		JLabel desCadInterLabel = new JLabel("Descrica\u00E7\u00E3o");

		desCadInterTextField = new JTextField();
		desCadInterTextField.setColumns(10);

		JLabel precoCadInterLabel = new JLabel("Pre\u00E7o");

		precoCadInterTextField = new JTextField();
		precoCadInterTextField.setColumns(10);

		JButton btnGravar = new JButton("Gravar", imgGravar);
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Chama o método para cadastrar os dados do ingrediente.
				cadastraDadosIngrediente();

			}
		});

		JButton btnCancelar = new JButton("Cancelar", imgCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				zeraCamposCadastrar();

			}
		});
		GroupLayout groupLayout = new GroupLayout(cadastrarIngrediente.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(desCadInterTextField, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
												.addComponent(desCadInterLabel)
												.addComponent(precoCadInterLabel)
												.addComponent(precoCadInterTextField, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
										.addContainerGap())
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(btnGravar)
										.addGap(18)
										.addComponent(btnCancelar)
										.addGap(27))))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(15)
						.addComponent(desCadInterLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(desCadInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(precoCadInterLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(precoCadInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGravar)
								.addComponent(btnCancelar))
						.addContainerGap(12, Short.MAX_VALUE))
				);
		cadastrarIngrediente.getContentPane().setLayout(groupLayout);
		desktop.setLayout(null);
		desktop.add(listarIngrediente);
		listarIngrediente.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 46, 292, 289);
		listarIngrediente.getContentPane().add(scrollPane);

		desListarInterTextField = new JTextField();
		desListarInterTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evento) {

				String prefixo = desListarInterTextField.getText();
				geraTabelaListar(listaPersonalizada(listaIngredientes, prefixo));

			}
		});
		desListarInterTextField.setBounds(69, 6, 189, 28);
		listarIngrediente.getContentPane().add(desListarInterTextField);
		desListarInterTextField.setColumns(10);

		lblNome = new JLabel("Nome: ");
		lblNome.setBounds(24, 12, 55, 16);
		listarIngrediente.getContentPane().add(lblNome);
		desktop.add(cadastrarIngrediente);

		consultarIngrediente = new JInternalFrame("Consultar Ingrediente");
		consultarIngrediente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		consultarIngrediente.setClosable(true);
		consultarIngrediente.setIconifiable(true);
		consultarIngrediente.getContentPane().setBackground(SystemColor.controlHighlight);
		consultarIngrediente.setBounds(34, 260, 332, 296);
		desktop.add(consultarIngrediente);

		JLabel desConInterLabel = new JLabel("Descri\u00E7\u00E3o:");
		desConInterLabel.setBounds(6, 12, 59, 16);

		desConInterTextField = new JTextField();
		desConInterTextField.setBounds(71, 6, 243, 28);
		desConInterTextField.setColumns(10);

		desConInterTextArea = new JTextArea();
		desConInterTextArea.setBounds(6, 76, 308, 183);
		desConInterTextArea.setEditable(false);
		desConInterTextArea.setWrapStyleWord(true);
		desConInterTextArea.setLineWrap(true);

		JButton desConInterBotao = new JButton("Pesquisar", imgPesquisar);
		desConInterBotao.setBounds(41, 42, 105, 28);
		desConInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				consultaIngrediente();

			}
		});
		consultarIngrediente.getContentPane().setLayout(null);
		
		JButton btnCancelar_1 = new JButton("Cancelar", imgCancelar);
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				zeraCamposConsultar();
				
			}
		});
		btnCancelar_1.setBounds(161, 42, 105, 28);
		consultarIngrediente.getContentPane().add(btnCancelar_1);
		consultarIngrediente.getContentPane().add(desConInterTextArea);
		consultarIngrediente.getContentPane().add(desConInterLabel);
		consultarIngrediente.getContentPane().add(desConInterTextField);
		consultarIngrediente.getContentPane().add(desConInterBotao);

		alterarIngrediente = new JInternalFrame("Alterar Ingrediente");
		alterarIngrediente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		alterarIngrediente.getContentPane().setBackground(SystemColor.controlHighlight);
		alterarIngrediente.setClosable(true);
		alterarIngrediente.setIconifiable(true);
		alterarIngrediente.setBounds(705, 36, 347, 312);
		desktop.add(alterarIngrediente);

		JLabel pesDesAlterarInterLabel = new JLabel("Descri\u00E7\u00E3o:");

		pesDesAlterarInterTextField = new JTextField();
		pesDesAlterarInterTextField.setColumns(10);

		pesDesAlterarInterBotao = new JButton("Pesquisar", imgPesquisar);
		pesDesAlterarInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				alterarSetaValores();

			}
		});

		JLabel sepAlterarInterLabel = new JLabel("");
		sepAlterarInterLabel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));

		JLabel altAlterarInterLabel = new JLabel("Alterar");

		desAlterarInterLabel = new JLabel("Descri\u00E7\u00E3o");

		desAlterarInterTextField = new JTextField();
		desAlterarInterTextField.setColumns(10);

		precoAlterarInterLabel = new JLabel("Pre\u00E7o");

		precoAlterarInterTextField = new JTextField();
		precoAlterarInterTextField.setColumns(10);

		altAlterarInterBotao = new JButton("Alterar", imgAlterarEditar);
		altAlterarInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				alterarCadastra();

			}
		});

		canAlterarInterBotao = new JButton("Cancelar", imgCancelar);
		canAlterarInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pesDesAlterarInterTextField.setEnabled(true);
				desativaCamposAlterar();
			}
		});
		GroupLayout groupLayout_2 = new GroupLayout(alterarIngrediente.getContentPane());
		groupLayout_2.setHorizontalGroup(
				groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_2.createSequentialGroup()
						.addContainerGap()
						.addComponent(pesDesAlterarInterLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pesDesAlterarInterTextField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pesDesAlterarInterBotao)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(sepAlterarInterLabel, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
				.addGroup(groupLayout_2.createSequentialGroup()
						.addContainerGap()
						.addComponent(desAlterarInterLabel)
						.addContainerGap(253, Short.MAX_VALUE))
				.addGroup(groupLayout_2.createSequentialGroup()
						.addContainerGap()
						.addComponent(desAlterarInterTextField, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(groupLayout_2.createSequentialGroup()
						.addContainerGap()
						.addComponent(precoAlterarInterLabel)
						.addContainerGap(277, Short.MAX_VALUE))
				.addGroup(groupLayout_2.createSequentialGroup()
						.addContainerGap()
						.addComponent(precoAlterarInterTextField, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(groupLayout_2.createSequentialGroup()
						.addGap(139)
						.addComponent(altAlterarInterLabel)
						.addContainerGap(146, Short.MAX_VALUE))
				.addGroup(groupLayout_2.createSequentialGroup()
						.addGap(72)
						.addComponent(altAlterarInterBotao)
						.addGap(18)
						.addComponent(canAlterarInterBotao)
						.addContainerGap(89, Short.MAX_VALUE))
				);
		groupLayout_2.setVerticalGroup(
				groupLayout_2.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_2.createSequentialGroup()
						.addGap(15)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(pesDesAlterarInterLabel)
								.addComponent(pesDesAlterarInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pesDesAlterarInterBotao))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(sepAlterarInterLabel, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(altAlterarInterLabel)
						.addGap(12)
						.addComponent(desAlterarInterLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(desAlterarInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(precoAlterarInterLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(precoAlterarInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(groupLayout_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(altAlterarInterBotao)
								.addComponent(canAlterarInterBotao))
						.addContainerGap(19, Short.MAX_VALUE))
				);
		alterarIngrediente.getContentPane().setLayout(groupLayout_2);

		excluirIngrediente = new JInternalFrame("Excluir Ingrediente");
		excluirIngrediente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		excluirIngrediente.getContentPane().setBackground(SystemColor.controlHighlight);

		pesDesExcluirInterLabel = new JLabel("Descri\u00E7\u00E3o: ");

		pesDesExcluirInterTextField = new JTextField();
		pesDesExcluirInterTextField.setColumns(10);

		pesDesExcluirInterBotao = new JButton("Pesquisar", imgPesquisar);
		pesDesExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluirSetaValores();

			}
		});

		pesDesExcluirInterTextArea = new JTextArea();
		pesDesExcluirInterTextArea.setEditable(false);
		pesDesExcluirInterTextArea.setLineWrap(true);
		pesDesExcluirInterTextArea.setWrapStyleWord(true);

		excluirExcluirInterBotao = new JButton("Excluir", imgDelete);
		excluirExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluiIngrediente();

			}
		});

		cancelarExcluirInterBotao = new JButton("Cancelar", imgCancelar);
		cancelarExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pesDesExcluirInterTextField.setEnabled(true);
				desativaCamposExcluir();

			}
		});
		GroupLayout groupLayout_3 = new GroupLayout(excluirIngrediente.getContentPane());
		groupLayout_3.setHorizontalGroup(
				groupLayout_3.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_3.createSequentialGroup()
						.addGroup(groupLayout_3.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout_3.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout_3.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(pesDesExcluirInterTextArea)
												.addGroup(Alignment.LEADING, groupLayout_3.createSequentialGroup()
														.addComponent(pesDesExcluirInterLabel)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(pesDesExcluirInterTextField, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(pesDesExcluirInterBotao))))
								.addGroup(groupLayout_3.createSequentialGroup()
										.addGap(84)
										.addComponent(excluirExcluirInterBotao)
										.addGap(18)
										.addComponent(cancelarExcluirInterBotao)))
						.addContainerGap(12, Short.MAX_VALUE))
				);
		groupLayout_3.setVerticalGroup(
				groupLayout_3.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_3.createSequentialGroup()
						.addGap(15)
						.addGroup(groupLayout_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(pesDesExcluirInterLabel)
								.addComponent(pesDesExcluirInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pesDesExcluirInterBotao))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pesDesExcluirInterTextArea, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(excluirExcluirInterBotao)
								.addComponent(cancelarExcluirInterBotao))
						.addContainerGap(32, Short.MAX_VALUE))
				);
		excluirIngrediente.getContentPane().setLayout(groupLayout_3);
		excluirIngrediente.setIconifiable(true);
		excluirIngrediente.setClosable(true);
		excluirIngrediente.setBounds(694, 360, 379, 263);
		desktop.add(excluirIngrediente);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(desktop, GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(desktop, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE))
				);
		gl_panel.setHonorsVisibility(false);
		panel.setLayout(gl_panel);

		JMenuBar barraDeMenu = new JMenuBar();
		barraDeMenu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		contentPane.add(barraDeMenu, BorderLayout.NORTH);

		JMenu menuOpcoes = new JMenu("Op\u00E7\u00F5es");
		barraDeMenu.add(menuOpcoes);

		JMenuItem menuItemSair = new JMenuItem("Sair", imgSair);
		menuItemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cadastrarIngrediente.dispose();
				consultarIngrediente.dispose();
				listarIngrediente.dispose();
				alterarIngrediente.dispose();
				excluirIngrediente.dispose();
				
				controleIngrediente.finaliza();
				dispose();
				new JanelaPrincipalGui(tipoFuncionario);

			}
		});
		menuOpcoes.add(menuItemSair);

		JMenu menuCadastrar = new JMenu("Cadastrar");
		menuCadastrar.setMnemonic('c');
		barraDeMenu.add(menuCadastrar);

		menuItemCadastrar = new JMenuItem("Cadastrar", imgCadastrar);
		menuItemCadastrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Ativa a janela interna cadastrarIngrediente.
				zeraCamposCadastrar();
				cadastrarIngrediente.setVisible(true);

			}
		});
		menuCadastrar.add(menuItemCadastrar);

		JMenu menuConsultar = new JMenu("Consultar");
		menuConsultar.setMnemonic('o');
		barraDeMenu.add(menuConsultar);

		menuItemConsultar = new JMenuItem("Consultar", imgPesquisar);
		menuItemConsultar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				zeraCamposConsultar();
				consultarIngrediente.setVisible(true);

			}
		});
		menuConsultar.add(menuItemConsultar);

		menuItemListar = new JMenuItem("Listar", imgListar);
		menuItemListar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				zeraCamposListar();
				listarIngrediente.setVisible(true);
				lista();
				geraTabelaListar(listaIngredientes);

			}
		});
		menuConsultar.add(menuItemListar);

		JMenu menuAlterar = new JMenu("Alterar");
		menuAlterar.setMnemonic('a');
		barraDeMenu.add(menuAlterar);

		JMenuItem menuItemAlterar = new JMenuItem("Alterar", imgAlterarEditar);
		menuItemAlterar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				desativaCamposAlterar();
				pesDesAlterarInterTextField.setEnabled(true);
				alterarIngrediente.setVisible(true);

			}
		});
		menuAlterar.add(menuItemAlterar);

		menuExcluir = new JMenu("Excluir");
		menuExcluir.setMnemonic('e');
		barraDeMenu.add(menuExcluir);

		JMenuItem menuItemExcluir = new JMenuItem("Excluir", imgDelete);
		menuItemExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluirIngrediente.setVisible(true);
				pesDesExcluirInterTextField.setEnabled(true);
				desativaCamposExcluir();

			}
		});
		menuExcluir.add(menuItemExcluir);

		// Se o tipoFuncionario for C - Colaborador: desabilite o menuExcluir.
		if(tipoFuncionario.getTipoUsuario() == 'C')
			menuExcluir.setEnabled(false);

		setVisible(true);
		setLocationRelativeTo(null);

	}// Fim do construtor

	/* ****************************** Insere Ingredientes *****************************/
	/** Entrada de dados do ingrediente.*/
	private void cadastraDadosIngrediente(){

		String descricao = desCadInterTextField.getText();
		String preco = precoCadInterTextField.getText();

		if(controleIngrediente.cadIngrediente(descricao, preco) == true){

			zeraCamposCadastrar();

			// Atualiza a tabela de ingredientes do interFrame: listarIngredientes.
			lista();
			geraTabelaListar(listaIngredientes);
		}

	}// cadastraIngrediente()


	/**  Zera os campos da janela interna cadastrarIngrediente.*/
	private void zeraCamposCadastrar(){

		desCadInterTextField.setText("");
		precoCadInterTextField.setText("");

	}// zeraCamposCadastrar()

	/**  Zera os campos da janela interna consultarIngrediente.*/
	private void zeraCamposConsultar(){

		desConInterTextField.setText("");
		desConInterTextArea.setText("");

	}// zeraCamposCadastrar()

	/**  Zera os campos da janela interna listarIngrediente.*/
	private void zeraCamposListar(){

		desListarInterTextField.setText("");

	}// zeraCamposCadastrar()

	/* ****************************** consulta Ingrediente *****************************/
	/** Consulta um ingrediente no banco de dados. */
	private void consultaIngrediente(){

		Ingrediente ingrediente = controleIngrediente.conIngrediente(desConInterTextField.getText());

		if(ingrediente == null){

			desConInterTextArea.setText("");
			return;
		}

		// Seta no textArea.
		desConInterTextArea.setText(ingrediente.toString());

	}// consultaIngrediente()

	/* ****************************** Lista de ingredientes *****************************/
	/** Recebe uma lista com todos os ingredientes salvos no banco de dados. */
	private void lista(){

		listaIngredientes = controleIngrediente.listaIngrediente();

	}

	/* ****************************** Gera uma tabela com os ingredientes *****************************/
	/** Gera uma tabela com todos os dados do arrayList.*/
	private void geraTabelaListar(ArrayList<Pizzaria> listaIngredientes2){

		if(listaIngredientes2 == null)
			return;

		else{

			// Gera a tabela com todos os valores do banco de dados.
			String campos[] = {"N°", "Nome", "Preço"};

			String t[][] = new String[listaIngredientes2.size()][3];

			for(int l = 0, x = 0; l < listaIngredientes2.size(); l++, x++)
				for(int c = 0; c < 3; c++){

					if(c == 0)
						t[l][c] = ((Ingrediente) listaIngredientes2.get(x)).getCodigo() + "";

					if(c == 1)
						t[l][c] = ((Ingrediente) listaIngredientes2.get(x)).getDescricao();

					// Colocar ******* na senha.
					if(c == 2)
						t[l][c] = ((Ingrediente) listaIngredientes2.get(x)).getPreco() + "";

				}

			tabelaInternalListar = new JTable(t, campos);
			scrollPane.setViewportView(tabelaInternalListar);

		}

	}// geraTabelaListar()


	/* ****************************** Personaliza lista ingredientes *****************************/
	/** Personaliza a lista de ingrediente apenas com os ingredientes cujo a descricação comece 
	 * com a string passada pelo usuário. 
	 * 
	 * @param listaIngredientes2 contendo os ingredientes.
	 * @param start uma string contendo o prefixo que será pesquisado na tabela.
	 * 
	 * @return lista com apenas os ingrediente começados com o prefixo passado pelo usuário.
	 * */

	private ArrayList<Pizzaria> listaPersonalizada(ArrayList<Pizzaria> listaIngredientes2, String start){

		if(listaIngredientes2 == null)
			return null;

		ArrayList<Pizzaria>listaPersonalizada = new ArrayList<>();

		for(int contador = 0; contador < listaIngredientes2.size(); contador++)
			if(((Ingrediente) listaIngredientes2.get(contador)).getDescricao().toLowerCase().startsWith(start) == true)
				listaPersonalizada.add((Ingrediente) listaIngredientes2.get(contador));


		return listaPersonalizada;

	}// listaPersonalizada()

	/* ****************************** alterar ingredientes *****************************/
	/** Pesquisa e seta os valores obtidos na consulta. */
	private void alterarSetaValores(){

		Ingrediente ingrediente = controleIngrediente.conIngrediente(pesDesAlterarInterTextField.getText());

		if(ingrediente == null)
			return;

		// Desativa o campo de pesquisa para o usuário não fazer gracinha.
		pesDesAlterarInterTextField.setEnabled(false);

		// Seta os valores antigos.
		desAlterarInterTextField.setText(ingrediente.getDescricao());
		precoAlterarInterTextField.setText(ingrediente.getPreco() + "");

		ativaCamposAlterar();

	}// alterarSetaValores()

	/** Atualiza o igrediente no banco de dados. */
	private void alterarCadastra(){

		Ingrediente ingrediente = new Ingrediente(desAlterarInterTextField.getText(), 
				EntradaESaida.stringToFloat(precoAlterarInterTextField.getText()));

		if(controleIngrediente.altIngrediente(pesDesAlterarInterTextField.getText(), ingrediente) == false){
			desativaCamposAlterar();
			pesDesAlterarInterTextField.setEnabled(true);
		}

		else{

			desativaCamposAlterar();
			pesDesAlterarInterTextField.setEnabled(true);
			pesDesAlterarInterTextField.setText("");

			lista();
			geraTabelaListar(listaIngredientes);
		}

	}

	/**  Desativa os campos da janela interna alterarIngrediente.*/
	private void desativaCamposAlterar(){

		//pesDesAlterarInterTextField.setText("");

		desAlterarInterTextField.setText("");
		desAlterarInterTextField.setEnabled(false);

		precoAlterarInterTextField.setText("");
		precoAlterarInterTextField.setEnabled(false);

		altAlterarInterBotao.setEnabled(false);
		canAlterarInterBotao.setEnabled(false);

	}// desativaCamposAlterar()

	/**  Ativa os campos da janela interna alterarIngrediente.*/
	private void ativaCamposAlterar(){

		desAlterarInterTextField.setEnabled(true);

		precoAlterarInterTextField.setEnabled(true);

		altAlterarInterBotao.setEnabled(true);
		canAlterarInterBotao.setEnabled(true);

	}// zeraCamposCadastrar()

	/* ****************************** Excluir ingredientes *****************************/
	/** Pesquisa e seta os valores obtidos na consulta. */
	private void excluirSetaValores(){

		Ingrediente ingrediente = controleIngrediente.conIngrediente(pesDesExcluirInterTextField.getText());

		if(ingrediente == null){

			// Se o ingrediente não for achado, desativa os campos.
			desativaCamposExcluir();
			return;
		}

		ativaCamposExcluir();

		// Desativa o campo de pesquisa para o usuário não fazer gracinha.
		pesDesExcluirInterTextField.setEnabled(false);

		// Seta os valores antigos.
		pesDesExcluirInterTextArea.setText(ingrediente.toString());


	}// excluirSetaValores()


	/** Exclui o ingrediente setado na área de texto.*/
	private void excluiIngrediente(){

		// Verifica se o campo de pesquisa se está vazio, caso sim, não faça nada.
		if(pesDesExcluirInterTextField.getText().equalsIgnoreCase("") == true)
			return;

		if(controleIngrediente.excIngrediente(pesDesExcluirInterTextField.getText()) == true){

			// Ativa o campo de pesquisa e limpa a área de texto.
			pesDesExcluirInterTextField.setText("");
			pesDesExcluirInterTextField.setEnabled(true);
			pesDesExcluirInterTextArea.setText("");

			// Atualiza a tabela.
			lista();
			geraTabelaListar(listaIngredientes);
		}

		// Ativa o campo de pesquisa e limpa a área de texto.
		pesDesExcluirInterTextField.setText("");
		pesDesExcluirInterTextField.setEnabled(true);
		pesDesExcluirInterTextArea.setText("");
		desativaCamposExcluir();

	}// excluiIngrediente()


	/**  Desativa os campos da janela interna excluirIngrediente.*/
	private void desativaCamposExcluir(){


		pesDesExcluirInterTextField.setText("");

		pesDesExcluirInterTextArea.setText("");
		pesDesExcluirInterTextArea.setEnabled(false);

		excluirExcluirInterBotao.setEnabled(false);
		cancelarExcluirInterBotao.setEnabled(false);

	}// desativaCamposAlterar()

	/**  Ativa os campos da janela interna excluirIngrediente.*/
	private void ativaCamposExcluir(){


		pesDesExcluirInterTextField.setEnabled(true);

		pesDesExcluirInterTextArea.setEnabled(true);

		excluirExcluirInterBotao.setEnabled(true);
		cancelarExcluirInterBotao.setEnabled(true);

	}// AtivaCamposAlterar()
}// Fim class IngredienteGui.
