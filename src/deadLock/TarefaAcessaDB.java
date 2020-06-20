package deadLock;

public class TarefaAcessaDB implements Runnable {

	private PoolDeConexao pool;
	private GerenciadorDeTransacao tx;
	
		
	public TarefaAcessaDB(PoolDeConexao pool, GerenciadorDeTransacao tx) {

		this.pool = pool;
		this.tx = tx;
				
	}

	@Override
	public void run() {
		
		String nome = Thread.currentThread().getName();
		
			//aguardando a chave/mutex de pool
		synchronized (pool) { //como os objetos s�o compartilhados entre v�rias threads, � preciso sincronizar o acesso.
			
			System.out.println(nome + "Estou com a chave da pool");
			pool.getConnection();
			
			//aguardando a chave/mutex de tx
			synchronized (tx) {
				
				System.out.println(nome + "Estou com a chave da tx");
				tx.begin();
				
			}
			
			
		}
		
		
	}

}
