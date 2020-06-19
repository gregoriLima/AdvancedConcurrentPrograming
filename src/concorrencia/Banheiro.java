package concorrencia;

public class Banheiro {
	
	
	
	public void fazNumero1() {
		
		String nomeThread = Thread.currentThread().getName();
		
		System.out.println(nomeThread + " batendo na porta!");
		
		
		//desta maneira, o acesso ao banheiro fica irrestrito e implementado de maneira incorreta
		//pois o acesso não está sincronizado e o método pode ser executado pois mais de uma Thread ao mesmo tempo
		//esta é a chamada região crítica do código em que duas Threads não podem entrar ao mesmo tempo
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
		
		//esta é a maneira de sincronizar o acesso das threads ao método
		
		synchronized (this) {
			
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
			
			System.out.println();
		
		}	
	}
	
	
	public void tomarBanho() { //uma outra maneira de sincronizar o método inteiro seria alterar
								//a declaração do método para 'public synchronized tomarBanho()'
		
		String nomeThread = Thread.currentThread().getName();
		
		System.out.println(nomeThread + " batendo na porta!");
		
		synchronized (this) {
		
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
			
			System.out.println();
		}
	}
	
	

}
