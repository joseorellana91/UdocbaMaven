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
 * @author Seba
 */
@Entity
@Table(name = "sede")
public class SedeDto implements Serializable{

    @Id
    @Setter
    @Getter
    @Column(name = "id", nullable = false)
    private int id;
    
    @Setter
    @Getter
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    
    
}
