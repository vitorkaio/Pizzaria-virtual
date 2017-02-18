package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.tipos.PizzaPedida;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe contém os métodos necessários para manipular a tabela PizzaPedida do banco de dados.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class PizzaPedidaBd extends DataBase{

	/** Recebe uma conexão com o banco de dados.*/
	public PizzaPedidaBd(){
		abreConexao();
	}

	/* ***************************** Insere PizzaPedida ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof PizzaPedida){

			String sql = "INSERT INTO pizzaPedida(numeroPedido, codigoPizza, borda, quantidade)"
					+ "VALUES(?,?,?,?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setInt(1, ((PizzaPedida) obj).getNumeroPedido());
				stm.setInt(2, ((PizzaPedida) obj).getCodigoPizza());
				stm.setString(3, ((PizzaPedida) obj).getBorda());
				stm.setInt(4, ((PizzaPedida) obj).getQuantidade());
				stm.execute();
				stm.close();
			} 

			catch (SQLException e) {

				e.printStackTrace();
				System.out.println(e.getMessage());
				return false;

			}

		}// Verifica se é PizzaPedida

		return true;

	}// insereBd()

	/* ***************************** Obtém PizzaPedida ***************************** */
	@Override
	public Pizzaria obtemObjeto(int codigo) {
		
		PizzaPedida pizzaPedida = null;

		String sql = "SELECT * FROM pizzaPedida WHERE numeroPedido=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				pizzaPedida = new PizzaPedida(rs.getInt("numeroPedido"), rs.getInt("codigoPizza"), 
						rs.getInt("quantidade"), rs.getString("borda"));
		}

		catch(SQLException ex){

			return null;

		}

		return pizzaPedida;
		
	}// obtemObjeto()
	
	/* ***************************** Obtém Pizza Pedidas ***************************** */
	/**
	 * Obtém através do codigo um ArrayList do tipo PizzaPedida vindo do banco de dados.
	 * 
	 * @param codigo do pedido que será pesquisado.
	 * 
	 * @return um objeto do tipo <code>Pizza</code> em caso de sucesso ou null caso contrário.
	 * */
	public ArrayList<PizzaPedida> obterPizzaPedidaBd(int codigo){

		ArrayList<PizzaPedida>lista = new ArrayList<>();
		
		String sql = "SELECT * FROM pizzaPedida WHERE numeroPedido=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			while(rs.next())
				lista.add(new PizzaPedida(codigo, rs.getInt("codigoPizza"), rs.getInt("quantidade"), 
						rs.getString("borda")));

			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return lista;

	}// obterPizza()
	
	/* ************************************************************************************* 
	*																					   *
	*																					   *
	*								Métodos não implementados							   *
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
	public int ultimoCodigo() {
		return 0;
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
	public boolean removeBd(int codigo) {
		return false;
	}

	@Override
	public boolean alterarBd(Pizzaria obj, String descricao) {
		return false;
	}

	@Override
	public boolean alterarBd(Pizzaria obj, int codigo) {
		return false;
	}



}// Fim da classe PizzaPedidaBd
