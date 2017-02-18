package tsi.too.pv.gui;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import tsi.too.pv.controle.ControleCliente;
import tsi.too.pv.tipos.Cliente;
import tsi.too.pv.tipos.Pizzaria;

public class ClienteExcluir extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cpfPesTextField;
	private JPanel lista;
	private JTable tabelaInternalListar;
	private JScrollPane scrollPane;
	private JTextArea cpfPesAreaTexto;

	/**
	 * Create the frame.
	 */
	public ClienteExcluir() {

		ImageIcon imgListar = new ImageIcon(this.getClass().getResource("/img/lista.png"));
		ImageIcon imgPesquisar = new ImageIcon(this.getClass().getResource("/img/pesquisa.png"));
		ImageIcon imgCancelar = new ImageIcon(this.getClass().getResource("/img/cancelar.png"));
		ImageIcon imgDelete = new ImageIcon(this.getClass().getResource("/img/delete.png"));

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

		setResizable(false);
		setTitle("Excluir Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 387);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel cpfPesLabel = new JLabel("CPF: ");

		cpfPesTextField = new JTextField();
		cpfPesTextField.setColumns(10);

		JButton cpfPesBotao = new JButton("Pesquisar", imgPesquisar);
		cpfPesBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pegando o nome do campo text field.
				String cpf = cpfPesTextField.getText();

				ControleCliente controleCliente = new ControleCliente();
				Cliente cliente = controleCliente.conCliente(cpf);

				if(cliente == null)
					;

				else{

					// Setando os valores do funcionário na area de texto.
					cpfPesAreaTexto.setText(cliente.toString());

				}
			}
		});

		cpfPesAreaTexto = new JTextArea();
		cpfPesAreaTexto.setEditable(false);
		cpfPesAreaTexto.setLineWrap(true);
		cpfPesAreaTexto.setWrapStyleWord(true);

		lista = new JPanel();
		lista.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lista.setBackground(SystemColor.controlHighlight);

		JButton btnExcluir = new JButton("Excluir", imgDelete);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Se a área de texto estiver vazia não altera nada.
				if(cpfPesAreaTexto.getText().equalsIgnoreCase("") == true)
					;

				else{
					String cpf = cpfPesTextField.getText();
					ControleCliente controleCliente = new ControleCliente();

					if(controleCliente.excCliente(cpf) == true){
						cpfPesAreaTexto.setText("");
						cpfPesTextField.setText("");
						geraTabelaListar();
					}
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar", imgCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cpfPesAreaTexto.setText("");
				cpfPesTextField.setText("");

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(cpfPesAreaTexto)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(cpfPesLabel)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(cpfPesTextField, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(cpfPesBotao))))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(63)
										.addComponent(btnExcluir)
										.addGap(18)
										.addComponent(btnCancelar)))
						.addGap(38)
						.addComponent(lista, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lista, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(cpfPesLabel)
												.addComponent(cpfPesTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(cpfPesBotao))
										.addGap(18)
										.addComponent(cpfPesAreaTexto, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnCancelar)
												.addComponent(btnExcluir))))
						.addContainerGap())
				);

		JButton listarBotao = new JButton("Listar", imgListar);
		listarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Gera a tabela.
				geraTabelaListar();

			}
		});

		scrollPane = new JScrollPane();
		GroupLayout gl_lista = new GroupLayout(lista);
		gl_lista.setHorizontalGroup(
				gl_lista.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lista.createSequentialGroup()
						.addGroup(gl_lista.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_lista.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
								.addGroup(gl_lista.createSequentialGroup()
										.addGap(136)
										.addComponent(listarBotao)))
						.addContainerGap())
				);
		gl_lista.setVerticalGroup(
				gl_lista.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lista.createSequentialGroup()
						.addGap(15)
						.addComponent(listarBotao)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addContainerGap())
				);
		lista.setLayout(gl_lista);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	/** Gera uma tabela com todos os funcionários vindo do banco de dados*/
	private void geraTabelaListar(){

		ControleCliente controleCliente = new ControleCliente();

		// Array com os todos os funcionários vindo do banco de dados.
		ArrayList<Pizzaria>listaCliente = controleCliente.listaClientes();

		if(listaCliente == null)
			System.out.println("assad");

		else{

			// Gera a tabela com todos os valores do banco de dados.
			String campos[] = {"N°", "Cpf", "Nome"};
			String t[][] = new String[listaCliente.size()][10];

			for(int l = 0, x = 0; l < listaCliente.size(); l++, x++)
				for(int c = 0; c < 10; c++){

					if(c == 0)
						t[l][c] = x + "";

					if(c == 1)
						t[l][c] = ((Cliente) listaCliente.get(x)).getCpf();

					// Colocar ******* na senha.
					if(c == 2)
						t[l][c] = ((Cliente) listaCliente.get(x)).getNome();

				}

			tabelaInternalListar = new JTable(t, campos);
			scrollPane.setViewportView(tabelaInternalListar);

		}
	}
}
