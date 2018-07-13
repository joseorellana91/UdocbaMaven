/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.dao;

import java.util.List;
import com.udocba.modelo.entidades.UsuarioDto;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author neteoro
 * @param <ClaseDto>
 */
public abstract class ObjetoDAO<ClaseDto> {

    private Session sesion;
    private Transaction tx;

   
   

    public void GuardaEntidad(ClaseDto objeto) throws HibernateException {
      
        try {
            iniciaOperacion();
            sesion.save(objeto);
            tx.commit();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(null, he.toString());

        } finally {

            sesion.close();
        }
    }

  
    
    public ClaseDto getEntidad( Class clase , long id )throws HibernateException{
        
          ClaseDto objeto = null;
        try {
            iniciaOperacion();
            objeto = (ClaseDto)sesion.get( clase, id);
        } finally {
            sesion.close();
        }

        return objeto;
    }
    
    /**
     *
     * @param obj
     */
    public void updateEntidad(ClaseDto objeto) throws HibernateException {

        try {

            iniciaOperacion();
            sesion.update(objeto);
           // sesion.merge(objeto);
            tx.commit();

        } catch (HibernateException he) {

            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public void eliminar(ClaseDto objeto) throws HibernateException {

        try {

            iniciaOperacion();
            sesion.delete(objeto);
            tx.commit();

        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }

    }
      
    public List<ClaseDto> obtenListaEntidad(Class clase) {

        List<ClaseDto> listaObjeto = null;
       

        try {
            iniciaOperacion();
            listaObjeto = sesion.createQuery("from " + clase.getName()).list();
            
        } finally {
            sesion.close();
        }

        return listaObjeto;

    }
    
    
    protected Query consultaHql(String consultaHql) throws HibernateException{
    
            Query query = null; 
        
        try{
            iniciaOperacion();
            query = sesion.createQuery(consultaHql);
            return query;
            
        
        }catch(Exception e){
            
        }
 
         return query;
    }
    
    
   

    protected void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    protected void terminaOperacion() {
        sesion.getTransaction().commit();
        sesion.close();
    }

    protected void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    
    protected Session getSession()
    {
        return sesion;
    }

}
