package calculadora;

import java.util.*;
import java.text.*;

class Calculadora{
    public int battery;
    public int batteryMax;
    public double display;

    public Calculadora(int batteryMax){
        this.battery = 0;
        this.batteryMax = batteryMax;
        this.display = 0.00;
    }

    public void charge(int valor){
        this.battery += valor;
        if(this.battery > this.batteryMax){
            this.battery = this.batteryMax;
        }
    }

    public boolean useBattery(){
        if(this.battery == 0){
            System.out.println("fail: bateria insuficiente");
            return false;
        }
        else{
            this.battery -= 1;
            return true;
        }
    }

    public void sum(int a, int b){
        if(useBattery()){
            this.display = a + b;
        }

    }

    public void division(int num, int den){
        if(!useBattery()){
            return;
        }
        if(den == 0){
            System.out.println("fail: divisao por zero");
        }
        else{
            this.display = (double) num / den;
        }
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("display = %s, battery = %d", df.format(this.display), this.battery);
    }
}

public class Main {
    static Calculadora calc = new Calculadora(0);
    public static void main(String[] args) {
        
        while (true) {
            String line = input();
            write("$" + line);
            String[] a = line.split(" ");

            if      (a[0].equals("end"))   { break;}
            else if (a[0].equals("init")) { calc = new Calculadora(number(a[1]));}
            else if (a[0].equals("show"))  { write(calc.toString());}
            else if (a[0].equals("sum")) { calc.sum(number(a[1]), number(a[2]));}
            else if (a[0].equals("div")) { calc.division(number(a[1]), number(a[2]));}
            else if (a[0].equals("charge"))  { calc.charge(number(a[1]));}
            else { write("fail: comando invalido");}
        }
        scanner.close();
    }

    static Scanner scanner = new Scanner(System.in);
    static String  input()              { return scanner.nextLine(); }
    static int number(String str) {return Integer.parseInt(str); }
    static void    write(String value)  { System.out.println(value); }
}
