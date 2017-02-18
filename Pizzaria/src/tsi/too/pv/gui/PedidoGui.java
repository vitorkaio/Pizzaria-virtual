package tsi.too.pv.gui;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import tsi.too.pv.controle.ControleDePedido;
import tsi.too.pv.controle.ControlePizza;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Acompanhamento;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pedido;
import tsi.too.pv.tipos.Pizza;
import tsi.too.pv.tipos.PizzaPedida;
import tsi.too.pv.tipos.Pizzaria;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

@SuppressWarnings("serial")
public class PedidoGui extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem sairMenuItem;
	private JMenuItem consultarMenuItem;
	private JMenuItem relatorioMenuItem;
	private JDesktopPane desktopPane;
	private JInternalFrame consultaInternalFrame;
	private static JTextField consultaInterTextField;
	private static JTextArea consultaInterTextArea;
	private static JButton pesquisarConsultaInterBotao;
	private static JButton cancelarConsultaInterBotao;

	private static ControleDePedido controlePedido;
	private static JButton encerrarPedidoBotao;
	private static JButton excluirPedidoBotao;

	private static Pedido pedido;
	private static ArrayList<PizzaPedida>listaPizzasPedidas;
	private static ArrayList<Pizza>listaPizzas;
	private static ControlePizza controlePizza;
	private static ArrayList<Acompanhamento>listaAcompanhamentos;
	private JInternalFrame listaInternalFrame;
	private static JScrollPane scrollPaneListaPedidos;
	private static JTable tabelaInternalListar;
	private JMenuItem listarMenuItem;
	private JInternalFrame relatorioInterFrame;
	private JLabel dataRelatorioLabel;
	private JScrollPane scrollPaneRelatorio;
	private static JTextArea relatorioTextArea;
	private JButton pesquisarRelatorioBotao;
	private JButton cancelarRelatorioBotao;
	@SuppressWarnings("rawtypes")
	private static JComboBox diaRelatoriocomboBox;
	@SuppressWarnings("rawtypes")
	private static JComboBox mesRelatorioComboBox;
	@SuppressWarnings("rawtypes")
	private static JComboBox anoRelatorioComboBox;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PedidoGui(Funcionario tipoFuncionario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PedidoGui.class.getResource("/img/pizzaria.png")));

		ImageIcon imgPesquisar = new ImageIcon(this.getClass().getResource("/img/pesquisa.png"));
		ImageIcon imgCancelar = new ImageIcon(this.getClass().getResource("/img/cancelar.png"));
		ImageIcon imgDelete = new ImageIcon(this.getClass().getResource("/img/delete.png"));
		ImageIcon imgListar = new ImageIcon(this.getClass().getResource("/img/lista.png"));
		ImageIcon imgSair = new ImageIcon(this.getClass().getResource("/img/sair.png"));
		ImageIcon imgRelatorio = new ImageIcon(this.getClass().getResource("/img/report.png"));
		ImageIcon imgEncerrado = new ImageIcon(this.getClass().getResource("/img/terminado.png"));

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

		controlePedido = new ControleDePedido();
		listaPizzas = new ArrayList<>();
		controlePizza = new ControlePizza();
		listaAcompanhamentos = new ArrayList<>();
		listaPizzasPedidas = new ArrayList<>();
		
		setTitle("Gerenciamento de Pedido");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		menuBar = new JMenuBar();
		menuBar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		contentPane.add(menuBar, BorderLayout.NORTH);

		JMenu menuOpcoes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(menuOpcoes);

		sairMenuItem = new JMenuItem("Sair", imgSair);
		sairMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		sairMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				// Dispose nas janelas internas.
				consultaInternalFrame.dispose();
				listaInternalFrame.dispose();
				relatorioInterFrame.dispose();

				dispose();
				new JanelaPrincipalGui(tipoFuncionario);
			}
		});
		menuOpcoes.add(sairMenuItem);

		JMenu menuConsultar = new JMenu("Consultar");
		menuConsultar.setMnemonic('c');
		menuBar.add(menuConsultar);

		consultarMenuItem = new JMenuItem("Consultar", imgPesquisar);
		consultarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		consultarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				consultaInternalFrame.setVisible(true);

			}
		});
		menuConsultar.add(consultarMenuItem);

		listarMenuItem = new JMenuItem("Listar", imgListar);
		listarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		listarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				listaInternalFrame.setVisible(true);
				listaPedidos();

			}
		});
		menuConsultar.add(listarMenuItem);

		JMenu menuRelatrio = new JMenu("Relat\u00F3rio");
		menuRelatrio.setMnemonic('r');
		menuBar.add(menuRelatrio);

		relatorioMenuItem = new JMenuItem("Relat\u00F3rio", imgRelatorio);
		relatorioMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		relatorioMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				relatorioInterFrame.setVisible(true);

			}
		});
		menuRelatrio.add(relatorioMenuItem);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		desktopPane = new JDesktopPane();

		consultaInternalFrame = new JInternalFrame("Consulta Pedido");
		consultaInternalFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		consultaInternalFrame.getContentPane().setBackground(SystemColor.controlHighlight);
		consultaInternalFrame.setClosable(true);
		consultaInternalFrame.setIconifiable(true);
		consultaInternalFrame.setBounds(33, 6, 321, 421);
		desktopPane.add(consultaInternalFrame);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(6, 20, 48, 16);

		consultaInterTextField = new JTextField();
		consultaInterTextField.setBounds(60, 14, 243, 28);
		consultaInterTextField.setColumns(10);

		pesquisarConsultaInterBotao = new JButton("Pesquisar", imgPesquisar);
		pesquisarConsultaInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pesquisaConsulta();

			}
		});
		pesquisarConsultaInterBotao.setBounds(45, 54, 105, 28);

		cancelarConsultaInterBotao = new JButton("Cancelar", imgCancelar);
		cancelarConsultaInterBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				consultaInterTextArea.setText("");
				consultaInterTextField.setText("");
				pedido = null;
				listaAcompanhamentos.clear();
				listaPizzas.clear();
				listaPizzasPedidas.clear();

			}
		});
		cancelarConsultaInterBotao.setBounds(159, 54, 105, 28);
		consultaInternalFrame.getContentPane().setLayout(null);
		consultaInternalFrame.getContentPane().add(lblNmero);
		consultaInternalFrame.getContentPane().add(consultaInterTextField);
		consultaInternalFrame.getContentPane().add(pesquisarConsultaInterBotao);
		consultaInternalFrame.getContentPane().add(cancelarConsultaInterBotao);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 94, 297, 221);
		consultaInternalFrame.getContentPane().add(scrollPane);

		consultaInterTextArea = new JTextArea();
		scrollPane.setViewportView(consultaInterTextArea);
		consultaInterTextArea.setEditable(false);
		consultaInterTextArea.setWrapStyleWord(true);
		consultaInterTextArea.setLineWrap(true);

		encerrarPedidoBotao = new JButton("Encerrar", imgEncerrado);
		encerrarPedidoBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				encerraPedido();
			}
		});
		encerrarPedidoBotao.setEnabled(false);
		encerrarPedidoBotao.setBounds(43, 325, 100, 28);
		consultaInternalFrame.getContentPane().add(encerrarPedidoBotao);

		excluirPedidoBotao = new JButton("Excluir", imgDelete);
		excluirPedidoBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluiPedido();

			}
		});
		excluirPedidoBotao.setBounds(155, 325, 100, 28);
		consultaInternalFrame.getContentPane().add(excluirPedidoBotao);

		listaInternalFrame = new JInternalFrame("Lista de Pedidos");
		listaInternalFrame.setIconifiable(true);
		listaInternalFrame.setClosable(true);
		listaInternalFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		listaInternalFrame.setBounds(524, 6, 327, 421);
		desktopPane.add(listaInternalFrame);

		scrollPaneListaPedidos = new JScrollPane();
		listaInternalFrame.getContentPane().add(scrollPaneListaPedidos, BorderLayout.CENTER);

		tabelaInternalListar = new JTable();
		scrollPaneListaPedidos.setViewportView(tabelaInternalListar);

		relatorioInterFrame = new JInternalFrame("Relat\u00F3rio");
		relatorioInterFrame.setBackground(SystemColor.controlHighlight);
		relatorioInterFrame.setClosable(true);
		relatorioInterFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		relatorioInterFrame.setIconifiable(true);
		relatorioInterFrame.setBounds(247, 20, 477, 371);
		desktopPane.add(relatorioInterFrame);

		dataRelatorioLabel = new JLabel("Data: ");
		dataRelatorioLabel.setBounds(95, 12, 32, 16);

		scrollPaneRelatorio = new JScrollPane();
		scrollPaneRelatorio.setBounds(6, 80, 453, 260);

		pesquisarRelatorioBotao = new JButton("Pesquisar", imgPesquisar);
		pesquisarRelatorioBotao.setBounds(109, 46, 115, 28);
		pesquisarRelatorioBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				relatorioDoPedido();

			}
		});
		relatorioInterFrame.getContentPane().setLayout(null);

		relatorioTextArea = new JTextArea();
		relatorioTextArea.setEditable(false);
		scrollPaneRelatorio.setViewportView(relatorioTextArea);
		relatorioInterFrame.getContentPane().add(scrollPaneRelatorio);
		relatorioInterFrame.getContentPane().add(dataRelatorioLabel);
		relatorioInterFrame.getContentPane().add(pesquisarRelatorioBotao);

		cancelarRelatorioBotao = new JButton("Cancelar", imgCancelar);
		cancelarRelatorioBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				relatorioTextArea.setText("");
				diaRelatoriocomboBox.setSelectedIndex(0);
				mesRelatorioComboBox.setSelectedIndex(0);
				anoRelatorioComboBox.setSelectedIndex(0);

			}
		});
		cancelarRelatorioBotao.setBounds(240, 46, 115, 28);
		relatorioInterFrame.getContentPane().add(cancelarRelatorioBotao);

		diaRelatoriocomboBox = new JComboBox();
		diaRelatoriocomboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		diaRelatoriocomboBox.setBounds(130, 7, 58, 26);
		relatorioInterFrame.getContentPane().add(diaRelatoriocomboBox);

		mesRelatorioComboBox = new JComboBox();
		mesRelatorioComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		mesRelatorioComboBox.setBounds(200, 7, 53, 26);
		relatorioInterFrame.getContentPane().add(mesRelatorioComboBox);

		anoRelatorioComboBox = new JComboBox();
		anoRelatorioComboBox.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099"}));
		anoRelatorioComboBox.setBounds(265, 7, 79, 26);
		relatorioInterFrame.getContentPane().add(anoRelatorioComboBox);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(6)
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
				);
		panel.setLayout(gl_panel);

		// Se o Funcionário for Colaborador, desative o botão de excluir.
		if(tipoFuncionario.getTipoUsuario() == 'C')
			excluirPedidoBotao.setEnabled(false);

		setVisible(true);
		setLocationRelativeTo(null);
	}// Fim do construtor()

	/** Trata o evento do botão pesquisarConsultaInterBotao */
	private static void pesquisaConsulta(){

		consultaInterTextArea.setText("");
		zeraListaEstaticas();

		String numeroPedido = consultaInterTextField.getText();

		pedido = controlePedido.conPedido(numeroPedido);

		if(pedido == null){

			consultaInterTextArea.setText("");
			encerrarPedidoBotao.setEnabled(false);
			return;
		}

		// Pega as informações das pizzas pedidas na tabela PizzaPedida.
		listaPizzasPedidas = controlePedido.listaPizzasPedidas(numeroPedido);

		if(listaPizzasPedidas == null)
			return;

		// Pega as pizzas.
		for(int contador = 0; contador < listaPizzasPedidas.size(); contador++){
			
			Pizza pizza = controlePizza.conPizzaCodigo(listaPizzasPedidas.get(contador).getCodigoPizza());
			ArrayList<Ingrediente>lista = controlePizza.listaIngredientesPizza(pizza.getDescricao());
			
			// Pega a lista de ingredientes da pizza e soma com seu preço.
			if(lista != null)
				for(int x = 0; x < lista.size(); x++)
					pizza.setPreco(pizza.getPreco() + lista.get(x).getPreco());
			
			listaPizzas.add(pizza);
			
		}

		/*
		 * PEDIR O PROFESSOR PARA INSERIR O CAMPO NUMERO_PEDIDO.
		 * 
		 * // Verifica se a pizza é personalizada, se for retorna uma lista de ingredientes.
		ArrayList<Ingrediente>listaIngrediente = new ArrayList<>();
		ArrayList<CompozicaoPizza>listaCompozicao = new ArrayList<>();
		for(int contador = 0; contador < listaPizzas.size(); contador++){

			if(listaPizzas.get(contador).getDescricao().startsWith("personalizada") == true){



			}

		}*/

		// Pega os acompanhamentos.
		listaAcompanhamentos = controlePedido.listaAcompanhamentos(numeroPedido);

		if(listaAcompanhamentos == null)
			return;

		setaValoresConsultaTexto();

	}// pesquisaConsulta()

	/** Trata o evento do botão encerrar pedido */
	private static void encerraPedido(){

		if(pedido != null){

			if(controlePedido.encerraPedido(pedido.getNumeroPedido()) == true){

				pedido.setSituacao("Fechado");
				setaValoresConsultaTexto();
				encerrarPedidoBotao.setEnabled(false);
				listaPedidos();
			}

		}

	}// encerraPedido()


	/** Seta os valores do pedido no campo consulta.
	 * 
	 *  */
	private static void setaValoresConsultaTexto(){

		// Seta os valores na área de texto.
		String relatorio = pedido.toString();

		relatorio += "\n\n";

		for(int contador = 0; contador < listaPizzas.size(); contador++)
			relatorio += listaPizzas.get(contador).toString() + "\n";

		relatorio += "\n";

		for(int contador = 0; contador < listaAcompanhamentos.size(); contador++)
			relatorio += listaAcompanhamentos.get(contador).toString();

		consultaInterTextArea.setText(relatorio);

		// Verifica a situação do pedido se estiver aberto, ativa o botão de encerrar.
		if(pedido.getSituacao().equalsIgnoreCase("Aberto") == true)
			encerrarPedidoBotao.setEnabled(true);


	} //setaValoresConsultaTexto()

	/** Exclui um pedido do sistema. */
	private static void excluiPedido(){

		if(pedido != null){

			if(controlePedido.excPedido(pedido.getNumeroPedido()) == true){

				consultaInterTextArea.setText("");
				consultaInterTextField.setText("");
				pedido = null;
				listaPedidos();

			}

		}

	}// excluiPedido()

	/** Lista os pedidos do sistema e insere em uma tabela. */
	private static void listaPedidos(){

		ArrayList<Pizzaria>lista = controlePedido.listarPedidos();

		if(lista == null)
			return;

		geraTabelaListar(lista);


	}// listaPedidos()

	/* ****************************** Gera uma tabela com os ingredientes *****************************/
	/** Gera uma tabela com todos os dados do arrayList.*/
	private static void geraTabelaListar(ArrayList<Pizzaria> lista){

		if(lista == null)
			return;

		else{

			// Gera a tabela com todos os valores do banco de dados.
			String campos[] = {"N°", "Data", "Hora", "Situação"};

			String t[][] = new String[lista.size()][4];

			for(int l = 0, x = 0; l < lista.size(); l++, x++)
				for(int c = 0; c < 4; c++){

					if(c == 0)
						t[l][c] = ((Pedido) lista.get(x)).getNumeroPedido() + "";

					if(c == 1){
						String data = EntradaESaida.dataToString( ((Pedido) lista.get(x)).getDataHora());
						t[l][c] = data;
					}
					// Colocar ******* na senha.
					if(c == 2)
						t[l][c] = ((Pedido) lista.get(x)).getHora();

					if(c == 3)
						t[l][c] = ((Pedido) lista.get(x)).getSituacao();

				}

			tabelaInternalListar = new JTable(t, campos);
			scrollPaneListaPedidos.setViewportView(tabelaInternalListar);

		}

	}// geraTabelaListar()

	/** Gera o relatório das pizzas e acompanhamentos que foram vendidos na data passada pelo usuário.. */
	private static void relatorioDoPedido(){

		String data = EntradaESaida.formataData(diaRelatoriocomboBox.getSelectedItem().toString(),
				mesRelatorioComboBox.getSelectedItem().toString(), 
				anoRelatorioComboBox.getSelectedItem().toString());

		// Pega os pedidos que foram atendidos na data passada pelo usuário.
		ArrayList<Pedido> listaPedido = controlePedido.listaPedidosData(data);

		if(listaPedido == null || listaPedido.size() == 0){

			EntradaESaida.msgErro("Não existe pedidos para esta data", "Relatório");

			relatorioTextArea.setText("");
			return;
		}

		// Pecorre os arrays de todos os pedidos cadastrados e pega as pizzaPedidas cadastradas em todos.
		ArrayList<PizzaPedida>listaPizzasPedidasFinal = new ArrayList<>();
		for(int contador = 0; contador < listaPedido.size(); contador++){
			// Pega as informações das pizzas pedidas na tabela PizzaPedida.
			ArrayList<PizzaPedida>listaPizzasPedidas = controlePedido.listaPizzasPedidas
					(listaPedido.get(contador).getNumeroPedido() + "");

			for(int x = 0; x < listaPizzasPedidas.size(); x++)
				listaPizzasPedidasFinal.add(listaPizzasPedidas.get(x));

		}

		// Pega as pizzas e soma seus valores com seus ingredientes.
		ArrayList<Pizza>listaPizza = new ArrayList<>();
		for(int contador = 0; contador < listaPizzasPedidasFinal.size(); contador++){
			
			Pizza pizza = controlePizza.conPizzaCodigo(listaPizzasPedidasFinal.get(contador).getCodigoPizza());
			ArrayList<Ingrediente>listaIngrediente = controlePizza.listaIngredientesPizza(pizza.getDescricao());
			
			// Percorre a lista de ingredientes da pizza e somando o preço dos ingredientes ao preço da pizza.
			if(listaIngrediente != null)
				for(int x = 0; x < listaIngrediente.size(); x++)
					pizza.setPreco(pizza.getPreco() + listaIngrediente.get(x).getPreco());
			
			
			listaPizza.add(pizza);
			//listaPizza.add(controlePizza.conPizzaCodigo(listaPizzasPedidasFinal.get(contador).getCodigoPizza()));
		}
		/*
		 * PEDIR O PROFESSOR PARA INSERIR O CAMPO NUMERO_PEDIDO.
		 * 
		 * // Verifica se a pizza é personalizada, se for retorna uma lista de ingredientes.
		ArrayList<Ingrediente>listaIngrediente = new ArrayList<>();
		ArrayList<CompozicaoPizza>listaCompozicao = new ArrayList<>();
		for(int contador = 0; contador < listaPizzas.size(); contador++){

			if(listaPizzas.get(contador).getDescricao().startsWith("personalizada") == true){



			}

		}*/

		// Pecorre os arrays de todos os pedidos cadastrados e pega os Acompanhamento cadastradas em todos.
		ArrayList<Acompanhamento>listaAcompanhamentoFinal = new ArrayList<>();
		for(int contador = 0; contador < listaPedido.size(); contador++){
			// Pega as informações das pizzas pedidas na tabela PizzaPedida.
			ArrayList<Acompanhamento>listaAcompanhamento = controlePedido.
					listaAcompanhamentos(listaPedido.get(contador).getNumeroPedido() + "");

			for(int x = 0; x < listaAcompanhamento.size(); x++)
				listaAcompanhamentoFinal.add(listaAcompanhamento.get(x));

		}

		/* ****************************** Gerando relatório ******************************* */
		float totalPrecoPizzas = 0;
		String relatorio = "Relatório do dia: " + data + "\n\n ************************************* "
				+ "Pizzas *************************************";
		for(int contador = 0; contador < listaPizza.size(); contador++){

			relatorio += "\n\nDescrição: " + listaPizza.get(contador).getDescricao() + "\n";
			relatorio += "Preço: R$ " + listaPizza.get(contador).getPreco();

			totalPrecoPizzas += listaPizza.get(contador).getPreco();

		}

		relatorio += "\n\n Preço total de pizzas: R$ " + totalPrecoPizzas;

		relatorio += "\n\n ****************************** Acompanhamentos "
				+ "******************************";

		float totalPrecoAcompanhamento = 0;
		for(int contador = 0; contador < listaAcompanhamentoFinal.size(); contador++){

			relatorio += "\n\nAcompanhamento: " + listaAcompanhamentoFinal.get(contador).getTipo() + "\n";
			relatorio += "Quantidade: " + listaAcompanhamentoFinal.get(contador).getQuantidade() + "\n";
			relatorio += "Preço: R$ " + listaAcompanhamentoFinal.get(contador).getPreco();

			totalPrecoAcompanhamento += listaAcompanhamentoFinal.get(contador).getPreco();

		}

		relatorio += "\n\n Preço total de acompanhamentos: R$ " + totalPrecoAcompanhamento;

		float total = totalPrecoPizzas + totalPrecoAcompanhamento;

		relatorio += "\n\n*********************************************** "
				+ "******************************\n\nPreço total: R$ " + total;

		relatorioTextArea.setText(relatorio);

	}// relatorioDoPedido()

	/** Zera todas as listas(listaAcompanhamentos, listaPizzas e listaPizzasPedidas) da aplicação.*/
	private static void zeraListaEstaticas(){

		listaAcompanhamentos.clear();
		listaPizzas.clear();
		listaPizzasPedidas.clear();

	}// zeraListaEstaticas()

}// Fim da classe PedidoGui
