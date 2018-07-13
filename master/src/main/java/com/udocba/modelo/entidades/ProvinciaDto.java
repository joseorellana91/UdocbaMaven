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
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jose
 */
@Entity
@Table (name = "provincia")
public class ProvinciaDto implements Serializable{
    
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    
    @Getter
    @Setter
    @Column (name = "provincia")
    private String provincia;
    
    
    public ProvinciaDto(){
    
    }

    
    
    
    
    
    
    
    @Override
    public String toString() {
        return this.provincia;
    }
    
    
    
    
    
    
}
