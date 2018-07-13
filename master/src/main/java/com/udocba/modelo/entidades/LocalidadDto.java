/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import java.io.Serializable;
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
 * @author Jose
 */
@Entity
@Table (name = "localidad")
public class LocalidadDto implements Serializable {
    
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Getter
    @Setter
    private String localidad;
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_provincia" , referencedColumnName = "id", nullable = false)
    private ProvinciaDto provincia;


    public LocalidadDto(){
    }

    @Override
    public String toString() {
        return this.localidad;
    }
    
    



}
