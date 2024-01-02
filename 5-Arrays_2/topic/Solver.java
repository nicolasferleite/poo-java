package topic;

//1. Passou em todos os casos de testes.
//2. Tem apenas uma tentativa, pois fiz o codigo no VSCODE e tambem juntamente com o senhor, quando foi resolvido em sala.
//3. Eu entendi como fazer todo o codigo
//4. Demorei cerca de 4horas divididos em 2 dias,

import java.util.*;
import java.util.stream.*;

class Pass {
    private String name;
    private int age;

    public Pass(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public boolean isPriority() {
        return age >= 65;
    }

    public String getName() {
        return this.name;
    }
    public String toString(){
        return this.name + ":" + this.age;
    }
}

class Topic {
    private List<Pass> prioritySeats;
    private List<Pass> normalSeats;

    public Topic(int capacity, int qtdPriority) {
        this.prioritySeats = new LinkedList<Pass>();
        for (int i=0; i<qtdPriority; i++) {
            this.prioritySeats.add( null );
        }
        this.normalSeats = new LinkedList<Pass>();
        for (int i=0; i<capacity-qtdPriority; i++) {
            this.normalSeats.add( null );
        }
    }

    private static int findFirstFreePos(List<Pass> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                return i;
            }
        }
        return -1;
    }
    
    private static int findByName(String name, List<Pass> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && list.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean insertOnList(Pass pass, List<Pass> list) {
        int ind = findFirstFreePos(list);
        if (ind == -1) {
            return false;
        }
        
        list.set(ind, pass);
        return true;
    }

    private static boolean removeFromList(String name, List<Pass> list) {
        int ind = findByName(name,list);
        if (ind == -1) {
            return false;
        }
        
        list.set(ind, null);
        return true;
    }


    public boolean insert(Pass pass) {
        if (findByName( pass.getName(), this.normalSeats) != -1    ||
             findByName(pass.getName(), this.prioritySeats) != -1     ) {
            System.out.println("fail: " + pass.getName() + " ja esta na topic");
            return false;
        }
        
        if (pass.isPriority()) {
            if (insertOnList(pass, this.prioritySeats)) {
                return true;
            }
            if (insertOnList(pass, this.normalSeats)) {
                return true;
            }
        } else {
            if (insertOnList(pass,this.normalSeats)) {
                return true;
            }
            if (insertOnList(pass, this.prioritySeats)) {
                return true;
            }
            
        }
        System.out.println("fail: topic lotada");
        return false;
    }

    public boolean remove(String name) {
        if (removeFromList(name, this.prioritySeats)) {
            return true;
        }
        if (removeFromList(name, this.normalSeats)) {
            return true;
        }
        
        System.out.println("fail: " + name + " nao esta na topic");
        return false;
    }

    public String toString() {
        return "[" + Stream.concat(
            this.prioritySeats.stream().map(p -> ("@" + ((p == null)?(""):("" + p)))), 
            this.normalSeats.stream().map(p -> ("=" + ((p == null)?(""):("" + p)))))
            .collect(Collectors.joining(" ")) + "]";
    }
}

class Solver{
    public static void main(String[] args) {
        System.out.println("side_by_side=100");
        Scanner scanner = new Scanner(System.in);
        Topic topic = new Topic(0, 0);
        while(true){
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if(line.equals("end")) {
                break;
            } else if(ui[0].equals("init")) {
                topic = new Topic(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
            } else if(ui[0].equals("show")) {
                System.out.println(topic);
            } else if(ui[0].equals("in")) {
                topic.insert(new Pass(ui[1], Integer.parseInt(ui[2])));
            } else if(ui[0].equals("out")) {
                topic.remove(ui[1]);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
