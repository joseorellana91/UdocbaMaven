/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

import com.udocba.modelo.entidades.UsuarioDto;

import com.udocba.modelo.dao.UsuarioDao;
import vista.FrmMenu;

import vista.frmLogin;
import vista.frmOlvidoContrasena;

/**
 *
 * @author Jose
 */
public class controlLogin implements ActionListener, MouseListener {
    
  //  private LoginDao login;
   private frmLogin frmlogin;
   private UsuarioDao daoUsuario;
   private UsuarioDto dtoUsuario;
    
    public controlLogin(frmLogin log,UsuarioDao dao){
    
        this.frmlogin= log;
    //  this.login = login;
        this.daoUsuario = dao;
        this.frmlogin.jbIngresar.addActionListener(this);
        this.frmlogin.jbAtras.addActionListener(this);
        this.frmlogin.jbRecordarContrasena.addActionListener(this);
        
    }
    
    
    
    public void iniciar(){
        
        try{
            frmlogin.setTitle("Login");
            frmlogin.setLocationRelativeTo(null);
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null,"Error"+e);
        
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource()== frmlogin.jbIngresar){
        dtoUsuario = daoUsuario.getUsuarioByCredenciales(frmlogin.txtUsuario.getText(), frmlogin.pswContrasena.getText());
       // if(daoUsuario.buscarUsuario(frmlogin.txtUsuario.getText(), frmlogin.pswContrasena.getText())){
        if(dtoUsuario != null){
            if( dtoUsuario.getId()!= 0 ){
            JOptionPane.showMessageDialog(null, "INGRESO CORRECTO AL SISTEMA");
            System.out.println("usuario: " + dtoUsuario.getId() + " " +  dtoUsuario.getNombre());
       
            FrmMenu menuFrm = new FrmMenu();
            CtrlMenuHome homeCtrl = new CtrlMenuHome(menuFrm, dtoUsuario);
        
            frmlogin.dispose();
            homeCtrl.inciar();
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Credenciales Invalidas, Verifique Usuario y contraseña");
        }
        }  else{
            JOptionPane.showMessageDialog(null, "Credenciales Invalidas, Verifique Usuario y contraseña");
        }}
        
      
        if (e.getSource() == frmlogin.jbRecordarContrasena){
            
            frmOlvidoContrasena frmcontrasena = new frmOlvidoContrasena();
          //  olvidoContrasenaDao contraDao = new olvidoContrasenaDao();
           
            controlOlvidoContrasena controlador = new controlOlvidoContrasena(frmcontrasena,daoUsuario);
            controlador.inciar();
            frmcontrasena.setVisible(true);
            frmlogin.dispose();
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
    
}
