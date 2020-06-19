package concorrencia;

public class FazerXixi implements Runnable {

	private Banheiro banheiro;

	
	public FazerXixi(Banheiro banheiro) {

		this.banheiro = banheiro;

	}

	@Override
	public void run() {
		
		banheiro.fazNumero1();
		
	}

}
