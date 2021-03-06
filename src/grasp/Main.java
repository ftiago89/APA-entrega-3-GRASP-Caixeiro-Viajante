
package grasp;

import model.Grafo;
import model.Leitor;
import model.Leitor2;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Leitor2 leitor = new Leitor2("TSPLIBsi175.txt");
        Grafo G = new Grafo(leitor.getnVertices(), leitor.getVertices(), leitor.getArestas());

        //VND vnd = new VND(G, G.getSolucaoInicial(1));
        //int[] solucaoVnd = vnd.run();
        //for (int i = 0; i < solucaoVnd.length; ++i){
        //    System.out.print(solucaoVnd[i] + " ");
        //}
        //System.out.print(" Valor da solucao => " + vnd.valorSolucao(solucaoVnd));
        
        
        GRASP grasp = new GRASP(G, 10);
        //rodar o grasp 10 vezes cada uma com 10 iterações
        for (int i = 0; i < 10; ++i) grasp.run();
        
    }
    
}
