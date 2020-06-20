package exemples.deadLock;

public class PoolDeConexao {
	
	public String getConnection() {
	
		System.out.println("Emprestando conexão...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "connection";
		
	}
}
