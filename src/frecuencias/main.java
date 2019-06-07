/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frecuencias;

import GUI.JFrameImagen;
import frecuencias.HerramientasColor.CanalColor;
import io.ImageManager;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author Usuario 1
 */
public class main {
    public static void main(String args[]){
        Image imagen1 = ImageManager.openImage();
        BufferedImage imagen = ImageManager.toBufferedImage(imagen1);
        Gestor g = new Gestor(imagen);
//        Image aux = ImageManager.toImage(g.obtenerImagenEspacial());
        Image aux2 =ImageManager.toImage(g.obtenerImagenFrecuencias(true));
//        JFrameImagen frame1 = new JFrameImagen(aux);
        JFrameImagen frame2 = new JFrameImagen(aux2);
    }
    
}
