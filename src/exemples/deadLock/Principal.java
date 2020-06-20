package exemples.deadLock;

public class Principal {

	public static void main(String[] args) {

		GerenciadorDeTransacao tx = new GerenciadorDeTransacao();
		PoolDeConexao pool = new PoolDeConexao();
		
		//---- Exemplo de DeadLock
		//Dessa maneira a execu��o nunca termina, pois uma thread tranca a execu��o da outra
		//classe onde primeiro � pegada a chave da pool e depois de tx
		new Thread(new TarefaAcessaDB(pool, tx), " 1 ").start();
		
		//classe com a ordem invertida, onde primeiro � pegada a chave de tx e depois da pool
		new Thread(new TarefaAcessaDBOrdemInversa(tx, pool), " 2 ").start();
		//---- � poss�vel ver as threads bloqueadas no jconsole

	}

}
