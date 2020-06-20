package br.gregori.servidor;

public class ServidorDeTestes {

    private boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTestes servidor = new ServidorDeTestes();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        new Thread(new Runnable() {

            public void run() {
                System.out.println("Servidor começando, estaRodando = " + estaRodando );

                while(!estaRodando) {} //a thread fica trancada dentro deste while, pois a variável estaRodando
                						//é cacheada pela thread do sistema operacional a qual esta thread foi mapeada
                						//assim o método main só altera a variável estaRodando da thread main
                						//para corrigir isso usa-se volatile ou Atomic
                //uma alternativa ao volatile e ao Atomic seria utilizar métodos sincronizados para o acesso a esta variável

                System.out.println("Servidor rodando, estaRodando = " + estaRodando );

                while(estaRodando) {}

                System.out.println("Servidor terminando, estaRodando = " + estaRodando );
            }
        }).start();
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