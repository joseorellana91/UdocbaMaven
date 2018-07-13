/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.GeneratorType;
/**
 *
 * @author Seba
 */
@Entity
@Table(name = "registro")
public class RegistroDto implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name="id")
    private long id;
    
    @Getter
    @Setter
    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Getter
    @Setter
    @Column(name="resumen")
    private String resumen;
    
    @Getter
    @Setter
    @Column(name="detalle")
    private String detalle;
    
    /*
    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name="usuario_id")
    private UsuarioDto usuario;
    */
    
    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name="tramite_id", referencedColumnName = "id", nullable = false)
    private TramiteDto tramite ;

    public RegistroDto() {
    }

 

 
    
}
