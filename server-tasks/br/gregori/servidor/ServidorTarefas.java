package br.gregori.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException {

		System.out.println(" > Iniciando o servidor < ");
		
		//mesmo a porta padr�o de escuta sendo a 12345, na negocia��o, outra porta � utilizada
		//uma diferente para cada cliente conectado
		ServerSocket servidor = new ServerSocket(12345);
		
//		ExecutorService threadPool = Executors.newFixedThreadPool(2); //define um n�mero fixo de threads na pool
		ExecutorService threadPool = Executors.newCachedThreadPool(); //define que o n�mero de threads na pool deve crescer ou diminuir din�micamente
																		//dependendo da nescessidade. Threads ociosas por mais de 60s s�o descartadas
		
		while(true) {
		
		Socket socket = servidor.accept(); //aguarda requisi��o de conex�o e aceita. Este m�todo � bloqueante e trava a thread main at� receber uma conex�o.
		System.out.println("Aceitando um novo cliente na porta: " + socket.getPort());
		
		//--- Ao se criar uma nova thread para cada conex�o, recursos s�o desperdi�ados
		//pois cada thread � mapeada para uma thread nativa do sistema, e ap�s ter sido executada
		//ela � destru�da.
		//new Thread(new DistribuirTarefas(socket)).start();
		//---
		//A melhor maneira de gerenciar recursos � fazer a reutiliza��o de threads, 
		//fazendo com que uma thread ap�s lidar com uma conex�o de um cliente, n�o se termine
		//e possa ser reutilizada para lidar com outra conex�o. Para isso utiliza-se um pool de threads
		//j� implementado pela classe Executors
		 threadPool.execute(new DistribuirTarefas(socket)); //executa o Runnable passado na thread dispon�vel
		 //---	
				
		}
		
	}

	

}
