package com.company.LeitorEscritor.idea;

import static java.lang.Thread.sleep;

public class BancoDoArthur {

    public int saldo;
    public boolean bloqueado = false;


    public BancoDoArthur(int saldo){
        this.saldo = saldo;
    }

    public int valorFinal(){
        return this.saldo;
    }

    public void escreveSemPrioridade(long eID, int novoValor){
        int macaco = this.saldo + novoValor;
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long startTimeMs = System.currentTimeMillis();
        System.out.println("[SYS]>> O Escritor " + eID + " entrou na regiao critica!!");
        System.out.println("[ESCRITOR " + eID + "]>> Vou mudar o seu saldo de: " + this.saldo + " para: " + macaco + " no instante "
                + startTimeMs % 1000000);
        this.saldo = macaco;
//        notifyAll();
//        try {
//            sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("[SYS]>> O Escritor " + eID + " esta saindo da regiao critica.");
        System.out.println("[ESCRITOR " + eID + "]>> Terminei meu trabalho, estou finalizando.");

    }

    public synchronized void escreveComPrioridade(long eID, int novoValor){
        this.bloqueado = true;
        System.out.println("[SYS]>> O Escritor " + eID + " entrou na regiao critica, TRAVANDO leitores!!");
        int macaco = this.saldo + novoValor;
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long startTimeMs = System.currentTimeMillis();
        System.out.println("[SYS]>> O Escritor " + eID + " entrou na regiao critica!!");
        System.out.println("[ESCRITOR " + eID + "]>> Vou mudar o seu saldo de: " + this.saldo + " para: " + macaco + " no instante "
                + startTimeMs % 1000000);
        this.saldo = macaco;
        this.bloqueado = false;
        notifyAll();
//        try {
//            sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("[SYS]>> O Escritor " + eID + " esta saindo da regiao critica.");
        System.out.println("[ESCRITOR " + eID + "]>> Terminei meu trabalho, estou finalizando.");


    }

    public int leSemPrioridade(){
//        try {
//            sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return this.saldo;
    }

    public int leSafe(long lID){
        if(this.bloqueado){
            System.out.println("[LEITOR " + lID + "]>> FUI BLOQUEADO!!!! Nao posso ler enquanto um escritor esta ativo.");
            while (!this.bloqueado){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return saldo;
        }
        return saldo;
    }

}
