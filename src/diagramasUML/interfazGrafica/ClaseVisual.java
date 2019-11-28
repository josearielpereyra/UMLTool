/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagramasUML.interfazGrafica;
import diagramasUML.clase.Atributo;
import diagramasUML.clase.Clase;
import diagramasUML.clase.Metodo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author josearielpereyra
 */
public class ClaseVisual extends JPanel {
  Clase claseADibujar;
  private final Color color;
  public Point puntoInicialDeArrastre;
  int diferenciaEnX;
  int diferenciaEnY;
  int x;
  int y;
  int width;
  int height;
  public static LineBorder bordePredeterminado = new LineBorder( Color.GRAY );
  public static ClaseVisual claseActiva;
  private final JPanel panelAtributos;
  private final JPanel panelMetodos;

  public ClaseVisual(int x, int y, String nombre) {
    setOpaque(false);
    setLocation(x, y);
    setSize(250, 350);
    setLayout(new BorderLayout());
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
    this.setBackground(color);
    
    JLabel etiquetaNombre = new JLabel( claseADibujar.getNombre());
    etiquetaNombre.setFont(etiquetaNombre.getFont().deriveFont(Font.BOLD));
    etiquetaNombre.setHorizontalAlignment(SwingConstants.CENTER);
    etiquetaNombre.setBorder(bordePredeterminado);
    panelAtributos = new JPanel();
    panelAtributos.setBorder(bordePredeterminado);
    panelAtributos.setOpaque(false);
    panelMetodos = new JPanel();
    panelMetodos.setBorder(bordePredeterminado);
    panelMetodos.setOpaque(false);
    this.add( etiquetaNombre, BorderLayout.NORTH );
    this.add(panelAtributos, BorderLayout.CENTER);
    this.add(panelMetodos, BorderLayout.SOUTH);
    
    this.setBorder(bordePredeterminado);
    
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent evento) {
        puntoInicialDeArrastre = evento.getPoint();
        actualizarClaseActiva();
      }
      
    });
    
    this.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent evento) {
        Point puntoActual = evento.getPoint();
        diferenciaEnX = puntoInicialDeArrastre.x - puntoActual.x;
        diferenciaEnY = puntoInicialDeArrastre.y - puntoActual.y;
        
        Point ubicacionActual = ClaseVisual.this.getLocation();
        ClaseVisual.this.setLocation( ubicacionActual.x - diferenciaEnX, ubicacionActual.y - diferenciaEnY);
      }
    });
    
    actualizarClaseActiva();
  }

  private void actualizarClaseActiva() {
    if(ClaseVisual.claseActiva != null) {
      claseActiva.setBorder(ClaseVisual.bordePredeterminado);
    }
    ClaseVisual.claseActiva = this;
    this.setBorder(new LineBorder(Color.RED, 3));
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.setColor( color );
    Rectangle r = g.getClipBounds();
    g.fillRect(r.x, r.y, r.width, r.height);
    super.paintComponent(g);
  }
}
