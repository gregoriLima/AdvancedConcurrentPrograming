package br.gregori.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;

	public DistribuirTarefas(ExecutorService threadPool, Socket socket, ServidorTarefas servidor) {
		this.threadPool = threadPool;
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
						saidaCliente.println("Confirmação comando c1");
						
						ComandoC1 c1 = new ComandoC1(saidaCliente);
						this.threadPool.execute(c1);
						
						break;
					}
					case "c2": {
						saidaCliente.println("Confirmação comando c2");
						
						ComandoC2 c2 = new ComandoC2(saidaCliente);
						this.threadPool.execute(c2);
						
						break;
					}
					case "fim": {
						saidaCliente.println("Servidor desligado");
						
						servidor.parar();
						break;
					}
					default: {
						saidaCliente.println("Comando inválido!");
					}
				}

			}

			entradaCliente.close();
			saidaCliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
