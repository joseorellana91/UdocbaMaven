/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.dao;

import com.udocba.modelo.entidades.LocalidadDto;
import java.util.List;

/**
 *
 * @author Jose
 */
public class LocalidadDao extends ObjetoDAO {
    
    
    public List<LocalidadDto> obtenerLocalidadesPorProvincia(long idprovincia){
        
        List<LocalidadDto> lista = consultaHql("from LocalidadDto L where L.provincia ="+idprovincia).list();
        terminaOperacion();
        
        return lista;
    
    
    
    }
    
}
