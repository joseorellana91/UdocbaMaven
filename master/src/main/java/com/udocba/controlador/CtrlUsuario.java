/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.controlador;

import com.udocba.enumerator.Estatus;
import com.udocba.enumerator.TipoDeDocumento;
import com.udocba.enumerator.TipoDeUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import com.udocba.modelo.dao.UsuarioDao;
import com.udocba.modelo.entidades.TramiteDto;
import com.udocba.modelo.entidades.UsuarioDto;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import vista.FrmMenu;
import vista.FrmTramite;
import vista.FrmUsuario;

/**
 *
 * @author neteoro
 */
public class CtrlUsuario extends ControladorAbstracto<UsuarioDto>{

    /**
     *
     */
    private FrmUsuario frm;
    private UsuarioDto dtoObj;
    private UsuarioDao daoObj;
    private int id;

    public CtrlUsuario(FrmUsuario frm, UsuarioDto dtoObj, UsuarioDao daoObj) {
        this.frm = frm;
        this.dtoObj = dtoObj;
        this.daoObj = daoObj;
        this.frm.jbAgregar.addActionListener(this);
        this.frm.jbAtras.addActionListener(this);
        this.frm.jbEliminar.addActionListener(this);
        this.frm.jbModificar.addActionListener(this);
        this.frm.tabla.addMouseListener(this);
       
    }
            
    
            
    
    @Override
    public void inciar() {
       try{ 
        frm.setTitle("Gestion Usuarios");
        try{
        //  List <UsuarioDto> lista = daoObj.obtenListaEntidad(UsuarioDto.class);
            List<UsuarioDto> lista = daoObj.FilterUsuarioByState();
            cargarJtable(lista);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas al cargar la lista de usuarios");
            System.err.println(e);
        }
        llenarComboTipoDoc();
        llenarComboTipoUsr();
   //     llenarComboEstado();
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);//centrar ventana
        frm.jbModificar.setEnabled(false);
        frm.jbEliminar.setEnabled(false);
       }catch(Exception e){
           System.out.println("algo fallo en ctrlUsuario");
       }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiarTxt() {
        
        frm.txtNombre.setText("");
        frm.txtApellido.setText("");
        frm.txtDocumento.setText("");
        frm.txtEmail.setText("");
        frm.txtContrasena.setText("");
        frm.txtContrasenaBis.setText("");
        frm.cbTipoDoc.setSelectedItem(TipoDeDocumento.SELECCIONE);
        frm.cbTipoUsr.setSelectedItem(TipoDeUsuario.SELECCIONE);
     //   frm.cbEstado.setSelectedItem(Estatus.SELECCIONE);
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public void seleccionarRegistro() {

        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void actionPerformed(ActionEvent ae) {

    //****** boton atras ************    
        if(ae.getSource() == frm.jbAtras){
            System.out.println("apreto el boton atras");
            System.out.println("dto usuario: " + dtoObj.getId() + " " +  dtoObj.getNombre());
            frm.dispose();
            FrmMenu menuFrm = new FrmMenu();
            CtrlMenuHome homeCtrl = new CtrlMenuHome(menuFrm, dtoObj);
            homeCtrl.inciar();
        }
        
    //***** boton agregar ***********
        
        if(ae.getSource() == frm.jbAgregar){
 
    ////Devuelve los bordes de los campos a color normal////
          resetColores();
          
        if(validarCampos()){
          if(usuarioExistente(frm.txtDocumento.getText())) { 
            UsuarioDto user = new UsuarioDto();
            UsuarioDao daoUsuario = new UsuarioDao();
        
            user.setNombre(frm.txtNombre.getText());
            user.setApellido(frm.txtApellido.getText());
            user.setContrasena(frm.txtContrasena.getText());
            user.setDocumento(frm.txtDocumento.getText());
            user.setEmail(frm.txtEmail.getText());
            
            TipoDeDocumento tipoDoc = (TipoDeDocumento)frm.cbTipoDoc.getSelectedItem();
            user.setTipoDocumento(tipoDoc);
            
            TipoDeUsuario tipoUsr = (TipoDeUsuario)frm.cbTipoUsr.getSelectedItem();
            user.setTipousuario(tipoUsr);
            
            user.setEstatus(Estatus.ACTIVO);
      //      Estatus estatus = (Estatus)frm.cbEstado.getSelectedItem();
      //      user.setEstatus(estatus);
            
            daoUsuario.GuardaEntidad(user);
            JOptionPane.showMessageDialog(null, "Se dio de alta al usuario " + frm.txtNombre.getText());
            limpiarTxt();
        //  List <UsuarioDto> lista = daoObj.obtenListaEntidad(UsuarioDto.class);
            List<UsuarioDto> lista = daoObj.FilterUsuarioByState();
            cargarJtable(lista);
          }else{
              JOptionPane.showMessageDialog(null, "Ya existe un usuario con el dni ingresado");
          }
        }else{
          
            JOptionPane.showMessageDialog(null, "¡Debe completar los campos marcados en rojo!");
          }    
  
        }
     //*********MODIFICAR ******************
        if(ae.getSource() == frm.jbModificar){
       
          resetColores();  
          if(validarCampos()){  
            UsuarioDto user = new UsuarioDto();
            UsuarioDao daoUsuario = new UsuarioDao();
            System.out.println("El id es: " + id);
            
            user.setId(id);
            user.setNombre(frm.txtNombre.getText());
            user.setApellido(frm.txtApellido.getText());
            user.setContrasena(frm.txtContrasena.getText());
            user.setDocumento(frm.txtDocumento.getText());
            user.setEmail(frm.txtEmail.getText());
            
            TipoDeDocumento tipoDoc = (TipoDeDocumento)frm.cbTipoDoc.getSelectedItem();
            user.setTipoDocumento(tipoDoc);
            
            TipoDeUsuario tipoUsr = (TipoDeUsuario)frm.cbTipoUsr.getSelectedItem();
            user.setTipousuario(tipoUsr);
            user.setEstatus(Estatus.ACTIVO);
            daoUsuario.updateEntidad(user);
            JOptionPane.showMessageDialog(null,"Se actualizo el usuario");
            
            limpiarTxt();
      //    List <UsuarioDto> lista = daoObj.obtenListaEntidad(UsuarioDto.class);
            List<UsuarioDto> lista = daoObj.FilterUsuarioByState();
            cargarJtable(lista);
            frm.jbEliminar.setEnabled(false);
            frm.jbModificar.setEnabled(false);
          }else{
                 JOptionPane.showMessageDialog(null, "¡Debe completar los campos marcados en rojo!");
          }
        }
        
       //***********ELIMINAR ******************* 
         if(ae.getSource() == frm.jbEliminar){
            UsuarioDto user;
            UsuarioDao daoUsuario = new UsuarioDao();
            
            System.out.println("El id es: " + id);
            user = daoUsuario.ObtenerUsuarioObj(id);
            
            int resp;
            resp = JOptionPane.showConfirmDialog(null,"Esta seguro que desea eliminar al usuario " + user.getNombre(), "Eliminar usuario",JOptionPane.OK_CANCEL_OPTION);
            if(resp == JOptionPane.OK_OPTION){
                try{
                    user.setEstatus(Estatus.INACTIVO);
                    daoUsuario.updateEntidad(user);
                //  daoUsuario.eliminar(user);
                    JOptionPane.showMessageDialog(null,"Se elimino al usuario");
                    resetColores();
                    limpiarTxt();
                //  List <UsuarioDto> lista = daoObj.obtenListaEntidad(UsuarioDto.class);
                    List<UsuarioDto> lista = daoObj.FilterUsuarioByState();
                    cargarJtable(lista);
                    frm.jbEliminar.setEnabled(false);
                    frm.jbModificar.setEnabled(false);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
                    resetColores();
                    limpiarTxt();
                }
            }
     
        }
      
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //**** llenar combos *******
    
    public void llenarComboTipoDoc(){
       try{ 
           
            this.frm.cbTipoDoc.setModel(new DefaultComboBoxModel(TipoDeDocumento.values()));

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Problema al cargar la lista de tipos de documento");
            }
    }
    
     public void llenarComboTipoUsr(){
  
       try{  
           
           this.frm.cbTipoUsr.setModel(new DefaultComboBoxModel(TipoDeUsuario.values()));
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Problema al cargar la lista de tipos de usuario");
       }
    }
     
  /*   public void llenarComboEstado(){
       try{   
         
           this.frm.cbEstado.setModel(new DefaultComboBoxModel(Estatus.values()));
   
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Problema al cargar la lista de estados");
       }
    }*/

     //*** llenar combos fin ****
     
     
    @Override
    public void mouseClicked(MouseEvent me) {
    //  JOptionPane.showMessageDialog(null, "mouse clicked");
     // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
       /*  JOptionPane.showMessageDialog(null, "mouse pressed");
        
         */ 
        //**** para que no intenten editar campos directamente en la tabla **********
        if(me.getClickCount()== 2){
            frm.tabla.editingCanceled(null);
            JOptionPane.showMessageDialog(null,"Para editar seleccione un registro, edite los campos de la izquierda"
                    + " y luego precione Modificar.");
           
        }
        
        
        limpiarTxt();
        int fila= frm.tabla.getSelectedRow();
        if(fila>=0){
        try{
        id = Integer.parseInt(frm.tabla.getValueAt(fila, 0).toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener el id del item seleccionado");
        }
        frm.txtNombre.setText(frm.tabla.getValueAt(fila,1).toString());
        frm.txtApellido.setText(frm.tabla.getValueAt(fila, 2).toString());
   //   frm.cbTipoDoc.setSelectedItem(frm.tabla.getValueAt(fila, 3).toString());
        frm.txtDocumento.setText(frm.tabla.getValueAt(fila, 4).toString());
        frm.txtEmail.setText(frm.tabla.getValueAt(fila, 5).toString());
        frm.txtContrasena.setText(frm.tabla.getValueAt(fila, 6).toString());
        
        frm.jbModificar.setEnabled(true);
        frm.jbEliminar.setEnabled(true);
    }
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
   //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarObjeto(UsuarioDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cargarJtable(List<UsuarioDto> lista) {
    
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        String columnas[]= {"Id", "Nombre","Apellido","TipoDoc", "DNI","Email", "Contraseña" , "Rol"};
        DefaultTableModel tModel = new DefaultTableModel(null,columnas);
     //   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (UsuarioDto usuarioDto : lista) {
            String registro[]= new String[8];
            registro[0]= String.valueOf(usuarioDto.getId());
            registro[1]=usuarioDto.getNombre();
            registro[2]=usuarioDto.getApellido();
            registro[3]=usuarioDto.getTipoDocumento().toString();
            registro[4]=usuarioDto.getDocumento();
            registro[5]=usuarioDto.getEmail();
            registro[6]=usuarioDto.getContrasena();
            registro[7]=usuarioDto.getTipousuario().toString();
            
            tModel.addRow(registro);
        }
        frm.tabla.setModel(tModel);
        frm.tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
        frm.tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
        frm.tabla.getColumnModel().getColumn(2).setCellRenderer(tcr);
        frm.tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
        frm.tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
        frm.tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
        frm.tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
        frm.tabla.getColumnModel().getColumn(7).setCellRenderer(tcr);
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     ////Devuelve los bordes de los campos a color normal////
    public void resetColores(){
          frm.txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
          frm.txtApellido.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
          frm.cbTipoDoc.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
          frm.txtDocumento.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
          frm.txtEmail.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
          frm.txtContrasena.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
          frm.txtContrasenaBis.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
          frm.cbTipoUsr.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    //    frm.cbEstado.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            
    }
    
    public boolean validarCampos(){
         String nombre       = "S";
         String apellido     = "S"; 
         String contrasenia  = "S";
         String contrasenia2 = "S";
         String email        = "S"; 
         String documento    = "S";
         String tipDoc       = "S";
         String tipoUsuario  = "S";
    //   String estadousr    = "S";
        
         if(frm.txtNombre.getText().isEmpty() || frm.txtNombre == null){
             nombre = "N";
             frm.txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
         else if(frm.txtApellido.getText().isEmpty() || frm.txtApellido == null){
             apellido = "N";
              frm.txtApellido.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
           else if(frm.cbTipoDoc.getSelectedItem().toString().equals("Seleccione")){
             tipDoc = "N";
              frm.cbTipoDoc.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
         else if(frm.txtDocumento.getText().isEmpty() || frm.txtDocumento == null){
             documento = "N";
              frm.txtDocumento.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
         else if(frm.txtEmail.getText().isEmpty() || frm.txtEmail == null){
             email = "N";
              frm.txtEmail.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
         else if(frm.txtContrasena.getText().isEmpty() || frm.txtContrasena == null){
             contrasenia = "N";
              frm.txtContrasena.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
       
         else if(!frm.txtContrasena.getText().equals(frm.txtContrasenaBis.getText())){
             contrasenia2 = "N";
              frm.txtContrasenaBis.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
              JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden");
         }
            else if(frm.cbTipoUsr.getSelectedItem().toString().equals("Seleccione")){
             tipoUsuario = "N";
              frm.cbTipoUsr.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
         /*   else if(frm.cbEstado.getSelectedItem().toString().equals("Seleccione")){
             estadousr = "N";
              frm.cbEstado.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         } */
  
        return !(nombre.equals("N") || apellido.equals("N") || contrasenia.equals("N") || contrasenia2.equals("N")
                || email.equals("N") || documento.equals("N") || tipDoc.equals("N") || tipoUsuario.equals("N"));
         
    }
    
    public boolean usuarioExistente(String dni){
          UsuarioDto user;
          UsuarioDao daoUsuario = new UsuarioDao();
          
          user = daoUsuario.getUsuarioByDoc(dni);
          
          if(user == null)
              return true;
          else
              return false;
            
        
    }

    @Override
    public void llenarCombo(List<UsuarioDto> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarFormulario(UsuarioDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
