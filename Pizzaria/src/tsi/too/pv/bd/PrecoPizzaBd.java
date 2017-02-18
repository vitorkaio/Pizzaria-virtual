package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Pizza;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe cont�m os m�todos necess�rios para manipular a tabela pizza do banco de dados.
 * 
 * @author V�tor Caio De Paula
 * @since 1.0
 * */
public class PrecoPizzaBd extends DataBase{

	/** Recebe uma conex�o com o banco de dados.*/
	public PrecoPizzaBd(){

		abreConexao();

	}

	/* ***************************** Insere Pizza ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof Pizza){

			String sql = "INSERT INTO PrecoPizza(codPizza, tamanho, data, preco) VALUES(?,?,?,?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setInt(1, ((Pizza) obj).getCodigo());
				stm.setString(2, String.valueOf(((Pizza) obj).getTamanho()));
				stm.setString(3, EntradaESaida.dataToString(((Pizza) obj).getData()));
				stm.setFloat(4, ((Pizza) obj).getPreco());
				stm.execute();
				stm.close();
			} 

			catch (SQLException e) {

				return false;

			}


		}// Verifica se � uma Pizza.

		return true;

	}// insereBd()

	/* ***************************** Obtem Pizza ***************************** */
	@Override
	public Pizzaria obtemObjeto(int codigo) {

		Pizza pi = null;

		String sql = "SELECT * FROM PrecoPizza WHERE codPizza=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				pi = new Pizza("", rs.getString("tamanho").charAt(0), EntradaESaida.StringToData(rs.getString("data")), 
						rs.getFloat("preco"));

			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return pi;

	}// obtemObjeto()
	
	/* ***************************** Remove Pizza ***************************** */
	@Override
	public boolean removeBd(int codigo) {
		
		String sql = "delete from precoPizza where codPizza=?";

		try {

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setInt(1, codigo);
			stm.execute();
			stm.close();

		} catch (SQLException e) {

			EntradaESaida.msgErro("N�o foi poss�vel excluir do banco de dados",
					ConstantesBd.ERRO_EXCLUIR_BANCO_DE_DADOS.valor());

			return false;

		}

		return true;

	}

	/* ***************************** Alterar Pizza ***************************** */
	@Override
	public boolean alterarBd(Pizzaria obj, int codigo) {
		
		if(obj instanceof Pizza){

			String sql = "update precoPizza set codPizza=?, tamanho=?, data=?, preco=? where codPizza=?";

			try {

				PreparedStatement stm;
				stm = connection.prepareStatement(sql);
				stm.setInt(1, ((Pizza) obj).getCodigo());
				stm.setString(2, String.valueOf(((Pizza) obj).getTamanho()));
				stm.setString(3, EntradaESaida.dataToString(((Pizza) obj).getData()));
				stm.setFloat(4, ((Pizza) obj).getPreco());
				stm.setInt(5, codigo);
				stm.executeUpdate();
				stm.close();

			} 
			
			catch (SQLException e) {
				
				return false;
				
			}

		}// Verifica se � uma Pizza.

		return true;
		
	}// alterarBd()
	
	/* ***************************** Alterar Pizza ***************************** */
	@Override
	public int ultimoCodigo() {
		
		int cod = -1;

		String sql = "SELECT * FROM sqlite_sequence WHERE name=\"precoPizza\"";

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
	
	/* ************************************************************************************* 
	*																					   *
	*																					   *
	*								M�todos n�o implementados							   *
	*																					   *
	*																					   *
	*																					   *
	************************************************************************************** */
	@Override
	public boolean pesquisaNomeBd(String nome) {
		return false;
	}

	@Override
	public Pizzaria obtemObjeto(String descricao) {
		return null;
	}

	@Override
	public ArrayList<Pizzaria> listarObjetos() {
		return null;
	}

	@Override
	public boolean removeBd(String descricao) {
		return false;
	}

	@Override
	public boolean alterarBd(Pizzaria obj, String descricao) {
		return false;
	}

} // Fim PrecoPizza
