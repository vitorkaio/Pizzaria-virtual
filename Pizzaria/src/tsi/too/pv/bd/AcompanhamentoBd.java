package tsi.too.pv.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Acompanhamento;
import tsi.too.pv.tipos.Pizzaria;

/**
 * Esta classe cont�m os m�todos necess�rios para manipular a tabela Acompanhamento do banco de dados.
 * 
 * @author V�tor Caio De Paula
 * @since 1.0
 * */
public class AcompanhamentoBd extends DataBase{

	/** Recebe uma conex�o com o banco de dados.*/
	public AcompanhamentoBd(){
		abreConexao();
	}

	/* ***************************** Insere Pedido ***************************** */
	@Override
	public boolean insereBd(Pizzaria obj) {

		if(obj instanceof Acompanhamento){

			String sql = "INSERT INTO acompanhamento(numeroPedido, tipo, quantidade, preco) VALUES(?,?,?,?)";

			try {

				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setInt(1, ((Acompanhamento) obj).getNumeroPedido());
				stm.setString(2, ((Acompanhamento) obj).getTipo());
				stm.setInt(3, ((Acompanhamento) obj).getQuantidade());
				stm.setFloat(4, ((Acompanhamento) obj).getPreco());
				stm.execute();
				stm.close();

			} 

			catch (SQLException e) {

				return false;

			}

		}// verifica se � um acompanhamento.
		return true;

	}// insereBd()

	/* ***************************** Obt�m Acompanhamento ***************************** */
	@Override
	public Pizzaria obtemObjeto(int codigo) {

		Acompanhamento acompanhamento = null;

		String sql = "SELECT * FROM acompanhamento WHERE numeroPedido=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			if(rs.next())
				acompanhamento = new Acompanhamento(rs.getInt("numeroPedido"), rs.getString("tipo"), 
						rs.getInt("quantidade"), rs.getFloat("preco"));

			stm.close();
		}

		catch(SQLException ex){

			return null;

		}

		return acompanhamento;

	}// obtemObjeto()

	/* ****************************** Deleta Acompanhamento do banco de dados ****************************** */
	@Override
	public boolean removeBd(int codigo) {

		String sql = "delete from acompanhamento where codPizza=?";

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

	}// removeBd()
	
	/* ***************************** Obt�m Acompanhamento ***************************** */
	/**
	 * Obt�m atrav�s do codigo uma lista de objetos do tipo Acompanhamento vindo do banco de dados.
	 * 
	 * @param codigo do Acompanhamento que ser� pesquisado.
	 * 
	 * @return um objeto do tipo <code>Acompanhamento</code> em caso de sucesso ou null caso contr�rio.
	 * */
	public ArrayList<Acompanhamento> obterListaAcompanhamentoBd(int codigo){

		ArrayList<Acompanhamento>lista = new ArrayList<>();
		
		String sql = "SELECT * FROM acompanhamento WHERE numeroPedido=?";

		try{

			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();

			while(rs.next())
				lista.add(new Acompanhamento(codigo, rs.getString("tipo"), rs.getInt("quantidade"), 
						rs.getFloat("preco")));

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
	*								M�todos n�o implementados							   *
	*																					   *
	*																					   *
	*																					   *
	************************************************************************************** */

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

}// Fim da class AcompanhamentoBd
