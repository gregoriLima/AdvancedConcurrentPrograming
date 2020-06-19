package concorrencia;

public class Banheiro {
	
	
	
	public void fazNumero1() {
		
		String nomeThread = Thread.currentThread().getName();
		
		System.out.println(nomeThread + " batendo na porta!");
		
		
		//desta maneira, o acesso ao banheiro fica irrestrito e implementado de maneira incorreta
		//pois o acesso n�o est� sincronizado e o m�todo pode ser executado pois mais de uma Thread ao mesmo tempo
		//esta � a chamada regi�o cr�tica do c�digo em que duas Threads n�o podem entrar ao mesmo tempo
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
		
		//esta � a maneira de sincronizar o acesso das threads ao m�todo
		
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
	
	
	public void tomarBanho() { //uma outra maneira de sincronizar o m�todo inteiro seria alterar
								//a declara��o do m�todo para 'public synchronized tomarBanho()'
		
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
