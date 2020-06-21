package br.gregori.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

//FrontController

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;
	private BlockingQueue<String> filaComandos;

	public DistribuirTarefas(ExecutorService threadPool, BlockingQueue<String> filaComandos, Socket socket, ServidorTarefas servidor) {
		this.threadPool = threadPool;
		this.filaComandos = filaComandos;
		this.socket = socket;
		this.servidor = servidor;
	}

	@Override
	public void run() {

		System.out.println(" > Distribuindo tarefas para: " + socket);

		try {

			InputStream inputStreamServidor = socket.getInputStream(); // recebe os dados enviados pelo cliente
			Scanner entradaCliente = new Scanner(inputStreamServidor);

			PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

			while (entradaCliente.hasNextLine()) {
				String comandoRecebido = entradaCliente.nextLine();
				System.out.println("Comando recebido: " + comandoRecebido);

				switch (comandoRecebido) {

					case "c1": {
						saidaCliente.println("Confirma��o comando c1");
						
						ComandoC1 c1 = new ComandoC1(saidaCliente);
						this.threadPool.execute(c1);
						
						break;
					}
					case "c2": {
						saidaCliente.println("Confirma��o comando c2");
						
						ComandoC2ChamaWS c2WS = new ComandoC2ChamaWS(saidaCliente);
						ComandoC2AcessaDB c2DB = new ComandoC2AcessaDB(saidaCliente);
//						this.threadPool.execute(c2); //execute � apenas para Runnable
						Future<String> futureWS = this.threadPool.submit(c2WS); //submit retorna um Future<T>, retorna algo que ainda n�o est� pronto
													//que estar� pronto no futuro
						Future<String> futureDB = this.threadPool.submit(c2DB);
						//Callable n�o � Runnable, mas Future �, ent�o pode ser passado como argumento para Thread! Podendo ser considerada um Adapter
						
						//O future tem um m�todo chamado .get, e este m�todo vai devolver o resultado da execu��o 
						//da thread quando estiver pronto. A linha get fica parada at� o resultado ser retornado
//						String resultadoWS = futureWS.get();
					
						//para que o programa n�o fica travado nesta linha do get() do future, ele deve ser implementado
						//dentro de uma outra thread
						
						this.threadPool.submit(new ResultadosFutureDBeWS(futureWS, futureDB, saidaCliente)); //como esta tarefa n�o devolve nada, poderia ser utilizado o Runnable
						//submit tem duas sobrecargas, uma que recebe  Callable e outra que recebe Runnable
						//submit retorna um Future e n�o bloqueia a execu��o do c�digo
						//duas coisas complicadas: implementar um cache e a segunda dar nome para as coisas;
						
						break;
					}
					case "C3":{
						
						this.filaComandos.put(comandoRecebido);//bloqueia a thread
						saidaCliente.println("Comando c3 adicionado a fila");
						
						break;
						}
					case "fim": {
						saidaCliente.println("Servidor desligado");
						
						servidor.parar();
						break;
					}
					default: {
						saidaCliente.println("Comando inv�lido!");
					}
				}

			}

			entradaCliente.close();
			saidaCliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
