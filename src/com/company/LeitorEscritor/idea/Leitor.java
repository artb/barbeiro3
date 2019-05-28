package com.company.LeitorEscritor.idea;

import static java.lang.Thread.sleep;

public class Leitor implements Runnable {

    public long lID;
    public BancoDoArthur banco;
    public int operacao;

    public Leitor(BancoDoArthur banco, int operacao) {
        this.banco = banco;
        this.operacao = operacao;
    }

    public void trabalha(int valor){
        long startTimeMs = System.currentTimeMillis();
        System.out.println("[SYS]>> O Leitor " + this.lID + " entrou na regiao critica!!");
        System.out.println("[LEITOR " + this.lID + "]>> O seu saldo eh de: " + valor + " no instante "
                + startTimeMs % 1000000);
//        try {
//            sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("[SYS]>> O Leitor " + lID + " esta saindo da regiao critica.");
        System.out.println("[LEITOR " + lID + "]>> Terminei meu trabalho, estou finalizando.");

    }

    @Override
    public void run() {
        this.lID = Thread.currentThread().getId();
        System.out.println("[SYS]>Leitor " + lID + " foi criado");
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int saldo = 0;
        if(this.operacao == 2){
            saldo = this.banco.leSemPrioridade();
            trabalha(saldo);
        }else{
            saldo = this.banco.leSafe(this.lID);
            trabalha(saldo);
        }

    }
}
