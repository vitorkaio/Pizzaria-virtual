package tsi.too.pv.controle;

public enum ConstantesPizzas {
	
	// ************** Constantes relacionadas a Pizzas **************
		TITULO_PIZZA_INSERIR("Cadastro - Pizza"),
		TITULO_PIZZA_LISTAR("Lista - Pizza"),
		TITULO_PIZZA_CONSULTA("Consulta - Pizza"),
		TITULO_PIZZA_EXCLUIR("Excluir - Pizza"),
		TITULO_PIZZA_ALTERAR("Alterar - Pizza");
	// ***********************************************************************
		
		
		private final String valor;
		
		ConstantesPizzas(String valor){
			this.valor = valor;
		}
		
		public String valor(){
			
			return this.valor;
			
		}

}
