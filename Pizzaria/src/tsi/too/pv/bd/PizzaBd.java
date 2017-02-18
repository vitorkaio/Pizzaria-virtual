package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Pizza;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe contém os métodos necessários para manipular a tabela pizza do banco de dados.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */

public class PizzaBd extends DataBase{

	/** Recebe uma conexão com o banco de dados.*/
	public PizzaBd(){
		abreConexao();
	}

	/* ***************************** Insere Pizza ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof Pizza){

			String descricao = ((Pizza) obj).getDescricao().toLowerCase().trim()
					+ "-" + ((Pizza) obj).getTamanho();

			String sql = "INSERT INTO pizza(descricao) VALUES(?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setString(1, descricao.toLowerCase().trim());
				stm.execute();
				stm.close();

			} 

			catch (SQLException e) {

				return false;

			}

			return true;


		}// Verifica se é uma Pizza.

		else
			return false;

	}// insereBancoDedados()


	/* ***************************** Pesquisa Nome ***************************** */
	@Override
	public boolean pesquisaNomeBd(String nome) {

		String sql = "SELECT * FROM pizza WHERE descricao=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, nome.toLowerCase().trim());
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				return true;

			stm.close();
		}

		catch(SQLException ex){

			return false;

		}

		return false;

	}// pesquisaNomeBd()

	/* ***************************** Obtém Pizza ***************************** */
	@Override
	public Pizzaria obtemObjeto(String descricao) {

		Pizza pi = null;

		String sql = "SELECT * FROM pizza WHERE descricao=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, descricao.toLowerCase().trim());
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				pi = new Pizza(rs.getInt("codigoPizza"), rs.getString("descricao"));

			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return pi;

	}// obtemObjeto()
	
	@Override
	public Pizzaria obtemObjeto(int codigo) {
		
		Pizza pi = null;

		String sql = "SELECT * FROM pizza WHERE codigoPizza=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				pi = new Pizza(rs.getInt("codigoPizza"), rs.getString("descricao"));

			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return pi;
		
	}// obtemObjeto()


	/* ***************************** Obtém last cod ***************************** */
	@Override
	public int ultimoCodigo() {

		int cod = -1;

		String sql = "SELECT * FROM sqlite_sequence WHERE name=\"pizza\"";

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

	/* ***************************** Retorna todos as pizza do banco ***************************** */
	@Override
	public ArrayList<Pizzaria> listarObjetos() {

		ArrayList<Pizzaria>lista = new ArrayList<>();

		String sql = "SELECT * FROM pizza ORDER BY descricao ASC";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while(rs.next()){

				Pizza pi = new Pizza(rs.getInt("codigoPizza"), rs.getString("descricao"));

				lista.add(pi);

			}

			stm.close();
			return lista;

		}

		catch(SQLException ex){

			return null;

		}

	}// listarObjetos()


	/* ***************************** Retorna todos as descrições pizza do banco ************************* */
	/**
	 * Este método retorna todas as descrições da pizza que foram cadastrados no banco de dados.
	 * 
	 * @return Um arrayList do tipo <code>String</code> em caso de sucesso ou null caso contrário.
	 * */
	public ArrayList<String>listaDescricaoPizza(){

		ArrayList<String>lista = new ArrayList<>();

		String sql = "SELECT * FROM pizza ORDER BY descricao ASC";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while(rs.next()){

				String pi = rs.getString("descricao");

				lista.add(pi);

			}

			stm.close();
			return lista;

		}

		catch(SQLException ex){

			return null;

		}


	}// listaDescricaoPizza()

	/* ****************************** Deleta Pizza do banco de dados ****************************** */
	@Override
	public boolean removeBd(String descricao) {

		String sql = "delete from pizza where descricao=?";

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
	
	@Override
	public boolean removeBd(int codigo) {
		
		String sql = "delete from pizza where codigoPizza=?";

		try {

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setInt(1, codigo);
			stm.execute();
			stm.close();

		} catch (SQLException e) {

			EntradaESaida.msgErro("Não foi possível excluir do banco de dados",
					ConstantesBd.ERRO_EXCLUIR_BANCO_DE_DADOS.valor());

			return false;

		}

		return true;
		
	}// removeBd()

	/* ****************************** Alterar Pizza do banco de dados ****************************** */
	@Override
	public boolean alterarBd(Pizzaria obj, String descricao) {

		if(obj instanceof Pizza){

			String des = ((Pizza) obj).getDescricao() + "-" + ((Pizza) obj).getTamanho();
			
			String sql = "update pizza set descricao=? where descricao=?";

			try {

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, des.toLowerCase().trim());
				stmt.setString(2, descricao.toLowerCase().trim());
				stmt.executeUpdate();
				stmt.close();

			} 
			
			catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
				
			}

		}// Verifica se é uma Pizza.

		return true;
		
	}// alterarBd()
	
	@Override
	public boolean alterarBd(Pizzaria obj, int codigo) {
		if(obj instanceof Pizza){

			String des = ((Pizza) obj).getDescricao() + "-" + ((Pizza) obj).getTamanho();
			
			String sql = "update pizza set descricao=? where codigoPizza=?";

			try {

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, des.toLowerCase().trim());
				stmt.setInt(2, codigo);
				stmt.executeUpdate();
				stmt.close();

			} 
			
			catch (SQLException e) {
				
				return false;
				
			}

		}// Verifica se é uma Pizza.

		return true;
	}

}// Fim PizzaBd