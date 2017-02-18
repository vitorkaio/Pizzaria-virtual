package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.tipos.CompozicaoPizza;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe contém os métodos necessários para manipular a tabela compozicao pizza do banco de dados.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class CompozicaoPizzaBd extends DataBase{

	/** Recebe uma conexão com o banco de dados.*/
	public CompozicaoPizzaBd(){
		abreConexao();
	}

	/* ***************************** Insere Compozicao Pizza ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof CompozicaoPizza){

			String sql = "INSERT INTO compozicaoPizza(codigoPizza, codigoIngrediente) VALUES(?,?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setInt(1, ((CompozicaoPizza) obj).getCodigoPizza());
				stm.setInt(2, ((CompozicaoPizza) obj).getCodigoIngrediente());
				stm.execute();
				stm.close();

			} 

			catch (SQLException e) {

				e.printStackTrace();
				return false;

			}

		}// verifica se é CompozicaoPizza.

		return true;

	}// insereBd()

	/* ***************************** Obtém Compozicao Pizza ***************************** */
	/**
	 * Obtém através do codigo uma lista de objetos do tipo compozicaoPizza vindo do banco de dados.
	 * 
	 * @param codigo da pizza que será pesquisado.
	 * 
	 * @return um objeto do tipo <code>Pizza</code> em caso de sucesso ou null caso contrário.
	 * */
	public ArrayList<CompozicaoPizza> obterCompozicaoPizzaBd(int codigo){

		ArrayList<CompozicaoPizza>lista = new ArrayList<>();

		String sql = "SELECT * FROM compozicaoPizza WHERE codigoPizza=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			while(rs.next())
				lista.add(new CompozicaoPizza(rs.getInt("codigoPizza"), rs.getInt("codigoIngrediente")));

			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return lista;

	}// obterPizza()

	@Override
	public ArrayList<Pizzaria> listarObjetos() {
		ArrayList<Pizzaria>lista = new ArrayList<>();

		String sql = "SELECT * FROM compozicaoPizza ORDER BY codigoPizza";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while(rs.next()){

				CompozicaoPizza compozicao = new CompozicaoPizza(rs.getInt("codigoPizza"), 
						rs.getInt("codigoIngrediente"));

				lista.add(compozicao);

			}

			stm.close();
			return lista;

		}

		catch(SQLException ex){

			return null;

		}
	}// listaCompozicaoPizza()

	/* ****************************** Alterar compozição ****************************** */
	@Override
	public boolean alterarBd(Pizzaria obj, int codigo) {

		if(obj instanceof CompozicaoPizza){

			String sql = "update compozicaoPizza set codigoIngrediente=? where codigoPizza=?";

			try {

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setInt(1, ((CompozicaoPizza) obj).getCodigoIngrediente());
				stmt.setInt(2, codigo);
				stmt.executeUpdate();
				stmt.close();

			} 

			catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;

			}

		}

		return true;
	}
	
	@Override
	public boolean removeBd(int codigo) {
		
		String sql = "delete from compozicaoPizza where codigoPizza=?";

		try {

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setInt(1, codigo);
			stm.execute();
			stm.close();

		} catch (SQLException e) {

			return false;

		}

		return true;
	}


	/* ************************************************************************************* 
	 *																					   *
	 *																					   *
	 *								Métodos não implementados							   *
	 *																					   *
	 *																					   *
	 *																					   *
	 ************************************************************************************** */

	@Override
	public boolean alterarBd(Pizzaria obj, String descricao) {

		return false;
	}
	
	@Override
	public boolean pesquisaNomeBd(String nome) {
		return false;
	}

	@Override
	public Pizzaria obtemObjeto(String descricao) {
		return null;
	}

	@Override
	public Pizzaria obtemObjeto(int codigo) {
		return null;
	}// obtemObjeto()

	@Override
	public int ultimoCodigo() {
		return 0;
	}


	@Override
	public boolean removeBd(String descricao) {
		return false;
	}

}// Fim da class CompozicaoPizzaBd
