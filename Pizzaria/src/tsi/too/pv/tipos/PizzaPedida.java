package tsi.too.pv.tipos;

/**
 * Modela a classe para os dados do PizzaPedida.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class PizzaPedida implements Pizzaria{
	
	private int numeroPedido, codigoPizza, quantidade;
	private String borda;
	
	public PizzaPedida(int numeroPedido, int codigoPizza, int quantidade, String borda) {
		this.numeroPedido = numeroPedido;
		this.codigoPizza = codigoPizza;
		this.quantidade = quantidade;
		this.borda = borda;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public int getCodigoPizza() {
		return codigoPizza;
	}

	public void setCodigoPizza(int codigoPizza) {
		this.codigoPizza = codigoPizza;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getBorda() {
		return borda;
	}

	public void setBorda(String borda) {
		this.borda = borda;
	}

	@Override
	public String toString() {
		return "Pizza Pedida \nNúmero do Pedido: " + numeroPedido + "\nCódigo da Pizza: " + codigoPizza + 
				"\nQuantidade: " + quantidade + "\nBorda: " + borda;
	}

}
