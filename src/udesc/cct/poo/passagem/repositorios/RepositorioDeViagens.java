package udesc.cct.poo.passagem.repositorios;

import java.util.ArrayList;

import udesc.cct.poo.passagem.modelos.Assento;
import udesc.cct.poo.passagem.modelos.Local;
import udesc.cct.poo.passagem.modelos.Parada;
import udesc.cct.poo.passagem.modelos.Viagem;

public class RepositorioDeViagens{
    private ArrayList<Viagem> viagens;

    public RepositorioDeViagens(){
        this.viagens = new ArrayList<Viagem>();

        Viagem v = this.criarViagemConvencional();
        Local l1 = new Local("Joinville");
        Parada x1 = new Parada(l1,10,10);
        v.addParada(x1);

        Local l2 = new Local("Garuva");
        Parada x2 = new Parada(l2,10,40);
        v.addParada(x2);

        Local l3 = new Local("Curitiba");
        Parada x3 = new Parada(l3,12,20);
        v.addParada(x3);

        this.viagens.add(v);

        Viagem v2 = this.criarViagemConvencional();
        Parada x6 = new Parada(l1,11,10);
        v2.addParada(x6);

        Parada x4 = new Parada(l2,11,40);
        v2.addParada(x4);

        Parada x5 = new Parada(l3,13,20);
        v2.addParada(x5);

        this.viagens.add(v2);
        
        Viagem v3 = this.criarViagemLeito(20);
        Local l4 = new Local("Florianópolis");
        Parada x7 = new Parada(l4, 10, 00);
        v3.addParada(x7);
        
        Parada x8 = new Parada(l1,12,40);
        v3.addParada(x8);
        
        Parada x9 = new Parada(l3,14,50);
        v3.addParada(x9);
        
        this.viagens.add(v3);
        
        Viagem v4 = this.criarViagemMista(0, 10, 30);
        Local l5 = new Local("Lages");
        Parada x10 = new Parada(l5,8,00);
        v4.addParada(x10);
        
        Parada x11 = new Parada(l4,11,30);
        v4.addParada(x11);
        
        this.viagens.add(v4);
        
        Viagem v5 = this.criarViagemConvencional();
        Local l6 = new Local("Jaraguá do Sul");
        Parada x12 = new Parada(l6,7,00);
        v5.addParada(x12);

        Parada x13 = new Parada(l1,7,40);
        v5.addParada(x13);
        
        Parada x14 = new Parada(l3,9,40);
        v5.addParada(x14);

        this.viagens.add(v5);
        
        Viagem v6 = this.criarViagemConvencional();
        Parada x15 = new Parada(l6,8,00);
        v6.addParada(x15);
        
        Parada x16 = new Parada(l1,8,40);
        v6.addParada(x16);
        
        Parada x17 = new Parada(l3,10,40);
        v6.addParada(x17);
        
        this.viagens.add(v6);
        
        Viagem v7 = this.criarViagemConvencional();
        Parada x18 = new Parada(l6,9,00);
        v7.addParada(x18);
        
        Parada x19 = new Parada(l1,9,40);
        v7.addParada(x19);
        
        Parada x20 = new Parada(l3,11,40);
        v7.addParada(x20);
        
        this.viagens.add(v7);
        
        Viagem v8 = this.criarViagemConvencional();
        Parada x21 = new Parada(l6,10,00);
        v8.addParada(x21);
        
        Parada x22 = new Parada(l1,10,35);
        v8.addParada(x22);
        
        Parada x23 = new Parada(l3,12,35);
        v8.addParada(x23);
        
        this.viagens.add(v8);
        
        Viagem v9 = this.criarViagemConvencional();
        Parada x24 = new Parada(l6,11,00);
        v9.addParada(x24);
        
        Parada x25 = new Parada(l1,11,40);
        v9.addParada(x25);
        
        Parada x26 = new Parada(l3,13,40);
        v9.addParada(x26);
        
        this.viagens.add(v9);

    }
    public ArrayList<Viagem> getTodasAsViagens(){
        return this.viagens;
    }

    public ArrayList<Viagem> getViagensPorOrigemDestino(String origem, String destino){
        ArrayList<Viagem> selecionadas = new ArrayList<Viagem>();

        for(int i = 0;i<this.viagens.size();i++){
            Viagem viagem = this.viagens.get(i);
            boolean passaPelaOrigem = viagem.passaPor(origem);
            boolean passaPeloDestino = viagem.passaPor(destino);
            if(passaPelaOrigem && passaPeloDestino){
                selecionadas.add(viagem);
            }
        }
        return selecionadas;
    }
    private Viagem criarViagemConvencional(){
    	Viagem v = new Viagem();
    	 for(int i =0;i<42;i++){
             Assento a = new Assento(Assento.CONVENCIONAL, i+1);
             v.addAssento(a);
         }

    	return v;
    }
    
    private Viagem criarViagemLeito(int n) {
    	Viagem v = new Viagem();
    	for(int i=0;i<n;i++) {
    		Assento a = new Assento(Assento.LEITO, i+1);
    		v.addAssento(a);
    	}
    	return v;
    }
    
    private Viagem criarViagemSemileito(int n) {
    	Viagem v = new Viagem();
    	for (int i=0;i<n;i++) {
    		Assento a = new Assento(Assento.SEMILEITO, i+1);
    		v.addAssento(a);
    	}
    	return v;
    }
    
    private Viagem criarViagemMista(int c, int l, int s) {
    	Viagem v = new Viagem();
    	int cont = 0;
    	for(int i = 0; i<c;i++) {
    		Assento a = new Assento(Assento.CONVENCIONAL, cont+1);
    		v.addAssento(a);
    		cont++;
    	}
    	for(int i=0;i<l;i++) {
    		Assento a = new Assento(Assento.LEITO,cont+1);
    		v.addAssento(a);
    		cont++;
    	}
    	for(int i=0;i<s;i++) {
    		Assento a = new Assento(Assento.SEMILEITO,cont+1);
    		v.addAssento(a);
    		cont++;
    	}
    	return v;
    }
    
    
}
