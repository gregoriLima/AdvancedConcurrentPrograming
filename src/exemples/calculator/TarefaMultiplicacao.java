package exemples.calculator;

import java.math.BigInteger;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TarefaMultiplicacao implements Runnable {

	private JTextField primeiro;
   	private JTextField segundo;
    private JLabel resultado;
	
    public TarefaMultiplicacao(JTextField primeiro, JTextField segundo, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.resultado = resultado;
	}
    
	
	@Override
	public void run() { //O construtor de Thread recebe um runnable, recebe algo que roda
		long valor1 = Long.parseLong(primeiro.getText());
        long valor2 = Long.parseLong(segundo.getText());
        
        BigInteger calculo = new BigInteger("0");

        for (int i = 0; i < valor1; i++) {
            for (int j = 0; j < valor2; j++) {
                calculo = calculo.add(new BigInteger("1"));
            }
        }

        resultado.setText(calculo.toString());

	}

}



//Herdar direto de thread para apenas sobreescrever o método run() não é uma boa prática
class TarefaMultiplicacaoThread extends Thread {

	private JTextField primeiro;
   	private JTextField segundo;
    private JLabel resultado;
	
    public TarefaMultiplicacaoThread(JTextField primeiro, JTextField segundo, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.resultado = resultado;
	}
    
	
	@Override
	public void run() { //A classe thread já implementa Runnable, então pode-se herdar Thread e sobreescrever o método run()
		long valor1 = Long.parseLong(primeiro.getText());
        long valor2 = Long.parseLong(segundo.getText());
        
        BigInteger calculo = new BigInteger("0");

        for (int i = 0; i < valor1; i++) {
            for (int j = 0; j < valor2; j++) {
                calculo = calculo.add(new BigInteger("1"));
            }
        }

        resultado.setText(calculo.toString());

	}
}
