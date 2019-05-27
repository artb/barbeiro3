package com.company.Barbeiro;

public class Cliente implements Runnable {

    public long id;
    public Barbearia fila;
    public boolean status = false;
    public boolean atendido = false;

    public Cliente(Barbearia fila){
        this.fila = fila;

    }

    public synchronized void foiPraCasa(){
        this.atendido = true;
        notifyAll();
    }

    public synchronized void cortouCabelo(){
        this.atendido = true;
        this.status = true;
        notifyAll();
    }

    @Override
    public synchronized void run() {
        this.id = Thread.currentThread().getId();
        System.out.println("[SYS]>Cliente " + id + " foi criado.");
        this.fila.clienteEntrou(this);
        while(!this.atendido){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(this.status){
            System.out.println("[SYS]>Cliente " + id + " esta sendo finalizado e ele cortou o cabelo.");
        } else{
            System.out.println("[SYS]>Cliente " + id + " esta sendo finalizado e nao conseguiu o que queria.");
        }

    }
}
