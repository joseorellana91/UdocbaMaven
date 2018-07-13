/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.controlador;

import com.udocba.enumerator.Estatus;
import com.udocba.modelo.dao.AfiliadoDao;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.udocba.modelo.dao.RegistroDao;
import com.udocba.modelo.dao.TramiteDao;
import com.udocba.modelo.entidades.AfiliadoDto;

import com.udocba.modelo.entidades.GrupoDto;
import com.udocba.modelo.entidades.RegistroDto;
import com.udocba.modelo.entidades.TramiteDto;
import com.udocba.modelo.entidades.UsuarioDto;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import vista.FrmSeleccionAfiliad;
import vista.FrmSlc;
import vista.FrmTramite;

/**
 *
 * @author neteoro
 */
public class CtrlTramite extends ControladorAbstracto<TramiteDto>{

    private AfiliadoDao afiliadoDao;
    private TramiteDto dtoTr;
    private TramiteDao daoTr;
    private FrmTramite frmTr;
    private RegistroDto dtoReg;
    private RegistroDao daoReg;
    private FrmSeleccionAfiliad frameSeleccionAfiliado;
    private FrmSlc frmslc= new FrmSlc();
    private UsuarioDto dtoUsuario;
    private GrupoDto dtoGrupo;
    
    private AfiliadoDto dtoafiliado;
    private CtrlSeleccionAfiliado controlAfiliado;
  
   
    private boolean valida;
    
    

    public CtrlTramite(UsuarioDto usrDto,TramiteDto dtoObj, TramiteDao daoObj, FrmTramite frm) {
        
        this.dtoafiliado = new AfiliadoDto();
        this.afiliadoDao = new AfiliadoDao();
        this.controlAfiliado = new CtrlSeleccionAfiliado();
        this.frameSeleccionAfiliado = new FrmSeleccionAfiliad();
        this.dtoTr = dtoObj;
        this.daoTr = daoObj;
        this.frmTr = frm;
        this.dtoUsuario = usrDto;
        
        
        this.frmTr.btnPropiedad.addActionListener(this);
        this.frmTr.btnEstado.addActionListener(this);
        this.frmTr.btnAsignar.addActionListener(this);
        this.frmTr.btnGuardarRegistro.addActionListener(this);
        this.frmTr.btnAfiliado.addActionListener(this);
        this.frmTr.btnClasificacion.addActionListener(this);
        this.frmTr.btnHistorial.addActionListener(this);
        this.frmTr.btnCancelarRegistro.addActionListener(this);
        this.frmTr.btnGuardarRegistro.addActionListener(this);
        this.frmTr.jTableRegistro.addMouseListener(this);
       
        this.frameSeleccionAfiliado.btnBuscar.addActionListener(this);
        
        this.frameSeleccionAfiliado.btnSlcAceptarAfld.addActionListener(this);
        this.frameSeleccionAfiliado.btnSlcCancelarAfld.addActionListener(this);
        this.frmslc.jCmbAceptar.addActionListener(this);
        this.frmslc.jCmbCancelar.addActionListener(this);
    }

    public UsuarioDto getDtoUsuario() {
        return dtoUsuario;
    }

    public void setDtoUsuario(UsuarioDto dtoUsuario) {
        this.dtoUsuario = dtoUsuario;
    }
    
    
    
    @Override
    public void inciar() {
        
        frmTr.setTitle("Gestion Tramites");
        llenarComboEstado();
        frmTr.setVisible(true);
        frmTr.setLocationRelativeTo(null);//centrar ventana
        
        //Para que no se vea Id de Afiliado
        
     
    }

    @Override
    public void limpiarTxt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
         if (ae.getSource() == frmTr.btnPropiedad) {
             
             estadoFormualario("propiedad");
            
             
             
         }
         
          if (ae.getSource() == frmTr.btnEstado) {
         }
          
           if (ae.getSource() == frmTr.btnAsignar) {
               
               
               
         }
           
            if (ae.getSource() == frmTr.btnGuardarTramite) {
                llenarObjeto(dtoTr);
                //valida = daoTr.guardaObjeto(dtoTr);
                daoTr.GuardaEntidad(dtoTr);
         }
            
            if (ae.getSource() == frmTr.btnAfiliado) {
              
               
               CtrlSeleccionAfiliado controlAfiliado = new CtrlSeleccionAfiliado(frameSeleccionAfiliado);
               controlAfiliado.inciar();
               
            }
            if(ae.getSource()== frameSeleccionAfiliado.btnSlcAceptarAfld){
                
                
                int indice = this.frameSeleccionAfiliado.jTableSeleccAfiliado.getSelectedRow();
                Object objetoString = this.frameSeleccionAfiliado.jTableSeleccAfiliado.getValueAt(indice, 0);
                String numeroAfiliado = (String)objetoString;
                dtoafiliado = afiliadoDao.AfiliadoPorNumero(numeroAfiliado);
                
                this.frmTr.txtNafiliado.setText(dtoafiliado.getNumafiliado());
                this.frmTr.txtNombAfiliado.setText(dtoafiliado.getNombre() + " "+dtoafiliado.getApellido());
                this.frameSeleccionAfiliado.setVisible(false);
                this.frmTr.txtNafiliado.setEnabled(false);
                this.frmTr.txtNombAfiliado.setEnabled(false);
                               
               
               }
               
               
               
               
              
         
              if (ae.getSource() == frmTr.btnClasificacion) {
                  
               
         }
               if (ae.getSource() == frmTr.btnHistorial) {
         }
                if (ae.getSource() == frmTr.btnCancelarRegistro) {
         }
                 if (ae.getSource() == frmTr.btnGuardarRegistro) {
         }
           
         
    }
    
     @Override
    public void llenarCombo(List<TramiteDto> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //******************************************************************************************//
    //** Los errores que aparezcan en clases que no esten relacionadas al login (que es lo primero que se ve al iniciar el programa)
    //** los revisamos mas adelante. Empecemos por el principio, una vez se pueda iniciar sesion sin problemas y muestre
    //** el siguiente menu trabajamos en los errores que salgan en ese menu, y asi hasta llegar a este y cualquier otro que no funcione
    //** o presente errores. Es muy importante ir probando todo en tiempo de ejecucion. Los cambios que hagamos aca no los
    //** vamos a poder probar hasta que funcione todo lo que este antes asi que al pedo modificarlo ahora.
    //** Seba.
    //******************************************************************************************//
    @Override
    public void llenarObjeto(TramiteDto o) {
        o.setResumen(frmTr.txtResumen.getText());
        o.setGrupo(dtoGrupo);
       
        o.setAfiliado(dtoafiliado);
        
     
    }

    @Override
    public void seleccionarRegistro() {
         DefaultTableModel tModel = (DefaultTableModel) frmTr.jTableRegistro.getModel();
        int filaseleccionada;
        int id;
        try {

            filaseleccionada = frmTr.jTableRegistro.getSelectedRow();

            if (filaseleccionada == -1) {

                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");

            } else {

                id = ((int) tModel.getValueAt(filaseleccionada, 0));

                dtoReg = daoReg.getEntidad(RegistroDto.class, id);

                
              

            }

        } catch (HeadlessException ex) {

            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void completarFormulario(TramiteDto o)
    {
        frmTr.txtNtramite.setText(String.valueOf(dtoTr.getId()));
        frmTr.txtResumen.setText(dtoTr.getResumen());
      
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
    public void cargarJtable(List<TramiteDto> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarFormulario(TramiteDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param lista
     */
   
   public void cargarTabla(List<RegistroDto> lista){
         DefaultTableModel tModel =(DefaultTableModel)frmTr.jTableRegistro.getModel();
        
        for (RegistroDto registroDto : lista) {
            
           
             
           
            
           
            
            
        }
   
   }
   
   public void llenarComboEstado(){
       this.frmTr.jComboBoxEstado.setModel(new DefaultComboBoxModel(Estatus.values()));
   
   
   }
    
   public void estadoFormualario(String estado){
       
       switch (estado) {
           
           case "frmTr-inicio":
               frmTr.btnAfiliado.setEnabled(false);
               frmTr.btnAsignar.setEnabled(false);
               frmTr.btnCancelarRegistro.setEnabled(false);
               frmTr.btnClasificacion.setEnabled(false);
               frmTr.btnEstado.setEnabled(false);
               frmTr.btnGuardarRegistro.setEnabled(false);
               frmTr.btnGuardarTramite.setEnabled(false);
               frmTr.btnHistorial.setEnabled(false);
               frmTr.btnPropiedad.setEnabled(true);
               frmTr.jTableRegistro.setEnabled(false);
               frmTr.txtAreaDescripcion.setEnabled(false);
           
           case "frmTr-propiedad":
               frmTr.btnAfiliado.setEnabled(false);
               frmTr.btnAsignar.setEnabled(false);
               frmTr.btnCancelarRegistro.setEnabled(false);
               frmTr.btnClasificacion.setEnabled(false);
               frmTr.btnEstado.setEnabled(false);
               frmTr.btnGuardarRegistro.setEnabled(false);
               frmTr.btnGuardarTramite.setEnabled(false);
               frmTr.btnHistorial.setEnabled(false);
               frmTr.btnPropiedad.setEnabled(false);
               frmTr.jTableRegistro.setEnabled(false);
               frmTr.txtClasificacion.setEnabled(false);
               frmTr.txtCreadoRegistro.setEnabled(false);
               frmTr.txtDetalleRegistro.setEnabled(false);
               frmTr.txtFechaRegistro.setEnabled(false);
               frmTr.txtNombAfiliado.setEnabled(false);
               frmTr.txtNtramite.setEnabled(false);
               break;
           default:
               throw new AssertionError();
       }

       
       
   }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
