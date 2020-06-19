package concorrenciaReal;

public class AdicionaElemento implements Runnable {

	private Lista lista;
	private int numeroThread;
	
	
	public AdicionaElemento(Lista lista, int numeroThread) {
		this.lista = lista;
		this.numeroThread = numeroThread;
	}


	@Override
	public void run() {
		 //populando a array de Lista
		for(int x=0; x<10; x++) {
			lista.adicionaElementos("Thread número " + numeroThread + " - " + x);
		}
		
	}

}
