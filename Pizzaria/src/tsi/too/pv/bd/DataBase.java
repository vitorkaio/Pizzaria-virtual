package tsi.too.pv.bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.tipos.Pizzaria;

/** Classe que define as opera��es para manipula��o no banco de dados. */
public abstract class DataBase {

	/** Campo que faz a conex�o com banco de dados. */
	protected Connection connection;
	
	public void abreConexao(){
		
		this.connection = new tsi.too.pv.bd.ConnectionFactory().getConnection();
		
	}// abreConexao()
	
	/**
	 * Insere um objeto do tipo Pizzaria no banco de dados.
	 * 
	 * @param obj um objeto que ser� inserido no banco de dados.
	 * 
	 * @return <code>true</code> se a inser��o foi feita com sucesso, <code>false</code> caso contr�rio.
	 * */
	public abstract boolean insereBd(Pizzaria obj);
	
	
	/** 
	 * Pesquisa se o nome fornecido j� est� presente no banco de dados.
	 * 
	 * @param nome a ser pesquisado no banco de dados.
	 * 
	 * @return true se o nome j� existe, false caso contr�rio
	 * */
	public abstract boolean pesquisaNomeBd(String nome);
	
	
	/**
	 * Obt�m atrav�s do nome(descrica��o), um objeto Pizzaria do banco de dados.
	 * 
	 * @param descri��o do objeto que ser� pesquisado.
	 * 
	 * @return objeto em caso de sucesso ou null caso contr�rio.
	 * */
	public abstract Pizzaria obtemObjeto(String descricao);
	
	
	/**
	 * Obt�m atrav�s do codigo, um objeto Pizzaria do banco de dados.
	 * 
	 * @param codigo do objeto que ser� pesquisado.
	 * 
	 * @return objeto em caso de sucesso ou null caso contr�rio.
	 * */
	public abstract Pizzaria obtemObjeto(int codigo);
	
	
	/**
	 * Pega o �ltimo c�digo cadastrado na tabela.
	 * 
	 * @return cod do �ltimo objeto cadastrado, -1 caso contr�rio. 
	 * 
	 */
	public abstract int ultimoCodigo();
	
	
	/**
	 * Este m�todo retorna todos os objetos que foram cadastrados no banco de dados.
	 * 
	 * @return Um arrayList com os objetos em caso de sucesso ou null caso contr�rio.
	 * */
	public abstract ArrayList<Pizzaria>listarObjetos();
	
	
	/**
	 * Deleta os dados de um objeto Pizzaria no banco de dados.
	 * 
	 * @param nome(descri��o) do objeto que deseja deletar os dados.
	 * 
	 * @return true caso deletou com sucesso, false caso contr�rio.
	 * */
	public abstract boolean removeBd(String descricao);
	
	
	/**
	 * Deleta os dados de um objeto Pizzaria no banco de dados.
	 * 
	 * @param codigo do objeto que deseja deletar os dados.
	 * 
	 * @return true caso deletou com sucesso, false caso contr�rio.
	 * */
	public abstract boolean removeBd(int codigo);
	
	
	/** Altera um objeto que est� no banco de dados.
	 * 
	 *  @param obj do tipo <code>Pizzaria</code> com as novas informa��es.
	 *  @param descricao do objeto antigo.
	 *  
	 *  
	 *  @return true se a opera��o ocorreu com sucesso, false caso contr�rio.
	 *  */
	public abstract boolean alterarBd(Pizzaria obj, String descricao);
	
	
	/** Altera um objeto que est� no banco de dados.
	 * 
	 *  @param obj do tipo <code>Pizzaria</code> com as novas informa��es.
	 *  @param codigo do objeto.
	 *  
	 *  
	 *  @return true se a opera��o ocorreu com sucesso, false caso contr�rio.
	 *  */
	public abstract boolean alterarBd(Pizzaria obj, int codigo);
	
	
	/**Fecha a conex�o com o banco de dados. */
	public boolean fechaConexao(){
		
		if(connection != null){
			try {
				connection.close();
			} 
			
			catch (SQLException e) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}// fechaConexao()
	
}// Fim da class DataBase
