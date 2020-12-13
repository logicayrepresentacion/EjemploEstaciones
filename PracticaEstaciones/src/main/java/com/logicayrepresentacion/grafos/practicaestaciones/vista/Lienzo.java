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
import java.awt.geom.QuadCurve2D;

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
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2 = (Graphics2D) g;
        Color natularColor = g2.getColor();
        if (datosEstacion != null) {
            // Este fragmento inicializa las ellipses de la estación en caso no existan
            for (Estacion esta : datosEstacion.getEstaciones()) {
                Ellipse2D elipse = esta.getForma();
                if (elipse == null) {
                    elipse = new Ellipse2D.Double(30, 30,
                            DIAMETRO + 50,
                            DIAMETRO);
                    esta.setForma(elipse);
                }
                g2.draw(elipse);
                String nombre = esta.getId() + " " + esta.getNombre();
                g2.drawString(nombre, (float) elipse.getX(), (float) elipse.getY());
            }

            // Este fragmento pinta las lineas de relación entre un nodo y otro de salida
            int[][] matrizAdy = datosEstacion.getGrafo().getMatrizAdy();
            int[][] matrizCostos = datosEstacion.getGrafo().getMatrizCostos();
            for (int i = 0; i < matrizAdy.length; i++) {

                for (int j = i; j < matrizAdy.length; j++) {
                    int ady = matrizAdy[i][j];
                    if (ady != 0) {
                        Estacion estacion1i = datosEstacion.getEstacion(i);
                        Double x1 = estacion1i.getForma().getCenterX();
                        Double y1 = estacion1i.getForma().getCenterY();

                        Estacion estacion2j = datosEstacion.getEstacion(j);
                        Double x2 = estacion2j.getForma().getCenterX();
                        Double y2 = estacion2j.getForma().getCenterY();
                        Double ctx = ((x1 + x2) / 2) + 2;
                        Double cty = ((y1 + y2) / 2) + 2;
                        g2.setPaint(Color.GREEN);
                        g2.drawString("" + matrizCostos[i][j], ctx.intValue(), cty.intValue());
                        g2.setPaint(Color.BLUE);
                        g2.drawLine(x1.intValue(), y1.intValue(), x2.intValue(), y2.intValue());
                        //g2.draw(q);

                        estacion1i = datosEstacion.getEstacion(j);
                        x1 = estacion1i.getForma().getCenterX();
                        y1 = estacion1i.getForma().getCenterY();

                        estacion2j = datosEstacion.getEstacion(i);
                        x2 = estacion2j.getForma().getCenterX();
                        y2 = estacion2j.getForma().getCenterY();
                        ctx = (x1 + x2) / 2 + 30;
                        cty = (y1 + y2) / 2 + 30;
                        QuadCurve2D q = new QuadCurve2D.Double();
                        q = new QuadCurve2D.Double();
                        q.setCurve(x2, y2, ctx, cty, x1, y1);

                        g2.setPaint(Color.BLUE);
                        g2.draw(q);

                    }
                }
            }
        }
    }

    /**
     * Creates new form NewJPanel
     */
    public Lienzo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder("Grafo"));
        setAutoscrolls(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (estacionSeleccionada != null) {
            Ellipse2D elipse = new Ellipse2D.Double(evt.getX(), evt.getY(),
                    DIAMETRO + 50,
                    DIAMETRO);
            estacionSeleccionada.setForma(elipse);
        }
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

        for (Estacion estacion : datosEstacion.getEstaciones()) {
            Ellipse2D figura = estacion.getForma();
            System.out.println("evaluando " + figura);
            if (figura.contains(evt.getX(), evt.getY())) {
                System.out.println("Me hicieron formMousePressed en " + estacion.getNombre());
                Graphics2D g2 = (Graphics2D) this.getGraphics();
                g2.setPaint(Color.RED);
                g2.fill(figura);
                estacionSeleccionada = estacion;
                break;
            }
        }
        datosEstacion.obtenerCostoMinimo(estacionSeleccionada.getId());

    }//GEN-LAST:event_formMousePressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println("Me hicieron click en " + evt.getX() + " " + evt.getY());


    }//GEN-LAST:event_formMouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        System.out.println("Me hicieron formMouseReleased en " + evt.getX() + " " + evt.getY());
        estacionSeleccionada = null;
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
