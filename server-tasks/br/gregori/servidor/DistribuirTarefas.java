package br.gregori.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {

	private Socket socket;

	public DistribuirTarefas(Socket socket) {
		this.socket = socket;
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
						break;
					}
					case "c2": {
						saidaCliente.println("Confirmação comando c2");
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
