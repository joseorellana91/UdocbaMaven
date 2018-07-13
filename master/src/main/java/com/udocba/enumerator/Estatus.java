package com.udocba.enumerator;

/**
 *
 * @author Seba
 */
public enum Estatus {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    PENDIENTE("PENDIENTE");
    
  private final String display;
  private Estatus(String s) {
    display = s;
  }
  
    @Override
  public String toString() {
    return display;
  }
}
