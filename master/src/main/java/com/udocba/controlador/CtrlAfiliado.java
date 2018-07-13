/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.controlador;


import com.udocba.enumerator.EstadoAfiliado;
import com.udocba.enumerator.EstadoCivil;
import com.udocba.enumerator.EstadoLaboral;
import com.udocba.enumerator.Estatus;
import com.udocba.enumerator.TipoDeDocumento;
import com.udocba.enumerator.TipoDeUsuario;
import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import com.udocba.modelo.entidades.AfiliadoDto;
import com.udocba.modelo.dao.AfiliadoDao;
import com.udocba.modelo.dao.DireccionAfiliadoDao;
import com.udocba.modelo.dao.EscuelaDao;
import com.udocba.modelo.dao.LocalidadDao;
import com.udocba.modelo.dao.ProvinciaDao;
import com.udocba.modelo.dao.ServicioDao;
import com.udocba.modelo.dao.UsuarioDao;
import com.udocba.modelo.entidades.DireccionAfiliadoDto;
import com.udocba.modelo.entidades.EscuelaDto;
import com.udocba.modelo.entidades.LocalidadDto;
import com.udocba.modelo.entidades.ProvinciaDto;
import com.udocba.modelo.entidades.ServicioDto;
import com.udocba.modelo.entidades.UsuarioDto;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import org.hibernate.HibernateException;
import vista.FrmAbmAfiliado;
import vista.FrmEscuela;
import vista.FrmMenu;
import vista.FrmUsuario;

/**
 *
 * @author userund
 */
public class CtrlAfiliado extends ControladorAbstracto<AfiliadoDto>{
    
    
    private AfiliadoDto afiliadoDto;
    private AfiliadoDao afiliadoDao;
    private FrmAbmAfiliado frmAfiliado;
    private ProvinciaDao provinciaDao;
    private LocalidadDao localidadDao;
    private String id;
    private EscuelaDao escuelaDao;
    private ServicioDto servicio;
    private DireccionAfiliadoDto direccion;
    private DireccionAfiliadoDao direccionDao;
    private ServicioDao servicioDao;
    private boolean vacio;
    private UsuarioDto user;

   //Se declara Contructor del ControladorAbstracto (recibira instancias de las clases Afilidado,ConsultasAfiliado y FormularioABM Afiliado)
    public CtrlAfiliado(AfiliadoDto dtoObj, AfiliadoDao daoObj, FrmAbmAfiliado frm, ProvinciaDao provinciaDao, LocalidadDao localidadDao,EscuelaDao escuelaDao, ServicioDto servicio, DireccionAfiliadoDto direccion, ServicioDao servicioDao, DireccionAfiliadoDao direccionDao, UsuarioDto user) {
            this.afiliadoDto = dtoObj;
            this.afiliadoDao =daoObj ;
            this.frmAfiliado = frm;
            this.provinciaDao = provinciaDao;
            this.localidadDao = localidadDao;
            this.escuelaDao = escuelaDao;
            this.servicio= servicio;
            this.afiliadoDto.setServicio(servicio);
            this.direccion = direccion;
            this.direccionDao = direccionDao;
            this.servicioDao = servicioDao;
            this.vacio = true;
            this.user = user;
            
            DefaultTableModel modelo = new DefaultTableModel();
            //Se inicializa a los metodos escuchadores de eventos de los objetos.
            this.frmAfiliado.btnAgregar.addActionListener(this);
            this.frmAfiliado.btnModificar.addActionListener(this);
            this.frmAfiliado.btnEliminar.addActionListener(this);
            this.frmAfiliado.jTableAfiliado.addMouseListener(this);
            this.frmAfiliado.cbProvincia.addItemListener(this);
            this.frmAfiliado.btnEscAgregar.addActionListener(this);
            this.frmAfiliado.jbAgregarEscuela.addActionListener(this);
            this.frmAfiliado.jLimpiar.addActionListener(this);
            this.frmAfiliado.jbEliminarEscuela.addActionListener(this);
            this.frmAfiliado.btnBuscar.addActionListener(this);
            this.frmAfiliado.jbActualizar.addActionListener(this);
            this.frmAfiliado.jbVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        
        
        if (ae.getSource() == frmAfiliado.jbVolver){
            
            FrmMenu frameMenu = new FrmMenu();
            CtrlMenuHome menu = new CtrlMenuHome(frameMenu,this.user);
            menu.inciar();
            this.frmAfiliado.dispose();
        
        
        
        
        
        
        
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        if (ae.getSource() == frmAfiliado.btnAgregar) {
            resetColores();
            
            if(validarCampos()){
                
                if(afiliadoDao.existeUsuaioConDni(frmAfiliado.txtAnDoc.getText()) || afiliadoDao.existeNumeroAfiliado(frmAfiliado.txtIdAfiliado.getText())){
                    
                    JOptionPane.showMessageDialog(null, "Ya existe un afiliado con este numero de documento o numero de afiliado");
                
                }
                else{
                   
                    
                    try{                
                        this.direccion.setCalle(frmAfiliado.txtAcalle.getText());
                        this.direccion.setCodigoPostal(Integer.parseInt(frmAfiliado.txtAcPostal.getText()));
                        this.direccion.setLocalidad((LocalidadDto)frmAfiliado.cbPartido.getSelectedItem());
                        this.direccion.setNumero(Integer.parseInt(frmAfiliado.txtAnCalle.getText()));
                        this.servicio.setCargo(frmAfiliado.txtCargo.getText());
                        this.servicio.setModalidad(frmAfiliado.txtSmodalidad.getText());
                        this.servicio.setNivel(frmAfiliado.cmbEnivel.getSelectedItem().toString());
                        this.servicio.setSecuencia(frmAfiliado.txtEsecuencia.getText());
                        Object escuelaObject = frmAfiliado.cbEscuelas.getSelectedItem();
                        EscuelaDto escuela = (EscuelaDto)escuelaObject;
                        this.servicio.addEscuela(escuela); 
                        this.afiliadoDto.setApellido(this.frmAfiliado.txtAapellido.getText());
                        this.afiliadoDto.setCelular(this.frmAfiliado.txtAcelular.getText());
                        this.afiliadoDto.setDireccion(direccion);
                        this.afiliadoDto.setDocumento(this.frmAfiliado.txtAnDoc.getText());
                        this.afiliadoDto.setEmail(this.frmAfiliado.txtAemail.getText());                  
                        this.afiliadoDto.setEstadocivil((EstadoCivil)this.frmAfiliado.cmbAeCivil.getSelectedItem()); 
                        this.afiliadoDto.setEstadolaboral((EstadoLaboral)this.frmAfiliado.cmbEstLaboral.getSelectedItem());                  
                        this.afiliadoDto.setEstatus((EstadoAfiliado)frmAfiliado.comboBoxEstado.getSelectedItem());  
                        this.afiliadoDto.setFechanac(this.frmAfiliado.jDateChooser.getDate());
                        this.afiliadoDto.setIomaid(this.frmAfiliado.txtAidIoma.getText());
                        this.afiliadoDto.setNombre(this.frmAfiliado.txtAnombre.getText());
                        this.afiliadoDto.setNumafiliado(this.frmAfiliado.txtIdAfiliado.getText());
                        this.afiliadoDto.setServicio(servicio);
                        this.afiliadoDto.setTelefono(this.frmAfiliado.txtAtelef.getText());
                        this.afiliadoDto.setFechanac(this.frmAfiliado.jDateChooser.getDate());
                        this.direccionDao.GuardaEntidad(direccion);
                        this.servicioDao.GuardaEntidad(servicio);
                        this.afiliadoDao.GuardaEntidad(this.afiliadoDto);
                        JOptionPane.showMessageDialog(null, "El usuario se ha registrado correctamente");
                        this.direccion= new DireccionAfiliadoDto();
                        this.afiliadoDto = new AfiliadoDto();
                        this.servicio = new ServicioDto();
                    
                        cargarJtable(afiliadoDao.FilterUsuarioByState());
                    
                    }catch(HibernateException e ){
                    
                        JOptionPane.showMessageDialog(null, e.toString());
                    
                    }
                }
            }
        }
        

        if (ae.getSource() == frmAfiliado.btnModificar) {
            
            resetColores();
            
            if(validarCampos()){
                
                if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar al afiliado "+ this.afiliadoDto.getNombre(), "Estas Seguro?", 0) ==0){
                
                
                

                    try{  
                        
                        
                        this.servicio = this.afiliadoDto.getServicio();
                        this.direccion = this.afiliadoDto.getDireccion();
                        
                        this.direccion.setCalle(frmAfiliado.txtAcalle.getText());
                        this.direccion.setCodigoPostal(Integer.parseInt(frmAfiliado.txtAcPostal.getText()));
                        this.direccion.setLocalidad((LocalidadDto)frmAfiliado.cbPartido.getSelectedItem());
                        this.direccion.setNumero(Integer.parseInt(frmAfiliado.txtAnCalle.getText()));
                        this.servicio.setCargo(frmAfiliado.txtCargo.getText());
                        this.servicio.setModalidad(frmAfiliado.txtSmodalidad.getText());
                        this.servicio.setNivel(frmAfiliado.cmbEnivel.getSelectedItem().toString());
                        this.servicio.setSecuencia(frmAfiliado.txtEsecuencia.getText());
                        this.direccionDao.updateEntidad(direccion);
                        this.servicioDao.updateEntidad(servicio);
                        
                       
                        this.afiliadoDto.setApellido(this.frmAfiliado.txtAapellido.getText());
                        this.afiliadoDto.setCelular(this.frmAfiliado.txtAcelular.getText());
                        this.afiliadoDto.setDireccion(direccion);
                        this.afiliadoDto.setDocumento(this.frmAfiliado.txtAnDoc.getText());
                        this.afiliadoDto.setEmail(this.frmAfiliado.txtAemail.getText());                  
                        this.afiliadoDto.setEstadocivil((EstadoCivil)this.frmAfiliado.cmbAeCivil.getSelectedItem()); 
                        this.afiliadoDto.setEstadolaboral((EstadoLaboral)this.frmAfiliado.cmbEstLaboral.getSelectedItem());                  
                        this.afiliadoDto.setEstatus((EstadoAfiliado)frmAfiliado.comboBoxEstado.getSelectedItem());  
                        this.afiliadoDto.setIomaid(this.frmAfiliado.txtAidIoma.getText());
                        this.afiliadoDto.setNombre(this.frmAfiliado.txtAnombre.getText());
                        this.afiliadoDto.setNumafiliado(this.frmAfiliado.txtIdAfiliado.getText());
                        this.afiliadoDto.setServicio(servicio);
                        this.afiliadoDto.setTelefono(this.frmAfiliado.txtAtelef.getText());
                        this.afiliadoDto.setFechanac(this.frmAfiliado.jDateChooser.getDate());
                        
                        this.afiliadoDao.updateEntidad(afiliadoDto);
                        JOptionPane.showMessageDialog(null, "El usuario se ha modificado correctamente");
                        
                    
                        cargarJtable(afiliadoDao.FilterUsuarioByState());
                    
                    }catch(HibernateException e ){
                    
                        JOptionPane.showMessageDialog(null, e.toString());
                    
                    }
                }
                }
            }

            
        
        
        if (ae.getSource() == frmAfiliado.btnEliminar){
            
            if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar al afiliado "+ this.afiliadoDto.getNombre(), "Estas Seguro?", 0) ==0){
              
                afiliadoDao.eliminar(this.afiliadoDto);
                cargarJtable(afiliadoDao.obtenListaEntidad(AfiliadoDto.class));
                limpiarTxt();
            
            }
          
        }
        
        
        
        if(ae.getSource()== frmAfiliado.jLimpiar){
        
            limpiarTxt();
            vaciarTablaEscuela();
            this.afiliadoDto = new AfiliadoDto();
            this.servicio = new ServicioDto();
            this.afiliadoDto.setServicio(this.servicio);
            this.vacio = true;
        
        
        }
        
        
        if (ae.getSource() == frmAfiliado.jbAgregarEscuela){
            
            if(vacio){
                this.afiliadoDto.getServicio().addEscuela((EscuelaDto)this.frmAfiliado.cbEscuelas.getSelectedItem());
                cargarTablaEscuelas(this.afiliadoDto.getServicio().getEscuelas());
            
            
            }
            else{
            this.servicio = this.afiliadoDto.getServicio();
            this.servicio.addEscuela((EscuelaDto)this.frmAfiliado.cbEscuelas.getSelectedItem());
            this.servicioDao.updateEntidad(this.servicio);
            this.afiliadoDto.setServicio(servicio);
            afiliadoDao.updateEntidad(afiliadoDto);
            
            

            
            cargarTablaEscuelas(this.afiliadoDto.getServicio().getEscuelas());
            }
        
        }
        
        
        if(ae.getSource() == frmAfiliado.jbEliminarEscuela){
            
            if(vacio){
            
                this.afiliadoDto.getServicio().removeEscuela((EscuelaDto)this.frmAfiliado.cbEscuelas.getSelectedItem());
                cargarTablaEscuelas(this.afiliadoDto.getServicio().getEscuelas());
            
            }
            else{
                
                int fila= this.frmAfiliado.jTableEscuelas.getSelectedRow();
                
                if(fila>=0){
                
                try{
                    
                    id = this.frmAfiliado.jTableEscuelas.getValueAt(fila, 0).toString();
                    System.out.println(id);
                    EscuelaDto escuela = escuelaDao.escuelaPorDipregrep(id);
                 
                    this.servicio = this.afiliadoDto.getServicio();
                    this.servicio.removeEscuela(escuela);
                    this.servicioDao.updateEntidad(this.servicio);
                    this.afiliadoDto.setServicio(this.servicio);
                    this.afiliadoDao.updateEntidad(this.afiliadoDto);
                    
                    cargarTablaEscuelas(this.afiliadoDto.getServicio().getEscuelas());
        
        
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener la escuela seleccionada");
        }
            
            }
        
                else{
                
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un campo");
                
                }
        }
        }
        
        
        if (ae.getSource() == frmAfiliado.btnEscAgregar) {
            
            FrmEscuela frameEscuela = new FrmEscuela();
            EscuelaDto escuelaDto = new EscuelaDto();
            EscuelaDao escueladao = new EscuelaDao();
            
            CtrlEscuela controlador = new CtrlEscuela(escuelaDto, escueladao, frameEscuela);
            controlador.inciar();
            
            
            
            
          
        }
        
        
        
        if (ae.getSource()== frmAfiliado.btnBuscar){
            
            if(frmAfiliado.txtBuscar.getText().isEmpty()){
            
                 JOptionPane.showMessageDialog(null, "Debe ingresar un DNI para la busqueda");
            
            
            }
            
            else{
            
            
            try{
                
               List<AfiliadoDto> lista = afiliadoDao.AfiliadoPorNumeroLista(this.frmAfiliado.txtBuscar.getText());
               
               if (!lista.isEmpty()){
               cargarJtable(lista);
               }
               
               else{
                   
                   JOptionPane.showMessageDialog(null, "No existe usuario con el DNI indicado");
               
               }
            
            }catch(Exception e){
                
                JOptionPane.showMessageDialog(null, "DNI incorrecto");
            }
            }
        }
        
        
        
        
        
        if (ae.getSource() == frmAfiliado.jbActualizar){
        
            cargarJtable(afiliadoDao.FilterUsuarioByState());
        
        }
    }
    
    
    public void llenarCbProvincia(){
    
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        List<ProvinciaDto> listaProvincias = provinciaDao.obtenListaEntidad(ProvinciaDto.class);
        for (ProvinciaDto provincia :  listaProvincias) {
        
            cb.addElement(provincia);
        
        
        }
        
        this.frmAfiliado.cbProvincia.setModel(cb);
        
        }
    
    
    public void llenarCbLocalidad(long idProvincia){
        
        
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        List<LocalidadDto> listaLocalidades = localidadDao.obtenerLocalidadesPorProvincia(idProvincia);
        for (LocalidadDto localidad :  listaLocalidades) {
        
            cb.addElement(localidad);
        
        
        }
        
        this.frmAfiliado.cbPartido.setModel(cb);
        
        
    
    
    }
    
    
    public void llenarCbEscuelas(){
    
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        List<EscuelaDto> listaEscuelas = escuelaDao.obtenListaEntidad(EscuelaDto.class);
        for (EscuelaDto escuela :  listaEscuelas) {
        
            cb.addElement(escuela);
        
        
        }
        
        this.frmAfiliado.cbEscuelas.setModel(cb);
        
        }
    
    
    
    public void validarCbLocalidad(){
        
        this.frmAfiliado.cbPartido.setEnabled(false);
        
        if(frmAfiliado.cbProvincia.getSelectedIndex() != -1){
        
            
            this.frmAfiliado.cbPartido.setEnabled(true);
            Object objeto = this.frmAfiliado.cbProvincia.getSelectedItem();
            ProvinciaDto provincia = (ProvinciaDto) objeto; 
            llenarCbLocalidad(provincia.getId());
            
        
        
        }
    
    
    }
    
    

    @Override
    public void mouseClicked(MouseEvent me) {
        
        
        
        

        
    }
                                             
    @Override
    public void mousePressed(MouseEvent me) {
        
        
        if(me.getClickCount()== 2){
            frmAfiliado.jTableAfiliado.editingCanceled(null);
            JOptionPane.showMessageDialog(null,"Para editar seleccione un registro, edite los campos de la izquierda"
                    + " y luego precione Modificar.");
           
        }
        
        
        
        int fila= this.frmAfiliado.jTableAfiliado.getSelectedRow();
        if(fila>=0){
        try{
            
            id = this.frmAfiliado.jTableAfiliado.getValueAt(fila, 1).toString();
            this.afiliadoDto = this.afiliadoDao.AfiliadoPorNumero(id);
        
        
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al obtener el afiliado seleccionado");
        }
        
        
        
        frmAfiliado.txtAapellido.setText(afiliadoDto.getApellido());
        frmAfiliado.txtAcPostal.setText(String.valueOf(afiliadoDto.getDireccion().getCodigoPostal()));
        frmAfiliado.txtAcalle.setText(afiliadoDto.getDireccion().getCalle());
        frmAfiliado.txtAcelular.setText(afiliadoDto.getCelular());
        frmAfiliado.txtAemail.setText(afiliadoDto.getEmail());
        frmAfiliado.txtAidIoma.setText(afiliadoDto.getIomaid());
        frmAfiliado.txtAnCalle.setText(String.valueOf(afiliadoDto.getDireccion().getNumero()));
        frmAfiliado.txtAnDoc.setText(afiliadoDto.getDocumento());
        frmAfiliado.txtAnombre.setText(afiliadoDto.getNombre());
        frmAfiliado.txtAtelef.setText(afiliadoDto.getTelefono());
        frmAfiliado.txtCargo.setText(afiliadoDto.getServicio().getCargo());
        frmAfiliado.txtEsecuencia.setText(afiliadoDto.getServicio().getSecuencia());
        frmAfiliado.txtIdAfiliado.setText(afiliadoDto.getNumafiliado());
        frmAfiliado.txtSmodalidad.setText(afiliadoDto.getServicio().getModalidad());
        frmAfiliado.jDateChooser.setDate(afiliadoDto.getFechanac());
        frmAfiliado.cmbAeCivil.setSelectedItem(afiliadoDto.getEstadocivil());
        frmAfiliado.cmbEnivel.setSelectedItem(afiliadoDto.getServicio().getNivel());
        frmAfiliado.cmbEstLaboral.setSelectedItem(afiliadoDto.getEstadolaboral());
        cargarTablaEscuelas(afiliadoDto.getServicio().getEscuelas());
        this.frmAfiliado.btnEliminar.setEnabled(true);
        this.frmAfiliado.btnModificar.setEnabled(true);
        this.frmAfiliado.jbVolver.addActionListener(this);
        vacio = false;

    }
        
        
        
        
        
        
        
       
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
     public void limpiarTxt(FrmAbmAfiliado f) {
       
        for (int i = 0; f.getComponents().length > i; i++) {
            //Limpia todos los JTextField de un JPanel
            if (f.getComponents()[i] instanceof JTextField) {
                ((JTextComponent) f.getComponents()[i]).setText("");
            }

        }
    }
     
     
     
   
    @Override
    public void seleccionarRegistro() {
        

        
    }
    
    
        
         

    @Override
    public void inciar() {
        
        frmAfiliado.setTitle("ABM AFILIADO");
        frmAfiliado.setLocationRelativeTo(null);
        frmAfiliado.setResizable(false);
        frmAfiliado.setVisible(true);
        llenarCbProvincia();
        llenarCbLocalidad(1);
        llenarCbEscuelas();
        llenarComboEstadoLaboral();
        llenarComboEstadoCivil();
        llenarComboEstado();
        cargarJtable(afiliadoDao.FilterUsuarioByState());
        this.frmAfiliado.btnEliminar.setEnabled(false);
        this.frmAfiliado.btnModificar.setEnabled(false);
    }

    @Override
    public void limpiarTxt() {

        this.frmAfiliado.txtAapellido.setText("");
        this.frmAfiliado.txtAcPostal.setText("");
        this.frmAfiliado.txtAcalle.setText("");
        this.frmAfiliado.txtAcelular.setText("");
        this.frmAfiliado.txtAemail.setText("");
        this.frmAfiliado.txtAidIoma.setText("");
        this.frmAfiliado.txtAnCalle.setText("");
        this.frmAfiliado.txtAnDoc.setText("");
        this.frmAfiliado.txtAnombre.setText("");
        this.frmAfiliado.txtAtelef.setText("");
        this.frmAfiliado.txtBuscar.setText("");
        this.frmAfiliado.txtCargo.setText("");
        this.frmAfiliado.txtEsecuencia.setText("");
        this.frmAfiliado.txtIdAfiliado.setText("");
        this.frmAfiliado.txtSmodalidad.setText("");
        ((JTextField)this.frmAfiliado.jDateChooser.getDateEditor().getUiComponent()).setText("");
        
        
    }

    
    public void vaciarTablaEscuela(){
               
        String columnas[]= {"Nro. dipregep", "Escuela"};
        DefaultTableModel tModel = new DefaultTableModel(null,columnas);
     //   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        frmAfiliado.jTableEscuelas.setModel(tModel);
  
    }
    
    
    
    @Override
    public void cargarJtable(List<AfiliadoDto> lista) {
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        String columnas[]= {"Numero de afiliado","Numero de documento","Nombres","Apellido"};
        DefaultTableModel tModel = new DefaultTableModel(null,columnas);
     //   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (AfiliadoDto afiliado : lista) {
            String registro[]= new String[4];
            registro[0]= afiliado.getNumafiliado();
            registro[1]=afiliado.getDocumento();
            registro[2]=afiliado.getNombre();
            registro[3]=afiliado.getApellido();
            
                        
            tModel.addRow(registro);
        }
        frmAfiliado.jTableAfiliado.setModel(tModel);
        frmAfiliado.jTableAfiliado.getColumnModel().getColumn(0).setCellRenderer(tcr);
        frmAfiliado.jTableAfiliado.getColumnModel().getColumn(1).setCellRenderer(tcr);
        frmAfiliado.jTableAfiliado.getColumnModel().getColumn(2).setCellRenderer(tcr);
        frmAfiliado.jTableAfiliado.getColumnModel().getColumn(3).setCellRenderer(tcr);
        
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    
    
    public void cargarTablaEscuelas(List<EscuelaDto> lista){
        
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        String columnas[]= {"Nro. dipregep", "Escuela"};
        DefaultTableModel tModel = new DefaultTableModel(null,columnas);
        
        
        for (EscuelaDto escuela : lista) {
            String registro[]= new String[2];
            registro[0]= escuela.getDipregep();
            registro[1]= escuela.getNombre();
            
                        
            tModel.addRow(registro);
        }
        frmAfiliado.jTableEscuelas.setModel(tModel);
        frmAfiliado.jTableAfiliado.getColumnModel().getColumn(0).setCellRenderer(tcr);
        frmAfiliado.jTableAfiliado.getColumnModel().getColumn(1).setCellRenderer(tcr);
        
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    
    }

    
    
    
    @Override
    public void llenarCombo(List<AfiliadoDto> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void llenarFormulario(AfiliadoDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void llenarObjeto(AfiliadoDto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
           
    

   
    
    
    
        public boolean validarCampos(){
        
        String fecha             = "S";
        String apellido          = "S";
        String nombre            = "S";
        String documento         = "S";
        String ioma              = "S";
        String fechaNacimiento   = "S";
        String telefonoFijo      = "S";
        String telefonoCelular   = "S";
        String email             = "S";
        String calle             = "S";
        String numeroCalle       = "S";
        String codigoPostal      = "S";
        String modalidad         = "S";
        String secuencia         = "S";
        
        
        
        if(frmAfiliado.txtAapellido.getText().isEmpty() || frmAfiliado.txtAapellido == null){
            apellido = "N";
            frmAfiliado.txtAapellido.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        
        
        }
        
        else if(frmAfiliado.txtAnombre.getText().isEmpty() || frmAfiliado.txtAnombre == null ){
            nombre = "N";
            frmAfiliado.txtAnombre.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
        
        
        else if(!isNumber(this.frmAfiliado.txtAnDoc.getText())){
            documento = "N";
            frmAfiliado.txtAnDoc.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
        
        
        else if(!isNumber(this.frmAfiliado.txtAidIoma.getText())){
            ioma = "N";
            frmAfiliado.txtAidIoma.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
        else if(frmAfiliado.txtAtelef.getText().isEmpty() || frmAfiliado.txtAtelef == null){
            telefonoFijo = "N";
            frmAfiliado.txtAtelef.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
        else if(!isNumber(this.frmAfiliado.txtAcelular.getText())){
            telefonoCelular = "N";
            frmAfiliado.txtAcelular.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
         }
       
        else if(frmAfiliado.txtAemail.getText().isEmpty() || !isEmail(this.frmAfiliado.txtAemail.getText())){
            email = "N";
            frmAfiliado.txtAemail.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            
         }
        else if(frmAfiliado.txtAcalle.getText().isEmpty() || frmAfiliado.txtAcalle == null){
            calle = "N";
            frmAfiliado.txtAcalle.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            
         }
        else if(!isNumber(frmAfiliado.txtAnCalle.getText())){
            numeroCalle = "N";
            frmAfiliado.txtAnCalle.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            
         }
        else if(!isNumber(frmAfiliado.txtAcPostal.getText())){
            codigoPostal = "N";
            frmAfiliado.txtAcPostal.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            
         }
        else if(frmAfiliado.txtSmodalidad.getText().isEmpty() || frmAfiliado.txtSmodalidad == null){
            modalidad = "N";
            frmAfiliado.txtSmodalidad.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            
         }
        else if(frmAfiliado.txtEsecuencia.getText().isEmpty() || frmAfiliado.txtEsecuencia == null){
            secuencia = "N";
            frmAfiliado.txtEsecuencia.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            
         }
        else if(frmAfiliado.txtCargo.getText().isEmpty() || frmAfiliado.txtCargo == null){
            secuencia = "N";
            frmAfiliado.txtCargo.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            
         }
        
        else if(frmAfiliado.jDateChooser.getDate() == null){
            fecha = "N";
            frmAfiliado.jDateChooser.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        }
        
        return !(apellido.equals("N")|| nombre.equals("N") || documento.equals("N") || ioma.equals("N") || fechaNacimiento.equals("N") ||
                 telefonoFijo.equals("N") || telefonoCelular.equals("N") || email.equals("N") || calle.equals("N") || numeroCalle.equals("N")
                 || codigoPostal.equals("N") || modalidad.equals("N") || secuencia.equals("N") || fecha.equals("N") );
                 
    
    }
        
        
          public void resetColores(){
              
          
            frmAfiliado.txtAnombre.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAapellido.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAcPostal.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAcalle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAcelular.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAemail.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAidIoma.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAnCalle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAnDoc.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtAtelef.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtCargo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtEsecuencia.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.txtSmodalidad.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            frmAfiliado.jDateChooser.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
           
    }
          
          
    public boolean isNumber(String numero){
        
        
        if (!numero.isEmpty()){
        
            try{
            
                Integer.parseInt(numero);
                
                return true;
            
            }catch(Exception e){                
             
                return false;
            }
            
        }else {
                    
                return false;
        
        }
        
    }
    
      
      
      
    public boolean isEmail(String mail) {
 
        
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        
        Matcher mather = pattern.matcher(mail);
 
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }
 

      
      
      
      
    
    
    
    
    
    

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        Object objeto = this.frmAfiliado.cbProvincia.getSelectedItem();
        ProvinciaDto provincia = (ProvinciaDto) objeto; 
        llenarCbLocalidad(provincia.getId());
        
    }
    
    
    
    
    
        public void llenarComboEstadoCivil(){
       try{ 
           
            this.frmAfiliado.cmbAeCivil.setModel(new DefaultComboBoxModel(EstadoCivil.values()));

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Problema al cargar la lista de estado civil");
            }
    }
    
     public void llenarComboEstadoLaboral(){
  
       try{  
           
           this.frmAfiliado.cmbEstLaboral.setModel(new DefaultComboBoxModel(EstadoLaboral.values()));
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Problema al cargar la lista de estados laborales");
       }
    }
     
     
     public void llenarComboEstado(){
         
         try{
         
             this.frmAfiliado.comboBoxEstado.setModel(new DefaultComboBoxModel(EstadoAfiliado.values()));
         
         }catch(Exception e){
         
         
             JOptionPane.showMessageDialog(null, "Problema al cargar la lista de estados");
         
         }
         
     
     
     
     }
     
     
     

    
    
    
    
    
}
    
    
    
    

   
           
    

   
    
    

