package tsi.too.pv.controle;

public enum ConstantesClientes {
	
	// ************** Constantes relacionadas ao CLIENTE **************
		TITULO_CLIENTE_INSERIR("Cadastro - Cliente"),
		TITULO_CLIENTE_LISTAR("Lista - Cliente"),
		TITULO_CLIENTE_CONSULTA("Consulta - Cliente"),
		TITULO_CLIENTE_EXCLUIR("Excluir - Cliente"),
		TITULO_CLIENTE_ALTERAR("Alterar - Cliente");
	// ***********************************************************************
		
		
		private final String valor;
		
		ConstantesClientes(String valor){
			this.valor = valor;
		}
		
		public String valor(){
			
			return this.valor;
			
		}

}
