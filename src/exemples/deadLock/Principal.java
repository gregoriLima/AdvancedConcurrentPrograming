package exemples.deadLock;

public class Principal {

	public static void main(String[] args) {

		GerenciadorDeTransacao tx = new GerenciadorDeTransacao();
		PoolDeConexao pool = new PoolDeConexao();
		
		//---- Exemplo de DeadLock
		//Dessa maneira a execução nunca termina, pois uma thread tranca a execução da outra
		//classe onde primeiro é pegada a chave da pool e depois de tx
		new Thread(new TarefaAcessaDB(pool, tx), " 1 ").start();
		
		//classe com a ordem invertida, onde primeiro é pegada a chave de tx e depois da pool
		new Thread(new TarefaAcessaDBOrdemInversa(tx, pool), " 2 ").start();
		//---- é possível ver as threads bloqueadas no jconsole

	}

}
