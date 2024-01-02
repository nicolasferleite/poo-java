package pulapula;

//1. Passou em todos os casos de teste.
//2. Fiz tudo sozinho.
//3. Aprendi a função isEmpty().
//4. Fiz em 3 horas divididas em 2 dias.

import java.util.*;
import java.util.stream.*;

class Kid {
    private int age;
    private String name;

    public Kid(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return name + ":" + age;
    }
}

class Trampoline{
    private LinkedList<Kid> waiting;
    private LinkedList<Kid> playing;
    
    public Trampoline() {
        this.waiting = new LinkedList<>();
        this.playing = new LinkedList<>();
    }

    private static Kid removeFromList(String name, LinkedList<Kid> list) {
        Kid removeKid = null;
        for (Kid kid : list) {
            if (kid.getName().equals(name)) {
                removeKid = kid;
                break;
            }
        }
        if (removeKid != null) {
            list.remove(removeKid);
        }
        return removeKid;
    }

    public void arrive(Kid kid) {
        this.waiting.addFirst(kid);
    }

    public void enter() {
        if(this.waiting != null){
            Kid kid = this.waiting.removeLast();
            this.playing.addFirst(kid);
        }
    }

    public void leave() {
        if (!this.playing.isEmpty()) {
            Kid kid = this.playing.removeLast();
            this.waiting.addFirst(kid);
        }
    }


    public Kid remoteKid(String name) {
        Kid removedKid = removeFromList(name, playing);
        if (removedKid == null) {
            removedKid = removeFromList(name, waiting);
        }
        return removedKid;
    }


    public String toString() {
        return   "[" + waiting.stream().map(Kid::toString).collect(Collectors.joining(", ")) + "]" + " => "
               + "[" + playing.stream().map(Kid::toString).collect(Collectors.joining(", ")) + "]";
    }
}


class Main {
    public static void main(String[] args) {
        System.out.println("side_by_side=080");
        Scanner scanner = new Scanner(System.in);
        Trampoline tramp = new Trampoline();
        while(true) {
            String line = scanner.nextLine();
            System.out.println("$"+ line);
            String[] ui = line.split(" ");
            if(ui[0].equals("end")) {
                break;
            } else if(ui[0].equals("arrive")) { // name age
                tramp.arrive(new Kid(ui[1], Integer.parseInt(ui[2]))) ;
            } else if(ui[0].equals("enter")) {
                tramp.enter();
            } else if(ui[0].equals("leave")) {
                tramp.leave();
            } else if(ui[0].equals("remove")) {//name
                tramp.remoteKid(ui[1]);
            } else if(ui[0].equals("show")) {
                System.out.println(tramp);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
