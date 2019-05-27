package com.company.Barbeiro;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner in = new Scanner(System.in);
        System.out.println("-> Digite o numero de barbeiros:");
        int num_barbeiros = in.nextInt();
        System.out.println("-> Digite o tamanho da fila:");
        int tam_fila = in.nextInt();
        System.out.println("-> Digite o numero de clientes do dia:");
        int num_clientes = in.nextInt();

        Barbearia barbearia = new Barbearia(tam_fila);
        barbearia.clientesDia(num_clientes);

        for(int i = 0; i < num_barbeiros; i++){
            Barbeiro barbeiro = new Barbeiro(barbearia);
            barbearia.adicionarBarbeiro(barbeiro);
            Thread t = new Thread(barbeiro);
            t.start();
        }

        for(int i = 0; i < num_clientes;i++){
            Cliente cliente = new Cliente(barbearia);
            Thread t = new Thread(cliente);
            t.start();

        }


    }
}
