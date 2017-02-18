package tsi.too.pv.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import tsi.too.pv.controle.ControlePizza;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pizza;

@SuppressWarnings("serial")
public class ComprarDetalhesPizzaGui extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTextArea pizzaDetalhes;

	/**
	 * Janela responsável por mostrar os ingredientes que compõem a pizza.
	 */
	public ComprarDetalhesPizzaGui(String descricao) {
		setResizable(false);
		setModal(true);
		setTitle("Detalhes Pizza");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		pizzaDetalhes = new JTextArea();
		pizzaDetalhes.setWrapStyleWord(true);
		pizzaDetalhes.setLineWrap(true);
		pizzaDetalhes.setEditable(false);
		scrollPane.setViewportView(pizzaDetalhes);

		mostraDetalhes(descricao);

		setLocationRelativeTo(null);
		setVisible(true);

	}// Fim do construtor

	/** Mostra em um JDialog as informações da pizza, tal como preço, data e lista de ingredientes.
	 * 
	 *  @param descricao - uma <i>String</i> contendo a descrição(nome) da pizza.
	 *  */
	private static void mostraDetalhes(String descricao){

		ControlePizza controlePizza = new ControlePizza();
		Pizza pizza = controlePizza.conPizza(descricao);

		// Pega a lista de ingredientes da pizza.
		ArrayList<Ingrediente>lista = controlePizza.listaIngredientesPizza(descricao);

		float preco = pizza.getPreco();
		String relatorio = pizza.toString() + "\n";

		for(int contador = 0; contador < lista.size(); contador++){

			preco += lista.get(contador).getPreco();
			relatorio += lista.get(contador).toString();

		}

		relatorio += "\n\n Preço total: R$ " + preco;
		pizzaDetalhes.setText(relatorio);

	}

}// Fim da classe ComprarDetalhesPizzaGui
