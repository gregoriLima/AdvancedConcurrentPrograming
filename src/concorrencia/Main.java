package concorrencia;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Banheiro banheiro = new Banheiro();
		
		new Thread(new FazerXixi(banheiro), "Jo�o").start();; //Thread nomeada como Jo�o
		new Thread(new TomarBanho(banheiro), "Pedro").start(); //Thread nomeada como Pedro
		
	}

}
