package tsi.too.pv.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import tsi.too.pv.entradas.EntradaESaida;

@SuppressWarnings("serial")
public class InicializacaoGui extends JFrame {

	private JPanel contentPane;
	private static JProgressBar carregandoProgressBar;
	private JPanel paienlCentral;
	private static JLabel imgModulosLabel;
	private static JLabel textoModulosLabel;

	private static final int MY_MINIMUM = 0; 

	private static final int MY_MAXIMUM = 100;
	private static JLabel valuesProgressBarLabel;

	private static ImageIcon imgFuncionario;
	private static ImageIcon imgCliente;
	private static ImageIcon imgPizza;
	private static ImageIcon imgIngrediente;
	private static ImageIcon imgComprar;
	private static ImageIcon imgPedido;

	/**
	 * Create the frame.
	 */
	public InicializacaoGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicializacaoGui.class.getResource("/img/pizzaria.png")));

		setResizable(false);
		setTitle("Inicializando");

		imgFuncionario = new ImageIcon(this.getClass().getResource("/img/funcionario.png"));
		imgCliente = new ImageIcon(this.getClass().getResource("/img/cliente.png"));
		imgPizza = new ImageIcon(this.getClass().getResource("/img/pizza.png"));
		imgIngrediente = new ImageIcon(this.getClass().getResource("/img/ingrediente.png"));
		imgComprar = new ImageIcon(this.getClass().getResource("/img/comprar.png"));
		imgPedido = new ImageIcon(this.getClass().getResource("/img/pedido.png"));

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 347);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		paienlCentral = new JPanel();
		paienlCentral.setBackground(new Color(70, 130, 180));
		paienlCentral.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		paienlCentral.setLayout(null);

		imgModulosLabel = new JLabel("");
		imgModulosLabel.setBounds(214, 38, 136, 136);
		paienlCentral.add(imgModulosLabel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
						.addGap(20)
						.addComponent(paienlCentral, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(15)
						.addComponent(paienlCentral, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(57, Short.MAX_VALUE))
				);

		textoModulosLabel = new JLabel("");
		textoModulosLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		textoModulosLabel.setBounds(199, 186, 192, 22);
		paienlCentral.add(textoModulosLabel);
		contentPane.setLayout(gl_contentPane);

		setVisible(true);
		setLocationRelativeTo(null);

		imgModulosLabel.setIcon(imgFuncionario);
		textoModulosLabel.setText("Configurando Funcionários");

		carregandoProgressBar = new JProgressBar();
		carregandoProgressBar.setStringPainted(true);
		carregandoProgressBar.setBounds(6, 234, 548, 14);
		paienlCentral.add(carregandoProgressBar);
		carregandoProgressBar.setMinimum(MY_MINIMUM);
		carregandoProgressBar.setMaximum(MY_MAXIMUM);

		valuesProgressBarLabel = new JLabel("");
		valuesProgressBarLabel.setBounds(270, 254, 55, 16);
		paienlCentral.add(valuesProgressBarLabel);
		
		inicializaProgressBar();
		
		dispose();
		new LoginGui();

	}// Fim do construtor

	/** Inicializa o JProgressBar. */
	private static void inicializaProgressBar(){
		
		for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) { 
			final int percent = i; 
			try { 
				SwingUtilities.invokeLater(new Runnable() { 
					public void run() { 
						carregandoProgressBar.setValue(percent);
						valuesProgressBarLabel.setText(percent + "%");

						if(percent == 15)
							EntradaESaida.somLogon();
						
						else if(percent == 21){
							imgModulosLabel.setIcon(imgCliente);
							textoModulosLabel.setText("Configurando Clientes");

						}

						else if(percent == 31){
							imgModulosLabel.setIcon(imgPizza);
							textoModulosLabel.setText("Configurando Pizzas");
						}

						else if(percent == 45){
							imgModulosLabel.setIcon(imgIngrediente);
							textoModulosLabel.setText("Configurando Ingredientes");
						}

						else if(percent == 65){
							imgModulosLabel.setIcon(imgComprar);
							textoModulosLabel.setText("Configurando Compras");
						}

						else if(percent == 85){
							imgModulosLabel.setIcon(imgPedido);
							textoModulosLabel.setText("Configurando Pedidos");
						}

					}
				}); 
				Thread.sleep(110); 
			} catch (InterruptedException e) { 

			} 
		}

	}// inicializaProgressBar()


}// Fim da class InicializacaoGui
