package carro;

import java.util.Scanner;

class Car {
    int pass;
    int passMax;
    int gas;
    int gasMax;
    int km;
    
    Car() {
        this.passMax = 2;
        this.gasMax = 100;
    }

    void enter() {
        if (this.pass < 2) {
            this.pass++;
        } else {
            System.out.println("fail: limite de pessoas atingido");
        }
    }

    void leave() {
        if (this.pass > 0) {
            this.pass--;
        } else {
            System.out.println("fail: nao ha ninguem no carro");
        }
    }
    
    void fuel( int comb ) {
        this.gas += comb;
        if ( this.gas > this.gasMax ) {
            this.gas = this.gasMax;
        }
    }
    
    public void drive(int km) {
        if (this.pass == 0) {
            System.out.println("fail: nao ha ninguem no carro");
        } else if (this.gas == 0) {
            System.out.println("fail: tanque vazio");
        } else if (this.gas >= km) {
            this.km += km;
            this.gas -= km;
        } else {
            System.out.println("fail: tanque vazio apos andar " + this.gas + " km");
            this.km += this.gas;
            this.gas = 0;
        }
    }
    
    void show() {
        System.out.println(
            "pass: " + this.pass + ", " +
            "gas: " + this.gas + ", " +
            "km: " + this.km
            );
    }
}

public class Main {
    public static void main(String[] a) {
        Car car = new Car();
        
        while (true) {
            String line = input();
            write("$" + line);
            String args[] = line.split(" ");

            if      (args[0].equals("end"))   { break;                                }
            else if (args[0].equals("show"))  { car.show(); } 
            else if (args[0].equals("leave")) { car.leave();                          }
            else if (args[0].equals("drive")) { car.drive((int) number(args[1]));     }
            else if (args[0].equals("fuel"))  { car.fuel((int) number(args[1]));      }
            else                              { write("fail: comando invalido");}
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static String  input()              { return scanner.nextLine(); }
    static double  number(String value) { return Double.parseDouble(value); }
    static void    write(String value)  { System.out.println(value); }
}