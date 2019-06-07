/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trapezoidal;

import GUI.JFrameImagen;
import io.ImageManager;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import muestreo.Croma;
import muestreo.Pix;

/**
 *
 * @author Usuario 1
 */
public class NewClass {
    
    public static double seleccion(int k0, int k1, int u, int v){
        double p = Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
        if(p<k1 && p<k0){
            return 0;
        }else{
            if(k0<p && k1<p){
                return 1;
            }else{
                return ((p-k1)/(k0-k1));
            }
        }
    }
    
    public static ArrayList<Pix> separa(Image imagen, int k0, int k1){
        ArrayList<Pix> separa=new ArrayList<>();
        BufferedImage bi = ImageManager.toBufferedImage(imagen);
        // recorrer el BufferedImage 
        int cx = centro(ancho(imagen), alto(imagen)).getX();
        int cy = centro(ancho(imagen), alto(imagen)).getY();
        for (int x=0; x < bi.getWidth();x++){
            for(int y=0; y < bi.getHeight();y++){
                Color pixel = new Color(bi.getRGB(x, y));
                Pix p = new Pix();
                double tmp = seleccion(k0, k1, x-cx, y-cy)*255;
                p.setR((int)tmp);
                p.setG((int)tmp);
                p.setB((int)tmp);
                p.setX(x);
                p.setY(y);
                separa.add(p);
            }
        }
        return separa;
    }
       
    public static Image imagenResultante(ArrayList<Pix> listaResultante, int ancho, int alto){
        BufferedImage i = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for(Pix p: listaResultante){
            i.setRGB(p.getX(), p.getY(), new Color(p.getR(), p.getG(), p.getB()).getRGB());
        }
        return ImageManager.toImage(i);
    }
    
    public static int alto(Image imagen){
        BufferedImage bi = ImageManager.toBufferedImage(imagen);
        return bi.getHeight();
    }
    
    public static int ancho(Image imagen){
        BufferedImage bi = ImageManager.toBufferedImage(imagen);
        return bi.getWidth();
    }
    
    public static Pix centro(int ancho, int alto){
        Pix p = new Pix();
        p.setX(ancho/2);
        p.setY(alto/2);
        return p;
    }
    
    public static Image trapezoidal(Image imagen, int k0, int k1){
        return imagenResultante(separa(imagen,k0,k1), ancho(imagen), alto(imagen));
    }
    
    
    public static void main(String args[]){
        //abrir verde.jpg
        Image imagen = ImageManager.openImage();
        JFrameImagen frame1 = new JFrameImagen(trapezoidal(imagen, 5, 300));
        //abrir fondo.jpg, dino.jpg
        System.out.println();
    }
    
}
