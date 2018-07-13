/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import com.udocba.enumerator.Estatus;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
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
import org.hibernate.bytecode.internal.javassist.AccessOptimizerAdapter;

/**
 *
 * @author Seba
 */
@Entity
@Table(name = "tramite")
public class TramiteDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private long id;

    @Getter
    @Setter
    @Column(name = "resumen")
    private String resumen;

    @Getter
    @Setter
    @Column(name = "descripcion")
    private String descripcion;
    
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Calendar fecha_apertura;
    
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Calendar fecha_resuelto;
    
    
    
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "afiliado_id" , referencedColumnName = "id", nullable = false)
    private AfiliadoDto afiliado;
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private GrupoDto grupo;
    
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "clasificacion_id")
    private ClasificacionDto clasificacion;
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "sede_id")
    private SedeDto sede;
    
    
    @Getter
    @Setter
    @Enumerated (EnumType.STRING) 
    private Estatus estado;
 
    
    
    
   // @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "RegistroDto")
    
 /*
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "registro_id")
    private List<RegistroDto> registros = new ArrayList();
    */

    public TramiteDto() {
    }


    
}
