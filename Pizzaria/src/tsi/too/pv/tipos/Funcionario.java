package tsi.too.pv.tipos;

/**
 * Esta classe molda objetos do tipo Funcionário.
 * 
 * @author Vítor Caio De Paula
 * @since 1.0
 * */
public class Funcionario implements Pizzaria{
	
	private String nomeUsuario, senha;
	private char tipoUsuario;
	
	public Funcionario() {
		
	}

	public Funcionario(String nomeUsuario, String senha, char tipoUsuario) {
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public char getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "Funcionario \nNome do usuário: " + nomeUsuario + "\nSenha: " + senha + 
				"\nTipo do usuário: " + tipoUsuario;
	}

}
