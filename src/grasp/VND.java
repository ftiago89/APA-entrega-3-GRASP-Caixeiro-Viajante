
package grasp;

import java.util.Arrays;
import model.Grafo;


public class VND {
    private Grafo G;
    private int[] solucaoConstruida;

    public VND(Grafo G, int[] solucaoConstruida) {
        this.G = G;
        this.solucaoConstruida = solucaoConstruida;
    }
    
    public int[] run(){
        int[] melhorVizinho;
        int r = 2;
        int k = 1;
        
        while (k <= r){
            if (k == 1){
                melhorVizinho = explorarSwap(this.solucaoConstruida);
            }else{
                melhorVizinho = explorarDoisOpt(this.solucaoConstruida);
            }
            
            if (valorSolucao(melhorVizinho) < valorSolucao(this.solucaoConstruida)){
                this.solucaoConstruida = melhorVizinho;
                k = 1;
            }else{
                ++k;
            }
        }
        //System.out.print("Rota encontrada => ");
        //escreverArray(this.solucaoConstruida);
        //System.out.print(" Valor da solucao => ");
        //System.out.print(valorSolucao(this.solucaoConstruida));
        return this.solucaoConstruida;
    }
    
    //////////////// funcao objetivo ///////////////////
    public double valorSolucao(int[] s) {
        double solucao = 0;

        for (int c = 1; c < s.length; ++c) {
            solucao += G.getAresta(s[c-1], s[c]).getPeso();
        }
        solucao += G.getAresta(s[s.length-1], s[0]).getPeso();
        
        return solucao;
    }
    //////////////// exploracao da vizinhança ///////////////
    private int[] explorarSwap(int[] s){
        int[] melhorVizinho = new int[s.length];
        int[] sAux;
        double melhorSolucao = Double.MAX_VALUE;
        
        for (int c = 0; c < s.length; ++c){
            for (int k = c+1; k < s.length; ++k){
                sAux = swap(c, k, s);
                //System.out.print("Vizinho => ");
                //escreverArray(sAux);
                //System.out.println(" => " + valorSolucao(sAux));
                if (valorSolucao(sAux) < melhorSolucao){
                    melhorSolucao = valorSolucao(sAux);
                    melhorVizinho = sAux;
                }
            }
        }
        //System.out.print("Melhor Vizinho => ");
        //escreverArray(melhorVizinho);
        //System.out.println("Solucao inicial => ");
        //escreverArray(this.solucaoConstruida);
        return melhorVizinho;
    }
    
    private int[] explorarDoisOpt(int[] s){
        int[] melhorVizinho = new int[s.length];
        int[] sAux;
        double melhorSolucao = Double.MAX_VALUE;
        
        for (int c = 0; c < s.length; ++c){
            for (int k = c+1; k < s.length; ++k){
                sAux = doisOpt(c, k, s);
                //System.out.print("Vizinho => ");
                //escreverArray(sAux);
                //System.out.println(" => " + valorSolucao(sAux));
                if (valorSolucao(sAux) < melhorSolucao){
                    melhorSolucao = valorSolucao(sAux);
                    melhorVizinho = sAux;
                }
            }
        }
        //System.out.print("Melhor Vizinho => ");
        //escreverArray(melhorVizinho);
        //System.out.print("Solucao inicial => ");
        //escreverArray(this.solucaoConstruida);
        return melhorVizinho;
    }
    
    ////////// movimentos de vizinhança///////////
    //swap de 2 vertices
    private int[] swap(int i, int j, int[] s){
        int[] arrayAux = s.clone();
        int aux = arrayAux[i];
        arrayAux[i] = arrayAux[j];
        arrayAux[j] = aux;
        
        return arrayAux;
    }
    
    //2-opt
    private int[] doisOpt(int i, int j, int[] s){
        int[] subArray;
        int[] sAux = s.clone();
        int contador = 0;
        
        subArray = Arrays.copyOfRange(sAux, i, j+1);
        //System.out.println(subArray.length);
        inverterArray(subArray);
        //System.out.println(subArray[0] + " " + subArray[1] + " " + subArray[2]);
        
        for (int k = i; k <= j; ++k){
            sAux[k] = subArray[contador];
            ++contador;
        }
        return sAux;
    }
    
    private void inverterArray(int[] a){
        int contador = 0;
        
        int[] aux = a.clone();
        for (int i = a.length-1; i >= 0; --i){
            a[i] = aux[contador];
            ++contador;
        }
    }
    
    private void escreverArray(int[] a){
        for (int i = 0; i < a.length; ++i){
            System.out.print(a[i] + " ");
        }
    }
}
