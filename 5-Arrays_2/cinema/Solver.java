//1. Passou em todos os casos de teste.
//2. Fiz tudo sozinho.
//3. Entendi tudo.
//4. Fiz em cerca de 2 horas.

package cinema;

import java.util.*;

class Client {
    private String id;
    private String fone;
    public Client(String id, String fone) {
        this.id = id;
        this.fone = fone;
    }

    @Override
    public String toString() {
        return id + ":" + fone;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
}

class Sala{
    private ArrayList<Client> cadeiras;

    public Sala(int capacidade) {
        this.cadeiras = new ArrayList<Client>();
        for (int i=0; i<capacidade; i++) {
            this.cadeiras.add(null);
        }
    }
    
    private int procurar(String nome) {
        for ( int i=0; i<this.cadeiras.size(); i++ ) {
            if ( this.cadeiras.get(i) != null ) {
                if ( this.cadeiras.get(i).getId().equals( nome ) ) {
                    return i;
                }
            }
        }
        return -1;
    }

    public List<Client> getCadeiras() {
        return this.cadeiras;
    }
    
    public boolean reservar(String id, String fone, int ind) {
        if(ind < 0 || ind >= this.cadeiras.size()){
            System.out.println("fail: cadeira nao existe");
            return false;
        }
        
        if ( this.cadeiras.get(ind) != null ) {
            System.out.println( "fail: cadeira ja esta ocupada" );
            return false;
        }
        
        if ( this.procurar(id) != -1 ) {
            System.out.println( "fail: cliente ja esta no cinema" );
            return false;
        }
        
        this.cadeiras.set( ind, new Client(id,fone) );
        return true;
    }

    public void cancelar(String id) {
        int indiceCliente = this.procurar(id);
        if (indiceCliente == -1) {
            System.out.println("fail: cliente nao esta no cinema");
        } else {
            cadeiras.set(indiceCliente, null);
        }
    }


    public String toString() {
        String saida = "[";
        
        for (int i = 0; i < cadeiras.size(); i++) {
            if (cadeiras.get(i) == null) {
                saida += "-";
            } else {
                saida += cadeiras.get(i);
            }
            
            if (i < cadeiras.size() - 1) {
                saida += " ";
            }
        }
        
        saida += "]";
        return saida;
    }

}

class Solver {
    static Shell sh = new Shell();
    static Sala sala = new Sala(0);

    public static void main(String args[]) {
        System.out.println("side_by_side=080");
        sh.chain.put("init",     () -> { sala = new Sala(getInt(1));});
        sh.chain.put("show",     () -> { System.out.println(sala);});
        sh.chain.put("reservar", () -> { sala.reservar(getStr(1), getStr(2), getInt(3));});
        sh.chain.put("cancelar", () -> { sala.cancelar(getStr(1));});

        sh.execute();
    }

    static int getInt(int pos) {
        return Integer.parseInt(sh.param.get(pos));
    }
    static String getStr(int pos) {
        return sh.param.get(pos);
    }
}

class Shell {    
    public Scanner scanner = new Scanner(System.in);
    public HashMap<String, Runnable> chain = new HashMap<>();
    public ArrayList<String> param = new ArrayList<>();
    public Shell() {
        Locale.setDefault(new Locale("en", "US"));
    }
    public void execute() {
        while(true) {
            param.clear();
            String line = scanner.nextLine();
            Collections.addAll(param, line.split(" "));
            System.out.println("$" + line);
            if(param.get(0).equals("end")) {
                break;
            } else if (chain.containsKey(param.get(0))) {
                chain.get(param.get(0)).run();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
    }
}
