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
@Table(name="direccion_afiliado")
public class DireccionAfiliadoDto implements Serializable{
    
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
    @ManyToOne
    private LocalidadDto localidad;
    
    @Getter
    @Setter
    private int codigoPostal;
    

    

    public DireccionAfiliadoDto() {
    }


    
    
}
