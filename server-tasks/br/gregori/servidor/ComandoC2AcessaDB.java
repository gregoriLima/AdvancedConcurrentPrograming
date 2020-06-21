package br.gregori.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

//Command

			
public class ComandoC2AcessaDB implements Callable<String>{
							//Como Runnable não retorna nada, é utilizado a interface Callable
							//que utiliza generics e tem um retorno
	
	private PrintStream saidaCliente;

	public ComandoC2AcessaDB(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String call() throws Exception {

		System.out.println("Executando comando C2 DB");
		
		saidaCliente.println("processando comando c2 DB...");
		
			Thread.sleep(10000);
		
		int numero = new Random().nextInt(100) + 1;
			
		saidaCliente.println(" Comando c2 - DB finalizado com sucesso!");
		
		
		return "" + numero;
	}

}
