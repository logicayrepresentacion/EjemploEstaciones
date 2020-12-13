/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logicayrepresentacion.grafos.practicaestaciones;

/**
 *
 * @author 57300
 */
public class Grafo {

    private final int[][] matrizAdy;
    private int[][] matrizCostos;

    public Grafo(int cantidadEstaciones) {
        matrizAdy = new int[cantidadEstaciones][cantidadEstaciones];
        matrizCostos = new int[cantidadEstaciones][cantidadEstaciones];
    }

    public void addAdyancencia(int vi, int vj, int distancia) {
        matrizAdy[vi][vj] = 1;
        matrizAdy[vj][vi] = 1;
        matrizCostos[vi][vj] = distancia;
        matrizCostos[vj][vi] = distancia;
    }

    public Costo[] dijkstra(int vi) {

        Costo[][] costos = new Costo[matrizCostos.length][matrizCostos.length];
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                if (matrizAdy[i][j] == 0) {
                    costos[i][j] = Costo.getIndeterminado();
                } else {
                    costos[i][j] = new Costo(matrizCostos[i][j]);
                }
            }
        }

        int[] visitados = new int[matrizCostos.length];
        Costo[] costoMinimo = new Costo[matrizCostos.length];
        for (int j = 0; j < matrizCostos.length; j++) {
            costoMinimo[j] = costos[vi][j];
        }

        visitados[vi] = 1;
        int indice = 0;
        while (indice < matrizCostos.length - 1) {
            int w = escogerMenor(costoMinimo, visitados);
            visitados[w] = 1;
            indice++;
            for (int j = 0; j < visitados.length; j++) {
                if (visitados[j] == 0) {
                    Costo costoJ = costoMinimo[j];
                    Costo distanciaWJ = costos[w][j];
                    Costo costo2 = Costo.sumar(costoMinimo[w], distanciaWJ);
                    costoMinimo[j] = Costo.menor(costoJ, costo2);
                }
            }
        }

        return costoMinimo;

    }

    private int escogerMenor(Costo[] costoMinimo, int[] visitados) {
        int w = 0;
        Costo minimow = costoMinimo[w];
        for (int j = 0; j < visitados.length; j++) {
            if (visitados[j] == 0) {
                Costo posiblemenor = Costo.menor(minimow, costoMinimo[j]);
                if (posiblemenor != minimow) {
                    minimow = posiblemenor;
                    w = j;
                }
            }
        }
        return w;
    }

    public int[][] getMatrizAdy() {
        return matrizAdy;
    }

    public int[][] getMatrizCostos() {
        return matrizCostos;
    }

}
