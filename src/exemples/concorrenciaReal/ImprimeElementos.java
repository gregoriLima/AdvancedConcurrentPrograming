package exemples.concorrenciaReal;

public class ImprimeElementos implements Runnable {

	private Lista lista;

	public ImprimeElementos(Lista lista) {
		// TODO Auto-generated constructor stub
		this.lista = lista;
	}

	@Override
	public void run() {

		synchronized (lista) {

		if(!lista.estaCheia()) {	//s� espera se a lista estiver sendo preenchida
									//assim n�o ocorre de o thread ficar esperando e n�o ser notificado
			try {
				System.out.println("Aguardando a lista ser populada!");
				lista.wait();//Devolvendo a chave e aguardando a notifica��o de lista
				//caso n�o seja esperado o notify de lista, o esta classe imprime os valores da lista
				//antes de eles serem populados, sendo assim impresso v�rios nulls
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
			for (int x = 0; x < lista.tamanho(); x++) {

				System.out.println(x + " - " + lista.pegaElementos(x));

			}
		}
	}

}
