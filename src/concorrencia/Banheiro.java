package concorrencia;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banheiro {
	
	private boolean banheiroSujo;

	public void fazNumero1() {

		String nomeThread = Thread.currentThread().getName();

		System.out.println(nomeThread + " batendo na porta!");

		// desta maneira, o acesso ao banheiro fica irrestrito e implementado de maneira
		// incorreta
		// pois o acesso não está sincronizado e o método pode ser executado pois mais
		// de uma Thread ao mesmo tempo
		// esta é a chamada região crítica do código em que duas Threads não podem
		// entrar ao mesmo tempo
//		System.out.println(nomeThread + " entrando no banheiro");
//		System.out.println(nomeThread + " fazendo Xixi");
//		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(nomeThread + " dando descarga");
//		System.out.println(nomeThread + " saindo do banheiro");

		// esta é a maneira de sincronizar o acesso das threads ao método
		// a chave para que cada Thread tenha acesso atômico a esta parte do código é
		// chama de Mutex
		// sem o Lock, o bloqueio de acesso é feito de maneira implícita
		synchronized (this) {
			
			if(banheiroSujo)
				esperaLaFora(nomeThread);
			

			System.out.println(nomeThread + " entrando no banheiro");
			System.out.println(nomeThread + " fazendo Xixi");

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(nomeThread + " dando descarga");
			System.out.println(nomeThread + " saindo do banheiro");

			banheiroSujo = true;
			
			System.out.println();

		}
	}

	// Pode-se também fazer o bloqueio de maneira explícita utilizando a classe
	// ReentrantLock
	// com o 'lock.lock();' e para liberar o acesso 'lock.unlock();'

	// private Lock lock = new ReentrantLock();

	public void tomarBanho() { // uma outra maneira de sincronizar o método inteiro seria alterar
								// a declaração do método para 'public synchronized tomarBanho()'

		String nomeThread = Thread.currentThread().getName();

		System.out.println(nomeThread + " batendo na porta!");

		synchronized (this) {
			
			while(banheiroSujo)
				esperaLaFora(nomeThread);

			System.out.println(nomeThread + " entrando no banheiro...");
			System.out.println(nomeThread + " tomando banho...");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(nomeThread + " se secando...");
			System.out.println(nomeThread + " saindo do banheiro...");

			banheiroSujo = true;
			
			System.out.println();
		}
	}
	
	
	public void esperaLaFora(String nome) {
		
		System.out.println(nome + " saiu do banheiro pois ele está sujo!");
		
		try {
			this.wait(); //o wait() herdado de object, faz a thread devolver o Mutex e ficar aguardando
					//para que a thread saia do estado de espera ela deve ser notificada
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void limpaBanheiro() {
		
		String nome = Thread.currentThread().getName();
		
				
		synchronized (this) {
			
			if(!banheiroSujo) {
				System.out.println("  O banheiro não está sujo, saindo...");
				System.out.println();
				return;
			}
			
			System.out.println(" >> " + nome + " limpando o banheiro!");
			System.out.println();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		banheiroSujo = false;
		
		notifyAll(); //só pode-se notificar os outros threads quando o mutex não estiver sendo utilizado

		}
	}

}
