package tsi.too.pv.controle;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.too.pv.bd.FuncionarioBd;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Pizzaria;

/** 
 * Esta classe é formada por um conjunto de funções responsáveis por controlar o acesso dos usuários ao 
 * sistema da Pv.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * 
 *  */
public class ControleFuncionario {

	public ControleFuncionario(){

	}

	/* *********************************** Insere Funcionário *********************************** */

	/**
	 * Recebe as entradas de dados da GUI, verifica o tratamento correspondente:
	 * 
	 * <ul>
	 * 	<li>nomeUsuario: O nome deve possuir no mínimo 8 e no máximo 15 caracteres. Os caracteres permitidos 
	 * são: letras, dígitos e os símbolos underscore e ponto.</li>
	 *  <li>senha: A senha deve possuir no mínimo 6 caracteres.</li>
	 *  <li>tipoUsuario: A - Administrador, C - Colaborador, G - Gerente</li>
	 * </ul>
	 * 
	 * @return true se a inserção ocorreu com sucesso, false caso contrário.
	 * 
	 * */
	public boolean cadFunc(String nome, String senha, String tipo){

		// Verifica se o nome atende aos requisitos.
		// letras, dígitos e underscore.
		if(verificaEntradaNome(nome) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inválido para o nome\n min 8 e max 15 caracteres\n"
					+ "É permitido apenas: letras, dígitos e underscore", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

			return false;
		}

		// Verifica a senha.
		if(verificaEntradaSenha(senha) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inválido para a senha\n min 6 caracteres", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

			return false;

		}

		// Verifica o tipo passado.
		char tipoFinal = verificaEntradaTipo(tipo);
		if(tipoFinal == 'N'){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inválido para o tipo\n  A - Administrador, C - Colaborador, "
					+ "G - Gerente", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

			return false;

		}

		FuncionarioBd funcBd = new FuncionarioBd();
		
		// Verificando se o nome do novo funcionário já existe no banco de dados.
		if(funcBd.pesquisaNomeBd(nome) == true){

			
			EntradaESaida.msgErro("Nome já existente no sistema!", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());
			funcBd.fechaConexao();
			return false;
		}

		Funcionario func = new Funcionario(nome, senha, tipoFinal);

		// Insere no banco de dados.
		if(funcBd.insereBd(func) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao cadastrar funcionário",
					ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

			funcBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Funcionário cadastrado", 
				ConstantesFuncionarios.TITULO_FUNCIONARIO_INSERIR.valor());

		funcBd.fechaConexao();

		return true;

	}// cadFunc()

	/* ******************** Verifica formatação da entrada de dados para Funcionário ******************** */
	/**
	 * Verifica se o nome possui: min 8 e max 15 caracteres. Também verifica se apenas possui
	 * letras, diítos e underscore.
	 * 
	 * @param entrada nome do usuário para verificação
	 * @return true caso a entrada for válida, false caso contrário.
	 * */
	private boolean verificaEntradaNome(String entrada){

		if(entrada.length() < 8 || entrada.length() > 15)
			return false;

		return true;

	}// verificaEntrada()

	/** 
	 * Verifica a entrada de dados referente a senha. Deve possui no min 6 caracteres.
	 * 
	 * @param senha que usuário forneceu.
	 * @return true caso a entrada for válida, false caso contrário.
	 * */
	private boolean verificaEntradaSenha(String senha){

		// Verifica se a senha tem apenas espaços.
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

	 * @param senha Tipo de usuário fornecido.
	 * @return um char referente ao tipo passado, N caso contrário.
	 * */
	private char verificaEntradaTipo(String tipo){

		if(tipo.length() > 1)
			return 'N';

		if(tipo.equalsIgnoreCase("A") || tipo.equalsIgnoreCase("C") || tipo.equalsIgnoreCase("G"))
			return tipo.charAt(0);

		return 'N';

	}// verificaEntradaSenha()

	/* *********************************** FIM Insere Funcionário *********************************** */


	/* *********************************** Consulta Funcionário *********************************** */
	/**
	 * Este método retorna um funcionário vindo do banco de dados.
	 * 
	 * @param nome do funcionário que será pesquisado.
	 * 
	 * @return Funcionario se existir no banco, null caso contrário.
	 * */
	public Funcionario conFunc(String nome){

		// Se o nome passado for null, não faz nada.
		if(nome.equalsIgnoreCase("") || nome.equalsIgnoreCase("root"))
			return null;

		FuncionarioBd funcBd = new FuncionarioBd();
		Funcionario func = (Funcionario) funcBd.obtemObjeto(nome);

		if(func == null){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Funcionário não encontrado", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_CONSULTA.valor());

			funcBd.fechaConexao();
			return null;

		}

		funcBd.fechaConexao();
		return func;
	}


	/* *********************************** Listar Funcionários *********************************** */
	/**
	 * Este método retorna uma lista com todos os funcionários vindo do banco de dados.
	 * 
	 * 
	 * @return lista de funcionarios se existir no banco, null caso contrário.
	 * */

	public ArrayList<Pizzaria> listaFuncionarios(){

		FuncionarioBd funcBd = new FuncionarioBd();

		ArrayList<Pizzaria>funcs = funcBd.listarObjetos();

		if(funcs == null){

			EntradaESaida.msgErro("Não foi possível listar os funcionários", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_LISTAR.valor());

			funcBd.fechaConexao();
			return null;

		}

		funcBd.fechaConexao();
		return funcs;

	}// listaFuncionarios()

	/* *********************************** Alterar Funcionário *********************************** */
	/**
	 * Altera os dados de um funcionário.
	 * 
	 * @param nome(antigo) do funcionário que deseja alterar os dados.
	 * @param func funcionario com os novos dados.
	 * 
	 * @return true caso alterou com sucesso, false caso contrário.
	 * */
	public boolean altFunc(String nome, Funcionario func){

		if(verificaEntradaNome(func.getNomeUsuario()) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inválido para o nome\n min 8 e max 15 caracteres\n"
					+ "É permitido apenas: letras, dígitos e underscore", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());

			return false;
		}

		// Verifica a senha.
		if(verificaEntradaSenha(func.getSenha()) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Valor inválido para a senha\n min 6 caracteres", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());

			return false;

		}


		FuncionarioBd funcBd = new FuncionarioBd();

		// Verifica se o funcionário ainda exista no banco de dados.
		boolean existe = funcBd.pesquisaNomeBd(nome);

		if(existe == false){

			
			EntradaESaida.msgErro("Funcionário Inexistente!", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());
			funcBd.fechaConexao();
			return false;

		}

		// Se for true, quer dizer que o usuário apenas quer trocar a senha.
		else if(func.getNomeUsuario().trim().equalsIgnoreCase(nome.trim()) == true)
			;

		// Verificando se o nome do novo funcionário já existe no banco de dados.
		else if(funcBd.pesquisaNomeBd(func.getNomeUsuario()) == true){

			
			EntradaESaida.msgErro("Nome já existente no sistema!", ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());
			funcBd.fechaConexao();
			return false;
		}

		// Insere no banco de dados.
		if(funcBd.alterarBd(func, nome) == false){

			// Executa o aviso sonoro de erro.
			

			EntradaESaida.msgErro("Erro ao alterar funcionário",
					ConstantesFuncionarios.TITULO_FUNCIONARIO_ALTERAR.valor());

			funcBd.fechaConexao();
			return false;
		}

		funcBd.fechaConexao();
		return true;

	}// altFunc()


	/* *********************************** Excluir Funcionário *********************************** */
	/**
	 * Exclui os dados de um funcionário.
	 * 
	 * @param nome do funcionário que deseja excluir os dados.
	 * 
	 * @return true caso excluiu com sucesso, false caso contrário.
	 * */
	public boolean excFunc(String nome){

		// Faz uma última verificação, para ver se o usuário ainda consta no banco de dados.
		FuncionarioBd funcBd = new FuncionarioBd();

		if(funcBd.pesquisaNomeBd(nome) == false){

			
			EntradaESaida.msgErro("Funcionário inexistente!", ConstantesFuncionarios.TITULO_FUNCIONARIO_EXCLUIR.valor());

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
			

			EntradaESaida.msgErro("Não foi possível remover o funcionário",
					ConstantesFuncionarios.TITULO_FUNCIONARIO_EXCLUIR.valor());

			funcBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Funcionário removido",
				ConstantesFuncionarios.TITULO_FUNCIONARIO_EXCLUIR.valor());

		funcBd.fechaConexao();
		return true;

	}// excFunc()

}// Fim da classe ControleFuncionario
