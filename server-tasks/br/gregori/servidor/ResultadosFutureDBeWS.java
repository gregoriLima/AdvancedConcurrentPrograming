package br.gregori.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResultadosFutureDBeWS implements Callable<Void> { //no generics, o tipo Void mai�sculo n�o devolve nada

	private Future<String> futureWS;
	private Future<String> futureDB;
	private PrintStream saidaCliente;

	public ResultadosFutureDBeWS(Future<String> futureWS, Future<String> futureDB, PrintStream saidaCliente) {
		this.futureWS = futureWS;
		this.futureDB = futureDB;
		this.saidaCliente = saidaCliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Void call() { //Void mai�sculo � uma maneira de n�o devolver nada

		System.out.println("Aguardando resultados do future WS e DB...");
		
		//o m�todo get tranca a execu��o ao esperar o resultado de future, por isso � executado em um thread
		
		try {
			//o m�todo get bloqueia a thread at� receber o resultado
			String numeroWS = this.futureWS.get(15, TimeUnit.SECONDS); //definindo um timeout para a execu��o da thread
			String numeroDB = this.futureDB.get(15, TimeUnit.SECONDS);
			
			System.out.println("Resultado do comando C2 : " + numeroWS + " ," + numeroDB);
			
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			this.saidaCliente.println("Timeout na execu��o do comando C2");
			this.futureDB.cancel(true);
			this.futureWS.cancel(true); //cancelando a execu��o de future
		} 
		
		System.out.println("Finalizou futures C2 DB e WS");
		
		return null;
	} 

}
