package tsi.too.pv.bd;

public enum ConstantesBd {
	
	// ************** Constantes relacionadas ao Banco de dados **************
	TITULO_CONNECTION_FACTORY("Connection Factory"),
	ERRO_CONSULTA_BANCO_DE_DADOS("Erro - Consulta ao banco de dados"),
	ERRO_LISTAR_BANCO_DE_DADOS("Erro - Listar Banco de dados"),
	ERRO_ALTERAR_BANCO_DE_DADOS("Erro - Alterar Banco de dados"),
	ERRO_EXCLUIR_BANCO_DE_DADOS("Erro - Excluir Banco de dados"),
	ERRO_FECHAR_BANCO_DE_DADOS("Erro - Fechar banco de dados"),
	TITULO_FUNCIONARIO_INSERIR_BD("Cadastrar - Funcionário Banco de Dados"),
	TITULO_ClIENTE_INSERIR_BD("Cadastrar - Cliente Banco de Dados"),
	TITULO_PIZZA_INSERIR_BD("Cadastrar - Pizza Banco de Dados"),
	TITULO_INGREDIENTE_INSERIR_BD("Cadastrar - Ingrediente Banco de Dados");
	// ***********************************************************************
	
	
	private final String valor;
	
	ConstantesBd(String valor){
		this.valor = valor;
	}
	
	public String valor(){
		
		return this.valor;
		
	}

}
