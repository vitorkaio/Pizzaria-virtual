package tsi.too.pv.controle;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.too.pv.bd.ClienteBd;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Cliente;
import tsi.too.pv.tipos.Pizzaria;

/** 
 * Esta classe é formada por um conjunto de funções responsáveis por controlar o acesso dos usuários ao 
 * sistema da Pv.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * 
 *  */
public class ControleCliente {

	public ControleCliente(){

	}

	/* *********************************** Insere Cliente *********************************** */

	/**
	 * Insere um novo cliente no sistema.
	 * 
	 * @param cpf - uma <i>String</i> contendo o cpf do cliente.
	 * @param nome - uma <i>String</i> que representa o nome do cliente.
	 * 
	 * @return <b>true</b> se a inserção ocorreu com sucesso, <b>false</b> caso contrário.
	 * 
	 * */
	public boolean cadCliente(String cpf, String nome, String log, String cidade, String bairro, String number, 
			String comple, String tel, String cel){

		// Verifica se o cpf atende aos requisitos.
		if(verificaEntradaCpf(cpf) == false){

			EntradaESaida.msgErro("Cpf inválido", 
					ConstantesClientes.TITULO_CLIENTE_INSERIR.valor());

			return false;
		}

		// Verifica se os campos Nome, Logradouro, Complemento, Bairro e Cidade estão vazios.
		if(verificaEntradaCamposVazio(nome, log, comple, bairro, cidade) == false){

			EntradaESaida.msgErro("Os campos Nome, Logradouro, Complemento, Bairro e Cidade\n não podem estar vazios", 
					ConstantesClientes.TITULO_CLIENTE_INSERIR.valor());

			return false;

		}

		// Verifica a integridade do numero.
		int numero = 0;
		try{
			numero = Integer.parseInt(number);
		}

		catch(NumberFormatException ex){


			EntradaESaida.msgErro("Número inválido", 
					ConstantesClientes.TITULO_CLIENTE_INSERIR.valor());

			return false;

		}

		ClienteBd clienteBd = new ClienteBd();

		// Verifica se o cpf escolhido já existe no sistema.
		if(clienteBd.pesquisaCpfBd(cpf) == true){

			EntradaESaida.msgErro("Cpf já existente no sistema!", 
					ConstantesClientes.TITULO_CLIENTE_INSERIR.valor());
			clienteBd.fechaConexao();
			return false;

		}

		Cliente cliente = new Cliente(cpf, nome, log, comple, bairro, cidade, tel, cel, numero);

		// Insere no banco de dados.
		if(clienteBd.insereBd(cliente) == false){

			EntradaESaida.msgErro("Erro ao cadastrar Cliente",
					ConstantesClientes.TITULO_CLIENTE_INSERIR.valor());

			clienteBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Cliente cadastrado", 
				ConstantesClientes.TITULO_CLIENTE_INSERIR.valor());


		clienteBd.fechaConexao();
		return true;
	}

	/* ******************** Verifica formatação da entrada de dados para Cliente ******************** */
	/**
	 * Verifica se o cpf atende aos requisitos
	 * 
	 * @param entrada cpf do cliente para verificação
	 * @return true caso a entrada for válida, false caso contrário.
	 * */
	private boolean verificaEntradaCpf(String entrada){

		String semSep = EntradaESaida.removerSepAll(entrada, ".-");

		if(EntradaESaida.validarCPF(semSep) == false)
			return false;

		return true;

	}// verificaEntrada()

	/** 
	 * Verifica se os campos nome, logradouro, complemento, bairro e cidade estão vazios)
	 * 
	 * @param nome, logradouro, complemento, bairro e cidade estão vazios.
	 * @return true caso a entrada for válida, false caso contrário.
	 * */
	private boolean verificaEntradaCamposVazio(String nome, String log, String comple, String bairro, String cidade){

		if(nome.length() == 0 || log.length() == 0 || comple.length() == 0 || bairro.length() == 0 || cidade.length() == 0)
			return false;

		if(nome.trim().equalsIgnoreCase("") == true 
				|| log.trim().equalsIgnoreCase("") == true 
				|| comple.trim().equalsIgnoreCase("") == true 
				|| bairro.trim().equalsIgnoreCase("") == true || cidade.trim().equalsIgnoreCase("") == true)
			return false;

		return true;
	}// verificaEntradaSenha()


	/* *********************************** FIM Insere Cliente *********************************** */


	/* *********************************** Consulta Cliente *********************************** */
	/**
	 * Este método retorna um Cliente vindo do banco de dados.
	 * 
	 * @param cpf do Cliente que será pesquisado.
	 * 
	 * @return Cliente se existir no banco, null caso contrário.
	 * */
	public Cliente conCliente(String cpf){

		// Se o cpf passado for null, não faz nada.
		if(cpf.equalsIgnoreCase(""))
			return null;

		ClienteBd clienteBd = new ClienteBd();
		Cliente cliente = (Cliente) clienteBd.obtemObjeto(cpf);

		if(cliente == null){

			EntradaESaida.msgErro("Cliente não encontrado", 
					ConstantesClientes.TITULO_CLIENTE_CONSULTA.valor());

			clienteBd.fechaConexao();
			return null;

		}

		clienteBd.fechaConexao();
		return cliente;
	}// conCliente()

	/**
	 * Este método retorna um Cliente vindo do banco de dados.
	 * 
	 * @param nome do Cliente que será pesquisado.
	 * 
	 * @return Cliente se existir no banco, null caso contrário.
	 * */
	public Cliente conClienteNome(String nome){

		// Se o cpf passado for null, não faz nada.
		if(nome.equalsIgnoreCase(""))
			return null;

		ClienteBd clienteBd = new ClienteBd();
		Cliente cliente = clienteBd.obterClienteNomeBd(nome);

		if(cliente == null){

			EntradaESaida.msgErro("Cliente não encontrado", 
					ConstantesClientes.TITULO_CLIENTE_CONSULTA.valor());

			clienteBd.fechaConexao();
			return null;

		}

		clienteBd.fechaConexao();
		return cliente;
	}// conClienteNome()


	/* *********************************** Listar Clientes *********************************** */
	/**
	 * Este método retorna uma lista com todos os Clientes vindo do banco de dados.
	 * 
	 * 
	 * @return lista de clientes se existir no banco, null caso contrário.
	 * */
	public ArrayList<Pizzaria> listaClientes(){

		ClienteBd clienteBd = new ClienteBd();

		ArrayList<Pizzaria>clientes = clienteBd.listarObjetos();

		if(clientes == null){

			EntradaESaida.msgErro("Não foi possível listar os Clientes", 
					ConstantesFuncionarios.TITULO_FUNCIONARIO_LISTAR.valor());

			clienteBd.fechaConexao();
			return null;

		}

		clienteBd.fechaConexao();
		return clientes;
	}

	/* *********************************** Alterar Cliente *********************************** */
	/**
	 * Altera os dados de um Cliente.
	 * 
	 * @param cpf(antigo) do Cliente que deseja alterar os dados.
	 * @param cliente Cliente com os novos dados.
	 * 
	 * @return true caso alterou com sucesso, false caso contrário.
	 * */
	public boolean altCliente(String cpf, Cliente cliente){

		// Verifica se os campos Nome, Logradouro, Complemento, Bairro e Cidade estão vazios.
		if(verificaEntradaCamposVazio(cliente.getNome(), cliente.getLogradouro(), 
				cliente.getComplemento(), cliente.getBairro(), cliente.getCidade()) == false){

			EntradaESaida.msgErro("Os campos Nome, Logradouro, Complemento, Bairro e Cidade\n não podem estar vazios", 
					ConstantesClientes.TITULO_CLIENTE_ALTERAR.valor());

			return false;

		}


		ClienteBd clienteBd = new ClienteBd();

		// Verifica se o funcionário ainda exista no banco de dados.
		boolean existe = clienteBd.pesquisaCpfBd(cpf);

		if(existe == false){

			EntradaESaida.msgErro("Cliente Inexistente!", 
					ConstantesClientes.TITULO_CLIENTE_ALTERAR.valor());
			clienteBd.fechaConexao();
			return false;

		}

		// Se for true, quer dizer que o usuário apenas quer trocar...
		else if(cliente.getCpf().trim().equalsIgnoreCase(cpf.trim()) == true)
			;

		// Verificando se o nome do novo funcionário já existe no banco de dados.
		else if(clienteBd.pesquisaCpfBd(cliente.getCpf()) == true){

			EntradaESaida.msgErro("Cpf já existente no sistema!", 
					ConstantesClientes.TITULO_CLIENTE_ALTERAR.valor());
			clienteBd.fechaConexao();
			return false;
		}

		// Insere no banco de dados.
		if(clienteBd.alterarBd(cliente, cpf) == false){

			EntradaESaida.msgErro("Erro ao alterar Cliente",
					ConstantesClientes.TITULO_CLIENTE_ALTERAR.valor());

			clienteBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Alteração feita com sucesso", 
				ConstantesClientes.TITULO_CLIENTE_ALTERAR.valor());

		clienteBd.fechaConexao();
		return true;
	}


	/* *********************************** Excluir Cliente *********************************** */
	/**
	 * Exclui os dados de um Cliente.
	 * 
	 * @param cpf do Cliente que deseja excluir os dados.
	 * 
	 * @return true caso excluiu com sucesso, false caso contrário.
	 * */
	public boolean excCliente(String cpf){

		// Faz uma última verificação, para ver se o usuário ainda consta no banco de dados.
		ClienteBd clienteBd = new ClienteBd();

		if(clienteBd.pesquisaCpfBd(cpf) == false){

			EntradaESaida.msgErro("Cliente inexistente!", 
					ConstantesClientes.TITULO_CLIENTE_EXCLUIR.valor());

			clienteBd.fechaConexao();
			return false;

		}

		// Colocar os comandos abaixo dentro do EntradaESaida.java
		EntradaESaida.somPergunta();
		int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?",
				ConstantesClientes.TITULO_CLIENTE_EXCLUIR.valor(), JOptionPane.YES_NO_OPTION);

		if(op == 1){
			clienteBd.fechaConexao();
			return false;
		}

		if(clienteBd.removeBd(cpf) == false){

			EntradaESaida.msgErro("Não foi possível remover o Cliente",
					ConstantesClientes.TITULO_CLIENTE_EXCLUIR.valor());

			clienteBd.fechaConexao();
			return false;
		}

		EntradaESaida.msgInfo("Cliente removido",
				ConstantesClientes.TITULO_CLIENTE_EXCLUIR.valor());

		clienteBd.fechaConexao();
		return true;
	}

	/** Fecha a conexão com o banco de dados e despacha outras coisas. */
	public boolean finaliza(){

		ClienteBd clienteBd = new ClienteBd();

		if(clienteBd.fechaConexao() == false)
			return false;

		return true;

	}// finaliza()

}// Fim da class ControleCliente
