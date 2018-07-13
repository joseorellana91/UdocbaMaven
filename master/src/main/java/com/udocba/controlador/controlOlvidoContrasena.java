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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import com.udocba.modelo.entidades.UsuarioDto;

import com.udocba.modelo.dao.UsuarioDao;

import vista.frmLogin;
import vista.frmOlvidoContrasena;

/**
 *
 * @author Jose
 */
public class controlOlvidoContrasena implements ActionListener, MouseListener {
    
    
    private frmOlvidoContrasena frm;
   // private olvidoContrasenaDao interfac;
      UsuarioDao daoUsuaurio;
      UsuarioDto dtoObjeto = new UsuarioDto();

    
        
   // public controlOlvidoContrasena(frmOlvidoContrasena frm, olvidoContrasenaDao interfac){
         public controlOlvidoContrasena(frmOlvidoContrasena frm, UsuarioDao dao){
        //this.interfac=interfac;
        this.frm= frm;
        this.daoUsuaurio = dao;
        
        
        
        this.frm.jbEnviar.addActionListener(this);
        this.frm.jbAtras.addActionListener(this);
        
    
    }
    
        public void sendMail(String email, String contrasena){
        
        
        //Direccion del destinatario del mensaje
        
        
       
        //Direccion de quien envia el mensaje
        //Contrasena  y password
        
        String from = "renguero128@gmail.com";
        final String username= "renguero128";
        final String password= "elcapo23";
        
        
        
        Properties props = new Properties();
      
        //nombre del host de correo
        
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        
        //tls si esta disponible
        
        props.setProperty("mail.smtp.starttls.enable", "true");
    
        //puerto de gmail para envio de correos
        
        props.setProperty("mail.smtp.port", "587");
        
        //nombre de usuario
        
        props.setProperty("mail.smtp.user", from);
        
        //Si requiere o no usuario y pass
        
        props.setProperty("mail.smtp.auth", "true");
        
        
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        
        try{
        
        
        MimeMessage msg = new MimeMessage(mailSession);
        
        msg.setFrom(new InternetAddress(from));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        msg.setHeader("Su clave de ingreso es", ":");
        msg.setText(contrasena);
        
        Transport t = mailSession.getTransport("smtp");
        t.connect(from, password);
        t.sendMessage(msg, msg.getAllRecipients());
        
        JOptionPane.showMessageDialog(null,"Se envio el mail correctamente");
        
        }catch(Exception e){
        
          JOptionPane.showMessageDialog(null,"Error al enviar el e-mail "+ e);
        
        }
        
    
    }
      
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.jbEnviar) {
           // int documento = 0;
           // documento = Integer.parseInt(frm.txtDocumento.getText());
            String documento = null;
             documento = frm.txtDocumento.getText();
             dtoObjeto = daoUsuaurio.getUsuarioByDoc(documento);
            
            String mail = dtoObjeto.getEmail();
            String contrasena = dtoObjeto.getContrasena();
           // String mail = interfac.emailEmpleado(documento);
           // String contrasena = interfac.contrasenaEmpleado(documento);
            if (mail.isEmpty()) {

                JOptionPane.showMessageDialog(null, "No existe usuario con el documento ingresado");

            } else {

                sendMail(mail, contrasena);

            }

        }

        if (e.getSource() == frm.jbAtras) {

            frmLogin frmlogin = new frmLogin();
           // LoginDao dao = new LoginDao();
            controlLogin controlador = new controlLogin(frmlogin, daoUsuaurio);
            controlador.iniciar();
            frmlogin.setVisible(true);
            frm.setVisible(false);

        }

    }

    public void inciar() {

        try {

            frm.setTitle("Recuperacion de contrasena");
            frm.setLocationRelativeTo(null);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error" + e);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
