/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import BD.ModeloExcel;
import Controller.CExcel;
import javax.swing.JFrame;
import modal.Verexcel;

/**
 *
 * @author Najera
 */
public class Excel {
    
        public void abrir(){
        Verexcel  vsti = new Verexcel(new JFrame(),true);
        ModeloExcel modeloE = new ModeloExcel();
        CExcel contraControladorExcel = new CExcel(vsti, modeloE);
        vsti.setVisible(true);
        vsti.setLocationRelativeTo(null);
    }
    
    
    
}
