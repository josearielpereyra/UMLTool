package umltool;

import diagramasUML.interfazGrafica.PanelDeDibujo;
import java.awt.BorderLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * @author josearielpereyra
 */
public class UMLTool {

  public static void main(String[] args) {
    JFrame ventana = new JFrame("Aplicacion UML");
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
    PanelDeDibujo panel = new PanelDeDibujo();
    ventana.add(panel, BorderLayout.CENTER);
    
    JMenuBar barraDeMenu = new JMenuBar();
    JMenu menuArchivo = new JMenu("Archivo");
    JCheckBoxMenuItem menuItemMostrarCuadricula = new JCheckBoxMenuItem("Mostrar Cuadricula");
    menuItemMostrarCuadricula.addActionListener((e) -> {
      panel.mostrarCuadricula( menuItemMostrarCuadricula.isSelected() );
      panel.repaint();
    } );
    JMenuItem menuItemCrearClase = new JMenuItem("Agregar Nueva Clase");
    menuItemCrearClase.addActionListener((e) -> {
      panel.agregarClase();
    });
    
    JCheckBoxMenuItem menuItemSeleccionarTodo = new JCheckBoxMenuItem("Seleccionar Todo");
    menuItemSeleccionarTodo.addActionListener((e) -> {
      panel.seleccionarTodo( menuItemSeleccionarTodo.isSelected() );
    } );
    
    JMenuItem menuItemSalir = new JMenuItem("Salir");
    
    menuArchivo.add(menuItemCrearClase);
    menuArchivo.add(menuItemSeleccionarTodo);
    menuArchivo.add(menuItemMostrarCuadricula);
    menuArchivo.add(menuItemSalir);
    
    
    barraDeMenu.add(menuArchivo);
    ventana.setJMenuBar(barraDeMenu);
    
    ventana.setVisible(true);
  }
  
}
