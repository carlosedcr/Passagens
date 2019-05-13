package udesc.cct.poo.passagem.helpers;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//
import java.io.FileWriter;
import java.io.PrintWriter;
import udesc.cct.poo.passagem.modelos.Usuario;


public class LeitorDeArquivo{
    private String caminho;

    public LeitorDeArquivo(String caminho){
        this.caminho = caminho;
    }
    public ArrayList<String> ler(){

        ArrayList<String> linhas = new ArrayList<String>();

        try {
          FileReader arq = new FileReader(this.caminho);
          BufferedReader lerArq = new BufferedReader(arq);
          String linha = lerArq.readLine();

          while (linha != null) {
              linhas.add(linha);
              linha = lerArq.readLine();
          }
          arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
            System.exit(0);
        }

        return linhas;
    }
    //
    public ArrayList<Usuario> lerUsuarios(){
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    	
    	try {
			FileReader arq = new FileReader(this.caminho);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			
			while ((linha = lerArq.readLine()) != null) {
				String[] tokens = linha.split(",");
				usuarios.add(new Usuario(tokens[0], tokens[1], tokens[2]));
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
            System.exit(0);
		}
    	return usuarios;
    	
    }
        
    public void gravar(Usuario usuario) {
    	try {
			FileWriter arq = new FileWriter(this.caminho,true);
			PrintWriter out = new PrintWriter(arq);
			
			out.print(usuario.nome);
			out.print(',');
			out.print(usuario.salt);
			out.print(',');
			out.print(usuario.senha);
			out.print('\n');
			out.flush();
			out.close();
			arq.close();
		} catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
            System.exit(0);
		}
    }
    

}
