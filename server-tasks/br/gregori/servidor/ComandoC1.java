package br.gregori.servidor;

import java.io.PrintStream;

public class ComandoC1 implements Runnable {

	private PrintStream saidaCliente;

	public ComandoC1(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		System.out.println("Executando comando C1");
		//faz algo demorado...
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		saidaCliente.println("Comando c1 executado com sucesso!");
	}

}
