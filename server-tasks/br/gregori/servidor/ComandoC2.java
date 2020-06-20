package br.gregori.servidor;

import java.io.PrintStream;

public class ComandoC2 implements Runnable{

	private PrintStream saidaCliente;

	public ComandoC2(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		System.out.println("Executando comando C2");
		//faz algo demorado...
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		saidaCliente.println("Comando c2 executado com sucesso!");
		
	}

}
