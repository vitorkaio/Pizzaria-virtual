package tsi.too.pv.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

import tsi.too.pv.entradas.EntradaESaida;

/**
 * Permite abrir uma conex�o com o banco de dados sqlite 3.
 * 
 * @author V�tor Caio De Paula
 * @since 1.0
 * 
 * */
public class ConnectionFactory {
	
	/**
	 * Este m�todo retorna uma conex�o com o jdbc sqlite 3.
	 * 
	 * @return retorna um objeto do tipo <code>Connection</code> com uma conex�o com sqlite 3. 
	 * */
	public Connection getConnection(){

		try{

			SQLiteConfig config = new SQLiteConfig();  
	        config.enforceForeignKeys(true);  
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:pv.db", config.toProperties());

		}

		catch(SQLException ex){

			EntradaESaida.msgErro("Erro ao abrir uma conex�o com o banco de dados", 
					ConstantesBd.TITULO_CONNECTION_FACTORY.valor());
			
			return null;

		}

		catch(ClassNotFoundException ex){

			EntradaESaida.msgErro("Erro ao abrir uma conex�o com o banco de dados", 
					ConstantesBd.TITULO_CONNECTION_FACTORY.valor());
			
			return null;

		}

	}

}
