/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import org.bytedeco.javacv.FrameGrabber;

/**
 *
 * @author Usuario 1
 */
public class main {
    
    public static void main(String args[]) throws FrameGrabber.Exception{
        Video2 vi = new Video2();
        Thread h = new Thread(vi);
        h.start();
    }
}
