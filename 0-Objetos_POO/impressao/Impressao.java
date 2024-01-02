package impressao;

import java.util.Scanner;

class Aluno{
    String nome;
    int matricula;
    String disciplina;
    double nota;
    
    void leitura(){
        Scanner sc = new Scanner(System.in);

        this.nome = sc.nextLine();
        this.matricula = sc.nextInt();
        sc.nextLine();
        this.disciplina = sc.nextLine();
        this.nota = sc.nextDouble();
        sc.close();
    }
    
    void imprimir(){
        System.out.printf("Nome = %s%n", this.nome);
        System.out.printf("Matrícula = %d%n", this.matricula);
        System.out.printf("Disciplina = %s%n", this.disciplina);
        System.out.printf("Nota = %.1f", this.nota);
    }
}

public class Impressao{
    public static void main(String[] args){
        Aluno alu = new Aluno();
        
        alu.leitura();
        alu.imprimir();
    }
}

