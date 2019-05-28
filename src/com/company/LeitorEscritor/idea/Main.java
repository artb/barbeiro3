package com.company.LeitorEscritor.idea;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args){

        ArrayList<Thread> threads = new ArrayList<Thread>();
        Random rand = new Random();

        Scanner in = new Scanner(System.in);
        System.out.println("-> Digite o numero de leitores:");
        int num_leitores = in.nextInt();

        System.out.println("-> Digite o numero de escritores:");
        int num_escritores = in.nextInt();
        int[] valoresEscrever= new int[num_escritores];

        System.out.println("-> Insira os valores a serem escritos:");
        for(int i = 0 ; i < num_escritores; i++){
            valoresEscrever[i] = in.nextInt();
        }

        System.out.println("-> Insira o numero da questao (2 ou 3)");
        int operacao = in.nextInt();

        BancoDoArthur banco = new BancoDoArthur(200);

        for(int i = 0; i < num_leitores; i++){
            Leitor leitor = new Leitor(banco,operacao);
            Thread t = new Thread(leitor);
            threads.add(t);
        }

        for(int i = 0; i < num_escritores; i++){
            Escritor escritor = new Escritor(banco,valoresEscrever[i],operacao);
            Thread t = new Thread(escritor);
            threads.add(t);
        }
        int limite = threads.size();

        ArrayList<Integer> list = new ArrayList<Integer>(limite);
        for(int i = 0; i < limite; i++) {
            list.add(i);
        }

        for(int i = 0; i < limite;i++){
            int index = rand.nextInt(list.size());
            list.remove(index);
            Thread daVez = threads.get(index);
            long idT = daVez.getId();
//            System.out.println("[SYS]> A thread " + idT + " foi escolhida aleatoriamente.");
            daVez.start();
            threads.remove(daVez);

        }


    }
}
