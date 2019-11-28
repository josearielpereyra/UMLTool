package diagramasUML.interfazGrafica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * @author josearielpereyra
 */
public class PanelDeDibujo extends JPanel{
  ArrayList<ClaseVisual> clases = new ArrayList<>();
  boolean cuadriculaVisible = false;
  boolean seDebeSeleccionarTodo = false;
  
  Point puntoInicialDeArrastre;
  ArrayList<Point> puntosInicialesDeArrastre = new ArrayList<>();
  Point puntoFinal;
  ClaseVisual claseActiva;
  private int diferenciaEnX;
  private int diferenciaEnY;
  private int numeroDeClase;
  int variacion = 10;

  public PanelDeDibujo() {
    setLayout(null);
    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode();
        int backSpace = KeyEvent.VK_BACK_SPACE;
        int delete = KeyEvent.VK_DELETE;
        int arriba = KeyEvent.VK_UP;
        int abajo = KeyEvent.VK_DOWN;
        int derecha = KeyEvent.VK_RIGHT;
        int izquierda = KeyEvent.VK_LEFT;
        
        if( claseActiva != null ) {
          variacion *= 2;
          if(variacion > 100) variacion = 100;
              
          if( codigo == backSpace || codigo == delete ) {
            clases.remove(claseActiva);
            claseActiva = null;
          }
          else if(codigo == arriba){
            claseActiva.y -= variacion;
          }
          else if(codigo == abajo){
            claseActiva.y += variacion;
          }
          else if(codigo == izquierda){
            claseActiva.x -= variacion;
          }
          else if(codigo == derecha){
            claseActiva.x += variacion;
          }
          
          if(claseActiva.x < 0) claseActiva.x = 0;
          if(claseActiva.x + claseActiva.width > getWidth()) claseActiva.x = getWidth() - claseActiva.width;
          if(claseActiva.y < 0) claseActiva.y = 0;
          if(claseActiva.y + claseActiva.height > getHeight()) claseActiva.y = getHeight() - claseActiva.height;
        }
        repaint();
      }

      @Override
      public void keyReleased(KeyEvent e) {
        variacion = 10;
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    if(cuadriculaVisible) {
      dibujarLineasHorizontales(g);
      dibujarLineasVerticales(g);
    }
    
    mostrarNumeros(g);
  }
  
  public void mostrarCuadricula(boolean seDebeMostrar) {
    cuadriculaVisible = seDebeMostrar;
  }
  
  private void mostrarNumeros(Graphics g) {
    g.setColor(Color.gray);
    for (int x = 40; x < getWidth(); x += 40) {
      g.setColor(Color.black);
      g.drawString("" + x, x-10, 20);
    }

    for (int y = 40; y < getWidth(); y += 40) {
      g.setColor(Color.black);
      g.drawString("" + y, 0, y);
    }
  }
  
  public void seleccionarTodo(boolean seleccionarTodo) {
    this.seDebeSeleccionarTodo = seleccionarTodo;
  }
  
  private void dibujarLineasVerticales(Graphics g) {
    g.setColor(Color.gray);
    for (int x = 20; x < getWidth(); x += 20) {
      g.setColor(Color.gray);
      g.drawLine(x, 0, x, getHeight());
    }
  }
  private void dibujarLineasHorizontales(Graphics g) {
    for (int y = 20; y < getWidth(); y += 20) {
      g.setColor(Color.gray);
      g.drawLine(0, y, getWidth(), y);
    }
  }
  
  public void agregarClase() {
    Random numerosAleatorios = new Random();
    int x = numerosAleatorios.nextInt(100);
    int y = numerosAleatorios.nextInt(30);
    
    ClaseVisual claseActual = new ClaseVisual(x, y, "Persona " + ++numeroDeClase);
    this.add( claseActual );
    claseActual.validate();
    repaint();
  }
}
