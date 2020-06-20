package br.gregori.servidor;

//Classe exemplo de como o cache de variáveis pode por em risco o código quando se trabalha com threads

public class ServidorDeTestes {

    private volatile boolean estaRodando = false; //sem o volatile, as threads em cache o valor desta variável
    												//não acessando ela na memória principal

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTestes servidor = new ServidorDeTestes();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
    	
    	try {
			 Thread thread = new Thread(new Runnable() { // A classe anônima é criada pela JVM com o nome ServidorTestes$1

					public void run() {
						System.out.println("Servidor começando, estaRodando = " + estaRodando);

						while (!estaRodando) {}
						 // a thread fica trancada dentro deste while, pois a variável estaRodando
							// é cacheada pela thread do sistema operacional a qual esta thread foi mapeada
							// assim o método main só altera a variável estaRodando da thread main
							// para corrigir isso usa-se volatile ou Atomic
						// uma alternativa ao volatile e ao Atomic seria utilizar métodos sincronizados
						// para o acesso a esta variável
						
						System.out.println("Servidor rodando, estaRodando = " + estaRodando);

						if(estaRodando) {
							throw new IllegalAccessError(" Erro na pilha da Thread não tratado!");
						}
						
						while (estaRodando) {}

						System.out.println("Servidor terminando, estaRodando = " + estaRodando);

						
					}
				});
			 thread.start();
    	} catch(Exception e ) {System.out.println(e);} //este catch não pega a exception lançada dentro da thread
    								//pois as exception são lançadas até chegar na thread principal
    								//ao se instanciar uma nova thread, uma nova pilha de execuções é criada
//    							|          |        | !Except  |  <- a exception é lançada até a thread base da pilha de chamadas 
//    							|  rodar   |        |   run    |
//    							|   main   |        |   run    |
//    							|__________|        |__________|
//    						     Thread Main       Thread Anônima
    
    	//com o setDefaultUncaughtExceptionHandler a exception que não é tratada na thread é tratada por outra classe
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