package umltool;

import diagramasUML.interfazGrafica.PanelDeDibujo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author josearielpereyra
 */
public class UMLTool {

  public static void main(String[] args) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
    //</editor-fold>
    
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
    
    JMenuItem menuItemMostrarTabla = new JMenuItem("Mostrar Tabla");
    menuItemMostrarTabla.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mostrarTabla();
      }
    });
    
    JMenuItem menuItemSalir = new JMenuItem("Salir");
    
    menuArchivo.add(menuItemCrearClase);
    menuArchivo.add(menuItemSeleccionarTodo);
    menuArchivo.add(menuItemMostrarCuadricula);
    menuArchivo.add(menuItemMostrarTabla);
    menuArchivo.add(menuItemSalir);
    
    
    barraDeMenu.add(menuArchivo);
    ventana.setJMenuBar(barraDeMenu);
    
    ventana.setVisible(true);
  }
  
  public static void mostrarTabla(){
    JFrame ventana = new JFrame("prueba de tabla");
    ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventana.setSize(500, 400);
    JTable tabla = new JTable();
    
    String[] nombreColumnas = {"Visibilidad", "Nombre", "", "Tipo"};
    String[][] filas = {
      {"-", "nombre"," : ", "String"},
      {"-", "apellido"," : ", "String"},
      {"-", "cedula"," : ", "String"},
      {"-", "direccion"," : ", "String"},
      {"-", "telefono"," : ", "String"}
    }; 
    
    String[] visibilidades = {"+","-","#"}; 
    JComboBox comboVisibilidad = new JComboBox(visibilidades);
    
    String[] tiposDeDatos = {"String", "Integer", "Double", "Character", "Boolean"}; 
    JComboBox comboTiposDeDatos = new JComboBox(tiposDeDatos);
    DefaultTableModel modeloTabla = new DefaultTableModel(filas, nombreColumnas){
      @Override
      public boolean isCellEditable(int row, int column) {
        if(column == 2) {
          return false;
        }
        
        return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
      }
    };
    tabla.setModel(modeloTabla);
    tabla.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboVisibilidad));
    tabla.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboTiposDeDatos));
    
    ventana.add(tabla);
    ventana.setVisible(true);
  }
}
