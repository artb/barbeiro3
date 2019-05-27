package com.company.Barbeiro;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Barbearia {

    public int qntd_clientes;
    public ArrayList<Cliente> filaClientes;
    public ArrayList<Barbeiro> funcionarios;
    public int capacidade;
    public int disponiveis;

    public Barbearia(int capacidade){
        this.filaClientes = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.capacidade = capacidade;

    }

    public void adicionarBarbeiro(Barbeiro barbeiro){
        this.funcionarios.add(barbeiro);
        this.disponiveis = this.funcionarios.size();
    }

    public void clientesDia(int clientes){

        this.qntd_clientes = clientes;
    }

    public int getFilaSize(){
        return this.filaClientes.size();
    }

    public int clientesAgendados(){
        return this.qntd_clientes;
    }

    public void decrementaClientes(){
        this.qntd_clientes --;
    }

    public void barbeiroBusy(){
        this.disponiveis --;
    }

    public void barbeiroFree(){
        this.disponiveis ++;
    }

    public void avisaBarbeiros(){
        for(Barbeiro barbeiro: funcionarios){
            synchronized (barbeiro){
                notifyAll();
            }
        }
    }

    public synchronized void clienteEntrou(Cliente cliente){
        //esse metodo so pode ser lido por uma thread por vez
        System.out.println("[CLI]>>Cliente " + cliente.id + " entrou na Barbearia.");
        if(this.filaClientes.size() <= this.capacidade){
            if(this.filaClientes.size() == 0 && this.disponiveis > 0){
                System.out.println("[CLI]>>Cliente " + cliente.id + " deu sorte e nao precisou sentar.");
                this.filaClientes.add(cliente);
                avisaBarbeiros();
            }else{
                this.filaClientes.add(cliente);
                System.out.println("[CLI]>>Cliente " + cliente.id + " sentou na cadeira de posicao [" + filaClientes.size() + "].");
                avisaBarbeiros();
            }
            //A thread que estava executando ja fez a alteracao que precisava agr ela libera o lock
            notifyAll();

        } else{
            System.out.println("[CLI]>>Cliente " + cliente.id + " nao conseguiu sentar e foi embora.");
            cliente.foiPraCasa();
            this.decrementaClientes();
        }

    }

    public synchronized Cliente atendeCliente(long barbeiroID){
        if(this.filaClientes.size() > 0){
            //Tem alguem para ser atendido pelo nosso barbeiro
            barbeiroBusy();
            Cliente cliente = this.filaClientes.get(0);
            this.filaClientes.remove(0);
            //olha que interessante, eu ja peguei o cabeca da fila, entao eu ja posso liberar os outros barbeiros a trabalharem
            notifyAll();
            barbeiroFree();
            this.decrementaClientes();
            return cliente;
        } else{
            if(this.qntd_clientes == 0){
                notifyAll();
                return null;
            }else{
                System.out.println("[SYS]>Barbeiro " + barbeiroID + " indo dormir....");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }


}
