package relogio;

import java.util.*;

class Time{
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second){  
        this.setHour(hour);
        this.setMinute(minute);
        this.setSecond(second);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setHour(int hour){
        if(hour >= 0 && hour <= 23)
            this.hour = hour;
        else
            System.out.println("fail: hora invalida");
    }

    public void setMinute(int minute){
        if(minute >= 0 && minute <= 59)
            this.minute = minute;
        else
            System.out.println("fail: minuto invalido");
    }

    public void setSecond(int second){
        if(second >= 0 && second <= 59)
            this.second = second;
        else
            System.out.println("fail: segundo invalido");
    }

    public void nextSecond(){
        this.second++;
        if(this.second >= 60){
            this.second %= 60;
            this.minute++;
        }
        if(this.minute >= 60){
            this.minute %= 60;
            this.hour++; 
        }
        if(this.hour >= 24){
            this.hour %= 24;
        }
    }

    public String toString(){
        return String.format("%02d:%02d:%02d", this.hour, this.minute, this.second);
    }
}

public class Main {
    public static void main(String[] a) {
        Time time = new Time(0, 0, 0);
        
        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");

            if      (args[0].equals("end"))   { 
                break; 
            }
            else if (args[0].equals("show"))  { 
                System.out.println(time); 
            }
            else if (args[0].equals("next"))  { 
                time.nextSecond(); 
            }
            else if (args[0].equals("set")) {
                time.setHour((int)number(args[1]));
                time.setMinute((int)number(args[2]));
                time.setSecond((int)number(args[3]));
            }
            else if (args[0].equals("init")) {
                time = new Time((int)number(args[1]), (int)number(args[2]), (int)number(args[3]));
            }
            else { 
                write("fail: comando invalido"); 
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    private static String  input()              { return scanner.nextLine(); }
    private static double  number(String value) { return Double.parseDouble(value); }
    private static void    write(String value)  { System.out.println(value); }
}
