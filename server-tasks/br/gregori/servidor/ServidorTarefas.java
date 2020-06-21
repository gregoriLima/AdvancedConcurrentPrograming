package br.gregori.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

	private ExecutorService threadPool;
	private ServerSocket servidor;
	
	//cada thread é mapeada para uma thread nativa do sistema operacional
	//para otimizar o acesso, cada thread copia esta variável booleana para o seu cache
	//e quando o método parar seta esta variável para false, ela é modificada só na thread main
	//e não é modificada na cache da thread do sistema que copiou o valor para otimizar o acesso
//	private boolean estaRodando;
	//para resolver isso, para que todas as threads acessem esta variável da memória principal, ela deve ser volátil
//	private volatile boolean estaRodando;
	//ou conforme o recurso do Java, pode ser um objeto AtomicBoolean já infere que a variável é volatile
	private AtomicBoolean estaRodando = new AtomicBoolean(false);
	
	
	private Thread listenigForConnections;
	private Socket socket;
	private Thread threadAguardaConexao;
	
	public ServidorTarefas() throws IOException {

		System.out.println(" > Iniciando o servidor < ");

		// mesmo a porta padrão de escuta sendo a 12345, na negociação, outra porta é
		// utilizada
		// uma diferente para cada cliente conectado
		this.servidor = new ServerSocket(12345);
		
		//para poder tratar as exceptions que são lançadas pelas threads, é necessário
		//utilizar o método set.UncaughtExceptoinHandler() com um objeto Thread.
		//Como o newFixedThreadPool cria automaticamente as threads internamente, não é possível
		//ter acesso aos objetos Threads criados. Para isso é passado como argumento uma Fábrica de Threads
		//assim, dentro desta fábrica de Threads, definimos uma classe tratadora de erros na criação de cada 
		//thread pela pool de Threads de Executor.
		this.threadPool = Executors.newFixedThreadPool(4, new FabricaDeThreads());
		//ThreadFactory factory = Executors.defaultThreadFactory(); //assim é possível acessar a Factory padrão utilizada por Executors
		
//		this.threadPool = Executors.newFixedThreadPool(4); //define um número fixo de threads na pool
//		this.threadPool = Executors.newCachedThreadPool(); // define que o número de threads na pool deve
															// crescer ou diminuir dinâmicamente
															// dependendo da nescessidade. Threads ociosas
															// por mais de 60s são descartadas
		this.estaRodando.set(true);
	}

	

	public void rodar() throws IOException {

		while (this.estaRodando.get()) {
 		
			try {			
				socket = servidor.accept(); // aguarda requisição de conexão e aceita. Este método é bloqueante e
				// trava a thread main até receber uma conexão.
				
				System.out.println("Aceitando um novo cliente na porta: " + socket.getPort());
	
				// --- Ao se criar uma nova thread para cada conexão, recursos são desperdiçados
				// pois cada thread é mapeada para uma thread nativa do sistema, e após ter sido
				// executada
				// ela é destruída.
				// new Thread(new DistribuirTarefas(socket)).start();
				// ---
				// A melhor maneira de gerenciar recursos é fazer a reutilização de threads,
				// fazendo com que uma thread após lidar com uma conexão de um cliente, não se
				// termine
				// e possa ser reutilizada para lidar com outra conexão. Para isso utiliza-se um
				// pool de threads
				// já implementado pela classe Executors
				threadPool.execute(new DistribuirTarefas(threadPool, socket, this)); // executa o Runnable passado na thread disponível
				// ---
			} catch (SocketException e) {System.out.println("Socket Exception");}
		}

	}
	
	
	public void parar() throws IOException{
		
		this.estaRodando.set(false);;
		
		this.threadPool.shutdown();
		this.servidor.close();
	
		
		
	}
	
	
	public static void main(String[] args) throws IOException {

		ServidorTarefas servidor = new ServidorTarefas();
		servidor.rodar();
		servidor.parar();

	}

}
