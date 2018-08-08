package react;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

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
            
            for(int i  = 0 ; i < quantidade_grupos; i ++){ 		//ALOCACAO
                grupos.add(i, new ArrayList<Pessoa>(estimativaPessoasPorGrupo));
            }
            
            //colocando os lideres de cada grupo
            int i =0;
            for(;i < qnt_lider && i < quantidade_grupos ; i++) {
            	 grupos.get(i).add(0, this.Lider.remove(0));
            }
          
 
            if(i < quantidade_grupos){		//social
            	for(int j = 0 ;j < qnt_social && i < quantidade_grupos ; i++, j++) {
               	 grupos.get(i).add(0, this.Social.remove(0));
               }
            }
            
            if(i < quantidade_grupos){		//ativo
            	for(int j = 0;j < qnt_ativo && i < quantidade_grupos ; i++, j++) {
               	 grupos.get(i).add(0, this.Ativo.remove(0));
               }
            }
            
            for( i  = 0 ; i < quantidade_grupos; i ++){            
               printUnicoArray(grupos.get(i));
           }
            
            try {
                preecherGrupo(grupos, estimativaPessoasPorGrupo);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            System.out.println("terminou!!");
            
            for( i  = 1 ; i <= quantidade_grupos; i ++){
            	System.out.println("Grupo "+ i + ": ");
                printUnicoArray(grupos.get(i-1));
           }
            
            this.gerarTXT(grupos);
            
            
        }
        
        private void gerarTXT(ArrayList<ArrayList<Pessoa>> grupos) {
        	FileWriter  arq = null;
        	try {
        		arq = new FileWriter("E:\\grupos.txt");
        		int aux = 0 ;
        		for( int i  = 0 ; i < grupos.size(); i ++){
        			aux = i+1;
                	arq.write(("Grupo "+ aux + ":\n"));
                    for(int j = 0 ; j < grupos.get(i).size(); j++) {
                    	arq.write(grupos.get(i).get(j).getNome()+ "Tipo "+ grupos.get(i).get(j).classificacao);
                    	arq.write("\n");
                    	aux = j;
                    }
                    aux +=1;
                    arq.write("qnt: "+ aux +"\n\n");
               }
        		
        		arq.close();
        	}catch (Exception e) {
				System.err.println("aaaaaaaa" + e.getMessage());
				try {
					arq.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        	
			
		}


		private void preecherGrupo(ArrayList<ArrayList<Pessoa>> grupos, int estimativa) throws Exception{
        	ArrayList<Pessoa> PessoasSemGrupo = addTodas();
        	
            while(PessoasSemGrupo.size() > 0 ) {
            	for(int i = 0; i < grupos.size(); i++ ){
                	
                	
                    if(grupos.get(i).get(0).classificacao == EnumClassificacao.LIDER){
                        preencherCabeca(grupos.get(i),this.Fleumatico, this.Melancolico,this.Ativo, EnumClassificacao.AMORFO, PessoasSemGrupo);
                    
                    }else if(grupos.get(i).get(0).classificacao == EnumClassificacao.SOCIAL){
                    	preencherCabeca(grupos.get(i), this.Amorfo, this.Ativo,this.Fleumatico, EnumClassificacao.MELANCOLICO, PessoasSemGrupo );
                   
                    } else if(grupos.get(i).get(0).classificacao == EnumClassificacao.ATIVO){
                    	preencherCabeca(grupos.get(i), this.Instavel, this.Social, this.Lider, EnumClassificacao.APATICO, PessoasSemGrupo);
                    
                    } else if(grupos.get(i).get(0).classificacao == EnumClassificacao.MELANCOLICO) {
                    	preencherCabeca(grupos.get(i),this.Lider ,this.Instavel ,this.Apatico ,EnumClassificacao.SOCIAL , PessoasSemGrupo);
                    
                    }else if(grupos.get(i).get(0).classificacao == EnumClassificacao.INSTAVEL) {
                    	preencherCabeca(grupos.get(i),this.Melancolico, this.Amorfo, this.Ativo, EnumClassificacao.FLEUMATICO, PessoasSemGrupo);
                   
                    } else if(grupos.get(i).get(0).classificacao == EnumClassificacao.AMORFO) {
                    	preencherCabeca(grupos.get(i),this.Instavel, this.Apatico, this.Social, EnumClassificacao.LIDER, PessoasSemGrupo);
                   
                    }else if(grupos.get(i).get(0).classificacao == EnumClassificacao.APATICO) {
                    	preencherCabeca(grupos.get(i),this.Melancolico, this.Amorfo, this.Fleumatico, EnumClassificacao.ATIVO, PessoasSemGrupo);
                    
                    }else if(grupos.get(i).get(0).classificacao == EnumClassificacao.FLEUMATICO) {
                    	preencherCabeca(grupos.get(i),this.Social,this.Lider,this.Apatico, EnumClassificacao.INSTAVEL, PessoasSemGrupo);
                    }
                    
                    else {
                        throw new Exception("Centroide nao identificado");
                    }
                    
                }
            }
            
        }
        
        
        private ArrayList<Pessoa> addTodas() {
        	ArrayList<Pessoa> todas = new ArrayList<>(this.Melancolico.size() + this.Instavel.size() + this.Amorfo.size()
        											+ this.Apatico.size() + this.Social.size()+ this.Fleumatico.size()
        											+ this.Ativo.size() + this.Lider.size());
        	
        	concatenar(todas, this.Melancolico);
        	concatenar(todas, this.Instavel);
        	concatenar(todas, this.Amorfo);
        	concatenar(todas, this.Apatico);
        	concatenar(todas, this.Social);
        	concatenar(todas, this.Fleumatico);
        	concatenar(todas, this.Ativo);
        	concatenar(todas, this.Lider);
        	
        	System.out.println("aAAAaaaaaA");
        	printUnicoArray(todas);
        	
        	
        	return todas;
		}


		private void preencherCabeca(ArrayList<Pessoa> grupo, ArrayList<Pessoa> afin1,
				ArrayList<Pessoa> afin2, ArrayList<Pessoa> afin3, EnumClassificacao antiteticos,
				ArrayList<Pessoa> pessoasSemGrupo) {
			Pessoa p = null;
			Random rand = new Random(); 
			
			ArrayList<Pessoa> TodosOsafins = new ArrayList<Pessoa>();
			
			this.concatenar(TodosOsafins, afin1);
			this.concatenar(TodosOsafins, afin2);
			this.concatenar(TodosOsafins, afin3);
			
					
			if(TodosOsafins.size() > 0) {
				p = TodosOsafins.remove(rand.nextInt(TodosOsafins.size()));
				afin1.remove(p);
				afin2.remove(p);
				afin3.remove(p);
				if(!pessoasSemGrupo.remove(p)) {
					System.err.println("ERRO Pessoa nao encontrada3");
				}
				grupo.add(p);
				return;
			}
			
			if(pessoasSemGrupo.size() > 0) {
				for(int i = 0; i< pessoasSemGrupo.size(); i++) {
					p = pessoasSemGrupo.get(i);
					if(p.classificacao != antiteticos) {
						grupo.add(p);
						pessoasSemGrupo.remove(p);
						return;
					}
				}
				System.out.println("AQUELE CASO LÁAAAAAAAAAAAAAAAAAAAAA");
				grupo.add(p);
				pessoasSemGrupo.remove(p);
				return;
				
				
			}
			//possivel erro: pessoa ser retirada do pessoasSemGrupo mas nao ser retirada do array dela
		}


        void vaiParaOGrupo(ArrayList<Pessoa> PessoaSemGrupo, ArrayList<Pessoa> grupo ) {
        	grupo.add(PessoaSemGrupo.remove(0));
        }

        
    private void concatenar(ArrayList<Pessoa> afins, ArrayList<Pessoa> array) {
      for(int i = 0; i < array.size(); i++){
          afins.add(afins.size(),array.get(i));
      }
    }
}




/*
 * ArrayList<Pessoa> afinsLider = new ArrayList<Pessoa>(this.Fleumatico.size()+ this.Melancolico.size() + this.Ativo.size());
        	ArrayList<Pessoa> afinsSocial = new ArrayList<Pessoa>(this.Fleumatico.size()+ this.Melancolico.size() + this.Ativo.size());
        	
        	concatenar(afinsLider,this.Fleumatico);
            concatenar(afinsLider,this.Melancolico);
            concatenar(afinsLider,this.Ativo);
            afinsLider.re*/
