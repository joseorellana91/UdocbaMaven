/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import com.udocba.enumerator.Estatus;
import com.udocba.enumerator.TipoDeDocumento;
import com.udocba.enumerator.TipoDeUsuario;
import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Seba
 */
@Entity
@Table(name = "usuario")
public class UsuarioDto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    @Column(name = "idusuario", nullable = false)
    private long id;
    
    
    @Setter
    @Getter
    @Column(name = "nombreDeUsuario", nullable = false)
    private String nombre;

    @Setter
    @Getter
    @Column(name = "apellido")
    private String apellido;

    @Setter
    @Getter
    @Column(name = "documento")
    private String documento;

    @Setter
    @Getter
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
     
    @Setter
    @Getter
    @Enumerated (EnumType.STRING)
    @Column(name = "estatus")
    private Estatus estatus;

    @Setter
    @Getter
    @Column(name = "email")
    private String email;

    /*
    @Setter
    @Getter
    @Column(name = "rol")
    private int rol;
    */
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sede_idsede", referencedColumnName = "id", nullable = true)
    private SedeDto sede;
    
 /* @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_secretaria", referencedColumnName = "id", nullable = false)
    private SecretariaDto secretaria;
    */
 //   public static enum Estatus{ACTIVO,INACTIVO,PENDIENTE};

   

    //private int activo;
  //  private Estatus estatus = Estatus.ACTIVO;
    
    @Getter
    @Setter
    @Enumerated (EnumType.STRING)
    @Column (name = "TipoDeDocumento")
    private TipoDeDocumento tipoDocumento;
    
    
    @Getter
    @Setter
    @Enumerated (EnumType.STRING)
    private TipoDeUsuario tipousuario;
    

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo_idgrupo", referencedColumnName = "idgrupo", nullable = true)
    private GrupoDto grupo;

    

    
    
    public UsuarioDto() {
    }

    
    
    
    
    
    public UsuarioDto(String nombre, String apellido, String documento, 
            String nombreDeUsuario, String contrasena, String mail, SedeDto sede, GrupoDto grupo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.nombre = nombreDeUsuario;
        this.contrasena = contrasena;
        this.email = mail;
        this.sede = sede;
        this.grupo = grupo;
        

    }

  
    
    
    


    
}


