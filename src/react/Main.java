package react;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Main {

	public static void main(String[] args) {
		 	String csvFile = "C:\\Roger_Verdier93.csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        final int QUANTIDADE_DE_PERGUNTAS = 15;
	        String nome = "";
	        boolean respostas[] = new boolean[QUANTIDADE_DE_PERGUNTAS];
	        
	        Cluster cluster = new Cluster();

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            line = br.readLine();  //PULANDO O CABECALHO
	            
	            int qnd_pessoas = 0;
	            
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] parser = line.split(cvsSplitBy);
	                
	                nome = parser[1];
	                for(int i = 2, j = 0; i < QUANTIDADE_DE_PERGUNTAS+2; i++, j++) {
	                	if(parser[i].equals("\"Sim\"")){
	                		//System.out.println("SIM");
	                		respostas[j] = true;
	                	}else {
	                		//System.out.println("NAO");
	                		respostas[j] = false;
	                	}
	                }
	                Pessoa novaPessoa = new Pessoa(nome,respostas);
	                //novaPessoa.pri();
	                novaPessoa.definir();
	                
	                cluster.receberPessoa(novaPessoa);
	                qnd_pessoas++;
	            }
	            System.out.println("Quantidade de pessoas analizadas "+ qnd_pessoas);
                    cluster.setQuantidadePessoas(qnd_pessoas);
	            cluster.PrintArrays();
                    cluster.agrupar(29
                    		);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }


	}

}
