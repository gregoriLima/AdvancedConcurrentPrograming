package concorrencia;

public class LimpezaDoBanheiro implements Runnable {

	private Banheiro banheiro;
	
	public LimpezaDoBanheiro(Banheiro banheiro) {
	
			this.banheiro = banheiro;
	
	}

	@Override
	public void run() {
		while(true) {
		banheiro.limpaBanheiro();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

}
