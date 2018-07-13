/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Seba
 */
@Entity
@Table(name = "clasificacion")
public class ClasificacionDto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private int id;
    
    @Getter
    @Setter
    @Column(name = "nombre")
    private String nombre;


    public ClasificacionDto() {
    }
/*
    public String nombreCompleto(){
       
       StringBuilder salida = new StringBuilder();
       salida.append(nombre);
       salida.append("/");
       salida.append(subclalist);
       
       return salida.toString();
   
}*/

}