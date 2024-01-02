package media;

import java.util.Scanner;

class Aluno{
    String nome;
    double nota1;
    double nota2;
    double nota3;
    double media;
    
    void leitura(){
        Scanner sc = new Scanner(System.in);
        this.nome = sc.nextLine();
        this.nota1 = sc.nextDouble();
        this.nota2 = sc.nextDouble();
        this.nota3 = sc.nextDouble();
        sc.close();
    }
    
    void media(){
        media = (nota1 + nota2 + nota3)/3;
        System.out.printf("%.2f%n", this.media);
    }
}

public class Media{
    public static void main(String[] args){
        Aluno alu = new Aluno();
        
        alu.leitura();
        alu.media();
    }
}
