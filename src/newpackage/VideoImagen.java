/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import GUI.JFrameImagen;
import java.awt.Image;
import io.ImageManager;
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
public class VideoImagen implements Runnable{
    
    public static Image imagenResultante(BufferedImage i){
        return ImageManager.toImage(i);
    }

    JFrameImagen frame2;

    FFmpegFrameGrabber frameGrabber;
    String video = "verde.mp4";    
    
    public VideoImagen() throws Exception {
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
    
    
    
//    public static void main(String []args) throws IOException, Exception
//    {
//        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber("verde.mp4");
//        frameGrabber.start();
//        Frame i;
//        OpenCVFrameConverter.ToIplImage converterToIplImage = new OpenCVFrameConverter.ToIplImage();
//        try {
//            int c=0;
//            while(true){
//        	frameGrabber.setFrameNumber(c);//puede ser cualquier frame
//                c++;
//            Frame frame = frameGrabber.grabImage();
//            System.out.println(frame);
//            IplImage image = converterToIplImage.convert(frame);
//            BufferedImage bi = IplImageToBufferedImage(image);
//            Image nueva = imagenResultante(bi);
//            JFrameImagen frame2 = new JFrameImagen(nueva);
//            frame2.setIconImage(nueva);
//            File outputfile = new File("image.jpg");
//            }
////            ImageIO.write(bi, "jpg", outputfile);
////            frameGrabber.stop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
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
            System.out.println(frame);
            IplImage image = converterToIplImage.convert(frame);
            BufferedImage bi = IplImageToBufferedImage(image);
            Image nueva = imagenResultante(bi);
//            JFrameImagen frame2 = new JFrameImagen(nueva);
//            frame2.setIconImage(nueva);
            frame2.setImagen(nueva);
            File outputfile = new File("image.jpg");
            }
//            ImageIO.write(bi, "jpg", outputfile);
//            frameGrabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
