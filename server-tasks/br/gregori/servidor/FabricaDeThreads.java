package br.gregori.servidor;

import java.util.concurrent.ThreadFactory;

//FactoryMethod

//F�brica de Threads para pool de threads de Executor, para podermos dar uma manipulada
//nos objetos Threads antes de eles serem utilizados
public class FabricaDeThreads implements ThreadFactory {

	private static int numero;
	
	@Override
	public Thread newThread(Runnable runnable) {

		
		Thread thread = new Thread(runnable, "Thread Servidor Tarefas " + numero++);
		
		thread.setUncaughtExceptionHandler(new TratadorDeExceptions());
		thread.setDaemon(true); //definindo que a thread � de servi�o, que ser� automaticamente finalizada quando
							//n�o houver nenhuma outra thread main rodando na JVM
		
		return thread;
	}

}
