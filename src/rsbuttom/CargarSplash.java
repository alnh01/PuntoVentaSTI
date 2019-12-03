/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuttom;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import principal.Login;
import principal.Session;
import principal.splash;

/**
 *
 * @author Najera10
 */
public class CargarSplash extends Thread{

    
    private JProgressBar progressBar;
    private splash window;
    private JLabel  lblProgress;

    public CargarSplash(JProgressBar progressBar, splash window, JLabel lblProgress) {
        super();
        this.progressBar = progressBar;
        this.window = window;
        this.lblProgress = lblProgress;
    }

    @Override
    public void run() {
        setProgress(0);
        pause(10);
        setProgress(10);
        pause(20);
        setProgress(20);
        pause(20);
        setProgress(30);
        pause(20);
        setProgress(45);
        pause(20);
        setProgress(80);
        pause(30);
        setProgress(100);


        
    }
    
    public void pause(int mls){
        try {
            Thread.sleep(mls);
        } catch (InterruptedException ex) {
            Logger.getLogger(CargarSplash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setProgress(int valor){
        progressBar.setValue(valor);
        lblProgress.setText(valor+"%");
        pause(1000);
        if (valor == 100) {
            pause(1000);
            window.dispose();
            new Session().setVisible(true);
        }
    }
    
}
