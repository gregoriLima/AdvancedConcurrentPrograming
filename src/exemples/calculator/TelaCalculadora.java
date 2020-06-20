package exemples.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.*;
import javafx.event.*;



public class TelaCalculadora {

	public static void main(String[] args) {

		//implementa uma simples janela com dois campos que s�o multiplicados de maneira pouco perform�tica propositalmente
		
		JFrame janela = new JFrame("Multiplica��o paralela");
		
		JTextField primeiro = new JTextField(10);
		JTextField segundo = new JTextField(10);
		JButton botao = new JButton(" = ");
		JLabel resultado = new JLabel("    ?    ");
		
		botao.addActionListener(new AcaoBotao(primeiro, segundo, resultado));
		
		JPanel painel = new JPanel();
        painel.add(primeiro);
        painel.add(new JLabel("x"));
        painel.add(segundo);
        painel.add(botao);
        painel.add(resultado);

        janela.add(painel);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setVisible(true);

	}

}




class AcaoBotao implements ActionListener{
	
	private JTextField primeiro;
    private JTextField segundo;
    private JLabel resultado;
    
	AcaoBotao(JTextField primeiro, JTextField segundo, JLabel resultado){
		
		this.primeiro = primeiro;
        this.segundo = segundo;
        this.resultado = resultado;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		//obs. Threads s�o mapeadas para Threads do sistema operacional pela JVM
		
		//Executando com threads implementando Runnable na classe tarefa
		TarefaMultiplicacao tarefa = new TarefaMultiplicacao(primeiro, segundo, resultado);
		Thread threadMultiplicador = new Thread(tarefa); //Thread recebe um Runnable
		threadMultiplicador.start(); //come�a a rodar a thread
		
		//---
		//Executando diretamente a classe que herda de Thread que � runabble
		//N�o � uma boa pr�tica, pois n�o � aproveitado o polimorfismo e al�m disso, a classe TarefaMultiplicacaoThread herda um monte de
		//m�todos n�o utilizados
		//TarefaMultiplicacaoThread tarefaThread = new TarefaMultiplicacaoThread(primeiro, segundo, resultado);
		//tarefaThread.start(); //como a classe extende Thread que � runnable, e sobreescreve o m�todo run(), pode-se dar o start() diretamente
		//---
		
		
		new Thread( () -> System.out.println("a") ).start();
		
		
		
		//executando desta maneira, com n�meros muito grandes, os campos ficam travados at� a finaliza��o do c�lculo
		//a melhor maneira � utilizar threads
		/*
		long valor1 = Long.parseLong(primeiro.getText());
        long valor2 = Long.parseLong(segundo.getText());
        
        BigInteger calculo = new BigInteger("0");

        for (int i = 0; i < valor1; i++) {
            for (int j = 0; j < valor2; j++) {
                calculo = calculo.add(new BigInteger("1"));
            }
        }

        resultado.setText(calculo.toString());
        */
		
	}
	
	
	
	
	
}