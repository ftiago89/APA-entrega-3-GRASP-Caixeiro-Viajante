
package grasp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aresta;
import model.Grafo;
import model.Vertice;



public class GRASP {
    private int graspMax;
    private Grafo G;

    public GRASP(Grafo g, int graspMax) {
        this.graspMax = graspMax;
        this.G = g;
    }
    
    //GRASP
    public void run(){
        
        
        int[] solucao = new int[G.getnVertices()];
        int[] solucaoAux = new int[G.getnVertices()];
        double melhorValor = Double.MAX_VALUE;
        
        for (int i = 0; i < graspMax; ++i){
            //o alfa pode ser definido aqui
            solucaoAux = new VND(G, construirSolucao(1, 0.1)).run();
            //visualização
            //System.out.print("Rota intermediaria => ");
            //escreverArray(solucaoAux);
            //System.out.print(" Valor da solucao => ");
            //System.out.println(valorSolucao(solucaoAux));
            ////////////////////////////////////////////
            if (valorSolucao(solucaoAux) < melhorValor)
                melhorValor = valorSolucao(solucaoAux);
                solucao = solucaoAux; 
        }
        //visualização
        System.out.print("Rota encontrada => ");
        escreverArray(solucao);
        System.out.print(" Valor da solucao => ");
        System.out.println(valorSolucao(solucao));
        ///////////////////////////////////////////////
    }
    
    /////////// metodo de construcao //////////////
    public int[] construirSolucao(int identificador, double alfa){
        int[] solucao = new int[G.getnVertices()];
        List<double[]> custosCandidatos;
        int contador = 0;
        
        custosCandidatos = inicializarCandidatos(identificador);
        
        try {
            while (!custosCandidatos.isEmpty()) {
                double[] aux = custosCandidatos.get((int) (Math.random() * ((int) custosCandidatos.size() * alfa + 1)));
                solucao[contador] = (int) aux[1];
                custosCandidatos.remove(aux);
                ++contador;
            }
        } catch (IndexOutOfBoundsException e) {
            double[] aux = custosCandidatos.get(0);
            solucao[contador] = (int) aux[1];
            custosCandidatos.remove(aux);
            ++contador;
        }
        return solucao;
    }
    
    //retorna uma lista ordenada com os custos e qual vertices desses custos
    private List<double[]> inicializarCandidatos(int identificador){
        List<double[]> custosCandidatos = new ArrayList<>();
        double aux[][] = new double[G.getnVertices()][2];
        int contador = 0;
        
        for (Vertice v : G.getVertices()) {
            if (identificador == v.getIdentificador()) {
                aux[contador][0] = 0;
                aux[contador][1] = v.getIdentificador();
                ++contador;
                //System.out.println(aux2[0] + " " + aux2[1]);
            } else {
                aux[contador][0] = G.getAresta(identificador, v.getIdentificador()).getPeso();
                aux[contador][1] = v.getIdentificador();
                //System.out.println(aux[contador][0] + " " + aux[contador][1]);
                ++contador;
            }
        }
        
        
        java.util.Arrays.sort(aux, new java.util.Comparator<double[]>() {
           @Override
           public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });
        
        for (double[] d: aux){
            custosCandidatos.add(d);
            //System.out.println(d[0] + " " + d[1]);
        }
        
        return custosCandidatos;
    }
    
    //funcoes de suporte///////////////////////////////////////
    //funcao objetivo
    private double valorSolucao(int[] s) {
        double solucao = 0;

        for (int c = 1; c < s.length; ++c) {
            solucao += G.getAresta(s[c-1], s[c]).getPeso();
        }
        solucao += G.getAresta(s[s.length-1], s[0]).getPeso();
        
        return solucao;
    }
    
    private void escreverArray(int[] a){
        for (int i = 0; i < a.length; ++i){
            System.out.print(a[i] + " ");
        }
    }
}
