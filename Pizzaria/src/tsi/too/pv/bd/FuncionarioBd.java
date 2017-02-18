package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Funcionario;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe contém os métodos necessários para manipular a tabela funcionario do banco de dados.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class FuncionarioBd extends DataBase{

	/** Recebe uma conexão com o banco de dados.*/
	public FuncionarioBd(){
		abreConexao();
	}

	/* ***************************** Insere Funcionário ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof Funcionario){

			String sql = "INSERT INTO funcionario(nomeUsuario, senha, tipoUsuario) VALUES(?,?,?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);

				stm.setString(1, ((Funcionario) obj).getNomeUsuario().toLowerCase().trim());
				stm.setString(2, ((Funcionario) obj).getSenha().replaceAll(" ", ""));
				stm.setString(3, String.valueOf(((Funcionario) obj).getTipoUsuario()));

				stm.execute();
				stm.close();

			} 

			catch (SQLException e) {

				return false;

			}

			return true;

		}// Verifica se é cliente
		
		else
			return false;

	}// insereBd()

	/* ***************************** Pesquisa Nome ***************************** */
	@Override
	public boolean pesquisaNomeBd(String nome) {
		String sql = "SELECT * FROM funcionario WHERE nomeUsuario=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, nome.toLowerCase().trim());
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

	}// pesquisaNome()

	/* ***************************** Obtém funcionario ***************************** */
	@Override
	public Pizzaria obtemObjeto(String descricao) {

		Funcionario func = null;

		String sql = "SELECT * FROM funcionario WHERE nomeUsuario=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, descricao.toLowerCase().trim());
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				func = new Funcionario(rs.getString("nomeUsuario"), rs.getString("senha"),
						rs.getString("tipoUsuario").charAt(0));

			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return func;

	}// obterFuncionario()

	/* ***************************** Retorna todos os funcionários do banco ***************************** */
	@Override
	public ArrayList<Pizzaria> listarObjetos() {

		ArrayList<Pizzaria>lista = new ArrayList<>();

		String sql = "SELECT * FROM funcionario ORDER BY nomeUsuario ASC";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while(rs.next()){

				Funcionario func = new Funcionario(rs.getString("nomeUsuario"), rs.getString("senha"), 
						rs.getString("tipoUsuario").charAt(0));

				lista.add(func);

			}

			stm.close();
			return lista;

		}

		catch(SQLException ex){

			return null;

		}

	}// listarObjetos()

	/* ****************************** Deleta Funcionário do banco de dados ****************************** */
	@Override
	public boolean removeBd(String descricao) {

		String sql = "delete from funcionario where nomeUsuario=?";

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

	}// removerBd()

	/* ******************************* Alterar Funcionário banco de dados ******************************* */
	@Override
	public boolean alterarBd(Pizzaria obj, String descricao) {
		
		String sql = "UPDATE funcionario set nomeUsuario=?, senha=?, tipoUsuario=? WHERE nomeUsuario=?";

		try {

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, ((Funcionario) obj).getNomeUsuario().toLowerCase());
			stm.setString(2, ((Funcionario) obj).getSenha().replaceAll(" ", ""));
			stm.setString(3, String.valueOf(((Funcionario) obj).getTipoUsuario()));

			stm.setString(4, descricao.toLowerCase().trim());
			stm.executeUpdate();
			stm.close();
		} 

		catch (SQLException e) {

			EntradaESaida.msgErro("Erro ao alterar dados banco de dados", 
					ConstantesBd.ERRO_ALTERAR_BANCO_DE_DADOS.valor());

			return false;
		}

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

	@Override
	public boolean alterarBd(Pizzaria obj, int codigo) {
		return false;
	}

	@Override
	public boolean removeBd(int codigo) {
		return false;
	}

	@Override
	public Pizzaria obtemObjeto(int codigo) {
		return null;
	}

	@Override
	public int ultimoCodigo() {
		return 0;
	}


}// Fim da classe FuncionarioBd
