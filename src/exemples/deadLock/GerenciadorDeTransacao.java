package exemples.deadLock;

public class GerenciadorDeTransacao {

	
	public void begin() {
		
		System.out.println("Come�ando a transa��o");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
