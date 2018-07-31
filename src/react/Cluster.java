package react;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cluster {

	private ArrayList<Pessoa> Melancolico;
	private ArrayList<Pessoa> Instavel;
	private ArrayList<Pessoa> Amorfo;
	private ArrayList<Pessoa> Apatico;
	private ArrayList<Pessoa> Social;
	private ArrayList<Pessoa> Fleumatico;
	private ArrayList<Pessoa> Ativo;
	private ArrayList<Pessoa> Lider;
	
        
        private int qndPessoas = 0;
	
	public Cluster() {
		this.Melancolico = new ArrayList<>();
		this.Instavel = new ArrayList<>();
		this.Amorfo = new ArrayList<>();
		this.Apatico = new ArrayList<>();
		this.Social = new ArrayList<>();
		this.Fleumatico = new ArrayList<>();
		this.Ativo = new ArrayList<>();
		this.Lider = new ArrayList<>();
	}


	public void receberPessoa(Pessoa novaPessoa) throws Exception {
		String tipo = novaPessoa.getTipo();
		if(tipo.equals("nEnAS")) {
                        novaPessoa.classificacao = EnumClassificacao.APATICO;
			this.Apatico.add(novaPessoa);
		}else if(tipo.equals("nEnAP")) {
                        novaPessoa.classificacao = EnumClassificacao.AMORFO;
			this.Amorfo.add(novaPessoa);
		}else if(tipo.equals("nEAP")) {
                        novaPessoa.classificacao = EnumClassificacao.SOCIAL;
			this.Social.add(novaPessoa);
		}else if(tipo.equals("nEAS")) {
                        novaPessoa.classificacao = EnumClassificacao.FLEUMATICO;
			this.Fleumatico.add(novaPessoa);
		}else if(tipo.equals("EnAP")) {
                        novaPessoa.classificacao = EnumClassificacao.INSTAVEL;
			this.Instavel.add(novaPessoa);
		}else if(tipo.equals("EAP")) {
                        novaPessoa.classificacao = EnumClassificacao.ATIVO;
			this.Ativo.add(novaPessoa);
		}else if(tipo.equals("EnAS")) {
                        novaPessoa.classificacao = EnumClassificacao.MELANCOLICO;
			this.Melancolico.add(novaPessoa);
		}else if(tipo.equals("EAS")) {
                    novaPessoa.classificacao = EnumClassificacao.LIDER;
			this.Lider.add(novaPessoa);
		}else {
			throw new Exception("Tipo inexistente");
		}
		
	}
	
	public void PrintArrays() {
		System.out.print("\n\n\nApatico: ");
		printUnicoArray(this.Apatico);
		System.out.println();
		
		System.out.print("Amorfo: ");
		printUnicoArray(this.Amorfo);
		System.out.println();
		
		System.out.print("Social: ");
		printUnicoArray(this.Social);
		System.out.println();
		
		System.out.print("Fleumatico: ");
		printUnicoArray(this.Fleumatico);
		System.out.println();
		

		System.out.print("Ativo: ");
		printUnicoArray(this.Ativo);
		System.out.println();
		
		
		System.out.print("Instavel: ");
		printUnicoArray(this.Instavel);
		System.out.println();
		
		System.out.print("Melancolico: ");
		printUnicoArray(this.Melancolico);
		System.out.println();
		
		System.out.print("Lider: ");
		printUnicoArray(this.Lider);
		System.out.println();
		
	}
	
	private void printUnicoArray(ArrayList<Pessoa> Arrayp) {
		int i;
                for(i = 0; i < Arrayp.size(); i++) {
			System.out.print("Nome: " + Arrayp.get(i).getNome() + " ");
		}
		System.out.println("quantidade = " + i);
	}
        
        public void setQuantidadePessoas(int a){
            this.qndPessoas = a;
        }
        
        public void agrupar(int quantidade_grupos){
            int qnt_lider = this.Lider.size();
            int qnt_social = this.Social.size();
            int qnt_ativo = this.Ativo.size();
            final int estimativaPessoasPorGrupo = qndPessoas/quantidade_grupos;
            
            
            ArrayList<ArrayList<Pessoa>> grupos = new ArrayList<ArrayList<Pessoa>>(quantidade_grupos);
            
            for(int i  = 0 ; i < quantidade_grupos; i ++){
                grupos.add(i, new ArrayList<Pessoa>(estimativaPessoasPorGrupo));
            }
            
            int k = 0, m = 0, i = 0;
            for(int j = 0; i < qnt_lider ; i++, j++, k++){          //lider
                if(k > quantidade_grupos - 1){
                    k = 0;
                    m++;
                }
                grupos.get(k).add(m, this.Lider.remove(i));
            }
            
            
            
            if(i < quantidade_grupos){
               for(int p = 0, j = 0; p < qnt_social ;p++, i++, j++, k++){         //social
                    if(i == quantidade_grupos ){
                        break;
                    }
                    if(k > quantidade_grupos - 1){
                        k = 0;
                        m++;
                    }
                    grupos.get(k).add(m, this.Social.remove(p));
                }
            }
            
            if(i < quantidade_grupos){
               for(int p = 0, j = 0; p < qnt_ativo ;p++, i++, j++, k++){         //ativo
                    if(i == quantidade_grupos ){
                        break;
                    }
                    if(k > quantidade_grupos - 1){
                        k = 0;
                        m++;
                    }
                    grupos.get(k).add(m, this.Ativo.remove(p));
                }
            }
            
            try {
                preecherGrupo(grupos, estimativaPessoasPorGrupo);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
             for( i  = 0 ; i < quantidade_grupos; i ++){            
                 printUnicoArray(grupos.get(i));
            }
            
        }
        
        private void preecherGrupo(ArrayList<ArrayList<Pessoa>> grupos, int estimativa) throws Exception{
            for(int i = 0; i < grupos.size(); i++ ){
                if(grupos.get(i).get(0).classificacao == EnumClassificacao.LIDER){
                    preencherCabecaLider();
                }else if(grupos.get(i).get(0).classificacao == EnumClassificacao.SOCIAL){
                    
                } else if(grupos.get(i).get(0).classificacao == EnumClassificacao.ATIVO){
                    
                }else {
                    throw new Exception("Centroide nao identificado");
                }
            }
        }
        
        private void preencherCabecaLider(){
            ArrayList<Pessoa> afins = new ArrayList<Pessoa>(this.Fleumatico.size()+ this.Melancolico.size() + this.Ativo.size());
            concatenar(afins,this.Fleumatico);
            concatenar(afins,this.Melancolico);
            concatenar(afins,this.Ativo);
            
            
            
            
            System.out.println("AQUIIIIIII");
            printUnicoArray(afins);
        }

    private void concatenar(ArrayList<Pessoa> afins, ArrayList<Pessoa> array) {
      for(int i = 0; i < array.size(); i++){
          afins.add(afins.size(),array.get(i));
      }
    }
}
