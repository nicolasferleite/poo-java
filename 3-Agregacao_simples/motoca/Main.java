package motoca;

import java.util.*;

class Person{
    private int age;
    private String name;

    public Person(String name, int age){
        this.age = age;
        this.name = name;
    }

    public int getAge(){
        return this.age;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name + ":" + this.age;
    }
}

class Motorcycle{
    private Person person;
    private int power;
    private int time;

    public Motorcycle(int power){
        this.person = null;
        this.power = power;
        this.time = 0;
    }

    public Person getPerson(){
        return this.person;
    }

    public int getPower(){
        return this.power;
    }

    public int getTime(){
        return this.time;
    }

    public boolean insertPerson(Person person){
        return true;
    }

    public Person remove(){
        return null;
    }

    public void buyTime(int tempo){
        this.time += tempo;
    }

    public void drive(int tempo){
        if(this.time == 0){
            System.out.println("fail: buy time first");
        }
        else if(this.person == null){
            System.out.println("fail: empty motorcycle");
        }
        else if(this.person.getAge() > 10){
            System.out.println("fail: too old to drive");
        }
        else if(this.time - tempo < 0){
            this.time = tempo - this.time;
            System.out.println("fail: time finished after " + this.time + " minutes");
            this.time = 0;
        }
        else{
            this.time -= tempo;
        }
    }

    public boolean enter(Person pessoa) {
        if(person == null){
            this.person = pessoa;
            return true;
        }
        else{
            System.out.println("fail: busy motorcycle");
            return false;
        }
    }

    public Person leave() {
        if(person != null){
            Person pessoa = person;
            person = null;
            return pessoa;
        }
        else{
            System.out.println("fail: empty motorcycle");
            return null;
        }
    }

    public void honk(){
        System.out.printf("P");
        for(int i = 1; i <= this.power; i++){
            System.out.printf("e");
        }
        System.out.println("m");
    }

    public String toString(){
        String saida = "power:" + this.power + ", time:" + this.time + ", person:";
        if (person != null)
            saida += "(" + person + ")";
        else
            saida += "(empty)";
        return saida;
    }
}

public class Main {
    static Motorcycle motoca = new Motorcycle(1);

    public static void main(String[] args) {    
        while(true) {
            String line = input();
            args = line.split(" ");
            write('$' + line);

            if      (args[0].equals("show"))     { System.out.println(motoca);                         }
            else if (args[0].equals("init"))     { motoca = new Motorcycle(number(args[1]));           }  
            else if (args[0].equals("enter"))    { motoca.enter(new Person(args[1], number(args[2]))); }
            else if (args[0].equals("buy"))      { motoca.buyTime(number(args[1]));                    }
            else if (args[0].equals("drive"))    { motoca.drive(number(args[1]));                      }
            else if (args[0].equals("honk"))     { motoca.honk();                                      }
            else if (args[0].equals("end"))      { break;                                              }
            else if (args[0].equals("leave"))    {
                Person person = motoca.leave();
                if(person != null) {
                    System.out.println(person.toString());
                }
            }
            else {
                System.out.println("fail: comando invalido");
            }
        }
    }

    static Scanner scanner = new Scanner(System.in);
    public static String input()           { return scanner.nextLine();    }
    public static void write(String value) { System.out.println(value);    }
    public static int number(String str)   { return Integer.parseInt(str); }
}