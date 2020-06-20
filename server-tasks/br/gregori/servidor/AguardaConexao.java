package br.gregori.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AguardaConexao implements Runnable{

	private ServerSocket serverSocket;
	private Socket socket;

	public AguardaConexao(ServerSocket servidor) {
	
		this.serverSocket = servidor;
	
	}

	@Override
	public void run() {

		try {
			socket = serverSocket.accept();
			System.out.println("Aceitando um novo cliente na porta: " + socket.getPort());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Socket getConexao() {
		return this.socket;
	}

}

/*AguardaConexao aguardaConexao = new AguardaConexao(servidor);
			this.threadAguardaConexao = new Thread(aguardaConexao);
			threadAguardaConexao.start();
			
			try {
				threadAguardaConexao.join();
				if (!estaRodando) break;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.socket = aguardaConexao.getConexao();
			
			//Socket */
