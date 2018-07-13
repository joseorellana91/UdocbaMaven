/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.modelo.dao;

import com.udocba.enumerator.Estatus;
import java.util.List;
import com.udocba.modelo.entidades.UsuarioDto;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jose
 */
public class UsuarioDao extends ObjetoDAO {

    public boolean buscarUsuario(String nombreUsuario, String contrasena) {
        int encontrado = 0;

        List<UsuarioDto> lista = this.obtenListaEntidad(UsuarioDto.class);
        for (UsuarioDto c : lista) {

            if (c.getNombre().equals(nombreUsuario) && c.getContrasena().equals(contrasena)) {

                encontrado++;
            }

        }
        if (encontrado > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getCredencialesUsuario(String documento) {

        String mail = "";
        List<UsuarioDto> lista = this.obtenListaEntidad(UsuarioDto.class);

        for (UsuarioDto u : lista) {

            if (u.getDocumento() == documento) {

                mail = u.getEmail();

            }
        }
        return mail;
    }
    
    public UsuarioDto getUsuarioByDoc( String documento){
             UsuarioDto usuario = null;
             System.out.println("documento : " + documento);
        try
        {
            iniciaOperacion();
            Query query = getSession().createQuery("FROM UsuarioDto u WHERE u. documento = :documento");
            query.setParameter("documento", documento);
            //query.setParameter("password", password);

            usuario = (UsuarioDto)query.uniqueResult();
            terminaOperacion();
        }
        catch (HibernateException he)
        {
            manejaExcepcion(he);
        }
       /* finally
        {
            terminaOperacion();
        }*/


        return usuario;
   
    }

    public String getPassUsuario(String documento) {
        String contrasena = "";
        List<UsuarioDto> lista = this.obtenListaEntidad(UsuarioDto.class);

        for (UsuarioDto u : lista) {

            if (u.getDocumento() == documento) {

                contrasena = u.getContrasena();

            }
        }
        return contrasena;

    }
   
    public UsuarioDto ObtenerUsuarioObj ( long id){
       return (UsuarioDto) super.getEntidad(UsuarioDto.class, id);
    }
    

  public UsuarioDto getUsuarioByCredenciales(String username, String password) throws HibernateException
    {
        UsuarioDto usuario = null;
      
        try
        {
            iniciaOperacion();
            Query query = getSession().createQuery("FROM UsuarioDto u WHERE u. nombre = :nombreUsuario AND u. contrasena = :password");
            query.setParameter("nombreUsuario", username);
            query.setParameter("password", password);

            usuario = (UsuarioDto)query.uniqueResult();
            
             terminaOperacion();
        }
        catch (HibernateException he)
        {
            System.out.println("CAYO EN EL CATCH USUARIODAO");
            manejaExcepcion(he);
        }
      /*  finally
        {
            terminaOperacion();
        }*/


        return usuario;
    }
  
//******** filtro los usuarios solo por estado ACTIVO ****************   
  public List<UsuarioDto> FilterUsuarioByState(){
      
       List< UsuarioDto> usuario = null;
       try{
         iniciaOperacion();
            Query query = getSession().createQuery("FROM UsuarioDto u WHERE  u.estatus = :estatus");
            query.setParameter("estatus", Estatus.ACTIVO);
            
            usuario = query.list();
            terminaOperacion();
       }catch(Exception e){
           System.out.println("Problemas en la consulta: " + e);
       }
         
         return usuario;
  }

    
}
