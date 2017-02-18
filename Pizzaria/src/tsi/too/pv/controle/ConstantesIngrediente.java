package tsi.too.pv.controle;

public enum ConstantesIngrediente {
	
	// ************** Constantes relacionadas a Ingredientes **************
			TITULO_INGREDIENTE_INSERIR("Cadastro - Ingrediente"),
			TITULO_INGREDIENTE_LISTAR("Lista - Ingrediente"),
			TITULO_INGREDIENTE_CONSULTA("Consulta - Ingrediente"),
			TITULO_INGREDIENTE_EXCLUIR("Excluir - Ingrediente"),
			TITULO_INGREDIENTE_ALTERAR("Alterar - Ingrediente");
		// ***********************************************************************
			
			
			private final String valor;
			
			ConstantesIngrediente(String valor){
				this.valor = valor;
			}
			
			public String valor(){
				
				return this.valor;
				
			}

}
