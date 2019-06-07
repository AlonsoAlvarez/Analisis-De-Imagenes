/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Usuario 1
 */
public class Lienzo extends javax.swing.JPanel {
 
    private BufferedImage imagen;
 
    public Lienzo() {
        imagen = null;
    }
 
    public void setImage(BufferedImage imagen) {
        this.imagen = imagen;
        repaint();
    }         
 
    @Override
    public void paint( Graphics g ) {
        super.paint( g );
        if( imagen != null ) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            g.setColor(Color.BLACK);
            g.drawString("http://rolandopalermo.blogspot.com", 10, 20);
        }
    }                 
 
}