package exemples.concorrencia;

public class TomarBanho implements Runnable {

	private Banheiro banheiro;
	
	public TomarBanho(Banheiro banheiro) {
		this.banheiro = banheiro;
	}

	@Override
	public void run() {

		banheiro.tomarBanho();

	}

}
