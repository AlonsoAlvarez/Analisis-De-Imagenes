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
public class BlancoNegro {

    private Image imagen;

    public BlancoNegro() {
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    public BlancoNegro(Image imagen, int tol) {
    //    ArrayList<Pix> listaResultante = listaResultante(imagen, tol);
        ArrayList<Pix> listaResultante = listaCaliente(imagen, tol);
        JFrameImagen frame1 = new JFrameImagen(imagenResultante(listaResultante, ancho(imagen), alto(imagen)));
    }
    
    public Image resultado(int tol){
    //    ArrayList<Pix> listaResultante = listaResultante(imagen, tol);
        ArrayList<Pix> listaResultante = listaCaliente(imagen, tol);
//        JFrameImagen frame1 = new JFrameImagen(imagenResultante(listaResultante, ancho(this.imagen), alto(this.imagen)));
        return imagenResultante(listaResultante, ancho(this.imagen), alto(this.imagen));
    }
    
    public Image resultadoCB(int tol, int tol2){
    //    ArrayList<Pix> listaResultante = listaResultante(imagen, tol);
        ArrayList<Pix> listaResultante = listaCalienteBrilla(imagen, tol, tol2);
//        JFrameImagen frame1 = new JFrameImagen(imagenResultante(listaResultante, ancho(this.imagen), alto(this.imagen)));
        return imagenResultante(listaResultante, ancho(this.imagen), alto(this.imagen));
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
    
    public ArrayList<Pix> listaResultante(Image imagen1, int tol){
        ArrayList<Pix> im1 = separa(imagen1); 
        int suma, media;
        for(int i=0; i<im1.size(); i++){
            suma = im1.get(i).getR()+im1.get(i).getG()+im1.get(i).getB();
            if(766<suma+tol){
                media = 255;
            }else{
                if(suma+tol<0){
                    media=0;
                }else{
                    media = (int)(suma)/3;
                }
            }
            System.out.println(i+" / "+im1.size());
            im1.get(i).setR(media);
            im1.get(i).setG(media);
            im1.get(i).setB(media);
        }
        return im1;
    }
    
    public ArrayList<Pix> listaCaliente(Image imagen1, int tol){
        ArrayList<Pix> im1 = separa(imagen1); 
        for(int i=0; i<im1.size(); i++){
            if(0 < im1.get(i).getR()+tol && im1.get(i).getR()+tol < 255){
                im1.get(i).setR(im1.get(i).getR()+tol);
            }else{
                if(im1.get(i).getR()+tol < 0){
                    im1.get(i).setR(0);
                }
                if(255 < im1.get(i).getR()+tol){
                    im1.get(i).setR(255);
                }
            }
            if(im1.get(i).getB()-tol < 255 && 0 < im1.get(i).getB()-tol){
                im1.get(i).setB(im1.get(i).getB()-tol);
            }else{
                if(im1.get(i).getB()-tol < 0){
                    im1.get(i).setB(0);
                }
                if(255 < im1.get(i).getB()+tol){
                    im1.get(i).setB(255);
                }
            }
            
        }
        return im1;
    }
    
    public ArrayList<Pix> listaCalienteBrilla(Image imagen1, int tol, int tol2){
        ArrayList<Pix> im1 = separa(imagen1);
        for(int i=0; i<im1.size(); i++){
            
            if(0 < im1.get(i).getR()+tol2 && im1.get(i).getR()+tol2 < 255){
                im1.get(i).setR(im1.get(i).getR()+tol2);
            }else{
                if(im1.get(i).getR()+tol2 < 0){
                    im1.get(i).setR(0);
                }
                if(255 < im1.get(i).getR()+tol2){
                    im1.get(i).setR(255);
                }
            }
            if(0 < im1.get(i).getG()+tol2 && im1.get(i).getG()+tol2 < 255){
                im1.get(i).setG(im1.get(i).getG()+tol2);
            }else{
                if(im1.get(i).getG()+tol2 < 0){
                    im1.get(i).setG(0);
                }
                if(255 < im1.get(i).getG()+tol2){
                    im1.get(i).setG(255);
                }
            }
            if(0 < im1.get(i).getB()+tol2 && im1.get(i).getB()+tol2 < 255){
                im1.get(i).setB(im1.get(i).getB()+tol2);
            }else{
                if(im1.get(i).getB()+tol2 < 0){
                    im1.get(i).setB(0);
                }
                if(255 < im1.get(i).getB()+tol2){
                    im1.get(i).setB(255);
                }
            }
            
            
            if(0 < im1.get(i).getR()+tol && im1.get(i).getR()+tol < 255){
                im1.get(i).setR(im1.get(i).getR()+tol);
            }else{
                if(im1.get(i).getR()+tol < 0){
                    im1.get(i).setR(0);
                }
                if(255 < im1.get(i).getR()+tol){
                    im1.get(i).setR(255);
                }
            }
            if(im1.get(i).getB()-tol < 255 && 0 < im1.get(i).getB()-tol){
                im1.get(i).setB(im1.get(i).getB()-tol);
            }else{
                if(im1.get(i).getB()-tol < 0){
                    im1.get(i).setB(0);
                }
                if(255 < im1.get(i).getB()+tol){
                    im1.get(i).setB(255);
                }
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
        Image imagen = ImageManager.openImage();
        BlancoNegro bn = new BlancoNegro(imagen, -200);
        System.out.println();
    }
}
