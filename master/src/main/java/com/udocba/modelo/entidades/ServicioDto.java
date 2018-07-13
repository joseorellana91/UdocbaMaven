/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Seba
 */
@Entity
@Table(name="Servicio")
public class ServicioDto implements Serializable{
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservicio")
    private int id;
   
    @Getter
    @Setter
    @Column(name = "nivel")
    private String nivel;
    
    @Getter
    @Setter
    @Column(name = "modalidad")  
    private String modalidad;
    
    @Getter
    @Setter
    @Column(name = "secuencia")
    private String secuencia;
    
    @Getter
    @Setter
    @Column(name = "cargo")
    private String cargo;
    
    
    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "escuela_servicio",joinColumns = @JoinColumn(name = "servicio_id"),inverseJoinColumns = @JoinColumn(name = "Escuela_id"))
    private List<EscuelaDto> escuelas = new ArrayList<>();
       
 
    
    
    public void addEscuela(EscuelaDto escuela) {
        escuelas.add(escuela);
        escuela.getServicios().add(this);
    }
 
    public void removeEscuela(EscuelaDto escuela) {
        escuelas.remove(escuela);
        escuela.getServicios().remove(this);
    }
    
    
    public ServicioDto() {
        
        
    }




   
    
}
