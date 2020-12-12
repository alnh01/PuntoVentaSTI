/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import BD.ModeloExcel;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import modal.Verexcel;

public class CExcel implements ActionListener{
    ModeloExcel modelE = new ModeloExcel();
    Verexcel vistaE = new Verexcel(new JFrame(),true);
    JFileChooser selectArchivo = new JFileChooser();
    File archivo;
    int contAction;
    
    public CExcel(Verexcel vistaE, ModeloExcel modeloE){
        this.vistaE=vistaE;
        this.modelE=modeloE;
        this.vistaE.btn_import.addActionListener(this);
        //this.vistaE.btn_export.addActionListener(this);
    }
    
    public void AgregaFiltro(){
        selectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)","xls"));
        selectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)","xlsx"));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        contAction++;
        if(contAction==1)AgregaFiltro();
        
        if(ae.getSource()== vistaE.btn_import){
            if(selectArchivo.showDialog(null,"Seleccionar archivo")==JFileChooser.APPROVE_OPTION){
                archivo=selectArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls")|| archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null,modelE.Importar(archivo,vistaE.JTDatos));
                       vistaE.JTDatos.setAutoResizeMode(vistaE.JTDatos.AUTO_RESIZE_NEXT_COLUMN);
        
                }else{
                    JOptionPane.showMessageDialog(null, "Elija un formato valido");
                }
            }
            
        }
        
////         if(ae.getSource()== vistaE.btn_export){
////            if(selectArchivo.showDialog(null,"Seleccionar a exportar")==JFileChooser.APPROVE_OPTION){
////                archivo=selectArchivo.getSelectedFile();
////                if(archivo.getName().endsWith("xls")|| archivo.getName().endsWith("xlsx")){
////                    JOptionPane.showMessageDialog(null,modelE.Exportar(archivo,vistaE.JTDatos));
////                }else{
////                    JOptionPane.showMessageDialog(null, "Elija un formato valido");
////                }
////            }
            
//        }
    }

    
}
