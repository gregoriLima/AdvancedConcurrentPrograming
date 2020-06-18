package buscaTextual;

public class Principal {

	public static void main(String[] args) {

			String nomeProcurado = "João";
			
			//exemplo simples mostrando que é o escalonador de threads (scheduler) que agenda a execução da thread e define 
			//o tempo de cada uma rodar, por isso a ordem não é previsível
			//Caso o sistema não suporte MultiThreading, então a JVM simula um ambiente multi-threaded, com as Green Threads
			new Thread(new TarefaBuscaNome("C:\\a\\arquivo1.txt", nomeProcurado)).start();
			new Thread(new TarefaBuscaNome("C:\\a\\arquivo2.txt", nomeProcurado)).start();
			new Thread(new TarefaBuscaNome("C:\\a\\arquivo3.txt", nomeProcurado)).start();
			
			
	}

}
