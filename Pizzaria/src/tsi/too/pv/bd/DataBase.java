package tsi.too.pv.bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tsi.too.pv.tipos.Pizzaria;

/** Classe que define as operações para manipulação no banco de dados. */
public abstract class DataBase {

	/** Campo que faz a conexão com banco de dados. */
	protected Connection connection;
	
	public void abreConexao(){
		
		this.connection = new tsi.too.pv.bd.ConnectionFactory().getConnection();
		
	}// abreConexao()
	
	/**
	 * Insere um objeto do tipo Pizzaria no banco de dados.
	 * 
	 * @param obj um objeto que será inserido no banco de dados.
	 * 
	 * @return <code>true</code> se a inserção foi feita com sucesso, <code>false</code> caso contrário.
	 * */
	public abstract boolean insereBd(Pizzaria obj);
	
	
	/** 
	 * Pesquisa se o nome fornecido já está presente no banco de dados.
	 * 
	 * @param nome a ser pesquisado no banco de dados.
	 * 
	 * @return true se o nome já existe, false caso contrário
	 * */
	public abstract boolean pesquisaNomeBd(String nome);
	
	
	/**
	 * Obtém através do nome(descricação), um objeto Pizzaria do banco de dados.
	 * 
	 * @param descrição do objeto que será pesquisado.
	 * 
	 * @return objeto em caso de sucesso ou null caso contrário.
	 * */
	public abstract Pizzaria obtemObjeto(String descricao);
	
	
	/**
	 * Obtém através do codigo, um objeto Pizzaria do banco de dados.
	 * 
	 * @param codigo do objeto que será pesquisado.
	 * 
	 * @return objeto em caso de sucesso ou null caso contrário.
	 * */
	public abstract Pizzaria obtemObjeto(int codigo);
	
	
	/**
	 * Pega o último código cadastrado na tabela.
	 * 
	 * @return cod do último objeto cadastrado, -1 caso contrário. 
	 * 
	 */
	public abstract int ultimoCodigo();
	
	
	/**
	 * Este método retorna todos os objetos que foram cadastrados no banco de dados.
	 * 
	 * @return Um arrayList com os objetos em caso de sucesso ou null caso contrário.
	 * */
	public abstract ArrayList<Pizzaria>listarObjetos();
	
	
	/**
	 * Deleta os dados de um objeto Pizzaria no banco de dados.
	 * 
	 * @param nome(descrição) do objeto que deseja deletar os dados.
	 * 
	 * @return true caso deletou com sucesso, false caso contrário.
	 * */
	public abstract boolean removeBd(String descricao);
	
	
	/**
	 * Deleta os dados de um objeto Pizzaria no banco de dados.
	 * 
	 * @param codigo do objeto que deseja deletar os dados.
	 * 
	 * @return true caso deletou com sucesso, false caso contrário.
	 * */
	public abstract boolean removeBd(int codigo);
	
	
	/** Altera um objeto que está no banco de dados.
	 * 
	 *  @param obj do tipo <code>Pizzaria</code> com as novas informações.
	 *  @param descricao do objeto antigo.
	 *  
	 *  
	 *  @return true se a operação ocorreu com sucesso, false caso contrário.
	 *  */
	public abstract boolean alterarBd(Pizzaria obj, String descricao);
	
	
	/** Altera um objeto que está no banco de dados.
	 * 
	 *  @param obj do tipo <code>Pizzaria</code> com as novas informações.
	 *  @param codigo do objeto.
	 *  
	 *  
	 *  @return true se a operação ocorreu com sucesso, false caso contrário.
	 *  */
	public abstract boolean alterarBd(Pizzaria obj, int codigo);
	
	
	/**Fecha a conexão com o banco de dados. */
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
