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
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author josearielpereyra
 */
public class ClaseVisual extends JPanel {
  static PanelDeDibujo contenedor;
  Clase claseADibujar;
  private final Color color;
  public Point puntoInicialDeArrastre;
  int diferenciaEnXParaArrastre;
  int diferenciaEnYParaArrastre;
  int x;
  int y;
  int width;
  int height;
  public static LineBorder bordePredeterminado = new LineBorder( Color.GRAY );
  public static ClaseVisual claseActiva;
  private final JPanel panelAtributos;
  private final JPanel panelMetodos;
  private final int PRECISION = 5;
  private int tipoDeCursorInicial;

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
        tipoDeCursorInicial = getCursor().getType();
        puntoInicialDeArrastre = evento.getPoint();
        actualizarClaseActiva();
      }

        @Override
        public void mouseReleased(MouseEvent e) {
          tipoDeCursorInicial = Cursor.DEFAULT_CURSOR;
        }
      
      
    });
    
    this.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent evento) {
        Point puntoActual = evento.getPoint();
        diferenciaEnXParaArrastre = puntoInicialDeArrastre.x - puntoActual.x;
        diferenciaEnYParaArrastre = puntoInicialDeArrastre.y - puntoActual.y;
        
        Point ubicacionActual = ClaseVisual.this.getLocation();
        
        int diferenciaEnXParaRedimensionamiento = puntoActual.x - getWidth();
        int diferenciaEnYParaRedimensionamiento = puntoActual.y - getHeight();
        int nuevaAnchura = getWidth() + diferenciaEnXParaRedimensionamiento;
        int nuevaAltura = getHeight() + diferenciaEnYParaRedimensionamiento;
        int anchuraMinima = 100;
        int alturaMinima = 100;
        
        int tipoDeCursor = getCursor().getType();
        if(tipoDeCursor == Cursor.DEFAULT_CURSOR) {
            ClaseVisual.this.setLocation(ubicacionActual.x - diferenciaEnXParaArrastre, ubicacionActual.y - diferenciaEnYParaArrastre);
        }
        else if(tipoDeCursor == Cursor.E_RESIZE_CURSOR ) {
            if( getWidth() > anchuraMinima ) {
                nuevaAnchura += diferenciaEnXParaRedimensionamiento > 0 ? 3 : -3;
                ClaseVisual.this.setSize(nuevaAnchura, getHeight());
            }
        }
        else if(tipoDeCursor == Cursor.SE_RESIZE_CURSOR) {
            if(getWidth() >= anchuraMinima) {
                nuevaAnchura += diferenciaEnXParaRedimensionamiento > 0 ? 3 : -3;
            }
            else {
               nuevaAnchura = anchuraMinima;
            }
            
            
            if( getHeight() >= alturaMinima ) {
                nuevaAltura += diferenciaEnYParaRedimensionamiento > 0 ? 3 : -3;
            }
            else {
                nuevaAltura = alturaMinima;
            }
            ClaseVisual.this.setSize(nuevaAnchura, nuevaAltura);
        }
        else if(tipoDeCursor == Cursor.S_RESIZE_CURSOR ) {
            if( getHeight() > alturaMinima ) {
                nuevaAltura += diferenciaEnYParaRedimensionamiento > 0 ? 3 : -3;
                ClaseVisual.this.setSize(getWidth(), nuevaAltura);
            }
        }
        revalidate();
      }

        @Override
        public void mouseMoved(MouseEvent evento) {
            Point punto = evento.getPoint();
            int tipoDeCursor = Cursor.DEFAULT_CURSOR;
            
            if(tipoDeCursorInicial != Cursor.DEFAULT_CURSOR) {
                tipoDeCursor = tipoDeCursorInicial;
            }
                
            if(punto.x <= PRECISION && punto.y <= PRECISION) {
                tipoDeCursor = Cursor.NW_RESIZE_CURSOR;
            }
            else if(punto.x <= PRECISION && punto.y >= getHeight() - PRECISION) {
                tipoDeCursor = Cursor.SW_RESIZE_CURSOR;
            }
            else if(punto.x >= getWidth() - PRECISION && punto.y <= PRECISION) {
                tipoDeCursor = Cursor.NE_RESIZE_CURSOR;
            }
            else if (punto.x >= getWidth() - PRECISION && punto.y >= getHeight() - PRECISION) {
                tipoDeCursor = Cursor.SE_RESIZE_CURSOR;
            }
            else if(punto.x <= PRECISION ) {
                tipoDeCursor = Cursor.W_RESIZE_CURSOR;
            }
            else if( punto.x >= getWidth() - PRECISION ) {
                tipoDeCursor = Cursor.E_RESIZE_CURSOR;   
            }
            else if( punto.y <= PRECISION ) {
                tipoDeCursor = Cursor.N_RESIZE_CURSOR;   
            }
            else if( punto.y >= getHeight() - PRECISION ) {
                tipoDeCursor = Cursor.S_RESIZE_CURSOR;   
            }
            
            setCursor( new Cursor( tipoDeCursor ) );
        }
        
    });
    
    actualizarClaseActiva();
  }

  protected void actualizarClaseActiva() {
    if( claseActiva != null ) {
      claseActiva.setBorder(ClaseVisual.bordePredeterminado);
    }
    
    this.setBorder(new LineBorder(Color.RED, 3));
    claseActiva = this;
    
    if(contenedor != null) {
        contenedor.moveToFront(claseActiva);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.setColor( color );
    Rectangle r = g.getClipBounds();
    g.fillRect(r.x, r.y, r.width, r.height);
    super.paintComponent(g);
  }
}
