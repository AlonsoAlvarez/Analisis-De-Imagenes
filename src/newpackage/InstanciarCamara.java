/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.BorderLayout;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Processor;
import javax.swing.JFrame;
 
/**
*
* @author Rolando
*/
public class InstanciarCamara extends JFrame {
 
    private static Player player;
    private MediaLocator localizador;
    private Processor p;
    private CaptureDeviceInfo dispositivo = null;
    private static String source = "vfw:Microsoft WDM Image Capture (Win32):0";

    public InstanciarCamara() {
        setLayout(new BorderLayout());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Demo Cámara Web");
        player = null;
        localizador = null;
        dispositivo = CaptureDeviceManager.getDevice(source);
        localizador = dispositivo.getLocator();
        iniciarCaptura();
    }
 
    private void iniciarCaptura() {
        try {
            player = Manager.createRealizedPlayer(localizador);
            player.start();
            if (player.getVisualComponent() != null) {
                add(player.getVisualComponent(), BorderLayout.CENTER);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
 
    
    public static void main(String[] args) {
        // TODO code application logic here
        InstanciarCamara.setDefaultLookAndFeelDecorated(true);
        InstanciarCamara camara = new InstanciarCamara();
        camara.setVisible(true);
    }
 
}