/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventanas;
import java.sql.PreparedStatement;
import BDD.CRUDReservas;
import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.List;
import java.sql.Connection;


/**
 *
 * @author Hola
 */
public class Reserva extends javax.swing.JPanel {

    private  static JPanel instance = null;
    private CRUDReservas crudReservas;
    
    public Reserva() {
        initComponents();
        crudReservas = new CRUDReservas();
        actualizarTabla("", (String) jComboCarreras.getSelectedItem(), (String) jComboNiveles.getSelectedItem());
        btnBuscar.addActionListener(e -> actualizarTabla(buscar.getText(), (String) jComboCarreras.getSelectedItem(), (String) jComboNiveles.getSelectedItem()));
        jComboCarreras.addActionListener(e -> {
            String carreraSeleccionada = jComboCarreras.getSelectedItem().toString();
            cargarNiveles(carreraSeleccionada);
            actualizarTabla(buscar.getText(), carreraSeleccionada, (String) jComboNiveles.getSelectedItem());
        });
        jComboNiveles.addActionListener(e -> actualizarTabla(buscar.getText(), (String) jComboCarreras.getSelectedItem(), (String) jComboNiveles.getSelectedItem()));
        jTable1.setDefaultRenderer(Object.class, new MultiLineCellRenderer());
        
    }
    
    public static JPanel getPanelReservas (){
        if (instance == null){
            instance = new Reserva();
        }
        
        return instance;
                     
        
    }

    
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jComboCarreras = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboNiveles = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jLabel1.setText("  Horarios de Clase");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendario.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(133, 133, 133))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado"
            }
        ));
        jTable1.setRowHeight(60);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar2.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jComboCarreras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Software", "Tecnologías De La Información", "Ingeniería Industrial", "Telecomunicaciones", "Robótica y Automatización" }));
        jComboCarreras.setToolTipText("");
        jComboCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCarrerasActionPerformed(evt);
            }
        });

        jLabel4.setText("Carreras : ");

        jLabel5.setText("Niveles:");

        jComboNiveles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboNivelesActionPerformed(evt);
            }
        });

        jLabel6.setText("Materia:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jComboNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void jComboCarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCarrerasActionPerformed
       actualizarTabla(buscar.getText(), (String) jComboCarreras.getSelectedItem(),(String) jComboNiveles.getSelectedItem());
           String carreraSeleccionada = jComboCarreras.getSelectedItem().toString();
           cargarNiveles(carreraSeleccionada);
    }//GEN-LAST:event_jComboCarrerasActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        actualizarTabla(buscar.getText(), (String) jComboCarreras.getSelectedItem(),(String) jComboNiveles.getSelectedItem());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        if (buscar.getText().trim().equals("")) {
        actualizarTabla(buscar.getText(), (String) jComboCarreras.getSelectedItem(),(String) jComboNiveles.getSelectedItem());
          }        // TODO add your handling code here:
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyPressed
            // TODO add your handling code here:
    }//GEN-LAST:event_buscarKeyPressed

    private void jComboNivelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboNivelesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboNivelesActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.rowAtPoint(evt.getPoint());
    int col = jTable1.columnAtPoint(evt.getPoint());
    
    // Verificar si el clic se realizó en una celda válida
    if (row >= 0 && col >= 0) {
        // Obtener el valor de la celda
        Object value = jTable1.getValueAt(row, col);
        
        // Verificar si el valor de la celda no es nulo y es una cadena de texto
        if (value != null && value instanceof String) {
            String cellValue = (String) value;
            
            // Separar la cadena de texto en sus partes
            String[] parts = cellValue.split("\n");
            
            // Verificar si hay suficientes partes para obtener la información necesaria
            if (parts.length >= 3) {
                String nombreResponsable = parts[0];  // El primer elemento es el nombre del responsable
                String materia = parts[1];            // El segundo elemento es el nombre de la materia
                String espacio = parts[2];            // El tercer elemento es el nombre del espacio
                
                // Abrir el diálogo de edición
                EditDialog dialog = new EditDialog((Frame) SwingUtilities.getWindowAncestor(jTable1), nombreResponsable, materia, espacio);
                dialog.setVisible(true);
                
                // Verificar si se confirmó la edición
                if (dialog.isConfirmed()) {
                    // Obtener los nuevos valores del diálogo
                    String nuevoResponsable = dialog.getResponsable();
                    String nuevaMateria = dialog.getMateria();
                    String nuevoEspacio = dialog.getEspacio();
                    
                    // Actualizar la celda con los nuevos valores
                    String newValue = nuevoResponsable + "\n" + nuevaMateria + "\n" + nuevoEspacio;
                    jTable1.setValueAt(newValue, row, col);
                    
                    // Actualizar los datos en la base de datos
                    actualizarBaseDeDatos(jTable1, row, col, nuevoResponsable, nuevaMateria, nuevoEspacio);
                }
            }
        }
    }
    
    }//GEN-LAST:event_jTable1MouseClicked

    private void actualizarTabla(String buscar,String carrera,String nivel) {
       crudReservas.mostrarReservasEnTabla(jTable1, buscar, carrera,nivel); // Llama al método para mostrar los horarios en la tabla.
    }

private void cargarNiveles(String carrera) {
    List<String> niveles = crudReservas.obtenerNivelesPorCarrera(carrera);
    if (niveles != null) {
        jComboNiveles.removeAllItems();
        for (String nivel : niveles) {
            jComboNiveles.addItem(nivel);
        }
    } else {
        System.out.println("No se encontraron niveles para la carrera: " + carrera);
    }
}
private void actualizarBaseDeDatos(JTable jTable1, int row, int col, String responsable, String materia, String espacio) {
    // Asumiendo que los nombres de las columnas son los días
    String dia = jTable1.getColumnName(col); 
    // Asumiendo que la primera columna es la hora
    String hora = jTable1.getValueAt(row, 0).toString(); 

    // Crear la consulta SQL para actualizar los datos en la base de datos
    String sql = "UPDATE horarios " +
             "JOIN materias ON horarios.idMateriaPertenece = materias.idMateria " +
             "JOIN espacios ON horarios.idEspacioImparte = espacios.idEspacio " +
             "SET materias.IdResponsable = ?, horarios.IdMateriaPertenece = ?, espacios.Espacio = ? " +
             "WHERE horarios.Fecha_HoraInicio = ? AND horarios.Fecha_HoraFin = ?";

    
    try (PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)) {
        ps.setString(1, responsable); // Usamos la variable responsable en lugar de profesor
        ps.setString(2, materia); // Usamos la variable materia
        ps.setString(3, espacio);
         ps.setString(4, dia + " " + hora); // Concatenamos el día con la hora
        ps.setString(5, dia + " " + hora); // Concatenamos el día con la hora
        
        // Ejecutar la consulta
        ps.executeUpdate();
        
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "Datos actualizados en la base de datos correctamente.");
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar los datos en la base de datos. ERROR: " + e.getMessage());
    }
}





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JTextField buscar;
    private javax.swing.JComboBox<String> jComboCarreras;
    private javax.swing.JComboBox<String> jComboNiveles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
public class MultiLineCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea textArea = new JTextArea();
            textArea.setText(value != null ? value.toString() : "");
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            if (isSelected) {
                textArea.setBackground(table.getSelectionBackground());
                textArea.setForeground(table.getSelectionForeground());
            } else {
                textArea.setBackground(table.getBackground());
                textArea.setForeground(table.getForeground());
            }
            return textArea;
        }
    }


}


 
