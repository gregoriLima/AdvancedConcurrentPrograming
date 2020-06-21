package br.gregori.servidor;

import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable {

	public Consumidor(BlockingQueue<String> filaComandos) {
		this.filaComandos = filaComandos;
	}
	
	private BlockingQueue<String> filaComandos;
	
	
	
	@Override
	public void run() {
		
		try {
			
			String comando = null;
			
			while( (comando = filaComandos.take()) != null) {
			
			System.out.println("Consumindo comando " + comando + " ," + Thread.currentThread().getName());
			
			Thread.sleep(5000);
			
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
	}

}
