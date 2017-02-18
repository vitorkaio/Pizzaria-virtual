package tsi.too.pv.gui;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Acompanhamento;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class ComprarAcompanhamentoGui extends JDialog {

	private static String REFRIGERANTE_350 = "Refrigerante lata de 350 ml";
	private static float precoRefri350 = 2.00f;

	private static String REFRIGERANTE_600 = "Refrigerante lata de 600 ml";
	private static float precoRefri600 = 3.50f;

	private static String REFRIGERANTE_1 = "Refrigerante lata de 1L";
	private static float precoRefri1 = 5.00f;

	private static String CERVEJA_350 = "Cerveja lata de 350 ml";
	private static float precoCerveja350 = 2.50f;

	private final JPanel contentPanel = new JPanel();
	private JPanel acompanhamentoPanel;
	private static JCheckBox refri350CheckBox;
	private static JCheckBox refri600CheckBox;
	private static JCheckBox refri1LCheckBox;
	private static JCheckBox cerveja350CheckBox;
	private static JPanel quantidadePanel;
	private static JTextField refri350QuantidadeTextFiel;
	private static JTextField refri600QuantidadeTextField;
	private static JTextField refri1LQuantidadeTextField;
	private static JTextField cerveja350QuantidadeTextField;
	private static JButton adicionarBotao;
	private static JButton cancelarBotao;

	/** É um ArrayList com todos os checkBox's que a gui disponibiliza.*/
	private static ArrayList<JCheckBox>acompanhamentosCheckBoxes;

	/** ArrayList com todos os campos(JTextField) quantidade referentes aos checkBox's. */
	private static ArrayList<JTextField>acompanhamentosQuantidadeJTextField;

	/**
	 * Cria uma janela(JDialog) para adicionar os acompanhamentos.
	 * 
	 * @param listaAcompanhamento é um <code>ArrayList</code> onde será armazenado os acompanhamentos
	 * escolhidos pelo usuário.
	 */
	public ComprarAcompanhamentoGui(ArrayList<Acompanhamento>listaAcompanhamento) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ComprarAcompanhamentoGui.class.getResource("/img/pizzaria.png")));
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setModal(true);
		
		ImageIcon imgCancelar = new ImageIcon(this.getClass().getResource("/img/cancelar.png"));
		ImageIcon imgGravar = new ImageIcon(this.getClass().getResource("/img/gravar.png"));

		acompanhamentosCheckBoxes = new ArrayList<>();
		acompanhamentosQuantidadeJTextField = new ArrayList<>();


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


		setTitle("Adicionar Acompanhamentos");
		setBounds(100, 100, 337, 267);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.controlHighlight);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		acompanhamentoPanel = new JPanel();
		acompanhamentoPanel.setBounds(6, 6, 215, 172);
		acompanhamentoPanel.setBorder(new TitledBorder(null, "Acompanhamentos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		acompanhamentoPanel.setBackground(SystemColor.controlHighlight);

		refri1LCheckBox = new JCheckBox(REFRIGERANTE_1);
		refri1LCheckBox.setToolTipText("Pre\u00E7o R$ 5.00");
		refri1LCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Se o evento do check box ocorrer chama o método ativaCampoQuantidade.
				ativaCampoQuantidade(refri1LCheckBox, refri1LQuantidadeTextField);

			}
		});

		cerveja350CheckBox = new JCheckBox(CERVEJA_350);
		cerveja350CheckBox.setToolTipText("Pre\u00E7o R$ 2.50");
		cerveja350CheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Se o evento do check box ocorrer chama o método ativaCampoQuantidade.
				ativaCampoQuantidade(cerveja350CheckBox, cerveja350QuantidadeTextField);

			}
		});

		refri350CheckBox = new JCheckBox(REFRIGERANTE_350);
		refri350CheckBox.setToolTipText("Pre\u00E7o R$ 2.00");
		refri350CheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Se o evento do check box ocorrer chama o método ativaCampoQuantidade.
				ativaCampoQuantidade(refri350CheckBox, refri350QuantidadeTextFiel);

			}
		});

		refri600CheckBox = new JCheckBox(REFRIGERANTE_600);
		refri600CheckBox.setToolTipText("Pre\u00E7o R$ 3.50");
		refri600CheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Se o evento do check box ocorrer chama o método ativaCampoQuantidade.
				ativaCampoQuantidade(refri600CheckBox, refri600QuantidadeTextField);

			}
		});
		GroupLayout gl_acompanhamentoPanel = new GroupLayout(acompanhamentoPanel);
		gl_acompanhamentoPanel.setHorizontalGroup(
			gl_acompanhamentoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_acompanhamentoPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_acompanhamentoPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(refri1LCheckBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addComponent(refri600CheckBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_acompanhamentoPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(refri350CheckBox, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
							.addComponent(cerveja350CheckBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_acompanhamentoPanel.setVerticalGroup(
			gl_acompanhamentoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_acompanhamentoPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(refri350CheckBox)
					.addGap(18)
					.addComponent(refri600CheckBox)
					.addGap(18)
					.addComponent(refri1LCheckBox)
					.addGap(18)
					.addComponent(cerveja350CheckBox)
					.addContainerGap(8, Short.MAX_VALUE))
		);
		acompanhamentoPanel.setLayout(gl_acompanhamentoPanel);
		contentPanel.add(acompanhamentoPanel);

		quantidadePanel = new JPanel();
		quantidadePanel.setBackground(SystemColor.controlHighlight);
		quantidadePanel.setBorder(new TitledBorder(null, "Quantidade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		quantidadePanel.setBounds(217, 6, 108, 172);
		contentPanel.add(quantidadePanel);

		refri350QuantidadeTextFiel = new JTextField();
		refri350QuantidadeTextFiel.setEnabled(false);
		refri350QuantidadeTextFiel.setBounds(14, 24, 77, 28);
		refri350QuantidadeTextFiel.setColumns(10);

		refri600QuantidadeTextField = new JTextField();
		refri600QuantidadeTextField.setEnabled(false);
		refri600QuantidadeTextField.setBounds(14, 58, 77, 28);
		refri600QuantidadeTextField.setColumns(10);
		quantidadePanel.setLayout(null);
		quantidadePanel.add(refri350QuantidadeTextFiel);
		quantidadePanel.add(refri600QuantidadeTextField);

		refri1LQuantidadeTextField = new JTextField();
		refri1LQuantidadeTextField.setEnabled(false);
		refri1LQuantidadeTextField.setBounds(14, 91, 77, 28);
		quantidadePanel.add(refri1LQuantidadeTextField);
		refri1LQuantidadeTextField.setColumns(10);

		cerveja350QuantidadeTextField = new JTextField();
		cerveja350QuantidadeTextField.setEnabled(false);
		cerveja350QuantidadeTextField.setBounds(14, 122, 77, 28);
		quantidadePanel.add(cerveja350QuantidadeTextField);
		cerveja350QuantidadeTextField.setColumns(10);

		adicionarBotao = new JButton("Adicionar", imgGravar);
		adicionarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				adcionaAcompanhamento(listaAcompanhamento);
				dispose();

			}
		});
		adicionarBotao.setBounds(61, 190, 99, 28);
		contentPanel.add(adicionarBotao);

		cancelarBotao = new JButton("Cancelar", imgCancelar);
		cancelarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listaAcompanhamento.clear();
				dispose();
				
			}
		});
		cancelarBotao.setBounds(172, 190, 99, 28);
		contentPanel.add(cancelarBotao);

		// Adicionando o checkBox's no arrayList.
		acompanhamentosCheckBoxes.add(refri350CheckBox);
		acompanhamentosCheckBoxes.add(refri600CheckBox);
		acompanhamentosCheckBoxes.add(refri1LCheckBox);
		acompanhamentosCheckBoxes.add(cerveja350CheckBox);

		// Adicionando os JTextFields no arrayList.
		acompanhamentosQuantidadeJTextField.add(refri350QuantidadeTextFiel);
		acompanhamentosQuantidadeJTextField.add(refri600QuantidadeTextField);
		acompanhamentosQuantidadeJTextField.add(refri1LQuantidadeTextField);
		acompanhamentosQuantidadeJTextField.add(cerveja350QuantidadeTextField);

		setLocationRelativeTo(null);
		setVisible(true);
		
	}// Fim do construtor()

	/* ******************************** Ativa campos quantidade ******************************** */
	/** Verifica se o algum check box foi marcado, caso foi, ative o campo quantidade referente a ele. 
	 * 
	 * @param check é um <code>JEcheckBox</code>, se ele está ativado, ativa o campo de texto referente, caso contrário desativa.
	 * @param text <code>JTextFiel</code> que será ativado ou desativado.
	 * */
	private static void ativaCampoQuantidade(JCheckBox check, JTextField text){

		// Verifica se o checkBox está ativado, então ativa o JTextField
		if(check.isSelected() == true)
			text.setEnabled(true);

		else
			text.setEnabled(false);

	}// ativaCampoQuantidade()

	/* ******************************** Adciona Acompanhamento ******************************** */
	/** Finaliza a escolha dos acompanhamentos.
	 * 
	 * @param lista um <code>ArrayList</code> onde será inseridos os acopanhamentos escolhidos.
	 * */
	private static void adcionaAcompanhamento(ArrayList<Acompanhamento>lista){

		// Verifica quais checkBox estão ativados e verifica o campo quantidade referentes a eles.
		// E por fim adiciona no arrayList listaAcompanhamentos.
		for(int contador = 0; contador < acompanhamentosCheckBoxes.size(); contador++){

			if(acompanhamentosCheckBoxes.get(contador).isSelected() == true){

				// Pega a quantidade referente ao checkBox.
				String qtd = acompanhamentosQuantidadeJTextField.get(contador).getText();

				// Verifica se o valor passado é válido, caso não, o valor será 1 como padrão.
				int quantidade = EntradaESaida.stringToInt(qtd);

				if(quantidade < 0)
					quantidade = 1;

				Acompanhamento acompanhamento = new Acompanhamento();
				String tipo = acompanhamentosCheckBoxes.get(contador).getText();

				acompanhamento.setTipo(tipo);
				acompanhamento.setQuantidade(quantidade);

				// Verifica qual acompanhamento foi escolhido. Possuem preços diferentes.
				if(tipo.equalsIgnoreCase(REFRIGERANTE_350) == true)
					acompanhamento.setPreco(quantidade * precoRefri350);

				else if(tipo.equalsIgnoreCase(REFRIGERANTE_600) == true)
					acompanhamento.setPreco(quantidade * precoRefri600);

				else if(tipo.equalsIgnoreCase(REFRIGERANTE_1) == true)
					acompanhamento.setPreco(quantidade * precoRefri1);

				else if(tipo.equalsIgnoreCase(CERVEJA_350) == true)
					acompanhamento.setPreco(quantidade * precoCerveja350);

				lista.add(acompanhamento);

			}

		}


	}// adcionaAcompanhamento()

}// Fim da class AddAcompanhamento.
