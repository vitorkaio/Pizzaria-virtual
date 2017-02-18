package tsi.too.pv.controle;

public enum ConstantesFuncionarios {
	
	// ************** Constantes relacionadas ao Funcionario **************
		TITULO_FUNCIONARIO_INSERIR("Cadastro - Funcion�rio"),
		TITULO_FUNCIONARIO_LISTAR("Lista - Funcion�rio"),
		TITULO_FUNCIONARIO_CONSULTA("Consulta - Funcion�rio"),
		TITULO_FUNCIONARIO_EXCLUIR("Excluir - Funcion�rio"),
		TITULO_FUNCIONARIO_ALTERAR("Alterar - Funcion�rio");
	// ***********************************************************************
		
		
		private final String valor;
		
		ConstantesFuncionarios(String valor){
			this.valor = valor;
		}
		
		public String valor(){
			
			return this.valor;
			
		}

}
