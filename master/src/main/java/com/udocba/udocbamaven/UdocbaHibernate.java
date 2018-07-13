/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.udocbamaven;

import com.udocba.controlador.CtrlAfiliado;
import com.udocba.controlador.CtrlEscuela;
import com.udocba.controlador.controlLogin;
import com.udocba.enumerator.EstadoAfiliado;
import com.udocba.enumerator.EstadoCivil;
import com.udocba.enumerator.EstadoLaboral;
import com.udocba.enumerator.Estatus;
import com.udocba.enumerator.TipoDeDocumento;
import com.udocba.enumerator.TipoDeUsuario;
import com.udocba.modelo.entidades.AfiliadoDto;
import com.udocba.modelo.dao.AfiliadoDao;
import com.udocba.modelo.dao.DireccionAfiliadoDao;
import com.udocba.modelo.dao.EscuelaDao;
import com.udocba.modelo.dao.LocalidadDao;
import com.udocba.modelo.dao.ProvinciaDao;
import com.udocba.modelo.dao.ServicioDao;
import com.udocba.modelo.dao.UsuarioDao;
import com.udocba.modelo.entidades.DireccionAfiliadoDto;
import com.udocba.modelo.entidades.DireccionEscuelaDto;
import com.udocba.modelo.entidades.EscuelaDto;
import com.udocba.modelo.entidades.LocalidadDto;
import com.udocba.modelo.entidades.ProvinciaDto;
import com.udocba.modelo.entidades.ServicioDto;
import com.udocba.modelo.entidades.UsuarioDto;
import java.util.List;
import javax.swing.JOptionPane;
//import com.udocba.modelo.entidades.TelefonoDto;
import vista.FrmAbmAfiliado;
import vista.FrmEscuela;
import vista.frmLogin;

/**
 *
 * @author neteoro
 */
public class UdocbaHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

 
        
        UsuarioDao daoUsuario = new UsuarioDao();  
        UsuarioDto user;
        user = daoUsuario.getUsuarioByCredenciales("admin1", "udocba2017");
        
        if(user == null){
            UsuarioDto user2 = new UsuarioDto();
            user2.setNombre("admin1");
            user2.setApellido("udocba");
            user2.setContrasena("udocba2017");
            user2.setDocumento("0000000");
            user2.setEmail("admin@hotmail.com");
            user2.setEstatus(Estatus.ACTIVO);
            user2.setTipoDocumento(TipoDeDocumento.DNI);
            user2.setTipousuario(TipoDeUsuario.ADMINISTRADOR);
            
            daoUsuario.GuardaEntidad(user2);
            JOptionPane.showMessageDialog(null, "Usuario administrador creado con exito");
        }


        frmLogin frmlog= new frmLogin();
        controlLogin loginCtl = new controlLogin(frmlog,daoUsuario );
        loginCtl.iniciar();
        frmlog.setVisible(true);
        System.out.println("********************************************");
        System.out.println("ACCESO ADM: USER: admin1 / PASS: udocba2017 ");
        System.out.println("********************************************");
     /*   
        TramiteDao tram = new TramiteDao();
       
        AfiliadoDto afiliado = new AfiliadoDto();
        afiliado.setApellido("canalla");
        afiliado.setDocumento("33456256");
        afiliado.setEmail("asdasd@hotmail.com");
        afiliado.setEstadocivil(EstadoCivil.CASADO);
        afiliado.setEstadolaboral(EstadoLaboral.JUBILADO);
        afiliado.setEstatus(Estatus.PENDIENTE);
        afiliado.setIomaid("123123");
        afiliado.setNombre("pepe");
        afiliado.setNumafiliado("1231");
        
        AfiliadoDao afil = new AfiliadoDao();
        afil.GuardaEntidad(afiliado);
        
        TramiteDao daotramite = new TramiteDao();
        TramiteDto tramite = new TramiteDto();
        
        Calendar calendar = Calendar.getInstance();

        
        tramite.setAfiliado(afiliado);
        tramite.setDescripcion("El agente se queja de olores en la escuela");
        tramite.setEstado(Estatus.ACTIVO);
        tramite.setFecha_apertura(calendar); 
        tramite.setFecha_resuelto(calendar);
        tramite.setResumen("Olores");
        daotramite.GuardaEntidad(tramite);
        
       
        
   
        
        
        UsuarioDto user = new UsuarioDto();
        user.setApellido("Orellana");
        user.setContrasena("elcapo23");
        user.setDocumento("35970613");
        user.setEmail("joseorellana_2@hotmail.com");
        user.setEstatus(Estatus.ACTIVO);
        user.setNombre("jose");
        user.setTipoDocumento(TipoDeDocumento.DNI);
        user.setTipousuario(TipoDeUsuario.ADMINISTRADOR);
      *** */ 
     //   UsuarioDao daoUsuario = new UsuarioDao();
        
 /////       daoUsuario.GuardaEntidad(user);
        
        
        //FrmTramite frame = new FrmTramite();
        
        //CtrlTramite control = new CtrlTramite(user,tramite, daotramite, frame);
        //control.inciar();
        
        
        /*
        FrmMenu frame = new FrmMenu();
        CtrlMenuHome menu = new CtrlMenuHome(frame,user);
        menu.inciar();
        */
        //FrmSeleccionAfiliad frm = new FrmSeleccionAfiliad();
        //CtrlSeleccionAfiliado control = new CtrlSeleccionAfiliado(frm);
       // control.inciar();

    
    
    
    
    
    }

//frmLogin frmlog= new frmLogin();
        //controlLogin loginCtl = new controlLogin(frmlog,daoUsuario );
        //loginCtl.iniciar();
        //frmlog.setVisible(true);
/*
        DireccionDto direccion1 = new DireccionDto("LA madrid", "2345", "san Miguel", "San Miguel", "1617");

        TelefonoDto telefono1 = new TelefonoDto("47408745", "Fijo");
        TelefonoDto telefono2 = new TelefonoDto("1536804970", "Movil");
        
        EscuelaDto escuela1 = new EscuelaDto();
        
        escuela1.setNombre("Escuela N° 5");

        escuela1.setDireccion(direccion1);
        escuela1.addTelefono(telefono1);
        escuela1.addTelefono(telefono2);
        escuela1.setGestion("Estatal");
        escuela1.setDipregep("1234566");

        try (Session sesion = HibernateUtil_old.getSessionFactory().openSession()) {
            sesion.beginTransaction();
            sesion.persist(escuela1);
            sesion.getTransaction().commit();
            // AfiliadoDto mod = new AfiliadoDto();
            // AfiliadoDao modC = new AfiliadoDao();
            // FrmAbmAfiliado frm = new FrmAbmAfiliado();
            // CtrlAfiliado ctrl = new CtrlAfiliado(mod,modC,frm);
            // ctrl.iniciar();
            //frm.setVisible(true);
            // TramiteDto mod = new TramiteDto();
            //TramiteDao modCons = new TramiteDao();
            //FrmTramite frmtr = new FrmTramite();
            //CtrlTramite ctrlTr = new CtrlTramite(mod,modCons,frmtr);
            //ctrlTr.inciar();
        }
    }
*/
}
