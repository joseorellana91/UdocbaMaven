/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.controlador;

import com.udocba.modelo.dao.EscuelaDao;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import com.udocba.modelo.entidades.EscuelaDto;
import java.awt.event.ItemEvent;
import vista.FrmEscuela;

/**
 *
 * @author userund
 */
public class CtrlEscuela extends ControladorAbstracto<EscuelaDto>{

    private EscuelaDto dtoescuela;
    private EscuelaDao daoescuela;
    private FrmEscuela frmEscuela;

    public CtrlEscuela() {
    }

    public CtrlEscuela(EscuelaDto dtoescuela, EscuelaDao daoescuela, FrmEscuela frmEscuela) {
        this.dtoescuela = dtoescuela;
        this.daoescuela = daoescuela;
        this.frmEscuela = frmEscuela;
        
        this.frmEscuela.btnAceptar.addActionListener(this);
        this.frmEscuela.btnCancelar.addActionListener(this);
    }
    
    
    
    
    @Override
    public void inciar() {
         frmEscuela.setTitle("ABM AFILIADO");
        frmEscuela.setLocationRelativeTo(null);//centrar ventana
        frmEscuela.setVisible(true);
       
    }

    @Override
    public void limpiarTxt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cargarJtable(List<EscuelaDto> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarCombo(List<EscuelaDto> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void seleccionarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarObjeto(EscuelaDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarFormulario(EscuelaDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
