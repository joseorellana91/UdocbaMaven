/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.dao;

import com.udocba.modelo.entidades.TramiteDto;
import java.util.List;

/**
 *
 * @author neteoro
 */
public class TramiteDao extends ObjetoDAO<TramiteDto>{

    
    /**
     * Devulve el id del ultimo registro +1 para obtener numero para un tramite nuevo
     * @return 
     */
    
    
    public String ultimoRegistro(){
    
        String id= "";
        
        try{
        long ultimoId =(long) consultaHql("select max(tram.id) from TramiteDto tram").uniqueResult();
        ultimoId++;
        id = String.valueOf(ultimoId);
        }
        catch(Exception e){
            id = "0";
        
        }
        finally{
        
            terminaOperacion();
        }
        
        return id;
        
    }
    
    
    public List<TramiteDto> listaPorEstadoGrupo(String grupo){
        
        List<TramiteDto> lista = null;
        
        try{
            
            consultaHql("Select ");
        
      
        
        }catch(Exception e){
            
        }
        
      return lista;
    }
 



   
    
}
