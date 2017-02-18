package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Cliente;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe contém os métodos necessários para manipular a tabela cliente do banco de dados.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class ClienteBd extends DataBase{

	/** Recebe uma conexão com o banco de dados.*/
	public ClienteBd(){

		abreConexao();		

	}// Fim do construtor.

	/* ***************************** Insere Cliente ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof Cliente){

			String sql = "INSERT INTO cliente(cpf, nome, logradouro, cidade, bairro, numero, complemento, "
					+ "telefone_fixo, telefone_movel) VALUES(?,?,?,?,?,?,?,?,?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);

				stm.setString(1, ((Cliente) obj).getCpf());
				stm.setString(2, ((Cliente) obj).getNome().toLowerCase().trim());
				stm.setString(3, ((Cliente) obj).getLogradouro().toLowerCase().trim());
				stm.setString(4, ((Cliente) obj).getCidade().toLowerCase().trim());
				stm.setString(5, ((Cliente) obj).getBairro().toLowerCase().trim());
				stm.setInt(6, ((Cliente) obj).getNumero());
				stm.setString(7, ((Cliente) obj).getComplemento().toLowerCase().trim());
				stm.setString(8, ((Cliente) obj).getTelefoneFixo());
				stm.setString(9, ((Cliente) obj).getTelefoneMovel());

				stm.execute();
				stm.close();

			} 

			catch (SQLException e) {
				return false;
			}

			return true;

		}

		else
			return false;

	}// insereCliente()

	/* ***************************** Pesquisa cpf ***************************** */
	/** 
	 * Pesquisa se o cpf fornecido já está presente no banco de dados.
	 * 
	 * @param cpf a ser pesquisado no banco de dados.
	 * 
	 * @return true se o cpf já existe, false caso contrário
	 * */
	public boolean pesquisaCpfBd(String cpf){

		String sql = "SELECT * FROM cliente WHERE cpf=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, cpf.toLowerCase().trim());
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				return true;

			stm.close();
		}

		catch(SQLException ex){

			EntradaESaida.msgErro("Erro no banco de dados", 
					ConstantesBd.ERRO_CONSULTA_BANCO_DE_DADOS.valor());

			return false;

		}

		return false;

	}// Fim do pesquisaCpf().

	/* ***************************** Obtém cliente ***************************** */
	@Override
	public Pizzaria obtemObjeto(String descricao) {

		Cliente cliente = null;

		String sql = "SELECT * FROM cliente WHERE cpf=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, descricao.toLowerCase().trim());
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("logradouro"),
						rs.getString("complemento"), rs.getString("bairro"), rs.getString("cidade"),
						rs.getString("telefone_fixo"), rs.getString("telefone_movel"), rs.getInt("numero"));
			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return cliente;

	}// obterCliente()

	/* ***************************** Obtém last cod ***************************** */
	@Override
	public int ultimoCodigo() {
		int cod = -1;

		String sql = "SELECT * FROM sqlite_sequence WHERE name=\"cliente\"";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				cod = rs.getInt("seq");

			stm.close();
		}

		catch(SQLException ex){

			System.out.println("Erro ao pegar last cod");
			return -1;

		}

		return cod;

	}// ultimoCodigo()

	/* ***************************** Retorna todos os clientes do banco ***************************** */
	@Override
	public ArrayList<Pizzaria> listarObjetos() {

		ArrayList<Pizzaria>lista = new ArrayList<>();

		String sql = "SELECT * FROM cliente ORDER BY cpf ASC";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while(rs.next()){

				Cliente cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("logradouro"),
						rs.getString("cidade"), rs.getString("bairro"), rs.getString("complemento"),
						rs.getString("telefone_fixo"), rs.getString("telefone_movel"), rs.getInt("numero"));

				lista.add(cliente);

			}

			stm.close();
			return lista;

		}

		catch(SQLException ex){

			return null;

		}

	}// listarObjetos()

	/* ****************************** Deleta Cliente do banco de dados ****************************** */
	@Override
	public boolean removeBd(String descricao) {

		String sql = "delete from cliente where cpf=?";

		try {

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, descricao.toLowerCase().trim());
			stm.execute();
			stm.close();

		} catch (SQLException e) {

			EntradaESaida.msgErro("Não foi possível excluir do banco de dados",
					ConstantesBd.ERRO_EXCLUIR_BANCO_DE_DADOS.valor());

			return false;

		}

		return true;

	}// removeBd()

	/* ****************************** Alterar Cliente do banco de dados ****************************** */
	@Override
	public boolean alterarBd(Pizzaria obj, String descricao) {

		if(obj instanceof Cliente){

			/*"INSERT INTO cliente(cpf, nome, logradouro, cidade, bairro, numero, complemento, "
				+ "telefone_fixo, telefone_movel) VALUES(?,?,?,?,?,?,?,?,?)"
			 * 
			 * */
			String sql = "UPDATE cliente set cpf=?, nome=?, logradouro=?, cidade=?, bairro=?"
					+ ", numero=?, complemento=?, telefone_fixo=?, telefone_movel=? where cpf=?";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setString(1, ((Cliente) obj).getCpf().toLowerCase().trim());
				stm.setString(2, ((Cliente) obj).getNome().toLowerCase().trim());
				stm.setString(3, ((Cliente) obj).getLogradouro().toLowerCase().trim());
				stm.setString(4, ((Cliente) obj).getCidade().toLowerCase().trim());
				stm.setString(5, ((Cliente) obj).getBairro().toLowerCase().trim());
				stm.setInt(6, ((Cliente) obj).getNumero());
				stm.setString(7, ((Cliente) obj).getComplemento().toLowerCase().trim());
				stm.setString(8, ((Cliente) obj).getTelefoneFixo());
				stm.setString(9, ((Cliente) obj).getTelefoneMovel());
				stm.setString(10, descricao);
				stm.executeUpdate();
				stm.close();

			} 

			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return false;

			}

		}// Verifica se é uma Cliente.

		return true;

	}// alterarBd()

	/* ************************************************************************************* 
	 *																					   *
	 *																					   *
	 *								Métodos não implementados							   *
	 *																					   *
	 *																					   *
	 *																					   *
	 ************************************************************************************** */

	/**
	 * Obtém através do nome um objeto do tipo cliente vindo do banco de dados.
	 * 
	 * @param nome do cliente que será pesquisado.
	 * 
	 * @return um objeto do tipo <code>cliente</code> em caso de sucesso ou null caso contrário.
	 * */
	public Cliente obterClienteNomeBd(String nome){

		Cliente cliente = null;

		String sql = "SELECT * FROM cliente WHERE nome=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, nome);
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("logradouro"),
						rs.getString("complemento"), rs.getString("bairro"), rs.getString("cidade"),
						rs.getString("telefone_fixo"), rs.getString("telefone_movel"), rs.getInt("numero"));

		}

		catch(SQLException ex){

			return null;

		}

		return cliente;

	}// obterFuncionario()

	@Override
	public boolean alterarBd(Pizzaria obj, int codigo) {
		return false;
	}

	@Override
	public boolean pesquisaNomeBd(String nome) {
		return false;
	}

	@Override
	public Pizzaria obtemObjeto(int codigo) {
		return null;
	}

	@Override
	public boolean removeBd(int codigo) {
		return false;
	}

}// Fim da classe ClienteBd
