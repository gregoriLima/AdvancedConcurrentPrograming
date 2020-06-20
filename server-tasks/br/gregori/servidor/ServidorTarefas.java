package br.gregori.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException {

		System.out.println(" > Iniciando o servidor < ");
		
		//mesmo a porta padrão de escuta sendo a 12345, na negociação, outra porta é utilizada
		//uma diferente para cada cliente conectado
		ServerSocket servidor = new ServerSocket(12345);
		
//		ExecutorService threadPool = Executors.newFixedThreadPool(2); //define um número fixo de threads na pool
		ExecutorService threadPool = Executors.newCachedThreadPool(); //define que o número de threads na pool deve crescer ou diminuir dinâmicamente
																		//dependendo da nescessidade. Threads ociosas por mais de 60s são descartadas
		
		while(true) {
		
		Socket socket = servidor.accept(); //aguarda requisição de conexão e aceita. Este método é bloqueante e trava a thread main até receber uma conexão.
		System.out.println("Aceitando um novo cliente na porta: " + socket.getPort());
		
		//--- Ao se criar uma nova thread para cada conexão, recursos são desperdiçados
		//pois cada thread é mapeada para uma thread nativa do sistema, e após ter sido executada
		//ela é destruída.
		//new Thread(new DistribuirTarefas(socket)).start();
		//---
		//A melhor maneira de gerenciar recursos é fazer a reutilização de threads, 
		//fazendo com que uma thread após lidar com uma conexão de um cliente, não se termine
		//e possa ser reutilizada para lidar com outra conexão. Para isso utiliza-se um pool de threads
		//já implementado pela classe Executors
		 threadPool.execute(new DistribuirTarefas(socket)); //executa o Runnable passado na thread disponível
		 //---	
				
		}
		
	}

	

}
