package tsi.too.pv.controle;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.too.pv.bd.FuncionarioBd;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Pizzaria;

/** 
 * Esta classe � formada por um conjunto de fun��es respons�veis por controlar o acesso dos usu�rios ao 
 * sistema da Pv.
 * 
 * @author V�tor Caio De Paula
 * @since 1.0
 * 
 *  */
public class ControleFuncionario {

	public ControleFuncionario(){

	}

	/* *********************************** Insere Funcion�rio *********************************** */

	/**
	 * Recebe as entradas de dados da GUI, verifica o tratamento correspondente:
	 * 
	 * <ul>
	 * 	<li>nomeUsuario: O nome deve possuir no m�nimo 8 e no m�ximo 15 caracteres. Os caracteres permitidos 
	 * s�o: letras, d�gitos e os s�mbolos underscore e ponto.</li>
	 *  <li>senha: A senha deve possuir no m�nimo 6 caracteres.</li>
	 *  <li>tipoUsuario: A - Administrador, C - Colaborador, G - Gerente</li>
	 * </ul>
	 * 
	 * @return true se a inser��o ocorreu com sucesso, false caso contr�rio.
	 * 
	 * */
	public boolean cadFunc(String nome, String senha, String tipo){

		// Verifica se o nome atende aos requisitos.
		// letras, d�gitos e underscore.
		if(verificaEntradaNome(nome) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inv�lido para o nome\n min 8 e max 15 caracteres\n"
					+ "� permitido apenas: letras, d�gitos e underscore", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

			return false;
		}

		// Verifica a senha.
		if(verificaEntradaSenha(senha) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inv�lido para a senha\n min 6 caracteres", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

			return false;

		}

		// Verifica o tipo passado.
		char tipoFinal = verificaEntradaTipo(tipo);
		if(tipoFinal == 'N'){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inv�lido para o tipo\n  A - Administrador, C - Colaborador, "
					+ "G - Gerente", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

			return false;

		}

		FuncionarioBd funcBd = new FuncionarioBd();
		
		// Verificando se o nome do novo funcion�rio j� existe no banco de dados.
		if(funcBd.pesquisaNomeBd(nome) == true){

			
			EntradaESaida.msgErro("Nome j� existente no sistema!", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());
			funcBd.fechaConexao();
			return false;
		}

		Funcionario func = new Funcionario(nome, senha, tipoFinal);

		// Insere no banco de dados.
		if(funcBd.insereBd(func) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao cadastrar funcion�rio",
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

			funcBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Funcion�rio cadastrado", 
				ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

		funcBd.fechaConexao();

		return true;

	}// cadFunc()

	/* ******************** Verifica formata��o da entrada de dados para Funcion�rio ******************** */
	/**
	 * Verifica se o nome possui: min 8 e max 15 caracteres. Tamb�m verifica se apenas possui
	 * letras, di�tos e underscore.
	 * 
	 * @param entrada nome do usu�rio para verifica��o
	 * @return true caso a entrada for v�lida, false caso contr�rio.
	 * */
	private boolean verificaEntradaNome(String entrada){

		if(entrada.length() < 8 || entrada.length() > 15)
			return false;

		return true;

	}// verificaEntrada()

	/** 
	 * Verifica a entrada de dados referente a senha. Deve possui no min 6 caracteres.
	 * 
	 * @param senha que usu�rio forneceu.
	 * @return true caso a entrada for v�lida, false caso contr�rio.
	 * */
	private boolean verificaEntradaSenha(String senha){

		// Verifica se a senha tem apenas espa�os.
		if(senha.trim().equalsIgnoreCase(" ".trim()) == true)
			return false;
		
		senha = senha.trim();
		
		if(senha.length() < 6)
			return false;

		return true;
	}// verificaEntradaSenha()

	/** 
	 * Verifica a entrada de dados referente ao tipo. 
	 * tipoUsuario: A - Administrador, C - Colaborador, G - Gerente.

	 * @param senha Tipo de usu�rio fornecido.
	 * @return um char referente ao tipo passado, N caso contr�rio.
	 * */
	private char verificaEntradaTipo(String tipo){

		if(tipo.length() > 1)
			return 'N';

		if(tipo.equalsIgnoreCase("A") || tipo.equalsIgnoreCase("C") || tipo.equalsIgnoreCase("G"))
			return tipo.charAt(0);

		return 'N';

	}// verificaEntradaSenha()

	/* *********************************** FIM Insere Funcion�rio *********************************** */


	/* *********************************** Consulta Funcion�rio *********************************** */
	/**
	 * Este m�todo retorna um funcion�rio vindo do banco de dados.
	 * 
	 * @param nome do funcion�rio que ser� pesquisado.
	 * 
	 * @return Funcionario se existir no banco, null caso contr�rio.
	 * */
	public Funcionario conFunc(String nome){

		// Se o nome passado for null, n�o faz nada.
		if(nome.equalsIgnoreCase("") || nome.equalsIgnoreCase("root"))
			return null;

		FuncionarioBd funcBd = new FuncionarioBd();
		Funcionario func = (Funcionario) funcBd.obtemObjeto(nome);

		if(func == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Funcion�rio n�o encontrado", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_CONSULTA.valor());

			funcBd.fechaConexao();
			return null;

		}

		funcBd.fechaConexao();
		return func;
	}


	/* *********************************** Listar Funcion�rios *********************************** */
	/**
	 * Este m�todo retorna uma lista com todos os funcion�rios vindo do banco de dados.
	 * 
	 * 
	 * @return lista de funcionarios se existir no banco, null caso contr�rio.
	 * */

	public ArrayList<Pizzaria> listaFuncionarios(){

		FuncionarioBd funcBd = new FuncionarioBd();

		ArrayList<Pizzaria>funcs = funcBd.listarObjetos();

		if(funcs == null){

			EntradaESaida.msgErro("N�o foi poss�vel listar os funcion�rios", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_LISTAR.valor());

			funcBd.fechaConexao();
			return null;

		}

		funcBd.fechaConexao();
		return funcs;

	}// listaFuncionarios()

	/* *********************************** Alterar Funcion�rio *********************************** */
	/**
	 * Altera os dados de um funcion�rio.
	 * 
	 * @param nome(antigo) do funcion�rio que deseja alterar os dados.
	 * @param func funcionario com os novos dados.
	 * 
	 * @return true caso alterou com sucesso, false caso contr�rio.
	 * */
	public boolean altFunc(String nome, Funcionario func){

		if(verificaEntradaNome(func.getNomeUsuario()) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inv�lido para o nome\n min 8 e max 15 caracteres\n"
					+ "� permitido apenas: letras, d�gitos e underscore", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());

			return false;
		}

		// Verifica a senha.
		if(verificaEntradaSenha(func.getSenha()) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inv�lido para a senha\n min 6 caracteres", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());

			return false;

		}


		FuncionarioBd funcBd = new FuncionarioBd();

		// Verifica se o funcion�rio ainda exista no banco de dados.
		boolean existe = funcBd.pesquisaNomeBd(nome);

		if(existe == false){

			
			EntradaESaida.msgErro("Funcion�rio Inexistente!", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());
			funcBd.fechaConexao();
			return false;

		}

		// Se for true, quer dizer que o usu�rio apenas quer trocar a senha.
		else if(func.getNomeUsuario().trim().equalsIgnoreCase(nome.trim()) == true)
			;

		// Verificando se o nome do novo funcion�rio j� existe no banco de dados.
		else if(funcBd.pesquisaNomeBd(func.getNomeUsuario()) == true){

			
			EntradaESaida.msgErro("Nome j� existente no sistema!", ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());
			funcBd.fechaConexao();
			return false;
		}

		// Insere no banco de dados.
		if(funcBd.alterarBd(func, nome) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao alterar funcion�rio",
					ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());

			funcBd.fechaConexao();
			return false;
		}

		funcBd.fechaConexao();
		return true;

	}// altFunc()


	/* *********************************** Excluir Funcion�rio *********************************** */
	/**
	 * Exclui os dados de um funcion�rio.
	 * 
	 * @param nome do funcion�rio que deseja excluir os dados.
	 * 
	 * @return true caso excluiu com sucesso, false caso contr�rio.
	 * */
	public boolean excFunc(String nome){

		// Faz uma �ltima verifica��o, para ver se o usu�rio ainda consta no banco de dados.
		FuncionarioBd funcBd = new FuncionarioBd();

		if(funcBd.pesquisaNomeBd(nome) == false){

			
			EntradaESaida.msgErro("Funcion�rio inexistente!", ConstantesFuncionarios.TITULO_FUNCIONARIO_EXCLUIR.valor());

			funcBd.fechaConexao();
			return false;

		}

		// Colocar os comandos abaixo dentro do EntradaESaida.java
		EntradaESaida.somPergunta();
		int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?",
				ConstantesFuncionarios.TITULO_FUNCIONARIO_EXCLUIR.valor(), JOptionPane.YES_NO_OPTION);

		if(op == 1){
			funcBd.fechaConexao();
			return false;
		}
		

		if(funcBd.removeBd(nome) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("N�o foi poss�vel remover o funcion�rio",
					ConstantesFuncionarios.TITULO_FUNCIONARIO_EXCLUIR.valor());

			funcBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Funcion�rio removido",
				ConstantesFuncionarios.TITULO_FUNCIONARIO_EXCLUIR.valor());

		funcBd.fechaConexao();
		return true;

	}// excFunc()

}// Fim da classe ControleFuncionario
