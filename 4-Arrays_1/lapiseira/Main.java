//1.Fiz tudo sozinho.
//2.Fiquei um pouco confuso na parte de puxar informações de uma classe para a 
//outra, mas consegui fazer.
//3. Aprendi a como manusear duas classes juntas.
//4. Fiz em 4 horas divididas em 2 dias.

package lapiseira;

import java.util.*;
import java.text.*;

class Grafite{
    private float thickness;
    private String hardness;
    private int size;

    public Grafite(float thickness, String hardness, int size){
        this.thickness = thickness;
        this.hardness = hardness;
        this.setSize(size);
    }

    public int usagePerSheet(){
        if(hardness.equals("HB")){
            return 1;
        }
        else if(hardness.equals("2B")){
            return 2;   
        }
        else if(hardness.equals("4B")){
            return 4;
        }
        else{
            return 6;
        }
    }

    public String getHardness() {
        return this.hardness;
    }

    public float getThickness() {
        return this.thickness;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public String toString(){
        DecimalFormat form = new DecimalFormat("0.0");
        return form.format(thickness) + ":" + hardness + ":" + size;
    }
}

class Pencil{
    private float thickness;
    private Grafite tip;
    private ArrayList<Grafite> barrel;

    public Pencil(float thickness){
        this.thickness = thickness;
        this.tip = null;
        this.barrel = new ArrayList<>();
    }

    public void setThickness(float thickness){
        this.thickness = thickness;
    }

    public float getThickness(){
        return this.thickness;
    }

    public boolean insert(Grafite grafite){
        if(this.thickness != grafite.getThickness()){
            System.out.println("fail: calibre incompatível");
            return true;
        }
        else{
            this.barrel.add(grafite);
            return true;
        }
    }

    public boolean pull(){
        if(this.tip != null){
            System.out.println("fail: ja existe grafite no bico");
            return false;
        }
        else{
            this.tip = this.barrel.remove(0);
            return true;
        }
    }

    public Grafite remove(){
        this.tip = null;
        return this.tip;
    }

    public void writePage(){
        if(this.tip == null){
            System.out.println("fail: nao existe grafite no bico");
        }
        else if(tip.getSize() <= 10){
            System.out.println("fail: tamanho insuficiente");
        }
        else if(tip.getSize() - 10 < tip.usagePerSheet()){
            System.out.println("fail: folha incompleta");
            tip.setSize(10);
        }
        else{
            tip.setSize(tip.getSize() - tip.usagePerSheet());
        }
    }

    public String toString(){
        String saida = "calibre: " + thickness + ", bico: ";
        if (this.tip != null){
            saida += "[" + this.tip + "]";
        }
        else{
            saida += "[]";
        }
        saida += ", tambor: {";
        for (Grafite g : barrel){
            saida += "[" + g + "]";
        }
        return saida + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        Pencil lap = new Pencil(0.5f);

        while (true) {
            String line = input();
            String[] argsL = line.split(" ");
            write('$' + line);

            if      ("end".equals(argsL[0])   ) { break;                                                                    }
            else if ("init".equals(argsL[0])  ) { lap = new Pencil(number(argsL[1]));                                       }
            else if ("insert".equals(argsL[0])) { lap.insert(new Grafite(number(argsL[1]), argsL[2], (int) number(argsL[3]))); }
            else if ("remove".equals(argsL[0])) { lap.remove();                                                             }
            else if ("pull".equals(argsL[0]))   { lap.pull();                                                               } 
            else if ("write".equals(argsL[0]) ) { lap.writePage();                                                          }
            else if ("show".equals(argsL[0])  ) { write(lap.toString());                                                    }
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public static String input()           { return scanner.nextLine();    }
    public static void write(String value) { System.out.println(value);    }
    public static float number(String str) { return Float.parseFloat(str); }
}
