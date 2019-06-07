/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import GUI.JFrameImagen;
import io.ImageManager;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static newpackage.video.binarizar;
import static newpackage.video.imagenResultante;
import static newpackage.video.punto;

/**
 *
 * @author Usuario 1
 */
public class Video2 implements Runnable{

    public NewJFrame nf = new NewJFrame();
    private JFrameImagen frame2 = new JFrameImagen();
    public boolean aux = false;
    
    public Video2() {
        nf= new NewJFrame();
        nf.setVisible(true);
        System.out.println("inicio");
    }

    @Override
    public void run() {
        int i=0;
        while(true){
            if(this.aux==false){
                nf.accion();
                System.out.println(i);
                i++;
                if(nf.getW()!=null){
                    this.aux=true;
                }
            }else{
                nf.accion();
                BufferedImage bi = ImageManager.toBufferedImage(nf.getW());
                Image nueva = imagenResultante(bi);
                Image bin = binarizar(nueva, 100);
                frame2.setImagen(punto(bin, 5));
            }
        }
    }
    
}
