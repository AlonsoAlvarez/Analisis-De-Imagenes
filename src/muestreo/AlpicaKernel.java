/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muestreo;

import io.ImageManager;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Usuario 1
 */
public class AlpicaKernel {
    
    public static Image aplicarMascaras(Image imagen, int divisor, int casoMascara){
        double[][] kernel = new double[3][3];
        Kernel k = new Kernel();
        BufferedImage nueva = new BufferedImage(imagen.getWidth(null),imagen.getHeight(null),BufferedImage.TYPE_INT_RGB);
        BufferedImage bi = ImageManager.toBufferedImage(imagen);      
        for(int x=0; x<imagen.getWidth(null);x++){
            for(int y=0; y<imagen.getHeight(null);y++){
                double muestra[][] = muestra(x,y,bi);
                if(muestra!=null){
                    Color colorRes = colorMascaras(muestra,divisor,casoMascara, k.getMascaras());
                    nueva.setRGB(x, y, colorRes.getRGB());
                }else{
                    nueva.setRGB(x, y, new Color(255,255,255).getRGB());
                }
            }
        }
       return ImageManager.toImage(nueva);
    }
    
    private static double[][] muestra(int x, int y, BufferedImage bi) {
        double matriz[][] = new double[3][3];
        int xx=0;
        int yy=0;
        try{
            for(int i=x-(matriz[0].length-1)/2;i<=x+(matriz[0].length-1)/2;i++){
                for(int j=y-(matriz[0].length-1)/2;j<=y+(matriz[0].length-1)/2;j++){
                    matriz[xx][yy] = bi.getRGB(i,j);
                    yy++;
                }   
                yy=0;
                xx++;
            }
            return matriz;
        } catch (Exception e) {
            return null;
        } 
    }
    
    private static Color colorMascaras(double[][] muestra, int divisor, int casoMascara, double[][][][] mascaras) {
        int mR = 0, mG = 0, mB = 0;
        int r,g,b;
        for(int i=0;i<mascaras[casoMascara].length;i++){
            Color color = Convolucion2.convulacionar(mascaras[casoMascara][i], muestra, divisor);
            r = color.getRed();
            g = color.getGreen();
            b = color.getBlue();
            if(r>mR)mR=r;
            if(g>mG)mG=g;
            if(b>mB)mB=b;
        }
        return new Color((int)Expansion.validarRango(mR),(int)Expansion.validarRango(mG), (int)Expansion.validarRango(mB));                
    }
    
}
