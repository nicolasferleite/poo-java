package budega;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


class Pessoa {
    private String nome;
    Pessoa(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}

class Mercantil {
    private ArrayList < Pessoa > caixas;
    private LinkedList < Pessoa > esperando;

    Mercantil(int qtd_caixas) {
        esperando = new LinkedList<>();
        caixas = new ArrayList<>(qtd_caixas);
        for(int i = 0; i < qtd_caixas; i++){
            caixas.add(null);
        }
    }
   
    public boolean validarIndice(int indice) {
        if(indice >=0 && indice < caixas.size()){
            return true;
        }
        return false;
    }
    public void chegar(Pessoa person) {
        esperando.add(person);
    }
    
    public boolean chamarNoCaixa(int indice) {
        if(validarIndice(indice)){
            if(caixas.get(indice)==null){
                if(!esperando.isEmpty()){
                    caixas.set(indice, esperando.remove());
                    return true;
                } else{
                    System.out.println("fail: sem clientes");
                    return false;
                }
            } else{
                System.out.println("fail: caixa ocupado");
                return false;
            }
        } else {
            System.out.println("fail: caixa inexistente");
            return false;
        }
    }
    
    public Pessoa finalizar(int indice) {
        if(validarIndice(indice)){
            if(caixas.get(indice) != null){
                Pessoa backup = caixas.get(indice);
                caixas.set(indice, null);
                return backup;
            } else{
                System.out.println("fail: caixa vazio");
                return null;
            }
        } else{
            System.out.println("fail: caixa inexistente");
            return null;
        }
    }
    
    public String toString() {
        String caixas = "Caixas: [";
        for (int i = 0; i < this.caixas.size(); i++) {
            if (this.caixas.get(i) == null) {
                caixas += "-----";
            } else {
                caixas += this.caixas.get(i).getNome();
            }
            if (i < this.caixas.size() - 1) {
                caixas += ", ";
            }
        }

        caixas += "]\n";

        String espera = "Espera: [";
        for (int i = 0; i < this.esperando.size(); i++) {
            espera += this.esperando.get(i).getNome();
            if (i < this.esperando.size() - 1) {
                espera += ", ";
            }
        }
        espera += "]";

        return caixas + espera;
    }
}



class Main {
    public static void main(String[] a) {
        Mercantil mercantil = new Mercantil(0);
        
        while(true){
            String line = input();
            write("$" + line);
            String args[] = line.split(" ");
            
            if(args[0].equals("end")){ break;}
            else if(args[0].equals("init")){ mercantil = new Mercantil(number(args[1])); }
            else if(args[0].equals("show")){ System.out.println(mercantil);}
            else if(args[0].equals("arrive")){ mercantil.chegar(new Pessoa(args[1])); }
            else if(args[0].equals("call")){ mercantil.chamarNoCaixa(number(args[1])); }
            else if(args[0].equals("finish")){ mercantil.finalizar(number(args[1])); }
            else {System.out.println("fail: comando invalido");}
            
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static String  input()              { return scanner.nextLine(); }
    static int  number(String value) { return Integer.parseInt(value); }
    static void    write(String value)  { System.out.println(value); }
}