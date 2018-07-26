package react;

import java.util.ArrayList;

public class Cluster {

	private ArrayList<Pessoa> Melancolico;
	private ArrayList<Pessoa> Instavel;
	private ArrayList<Pessoa> Amorfo;
	private ArrayList<Pessoa> Apatico;
	private ArrayList<Pessoa> Social;
	private ArrayList<Pessoa> Fleumatico;
	private ArrayList<Pessoa> Ativo;
	private ArrayList<Pessoa> Lider;
	
	
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
			this.Apatico.add(novaPessoa);
		}else if(tipo.equals("nEnAP")) {
			this.Amorfo.add(novaPessoa);
		}else if(tipo.equals("nEAP")) {
			this.Social.add(novaPessoa);
		}else if(tipo.equals("nEAS")) {
			this.Fleumatico.add(novaPessoa);
		}else if(tipo.equals("EnAP")) {
			this.Instavel.add(novaPessoa);
		}else if(tipo.equals("EAP")) {
			this.Ativo.add(novaPessoa);
		}else if(tipo.equals("EnAS")) {
			this.Melancolico.add(novaPessoa);
		}else if(tipo.equals("EAS")) {
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
		for(int i = 0; i < Arrayp.size(); i++) {
			System.out.print("Nome: " + Arrayp.get(i).getNome() + " ");
		}
		System.out.println();
	}
}
