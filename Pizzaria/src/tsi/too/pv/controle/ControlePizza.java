package tsi.too.pv.controle;

import java.util.ArrayList;

import tsi.too.pv.bd.CompozicaoPizzaBd;
import tsi.too.pv.bd.PizzaBd;
import tsi.too.pv.bd.PrecoPizzaBd;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.CompozicaoPizza;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pizza;
import tsi.too.pv.tipos.Pizzaria;

/** 
 * Esta classe � formada por um conjunto de fun��es respons�veis por gerenciar as pizzas.
 * 
 * @author V�tor Caio De Paula
 * @since 1.0
 * 
 *  */
public class ControlePizza {

	public ControlePizza(){

	}

	/* *********************************** Insere Pizza *********************************** */

	/**
	 * Recebe as entradas de dados da GUI, verifica o tratamento correspondente:
	 * 
	 * @param descricao a informa��o sobre a pizza, nome.
	 * @param tamanho indica o di�metro da pizza. P = Pequena(20 cm), M = M�dia(30 cm) e G = Grande(40 cm).
	 * @param data em que o pre�o foi estipulado para pizza. 
	 * @param preco valor em reias da pizza.
	 * 
	 * @return true se a inser��o ocorreu com sucesso, false caso contr�rio.
	 * 
	 * */
	public boolean cadPizza(String descricao, String tamanho, String data, String preco){

		if(verificaEntradaCamposVazio(descricao, tamanho, data, preco) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Os campos Descri��o e pre�o\n n�o podem estar vazios", 
					ConstantesPizzas.TITULO_PIZZA_INSERIR.valor());

			return false;

		}

		// Verifica se o preco passado � um n�mero real v�lido e positivo
		float num = EntradaESaida.stringToFloat(preco);
		if(num == -1){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Pre�o inv�lido", 
					ConstantesPizzas.TITULO_PIZZA_INSERIR.valor());

			return false;

		}

		// Verifica se a descri��o da pizza j� existe.
		PizzaBd pizzaBd = new PizzaBd();

		if(pizzaBd.pesquisaNomeBd(descricao.toLowerCase().trim() + "-" + tamanho.charAt(0)) 
				== true){

			
			EntradaESaida.msgErro("Descri��o j� existente no sistema!", 
					ConstantesPizzas.TITULO_PIZZA_INSERIR.valor());
			pizzaBd.fechaConexao();
			return false;

		}

		Pizza pi = new Pizza(descricao, tamanho.charAt(0), EntradaESaida.StringToData(data), num);

		if(pizzaBd.insereBd(pi) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao cadastrar Pizza",
					ConstantesPizzas.TITULO_PIZZA_INSERIR.valor());

			pizzaBd.fechaConexao();
			return false;

		}

		// Pega o c�digo da �ltima pizza cadastrada. 
		int cod = pizzaBd.ultimoCodigo();

		Pizza pi2 = new Pizza();
		pi2.setCodigo(cod);
		pi2.setData(pi.getData());
		pi2.setTamanho(pi.getTamanho());
		pi2.setPreco(pi.getPreco());

		// Inserir dados da na tabela PrecoPizza.
		pizzaBd.fechaConexao();
		PrecoPizzaBd precoPizzaBd = new PrecoPizzaBd();

		if(precoPizzaBd.insereBd(pi2) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao cadastrar Pre�o pizza",
					ConstantesPizzas.TITULO_PIZZA_INSERIR.valor());

			precoPizzaBd.fechaConexao();
			return false;

		}

		EntradaESaida.msgInfo("Pizza cadastrada", 
				ConstantesClientes.TITULO_CLIENTE_INSERIR.valor());


		precoPizzaBd.fechaConexao();
		return true;

	}// cadPizza()


	/* ************************ Verifica Campos Vazios *********************************** */
	/**
	 * Verifica se os campos para pizza est�o vazios.
	 * 
	 * @param descricao a informa��o sobre a pizza, nome.
	 * @param tamanho indica o di�metro da pizza. P = Pequena(20 cm), M = M�dia(30 cm) e G = Grande(40 cm).
	 * @param data em que o pre�o foi estipulado para pizza. 
	 * @param preco valor em reias da pizza.
	 * 
	 * @return true caso todos os campos foram preenchidos, false caso contr�rio.
	 */
	private boolean verificaEntradaCamposVazio(String descricao, String tamanho, String data, String preco){

		if(descricao.length() == 0 || tamanho.length() == 0 || data.length() == 0 || preco.length() == 0)
			return false;

		return true;

	}// verificaEntradaCamposVazio()

	/* *********************************** Consulta Pizza *********************************** */
	/**
	 * Este m�todo retorna um Pizza vindo do banco de dados.
	 * 
	 * @param descricao do Pizza que ser� pesquisado.
	 * 
	 * @return Pizza se existir no banco, null caso contr�rio.
	 * */
	public Pizza conPizza(String descricao){

		// Se a descricao passado for null, n�o faz nada.
		if(descricao.equalsIgnoreCase(""))
			return null;

		PizzaBd pizzaBd = new PizzaBd();
		Pizza pi = (Pizza) pizzaBd.obtemObjeto(descricao.toLowerCase().trim());

		if(pi == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Pizza n�o encontrada", 
					ConstantesPizzas.TITULO_PIZZA_CONSULTA.valor());

			pizzaBd.fechaConexao();
			return null;

		}


		// Pega os restos das infroma��es sobre a pizza na tabela PrecoPizza.
		pizzaBd.fechaConexao();
		PrecoPizzaBd precoPizzaBd = new PrecoPizzaBd();
		Pizza tmp = (Pizza) precoPizzaBd.obtemObjeto(pi.getCodigo());

		if(tmp == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Pizza n�o encontrada", 
					ConstantesPizzas.TITULO_PIZZA_CONSULTA.valor());

			precoPizzaBd.fechaConexao();
			return null;

		}

		// Converge os valores dos objetos.
		pi.setTamanho(tmp.getTamanho());
		pi.setData(tmp.getData());
		pi.setPreco(tmp.getPreco());

		precoPizzaBd.fechaConexao();
		return pi;

	}// conPizz()

	/**
	 * Este m�todo retorna um Pizza vindo do banco de dados.
	 * 
	 * @param codigo da Pizza que ser� pesquisado.
	 * 
	 * @return Pizza se existir no banco, null caso contr�rio.
	 * */
	public Pizza conPizzaCodigo(int codigo){

		PizzaBd pizzaBd = new PizzaBd();
		Pizza pi = (Pizza) pizzaBd.obtemObjeto(codigo);

		if(pi == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Pizza n�o encontrada", 
					ConstantesPizzas.TITULO_PIZZA_CONSULTA.valor());

			pizzaBd.fechaConexao();
			return null;

		}


		// Pega os restos das infroma��es sobre a pizza na tabela PrecoPizza.
		pizzaBd.fechaConexao();
		PrecoPizzaBd precoPizzaBd = new PrecoPizzaBd();
		Pizza tmp = (Pizza) precoPizzaBd.obtemObjeto(pi.getCodigo());

		if(tmp == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Pizza n�o encontrada", 
					ConstantesPizzas.TITULO_PIZZA_CONSULTA.valor());

			precoPizzaBd.fechaConexao();
			return null;

		}

		// Converge os valores dos objetos.
		pi.setTamanho(tmp.getTamanho());
		pi.setData(tmp.getData());
		pi.setPreco(tmp.getPreco());

		precoPizzaBd.fechaConexao();
		return pi;

	}// conPizz()


	/* *********************************** Listar Pizzas *********************************** */
	/**
	 * Este m�todo retorna uma lista com todos as Pizzas vindo do banco de dados.
	 * 
	 * @return lista de pizzas se existir no banco, null caso contr�rio.
	 * */
	public ArrayList<Pizzaria>listaPizza(){

		PizzaBd pizzaBd = new PizzaBd();

		ArrayList<Pizzaria>listaPizza = pizzaBd.listarObjetos();

		pizzaBd.fechaConexao();

		if(listaPizza == null){

			EntradaESaida.msgErro("N�o foi poss�vel listar as Pizzas", 
					ConstantesPizzas.TITULO_PIZZA_LISTAR.valor());


			return null;

		}

		// Pega o resto das informa��es sobre a pizza na tabela PrecoPizza.
		PrecoPizzaBd precoPizzaBd = new PrecoPizzaBd();

		for(int cont = 0; cont < listaPizza.size(); cont++){
			//asdsddsa
			Pizza tmp = (Pizza) precoPizzaBd.obtemObjeto(((Pizza) listaPizza.get(cont)).getCodigo());

			// Verifica se conseguiu listar.
			if(tmp == null){

				EntradaESaida.msgErro("N�o foi poss�vel listar as Pizzas", 
						ConstantesPizzas.TITULO_PIZZA_LISTAR.valor());

				precoPizzaBd.fechaConexao();
				return null;

			}

			// Convergindo os valores
			((Pizza) listaPizza.get(cont)).setTamanho(tmp.getTamanho());
			((Pizza) listaPizza.get(cont)).setData(tmp.getData());
			((Pizza) listaPizza.get(cont)).setPreco(tmp.getPreco());

		}

		precoPizzaBd.fechaConexao();
		return listaPizza;

	}// listaPizzas()


	/* *********************************** Listar Descri��es pizzas *********************************** */
	/**
	 * Este m�todo retorna uma lista com todos as descri��es da Pizzas 
	 * referentes ao tamanho passado, vindo do banco de dados.
	 * 
	 * @param tamanho das pizzas que ser�o listadas.
	 * 
	 * @return lista de pizzas se existir no banco, null caso contr�rio.
	 * */
	public ArrayList<String> listaDescricaoPizza(char tamanho){

		PizzaBd pizzaBd = new PizzaBd();
		ArrayList<String>lista = pizzaBd.listaDescricaoPizza();

		ArrayList<String>listaFiltrada = new ArrayList<>();

		for(int contador = 0; contador < lista.size(); contador++){

			Pizza pizza = conPizza(lista.get(contador));

			// Se a pizza for personalizada.
			if(pizza.getDescricao().startsWith("personalizada"))
				;
			
			// Se a pizza n�o possuir ingredientes ela n�o � listada.
			else if(listaIngredientesPizza(pizza.getDescricao()) == null)
				continue;

			if(pizza.getTamanho() == tamanho)
				listaFiltrada.add(EntradaESaida.removerSep(lista.get(contador),"-"));

		}

		pizzaBd.fechaConexao();

		return listaFiltrada;


	}// listaDescricaoPizza()

	/* *********************************** Alterar Pizza *********************************** */
	/**
	 * Altera os dados de uma Pizza.
	 * 
	 * @param descricao(antiga) da Pizza que deseja alterar os dados.
	 * @param Pizza pizza com os novos dados.
	 * 
	 * @return true caso alterou com sucesso, false caso contr�rio.
	 * */
	public boolean altPizza(String descricao, Pizza pizza){

		// N�o deixa modificar pizza personalizada.
		if(descricao.startsWith("personalizada") == true)
			return false;

		PizzaBd pizzaBd = new PizzaBd();

		// Verifica se o funcion�rio ainda exista no banco de dados.
		boolean existe = pizzaBd.pesquisaNomeBd(descricao);
		String novaDescricao = pizza.getDescricao().toLowerCase().trim()  + "-" + pizza.getTamanho();

		if(existe == false){

			
			EntradaESaida.msgErro("Pizza Inexistente!", 
					ConstantesPizzas.TITULO_PIZZA_ALTERAR.valor());
			pizzaBd.fechaConexao();
			return false;

		}

		// Se for true, quer dizer que o usu�rio apenas quer trocar a senha.
		else if(novaDescricao.equalsIgnoreCase(descricao.trim()) == true)
			;

		// Verificando se o nome do novo funcion�rio j� existe no banco de dados.
		else if(pizzaBd.pesquisaNomeBd(novaDescricao) == true){

			
			EntradaESaida.msgErro("Descri��o j� existente no sistema!", 
					ConstantesPizzas.TITULO_PIZZA_ALTERAR.valor());
			pizzaBd.fechaConexao();
			return false;
		}

		pizzaBd.fechaConexao();

		Pizza backupPizza = conPizza(descricao);

		if(backupPizza == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao alterar Pizza",
					ConstantesPizzas.TITULO_PIZZA_ALTERAR.valor());

			return false;

		}

		pizza.setCodigo(backupPizza.getCodigo());

		pizzaBd = new PizzaBd();
		PrecoPizzaBd precoPizzaBd = new PrecoPizzaBd();

		if(pizzaBd.alterarBd(pizza, descricao) == false || 
				precoPizzaBd.alterarBd(pizza, pizza.getCodigo()) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao alterar Pizza",
					ConstantesPizzas.TITULO_PIZZA_ALTERAR.valor());


			pizzaBd.fechaConexao();
			precoPizzaBd.fechaConexao();

			return false;

		}

		pizzaBd.fechaConexao();
		precoPizzaBd.fechaConexao();

		return true;

	}// Fim altPizza

	/* *********************************** Excluir Pizza *********************************** */
	/**
	 * Exclui os dados de um Pizza.
	 * 
	 * @param nome do Pizza que deseja excluir os dados.
	 * 
	 * @return true caso excluiu com sucesso, false caso contr�rio.
	 * */
	public boolean excPizza(String nome){

		PizzaBd pizzaBd = new PizzaBd();

		// Verifica se a pizza ainda exista no banco de dados.
		boolean existe = pizzaBd.pesquisaNomeBd(nome);

		if(existe == false){

			
			EntradaESaida.msgErro("Pizza Inexistente!", 
					ConstantesPizzas.TITULO_PIZZA_EXCLUIR.valor());
			pizzaBd.fechaConexao();
			return false;

		}

		// N�o deixa modificar pizza personalizada.
		if(nome.startsWith("personalizada") == true)
			return false;

		// Colocar os comandos abaixo dentro do EntradaESaida.java
		EntradaESaida.somPergunta();
		int op = EntradaESaida.msgPergunta("Tem certeza que deseja excluir", 
				ConstantesPizzas.TITULO_PIZZA_EXCLUIR.valor());

		if(op == 1){
			pizzaBd.fechaConexao();
			return false;
		}

		if(pizzaBd.removeBd(nome) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("N�o foi poss�vel remover o Pizza",
					ConstantesPizzas.TITULO_PIZZA_EXCLUIR.valor());

			pizzaBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Pizza removido",
				ConstantesPizzas.TITULO_PIZZA_EXCLUIR.valor());

		pizzaBd.fechaConexao();
		return true;

	}// excPizza()

	/** Fecha a conex�o com o banco de dados e despacha outras coisas. */
	public boolean finaliza(){

		PizzaBd pizza = new PizzaBd();

		if(pizza.fechaConexao() == false)
			return false;

		return true;

	}// finaliza()

	/* *********************************** Excluir Pizza *********************************** */
	/**
	 * Exclui os dados de um Pizza.
	 * 
	 * @param nome do Pizza que deseja excluir os dados.
	 * 
	 * @return true caso excluiu com sucesso, false caso contr�rio.
	 * */
	public boolean excPizzasPersonalizadas(String nome){

		// N�o deixa modificar pizza personalizada.
		if(nome.startsWith("personalizada") == true)
			return false;


		PizzaBd pizzaBd = new PizzaBd();

		if(pizzaBd.removeBd(nome) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("N�o foi poss�vel remover a Pizza",
					ConstantesPizzas.TITULO_PIZZA_EXCLUIR.valor());

			pizzaBd.fechaConexao();
			return false;
		}

		pizzaBd.fechaConexao();
		return true;

	}// excPizza()

	/** Adiciona pizza e sua compozi��o.
	 * 
	 *  @param codigo da pizza personalizada.
	 *  @param ArrayList com os ingredientes.
	 *  
	 *  @return true em caso de sucesso, false caso contr�rio.
	 *  
	 *  */
	public boolean cadastraPizzaPersonaliza(int codigoPizza, ArrayList<Ingrediente>listaIngrediente){

		ArrayList<CompozicaoPizza>listaCompozicaoPizza = new ArrayList<>();

		// Seta as informa��es em um array de compozi��o.
		for(int contador = 0; contador < listaIngrediente.size(); contador++)
			listaCompozicaoPizza.add(new CompozicaoPizza(codigoPizza, 
					listaIngrediente.get(contador).getCodigo()));

		CompozicaoPizzaBd compozicaoBd = new CompozicaoPizzaBd();

		for(int contador = 0; contador < listaCompozicaoPizza.size(); contador++){

			if(compozicaoBd.insereBd(listaCompozicaoPizza.get(contador)) == false){

				
				EntradaESaida.msgErro("N�o foi poss�vel gravar compoiza��o pizza", 
						"Cadastrar Pizza Personalizada");

				compozicaoBd.fechaConexao();
				return false;

			}
		}

		compozicaoBd.fechaConexao();
		return true;

	}// cadastraPizzaPersonaliza()

	/** Retorna uma lista com todas compozicoes da tabela */
	public ArrayList<Pizzaria>listaCompozicao(){

		CompozicaoPizzaBd compozicao = new CompozicaoPizzaBd();
		ArrayList<Pizzaria> lista = compozicao.listarObjetos();

		return lista;

	}// listaCompozicao()

	/** Retorna todos os objetos contidos na tabela de acordo com o c�digo passado compozicaoPizza e retorna.
	 * 
	 *  @param codigo da pizza que ser� pesquisado.
	 *  
	 *  @return ArrayList de compozicaoPizza.
	 *  
	 *  */
	public ArrayList<CompozicaoPizza>listaCompozicaoPizza(int codigo){

		CompozicaoPizzaBd compozicao = new CompozicaoPizzaBd();
		ArrayList<CompozicaoPizza> lista = compozicao.obterCompozicaoPizzaBd(codigo);

		compozicao.fechaConexao();

		return lista;

	}// listaCompozicaoPizza()

	/** Retorna uma lista de ingredientes que comp�em a pizza informada.
	 * 
	 *  @param descricao da pizza que ter� seus ingredientes verificados.
	 *  
	 *  @return Lista com todos os ingredientes formados pela pizza, em caso de falha, null.
	 *  */
	public ArrayList<Ingrediente>listaIngredientesPizza(String descricao){

		ControlePizza controlePizza = new ControlePizza();
		// Pega as informa��es da pizza na tabela Pizza.
		Pizza pizza = controlePizza.conPizza(descricao);

		if(pizza == null)
			return null;

		ArrayList<CompozicaoPizza>listaCompozicao = listaCompozicaoPizza(pizza.getCodigo());

		if(listaCompozicao == null || listaCompozicao.size() == 0)
			return null;

		ArrayList<Ingrediente>listaIngrediente = new ArrayList<>();
		ControleIngrediente controleIngrediente = new ControleIngrediente();

		// Percorre o array de listaCompozicao, pegando c�digo dos ingredientes e o retornando.
		for(int contador = 0; contador < listaCompozicao.size(); contador++){

			int codigoIngrediente = listaCompozicao.get(contador).getCodigoIngrediente();

			Ingrediente ingrediente = controleIngrediente.conIngrediente(codigoIngrediente);

			if(ingrediente == null)
				return null;

			listaIngrediente.add(ingrediente);

		}

		return listaIngrediente;

	}// listaIngredientesPizza()

	/** Altera os ingredientes relacionados algima pizza. */
	public boolean alteraCompozicaoPizza(int codigo, Ingrediente ingrediente){

		CompozicaoPizza compozicao = new CompozicaoPizza(codigo, ingrediente.getCodigo());
		CompozicaoPizzaBd compozicaoBd = new CompozicaoPizzaBd();

		if(compozicaoBd.alterarBd(compozicao, codigo) == false){

			
			EntradaESaida.msgErro("N�o foi poss�vel alterar a compozi��o", "Alterar Pizza");

			compozicaoBd.fechaConexao();
			return false;

		}

		compozicaoBd.fechaConexao();
		return true;

	}// alteraCompozicaoPizza()


	/** Remove uma compozi��oDeUmaPizza. */
	public boolean removeCompozicao(int codigo){

		CompozicaoPizzaBd compozicaoBd = new CompozicaoPizzaBd();

		if(compozicaoBd.removeBd(codigo) == false){

			compozicaoBd.fechaConexao();
			return false;

		}

		return true;

	}// removeCompozicao()

}// Fim ControlePizza.
