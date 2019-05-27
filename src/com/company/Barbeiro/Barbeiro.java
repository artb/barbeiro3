package com.company.Barbeiro;

public class Barbeiro implements Runnable {

    public Barbearia fila;
    public long id;
    public int status;

    public Barbeiro(Barbearia fila){
        this.fila = fila;
    }


    @Override
    public void run() {
        this.id = Thread.currentThread().getId();
        System.out.println("[SYS]>Barbeiro " + this.id + " foi criado.");
        while(this.fila.clientesAgendados() > 0 ){
            /*
            Parece estranho, mas a ideia eh assim: eu vou ter varios barbeiros querendo um cliente
            so que como nao eh o barbeiro que esta na zona critica, eu nao vou por ele pra dormir dentro desta classe
            -> Eu durmo um barbeiro quando ele tenta pegar um cliente e nao tem ngm na fila ainda
            O metodo getCliente eh syncronized, ou seja, apenas uma thread vai poder manusear por vez
             */
//            this.fila.barbeiroBusy();
            this.fila.atendeCliente(this.id);
//            this.fila.barbeiroFree();

        }
        System.out.println("[BAR]>Barbeiro " + this.id + " batendo o ponto e indo pra casa.");
    }
}
