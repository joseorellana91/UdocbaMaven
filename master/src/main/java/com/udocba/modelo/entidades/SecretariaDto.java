/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Seba
 */
public class SecretariaDto implements Serializable {
    
    
    @Id
    @Setter
    @Getter
    @Column(name = "id_secretaria", nullable = false)
    private String id;
    
    @Setter
    @Getter
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Setter
    @Getter
    @Column(name = "funcion")
    private String funcion;
    
    
    @Setter
    @Getter
    @Column(name = "estado", nullable = false)
    private int estado;

}
