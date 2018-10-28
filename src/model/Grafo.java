package model;



import java.util.ArrayList;
import java.util.List;


public class Grafo {
    private int nVertices;
    private List<Vertice> vertices, candidatos;
    private List<Aresta> arestas;

    public Grafo(int nVertices, List<Vertice> vertices, List<Aresta> arestas) {
        this.nVertices = nVertices;
        this.vertices = vertices;
        this.arestas = arestas;
    }

    public int getnVertices() {
        return nVertices;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }
    
    public Vertice getVertice(int identificador){
        for (Vertice c: vertices){
            if (c.getIdentificador() == identificador){
                return c;
            }
        }
        return null;
    }
    
    private Vertice getCandidato(int identificador){
        for (Vertice c: candidatos){
            if (c.getIdentificador() == identificador){
                return c;
            }
        }
        return null;
    }
    
    public Aresta getAresta(int v1, int v2){

        for (Aresta a: arestas){
            if (((a.getV1().getIdentificador() == v1) && (a.getV2().getIdentificador() == v2))
                    || ((a.getV2().getIdentificador() == v1) && (a.getV1().getIdentificador() == v2))){
                return a;
            }
        }
        return null;
    }
    
    private void inicializarCandidatos(){
        this.candidatos = new ArrayList<>();
        
        for (int i = 0; i < nVertices; ++i){
            this.candidatos.add(new Vertice(i+1));
        }
    }
    
    ///////// heuristica de construcao do vizinho mais proximo//////////
    public int[] getSolucaoInicial(int identificador){
        int[] solucaoInicial = new int[nVertices];
        int contador = 0;
        float melhorPeso = Float.MAX_VALUE;
        Vertice melhorVertice = null;
                
        inicializarCandidatos();
        
        
        while(!candidatos.isEmpty()){
            //System.out.println(candidatos.size());
            melhorPeso = Float.MAX_VALUE;
            for (Vertice c: candidatos){
                if (identificador == c.getIdentificador()){
                    melhorPeso = 0;
                    melhorVertice = c;
                } else {
                    if (getAresta(identificador, c.getIdentificador()).getPeso() < melhorPeso) {
                        melhorPeso = getAresta(identificador, c.getIdentificador()).getPeso();
                        melhorVertice = c;
                    }
                }

                
            }
            solucaoInicial[contador] = melhorVertice.getIdentificador();
            identificador = solucaoInicial[contador];
            ++contador;
            candidatos.remove(melhorVertice);
        }
        return solucaoInicial;
    }
}
