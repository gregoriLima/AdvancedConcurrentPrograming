package br.gregori.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

//Command

			
public class ComandoC2ChamaWS implements Callable<String>{ //Callable devem ser executadas por um ExecutorService, ou seja
															//através de uma pool de threads
							//Como Runnable não retorna nada, é utilizado a interface Callable
							//que utiliza generics e tem um retorno
	
	private PrintStream saidaCliente;

	public ComandoC2ChamaWS(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String call() throws Exception {

		System.out.println("Executando comando C2 WS");
		
		saidaCliente.println("processando comando c2 WS...");
		
			Thread.sleep(10000);
		
		int numero = new Random().nextInt(100) + 1;
			
		saidaCliente.println(" Comando c2 - WS finalizado com sucesso!");
		
		
		return "" + numero;
	}

}
