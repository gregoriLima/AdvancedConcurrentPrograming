package br.gregori.servidor;

import java.util.concurrent.ThreadFactory;


//Fábrica de Threads para pool de threads de Executor, para podermos dar uma manipulada
//nos objetos Threads antes de eles serem utilizados
public class FabricaDeThreads implements ThreadFactory {

	private int numero;
	
	@Override
	public Thread newThread(Runnable runnable) {

		
		Thread thread = new Thread(runnable, "Thread Servidor Tarefas " + numero++);
		
		thread.setUncaughtExceptionHandler(new TratadorDeExceptions());
		
		return thread;
	}

}
