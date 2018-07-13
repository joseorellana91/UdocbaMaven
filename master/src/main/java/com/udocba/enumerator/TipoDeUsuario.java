/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udocba.enumerator;

/**
 *
 * @author Jose
 */
public enum TipoDeUsuario {
    SELECCIONE("Seleccione"),
    ADMINISTRADOR("ADMINISTRADOR"),
    USUARIO("USUARIO");
    
  private final String display;
  private TipoDeUsuario(String s) {
    display = s;
  }
  
    @Override
  public String toString() {
    return display;
  }
    
}
