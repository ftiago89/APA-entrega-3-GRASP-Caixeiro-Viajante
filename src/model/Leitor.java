/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class Leitor {
    private int nVertices;
    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> vertices;
    private String nomeArquivo;

    public Leitor(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        leArquivo();
    }
    
    private void leArquivo() {
        String linha = null;
        String[] aux = null;
        int i = 0;
        int j = 0;
        int cArestas = 0;
        int[] aresta = new int[3];

        try {
            FileReader r = new FileReader(nomeArquivo);
            BufferedReader buffer = new BufferedReader(r);

            linha = buffer.readLine();
            nVertices = Integer.parseInt(linha);
            
            for (int c = 0; c < nVertices; ++c){
                vertices.add(new Vertice(c+1));
            }

            while ((linha = buffer.readLine()) != null) {
                j = i + 1;
                
                //System.out.println(linha);
                aux = linha.split(" ");
                //for (int c = 0; c < aux.length; ++c){
                //    System.out.print(aux[c] + "-");
                //}
                //System.out.println("====>>> " + aux.length);
                
                for (int k = 0; k < aux.length; ++k) {
                    arestas.add(new Aresta(vertices.get(i), vertices.get(j), Integer.parseInt(aux[k])));
                    //System.out.println("Adicionando aresta com v1 = " + vertices.get(i).getIdentificador() + " e v2 =" + vertices.get(j).getIdentificador() + " com peso = " + Integer.parseInt(aux[k]));
                    ++j;
                }
                ++i;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Erro na leitura do arquivo.");
        } catch (IOException ex) {
            System.err.println("Erro na leitura da linha do arquivo.");
        }
    }

    public int getnVertices() {
        return nVertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }
    
    public ArrayList<Vertice> getVertices(){
        return vertices;
    }
}
