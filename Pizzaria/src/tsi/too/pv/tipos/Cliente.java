package tsi.too.pv.tipos;


/**
 * Modela a classe para os dados do cliente.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class Cliente implements Pizzaria{
	
	private String cpf, nome, logradouro, complemento, bairro, cidade, telefoneFixo, telefoneMovel;
	private int numero;
	
	public Cliente() {
		
	}

	public Cliente(String cpf, String nome, String logradouro,
			String complemento, String bairro, String cidade,
			String telefoneFixo, String telefoneMovel, int numero) {
		
		
		this.cpf = cpf;
		this.nome = nome;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.telefoneFixo = telefoneFixo;
		this.telefoneMovel = telefoneMovel;
		this.numero = numero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneMovel() {
		return telefoneMovel;
	}

	public void setTelefoneMovel(String telefoneMovel) {
		this.telefoneMovel = telefoneMovel;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Cliente \nCpf: " + cpf + "\nNome: " + nome + "\nLogradouro: "
				+ logradouro + "\nCidade: " + cidade + "\nBairro: " + bairro + 
				"\nNúmero: " + numero + "\nComplemento: " + complemento + "\nTelefone fixo: "
				+ telefoneFixo + "\nTelefone Móvel: " + telefoneMovel;
	}
	
	

}
