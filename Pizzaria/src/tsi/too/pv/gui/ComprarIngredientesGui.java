package tsi.too.pv.gui;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import tsi.too.pv.controle.ControleIngrediente;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pizzaria;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class ComprarIngredientesGui extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel salvarPainel;
	@SuppressWarnings("rawtypes")
	private static JList listaNomesIngredientes;
	@SuppressWarnings("rawtypes")
	private static JList listaEscolhidosIngredientes;
	private JButton adicionarBotao;
	private JButton removerBotao;

	// Pega todos ingredientes do banco de dados e insere em um array.
	private static ControleIngrediente controle;
	private static ArrayList<Pizzaria> ingredientes;

	private static DefaultListModel<String>lista1;
	private static DefaultListModel<String>lista2;

	/**
	 * Cria uma janela(JDialog) para o usuário escolher os ingredientes para montar a pizza.
	 * 
	 * @param listaDeIngredientes <code>arrayList</code> que irá armazenar as escolhas do usuário.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ComprarIngredientesGui(ArrayList<Ingrediente>listaDeIngredientes) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ComprarIngredientesGui.class.getResource("/img/pizzaria.png")));
		setResizable(false);

		ImageIcon imgGravar = new ImageIcon(this.getClass().getResource("/img/gravar.png"));

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);

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

		lista1 = new DefaultListModel<>();
		lista2 = new DefaultListModel<>();

		// Pega todos ingredientes do banco de dados e insere em um array.
		controle = new ControleIngrediente();
		ingredientes = controle.listaIngrediente();

		setTitle("Adicionar Ingredientes");
		setBounds(100, 100, 528, 286);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.controlHighlight);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		{
			salvarPainel = new JPanel();
			salvarPainel.setBounds(41, 203, 451, 38);
			salvarPainel.setBackground(SystemColor.controlHighlight);
			{
				JButton salvarBotao = new JButton("Salvar", imgGravar);
				salvarBotao.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// Insere os elementos da JList da esquerda na lista de ingredientes.
						ingredientesEscolhidos(listaDeIngredientes);
						dispose();
					}
				});
				salvarPainel.add(salvarBotao);
			}
		}
		contentPanel.setLayout(null);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(327, 11, 184, 180);
		contentPanel.add(scrollPane2);

		listaEscolhidosIngredientes = new JList();
		scrollPane2.setViewportView(listaEscolhidosIngredientes);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 11, 184, 180);
		contentPanel.add(scrollPane);

		listaNomesIngredientes = new JList(carregaListaDeIngredientes());
		scrollPane.setViewportView(listaNomesIngredientes);

		if(listaDeIngredientes.size() > 0)
			alterarIngredientes(listaDeIngredientes);

		removerBotao = new JButton("<< Remover");
		removerBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Troca de lista.
				adicionaIngredientes(listaEscolhidosIngredientes, lista2, listaNomesIngredientes, lista1);

			}
		});

		adicionarBotao = new JButton("Adicionar >>");
		adicionarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Troca de lista.
				adicionaIngredientes(listaNomesIngredientes, lista1, listaEscolhidosIngredientes, lista2);

			}
		});

		adicionarBotao.setBounds(213, 59, 96, 28);
		contentPanel.add(adicionarBotao);
		removerBotao.setBounds(214, 105, 95, 28);
		contentPanel.add(removerBotao);
		contentPanel.add(salvarPainel);

		setLocationRelativeTo(null);
		setVisible(true);

	}// Fim do construtor.


	/** Insere no JList listaNomesIngredientes o nome de todos os ingredientes cadastrados no sistema. */
	private static DefaultListModel<String> carregaListaDeIngredientes(){

		for(int contador = 0; contador < ingredientes.size(); contador++)
			lista1.addElement(((Ingrediente) ingredientes.get(contador)).getDescricao());


		return lista1;

	}// carregaListaDeIngredientes()

	/** Move os itens escolhidos do primeiro JList para o segundo JList. 
	 * 
	 * @param JList1 é um <code>JList</code> com todos os ingredientes da esquerda. Parte visivel.
	 * @param ing1 é um <code>DefaultListModel</code> que controlá os ingredientes setados no JList1.
	 * @param JList2 é um <code>JList</code> com todos os ingredientes da direita. Parte visivel.
	 * @param ing2 é um <code>DefaultListModel</code> que controlá os ingredientes setados no JList2.
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void adicionaIngredientes(JList JList1, DefaultListModel<String> ing1, JList JList2, 
			DefaultListModel<String> ing2){

		// Array com os nomes do itens selecionados.
		List<String>lista = JList1.getSelectedValuesList();

		for(int contador = 0; contador < lista.size(); contador++){

			// Insere elementos no defaultListaModel 2.
			ing2.addElement(lista.get(contador));

		}

		JList2.setModel(ing2);

		// Remove elementos do JList 1.
		for(int contador = 0; contador < ing1.size(); contador++){
			for(int x = 0; x < lista.size(); x++){
				if(ing1.getElementAt(contador).equalsIgnoreCase(lista.get(x)) == true){
					ing1.remove(contador);

				}
			}
		}
		JList1 = new JList<>(ing1);

	}// adicionaIngredientes()

	/** Insere os ingredientes escolhidos pelo usuário no array. 
	 * 
	 * @param lista que armazenará os ingredientes escolhidos.
	 * */
	private static void ingredientesEscolhidos(ArrayList<Ingrediente>lista){
		
		// Limpa o array.
		lista.clear();
		
		for(int contador = 0; contador < ingredientes.size(); contador++)
			for(int x = 0; x < lista2.size(); x++)
				if(((Ingrediente) ingredientes.get(contador)).getDescricao().equalsIgnoreCase(lista2.get(x)) == true)
					lista.add((Ingrediente) ingredientes.get(contador));
		
		
	}// ingredientesEscolhidos()

	/**Se a lista Ingredientes for diferente de null, quer dizer que o cliente deseja alterar
	 * algum ingrediente de alguma pizza. Este método seta os valores já existentes na lista da
	 * direita e os remove da lista da esquerda*/
	@SuppressWarnings("unchecked")
	private static void alterarIngredientes(ArrayList<Ingrediente>listaComIngredientes){

		for(int contador = 0; contador < listaComIngredientes.size(); contador++){
			for(int x = 0; x < lista1.size(); x++){
				if(lista1.getElementAt(x).equalsIgnoreCase(listaComIngredientes.
						get(contador).getDescricao()) == true){
					lista1.remove(x);
					lista2.addElement(listaComIngredientes.get(contador).getDescricao());
					break;
				}			
			}

		}

		listaNomesIngredientes.setModel(lista1);
		listaEscolhidosIngredientes.setModel(lista2);

	}// alterarIngrdientes()
	
}// Fim da classe AddIngredientes()

