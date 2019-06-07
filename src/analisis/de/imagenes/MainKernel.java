/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.de.imagenes;

import GUI.JFrameImagen;
import io.ImageManager;
import java.awt.Image;
import static muestreo.AlpicaKernel.aplicarMascaras;
import muestreo.Convolucion2;

/**
 *
 * @author Usuario 1
 */
public class MainKernel {
    public static void main(String[] args) {
        Image imagen = ImageManager.openImage();
        JFrameImagen frame1 = new JFrameImagen(imagen);
        // 1 = diferenciaPixeles
        // 2 = diferenciaPixelesSeparados
        // 3 = kirsch
        // 4 = laplace
        // 5 = prewitt
        // 6 = roberts
        // 7 = sobel
        // aplicarMascaras(Image imagen, int divisor, int caso)
        Image nueva = aplicarMascaras(imagen, 1, 6);
        JFrameImagen frame2 = new JFrameImagen(nueva);
        System.out.println();
            
    }
}
