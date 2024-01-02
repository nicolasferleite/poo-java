package circulo;

import java.util.Scanner;
import java.lang.Math;

class Ponto {
    
    float x;
    float y;
    
    void ler() {
        this.x = QuantosDentro.scan.nextFloat();
        this.y = QuantosDentro.scan.nextFloat();
    }
    
    float distancia(Ponto p) {
        float cH = p.x - this.x;
        float cV = p.y - this.y;
        float dist = (float) Math.sqrt( cH*cH + cV*cV );
        return dist;
    }
    
    boolean dentro( Circulo c ) {
        if ( this.distancia( c.centro ) <= c.raio ) {
            return true;
        } else {
            return false;
        }
    }
}

class Circulo {
    Ponto centro;
    float raio;

    void ler() {
        this.centro = new Ponto();
        this.centro.x = QuantosDentro.scan.nextFloat();
        this.centro.y = QuantosDentro.scan.nextFloat();
        this.raio = QuantosDentro.scan.nextFloat();
    }

    boolean contem( Ponto p ) {
        if ( p.distancia( this.centro ) <= this.raio ) {
            return true;
        } else {
            return false;
        }
    }

    int quantosDentro( Ponto pontos[] ) {
        int cont = 0;
        for ( Ponto ponto : pontos ) {
            if ( this.contem( ponto ) ) {
            // if ( ponto.dentro( this ) ) {
                cont++;
            }
        }
        return cont;
    }
}

class QuantosDentro {
    static Scanner scan = new Scanner(System.in);
    public static void main( String args[] ) {
        int n = scan.nextInt();
        
        Ponto vetor[] = new Ponto[n];
        for (int i=0; i<n; i++) {
            vetor[i] = new Ponto();
            vetor[i].ler();
        }
        
        Circulo c = new Circulo();
        c.ler();
        
        System.out.println( c.quantosDentro( vetor ) );
    }
}