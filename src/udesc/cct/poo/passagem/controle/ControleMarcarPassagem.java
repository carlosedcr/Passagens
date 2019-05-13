package udesc.cct.poo.passagem.controle;
import java.util.ArrayList;
import java.util.Scanner;

import udesc.cct.poo.passagem.modelos.Assento;
import udesc.cct.poo.passagem.modelos.Local;
import udesc.cct.poo.passagem.modelos.Parada;
import udesc.cct.poo.passagem.modelos.Viagem;
import udesc.cct.poo.passagem.servicos.ServicoDeLocais;
import udesc.cct.poo.passagem.servicos.ServicoDePassagens;
import udesc.cct.poo.passagem.servicos.ServicoDeViagens;

public class ControleMarcarPassagem{

    private Scanner scanner;
    private ServicoDeLocais servicoDeLocais;
    private ServicoDeViagens servicoDeViagens;
    private ServicoDePassagens servicoDePassagens;

    public ControleMarcarPassagem(Scanner scanner, ServicoDeLocais servicoDeLocais, ServicoDeViagens servicoDeViagens, ServicoDePassagens servicoDePassagens){
        this.scanner = scanner;
        this.servicoDeLocais = servicoDeLocais;
        this.servicoDeViagens = servicoDeViagens;
        this.servicoDePassagens = servicoDePassagens;
    }

    public void iniciar(){
        Local origem = this.escolherOrigem();
        Local destino = this.escolherDestino();
        Viagem escolhida = this.acharUmaViagemPorOrigemDestino(origem, destino);
        Assento assento = this.escolherAssento(escolhida);
        assento.reservar();

        ArrayList<Assento> assentos = escolhida.getAssentos();
        this.listarAssentos(assentos);
    }

    private Viagem acharUmaViagemPorOrigemDestino(Local origem, Local destino){
        ArrayList<Viagem> viagens = servicoDeViagens.getTodasAsViagensPorOrigemDestino(origem, destino);
        listarViagens(viagens, origem, destino);
        System.out.println("escolha um Onibus:");
        int viagensIdx = this.scanner.nextInt();
        Viagem escolhida = viagens.get(viagensIdx-1);

        return escolhida;
    }

    private void listarLocais(ArrayList<Local> locais){

        for(int i =0;i<locais.size();i++){
            int idx = i+1;
            Local l = locais.get(i);
            String nome = l.getNome();
            System.out.println(idx+") "+nome);
        }
    }

    private void listarViagens(ArrayList<Viagem> viagens, Local origem, Local destino){

        for(int i =0;i<viagens.size();i++){
            int idx = i+1;
            Viagem v = viagens.get(i);
            Parada embarque = v.getParadaPorNome(origem.getNome());
            Parada desembarque = v.getParadaPorNome(destino.getNome());
            System.out.println(idx+") "+embarque.getInfo()+" "+desembarque.getInfo());
        }
    }

    private void listarAssentos(ArrayList<Assento> assentos){
    	System.out.printf("%3s%5s%3s", "  nº |", "  Livre |", "   Tipo       |");
    	System.out.printf("%3s%5s%3s\n", "  nº |", "  Livre |", "   Tipo       |");
    	System.out.printf("%s%s%s", "-----|","--------|", "--------------|");
    	System.out.printf("%s%s%s\n", "-----|","--------|", "--------------|");
        for(int i =0;i<assentos.size();i++){
            Assento a = assentos.get(i);
            boolean desocupado = a.estaDesocupado();
            String tipo = "";
            if (a.getTipo() == Assento.CONVENCIONAL) {
            	tipo = "Convencional";
            }
            if (a.getTipo() == Assento.LEITO) {
            	tipo = "Leito";
            }
            if (a.getTipo() == Assento.SEMILEITO) {
            	tipo = "Semileito";
            }
            if (a.getNumero() % 2 == 0 && desocupado == true) {            	
            	System.out.printf("%3d%s%5s%4s%12s%s\n", a.getNumero(), ") | ", desocupado," | ", tipo, " |");
            } else if (desocupado == true){
            	System.out.printf("%3d%s%5s%4s%12s%s", a.getNumero(), ") | ", desocupado," | ", tipo, " |");
            } else if (a.getNumero() % 2 ==0 && desocupado == false) {
            	System.out.println("");
            }

        }
    }

    private Local escolherOrigem(){
        ArrayList<Local> locais = servicoDeLocais.getTodosOsLocais();
        this.listarLocais(locais);
        System.out.println("escolha uma ORIGEM:");
        int origemIdx = this.scanner.nextInt();
        Local origem = locais.get(origemIdx-1);

        System.out.println("------------------------------------");
        System.out.println("ORIGEM:"+origem.getNome());
        System.out.println("------------------------------------");

        return origem;
    }

    private Local escolherDestino(){
        ArrayList<Local> locais = servicoDeLocais.getTodosOsLocais();
        this.listarLocais(locais);
        System.out.println("escolha um DESTINO:");
        int destinoIdx = this.scanner.nextInt();
        Local destino = locais.get(destinoIdx-1);

        System.out.println("------------------------------------");
        System.out.println("DESTINO:"+destino.getNome());
        System.out.println("------------------------------------");

        return destino;
    }

    private Assento escolherAssento(Viagem viagem){
        ArrayList<Assento> assentos = viagem.getAssentos();
        this.listarAssentos(assentos);
        System.out.println("escolha um assento:");
        int assentoIdx = this.scanner.nextInt();
        Assento assento = assentos.get(assentoIdx-1);

        return assento;
    }
}
