package exemples.concorrenciaReal;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Lista lista = new Lista();
		
		for(int x = 0; x<10; x++) {
			
			new Thread(new AdicionaElemento(lista, x)).start();
			
		}
		
		
		
		//Thread.sleep(5000);
		
		
		
		
		
		
//		for(int x = 0; x<lista.tamanho(); x++) {
//			
//			System.out.println(lista.pegaElementos(x));
//			
//		}


		//substituindo o for de cima por uma Thread
		new Thread(new ImprimeElementos(lista)).start();;

		
		//em Java a classe ArrayList não é threadSafe, mas pode ser 
		//utilizando-se o método estático 'Collections.synchronizedLis(new ArrayList<>());'
		//ou utilizando-se a classe Vector que já tem os métodos add() sincronizados
		

	}

}
