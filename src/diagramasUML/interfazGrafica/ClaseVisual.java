/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagramasUML.interfazGrafica;
import diagramasUML.clase.Atributo;
import diagramasUML.clase.Clase;
import diagramasUML.clase.Metodo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author josearielpereyra
 */
public class ClaseVisual extends Rectangle {
  Clase claseADibujar;
  private final Color color;
  public Point puntoInicialDeArrastre;

  public ClaseVisual(int x, int y, String nombre) {
    setLocation(x, y);
    setSize(250, 350);
    Metodo metodo1 = new Metodo();
    Atributo atributo1 = new Atributo();
    Atributo atributo2 = new Atributo();
    ArrayList<Atributo> atributos = new ArrayList<>();
    atributos.add(atributo1);
    atributos.add(atributo2);
    ArrayList<Metodo> metodos = new ArrayList<>();
    metodos.add(metodo1);
    claseADibujar = new Clase(nombre, atributos, metodos);
    
    Random numerosAleatorios = new Random();
    int rojo = numerosAleatorios.nextInt(56) + 200;
    int verde = numerosAleatorios.nextInt(56) + 200;
    int azul = numerosAleatorios.nextInt(56) + 200;
    color = new Color(rojo, verde, azul, 200);
  }

  
  void dibujar(Graphics g) {    
    g.setColor(color);
    g.fillRect(x, y, width, height);
    
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width, height);
    g.drawString(claseADibujar.getNombre(), x + 10, y + 20);
    g.drawLine(x, y + 30, x + width, y + 30);
  }
}
