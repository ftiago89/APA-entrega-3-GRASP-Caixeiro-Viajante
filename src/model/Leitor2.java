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
import java.util.Scanner;

/**
 *
 * @author Felipe
 */
public class Leitor2 {
    private int nVertices;
    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> vertices;
    private String nomeArquivo;

    public Leitor2(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        leArquivo();
    }
    
    private void leArquivo() {
        String linha = null;
        String[] aux = null;
        Scanner scanner;
        String temp;
        int i = 0;
        int j = i+1;

        try {
            FileReader r = new FileReader(nomeArquivo);
            //BufferedReader buffer = new BufferedReader(r);

            
            
            scanner = new Scanner(r);
            
            nVertices = Integer.valueOf(scanner.next());
            
            for (int c = 0; c < nVertices; ++c){
                vertices.add(new Vertice(c+1));
                //System.out.println(c+1);
            }
            
            for (int c = 0; c < nVertices; ++c){
                arestas.add(new Aresta(vertices.get(c),vertices.get(c), 0));
            }

            while (scanner.hasNext()) {
                temp = scanner.next();
                //System.out.println(temp);
                if (temp.equalsIgnoreCase("0")) {
                    ++i;
                    j = i + 1;
                } else {
                    //System.out.println(temp);
                    //System.out.println(i + " " + j + " " + temp);
                    arestas.add(new Aresta(vertices.get(i-1), vertices.get(j-1), Float.valueOf(temp)));
                    ++j;
                }
            }
            //for (Aresta a: arestas){
            //    System.out.println(a.getPeso());
            //}
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
