package br.gregori.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String... args) throws UnknownHostException, IOException {

		Socket socket = new Socket("localhost", 12345); // solicita uma conexão em loopback na porta 12345

		System.out.println("Conexão estabelecida ");

	
		
		
		Thread threadEnviaComando = new Thread(new Runnable() {

			@Override
			public void run() {

				OutputStream outputStreamCliente;
				try {
					outputStreamCliente = socket.getOutputStream();

					PrintStream saida = new PrintStream(outputStreamCliente);
					saida.print("Comando: "); // envia simulação de comando par ao servidor através da OutputStream

					// Enviando dados para o servidor
					Scanner keyboard = new Scanner(System.in);
					while (keyboard.hasNextLine()) {
						String linha = keyboard.nextLine();
						if (linha.trim().equals("")) {
							break; // se for apertado enter, encerra o while
						}
						saida.println(linha);
					}
//				keyboard.nextLine(); //aguarda uma entrada para prosseguir

					keyboard.close();
					saida.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		
		
		
		Thread threadRecebeComando = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					// lendo os dados do servidor
					System.out.println("Recebendo dados do servidor");
					Scanner respostaServidor = new Scanner(socket.getInputStream());
					while (respostaServidor.hasNextLine()) {
						String linha = respostaServidor.nextLine();
						System.out.println(linha);

					}

					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		
		threadEnviaComando.start();
		threadRecebeComando.start();
	
		
		try {
			//diz para a thread main aguardar enquanto a threadEnviacomando executa
			threadEnviaComando.join(); //junta esta thread atual, a main, com a thread Envia comando. Assim esta thread 
										//main atual só continua quando a threadEnviaComando terminar
			
//			threadEnviaComando.join(5000);//junta-se a threadEnviaComandos por 5 segundos, após este tempo, continua a execuçãos
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Fechando conexão...");
		
		socket.close();
		
		
		
	}

}
