package tsi.too.pv.gui;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import tsi.too.pv.controle.ControleDePedido;
import tsi.too.pv.controle.ControleIngrediente;
import tsi.too.pv.controle.ControlePizza;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Acompanhamento;
import tsi.too.pv.tipos.Cliente;
import tsi.too.pv.tipos.CompozicaoPizza;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pedido;
import tsi.too.pv.tipos.Pizza;
import tsi.too.pv.tipos.PizzaPedida;

@SuppressWarnings("serial")
public class ComprarGui extends JFrame {

	private JPanel contentPane;
	private static JButton acompanhamentoBotao;
	private static JPanel pizzaPanel;
	private static JPanel acompanhamentoPanel;
	private static JPanel pagamentoPanel;
	private static JCheckBox cartaoCheck;
	private static JCheckBox dinheiroCheck;
	private final static ButtonGroup formaPagamentoBotaoGrupo = new ButtonGroup();
	private static JPanel relatorioPanel;
	private static JScrollPane scrollPane;
	private static JTextArea relatorioTextArea;

	private static Cliente cliente;

	// Meio campo com o banco de dados.
	private static ControlePizza controlePizza;
	private static ControleDePedido controlePedido;
	// A forma de pagamento.
	private static String formaDePagamento = "Dinheiro";

	// Preço total do pedido.
	private static float precoFinal = 0;

	// Preço da pizza.
	private static float precoPizza = 0;

	// Preço dosingredientes ingredientes.
	private static float precoIngredientes = 0;

	// Preço do acompanhamento.
	private static float precoAcompanhamento = 0;

	// Relatório da pizza escolhida.
	private static String relatorioPizza = "";

	// Relatório dos ingredientes escolhido
	private static String relatorioIngredientes = "";

	// Relatório do acompanhamento escolhido.
	private static String relatorioAcompanhamento = "";

	// Relátorio com as informações da compra do usuário.
	private static String relatorioCompra = "";

	// Lista com as pizzas do sistema.
	private static ArrayList<String>listaPizzas;

	// Lista com os acompanhamentos.
	private static ArrayList<Acompanhamento>listaAcompanhamentos;

	// Lista pizzas pedidas
	private static ArrayList<PizzaPedida>listaPizzasPedidas;

	private final ButtonGroup bordaBotaoGrupo = new ButtonGroup();
	@SuppressWarnings("rawtypes")
	private static JComboBox pizzaComboBox;
	private static JCheckBox catupiryCheck;
	private static JCheckBox cheddarChech;
	private static JCheckBox semBordaCheck;
	private static JButton pizzaAdicionarBotao;
	private static JButton cancelarBotao;
	private static JButton fecharPedidoBotao;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menuOpcoes;
	private JMenuItem menuItemSair;

	// Define o nome personalizado das pizzas escolhidas.
	private static String nomePersonalizado = "";
	private static String nomePizzaParaPersonaliza = "";

	private static ArrayList<String>listaNomesPizzasPersonalizadosExclusao;
	private JPanel painelBorda;
	private static JRadioButton pequenaRadioButton;
	private static JRadioButton mediaRadioButton;
	private static JRadioButton grandeRadioButton;
	private final ButtonGroup tamanhoPizzaBotaoGrupo = new ButtonGroup();

	/**
	 * Cria uma janela na qual o funcionário gerencie o módulo de comprar.
	 * 
	 * @param funcionario que vai gerencia a compra. Apenas colaborador e gerente tem acesso. 
	 */
	@SuppressWarnings("rawtypes")
	public ComprarGui(Funcionario tipoFuncionario, Cliente cl) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ComprarGui.class.getResource("/img/pizzaria.png")));

		ImageIcon imgCancelar = new ImageIcon(this.getClass().getResource("/img/cancelar.png"));
		ImageIcon imgSucesso = new ImageIcon(this.getClass().getResource("/img/sucesso.png"));
		ImageIcon imgSair = new ImageIcon(this.getClass().getResource("/img/sair.png"));
		ImageIcon imgAdd = new ImageIcon(this.getClass().getResource("/img/more.png"));


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

		cliente = cl;
		controlePizza = new ControlePizza();
		new ControleIngrediente();
		controlePedido = new ControleDePedido();

		//cliente = new Cliente();
		new Pizza();

		listaNomesPizzasPersonalizadosExclusao = new ArrayList<>();

		listaPizzasPedidas = new ArrayList<PizzaPedida>();

		new ArrayList<CompozicaoPizza>();

		listaAcompanhamentos = new ArrayList<>();

		listaPizzas = new ArrayList<>();

		setBackground(SystemColor.controlText);
		setTitle("Comprar Pizza");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 432);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(0, 30, 328, 317);
		contentPane.add(panel);
		panel.setLayout(null);

		pizzaPanel = new JPanel();
		pizzaPanel.setBounds(6, 6, 322, 86);
		panel.add(pizzaPanel);
		pizzaPanel.setBackground(SystemColor.controlHighlight);
		pizzaPanel.setBorder(new TitledBorder(null, "Pizza", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		pizzaComboBox = new JComboBox();
		pizzaComboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				
				// Trata o evento do botão direito do mouse.
				verificaIngredientesPizza(me);
				
			}
		});
		pizzaComboBox.setBounds(20, 18, 184, 26);
		pizzaPanel.setLayout(null);
		pizzaPanel.add(pizzaComboBox);

		pizzaAdicionarBotao = new JButton("Adicionar", imgAdd);
		pizzaAdicionarBotao.setToolTipText("Adiciona uma pizza");
		pizzaAdicionarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Verifica se é algum tipo de pizza personalizada.
				String pizza = pizzaComboBox.getSelectedItem() + "";

				if(pizza.startsWith("personalizada") == true){

					if(criaNovaPizza(pizza) == true)
						ativaPaineis();
					
					else
						limpaCampos();

				}

				else if(pizza.equalsIgnoreCase("null") == false){
					setPizzaRelatorio();
					ativaPaineis();
				}
				// Ativa paineis.

			}
		});
		pizzaAdicionarBotao.setBounds(206, 17, 100, 28);
		pizzaPanel.add(pizzaAdicionarBotao);

		pequenaRadioButton = new JRadioButton("Pequena");
		pequenaRadioButton.setSelected(true);
		pequenaRadioButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {

				// Adiciona apenas as pizzas pequenas.
				listaPizzas.clear();
				listaPizzas.add("Escolha...");
				listaPizzas = controlePizza.listaDescricaoPizza('P');
				DefaultComboBoxModel modelo = new DefaultComboBoxModel<>(listaPizzas.toArray());
				pizzaComboBox.setModel(modelo);

			}
		});
		tamanhoPizzaBotaoGrupo.add(pequenaRadioButton);
		pequenaRadioButton.setBounds(30, 56, 85, 18);
		pizzaPanel.add(pequenaRadioButton);

		mediaRadioButton = new JRadioButton("M\u00E9dia");
		mediaRadioButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				listaPizzas.clear();
				listaPizzas.add("Escolha...");
				// Adiciona apenas as pizzas médias.
				listaPizzas = controlePizza.listaDescricaoPizza('M');
				DefaultComboBoxModel modelo = new DefaultComboBoxModel<>(listaPizzas.toArray());
				pizzaComboBox.setModel(modelo);

			}
		});
		tamanhoPizzaBotaoGrupo.add(mediaRadioButton);
		mediaRadioButton.setBounds(116, 56, 68, 18);
		pizzaPanel.add(mediaRadioButton);

		grandeRadioButton = new JRadioButton("Grande");
		grandeRadioButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				listaPizzas.clear();
				listaPizzas.add("Escolha...");
				// Adiciona apenas as pizzas grandes.
				listaPizzas = controlePizza.listaDescricaoPizza('G');
				DefaultComboBoxModel modelo = new DefaultComboBoxModel<>(listaPizzas.toArray());
				pizzaComboBox.setModel(modelo);

			}
		});
		tamanhoPizzaBotaoGrupo.add(grandeRadioButton);
		grandeRadioButton.setBounds(192, 56, 73, 18);
		pizzaPanel.add(grandeRadioButton);

		acompanhamentoPanel = new JPanel();
		acompanhamentoPanel.setBounds(6, 163, 316, 71);
		panel.add(acompanhamentoPanel);
		acompanhamentoPanel.setBackground(SystemColor.controlHighlight);
		acompanhamentoPanel.setBorder(new TitledBorder(null, "Acompanhamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		acompanhamentoBotao = new JButton("Adicionar", imgAdd);
		acompanhamentoBotao.setBounds(106, 24, 110, 28);
		acompanhamentoBotao.setToolTipText("Adiciona acompanhamentos");
		acompanhamentoBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				relatorioAcompanhamento = "";
				geraListaAcompanhamentos();

			}
		});
		acompanhamentoPanel.setLayout(null);
		acompanhamentoPanel.add(acompanhamentoBotao);

		pagamentoPanel = new JPanel();
		pagamentoPanel.setBounds(6, 246, 316, 65);
		panel.add(pagamentoPanel);
		pagamentoPanel.setBackground(SystemColor.controlHighlight);
		pagamentoPanel.setBorder(new TitledBorder(null, "Forma de Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		cartaoCheck = new JCheckBox("Cart\u00E3o");
		cartaoCheck.setSelected(true);
		cartaoCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				formaDePagamento = cartaoCheck.getText();
				geraRelatorio("", "", "");

			}
		});
		formaPagamentoBotaoGrupo.add(cartaoCheck);
		cartaoCheck.setBorder(new TitledBorder(null, "Forma de Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		dinheiroCheck = new JCheckBox("Dinheiro");
		dinheiroCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				formaDePagamento = dinheiroCheck.getText();
				geraRelatorio("", "", "");

			}
		});
		formaPagamentoBotaoGrupo.add(dinheiroCheck);
		dinheiroCheck.setBorder(new TitledBorder(null, "Forma de Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_pagamentoPanel = new GroupLayout(pagamentoPanel);
		gl_pagamentoPanel.setHorizontalGroup(
				gl_pagamentoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pagamentoPanel.createSequentialGroup()
						.addGap(47)
						.addComponent(cartaoCheck, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(dinheiroCheck, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(41, Short.MAX_VALUE))
				);
		gl_pagamentoPanel.setVerticalGroup(
				gl_pagamentoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pagamentoPanel.createSequentialGroup()
						.addGroup(gl_pagamentoPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cartaoCheck, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(dinheiroCheck, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		pagamentoPanel.setLayout(gl_pagamentoPanel);

		painelBorda = new JPanel();
		painelBorda.setBackground(SystemColor.controlHighlight);
		painelBorda.setBounds(6, 93, 316, 58);
		panel.add(painelBorda);
		painelBorda.setBorder(new TitledBorder(null, "Borda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelBorda.setLayout(null);

		catupiryCheck = new JCheckBox("Catupiry");
		catupiryCheck.setBounds(52, 22, 73, 18);
		painelBorda.add(catupiryCheck);

		bordaBotaoGrupo.add(catupiryCheck);

		cheddarChech = new JCheckBox("Cheddar");
		cheddarChech.setBounds(124, 22, 73, 18);
		painelBorda.add(cheddarChech);
		bordaBotaoGrupo.add(cheddarChech);

		semBordaCheck = new JCheckBox("Sem Borda");
		semBordaCheck.setBounds(198, 22, 84, 18);
		painelBorda.add(semBordaCheck);

		semBordaCheck.setSelected(true);
		bordaBotaoGrupo.add(semBordaCheck);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 793, 23);
		contentPane.add(menuBar);

		menuOpcoes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(menuOpcoes);

		menuItemSair = new JMenuItem("Sair", imgSair);
		menuItemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpaCampos();
				dispose();
				new JanelaPrincipalGui(tipoFuncionario);

			}
		});
		menuOpcoes.add(menuItemSair);

		relatorioPanel = new JPanel();
		relatorioPanel.setBounds(329, 30, 441, 363);
		contentPane.add(relatorioPanel);
		relatorioPanel.setBorder(new TitledBorder(null, "Relat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		relatorioPanel.setBackground(SystemColor.controlHighlight);

		scrollPane = new JScrollPane();
		GroupLayout gl_relatorioPanel = new GroupLayout(relatorioPanel);
		gl_relatorioPanel.setHorizontalGroup(
				gl_relatorioPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
				);
		gl_relatorioPanel.setVerticalGroup(
				gl_relatorioPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
				);

		relatorioTextArea = new JTextArea();
		relatorioTextArea.setEditable(false);
		relatorioTextArea.setLineWrap(true);
		relatorioTextArea.setWrapStyleWord(true);
		scrollPane.setViewportView(relatorioTextArea);
		relatorioPanel.setLayout(gl_relatorioPanel);

		fecharPedidoBotao = new JButton("Fechar", imgSucesso);
		fecharPedidoBotao.setBounds(49, 351, 100, 28);
		contentPane.add(fecharPedidoBotao);
		fecharPedidoBotao.setToolTipText("Fechar pedido");

		cancelarBotao = new JButton("Cancelar", imgCancelar);
		cancelarBotao.setBounds(165, 351, 108, 28);
		contentPane.add(cancelarBotao);
		cancelarBotao.setToolTipText("Cancelar Pedido");
		cancelarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Deleta as pizzas personalizadas.
				for(int contador = 0; contador < listaNomesPizzasPersonalizadosExclusao.size(); contador++)
					if(controlePizza.excPizzasPersonalizadas(listaNomesPizzasPersonalizadosExclusao.get(contador)) == false)
						return;


				// Reinicia o comboBox
				inicializaComboBoxPizzas();

				// Limpa todos os campos.
				limpaCampos();
				
				// Seta lista de pizzas pequenas como padrão.
				pequenaRadioButton.setSelected(true);

				desativaPaineis();

			}
		});
		fecharPedidoBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Fecha o pedido do usuário.
				controlePedido = new ControleDePedido();
				controlePedido.fechaPedido(listaAcompanhamentos, listaPizzasPedidas, 
						getPedido());



				// Reinicia o comboBox
				inicializaComboBoxPizzas();
				desativaPaineis();
				limpaCampos();
			}
		});
		// inicializa combo box pizzas.
		inicializaComboBoxPizzas();

		setVisible(true);
		setLocationRelativeTo(null);
		
		desativaPaineis();

	}// Fim do construtor

	/** Adiciona todos os ingrdientes em um novo JFrame para o usuário escolher.
	 * 
	 * @return lista com os ingredientes escolhidos pelo usuário, null caso contrário.
	 * */
	private static ArrayList<Ingrediente> geraListaIngredientes(){

		// Zera a lista de ingredientes.
		relatorioIngredientes = "";
		ArrayList<Ingrediente> listaIngredientes = new ArrayList<>();

		new ComprarIngredientesGui(listaIngredientes);

		if(listaIngredientes == null || listaIngredientes.size() == 0)
			return null;

		for(int contador = 0; contador < listaIngredientes.size(); contador++){

			Ingrediente ingrediente = listaIngredientes.get(contador);
			relatorioIngredientes += "\n" + ingrediente.toString();
			precoIngredientes += ingrediente.getPreco();

		}

		/* Insere valores no ArrayList de compozicaoPizza.
		CompozicaoPizza composicao = new CompozicaoPizza();
		for(int contador = 0; contador < listaIngredientes.size(); contador++){

			composicao.setCodigoPizza(pizzaPersonalizada.getCodigo());
			composicao.setCodigoIngrediente(listaIngredientes.get(contador).getCodigo());
			listaComposicaoPizzas.add(composicao);
			composicao = new CompozicaoPizza();

		}*/


		return listaIngredientes;

	}// geraListaIngredientes()

	/** Gera uma lista com os acompanhamentos escolhidos pelo usuário.*/
	private static void geraListaAcompanhamentos(){

		listaAcompanhamentos = new ArrayList<>();

		new ComprarAcompanhamentoGui(listaAcompanhamentos);

		for(int contador = 0; contador < listaAcompanhamentos.size(); contador++){

			Acompanhamento acompanhamento = listaAcompanhamentos.get(contador);
			relatorioAcompanhamento += acompanhamento.toString();
			precoAcompanhamento += acompanhamento.getPreco();

		}

		geraRelatorio("", "", relatorioAcompanhamento);
		relatorioAcompanhamento = "";

	}// geraListaAcompanhamentos() 

	/** Inicializa o combo box referentes as pizzas cadastradas no sistema. */
	@SuppressWarnings("unchecked")
	private static void inicializaComboBoxPizzas(){

		// Array com os nomes das pizzas.
		listaPizzas = controlePizza.listaDescricaoPizza('P');

		@SuppressWarnings("rawtypes")
		DefaultComboBoxModel modelo = new DefaultComboBoxModel<>(listaPizzas.toArray());
		pizzaComboBox.setModel(modelo);

	}// inicializaComboBoxPizzas()

	/** Seta as informações da escolha da pizza no relatório. */
	private static void setPizzaRelatorio(){

		String escolha = pizzaComboBox.getSelectedItem() + "";

		// Pega os ingredientes da pizza se ela tiver.
		String relatorioIngrediente = "";

		char tamanho = 'P';
		// Verifica o tamanho da pizza.
		if(pequenaRadioButton.isSelected())
			tamanho = 'P';
		else if(mediaRadioButton.isSelected())
			tamanho = 'M';
		else if(grandeRadioButton.isSelected())
			tamanho = 'G';

		// Verifica a escolha da pizza do usuário. Note que a opção "escolha" não irá entrar no relatório.
		if(escolha.equalsIgnoreCase("Escolha...") == true || 
				escolha.equalsIgnoreCase(nomePizzaParaPersonaliza) == true){

			relatorioPizza = "";
			return;
		}


		else{

			// Pega todas as informações da pizza escolhida.
			Pizza pizza = new Pizza();

			for(int contador = 0; contador < listaPizzas.size(); contador++)
				if(listaPizzas.get(contador).equalsIgnoreCase(escolha) == true){

					pizza = controlePizza.conPizza(escolha + "-" + tamanho);
					break;

				}

			if(pizza == null)
				return;

			ArrayList<Ingrediente>listaIngrediente = new ArrayList<>();
			listaIngrediente = controlePizza.listaIngredientesPizza(escolha + "-" + tamanho);

			// Gera o relatório dos ingredientes.
			if(listaIngrediente != null){

				for(int contador = 0; contador < listaIngrediente.size(); contador++){
					relatorioIngrediente += listaIngrediente.get(contador).toString();
					precoIngredientes += listaIngrediente.get(contador).getPreco();
				}

			}

			// Formata o relatório da pizza.
			relatorioPizza = pizza.toString();

			precoPizza += pizza.getPreco();

			// Adiciona o tipo de borda.
			relatorioPizza += "\nBorda: ";

			String borda = bordaEscolhida();

			// Insere informações na PizzaPedido.
			PizzaPedida pizzaPedida = new PizzaPedida(0, pizza.getCodigo(), 1, borda);
			listaPizzasPedidas.add(pizzaPedida);

		}

		geraRelatorio(relatorioPizza, relatorioIngrediente, "");

	}// setPizzaRelatorio()

	/** Gera o relatório */
	private static void geraRelatorio(String relatorioPizza, String relatorioIngrediente, 
			String relatorioAcomp){

		// Recebe a forma de pagamento.
		if(dinheiroCheck.isSelected() == true)
			formaDePagamento = dinheiroCheck.getText();

		else
			formaDePagamento = cartaoCheck.getText();

		if(relatorioPizza.length() != 0)
			relatorioCompra += relatorioPizza + "\n";

		// Verifica se tem algo, se tiver da quebra de linha
		if(relatorioIngrediente.length() != 0)
			relatorioCompra += relatorioIngrediente + "\n";

		if(relatorioAcomp.length() != 0)
			relatorioCompra += relatorioAcomp + "\n";

		precoFinal = precoPizza + precoIngredientes + precoAcompanhamento;

		String relFinal = "";
		// Adicionas algumas informações.
		relFinal = "\n\n ********** Informações **********" + "\n Cliente: " + cliente.getNome() +
				"\n Preço total: R$ " + precoFinal + "\n Forma de Pagamento: " + formaDePagamento;

		relatorioTextArea.setText(relatorioCompra + relFinal);

	}// geraRelatorio()

	/** Limpa todos os campos. */
	private static void limpaCampos(){

		desativaPaineis();
		
		pizzaComboBox.setSelectedIndex(0);
		semBordaCheck.setSelected(true);
		relatorioPizza = "";
		precoPizza = 0;
		precoFinal = 0;

		relatorioIngredientes = "";
		precoIngredientes = 0;

		listaAcompanhamentos = new ArrayList<>();
		relatorioAcompanhamento = "";
		precoAcompanhamento = 0;

		dinheiroCheck.setSelected(true);
		formaDePagamento = "Dinheiro";

		nomePersonalizado = "";
		nomePizzaParaPersonaliza = "";
		listaNomesPizzasPersonalizadosExclusao.clear();

		listaPizzasPedidas.clear();

		relatorioTextArea.setText("");
		relatorioCompra = "";

	}// limpaCampos()

	/** Preenche as informações relacionadas ao pedido e o retorna*/
	private static Pedido getPedido(){

		Pedido pedido = new Pedido();

		pedido.setCpf(cliente.getCpf());
		pedido.setDataHora(Calendar.getInstance());
		pedido.setFormaDePagamento(formaDePagamento);
		pedido.setPreco(precoFinal);
		pedido.setSituacao("Aberto");

		return pedido;


	}// formaPedido()


	/** Ativa os painéis depois que o botão de adicionar pizza é acionado. */
	private static void ativaPaineis(){

		// Verifica se a pizza selecionada é personalizada, se for ativa o campo ingredientes.
		//ativaCampoIngredientes();

		acompanhamentoBotao.setEnabled(true);
		dinheiroCheck.setEnabled(true);
		cartaoCheck.setEnabled(true);
		scrollPane.setEnabled(true);
		fecharPedidoBotao.setEnabled(true);
		cancelarBotao.setEnabled(true);

		acompanhamentoPanel.setEnabled(true);
		pagamentoPanel.setEnabled(true);



	}// ativaPaineis()

	/** Desativa os painéis.. */
	private static void desativaPaineis(){

		acompanhamentoBotao.setEnabled(false);
		dinheiroCheck.setEnabled(false);
		cartaoCheck.setEnabled(false);
		scrollPane.setEnabled(false);
		fecharPedidoBotao.setEnabled(false);
		cancelarBotao.setEnabled(false);
		acompanhamentoPanel.setEnabled(false);
		pagamentoPanel.setEnabled(false);

	}// desativaPaineis()


	/** Cria uma nova pizza de acordo com os ingredientes escolhidos pelo usuário. */
	@SuppressWarnings("unchecked")
	private static boolean criaNovaPizza(String pizza){


		// Gera os ingredientes da pizza formada.
		ArrayList<Ingrediente>listaIngredientes = geraListaIngredientes();

		if(listaIngredientes == null || listaIngredientes.size() == 0)
			return false;

		// Define o nome para a pizza.
		nomePersonalizado = EntradaESaida.leString("Nome para a pizza?", "Adicionar Pizza");
		if(nomePersonalizado.equalsIgnoreCase(""))
			return false;

		// Pega o nome "personalizada" alguma coisa. Serve para recuperar as informações sobre a pizza.
		nomePizzaParaPersonaliza = pizza;

		// Recupera as informações sobre a pizza personalizada.
		String tamanho = "";
		if(pequenaRadioButton.isSelected())
			tamanho = "-p";
		else if(mediaRadioButton.isSelected())
			tamanho = "-m";
		else if(grandeRadioButton.isSelected())
			tamanho = "-g";

		// Recupera as informações da pizza personalizada, tal como preço.
		Pizza conPizza = controlePizza.conPizza(nomePizzaParaPersonaliza + tamanho);

		if(conPizza == null)
			return false;

		// Seta na descrição que está como "personalizada" o novo nome da pizza.
		conPizza.setDescricao(nomePersonalizado);

		// Cadastra a pizza no sistema.
		if(controlePizza.cadPizza(conPizza.getDescricao(), conPizza.getTamanho() + "", 
				EntradaESaida.dataToString(conPizza.getData()), conPizza.getPreco() + "") == false)
			return false;

		// adiciona a pizza no comboBox e o seleciona.
		//String n = nomePersonalizado + pizza.substring(13, pizza.length());

		// adiciona esse nome personalizado em array de exclusão.
		listaNomesPizzasPersonalizadosExclusao.add(nomePersonalizado + tamanho);

		// Adiciona a nova pizza no comboBox de pizzas
		pizzaComboBox.addItem(nomePersonalizado);
		int indice = pizzaComboBox.getItemCount();
		indice--;
		pizzaComboBox.setSelectedIndex(indice);

		// Cadastra os ingredientes da pizza personalazada no sistema.
		// Pega essa pizza e adiciona listaPizzasPedidas.
		String pesquisa = nomePersonalizado + "-" + conPizza.getTamanho();
		conPizza = controlePizza.conPizza(pesquisa.toLowerCase());

		controlePedido = new ControleDePedido();
		if(controlePizza.cadastraPizzaPersonaliza(conPizza.getCodigo(), listaIngredientes) == 
				false)
			return false;


		// Adiciona no array de lista pedidas.
		listaPizzas.add(pesquisa);

		// Pega a borda.
		String borda = bordaEscolhida();

		// Insere informações na PizzaPedido.
		PizzaPedida pizzaPedida = new PizzaPedida(0, conPizza.getCodigo(), 1, borda);
		listaPizzasPedidas.add(pizzaPedida);

		nomePizzaParaPersonaliza = "";

		precoPizza += conPizza.getPreco();
		String relatorio = conPizza.toString() + "\n" + borda;

		geraRelatorio(relatorio, relatorioIngredientes, "");

		relatorioIngredientes = "";

		return true;

	}// criaNovaPizza()

	/** Seleciona a borda escolhida pelo usuário.
	 * 
	 *  @return nome da borda escolhida
	 *  */
	private static String bordaEscolhida(){

		String borda = "";

		if(cheddarChech.isSelected() == true){
			relatorioPizza += cheddarChech.getText();
			borda = cheddarChech.getText();
		}
		else if(catupiryCheck.isSelected() == true){
			relatorioPizza += catupiryCheck.getText();
			borda = catupiryCheck.getText();
		}
		else if(semBordaCheck.isSelected() == true){
			relatorioPizza += semBordaCheck.getText();
			borda = semBordaCheck.getText();
		}

		return borda;

	}// bordaEscolhida()
	
	
	/** Mostra uma janela com os ingredientes que compõem a pizza. O evento é disparado 
	 * ao clicar com o botão direito do mouse sobre o combo box.
	 * 
	 * @param me um <b>MouseEvent</b> que possui o evento do mouse.
	 * */
	private static void verificaIngredientesPizza(MouseEvent me){
		
		if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0){
			
			char tamanho = 'P';
			// Verifica o tamanho da pizza.
			if(pequenaRadioButton.isSelected())
				tamanho = 'P';
			else if(mediaRadioButton.isSelected())
				tamanho = 'M';
			else if(grandeRadioButton.isSelected())
				tamanho = 'G';
			
			// Pega a descrição da pizza.
			String descricaoPizza = pizzaComboBox.getSelectedItem() + "-" + tamanho;
			
			if(descricaoPizza.equalsIgnoreCase("") == false && descricaoPizza.startsWith("personalizada") == false)
				new ComprarDetalhesPizzaGui(descricaoPizza);
			
		}
		
		
	}// verificaIngredientesPizza()
	
}// Fim da class ComprarGui.