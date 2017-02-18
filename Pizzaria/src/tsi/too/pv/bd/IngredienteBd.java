package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Ingrediente;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe contém os métodos necessários para manipular a tabela ingrediente do banco de dados.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class IngredienteBd extends DataBase{

	/** Recebe uma conexão com o banco de dados.*/
	public IngredienteBd(){
		abreConexao();
	}

	/* ***************************** Insere Ingrediente ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof Ingrediente){
			
			String sql = "INSERT INTO ingrediente(descricao, preco) VALUES(?,?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setString(1, ((Ingrediente) obj).getDescricao().toLowerCase().trim());
				stm.setFloat(2, ((Ingrediente) obj).getPreco());
				stm.execute();
				stm.close();

			} 

			catch (SQLException e) {

				return false;

			}
			
			return true;
			
		}// Verifica se é um ingrediente.
		
		else
			return false;

	}// insereBd()

	/* ***************************** Pesquisa Nome ***************************** */
	@Override
	public boolean pesquisaNomeBd(String nome) {

		String sql = "SELECT * FROM ingrediente WHERE descricao=?";

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

	}// pesquisaNomeBd()

	/* ***************************** Obtém Ingrediente ***************************** */
	@Override
	public Pizzaria obtemObjeto(String descricao) {

		Ingrediente ingrediente = null;

		String sql = "SELECT * FROM ingrediente WHERE descricao=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, descricao.toLowerCase().trim());
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				ingrediente = new Ingrediente(rs.getInt("codigoIngrediente"), 
						rs.getString("descricao"), rs.getFloat("preco"));
			
			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return ingrediente;

	}// obtemObjeto()

	@Override
	public Pizzaria obtemObjeto(int codigo) {

		Ingrediente ingrediente = null;

		String sql = "SELECT * FROM ingrediente WHERE codigoIngrediente=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				ingrediente = new Ingrediente(rs.getInt("codigoIngrediente"), 
						rs.getString("descricao"), rs.getFloat("preco"));

			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return ingrediente;

	}// obtemObjeto()

	/* ***************************** Obtém last cod ***************************** */
	@Override
	public int ultimoCodigo() {
		int cod = -1;

		String sql = "SELECT * FROM sqlite_sequence WHERE name=\"ingrediente\"";

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
	}

	/* ***************************** Retorna todos os ingrediente do banco ***************************** */
	@Override
	public ArrayList<Pizzaria> listarObjetos() {

		ArrayList<Pizzaria>lista = new ArrayList<>();

		String sql = "SELECT * FROM ingrediente ORDER BY descricao ASC";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while(rs.next()){

				Ingrediente ingrediente = new Ingrediente(rs.getInt("codigoIngrediente"), 
						rs.getString("descricao"), rs.getFloat("preco"));

				lista.add(ingrediente);

			}

			stm.close();
			return lista;

		}

		catch(SQLException ex){

			return null;

		}

	}// listarObjetos()

	/* ****************************** Deleta Ingrediente do banco de dados ****************************** */
	@Override
	public boolean removeBd(String descricao) {

		String sql = "delete from ingrediente where descricao=?";

		try {

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, descricao.toLowerCase().trim());
			stm.execute();
			stm.close();

		} catch (SQLException e) {

			return false;

		}

		return true;

	}// removeBd()

	@Override
	public boolean removeBd(int codigo) {

		String sql = "delete from ingrediente where codigoIngrediente=?";

		try {

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setInt(1, codigo);
			stm.execute();
			stm.close();

		} catch (SQLException e) {

			return false;

		}

		return true;

	}// removeBd()

	/* ****************************** Alterar Ingrediente do banco de dados ****************************** */
	@Override
	public boolean alterarBd(Pizzaria obj, String descricao) {

		if(obj instanceof Ingrediente){
			
			String des = ((Ingrediente) obj).getDescricao();

			String sql = "update ingrediente set descricao=?, preco=? where descricao=?";

			try {

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, des.toLowerCase().trim());
				stmt.setFloat(2, ((Ingrediente) obj).getPreco());
				stmt.setString(3, descricao.toLowerCase().trim());
				stmt.executeUpdate();
				stmt.close();

			} 

			catch (SQLException e) {
				return false;
			}

		}// Verifica se é uma Pizza.

		return true;

	}// alterarBd()

	@Override
	public boolean alterarBd(Pizzaria obj, int codigo) {
		
		if(obj instanceof Ingrediente){

			String sql = "update ingrediente set descricao=?, preco=? where codigoIngrediente=?";

			try {

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, ((Ingrediente) obj).getDescricao().toLowerCase());
				stmt.setFloat(2, ((Ingrediente) obj).getPreco());
				stmt.setInt(3, ((Ingrediente) obj).getCodigo());
				stmt.executeUpdate();
				stmt.close();

			} 

			catch (SQLException e) {
				return false;
			}

		}// Verifica se é uma Pizza.

		return true;

	}// alterarBd()

}// Fim class IngredienteBd
