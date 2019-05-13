package udesc.cct.poo.passagem.controle;

import java.util.Scanner;
import udesc.cct.poo.passagem.servicos.ServicoDeLogin;
import udesc.cct.poo.passagem.modelos.Usuario;
import udesc.cct.poo.passagem.controle.ControleGeral;

public class ControleDeLogin {
	public final static int LOGAR = 2;
	public final static int CRIAR_LOGIN = 1;
	public final static int SAIR = 0;
	
	private Scanner scanner;
	
	private ServicoDeLogin servicoDeLogin;
	private Usuario usuario;
	
	public ControleDeLogin() {
		this.scanner = new Scanner(System.in);
		this.servicoDeLogin = new ServicoDeLogin();
		this.usuario = new Usuario(null, null, null);
	}
	
	public void login() {
		int escolha = -1;
		while (escolha != ControleDeLogin.SAIR) {
			System.out.println("Escolha algo:");
			System.out.println(ControleDeLogin.LOGAR+") Logar");
			System.out.println(ControleDeLogin.CRIAR_LOGIN+") Criar login");
			System.out.println(ControleDeLogin.SAIR+") Sair");
			escolha = this.scanner.nextInt();
			
			switch(escolha) {
			case ControleDeLogin.CRIAR_LOGIN:
				this.criaUsuario();
				break;
			case ControleDeLogin.LOGAR:
				this.verificaUsuario();
				break;
			case ControleDeLogin.SAIR:
				System.out.println("Programa Finalizado");
				break;
			}
		}
	}
	
	private void verificaUsuario() {
		System.out.println("Usuario: ");
		this.usuario.nome = this.scanner.next();
		System.out.println("Senha: ");
		this.usuario.senha = this.scanner.next();
		if (this.servicoDeLogin.verificaUsuario(this.usuario)) {
			ControleGeral controleGeral = new ControleGeral();
			controleGeral.iniciar();
		} else {
			System.out.println("Usuario ou senha incorretos");
		}
		
	}
	
	private void criaUsuario() {
		System.out.println("Usuario: ");
		this.usuario.nome = this.scanner.next();
		System.out.println("Senha: ");
		this.usuario.senha = this.scanner.next();
		if (this.servicoDeLogin.gravaUsuario(this.usuario)) {
			System.out.println("Usuario criado com sucesso");
		} else {
			System.out.println("Usuario já existe");
		}

	}

}
