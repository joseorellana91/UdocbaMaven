/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.dao;

import com.udocba.enumerator.EstadoAfiliado;
import com.udocba.enumerator.Estatus;
import java.io.Serializable;
import com.udocba.modelo.entidades.AfiliadoDto;
import com.udocba.modelo.entidades.UsuarioDto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AfiliadoDao extends ObjetoDAO<AfiliadoDto>{
        
    Session sesion;
    Transaction tx;  
    
    
    public AfiliadoDto AfiliadoPorNumero(String numeroDocumento) throws HibernateException{
            
            AfiliadoDto afiliado = null;
            
            try{
            afiliado = (AfiliadoDto)consultaHql("select A from AfiliadoDto A where numeroDocumento = "+ numeroDocumento).uniqueResult();
            
            }
            finally{
            terminaOperacion();
       
            }
            return afiliado;
        
     
    }
    
    
        public List<AfiliadoDto> AfiliadoPorNumeroLista(String numeroDocumento) throws HibernateException{
            
       List< AfiliadoDto> afiliados = null;
       try{
         iniciaOperacion();
            Query query = getSession().createQuery("FROM AfiliadoDto a WHERE  numeroDocumento = "+numeroDocumento);
            
            
            afiliados = query.list();
            terminaOperacion();
       }catch(Exception e){
           System.out.println("Problemas en la consulta: " + e);
       }
         
         return afiliados;
  }
        
        
        
        
        
        
        
        
    
      public boolean existeUsuaioConDni(String documento) {
          
          
          List<AfiliadoDto> lista = this.obtenListaEntidad(AfiliadoDto.class);

          for (AfiliadoDto u : lista) {
              if (u.getDocumento().equals(documento)) {
                  return true;

            }
        }
        return false;
    }
      
      
      
            public boolean existeNumeroAfiliado(String idAfiliado) {
          
          
          List<AfiliadoDto> lista = this.obtenListaEntidad(AfiliadoDto.class);

          for (AfiliadoDto u : lista) {
              if (u.getNumafiliado().equals(idAfiliado)) {
                  return true;

            }
        }
        return false;
    }
      
      
      
      
        public List<AfiliadoDto> FilterUsuarioByState(){
      
       List< AfiliadoDto> afiliados = null;
       try{
         iniciaOperacion();
            Query query = getSession().createQuery("FROM AfiliadoDto a WHERE  a.estatus = :estatus");
            query.setParameter("estatus", EstadoAfiliado.ACTIVO);
            
            afiliados = query.list();
            terminaOperacion();
       }catch(Exception e){
           System.out.println("Problemas en la consulta: " + e);
       }
         
         return afiliados;
  }
      
      
      

}
    
    

    
    



   
 

   
   
   
   


   
    
    
        
    

  

   
   
  

   
  
     
    
    


