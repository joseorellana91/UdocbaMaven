/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "telefono_escuela")
public class TelefonoEscuelaDto implements Serializable {
    
    
    @Id
    @Setter
    @Getter
    @Column(name = "id", nullable = false)
    private int id;
    

    @Id
    @Setter
    @Getter
    @Column(name = "numero")
    private int numero;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JoinColumn(name = "escuela_id", referencedColumnName = "idescuela", nullable = false)
    private EscuelaDto escuela;
    
    
    @Setter
    @Getter
    @Column(name="tipo")
    private String tipo;
}
