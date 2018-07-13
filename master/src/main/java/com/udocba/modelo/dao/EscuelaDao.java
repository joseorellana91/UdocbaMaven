/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.dao;

import java.util.List;
import com.udocba.modelo.entidades.EscuelaDto;

/**
 *
 * @author userund
 */
public class EscuelaDao extends ObjetoDAO<EscuelaDto>{
    
    
    
    public EscuelaDto escuelaPorDipregrep (String nroDipregrep){
    
            EscuelaDto escuela = null;
            
            try{
                
                escuela = (EscuelaDto)consultaHql("SELECT E FROM EscuelaDto E WHERE numero_dipregep ="+nroDipregrep).uniqueResult();
            
                
            }
            finally{
                
                terminaOperacion();
            
            }
            
            return escuela;
    }

  

   
  

    
   
   
}
