package deadLock;

public class TarefaAcessaDBOrdemInversa implements Runnable {

	GerenciadorDeTransacao tx;
	PoolDeConexao pool;

	public TarefaAcessaDBOrdemInversa(GerenciadorDeTransacao tx, PoolDeConexao pool) {

		this.tx = tx;
		this.pool = pool;

	}

	@Override
	public void run() {

		String nome = Thread.currentThread().getName();
		
		// aguardando a chave/mutex de tx
		synchronized (tx) { // como os objetos são compartilhados entre várias threads, é preciso
							// sincronizar o acesso.

			System.out.println(nome + "Estou com a chave da tx");
			tx.begin();

			// aguardando a chave/mutex de pool
			synchronized (pool) {

				System.out.println(nome + "Estou com a chave da pool");
				pool.getConnection();

			}

		}

	}
}
