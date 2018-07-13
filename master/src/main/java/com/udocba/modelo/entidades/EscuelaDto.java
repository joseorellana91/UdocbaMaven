/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author seba
 */
@Entity
@Table(name="escuela")
public class EscuelaDto implements Serializable {
    
    
    @Id
    @Setter
    @Getter
    @Column(name = "idescuela")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Setter
    @Getter
    @Column(name = "nombre")
    private String nombre;
    
    @Setter
    @Getter
    @Column(name = "gestion")
    private String gestion;
    
    @Setter
    @Getter
    @Column(name="numero_dipregep")
    private String dipregep;
    
    @Setter
    @Getter
    @Column(name="calle")
    private String calle;
    
    @Setter
    @Getter
    @Column(name="localidad")
    private String localidad;
    
    @Setter
    @Getter
    @Column(name="partido")
    private String partido;
    
    @Setter
    @Getter
    @Column(name="codigoPostal")
    private String codigoPostal;
    
    @Setter
    @Getter
    @ManyToMany(mappedBy = "escuelas")
    private List<ServicioDto> servicios = new ArrayList<>();
 
    

   
  //  @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
  //  @JoinColumn(name = "direccion_id")
  //  private DireccionDto direccion;
    
    
    public EscuelaDto() {
        
    }


    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EscuelaDto other = (EscuelaDto) obj;
        if (!Objects.equals(this.dipregep, other.dipregep)) {
            return false;
        }
        return true;
    }
    
    
    
}
