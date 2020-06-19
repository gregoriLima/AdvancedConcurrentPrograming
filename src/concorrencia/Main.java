package concorrencia;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Banheiro banheiro = new Banheiro();
		
		//ao se dizer que esta Thread � um daemon, ela s� existe quando houverem outras Threads rodando.
		Thread limpeza = new Thread(new LimpezaDoBanheiro(banheiro), "Equipe");
		limpeza.setDaemon(true);
		limpeza.start();
		
		
		new Thread(new FazerXixi(banheiro), "Jo�o").start();; //Thread nomeada como Jo�o
		
		new Thread(new TomarBanho(banheiro), "Pedro").start(); //Thread nomeada como Pedro
		
		new Thread(new FazerXixi(banheiro), "Maria").start();; //Thread nomeada como Jo�o
		
		new Thread(new TomarBanho(banheiro), "Jos�").start(); //Thread nomeada como Pedro
		
	}

}
