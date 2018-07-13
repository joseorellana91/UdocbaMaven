/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.controlador;

import com.udocba.modelo.dao.AfiliadoDao;
import com.udocba.modelo.entidades.AfiliadoDto;
import com.udocba.modelo.entidades.TramiteDto;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import vista.FrmSeleccionAfiliad;

/**
 *
 * @author Jose
 */
public class CtrlSeleccionAfiliado extends ControladorAbstracto<AfiliadoDto>  {
    
    private FrmSeleccionAfiliad frmSeleccionAfiliado;
    private AfiliadoDto afiliado;
    private AfiliadoDao afiliadoDao;
    
    
    
    public CtrlSeleccionAfiliado(){
    }
    
    
    public CtrlSeleccionAfiliado(FrmSeleccionAfiliad frm){
        
        this.frmSeleccionAfiliado = frm;
        this.afiliadoDao = new AfiliadoDao();
        this.afiliado = new AfiliadoDto();
    
    
    
    }
    
    
    
    

    @Override
    public void inciar() {
        
       List<AfiliadoDto> lista = afiliadoDao.obtenListaEntidad(AfiliadoDto.class);
       cargarJtable(lista);
       this.frmSeleccionAfiliado.setVisible(true);
    }
    
    

    @Override
    public void limpiarTxt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cargarJtable(List<AfiliadoDto> lista) {
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        String columnas[]= {"N_Afiliado","Apellido","Nombre"};
        DefaultTableModel tModel = new DefaultTableModel(null,columnas);
        
        for (AfiliadoDto afiliadoDto : lista) {
            String registro[]= new String[3];
            registro[0]= afiliadoDto.getNumafiliado();
            registro[1]= afiliadoDto.getApellido();
            registro[2]=afiliadoDto.getNombre();
            
           
             
            tModel.addRow(registro);
        }
        frmSeleccionAfiliado.jTableSeleccAfiliado.setModel(tModel);
        frmSeleccionAfiliado.jTableSeleccAfiliado.getColumnModel().getColumn(0).setCellRenderer(tcr);
        frmSeleccionAfiliado.jTableSeleccAfiliado.getColumnModel().getColumn(1).setCellRenderer(tcr);
        frmSeleccionAfiliado.jTableSeleccAfiliado.getColumnModel().getColumn(2).setCellRenderer(tcr);

        
        
        
        
        
        
    }

    @Override
    public void llenarCombo(List<AfiliadoDto> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void seleccionarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarObjeto(AfiliadoDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarFormulario(AfiliadoDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmSeleccionAfiliado.btnBuscar){
            
            
        
        
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
