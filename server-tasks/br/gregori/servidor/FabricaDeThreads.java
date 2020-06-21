package br.gregori.servidor;

import java.util.concurrent.ThreadFactory;

//FactoryMethod

//Fábrica de Threads para pool de threads de Executor, para podermos dar uma manipulada
//nos objetos Threads antes de eles serem utilizados
public class FabricaDeThreads implements ThreadFactory {

	private static int numero;
	
	@Override
	public Thread newThread(Runnable runnable) {

		
		Thread thread = new Thread(runnable, "Thread Servidor Tarefas " + numero++);
		
		thread.setUncaughtExceptionHandler(new TratadorDeExceptions());
		thread.setDaemon(true); //definindo que a thread é de serviço, que será automaticamente finalizada quando
							//não houver nenhuma outra thread main rodando na JVM
		
		return thread;
	}

}
