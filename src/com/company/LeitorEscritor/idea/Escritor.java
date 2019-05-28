package com.company.LeitorEscritor.idea;

import static java.lang.Thread.sleep;

public class Escritor implements Runnable{

    public long eID;
    public BancoDoArthur banco;
    public int input;
    public int operacao;

    public Escritor(BancoDoArthur banco, int input, int operacao){
        this.input = input;
        this.banco = banco;
        this.operacao = operacao;
    }

    @Override
    public void run() {
        this.eID = Thread.currentThread().getId();
        System.out.println("[SYS]>Escritor " + eID + " foi criado");
//        try {
//            sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(this.operacao == 2){
            this.banco.escreveSemPrioridade(this.eID,this.input);
        }else{
            this.banco.escreveComPrioridade(this.eID,this.input);
        }

    }
}
