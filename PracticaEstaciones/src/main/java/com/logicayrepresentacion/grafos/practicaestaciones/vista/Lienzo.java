/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logicayrepresentacion.grafos.practicaestaciones.vista;

import com.logicayrepresentacion.grafos.practicaestaciones.DatosEstacion;
import com.logicayrepresentacion.grafos.practicaestaciones.Estacion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author 57300
 */
public class Lienzo extends javax.swing.JPanel {

    DatosEstacion datosEstacion;
    public static final int DIAMETRO = 30;
    Estacion estacionSeleccionada;

    public void setObjArbol(DatosEstacion datosEstacion) {
        this.datosEstacion = datosEstacion;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        if (datosEstacion != null) {
            for (Estacion esta : datosEstacion.getEstaciones()) {
                Ellipse2D elipse = esta.getForma();
                if (elipse == null) {
                    elipse = new Ellipse2D.Double(30, 30,
                            DIAMETRO + 50,
                            DIAMETRO);
                    esta.setForma(elipse);

                }
                g2.draw(elipse);
            }
        }
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {

        System.out.println("Me hicieron click en " + evt.getX() + " " + evt.getY());

        for (Estacion estacion : datosEstacion.getEstaciones()) {
            Ellipse2D figura = estacion.getForma();
            System.out.println("evaluando " + figura);
            if (figura.contains(evt.getX(), evt.getY())) {
                System.out.println("Me hicieron click en " + estacion.getNombre());
                Graphics2D g2 = (Graphics2D) this.getGraphics();
                g2.setPaint(Color.red);
                g2.fill(figura);
                estacionSeleccionada = estacion;
                break;
            }
        }
    }

}
