/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jose
 */
@Entity
@Table (name = "direccion_escuela")
public class DireccionEscuelaDto implements Serializable {
    
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private long id;
    
    
    @Getter
    @Setter
    private String calle;
    
    @Getter
    @Setter
    private int numero;
    
    @Getter
    @Setter
    private int codigoPostal;
    
    @Getter
    @Setter
    @ManyToOne
    private LocalidadDto localidad;
    
    
    @Getter
    @Setter
    @ManyToOne (fetch = FetchType.EAGER)
    private EscuelaDto escuela;
          
    
    
    public DireccionEscuelaDto(){
    }
}
