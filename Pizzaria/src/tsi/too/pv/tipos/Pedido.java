package tsi.too.pv.tipos;

import java.util.Calendar;

import tsi.too.pv.entradas.EntradaESaida;

/**
 * Modela a classe para os dados do Pedido.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class Pedido implements Pizzaria{
	
	private int numeroPedido;
	private String cpf, situacao, formaDePagamento;
	private Calendar dataHora;
	private String hora;
	private float preco;
	
	public Pedido(){
		
	}
	
	public Pedido(int numeroPedido, String cpf, String situacao, String formaDePagamento, Calendar dataHora,
			String hora, float preco) {
		this.numeroPedido = numeroPedido;
		this.cpf = cpf;
		this.situacao = situacao;
		this.formaDePagamento = formaDePagamento;
		this.dataHora = dataHora;
		this.hora = hora;
		this.preco = preco;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Calendar getDataHora() {
		return dataHora;
	}
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "*************** Pedido *************** \nNúmero do Pedido: " + numeroPedido + "\nCpf: " 
				+ cpf + "\nSituação: " + situacao
				+ "\nForma de Pagamento: " + formaDePagamento + "\nData: " 
				+ EntradaESaida.dataToString(dataHora)
				+ "\nHora: " + hora +"\nPreço: " + preco;
	}

}
