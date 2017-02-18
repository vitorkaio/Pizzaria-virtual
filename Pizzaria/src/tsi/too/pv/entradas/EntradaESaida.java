package tsi.too.pv.entradas;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.BufferedInputStream;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/** Possui um conjuntos de métodos para leitura, saída e verificação de dados.*/
public class EntradaESaida extends Thread{

	/** Emite um sinal de erro para o usuário. */
	public static void msgErro(Object mensagem, String titulo){

		somErro();
		showMessageDialog(null, mensagem, titulo, ERROR_MESSAGE);

	}
	
	/** Mostra um tela de diálogo para usuário em relação a uma pergunta.
	 * 
	 * @param mensagem - uma <code>String</code> com a mensagem que será exibida.
	 * @param titulo - uma <code>String</code> com o módulo que está utilizando o metódo. 
	 * . */
	public static int msgPergunta(Object mensagem, String titulo){

		somPergunta();
		return JOptionPane.showConfirmDialog(null, mensagem,
				titulo, JOptionPane.YES_NO_OPTION);

	}

	/** Mostra um tela de diálogo para usuário em relação a uma informação.
	 * 
	 * @param mensagem - uma <code>String</code> com a mensagem que será exibida.
	 * @param titulo - uma <code>String</code> com o módulo que está utilizando o metódo. 
	 * . */
	public static void msgInfo(Object mensagem, String titulo){

		somSucesso();
		showMessageDialog(null, mensagem, titulo, INFORMATION_MESSAGE);

	}

	/** Mostra um tela de diálogo para usuário em relação a entrada de texto para o programa.
	 * 
	 * @param mensagem - uma <code>String</code> com a mensagem que será exibida.
	 * @param titulo - uma <code>String</code> com o módulo que está utilizando o metódo. 
	 * . */
	public static String leString(Object mensagem, String titulo){

		String entrada = "";

		do{

			entrada = showInputDialog(null, mensagem, titulo, QUESTION_MESSAGE);

			if(entrada == null)
				return "";

		}while(entrada.equalsIgnoreCase(""));

		return entrada;
	}

	/**
	 * Reproduz um som de sucesso quando ocorrer algum.
	 * */
	private static void somSucesso(){

		Clip clip = null;
		try {  
			AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream
					(Thread.currentThread().getClass().getResourceAsStream("/sons/tada.wav")));  

			clip = AudioSystem.getClip();  
			clip.open(sound);  
			clip.start();  
		} catch (Exception e) {  
			e.printStackTrace();
		}  

	}// somSucesso.

	/**
	 * Reproduz um som de erro quando ocorrer algum.
	 * */
	private static void somErro(){

		Clip clip = null;
		try {  
			AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream
					(Thread.currentThread().getClass().getResourceAsStream("/sons/error.wav")));  

			clip = AudioSystem.getClip();  
			clip.open(sound);  
			clip.start();  
		} catch (Exception e) {  
			e.printStackTrace();
		}  

	}// somErro

	/**
	 * Reproduz um som de pergunta quando ocorrer algum.
	 * */
	public static void somPergunta(){

		Clip clip = null;
		try {  
			AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream
					(Thread.currentThread().getClass().getResourceAsStream("/sons/pergunta.wav")));  

			clip = AudioSystem.getClip();  
			clip.open(sound);  
			clip.start();  
		} catch (Exception e) {  
			e.printStackTrace();
		}  

	}// somPergunta.

	/**
	 * Reproduz um som de login quando ocorrer algum.
	 * */
	public static void somLogin(){

		Clip clip = null;
		try {  
			AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream
					(Thread.currentThread().getClass().getResourceAsStream("/sons/login.wav")));  

			clip = AudioSystem.getClip();  
			clip.open(sound);  
			clip.start();  
		} catch (Exception e) {  
			e.printStackTrace();
		}  

	}// somLogin.

	/**
	 * Reproduz um som de logout quando ocorrer algum.
	 * */
	public static void somLogout(){

		Clip clip = null;
		try {  
			AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream
					(Thread.currentThread().getClass().getResourceAsStream("/sons/logout.wav")));  

			clip = AudioSystem.getClip();  
			clip.open(sound);  
			clip.start();  
		} catch (Exception e) {  
			e.printStackTrace();
		}  

	}// somLogout.
	
	/**
	 * Reproduz um som de logon quando ocorrer algum.
	 * */
	public static void somLogon(){

		Clip clip = null;
		try {  
			AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream
					(Thread.currentThread().getClass().getResourceAsStream("/sons/logon.wav")));  

			clip = AudioSystem.getClip();  
			clip.open(sound);  
			clip.start();  
		} catch (Exception e) {  
			e.printStackTrace();
		}  

	}// somSucesso.

	/**
	 * Converte uma data do tipo <code>Calendar</code> para String
	 * @param cal Uma data válida do tipo Calendar
	 * @return uma  String com a data convertida.
	 */
	public static String dataToString(Calendar cal){

		int mes = cal.get(Calendar.MONTH);
		mes++;

		// Colocando 0 antes do número 10.
		if(cal.get(Calendar.DAY_OF_MONTH) < 10 && mes < 10)
			return "0" + cal.get(Calendar.DAY_OF_MONTH) + "/" + "0" + mes + "/" + cal.get(Calendar.YEAR);

		if(cal.get(Calendar.DAY_OF_MONTH) < 10)
			return "0" + cal.get(Calendar.DAY_OF_MONTH) + "/" + mes + "/" + cal.get(Calendar.YEAR);

		// Colocando 0 antes do número 10.
		if(mes < 10)
			return cal.get(Calendar.DAY_OF_MONTH) + "/" + "0" + mes + "/" + cal.get(Calendar.YEAR);

		String data = cal.get(Calendar.DAY_OF_MONTH) + "/" + mes + "/" + cal.get(Calendar.YEAR);

		return data;
	}

	/**
	 * Converte uma data do tipo String para <code>Calendar</code>.
	 * @param data uma String com a data
	 * @return um Calendar com a data convertida, null caso contrário.
	 */
	public static Calendar StringToData(String data){

		Calendar cal = Calendar.getInstance();
		StringTokenizer token = new StringTokenizer(data, "/");

		int dia = 0;
		int mes = 0;
		int ano = 0;

		while(token.hasMoreTokens()){

			try{
				dia = Integer.parseInt(token.nextToken());
				mes = Integer.parseInt(token.nextToken());
				ano = Integer.parseInt(token.nextToken());
			}

			catch(NumberFormatException ex){

				msgErro("Data inválida", "Data");
				return null;

			}
		}

		// Mes começa com 0 - Janeiro, 11 - Dezembro.
		mes--;

		cal.set(Calendar.DAY_OF_MONTH, dia);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, ano);

		return cal;
	}// StringToData

	/** Verfica se a data passada atende aos requisitos. 
	 * 
	 * @param diaa - dia do mês.
	 * @param mmes - mês do ano.
	 * @param aano - ano.
	 * */
	public static boolean DataOk(String diaa, String mmes, String aano) {  

		//variaveis que recebem o valor  
		int dia,mes;  
		Integer ano;    

		dia = Integer.parseInt(diaa); 
		mes = Integer.parseInt(mmes); 
		ano = Integer.parseInt(aano);

		if ((dia >= 1 && dia <= 31) && (mes >= 1 && mes <= 12) && (ano >= 1900 && ano <= 2100)) //verifica se os numeros sao validos
		{
			if ((dia == 29 && mes == 2) && ((ano % 4) == 0)) //verifica se o ano e bissexto
			{
				return true;
			}
			if (dia <= 28 && mes == 2) //verifica o mes de feveireiro
			{
				return true;
			}
			if ((dia <= 30) && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) //verifica os meses de 30 dias
			{
				return true;
			}
			if ((dia <= 31) && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)) //verifica os meses de 31 dias
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}  


	}  


	/** Formata a data no modelo dd/mm/aaaa */
	public static String formataData(String dia, String mes, String ano){

		String data[] = new String[5];

		data[0] = dia;
		data[1] = "/";
		data[2] = mes;
		data[3] = "/";
		data[4] = ano;

		String d = data[0] + data[1] + data[2] + data[3] + data[4];

		return d;

	}

	/**
	 * Converte uma hora do tipo <code>Calendar</code> para String
	 * @param hour Um horário válida do tipo Calendar
	 * @return uma  String com a data convertida.
	 */
	public static String horaToString(Calendar cal){

		String hora = "";

		hora += cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);

		return hora;

	}

	/**
	 * Converter se possível, uma String para float positivo.
	 * 
	 * @param number String valor a ser convertido.
	 * 
	 * @return um float positivo em caso de sucesso, -1 caso contrário.
	 * 
	 * @param number
	 * @return o número convertido para float, se não retorna 1.
	 */
	public static float stringToFloat(String number){

		float n = -1;

		try{

			n = Float.parseFloat(number);

			if(n < 0)
				return -1;

			return n;

		}
		catch(NumberFormatException ex){

			return -1;
		}

	}// stringToFloat()

	/** Este método é responsável por retirar um sepador de uma String.
	 * 
	 * @param string que você deseja remover o seperador.
	 * @param sep o sepador que será removido.
	 * 
	 * @return String com o seperador removido.
	 *  */
	public static String removerSep(String string, String sep){

		
		try{
		
		StringTokenizer token = new StringTokenizer(string, sep);

		while(token.hasMoreTokens())
			return token.nextToken();
		}
		
		catch(NullPointerException e){
			
			return string;
			
		}

		return string;

	}// removeSep()
	
	
	/** Este método é responsável por retirar um sepador total de uma String.
	 * 
	 * @param string que você deseja remover o seperador.
	 * @param sep o sepador que será removido.
	 * 
	 * @return String com o seperador removido.
	 *  */
	public static String removerSepAll(String string, String sep){

		String str = "";
		
		try{
		
		StringTokenizer token = new StringTokenizer(string, sep);
		
		while(token.hasMoreTokens())
			str += token.nextToken();
		}
		
		catch(NullPointerException e){
			
			return "";
			
		}

		return str;

	}// removeSep()


	/**
	 * Converter se possível, uma String para int positivo.
	 * 
	 * @param number String valor a ser convertido.
	 * 
	 * @return um int positivo em caso de sucesso, -1 caso contrário.
	 * 
	 * @param number
	 * @return
	 */
	public static int stringToInt(String number){

		int n = -1;

		try{

			n = Integer.parseInt(number);

			if(n < 0)
				return -1;

			return n;

		}
		catch(NumberFormatException ex){

			return -1;
		}

	}// stringToFloat()
	
	
	/** Verifica se uma <code>String</code> de dígitos decimais é um cpf válido
	 *  @param CPF <code>String</code> com os dígitos do CPF
	 *  @return <code>boolean</code> com <code>true</code> caso a cadeia de caracteres da <code>String</code> 
	 *  passada como parâmetro forme um cpf válido, e <code>false</code> caso contrário
	 */
	public static boolean validarCPF(String CPF) {
		
		// Considera-se erro CPF's formados por uma sequência de números iguais
	    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
	        CPF.equals("22222222222") || CPF.equals("33333333333") ||
	        CPF.equals("44444444444") || CPF.equals("55555555555") ||
	        CPF.equals("66666666666") || CPF.equals("77777777777") ||
	        CPF.equals("88888888888") || CPF.equals("99999999999") ||
	       (CPF.length() != 11))
			return false;

	    try {
			char dig10, dig11;
			int sm = 0, i, r, num, peso = 10;

			// Cálculo do 1º dígito verificador
			for(i = 0; i < 9; i++) {              
			/*
			 *  converte o i-esimo caractere do CPF em um numero:
			 *  por exemplo, transforma o caractere '0' no inteiro 0
			 *  (48 eh a posicao de '0' na tabela ASCII)
			 */    
				num = (int)(CPF.charAt(i) - 48); 
				sm = sm + (num * peso);
				peso = peso - 1;
			}
		
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
			   dig10 = '0';
			else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
			
			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
			  num = (int)(CPF.charAt(i) - 48);
			  sm = sm + (num * peso);
			  peso = peso - 1;
			}
		
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
			   dig11 = '0';
			else dig11 = (char)(r + 48);
			
			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
			   return true;
		  
			return false;
			
		} catch (InputMismatchException erro) {
			return false;
		}
	}

}// Fim da class EntradaESaida
