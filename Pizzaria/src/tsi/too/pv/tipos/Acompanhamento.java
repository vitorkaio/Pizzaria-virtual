package tsi.too.pv.tipos;

/**
 * Modela a classe para os dados do acompanhamento.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class Acompanhamento implements Pizzaria{

	private int numeroPedido;
	private String tipo;
	private int quantidade;
	private float preco;
	
	public Acompanhamento() {
	}

	public Acompanhamento(int numeroPedido, String tipo, int quantidade, float preco) {
		this.numeroPedido = numeroPedido;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "\n********** Acompanhamento **********" + "\nTipo: " + tipo + 
				"\nQuantidade: " + quantidade + "\nPreço: R$ " + preco;
	}
	
}
