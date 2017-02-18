package tsi.too.pv.tipos;

import java.util.Calendar;

import tsi.too.pv.entradas.EntradaESaida;

/**
 * Esta classe molda objetos do tipo Funcionário.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class Pizza implements Pizzaria{
	
	private int codigo;
	private String descricao;
	private char tamanho;
	private Calendar data;
	private float preco;
	
	public Pizza(){
		
	}
	
	public Pizza(int codigo, String descricao){
		
		this.codigo = codigo;
		this.descricao = descricao;
		
	}

	public Pizza(String descricao, char tamanho, Calendar data, float preco) {
		this.descricao = descricao;
		this.tamanho = tamanho;
		this.data = data;
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

	public char getTamanho() {
		return tamanho;
	}

	public void setTamanho(char tamanho) {
		this.tamanho = tamanho;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "\n*************** Pizza ***************" + "\nDescrição: " + 
				EntradaESaida.removerSep(descricao, "-") +  "\nTamanho: " + tamanho + "\nData: " 
				+ EntradaESaida.dataToString(data) + "\nPreço: R$ " + preco;
	}
	
}
