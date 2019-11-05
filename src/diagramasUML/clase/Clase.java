/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagramasUML.clase;

import java.util.ArrayList;

/**
 *
 * @author josearielpereyra
 */
public class Clase {
  private String nombre;
  private ArrayList<Atributo> atributos;
  private ArrayList<Metodo> metodos;

  public Clase(String nombre, ArrayList<Atributo> atributos, ArrayList<Metodo> metodos) {
    this.nombre = nombre;
    this.atributos = atributos;
    this.metodos = metodos;
  }

  public ArrayList<Metodo> getMetodos() {
    return metodos;
  }

  public void setMetodos(ArrayList<Metodo> metodos) {
    this.metodos = metodos;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public ArrayList<Atributo> getAtributos() {
    return atributos;
  }

  public void setAtributos(ArrayList<Atributo> atributos) {
    this.atributos = atributos;
  }
}
