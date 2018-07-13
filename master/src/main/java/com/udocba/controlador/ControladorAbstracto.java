/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.controlador;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author neteoro
 * @param <objeto>
 */
public abstract class ControladorAbstracto <objeto> implements ActionListener,MouseListener, ItemListener{
    
 
 
 
 
    private  DefaultTableModel modelo;

    
    
    abstract public void inciar();
    abstract public void limpiarTxt();
    abstract public void cargarJtable(List<objeto> lista);
    abstract public void llenarCombo(List<objeto> lista);
    abstract public void seleccionarRegistro();
    abstract public void llenarObjeto(objeto o);
    abstract public void llenarFormulario(objeto o);
        
}


