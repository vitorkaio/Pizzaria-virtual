package tsi.too.pv.tipos;

/**
 * Esta classe molda objetos do tipo Ingrediente.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */

public class Ingrediente implements Pizzaria{
	
	private int codigo;
	private String descricao;
	private float preco;
	
	public Ingrediente(){
		
	}

	public Ingrediente(int codigo, String descricao, float preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Ingrediente(String descricao, float preco) {
		super();
		this.descricao = descricao;
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "\n********** Ingrediente **********" + "\nDescrição: " + descricao + 
				"\nPreço: R$ " + preco;
	}

}
