package udesc.cct.poo.passagem.modelos;

public class Usuario {
	public String nome;
	public String senha;
	public String salt;
	
	public Usuario(String nome, String salt, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.salt = salt;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public String getSalt() {
		return this.salt;
	}

}
