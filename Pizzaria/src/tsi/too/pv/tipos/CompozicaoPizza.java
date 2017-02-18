package tsi.too.pv.tipos;

public class CompozicaoPizza implements Pizzaria{
	
	private int codigoPizza;
	int codigoIngrediente;

	public CompozicaoPizza(){
	}

	public CompozicaoPizza(int codigoPizza, int codigoIngrediente) {
		this.codigoPizza = codigoPizza;
		this.codigoIngrediente = codigoIngrediente;
	}

	public int getCodigoPizza() {
		return codigoPizza;
	}

	public void setCodigoPizza(int codigoPizza) {
		this.codigoPizza = codigoPizza;
	}

	public int getCodigoIngrediente() {
		return codigoIngrediente;
	}

	public void setCodigoIngrediente(int codigoIngrediente) {
		this.codigoIngrediente = codigoIngrediente;
	}
	

}// Fim da class CompozicaoPizza
