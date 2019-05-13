package udesc.cct.poo.passagem.repositorios;
import java.util.ArrayList;

import udesc.cct.poo.passagem.helpers.LeitorDeArquivo;
import udesc.cct.poo.passagem.modelos.Usuario;
import udesc.cct.poo.passagem.helpers.HashUtil;
import java.util.Calendar;

public class RepositorioDeLogins {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	public RepositorioDeLogins() {
		LeitorDeArquivo leitor = new LeitorDeArquivo("data/users.csv");
		ArrayList<String> linhas = leitor.ler();
		
		for(int i = 0; i < linhas.size(); i++) {
			String nome = linhas.get(i);
			String salt = linhas.get(i);
			String senha = linhas.get(i);
			Usuario u = new Usuario(nome,salt,senha);
			usuarios.add(u);
		}
	}
	
	public ArrayList<Usuario> getTodosOsUsuarios(){
		return this.usuarios;
	}
	
	public void gravaUsuario(Usuario usuario) {
		LeitorDeArquivo leitor = new LeitorDeArquivo("data/users.csv");
		usuario.salt = (Calendar.getInstance().getTime().toString() + "@#!*");
		usuario.senha = HashUtil.stringParaMD5(usuario.senha + usuario.salt);//+usuario.salt

		leitor.gravar(usuario);
	}
	
	public ArrayList<Usuario> lerUsuarios() {
		LeitorDeArquivo leitor = new LeitorDeArquivo("data/users.csv");
		return leitor.lerUsuarios();
		
	}
}
