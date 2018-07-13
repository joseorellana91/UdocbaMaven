package com.udocba.modelo.entidades;

import com.udocba.enumerator.TipoDeTelefono;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "telefono_afiliado")
public class TelefonoAfiliadoDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    @Column(name = "id", nullable = false)
    private long id;
    

    @Id
    @Setter
    @Getter
    @Column(name = "numero")
    private int numero;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JoinColumn(name = "afiliado_id", referencedColumnName = "id", nullable = false)
    private AfiliadoDto afiliado;
    
    
    @Setter
    @Getter
    @Enumerated (EnumType.STRING)
    @Column(name="tipo")
    private TipoDeTelefono tipo;





    public TelefonoAfiliadoDto(){
    }




}
