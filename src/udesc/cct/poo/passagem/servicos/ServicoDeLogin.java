package udesc.cct.poo.passagem.servicos;
import java.util.ArrayList;

import udesc.cct.poo.passagem.modelos.Usuario;
import udesc.cct.poo.passagem.repositorios.RepositorioDeLogins;
import udesc.cct.poo.passagem.helpers.HashUtil;

public class ServicoDeLogin {
	private RepositorioDeLogins repositorioDeLogins;
	
	public ServicoDeLogin() {
		this.repositorioDeLogins = new RepositorioDeLogins();
	}
	
	public ArrayList<Usuario> getTodosOsUsuarios(){
		return this.repositorioDeLogins.getTodosOsUsuarios();
	}
	
	
	private String pegaSaltUsuario(String usuario) {
		ArrayList<Usuario> usuarios = this.repositorioDeLogins.lerUsuarios();
		
		for ( int i = 0; i < usuarios.size() ; i++) {
			if (usuarios.get(i).nome.compareTo(usuario) == 0) {
				return usuarios.get(i).salt;
			}
		}
		
		return "erro";
	}
	
	public boolean verificaUsuario(Usuario usuario) {
		ArrayList<Usuario> usuarios = this.repositorioDeLogins.lerUsuarios();
		String salt = pegaSaltUsuario(usuario.nome);
		usuario.senha = HashUtil.stringParaMD5(usuario.senha + salt);
		return usuarios.stream()
				.filter(u -> u.getNome().equals(usuario.nome))
				.filter(u -> u.getSenha().equals(usuario.senha))
				.findFirst()
				.isPresent();
	}

	public boolean gravaUsuario(Usuario usuario) {
		ArrayList<Usuario> usuarios = this.repositorioDeLogins.lerUsuarios();
		if (usuarios.stream().filter(u -> u.getNome().equals(usuario.nome)).findFirst().isPresent()) {
			return false;
		} else {
			this.repositorioDeLogins.gravaUsuario(usuario);
			return true;
		}

	}
}
