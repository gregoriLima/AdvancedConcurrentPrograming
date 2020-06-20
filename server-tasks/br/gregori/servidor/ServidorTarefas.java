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
	
	//cada thread � mapeada para uma thread nativa do sistema operacional
	//para otimizar o acesso, cada thread copia esta vari�vel booleana para o seu cache
	//e quando o m�todo parar seta esta vari�vel para false, ela � modificada s� na thread main
	//e n�o � modificada na cache da thread do sistema que copiou o valor para otimizar o acesso
//	private boolean estaRodando;
	//para resolver isso, para que todas as threads acessem esta vari�vel da mem�ria principal, ela deve ser vol�til
//	private volatile boolean estaRodando;
	//ou conforme o recurso do Java, pode ser um objeto AtomicBoolean j� infere que a vari�vel � volatile
	private AtomicBoolean estaRodando = new AtomicBoolean(false);
	
	
	private Thread listenigForConnections;
	private Socket socket;
	private Thread threadAguardaConexao;
	
	public ServidorTarefas() throws IOException {

		System.out.println(" > Iniciando o servidor < ");

		// mesmo a porta padr�o de escuta sendo a 12345, na negocia��o, outra porta �
		// utilizada
		// uma diferente para cada cliente conectado
		this.servidor = new ServerSocket(12345);

//		ExecutorService threadPool = Executors.newFixedThreadPool(2); //define um n�mero fixo de threads na pool
		this.threadPool = Executors.newCachedThreadPool(); // define que o n�mero de threads na pool deve
															// crescer ou diminuir din�micamente
															// dependendo da nescessidade. Threads ociosas
															// por mais de 60s s�o descartadas
		this.estaRodando.set(true);
	}

	

	public void rodar() throws IOException {

		while (this.estaRodando.get()) {
 		
			try {			
				socket = servidor.accept(); // aguarda requisi��o de conex�o e aceita. Este m�todo � bloqueante e
				// trava a thread main at� receber uma conex�o.
				
				System.out.println("Aceitando um novo cliente na porta: " + socket.getPort());
	
				// --- Ao se criar uma nova thread para cada conex�o, recursos s�o desperdi�ados
				// pois cada thread � mapeada para uma thread nativa do sistema, e ap�s ter sido
				// executada
				// ela � destru�da.
				// new Thread(new DistribuirTarefas(socket)).start();
				// ---
				// A melhor maneira de gerenciar recursos � fazer a reutiliza��o de threads,
				// fazendo com que uma thread ap�s lidar com uma conex�o de um cliente, n�o se
				// termine
				// e possa ser reutilizada para lidar com outra conex�o. Para isso utiliza-se um
				// pool de threads
				// j� implementado pela classe Executors
				threadPool.execute(new DistribuirTarefas(socket, this)); // executa o Runnable passado na thread dispon�vel
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
