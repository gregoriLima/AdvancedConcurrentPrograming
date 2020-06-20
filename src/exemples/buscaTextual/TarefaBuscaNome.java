package exemples.buscaTextual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.plaf.SliderUI;

public class TarefaBuscaNome implements Runnable {

	private String nomeDoArquivo, nomeProcurado;
	
	public TarefaBuscaNome(String nomeDoArquivo, String nomeProcurado) {
		this.nomeDoArquivo = nomeDoArquivo;
		this.nomeProcurado = nomeProcurado;
	}

	@Override
	public void run() {

		
		
		//utilizando o try-with-resourcer que automaticamente faz um scan.close() sem a necessidade de um finally
		try (Scanner scan = new Scanner(new File(nomeDoArquivo))) {
			
			int numeroLinha = 1;
			
			while(scan.hasNext()) {
				String linha = scan.nextLine();
				
				if (linha.contains(nomeProcurado)) {
					
					System.out.println("Nome encontrado no " + nomeDoArquivo + " na linha " + numeroLinha);
				}
				
				numeroLinha++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Busca no " + nomeDoArquivo + " finalizada!");
		}

	}

	
}
