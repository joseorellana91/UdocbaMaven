/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.controlador;

import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.udocba.modelo.dao.AfiliadoDao;
import com.udocba.modelo.dao.DireccionAfiliadoDao;
import com.udocba.modelo.dao.EscuelaDao;
import com.udocba.modelo.dao.GrupoDao;
import com.udocba.modelo.dao.LocalidadDao;
import com.udocba.modelo.dao.ProvinciaDao;
import com.udocba.modelo.dao.ServicioDao;
import com.udocba.modelo.entidades.AfiliadoDto;
import com.udocba.modelo.dao.TramiteDao;
import com.udocba.modelo.dao.UsuarioDao;
import com.udocba.modelo.entidades.ClasificacionDto;
import com.udocba.modelo.entidades.DireccionAfiliadoDto;
import com.udocba.modelo.entidades.DireccionEscuelaDto;
import com.udocba.modelo.entidades.GrupoDto;
import com.udocba.modelo.entidades.SedeDto;
import com.udocba.modelo.entidades.ServicioDto;
import com.udocba.modelo.entidades.SubClasificaDto;
import com.udocba.modelo.entidades.TramiteDto;
import com.udocba.modelo.entidades.UsuarioDto;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


import vista.FrmAbmAfiliado;
import vista.FrmMenu;
import vista.FrmTramite;
import vista.FrmUsuario;
import vista.frmLogin;

/**
 *
 * @author userund
 */
public class CtrlMenuHome extends ControladorAbstracto<TramiteDto> {

    private TramiteDto dtotramite;
    private TramiteDao daoTramite;
    private FrmMenu menufrm;
    private UsuarioDto dtousuario;
    private UsuarioDao daousuario;
    private FrmTramite tramitefrm;
    private GrupoDto dtoGrupo;
    private GrupoDao daoGrupo;
    
    

    public CtrlMenuHome() {
    }

    public CtrlMenuHome(FrmMenu menufrm, UsuarioDto dtousuario) {
        this.daoTramite = new TramiteDao();
        this.dtotramite = dtotramite;
        this.daoTramite = daoTramite;
        this.menufrm = menufrm;
        this.dtousuario = dtousuario;
        
        // Inicializo Listeners de lo objetos
        this.menufrm.jMenuAfiliado.addActionListener(this);
        this.menufrm.jMenuUsuario.addActionListener(this);
        this.menufrm.jMenuReportes.addActionListener(this);
        this.menufrm.jMenuSalir.addActionListener(this);
        this.menufrm.jMenuAyuda.addActionListener(this);
        this.menufrm.btnCrear.addActionListener(this);
        this.menufrm.btnBuscar.addActionListener(this);
        this.menufrm.jTableTramite.addMouseListener(this);

    }

    @Override
    public void inciar() {
       
        List <TramiteDto> lista = daoTramite.obtenListaEntidad(TramiteDto.class);
        cargarJtable(lista);
        menufrm.setTitle("Bandeja de entrada");
        menufrm.setLocationRelativeTo(null);
        menufrm.setVisible(true);
        
        
        if (dtousuario.getTipousuario().toString().equals("ADMINISTRADOR")) {
            
            
        
            
           
        } else {
            menufrm.jMenuUsuario.setVisible(false);
            menufrm.jMenuReportes.setVisible(false);

        }
    }

    @Override
    public void limpiarTxt() {
        for (int x = 0; x < menufrm.jPnlTramite.getComponentCount(); x++) {
            if (menufrm.jPnlTramite.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) menufrm.jPnlTramite.getComponent(x);
                textField.setText("");
            }
        }
    }

    @Override
    public void seleccionarRegistro() {
        DefaultTableModel tModel = (DefaultTableModel) menufrm.jTableTramite.getModel();
        int filaseleccionada;
        int id;
        try {

            filaseleccionada = menufrm.jTableTramite.getSelectedRow();

            if (filaseleccionada == -1) {

                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");

            } else {

                id = ((int) tModel.getValueAt(filaseleccionada, 0));

                dtotramite = daoTramite.getEntidad(TramiteDto.class, id);

                CtrlTramite ctrlTramite = new CtrlTramite(dtousuario,dtotramite, daoTramite, tramitefrm = new FrmTramite());
                ctrlTramite.llenarObjeto(dtotramite);
                ctrlTramite.inciar();

            }

        } catch (HeadlessException ex) {

            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);

        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       System.out.println("Action performed");
        if (ae.getSource() == menufrm.jMenuAfiliado) {
            
            DireccionEscuelaDto direccionEsc = new DireccionEscuelaDto();
        
            EscuelaDao escuelaDao = new EscuelaDao();
   
            AfiliadoDto afiliadoDto = new AfiliadoDto();
  
            AfiliadoDao afiliadoDao = new AfiliadoDao();
   
            ProvinciaDao provinciaDao = new ProvinciaDao();
  
            LocalidadDao localidadDao = new LocalidadDao();
  
            FrmAbmAfiliado frame = new FrmAbmAfiliado();
        
            ServicioDto servicio = new ServicioDto();
        
            DireccionAfiliadoDto direccion = new DireccionAfiliadoDto();
        
            ServicioDao servicioDao = new ServicioDao();
        
            DireccionAfiliadoDao direccionDao = new DireccionAfiliadoDao();
        
            CtrlAfiliado controlador = new CtrlAfiliado(afiliadoDto, afiliadoDao, frame , provinciaDao, localidadDao, escuelaDao, servicio, direccion, servicioDao, direccionDao,this.dtousuario);
             
            
            
            controlador.inciar();
            
            this.menufrm.dispose();
        }

        if (ae.getSource() == menufrm.jMenuUsuario) {
            
            
            menufrm.dispose();
           // menufrm.setVisible(false);
            FrmUsuario frmusuario = new FrmUsuario();
            // envio como parametro el dto usuario, no un new dtousuario
            CtrlUsuario ctrlusuario = new CtrlUsuario(frmusuario, dtousuario, daousuario = new UsuarioDao());
            ctrlusuario.inciar();
            
           
            
        }

        if (ae.getSource() == menufrm.jMenuReportes) {
            //falta completar
            
          
        }
        if(ae.getSource() == menufrm.jMenuSalir){
            System.out.println("preciono salir");
            int resp;
            resp = JOptionPane.showConfirmDialog(null,"¿Desea cerrar la sesion actual? ", "Cerrar Sesion",JOptionPane.OK_CANCEL_OPTION);
            if(resp == JOptionPane.OK_OPTION){  
                menufrm.dispose();
                UsuarioDao daoUsuario = new UsuarioDao();
                frmLogin frmlog= new frmLogin();
                controlLogin loginCtl = new controlLogin(frmlog,daoUsuario );
                loginCtl.iniciar();
                frmlog.setVisible(true);
            }
            
        }

        if (ae.getSource() == menufrm.btnCrear) {

            tramitefrm = new FrmTramite();
            tramitefrm.txtNtramite.setText(daoTramite.ultimoRegistro());
            tramitefrm.txtNtramite.setEditable(false);
            tramitefrm.JLabelEstado.setVisible(false);
            tramitefrm.jLabelEst2.setVisible(false);
            tramitefrm.JLabelEstado.setText(this.dtousuario.getEstatus().toString());
            CtrlTramite controladorTramite = new CtrlTramite(this.dtousuario, this.dtotramite,this.daoTramite, tramitefrm);
            controladorTramite.inciar();
        }
        
        
        if (ae.getSource() == menufrm.btnBuscar) {

            if (menufrm.txtTramiteId.getText().isEmpty() && menufrm.txtNafiliado.getText().isEmpty() && menufrm.txtDni.getText().isEmpty()) {

            //    cargarJtable(daoTramite.obtenListaObjetosWhere(dtousuario));

            } else if (menufrm.txtNafiliado.getText().isEmpty() && menufrm.txtDni.getText().isEmpty() && (!menufrm.txtTramiteId.getText().isEmpty())) {

            //    cargarJtable(daoTramite.obtenListaObjetosWhere(menufrm.txtTramiteId.getText()));

            } else if (menufrm.txtTramiteId.getText().isEmpty() && menufrm.txtDni.getText().isEmpty() && (!menufrm.txtNafiliado.getText().isEmpty())) {
           //     cargarJtable(daoTramite.obtenListaObjetosWhere(menufrm.txtNafiliado.getText()));
                
            } else if (menufrm.txtTramiteId.getText().isEmpty() && menufrm.txtNafiliado.getText().isEmpty() && (!menufrm.txtDni.getText().isEmpty())) {
            //       cargarJtable(daoTramite.obtenListaObjetosWhere(menufrm.txtDni.getText()));
                   
            } else {
                
                JOptionPane.showMessageDialog(null, "Se ha selecionado mas un campo de Busqueda,favor de completar solo uno y pulsar Buscar");
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
         if (me.getSource()== menufrm.jTableTramite) {
             
            JOptionPane.showMessageDialog(null, "funciona con doble click");
            
        }
    }
    
    @Override
    public void cargarJtable(List<TramiteDto> lista) {
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        String columnas[]= {"Tramite_Id", "N_Afiliado","Dni_Afiliado","Apellido","Nombre", "Estado" , "Fecha_Inicio"};
        DefaultTableModel tModel = new DefaultTableModel(null,columnas);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (TramiteDto tramiteDto : lista) {
            String registro[]= new String[7];
            registro[0]= String.valueOf(tramiteDto.getId());
            registro[1]=tramiteDto.getAfiliado().getNumafiliado();
            registro[2]=tramiteDto.getAfiliado().getDocumento();
            registro[3]=tramiteDto.getAfiliado().getApellido();
            registro[4]=tramiteDto.getAfiliado().getNombre();
            registro[5]=tramiteDto.getEstado().toString();
            registro[6]=dateFormat.format(tramiteDto.getFecha_apertura().getTime());
             
            tModel.addRow(registro);
        }
        menufrm.jTableTramite.setModel(tModel);
        menufrm.jTableTramite.getColumnModel().getColumn(0).setCellRenderer(tcr);
        menufrm.jTableTramite.getColumnModel().getColumn(1).setCellRenderer(tcr);
        menufrm.jTableTramite.getColumnModel().getColumn(2).setCellRenderer(tcr);
        menufrm.jTableTramite.getColumnModel().getColumn(3).setCellRenderer(tcr);
        menufrm.jTableTramite.getColumnModel().getColumn(4).setCellRenderer(tcr);
        menufrm.jTableTramite.getColumnModel().getColumn(5).setCellRenderer(tcr);
        menufrm.jTableTramite.getColumnModel().getColumn(6).setCellRenderer(tcr);
        
        
    }
    

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    

    @Override
    public void llenarCombo(List lista) {
     //no utilizado
    }

    @Override
    public void llenarObjeto(TramiteDto o) {
        //no utilizado
    }

    @Override
    public void llenarFormulario(TramiteDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
