package tsi.too.pv.controle;

public enum ConstantesFuncionarios {
	
	// ************** Constantes relacionadas ao Funcionario **************
		TITULO_FUNCIONARIO_INSERIR("Cadastro - Funcionário"),
		TITULO_FUNCIONARIO_LISTAR("Lista - Funcionário"),
		TITULO_FUNCIONARIO_CONSULTA("Consulta - Funcionário"),
		TITULO_FUNCIONARIO_EXCLUIR("Excluir - Funcionário"),
		TITULO_FUNCIONARIO_ALTERAR("Alterar - Funcionário");
	// ***********************************************************************
		
		
		private final String valor;
		
		ConstantesFuncionarios(String valor){
			this.valor = valor;
		}
		
		public String valor(){
			
			return this.valor;
			
		}

}
