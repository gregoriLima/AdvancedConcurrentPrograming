package br.gregori.servidor;

//Classe exemplo de como o cache de vari�veis pode por em risco o c�digo quando se trabalha com threads

public class ServidorDeTestes {

    private volatile boolean estaRodando = false; //sem o volatile, as threads em cache o valor desta vari�vel
    												//n�o acessando ela na mem�ria principal

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTestes servidor = new ServidorDeTestes();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
    	
    	try {
			 Thread thread = new Thread(new Runnable() { // A classe an�nima � criada pela JVM com o nome ServidorTestes$1

					public void run() {
						System.out.println("Servidor come�ando, estaRodando = " + estaRodando);

						while (!estaRodando) {}
						 // a thread fica trancada dentro deste while, pois a vari�vel estaRodando
							// � cacheada pela thread do sistema operacional a qual esta thread foi mapeada
							// assim o m�todo main s� altera a vari�vel estaRodando da thread main
							// para corrigir isso usa-se volatile ou Atomic
						// uma alternativa ao volatile e ao Atomic seria utilizar m�todos sincronizados
						// para o acesso a esta vari�vel
						
						System.out.println("Servidor rodando, estaRodando = " + estaRodando);

						if(estaRodando) {
							throw new IllegalAccessError(" Erro na pilha da Thread n�o tratado!");
						}
						
						while (estaRodando) {}

						System.out.println("Servidor terminando, estaRodando = " + estaRodando);

						
					}
				});
			 thread.start();
    	} catch(Exception e ) {System.out.println(e);} //este catch n�o pega a exception lan�ada dentro da thread
    								//pois as exception s�o lan�adas at� chegar na thread principal
    								//ao se instanciar uma nova thread, uma nova pilha de execu��es � criada
//    							|          |        | !Except  |  <- a exception � lan�ada at� a thread base da pilha de chamadas 
//    							|  rodar   |        |   run    |
//    							|   main   |        |   run    |
//    							|__________|        |__________|
//    						     Thread Main       Thread An�nima
    
    	//com o setDefaultUncaughtExceptionHandler a exception que n�o � tratada na thread � tratada por outra classe
    	Thread.setDefaultUncaughtExceptionHandler(new TratadorDeExceptions());
    	
    
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando = true;

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando = false;        
    }
}