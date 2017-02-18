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
import java.util.Calendar;

import javax.swing.ButtonGroup;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import tsi.too.pv.controle.ConstantesPizzas;
import tsi.too.pv.controle.ControlePizza;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pizza;
import tsi.too.pv.tipos.Pizzaria;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class PizzaGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem menuItemSair;
	private JMenuItem menuItemCadastrar;
	private JMenuItem menuItemConsultar;
	private JMenuItem menuItemListar;
	private JMenuItem menuItemAlterar;
	private static JTextField desCadInterTextField;
	private static JTextField precoCadInterTextField;
	private static JButton gravarCadInterBotao;
	private static JButton cancelarCadInterBotao;
	private static JInternalFrame cadastrarPizza;
	private static JInternalFrame listarPizza;
	private static JScrollPane scrollPane;
	private static JTable tabelaInternalListar;
	private static JInternalFrame consultarPizza;
	private static JLabel nomeConsInterLabel;
	private static JTextField nomeConsInterTextField;
	private static JButton pesnomeConsInterBotao;
	private static JTextArea pizzaConsInterTextArea;
	private static JInternalFrame alterarPizza;
	private static JLabel nomePesAltInterLabel;
	private static JTextField nomePesAltInterTextField;
	private static JButton pesAltInterBotao;
	private static JLabel sepAltInter;
	private static JLabel altAltInterLabel;
	private static JLabel nomeAltInterLabel;
	private static JTextField nomeAltInterTextField;
	private static JLabel tamAltInterLabel;
	private static JTextField tamAltInterTextField;
	@SuppressWarnings("rawtypes")
	private static JComboBox tamAltInterComboBox;
	private static JLabel dataAltInterLabel;
	private static JLabel precoAltInterLabel;
	private static JTextField precoAltInterTextField;
	private static JButton altAltInterBotao;
	private static JButton cancelAltInterBotao;
	private static JPanel painelDesktop;
	private static JDesktopPane dataConInterCheckBox;
	private static JLabel label;
	private static JTextField listListaInterTextField;
	@SuppressWarnings("unused")
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private static ArrayList<Pizzaria> listaPizza = new ArrayList<>();
	private static ControlePizza controlePizza = new ControlePizza();
	private static JTextField desPesExcluirInterTextField;
	private static JButton pesExcluirInterBotao;
	private static JButton excluirExcluirInterBotao;
	private static JButton cancelarExcluirInterBotao;
	private static JTextArea desPesExcluirInterTextArea;
	@SuppressWarnings("rawtypes")
	private static JComboBox tamAtualAltInterComboBox;
	@SuppressWarnings("rawtypes")
	private static JComboBox tamAtualExcluirIntercomboBox;
	private static JLabel nomeListarInterText;
	@SuppressWarnings("rawtypes")
	private static JComboBox diaCadastraComboBox;
	@SuppressWarnings("rawtypes")
	private static JComboBox mesCadastrarComboBox;
	@SuppressWarnings("rawtypes")
	private static JComboBox anoCadastrarComboBox;
	@SuppressWarnings("rawtypes")
	private static JComboBox diaAlterarComboBox;
	@SuppressWarnings("rawtypes")
	private static JComboBox mesAlterarComboBox;
	@SuppressWarnings("rawtypes")
	private static JComboBox anoAlterarComboBox;;

	private static ArrayList<Ingrediente>listaIngredientes;

	private static ArrayList<Ingrediente>listaIngredientesAlter;
	private static String nomeAlterar = "";
	private static int codigoPizzaAlterar = 0;

	@SuppressWarnings("rawtypes")
	private static JComboBox tamCadInterComboBox;
	private static  JButton ingredietesAlterInterBotao;
	private JScrollPane scroolConsultaInter;
	private JButton pesCancelarNomeConsInterBotao;
	@SuppressWarnings("rawtypes")
	private JComboBox pesComboBoxConsultaInert;
	private JLabel lblPreoIngrediente;
	private static JLabel precoIngredientesCadInterLabel;
	private static JLabel precoIngredientesAltInterLabel;
	private static JLabel desPesExcluirInterLabel;
	
	private static ArrayList<Float>listaPreco;
	private static JInternalFrame excluirPizza;

	/**
	 * Cria o frame do gerenciamento do ingrediente.
	 * 
	 * @param tipo referente ao funcionário que cadastrou, A - administrador: não tem acesso, 
	 * se for C - colaborador: poderá utilizar todas as operações do gerenciamento de do ingrediente menos o excluir, 
	 * se for G - gerente: possui acesso irrestrito.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PizzaGui(Funcionario tipoFuncionario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PizzaGui.class.getResource("/img/pizzaria.png")));

		ImageIcon imgGravar = new ImageIcon(this.getClass().getResource("/img/gravar.png"));
		ImageIcon imgAlterarEditar = new ImageIcon(this.getClass().getResource("/img/edita.png"));
		ImageIcon imgPesquisar = new ImageIcon(this.getClass().getResource("/img/pesquisa.png"));
		ImageIcon imgCancelar = new ImageIcon(this.getClass().getResource("/img/cancelar.png"));
		ImageIcon imgDelete = new ImageIcon(this.getClass().getResource("/img/delete.png"));
		ImageIcon imgCadastrar = new ImageIcon(this.getClass().getResource("/img/cadastrar.png"));
		ImageIcon imgListar = new ImageIcon(this.getClass().getResource("/img/lista.png"));
		ImageIcon imgSair = new ImageIcon(this.getClass().getResource("/img/sair.png"));

		ImageIcon imgIngrediente = new ImageIcon(this.getClass().getResource("/img/ingredientesAdd.png"));

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

		listaIngredientes = new ArrayList<>();
		listaIngredientesAlter = new ArrayList<>();
		listaPreco = new ArrayList<>();
		
		setTitle("Controle - Pizza");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		alterarPizza = new JInternalFrame("Alterar Pizza");
		alterarPizza.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		alterarPizza.setIconifiable(true);
		alterarPizza.setBounds(747, 16, 323, 509);
		alterarPizza.setClosable(true);

		nomePesAltInterLabel = new JLabel("Nome:");
		nomePesAltInterLabel.setBounds(6, 12, 37, 16);

		nomePesAltInterTextField = new JTextField();
		nomePesAltInterTextField.setBounds(49, 6, 145, 28);
		nomePesAltInterTextField.setColumns(10);

		pesAltInterBotao = new JButton("Pesquisar", imgPesquisar);
		pesAltInterBotao.setBounds(89, 39, 105, 28);
		pesAltInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				alterarSetaValores();
				//nomePesAltInterTextField.setEnabled(true);
				//tamAtualAltInterComboBox.setEnabled(true);
			}
		});

		sepAltInter = new JLabel("");
		sepAltInter.setBounds(6, 79, 299, 1);
		sepAltInter.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));

		altAltInterLabel = new JLabel("Alterar");
		altAltInterLabel.setBounds(124, 86, 35, 16);

		nomeAltInterLabel = new JLabel("Nome");
		nomeAltInterLabel.setBounds(6, 114, 34, 16);

		nomeAltInterTextField = new JTextField();
		nomeAltInterTextField.setBounds(6, 136, 299, 28);
		nomeAltInterTextField.setColumns(10);

		tamAltInterLabel = new JLabel("Tamanho");
		tamAltInterLabel.setBounds(6, 229, 53, 16);

		tamAltInterTextField = new JTextField();
		tamAltInterTextField.setBounds(65, 223, 35, 28);
		tamAltInterTextField.setColumns(10);

		tamAltInterComboBox = new JComboBox();
		tamAltInterComboBox.setBounds(6, 257, 299, 26);
		tamAltInterComboBox.setModel(new DefaultComboBoxModel(new String[] {"Pequena(20 cm)", "M\u00E9dia(30 cm)", "Grande(40 cm)"}));

		dataAltInterLabel = new JLabel("Data:");
		dataAltInterLabel.setBounds(26, 317, 29, 16);

		precoAltInterLabel = new JLabel("Pre\u00E7o");
		precoAltInterLabel.setBounds(6, 366, 32, 16);

		precoAltInterTextField = new JTextField();
		precoAltInterTextField.setBounds(6, 388, 299, 28);
		precoAltInterTextField.setColumns(10);

		altAltInterBotao = new JButton("Alterar", imgAlterarEditar);
		altAltInterBotao.setBounds(74, 428, 83, 28);
		altAltInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				alteraPizza();
			}
		});

		cancelAltInterBotao = new JButton("Cancelar", imgCancelar);
		cancelAltInterBotao.setBounds(163, 428, 98, 28);
		cancelAltInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				desativaCamposAlterar();
				nomePesAltInterTextField.setText("");

			}
		});

		tamAtualAltInterComboBox = new JComboBox();
		tamAtualAltInterComboBox.setBounds(200, 7, 93, 26);
		tamAtualAltInterComboBox.setModel(new DefaultComboBoxModel(new String[] {"Pequena", "M\u00E9dia", "Grande"}));

		diaAlterarComboBox = new JComboBox();
		diaAlterarComboBox.setBounds(73, 312, 49, 26);
		diaAlterarComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));

		mesAlterarComboBox = new JComboBox();
		mesAlterarComboBox.setBounds(134, 312, 47, 26);
		mesAlterarComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));

		anoAlterarComboBox = new JComboBox();
		anoAlterarComboBox.setBounds(193, 312, 68, 26);
		anoAlterarComboBox.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099"}));

		ingredietesAlterInterBotao = new JButton("Ingredientes", imgIngrediente);
		ingredietesAlterInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				alteraListaIngredientes(nomeAlterar);

			}
		});
		ingredietesAlterInterBotao.setBounds(89, 170, 117, 28);
		alterarPizza.getContentPane().setLayout(null);
		alterarPizza.getContentPane().add(nomeAltInterLabel);
		alterarPizza.getContentPane().add(nomeAltInterTextField);
		alterarPizza.getContentPane().add(pesAltInterBotao);
		alterarPizza.getContentPane().add(nomePesAltInterLabel);
		alterarPizza.getContentPane().add(nomePesAltInterTextField);
		alterarPizza.getContentPane().add(tamAtualAltInterComboBox);
		alterarPizza.getContentPane().add(altAltInterLabel);
		alterarPizza.getContentPane().add(sepAltInter);
		alterarPizza.getContentPane().add(altAltInterBotao);
		alterarPizza.getContentPane().add(cancelAltInterBotao);
		alterarPizza.getContentPane().add(ingredietesAlterInterBotao);
		alterarPizza.getContentPane().add(precoAltInterLabel);
		alterarPizza.getContentPane().add(precoAltInterTextField);
		alterarPizza.getContentPane().add(dataAltInterLabel);
		alterarPizza.getContentPane().add(diaAlterarComboBox);
		alterarPizza.getContentPane().add(mesAlterarComboBox);
		alterarPizza.getContentPane().add(anoAlterarComboBox);
		alterarPizza.getContentPane().add(tamAltInterComboBox);
		alterarPizza.getContentPane().add(tamAltInterLabel);
		alterarPizza.getContentPane().add(tamAltInterTextField);

		JLabel labelPrecoIngredientes = new JLabel("Ingredientes: R$");
		labelPrecoIngredientes.setEnabled(false);
		labelPrecoIngredientes.setBounds(114, 366, 92, 16);
		alterarPizza.getContentPane().add(labelPrecoIngredientes);

		precoIngredientesAltInterLabel = new JLabel("");
		precoIngredientesAltInterLabel.setEnabled(false);
		precoIngredientesAltInterLabel.setBounds(208, 366, 55, 16);
		alterarPizza.getContentPane().add(precoIngredientesAltInterLabel);

		consultarPizza = new JInternalFrame("Consultar Pizza");
		consultarPizza.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		consultarPizza.setIconifiable(true);
		consultarPizza.setBounds(100, 300, 331, 393);
		consultarPizza.getContentPane().setBackground(SystemColor.controlHighlight);
		consultarPizza.setClosable(true);

		nomeConsInterLabel = new JLabel("Nome:");
		nomeConsInterLabel.setBounds(6, 12, 37, 16);

		nomeConsInterTextField = new JTextField();
		nomeConsInterTextField.setBounds(49, 6, 163, 28);
		nomeConsInterTextField.setColumns(10);

		pesnomeConsInterBotao = new JButton("Pesquisar", imgPesquisar);
		pesnomeConsInterBotao.setBounds(59, 46, 105, 28);
		pesnomeConsInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				consultarPizza();
			}
		});
		consultarPizza.getContentPane().setLayout(null);

		scroolConsultaInter = new JScrollPane();
		scroolConsultaInter.setBounds(6, 87, 307, 269);

		pizzaConsInterTextArea = new JTextArea();
		scroolConsultaInter.setViewportView(pizzaConsInterTextArea);
		pizzaConsInterTextArea.setEditable(false);
		pizzaConsInterTextArea.setLineWrap(true);
		pizzaConsInterTextArea.setWrapStyleWord(true);
		consultarPizza.getContentPane().add(scroolConsultaInter);
		consultarPizza.getContentPane().add(nomeConsInterLabel);
		consultarPizza.getContentPane().add(nomeConsInterTextField);
		consultarPizza.getContentPane().add(pesnomeConsInterBotao);

		pesCancelarNomeConsInterBotao = new JButton("Cancelar", imgCancelar);
		pesCancelarNomeConsInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				zeraCamposConsulta();

			}
		});
		pesCancelarNomeConsInterBotao.setBounds(176, 47, 105, 28);
		consultarPizza.getContentPane().add(pesCancelarNomeConsInterBotao);

		pesComboBoxConsultaInert = new JComboBox();
		pesComboBoxConsultaInert.setModel(new DefaultComboBoxModel(new String[] {"Pequena", "M\u00E9dia", "Grande"}));
		pesComboBoxConsultaInert.setBounds(215, 7, 98, 26);
		consultarPizza.getContentPane().add(pesComboBoxConsultaInert);

		listarPizza = new JInternalFrame("Listar Pizza");
		listarPizza.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		listarPizza.setBounds(422, 16, 313, 383);
		listarPizza.setIconifiable(true);
		listarPizza.setClosable(true);
		listarPizza.getContentPane().setBackground(SystemColor.controlHighlight);
		listarPizza.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 75, 301, 277);
		listarPizza.getContentPane().add(scrollPane);

		JPanel conPainel = new JPanel();
		conPainel.setBounds(0, 0, 301, 72);
		listarPizza.getContentPane().add(conPainel);

		listListaInterTextField = new JTextField();

		// Pesquisa em tempo real.
		listListaInterTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				geraTabelaListar(consulta(listListaInterTextField.getText()));
			}
		});
		listListaInterTextField.setColumns(10);

		nomeListarInterText = new JLabel("Nome: ");
		GroupLayout gl_conPainel = new GroupLayout(conPainel);
		gl_conPainel.setHorizontalGroup(
				gl_conPainel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_conPainel.createSequentialGroup()
						.addContainerGap()
						.addComponent(nomeListarInterText)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(listListaInterTextField, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addGap(16))
				);
		gl_conPainel.setVerticalGroup(
				gl_conPainel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_conPainel.createSequentialGroup()
						.addGap(22)
						.addGroup(gl_conPainel.createParallelGroup(Alignment.BASELINE)
								.addComponent(listListaInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nomeListarInterText))
						.addContainerGap(22, Short.MAX_VALUE))
				);
		conPainel.setLayout(gl_conPainel);

		JLabel sepListInterLabel = new JLabel("");
		sepListInterLabel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		sepListInterLabel.setBounds(0, 73, 301, 1);
		listarPizza.getContentPane().add(sepListInterLabel);

		cadastrarPizza = new JInternalFrame("Cadastrar Pizza");
		cadastrarPizza.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		cadastrarPizza.setBounds(6, 6, 241, 409);
		cadastrarPizza.setIconifiable(true);
		cadastrarPizza.setClosable(true);
		cadastrarPizza.getContentPane().setBackground(SystemColor.controlHighlight);

		JLabel desCadInterLabel = new JLabel("Descri\u00E7\u00E3o");
		desCadInterLabel.setBounds(6, 6, 56, 16);

		desCadInterTextField = new JTextField();
		desCadInterTextField.setBounds(6, 28, 217, 28);
		desCadInterTextField.setColumns(10);

		JLabel tamCadInterLabel = new JLabel("Tamanho");
		tamCadInterLabel.setBounds(6, 111, 53, 16);

		JLabel dataCadInterLabel = new JLabel("Data");
		dataCadInterLabel.setBounds(6, 177, 26, 16);

		JLabel precoCadInterLabel = new JLabel("Pre\u00E7o");
		precoCadInterLabel.setBounds(6, 245, 32, 16);

		precoCadInterTextField = new JTextField();
		precoCadInterTextField.setBounds(6, 267, 217, 28);
		precoCadInterTextField.setColumns(10);

		gravarCadInterBotao = new JButton("Gravar", imgGravar);
		gravarCadInterBotao.setBounds(15, 330, 98, 28);
		gravarCadInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cadastrarPizza();

			}
		});

		cancelarCadInterBotao = new JButton("Cancelar", imgCancelar);
		cancelarCadInterBotao.setBounds(117, 330, 98, 28);
		cancelarCadInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				desCadInterTextField.setText("");
				diaCadastraComboBox.setSelectedIndex(0);
				mesCadastrarComboBox.setSelectedIndex(0);
				anoCadastrarComboBox.setSelectedIndex(0);
				precoCadInterTextField.setText("");
				listaIngredientes.clear();
				precoIngredientesCadInterLabel.setText("");

			}
		});

		label = new JLabel("");
		label.setBounds(212, 148, 0, 0);

		diaCadastraComboBox = new JComboBox();
		diaCadastraComboBox.setBounds(24, 199, 49, 26);
		diaCadastraComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));

		mesCadastrarComboBox = new JComboBox();
		mesCadastrarComboBox.setBounds(85, 199, 47, 26);
		mesCadastrarComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));

		anoCadastrarComboBox = new JComboBox();
		anoCadastrarComboBox.setBounds(144, 199, 68, 26);
		anoCadastrarComboBox.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099"}));
		cadastrarPizza.getContentPane().setLayout(null);
		cadastrarPizza.getContentPane().add(dataCadInterLabel);
		cadastrarPizza.getContentPane().add(precoCadInterLabel);
		cadastrarPizza.getContentPane().add(precoCadInterTextField);
		cadastrarPizza.getContentPane().add(desCadInterTextField);
		cadastrarPizza.getContentPane().add(gravarCadInterBotao);
		cadastrarPizza.getContentPane().add(cancelarCadInterBotao);
		cadastrarPizza.getContentPane().add(desCadInterLabel);
		cadastrarPizza.getContentPane().add(tamCadInterLabel);
		cadastrarPizza.getContentPane().add(diaCadastraComboBox);
		cadastrarPizza.getContentPane().add(mesCadastrarComboBox);
		cadastrarPizza.getContentPane().add(anoCadastrarComboBox);
		cadastrarPizza.getContentPane().add(label);

		JButton btnIngredientes = new JButton("Ingredientes", imgIngrediente);
		btnIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Adiciona ingredientes a pizza.
				adicionaIngredientes();

			}
		});
		contentPane.setLayout(new BorderLayout(0, 0));
		btnIngredientes.setBounds(52, 60, 117, 28);
		cadastrarPizza.getContentPane().add(btnIngredientes);

		tamCadInterComboBox = new JComboBox();
		tamCadInterComboBox.setModel(new DefaultComboBoxModel(new String[] {"Pequena(20 cm)", "M\u00E9dia(30 cm)", "Grande(40 cm)"}));
		tamCadInterComboBox.setBounds(6, 132, 206, 26);
		cadastrarPizza.getContentPane().add(tamCadInterComboBox);

		lblPreoIngrediente = new JLabel("Ingredientes: R$");
		lblPreoIngrediente.setEnabled(false);
		lblPreoIngrediente.setBounds(72, 245, 92, 16);
		cadastrarPizza.getContentPane().add(lblPreoIngrediente);

		precoIngredientesCadInterLabel = new JLabel("");
		precoIngredientesCadInterLabel.setEnabled(false);
		precoIngredientesCadInterLabel.setBounds(168, 245, 55, 16);
		cadastrarPizza.getContentPane().add(precoIngredientesCadInterLabel);

		painelDesktop = new JPanel();
		contentPane.add(painelDesktop,BorderLayout.CENTER);

		dataConInterCheckBox = new JDesktopPane();
		GroupLayout gl_painelDesktop = new GroupLayout(painelDesktop);
		gl_painelDesktop.setAutoCreateContainerGaps(true);
		gl_painelDesktop.setHonorsVisibility(false);
		gl_painelDesktop.setHorizontalGroup(
				gl_painelDesktop.createParallelGroup(Alignment.LEADING)
				.addComponent(dataConInterCheckBox, GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
				);
		gl_painelDesktop.setVerticalGroup(
				gl_painelDesktop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelDesktop.createSequentialGroup()
						.addComponent(dataConInterCheckBox, GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
						.addGap(0))
				);

		painelDesktop.setLayout(gl_painelDesktop);
		painelDesktop.add(dataConInterCheckBox);
		dataConInterCheckBox.setLayout(null);

		excluirPizza = new JInternalFrame("Excluir Pizza");
		excluirPizza.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		excluirPizza.getContentPane().setBackground(SystemColor.controlHighlight);

		desPesExcluirInterLabel = new JLabel("Descri\u00E7\u00E3o:");

		desPesExcluirInterTextField = new JTextField();
		desPesExcluirInterTextField.setColumns(10);

		pesExcluirInterBotao = new JButton("Pesquisar", imgPesquisar);
		pesExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				excluirSetaValores();

			}
		});

		desPesExcluirInterTextArea = new JTextArea();
		desPesExcluirInterTextArea.setEditable(false);
		desPesExcluirInterTextArea.setLineWrap(true);
		desPesExcluirInterTextArea.setWrapStyleWord(true);

		excluirExcluirInterBotao = new JButton("Excluir", imgDelete);
		excluirExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluiPizza();

			}
		});

		cancelarExcluirInterBotao = new JButton("Cancelar", imgCancelar);
		cancelarExcluirInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				desPesExcluirInterTextField.setEnabled(true);
				desPesExcluirInterTextField.setText("");
				desativaCamposExcluir();

			}
		});

		tamAtualExcluirIntercomboBox = new JComboBox();
		tamAtualExcluirIntercomboBox.setModel(new DefaultComboBoxModel(new String[] {"Pequena", "M\u00E9dia", "Grande"}));
		GroupLayout groupLayout_3 = new GroupLayout(excluirPizza.getContentPane());
		groupLayout_3.setHorizontalGroup(
				groupLayout_3.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_3.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout_3.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout_3.createSequentialGroup()
										.addComponent(desPesExcluirInterLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(desPesExcluirInterTextField, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tamAtualExcluirIntercomboBox, 0, 105, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING, groupLayout_3.createSequentialGroup()
										.addComponent(excluirExcluirInterBotao)
										.addGap(18)
										.addComponent(cancelarExcluirInterBotao)
										.addGap(73))
								.addGroup(groupLayout_3.createSequentialGroup()
										.addComponent(desPesExcluirInterTextArea, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
										.addContainerGap())))
				.addGroup(groupLayout_3.createSequentialGroup()
						.addGap(118)
						.addComponent(pesExcluirInterBotao)
						.addContainerGap(133, Short.MAX_VALUE))
				);
		groupLayout_3.setVerticalGroup(
				groupLayout_3.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_3.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(desPesExcluirInterLabel)
								.addComponent(desPesExcluirInterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tamAtualExcluirIntercomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
						.addComponent(pesExcluirInterBotao)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(desPesExcluirInterTextArea, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(excluirExcluirInterBotao)
								.addComponent(cancelarExcluirInterBotao))
						.addGap(16))
				);
		excluirPizza.getContentPane().setLayout(groupLayout_3);
		excluirPizza.setClosable(true);
		excluirPizza.setIconifiable(true);
		excluirPizza.setBounds(438, 320, 368, 321);
		dataConInterCheckBox.add(excluirPizza);
		dataConInterCheckBox.add(cadastrarPizza);
		dataConInterCheckBox.add(alterarPizza);
		dataConInterCheckBox.add(consultarPizza);
		dataConInterCheckBox.add(listarPizza);

		JMenuBar barraDeMenu = new JMenuBar();
		barraDeMenu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		barraDeMenu.setForeground(SystemColor.controlHighlight);
		barraDeMenu.setBackground(SystemColor.controlHighlight);
		getContentPane().add(barraDeMenu, BorderLayout.NORTH);

		JMenu menuOpcoes = new JMenu("Op\u00E7\u00F5es");
		barraDeMenu.add(menuOpcoes);

		menuItemSair = new JMenuItem("Sair", imgSair);
		menuItemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cadastrarPizza.dispose();
				consultarPizza.dispose();
				listarPizza.dispose();
				alterarPizza.dispose();
				excluirPizza.dispose();
				controlePizza.finaliza();
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

				cadastrarPizza.setVisible(true);
				// Zera os campos cadastrarInternal
				zeraCamposCadastrar();

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
				consultarPizza.setVisible(true);

				// Zera os campos consultaInternal
				zeraCamposConsulta();

			}
		});
		menuConsultar.add(menuItemConsultar);

		menuItemListar = new JMenuItem("Listar", imgListar);
		menuItemListar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemListar.addActionListener(new ActionListener() {
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

				listarPizza.setVisible(true);

				// Gera a tabela.
				geraTabelaListar(inicializaLista(0));

			}
		});
		menuConsultar.add(menuItemListar);

		JMenu menuAlterar = new JMenu("Alterar");
		menuAlterar.setMnemonic('a');
		barraDeMenu.add(menuAlterar);

		menuItemAlterar = new JMenuItem("Alterar", imgAlterarEditar);
		menuItemAlterar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemAlterar.addActionListener(new ActionListener() {
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

				alterarPizza.setVisible(true);

				// Desativando os campos alterar.
				desativaCamposAlterar();
			}
		});
		menuAlterar.add(menuItemAlterar);

		JMenu menuExcluir = new JMenu("Excluir");
		menuExcluir.setMnemonic('e');
		barraDeMenu.add(menuExcluir);

		JMenuItem menuItemExcluir = new JMenuItem("Excluir", imgDelete);
		menuItemExcluir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				excluirPizza.setVisible(true);
				desativaCamposExcluir();

			}
		});
		menuExcluir.add(menuItemExcluir);

		// Se o tipoFuncionario for C - Colaborador: desabilite o menuExcluir.
		if(tipoFuncionario.getTipoUsuario() == 'C')
			menuExcluir.setEnabled(false);


		setVisible(true);
		setLocationRelativeTo(null);

	}// Fim construtor.

	/* ************************ Métodos para zerar os campos das janelas internas ************************ */
	/** Zera os campos da janela interna consulta  */
	private void zeraCamposConsulta(){

		nomeConsInterTextField.setText("");
		pizzaConsInterTextArea.setText("");

	}

	/** Zera os campos da janela interna cadastrar  */
	private void zeraCamposCadastrar(){

		desCadInterTextField.setText("");
		diaCadastraComboBox.setSelectedIndex(0);
		mesCadastrarComboBox.setSelectedIndex(0);
		anoCadastrarComboBox.setSelectedIndex(0);
		precoCadInterTextField.setText("");
		precoIngredientesCadInterLabel.setText("");

		listaIngredientes = new ArrayList<>();

	}

	/** Desativa os campos da janela interna Alterar na parte de alterar novos campos.
	 * Também zera os campos.*/
	private void desativaCamposAlterar(){

		pesAltInterBotao.setEnabled(true);
		nomePesAltInterLabel.setEnabled(true);
		nomePesAltInterTextField.setEnabled(true);
		tamAtualAltInterComboBox.setEnabled(true);

		//nomePesAltInterTextField.setText("");

		altAltInterBotao.setEnabled(false);
		cancelAltInterBotao.setEnabled(false);

		nomeAltInterLabel.setEnabled(false);
		nomeAltInterTextField.setEnabled(false);
		nomeAltInterTextField.setText("");

		tamAltInterLabel.setEnabled(false);
		tamAltInterTextField.setEnabled(false);
		tamAltInterTextField.setText("");
		tamAltInterComboBox.setEnabled(false);

		dataAltInterLabel.setEnabled(false);

		diaAlterarComboBox.setSelectedIndex(0);
		diaAlterarComboBox.setEnabled(false);

		mesAlterarComboBox.setSelectedIndex(0);
		mesAlterarComboBox.setEnabled(false);

		anoAlterarComboBox.setSelectedIndex(0);
		anoAlterarComboBox.setEnabled(false);

		precoAltInterLabel.setEnabled(false);
		precoAltInterTextField.setEnabled(false);
		precoAltInterTextField.setText("");

		ingredietesAlterInterBotao.setEnabled(false);
		listaIngredientesAlter.clear();
		nomeAlterar = "";
		precoIngredientesAltInterLabel.setText("");
		codigoPizzaAlterar = 0;

	}

	/** Ativa os campos da janela interna Alterar na parte de alterar novos campos, e insere os dados nos campos que deseja alterar
	 * 
	 * @param Cliente objeto do tipo <code>Cliente</code>
	 * */
	private void ativaCamposAlterar(Pizza pizza){

		pesAltInterBotao.setEnabled(false);
		nomePesAltInterLabel.setEnabled(false);
		nomePesAltInterTextField.setEnabled(false);
		tamAtualAltInterComboBox.setEnabled(false);

		altAltInterBotao.setEnabled(true);
		cancelAltInterBotao.setEnabled(true);

		nomeAltInterLabel.setEnabled(true);
		nomeAltInterTextField.setEnabled(true);
		nomeAltInterTextField.setText(EntradaESaida.removerSep(pizza.getDescricao(), "-"));

		tamAltInterLabel.setEnabled(true);
		String tamanhoPizza = pizza.getTamanho() + "";
		tamAltInterTextField.setText(tamanhoPizza);
		tamAltInterComboBox.setEnabled(true);

		// Setando no combo Box tamAltInterComboBox o valor real da pizza.
		if(tamanhoPizza.equalsIgnoreCase("P") == true)
			tamAltInterComboBox.setSelectedIndex(0);

		else if(tamanhoPizza.equalsIgnoreCase("M") == true)
			tamAltInterComboBox.setSelectedIndex(1);

		else if(tamanhoPizza.equalsIgnoreCase("G") == true)
			tamAltInterComboBox.setSelectedIndex(2);

		dataAltInterLabel.setEnabled(true);
		Calendar data = pizza.getData();

		// Insere os valores da data em todos os combo boxes
		diaAlterarComboBox.setSelectedIndex(data.get(Calendar.DAY_OF_MONTH) - 1);
		diaAlterarComboBox.setEnabled(true);

		mesAlterarComboBox.setSelectedIndex(data.get(Calendar.MONTH));
		mesAlterarComboBox.setEnabled(true);

		for(int contador = 0; contador < anoAlterarComboBox.getItemCount(); contador++)
			if(Integer.parseInt(anoAlterarComboBox.getItemAt(contador).toString()) == data.get(Calendar.YEAR))
				anoAlterarComboBox.setSelectedIndex(contador);

		anoAlterarComboBox.setEnabled(true);

		precoAltInterLabel.setEnabled(true);
		precoAltInterTextField.setEnabled(true);
		precoAltInterTextField.setText(pizza.getPreco() + "");

		ingredietesAlterInterBotao.setEnabled(true);
		nomeAlterar = pizza.getDescricao();

		// Ativa a lista de ingredientes da pizza.
		listaIngredientesAlter = controlePizza.listaIngredientesPizza(nomeAlterar);
		precoIngredientesAltInterLabel.setText(calculaPreloIngredientes(listaIngredientesAlter) + "");

		codigoPizzaAlterar = pizza.getCodigo();
	}

	/** Gera uma tabela com todos as pizzas vindo do banco de dados*/
	private void geraTabelaListar(ArrayList<Pizzaria> arrayList){

		// Adiciona o preço das pizzas mais os ingredientes que a compõem.
		if(arrayList != null)
			if(arrayList.get(0) instanceof Pizza){
				
				listaPreco.clear();
				// Soma os valores das pizzas com seus ingredientes
				for(int contador = 0; contador < arrayList.size(); contador++){

					Pizza pizza = ((Pizza) arrayList.get(contador));
					ArrayList<Ingrediente>lista = controlePizza.listaIngredientesPizza(pizza.getDescricao());
					float soma = ((Pizza) arrayList.get(contador)).getPreco();
					
					if(lista != null){
						for(int x = 0; x < lista.size(); x++)
							soma += lista.get(x).getPreco();
						
					}
					
					listaPreco.add(soma);
				}

			}

		if(arrayList == null)
			return;

		else{

			// Gera a tabela com todos os valores do banco de dados.
			String campos[] = {"Código", "Nome", "Tamanho", "Preço"};
			String t[][] = new String[arrayList.size()][10];

			for(int l = 0, x = 0; l < arrayList.size(); l++, x++)
				for(int c = 0; c < 4; c++){

					if(c == 0)
						t[l][c] = ((Pizza) arrayList.get(x)).getCodigo() + "";

					if(c == 1)
						t[l][c] = EntradaESaida.removerSep(((Pizza) arrayList.get(x)).getDescricao(), "-");

					// Colocar ******* na senha.
					if(c == 2)
						t[l][c] = ((Pizza) arrayList.get(x)).getTamanho() + "";

					if(c == 3)
						t[l][c] = listaPreco.get(x) + "";

				}

			tabelaInternalListar = new JTable(t, campos);
			scrollPane.setViewportView(tabelaInternalListar);

		}

	}

	/** Pesquisa se tem alguma String começado com o parâmetro passado. */
	private ArrayList<Pizzaria> consulta(String pesquisa){

		ArrayList<Pizzaria>pizzas = new ArrayList<>();

		for(int contador = 0; contador < listaPizza.size(); contador++)
			if(((Pizza) listaPizza.get(contador)).getDescricao().toLowerCase().startsWith(pesquisa.toLowerCase())
					== true)
				pizzas.add((Pizza) listaPizza.get(contador));

		return pizzas;

	}

	/** Retorna todas as pizzas cadastradas no sistema. */
	private ArrayList<Pizzaria> inicializaLista(int ord){

		// Array com os todos as pizzas vindo do banco de dados.
		listaPizza = controlePizza.listaPizza();

		return listaPizza;

	}


	/* *************************** Cadastra Pizza *************************** */
	/** Cadastra uma pizza no sistema.*/
	private void cadastrarPizza(){

		if(listaIngredientes == null || listaIngredientes.size() == 0){

			EntradaESaida.msgErro("Lista de Ingredientes vazias", "Cadastrar Pizza");
			return;

		}

		String desc = desCadInterTextField.getText().toLowerCase().trim();
		String tam = tamCadInterComboBox.getSelectedItem() + "";

		// Verifica se a entrada da data é válida.
		if(EntradaESaida.DataOk(diaCadastraComboBox.getSelectedItem().toString(), mesCadastrarComboBox.getSelectedItem().toString(),
				anoCadastrarComboBox.getSelectedItem().toString()) == false){

			EntradaESaida.msgErro("Data inválida", "Cadastrar Pizza");

			return;

		}

		String data = EntradaESaida.formataData(diaCadastraComboBox.getSelectedItem().toString(), mesCadastrarComboBox.getSelectedItem().toString(),
				anoCadastrarComboBox.getSelectedItem().toString());

		String preco = precoCadInterTextField.getText();


		if(controlePizza.cadPizza(desc, tam, data, preco) == true){
			geraTabelaListar(inicializaLista(0));
		}

		else
			return;

		// Insere a compozicao, se der errado remove a pizza do sistema.
		Pizza pizza = controlePizza.conPizza(desc.toLowerCase().trim() + "-" + tam.charAt(0));

		if(pizza == null)
			controlePizza.excPizza(desc.toLowerCase().trim() + "-" + tam.charAt(0));

		controlePizza.cadastraPizzaPersonaliza(pizza.getCodigo(), listaIngredientes);

		EntradaESaida.somPergunta();
		int op = EntradaESaida.msgPergunta("Deseja limpar os campos do cadastro?", "Cadastra Pizza");

		if(op == 0)
			zeraCamposCadastrar();

	}// cadastrarPizza()

	/* *************************** Consultar Pizza *************************** */
	/** Consulta uma pizza no sistema.*/
	private void consultarPizza(){

		// Zera o campo de texto.
		pizzaConsInterTextArea.setText("");

		// Pegando o nome do campo text field.
		String tamanho = pesComboBoxConsultaInert.getSelectedItem() + "";
		String nome = nomeConsInterTextField.getText().toLowerCase().trim() + "-" + tamanho.charAt(0);

		Pizza pizza = controlePizza.conPizza(nome);

		if(pizza == null)
			;

		else{

			// Pega a lista de ingredientes da pizza.
			ArrayList<Ingrediente>lista = controlePizza.listaIngredientesPizza(nome);
			String relatorioIngredientes = "";

			if(lista != null && lista.size() > 0)
				for(int contador = 0; contador < lista.size(); contador++)
					relatorioIngredientes += lista.get(contador).toString();

			relatorioIngredientes += "\n";

			// Setando os valores do cliente na area de texto.
			pizzaConsInterTextArea.setText(pizza.toString() + "\n" + relatorioIngredientes);

		}


	}// consultarPizza()

	/* *************************** Alterar Pizza *************************** */
	/** Seta os valores do objeto pizza na janela interna alterar.*/
	private void alterarSetaValores(){

		// Verifica se a pizza é personalizada, caso sim, não faça nada.
		if(nomePesAltInterTextField.getText().equalsIgnoreCase("personalizada") == true)
			return;

		//nomePesAltInterTextField.setEnabled(false);
		//tamAtualAltInterComboBox.setEnabled(false);

		// Pegando o nome do campo text field.
		String tamAtual = tamAtualAltInterComboBox.getSelectedItem() + "";
		String nome = nomePesAltInterTextField.getText().toLowerCase().trim()
				+ "-" + tamAtual.charAt(0);

		Pizza pizza = controlePizza.conPizza(nome);

		if(pizza == null){
			desativaCamposAlterar();
			//nomePesAltInterTextField.setEnabled(true);
			//tamAtualAltInterComboBox.setEnabled(true);
		}
		else{

			// Ativa os campos do alterar
			ativaCamposAlterar(pizza);

		}

	}// alterarSetaValores()

	/** Altera os dados de uma pizza.*/
	private void alteraPizza(){

		if(listaIngredientesAlter == null || listaIngredientesAlter.size() == 0){

			EntradaESaida.msgErro("Lista de ingredientes vazia", "Alterar Pizza");
			return;

		}

		String tamAtual = tamAtualAltInterComboBox.getSelectedItem() + "";
		String desc = nomeAltInterTextField.getText();
		String tam = tamAltInterComboBox.getSelectedItem() + "";

		// Verifica se a entrada da data é válida.
		if(EntradaESaida.DataOk(diaAlterarComboBox.getSelectedItem().toString(), mesAlterarComboBox.getSelectedItem().toString(),
				anoAlterarComboBox.getSelectedItem().toString()) == false){

			EntradaESaida.msgErro("Data inválida", "Alterar Pizza");

			return;

		}

		String data = EntradaESaida.formataData(diaAlterarComboBox.getSelectedItem().toString(), mesAlterarComboBox.getSelectedItem().toString(),
				anoAlterarComboBox.getSelectedItem().toString());

		String preco = precoAltInterTextField.getText();

		Pizza pizza = new Pizza(desc, tam.charAt(0), EntradaESaida.StringToData(data), EntradaESaida.stringToFloat(preco));

		if(controlePizza.altPizza(nomePesAltInterTextField.getText().toLowerCase().trim()
				+ "-" + tamAtual.charAt(0), pizza) == true){

			// Remove as informações antigas de compozição.
			if(controlePizza.removeCompozicao(codigoPizzaAlterar) == false)
				return;

			// Insere as novas informações de compozição da pizza.
			if(controlePizza.cadastraPizzaPersonaliza(codigoPizzaAlterar, listaIngredientesAlter) == false)
				return;

			// Zera todos os campos.
			desativaCamposAlterar();

			// Atualiza a tabela listar.
			geraTabelaListar(inicializaLista(0));

			//nomePesAltInterTextField.setEnabled(true);
			//tamAtualAltInterComboBox.setEnabled(true);

			EntradaESaida.msgInfo("Alteração feita com sucesso", ConstantesPizzas.TITULO_PIZZA_ALTERAR.valor());
			nomePesAltInterTextField.setText("");

		}

		else
			desativaCamposAlterar();

		//nomePesAltInterTextField.setEnabled(true);
		//tamAtualAltInterComboBox.setEnabled(true);

	}// alteraPizza

	/* *************************** Excluir Pizza *************************** */
	/*private void excluirPizza(){

		// Desativa a área de pesquisa.
		desPesExcluirInterTextField.setEnabled(false);

		if(controlePizza.excPizza(desPesExcluirInterTextField.getText()) == true)
			desativaCamposExcluir();

		else
			ativaCamposExcluir();


	}// excluirPizza()*/

	/** Ativa os campos de dados da janela interna excluir. */
	private void ativaCamposExcluir(){

		// Desativa a área de texto e os botões.
		desPesExcluirInterTextField.setEnabled(false);
		tamAtualExcluirIntercomboBox.setEnabled(false);
		desPesExcluirInterLabel.setEnabled(false);
		pesExcluirInterBotao.setEnabled(false);


		desPesExcluirInterTextArea.setText("");
		desPesExcluirInterTextArea.setEnabled(true);

		excluirExcluirInterBotao.setEnabled(true);
		cancelarExcluirInterBotao.setEnabled(true);

	}// ativaCamposExcluir()

	/** Desativa os campos de dados da janela interna excluir. */
	private void desativaCamposExcluir(){

		desPesExcluirInterTextField.setEnabled(true);
		tamAtualExcluirIntercomboBox.setEnabled(true);
		desPesExcluirInterLabel.setEnabled(true);
		pesExcluirInterBotao.setEnabled(true);

		// Ativa a área de pesquisa e reseta.
		//desPesExcluirInterTextField.setText("");
		desPesExcluirInterTextField.setEnabled(true);

		// Desativa a área de texto e os botões.
		desPesExcluirInterTextArea.setText("");
		desPesExcluirInterTextArea.setEnabled(false);

		excluirExcluirInterBotao.setEnabled(false);
		cancelarExcluirInterBotao.setEnabled(false);

	}// desativaCamposExcluir()

	/* ****************************** Excluir pizzas *****************************/
	/** Pesquisa e seta os valores do objeto pizza obtidos na consulta. */
	private void excluirSetaValores(){

		// Verifica se a pizza é personalizada, caso sim, não faça nada.
		if(desPesExcluirInterTextField.getText().equalsIgnoreCase("personalizada") == true)
			return;

		// Desativa a área de texto.
		desPesExcluirInterTextField.setEnabled(false);

		// Concatena o nome da pizza com o tamanho, pois no banco de dados a descrição é nome mais tamanho.
		String tamanhoPizza = tamAtualExcluirIntercomboBox.getSelectedItem() + "";
		Pizza pizza = controlePizza.conPizza(desPesExcluirInterTextField.getText().toLowerCase().trim()
				+ "-" + tamanhoPizza.charAt(0));

		if(pizza == null){

			// Se a pizza não for achada, desativa os campos.
			desativaCamposExcluir();
			return;
		}

		ativaCamposExcluir();

		// Seta os valores antigos.
		desPesExcluirInterTextArea.setText(pizza.toString());

	}// excluirSetaValores()


	/** Exclui o pizza setado na área de texto.*/
	private void excluiPizza(){

		// Verifica se o campo de pesquisa se está vazio ou é pizza personalizada, caso sim, não faça nada.
		if(desPesExcluirInterTextField.getText().equalsIgnoreCase("") == true)
			return;

		// Pega o cod da pizza.
		String tamanhoPizza = tamAtualExcluirIntercomboBox.getSelectedItem() + "";
		String descricao = desPesExcluirInterTextField.getText().toLowerCase().trim()
				+ "-" + tamanhoPizza.charAt(0);
		if(controlePizza.excPizza(descricao) == true){

			// Ativa o campo de pesquisa e limpa a área de texto.
			desPesExcluirInterTextField.setText("");
			desPesExcluirInterTextField.setEnabled(true);
			desPesExcluirInterTextArea.setText("");

			// Atualiza a tabela.
			// Atualiza a tabela listar.
			geraTabelaListar(inicializaLista(0));
		}

		// Ativa o campo de pesquisa e limpa a área de texto.
		//desPesExcluirInterTextField.setText("");
		desPesExcluirInterTextField.setEnabled(true);
		desPesExcluirInterTextArea.setText("");
		desativaCamposExcluir();

	}// excluiPizza()

	/** Adiciona ingredientes a pizza. */
	private static void adicionaIngredientes(){

		// Verifica se não cadastrou algum ingredientes antes.
		new ComprarIngredientesGui(listaIngredientes);

		if(listaIngredientes == null || listaIngredientes.size() == 0){
			precoIngredientesCadInterLabel.setText("0");
			return;
		}

		precoIngredientesCadInterLabel.setText(calculaPreloIngredientes(listaIngredientes) + "");

	}// adicionaIngredientes()

	/** Pega a lista de ingredientes relacionada a pizza e da opção de alterar os mesmo.*/
	private static boolean alteraListaIngredientes(String descricao){

		//if(listaIngredientesAlter.size() < 1)
		listaIngredientesAlter = controlePizza.listaIngredientesPizza(descricao);

		//if(listaIngredientesAlter.size() == 0)
		//return false;

		if(listaIngredientesAlter == null)
			listaIngredientesAlter = new ArrayList<>();

		new ComprarIngredientesGui(listaIngredientesAlter);

		if(listaIngredientesAlter == null || listaIngredientesAlter.size() == 0){
			precoIngredientesAltInterLabel.setText("0");
			return false;
		}

		precoIngredientesAltInterLabel.setText(calculaPreloIngredientes(listaIngredientesAlter) + "");
		return true;

	}// alteraListaIngredientes.

	/** Calcula o preço da lista de ingredientes.
	 * 
	 *  @param listaIngrediente que terá seu preço calculado.
	 *  @return preco total da lista de ingredientes. 0 caso essa lsita esteja vazia.
	 *  */
	private static float calculaPreloIngredientes(ArrayList<Ingrediente>lista){

		if(lista == null || lista.size() == 0)
			return 0;

		float preco = 0;

		for(int contador = 0; contador < lista.size(); contador++)
			preco += lista.get(contador).getPreco();

		return preco;

	}
}// Fim da class PizzaGui
