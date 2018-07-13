/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.dao;

import java.util.List;
import com.udocba.modelo.entidades.SedeDto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jose
 */
public class SedeDao {
    
    private Session sesion; 
    private Transaction tx;  

    public long guardaSede(SedeDto Sede) throws HibernateException {
        
        
        long id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (Long) sesion.save(Sede); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  

        return id; 
    }  

    public void actualizaSede(SedeDto sede) throws HibernateException { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(sede); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    
    
    public void eliminaSede(SedeDto Sede) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(Sede); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public SedeDto obtenerSede(long idContacto) throws HibernateException {
        
        
        SedeDto sedevar = null;  
        try 
        { 
            iniciaOperacion(); 
            sedevar = sesion.get(SedeDto.class, idContacto); 
        } finally 
        { 
            sesion.close(); 
        }  

        return sedevar; 
    }  

    
    
    
    
    
    
    public List<SedeDto> obtenListaSede() throws HibernateException { 
        
        List<SedeDto> listasede = null;  

        try 
        { 
            iniciaOperacion(); 
            listasede = sesion.createQuery("from sede").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listasede; 
    }  

    
    
    
    
    private void iniciaOperacion() throws HibernateException { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    }  
    
    
    
    
    

    private void manejaExcepcion(HibernateException he) throws HibernateException 
    { 
        tx.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    }



}
    
    
 
    

