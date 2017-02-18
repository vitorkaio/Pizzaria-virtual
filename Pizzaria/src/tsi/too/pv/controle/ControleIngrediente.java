package tsi.too.pv.controle;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.too.pv.bd.IngredienteBd;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pizzaria;

/** 
 * Esta classe � formada por um conjunto de fun��es respons�veis por gerenciar as ingredientes.
 * 
 * @author V�tor Caio De Paula
 * @since 1.0
 * 
 *  */
public class ControleIngrediente {

	public ControleIngrediente(){

	}

	/* *********************************** Insere Ingrediente *********************************** */

	/**
	 * Recebe as entradas de dados da GUI, verifica o tratamento correspondente:
	 * 
	 * @param descricao a informa��o sobre o ingrediemte, nome.
	 * @param preco valor em reias do ingrediente.
	 * 
	 * @return true se a inser��o ocorreu com sucesso, false caso contr�rio.
	 * 
	 * */
	public boolean cadIngrediente(String descricao, String preco){

		if(verificaEntradaCamposVazio(descricao, preco) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Os campos Descri��o e pre�o\n n�o podem estar vazios", 
					ConstantesIngrediente.TITULO_INGREDIENTE_INSERIR.valor());

			return false;

		}

		// Verifica se o preco passado � um n�mero real v�lido e positivo
		float num = EntradaESaida.stringToFloat(preco);
		if(num == -1){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Pre�o inv�lido", 
					ConstantesIngrediente.TITULO_INGREDIENTE_INSERIR.valor());

			return false;

		}

		// Verifica se a descric�o do ingrediente j� existe no sistema.
		IngredienteBd ingredienteBd = new IngredienteBd();

		if(ingredienteBd.pesquisaNomeBd(descricao) == true){

			
			EntradaESaida.msgErro("Descri��o j� existente no sistema!", ConstantesIngrediente.TITULO_INGREDIENTE_INSERIR.valor());
			ingredienteBd.fechaConexao();
			return false;

		}

		Ingrediente ingrediente = new Ingrediente(descricao, num);

		if(ingredienteBd.insereBd(ingrediente) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao cadastrar Ingrediente",
					ConstantesIngrediente.TITULO_INGREDIENTE_INSERIR.valor());

			ingredienteBd.fechaConexao();
			return false;

		}

		EntradaESaida.msgInfo("Ingrediente cadastrado", 
				ConstantesIngrediente.TITULO_INGREDIENTE_INSERIR.valor());


		ingredienteBd.fechaConexao();
		return true;

	}// cadIngrediente()

	/* ************************ Verifica Campos Vazios *********************************** */
	/**
	 * Verifica se os campos para ingrediente est�o vazios.
	 * 
	 * @param descricao a informa��o sobre o ingrediente, nome.
	 * @param preco valor em reias do ingrediente.
	 * 
	 * @return true caso todos os campos foram preenchidos, false caso contr�rio.
	 */
	private boolean verificaEntradaCamposVazio(String descricao, String preco){

		if(descricao.length() == 0 ||  preco.length() == 0)
			return false;

		return true;

	}// verificaEntradaCamposVazio()

	/* *********************************** Consulta Ingrediente *********************************** */
	/**
	 * Este m�todo retorna um Ingrediente vindo do banco de dados.
	 * 
	 * @param descricao do Ingrediente que ser� pesquisado.
	 * 
	 * @return Ingrediente se existir no banco, null caso contr�rio.
	 * */
	public Ingrediente conIngrediente(String descricao){

		// Se a descricao passado for null, n�o faz nada.
		if(descricao.equalsIgnoreCase(""))
			return null;

		IngredienteBd ingredienteBd = new IngredienteBd();
		Ingrediente ingrediente = (Ingrediente) ingredienteBd.obtemObjeto(descricao);

		if(ingrediente == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Ingrediente n�o encontrada", 
					ConstantesIngrediente.TITULO_INGREDIENTE_CONSULTA.valor());

			ingredienteBd.fechaConexao();
			return null;

		}

		ingredienteBd.fechaConexao();
		return ingrediente;

	}// conPizz()


	/* *********************************** Listar Ingredientes *********************************** */
	/**
	 * Este m�todo retorna uma lista com todos os ingredientes vindo do banco de dados.
	 * 
	 * @return lista de ingredientes se existir no banco, null caso contr�rio.
	 * */
	public ArrayList<Pizzaria>listaIngrediente(){

		IngredienteBd ingredienteBd = new IngredienteBd();

		ArrayList<Pizzaria>listaIngrediente = ingredienteBd.listarObjetos();

		if(listaIngrediente == null){

			EntradaESaida.msgErro("N�o foi poss�vel listar as Ingredientes", 
					ConstantesIngrediente.TITULO_INGREDIENTE_LISTAR.valor());

			ingredienteBd.fechaConexao();
			return null;

		}

		ingredienteBd.fechaConexao();
		return listaIngrediente;

	}// listaIngredientes()


	/* *********************************** Alterar Ingrediente *********************************** */
	/**
	 * Altera os dados de um Ingrediente.
	 * 
	 * @param descricao(antiga) da Ingrediente que deseja alterar os dados.
	 * @param Ingrediente ingrediente com os novos dados.
	 * 
	 * @return true caso alterou com sucesso, false caso contr�rio.
	 * */
	public boolean altIngrediente(String descricao, Ingrediente ingrediente){

		// Fazendo backup do ingrediente antiga.
		IngredienteBd ingredienteBd = new IngredienteBd();

		// Verifica se o ingrediente ainda exista no banco de dados.
		boolean existe = ingredienteBd.pesquisaNomeBd(descricao);

		if(existe == false){

			
			EntradaESaida.msgErro("Ingrediente Inexistente!", 
					ConstantesIngrediente.TITULO_INGREDIENTE_ALTERAR.valor());
			ingredienteBd.fechaConexao();
			return false;

		}

		// Se for true, quer dizer que o usu�rio apenas quer trocar o valor.
		else if(ingrediente.getDescricao().trim().equalsIgnoreCase(descricao.trim()) == true)
			;

		// Verificando se o nome do novo funcion�rio j� existe no banco de dados.
		else if(ingredienteBd.pesquisaNomeBd(ingrediente.getDescricao()) == true){

			
			EntradaESaida.msgErro("Descri��o j� existente no sistema!", 
					ConstantesIngrediente.TITULO_INGREDIENTE_ALTERAR.valor());
			ingredienteBd.fechaConexao();
			return false;
		}

		// Insere no banco de dados.
		if(ingredienteBd.alterarBd(ingrediente, descricao) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao alterar Ingrediente",
					ConstantesIngrediente.TITULO_INGREDIENTE_ALTERAR.valor());

			ingredienteBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Altera��o feita com sucesso", 
				ConstantesIngrediente.TITULO_INGREDIENTE_ALTERAR.valor());

		ingredienteBd.fechaConexao();
		return true;

	}// Fim altIngrediente


	/* *********************************** Excluir Ingrediente *********************************** */
	/**
	 * Exclui os dados de um Ingrediente.
	 * 
	 * @param nome do Ingrediente que deseja excluir os dados.
	 * 
	 * @return true caso excluiu com sucesso, false caso contr�rio.
	 * */
	public boolean excIngrediente(String nome){
		

		IngredienteBd ingredienteBd = new IngredienteBd();
		
		// Verifica se o ingrediente ainda exista no banco de dados.
		boolean existe = ingredienteBd.pesquisaNomeBd(nome);

		if(existe == false){

			
			EntradaESaida.msgErro("Ingrediente Inexistente!", 
					ConstantesIngrediente.TITULO_INGREDIENTE_EXCLUIR.valor());
			ingredienteBd.fechaConexao();
			return false;

		}

		// Colocar os comandos abaixo dentro do EntradaESaida.java
		EntradaESaida.somPergunta();
		int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?",
				ConstantesIngrediente.TITULO_INGREDIENTE_EXCLUIR.valor(), JOptionPane.YES_NO_OPTION);

		if(op == 1){
			ingredienteBd.fechaConexao();
			return false;
		}

		if(ingredienteBd.removeBd(nome) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("N�o foi poss�vel remover o Ingrediente",
					ConstantesIngrediente.TITULO_INGREDIENTE_EXCLUIR.valor());

			ingredienteBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Ingrediente removido",
				ConstantesIngrediente.TITULO_INGREDIENTE_EXCLUIR.valor());

		ingredienteBd.fechaConexao();
		return true;

	}// excIngrediente()

	/** Fecha a conex�o com o banco de dados e despacha outras coisas. */
	public boolean finaliza(){

		IngredienteBd ingredienteBd = new IngredienteBd();

		if(ingredienteBd.fechaConexao() == false)
			return false;

		return true;

	}// finaliza()

	/* *********************************** Consulta Ingrediente *********************************** */
	/**
	 * Este m�todo retorna um Ingrediente vindo do banco de dados.
	 * 
	 * @param codigo do Ingrediente que ser� pesquisado.
	 * 
	 * @return Ingrediente se existir no banco, null caso contr�rio.
	 * */
	public Ingrediente conIngrediente(int codigo){

		IngredienteBd ingredienteBd = new IngredienteBd();
		Ingrediente ingrediente = (Ingrediente) ingredienteBd.obtemObjeto(codigo);

		if(ingrediente == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Ingrediente n�o encontrada", 
					ConstantesIngrediente.TITULO_INGREDIENTE_CONSULTA.valor());

			ingredienteBd.fechaConexao();
			return null;

		}

		ingredienteBd.fechaConexao();
		return ingrediente;

	}// conPizz()


}// Fim da class ControleIngrediente
