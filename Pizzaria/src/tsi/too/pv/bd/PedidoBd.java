package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Pedido;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe contém os métodos necessários para manipular a tabela Pedido do banco de dados.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class PedidoBd extends DataBase{

	/** Recebe uma conexão com o banco de dados.*/
	public PedidoBd(){
		abreConexao();
	}

	/* ***************************** Insere Pedido ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof Pedido){

			String sql = "INSERT INTO pedido(cpf, data, hora, situacao, precoTotal, formaDePagamento) "
					+ "VALUES(?,?,?,?,?,?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setString(1, ((Pedido) obj).getCpf());
				stm.setString(2, EntradaESaida.dataToString(((Pedido) obj).getDataHora()));
				stm.setString(3, EntradaESaida.horaToString(((Pedido) obj).getDataHora()));
				stm.setString(4, ((Pedido) obj).getSituacao());
				stm.setFloat(5, ((Pedido) obj).getPreco());
				stm.setString(6, ((Pedido) obj).getFormaDePagamento());
				stm.execute();
				stm.close();

			} 

			catch (SQLException e) {

				return false;

			}

		}// Verifica se é um pedido.

		return true;

	}// insereBd()

	/* ***************************** Obtém Pedido ***************************** */
	@Override
	public Pizzaria obtemObjeto(int codigo) {

		Pedido pedido = null;

		String sql = "SELECT * FROM pedido WHERE numeroPedido=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				pedido = new Pedido(rs.getInt("numeroPedido"), rs.getString("cpf"), rs.getString("situacao"), 
						rs.getString("formaDePagamento"), EntradaESaida.StringToData(rs.getString("data")), 
						rs.getString("hora"), rs.getFloat("precoTotal"));

			stm.close();
		}

		catch(SQLException ex){

			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return null;

		}

		return pedido;

	}// obtemObjeto()

	/* ****************************** Deleta Pedido do banco de dados ****************************** */
	@Override
	public boolean removeBd(int codigo) {

		String sql = "delete from pedido where numeroPedido=?";

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

	/* ***************************** Obtém último código ***************************** */
	@Override
	public int ultimoCodigo() {

		int cod = -1;

		String sql = "SELECT * FROM sqlite_sequence WHERE name=\"pedido\"";

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

	/* ***************************** Seta situação ***************************** */
	/** Seta a situação de pedido fechado na tabela pedido.
	 * @param codigo do pedido.
	 * 
	 * @return true em caso de sucesso, false caso contrário.
	 * */
	public boolean encerraPedidoBd(int numeroPedido){

		String sql = "update pedido set situacao=? where numeroPedido=?";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "Fechado");
			stmt.setInt(2, numeroPedido);
			stmt.executeUpdate();
			stmt.close();

		} 

		catch (SQLException e) {
			return false;
		}


		return true;

	}// encerraPedido

	/* ***************************** Lista Pedidos ***************************** */
	@Override
	public ArrayList<Pizzaria> listarObjetos() {

		ArrayList<Pizzaria>lista = new ArrayList<>();

		String sql = "SELECT * FROM pedido ORDER BY numeroPedido ASC";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while(rs.next()){

				Pedido pedido = new Pedido(rs.getInt("numeroPedido"), rs.getString("cpf"), rs.getString("situacao"), 
						rs.getString("formaDePagamento"), EntradaESaida.StringToData(rs.getString("data")), 
						rs.getString("hora"), rs.getFloat("precoTotal"));

				lista.add(pedido);

			}

			stm.close();
			return lista;

		}

		catch(SQLException ex){

			return null;

		}


	}// listaObetos()
	
	/* ***************************** Obtém Pedidos através da data ***************************** */
	/**
	 * Obtém através da data um ArrayList do tipo Pedido vindo do banco de dados.
	 * 
	 * @param data do pedido que será pesquisado.
	 * 
	 * @return um objeto do tipo <code>Pizza</code> em caso de sucesso ou null caso contrário.
	 * */
	public ArrayList<Pedido> listaDataPedidos(String data){

		ArrayList<Pedido>lista = new ArrayList<>();
		
		String sql = "SELECT * FROM pedido WHERE data=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, data);
			ResultSet rs = stm.executeQuery();

			while(rs.next())
				lista.add(new Pedido(rs.getInt("numeroPedido"), rs.getString("cpf"), rs.getString("situacao"), 
						rs.getString("formaDePagamento"), EntradaESaida.StringToData(rs.getString("data")), 
						rs.getString("hora"), rs.getFloat("precoTotal")));

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
	public boolean removeBd(String descricao) {
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

	@Override
	public boolean pesquisaNomeBd(String nome) {
		return false;
	}

	@Override
	public Pizzaria obtemObjeto(String descricao) {
		return null;
	}


}
