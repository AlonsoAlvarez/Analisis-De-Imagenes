/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muestreo;

import GUI.JFrameImagen;
import io.ImageManager;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Usuario 1
 */
public class Croma {

    public Croma(Image imagen1, Image imagen2, int r, int g, int b, int tol) {
        ArrayList<Pix> listaResultante = listaResultante(imagen1, imagen2, r, g, b, tol);
        JFrameImagen frame1 = new JFrameImagen(imagenResultante(listaResultante, ancho(imagen1), alto(imagen1)));
    }
 
    public static ArrayList<Pix> separa(Image imagen){
        ArrayList<Pix> separa=new ArrayList<>();
        BufferedImage bi = ImageManager.toBufferedImage(imagen);
        // recorrer el BufferedImage 
        for (int x=0; x < bi.getWidth();x++){
            for(int y=0; y < bi.getHeight();y++){
                Color pixel = new Color(bi.getRGB(x, y));
                Pix p = new Pix();
                p.setR(pixel.getRed());
                p.setG(pixel.getGreen());
                p.setB(pixel.getBlue());
                p.setX(x);
                p.setY(y);
                separa.add(p);
            }
        }
        return separa;
    }
       
    public int alto(Image imagen){
        BufferedImage bi = ImageManager.toBufferedImage(imagen);
        return bi.getHeight();
    }
    
    public int ancho(Image imagen){
        BufferedImage bi = ImageManager.toBufferedImage(imagen);
        return bi.getWidth();
    }
    
    public ArrayList<Pix> listaResultante(Image imagen1, Image imagen2, int r, int g, int b, int tol){
        ArrayList<Pix> im1 = separa(imagen1); 
        ArrayList<Pix> im2 = separa(imagen2);
        for(int i=0; i<im1.size(); i++){
            if((im1.get(i).getR()<r+tol && r-tol<im1.get(i).getR()) &&
                    (im1.get(i).getG()<g+tol && g-tol<im1.get(i).getG()) &&
                    (im1.get(i).getB()<b+tol && b-tol<im1.get(i).getB())){
                im1.get(i).setR(im2.get(i).getR());
                im1.get(i).setG(im2.get(i).getG());
                im1.get(i).setB(im2.get(i).getB());
            }
        }
        return im1;
    }
    
    public Image imagenResultante(ArrayList<Pix> listaResultante, int ancho, int alto){
        BufferedImage i = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for(Pix p: listaResultante){
            i.setRGB(p.getX(), p.getY(), new Color(p.getR(), p.getG(), p.getB()).getRGB());
        }
        return ImageManager.toImage(i);
    }
    
    public static void main(String args[]){
        //abrir verde.jpg
        Image imagen1 = ImageManager.openImage();
        //abrir fondo.jpg, dino.jpg
        Image imagen2 = ImageManager.openImage();
        Croma e = new Croma(imagen1, imagen2, 20, 255, 9, 70);
        System.out.println();
    }
    
}


