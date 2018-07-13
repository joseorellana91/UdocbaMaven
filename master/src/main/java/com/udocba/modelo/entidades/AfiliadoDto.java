/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.udocba.enumerator.EstadoAfiliado;
import com.udocba.enumerator.EstadoCivil;
import com.udocba.enumerator.EstadoLaboral;
import com.udocba.enumerator.Estatus;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Seba
 */

// Si no se define una anotacion @Table con el atributo name= "nombre-de-la-tabla" hibernate va a buscar en la base
// de datos una tabla con el nombre de la clase, AfiliadoDto. Si esta setteado en create va a crear una tabla AfiliadoDto.
// Tambien hay que definir cada columna con @Column(name=" ") de lo contrario, en validate hibernate va a buscar columnas
// con los nombres de cada variable que aparezca dentro de la clase, dentro de la tabla. En Create las va a crear. 
@Entity
@Table(name="afiliado")
public class AfiliadoDto implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private long id;
    
    @Getter
    @Setter
    @Column(name = "nombre")
    private String nombre;
    
    @Getter
    @Setter
    @Column(name = "apellido")
    private String apellido;
    
    @Getter
    @Setter
    @Column(name = "numeroAfiliado")
    private String numafiliado;
    
    @Getter
    @Setter
    @Column (name = "celular")
    private String celular;
    
    @Getter
    @Setter
    @Column (name = "telefono")
    private String telefono;
    
    @Getter
    @Setter
    @Column(name = "numeroDocumento")
    private String documento;
    
    @Getter
    @Setter
    @Column(name = "ioma_id")
    private String iomaid;
    
    @Getter
    @Setter
    @Enumerated (EnumType.STRING)
    @Column(name ="estadoCivil")
    private EstadoCivil estadocivil;
    
    @Getter
    @Setter
    @Enumerated (EnumType.STRING)
    @Column(name ="estadoLaboral")
    private EstadoLaboral estadolaboral;
    
    @Getter
    @Setter
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanac;
    
    @Getter
    @Setter
    @Enumerated (EnumType.STRING)
    @Column(name = "estatus")
    private EstadoAfiliado estatus = EstadoAfiliado.ACTIVO;
    
    
    
    @Getter
    @Setter
    @Column(name = "email")
    private String email;
    
    
    @Getter
    @Setter
    @ManyToOne
    private ServicioDto servicio;
    
    
    @Getter
    @Setter
    @ManyToOne
    private DireccionAfiliadoDto direccion;
    
    
   public AfiliadoDto() {
   
   } 
    
    
    

   public String nombreCompleto(){
       
       StringBuilder salida = new StringBuilder();
       salida.append(nombre);
       salida.append(",");
       salida.append(apellido);
       
       return salida.toString();
   }
   

   
   
 
}
