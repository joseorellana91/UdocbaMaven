/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Seba
 */
@Entity
@Table(name= "subclasificacion")
public class SubClasificaDto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name="id")
    private int id;
    
    
    @Getter
    @Setter
    @Column(name="nombre")
    private String nombre;
    
 
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "clasificacion_id")
    private ClasificacionDto clasificacion;
    
    @Override
    public String toString() {
        return "SubClasficaDto{" + "nombre=" + nombre + '}';
    }
    
    
}
