package com.company.Barbeiro;

import static java.lang.Thread.sleep;

public class Barbeiro implements Runnable {

    public Barbearia fila;
    public long id;
    public int status;

    public Barbeiro(Barbearia fila){
        this.fila = fila;
    }

    public void trabalha(Barbearia fila){
        Cliente cliente;
        System.out.println("[SYS]>Barbeiro " + this.id + " acordou...");
        cliente = fila.atendeCliente(this.id);
        if(cliente != null){
            System.out.println("[BAR]>>O barbeiro " + this.id + " esta atendendo o cliente " + cliente.id);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cliente.cortouCabelo();

        }
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
            trabalha(this.fila);
//            this.fila.barbeiroFree();

        }
        System.out.println("[BAR]>Barbeiro " + this.id + " batendo o ponto e indo pra casa.");
    }
}
