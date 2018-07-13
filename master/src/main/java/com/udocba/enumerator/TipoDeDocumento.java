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
public enum TipoDeDocumento {
    
    SELECCIONE("Seleccione"),
    DNI("DNI"),
    DU("DU"),
    CEDULA_DE_IDENTIDAD("CEDULA DE IDENTIDAD");
    
  private final String display;
  private TipoDeDocumento(String s) {
    display = s;
  }
  
    @Override
  public String toString() {
    return display;
  }
}
