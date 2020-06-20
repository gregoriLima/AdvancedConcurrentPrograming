package concorrenciaReal;

public class Lista {

	private String[] elementos= new String[100];
	private int indice = 0;
	
	
	//sem o synchronized, várias threaads acabam trabalhando sobre o mesmo
	//elemento da array fazendo com que alguns elementos fiquem null
	public synchronized void adicionaElementos(String elemento)	{
		
		this.elementos[indice] = elemento;
		
		indice++;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(indice == elementos.length-1) {
			System.out.println("Lista cheia, notificando!");
			this.notify(); // notificando que a lista está cheia para rodar a thread ImprimeElementos
		}
		
		
	}
	
	public int tamanho() {
		return this.elementos.length;
	}
	
	public String pegaElementos(int posicao) {
		return this.elementos[posicao];
	}
	
	public boolean estaCheia() {
		return elementos.length == indice;
	}
	
	
}
