package buscaTextual;

public class Principal {

	public static void main(String[] args) {

			String nomeProcurado = "Jo�o";
			
			//exemplo simples mostrando que � o escalonador de threads (scheduler) que agenda a execu��o da thread e define 
			//o tempo de cada uma rodar, por isso a ordem n�o � previs�vel
			//Caso o sistema n�o suporte MultiThreading, ent�o a JVM simula um ambiente multi-threaded, com as Green Threads
			new Thread(new TarefaBuscaNome("C:\\a\\arquivo1.txt", nomeProcurado)).start();
			new Thread(new TarefaBuscaNome("C:\\a\\arquivo2.txt", nomeProcurado)).start();
			new Thread(new TarefaBuscaNome("C:\\a\\arquivo3.txt", nomeProcurado)).start();
			
			
	}

}
