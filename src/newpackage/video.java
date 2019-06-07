/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import GUI.JFrameImagen;
import java.awt.Image;
import io.ImageManager;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

/**
 *
 * @author Usuario 1
 */
public class video implements Runnable{
    
    private JFrameImagen frame2;
    private FFmpegFrameGrabber frameGrabber;
    private String video = "verde.mp4";    

    public static Image imagenResultante(BufferedImage i){
        return ImageManager.toImage(i);
    }
    
    public static Image binarizar(Image original, int hum){
        BufferedImage bi = ImageManager.toBufferedImage(original);
        // recorrido por pixel para hacer el tono gris
        for(int x=0; x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                //extrer el tono del pixel en RGB
                Color color = new Color(bi.getRGB(x, y));
                // calculamos la reducción por promedio
                int aux = (color.getRed()+color.getGreen()+color.getBlue());
                if(aux<hum){
                    color = new Color(0,0,0);
                }else{
                    color = new Color(255,255,255);
                }
                // se modifica el RGB del pixel en base al nuevo tono
                bi.setRGB(x, y, color.getRGB());
            }
        } 
        return ImageManager.toImage(bi);   
    }
    
    public static Image punto(Image original, int t){
        BufferedImage bi = ImageManager.toBufferedImage(original);
        // recorrido por pixel para hacer el tono gris
        int cuantos=0, h=0, w=0; 
        for(int x=0; x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                //extrer el tono del pixel en RGB
                Color color = new Color(bi.getRGB(x, y));
                // calculamos la reducción por promedio
                if(color.getBlue()==0){
                    h+=y;
                    w+=x;
                    cuantos++;
                }
                // se modifica el RGB del pixel en base al nuevo tono
                bi.setRGB(x, y, color.getRGB());
            }
        } 
        //bi.setRGB(w/cuantos, h/cuantos, new Color(255, 0, 0).getRGB());
        if(cuantos==0){
            
        }else{
            h/=cuantos;
            w/=cuantos;
            for(int a=0; a<t; a++){
                for(int b=0; b<t; b++){
                    if((w+a)<255 && (h+b)<255){
                        bi.setRGB(w+a, h+b, new Color(255, 0, 0).getRGB());
                    }
                    if(0<(w-a) && (h+b)<255){
                        bi.setRGB(w-a, h+b, new Color(255, 0, 0).getRGB());
                    }
                    if(0<(w-a) && 0<(h-b)){
                        bi.setRGB(w-a, h-b, new Color(255, 0, 0).getRGB());
                    }
                    if((w+a)<255 && 0<(h-b)){
                        bi.setRGB(w+a, h-b, new Color(255, 0, 0).getRGB());
                    }
                }    
            }
        }
        
//        bi.setRGB((w+1)/cuantos, (h+1)/cuantos, new Color(255, 0, 0).getRGB());
//        bi.setRGB((w+2)/cuantos, (h+2)/cuantos, new Color(255, 0, 0).getRGB());
//        bi.setRGB((w+3)/cuantos, (h+3)/cuantos, new Color(255, 0, 0).getRGB());
//        bi.setRGB((w-1)/cuantos, (h-1)/cuantos, new Color(255, 0, 0).getRGB());
//        bi.setRGB((w-2)/cuantos, (h-2)/cuantos, new Color(255, 0, 0).getRGB());
//        bi.setRGB((w-3)/cuantos, (h-3)/cuantos, new Color(255, 0, 0).getRGB());
        return ImageManager.toImage(bi);   
    }
    
    public static Image generarImagenEnGrises(Image original){
        BufferedImage bi = ImageManager.toBufferedImage(original);
     // recorrido por pixel para hacer el tono gris
     for(int x=0; x<bi.getWidth();x++){
         for(int y=0;y<bi.getHeight();y++){
         //extrer el tono del pixel en RGB
         Color color = new Color(bi.getRGB(x, y));
         // calculamos la reducción por promedio
         int gris = (color.getRed()+color.getGreen()+color.getBlue())/3;
         // generamos el color con el nuevo tono promedio(gris)
         color = new Color(gris,gris,gris);
         // se modifica el RGB del pixel en base al nuevo tono
         bi.setRGB(x, y, color.getRGB());
         }
     }
     return ImageManager.toImage(bi);
    }
    
    public video() throws Exception {
        frameGrabber = new FFmpegFrameGrabber(video);
        frameGrabber.start();
        Frame i;
        OpenCVFrameConverter.ToIplImage converterToIplImage = new OpenCVFrameConverter.ToIplImage();
            frameGrabber.setFrameNumber(1);//puede ser cualquier frame
            Frame frame = frameGrabber.grabImage();
            System.out.println(frame);
            IplImage image = converterToIplImage.convert(frame);
            BufferedImage bi = IplImageToBufferedImage(image);
            Image nueva = imagenResultante(bi);
            this.frame2 = new JFrameImagen(nueva);
    }
    
    public static BufferedImage IplImageToBufferedImage(IplImage src) {
        OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        Frame frame = grabberConverter.convert(src);
        return paintConverter.getBufferedImage(frame,1);
    }    

    @Override
    public void run() {
        
        try {
            frameGrabber.start();
        } catch (Exception ex) {
            Logger.getLogger(VideoImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Frame i;
        OpenCVFrameConverter.ToIplImage converterToIplImage = new OpenCVFrameConverter.ToIplImage();
        try {
            int c=0;
            while(frameGrabber.getLengthInFrames()!=c){
        	frameGrabber.setFrameNumber(c);//puede ser cualquier frame
                c++;
            Frame frame = frameGrabber.grabImage();
                System.out.println(c+" / "+frameGrabber.getLengthInFrames());
            IplImage image = converterToIplImage.convert(frame);
            BufferedImage bi = IplImageToBufferedImage(image);
            Image nueva = imagenResultante(bi);
            Image bin = binarizar(nueva, 150);
            frame2.setImagen(punto(bin, 5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
