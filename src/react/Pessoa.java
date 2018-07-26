package react;

public class Pessoa {
	private String nome;
	private String tipo;
	private boolean[] respostas;
	
	
	public Pessoa(String nome, boolean[] r) {
		this.nome = nome;
		this.respostas = r;
		
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void pri() {
		System.out.println("nome:" + this.nome);
		for(boolean m: this.respostas) {
			System.out.println(m);
		}
	}
	
	public void definir() {
		System.out.println("nome:" + this.nome);
		//PAGINA 15
		
		String def = "";
		int i = 0, j=0, k=0;
		
		
		//EMOTIVO - NAO EMOTIVO
		if(respostas[1]) i++;
		if(respostas[3]) i++;
		if(respostas[6]) i++;
		if(respostas[7]) i++;
		if(respostas[13]) i++;
		
		if(i >= 3 ) {
			//System.out.println("Emotivo");
			def+= "E";
		}else {
			//System.out.println("NAO Emotivo");
			def+= "nE";
		}
		
		//ATIVO - NAO ATIVO
		if(respostas[2]) j++;
		if(respostas[5]) j++;
		if(respostas[9]) j++;
		if(respostas[10]) j++;
		if(respostas[12]) j++;
		
		if(j >= 3 ) {
			//System.out.println("Ativo");
			def+= "A";
		}else {
			//System.out.println("NAO Ativo");
			def+= "nA";
		}
		
		// REPERCUSSAO SECUNDARIA - PRIMARIA
		if(respostas[0]) k++;
		if(respostas[4]) k++;
		if(respostas[8]) k++;
		if(respostas[11]) k++;
		if(respostas[14]) k++;
		
		if(k >= 3 ) {
			//System.out.println("Repercuss�o Secundaria");
			def+= "S";
		}else {
			//System.out.println("Repercuss�o primaria");
			def+= "P";
		}
		
		this.tipo = def;
		System.out.println("String: " + def + "\n");
	}
}
