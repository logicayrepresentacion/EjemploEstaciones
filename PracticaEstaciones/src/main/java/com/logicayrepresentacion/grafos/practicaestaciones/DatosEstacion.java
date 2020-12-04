/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logicayrepresentacion.grafos.practicaestaciones;

import arbol.binario.listaligada.busqueda.avl.ArbolAVL;
import arbol.binario.listaligada.busqueda.avl.NodoAVL;

/**
 *
 * @author 57300
 */
public class DatosEstacion {

    Estacion[] estaciones;
    ArbolAVL<Estacion> arbolAVL = new ArbolAVL();
    private static int SIGUIENTE_ID = 0;

    public DatosEstacion(int c) {
        SIGUIENTE_ID = 0;
        estaciones = new Estacion[c];
    }

    public Estacion add(String ciudad1) throws CantidadEstacionesException {

        NodoAVL<Estacion> nodo = arbolAVL.buscar(new Estacion(ciudad1, -1));
        if (nodo == null) {
            int siguienteID = DatosEstacion.nextSIGUIENTE_ID();
            if (siguienteID >= estaciones.length) {
                throw new CantidadEstacionesException("Sobrepaso la cantidad de estaciones");
            }
            Estacion estacion = new Estacion(ciudad1, siguienteID);
            arbolAVL.insertarDato(estacion);
            estaciones[estacion.getId()] = estacion;
            return estacion;
        } else {
            return (Estacion) nodo.getDato();
        }
    }

    public static int nextSIGUIENTE_ID() {
        int actual = SIGUIENTE_ID;
        SIGUIENTE_ID++;
        return actual;
    }

    public static int getSIGUIENTE_ID() {
        return SIGUIENTE_ID;
    }

    public Estacion[] getEstaciones() {
        return estaciones;
    }

    public Estacion buscar(String ciudad1) {
        NodoAVL<Estacion> nodo = arbolAVL.buscar(new Estacion(ciudad1, -1));
        if (nodo == null) {
            return null;
        } else {
            return (Estacion) nodo.getDato();
        }
    }

}
