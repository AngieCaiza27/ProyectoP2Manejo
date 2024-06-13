/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventanas;
import java.sql.PreparedStatement;
import BDD.CRUDHorarios;
import BDD.CRUDHorarios.Espacio;
import BDD.CRUDHorarios.Materia;
import BDD.CRUDHorarios.Responsable;
import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;


/**
 *
 * @author Hola
 */
public class Reservas extends javax.swing.JPanel {

    private  static JPanel instance = null;
    private CRUDHorarios crudHorarios;
    
    public Reservas() {
        initComponents();
        crudHorarios = new CRUDHorarios();
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
    
    public static JPanel getPanelHorarios (){
        if (instance == null){
            instance = new Reservas();
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

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

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
                .addGap(238, 238, 238)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
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
        jTable1.setRowHeight(90);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(200);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(1).setMinWidth(200);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(2).setMinWidth(200);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(3).setMinWidth(200);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(4).setMinWidth(200);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(200);
            jTable1.getColumnModel().getColumn(5).setMinWidth(200);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(200);
        }

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

        jComboCarreras.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboCarreras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Software", "Tecnologías De La Información", "Ingeniería Industrial", "Telecomunicaciones", "Robótica y Automatización" }));
        jComboCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCarrerasActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel4.setText("Carreras : ");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel5.setText("Niveles:");

        jComboNiveles.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboNiveles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboNivelesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jComboCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jComboNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
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
                String Materia = parts[1];            // El segundo elemento es el nombre de la materia
                String espacio = parts[2];            // El tercer elemento es el nombre del espacio
                
                // Abrir el diálogo de edición
                EditDialog dialog = new EditDialog((Frame) SwingUtilities.getWindowAncestor(jTable1));
                dialog.setVisible(true);
                
                // Verificar si se confirmó la edición
                if (dialog.isConfirmed()) {
                    // Obtener los nuevos valores del diálogo

                    Materia nuevaMateria = dialog.getMateria();
                    Espacio nuevoEspacio = dialog.getEspacio();
                    
                    // Actualizar la celda con los nuevos valores
                    String newValue =  nuevaMateria.getNombre() + "\n" + nuevoEspacio.getNombre();
                    jTable1.setValueAt(newValue, row, col);
                    
                    
                    // Actualizar los datos en la base de datos
                    actualizarBaseDeDatos(jTable1, row, col, nuevaMateria.getId(), nuevoEspacio.getId());
                }
            }
        }else {
    try (Connection connection = crudHorarios.getConnection()) {
        String responsablesQuery = "SELECT idResponsable, nombre1Responsable, apellido1Responsable FROM responsables";
        try (PreparedStatement statement = connection.prepareStatement(responsablesQuery)) {
            ResultSet resultSet = statement.executeQuery();

            // Crear un JComboBox para mostrar los responsables disponibles
            JComboBox<String> responsablesComboBox = new JComboBox<>();
            while (resultSet.next()) {
                String nombreCompleto = resultSet.getString("nombre1Responsable") + " " + resultSet.getString("apellido1Responsable");
                responsablesComboBox.addItem(nombreCompleto);
            }

            // Mostrar un JOptionPane con el JComboBox para seleccionar al responsable
            int option = JOptionPane.showOptionDialog(this, responsablesComboBox, "Seleccionar Responsable", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.OK_OPTION) {
                // Obtener el ID del responsable seleccionado directamente del JComboBox
                int indexResponsableSeleccionado = responsablesComboBox.getSelectedIndex();
                int idResponsable = indexResponsableSeleccionado + 1; // Sumar 1 porque los IDs en la base de datos generalmente comienzan en 1

                // Actualizar los datos en la base de datos
                actualizarBaseDeDatos(jTable1, row, col, idResponsable);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar los responsables.");
    }
}

    }
    
    }//GEN-LAST:event_jTable1MouseClicked

    private void actualizarTabla(String buscar,String carrera,String nivel) {
       crudHorarios.mostrarHorariosEnTabla(jTable1, buscar, carrera,nivel); // Llama al método para mostrar los horarios en la tabla.
    }

private void cargarNiveles(String carrera) {
    List<String> niveles = crudHorarios.obtenerNivelesPorCarrera(carrera);
    if (niveles != null) {
        jComboNiveles.removeAllItems();
        for (String nivel : niveles) {
            jComboNiveles.addItem(nivel);
        }
    } else {
        System.out.println("No se encontraron niveles para la carrera: " + carrera);
    }
}
private void actualizarBaseDeDatos(JTable jTable1, int row, int col,  int materia, int espacio) {
    // Asumiendo que los nombres de las columnas son los días
    String dia = jTable1.getColumnName(col); 
    
    int numDia =0;
    switch(dia) {
        case "Lunes": 
            numDia=2;
            break;
        case "Martes": 
            numDia=3;
            break;
        case "Miércoles": 
            numDia=4;
            break;    
        case "Jueves": 
            numDia=5;
            break;
        case "Viernes": 
            numDia=6;
            break;
        case "Sábado": 
            numDia=7;
            break;
          
    }
    // Asumiendo que la primera columna es la hora
    String hora = jTable1.getValueAt(row, 0).toString(); 
    String[] parts = hora.split(":");
    int hours = 0;
    
    hours++;
    String horaFin = String.format("%02d:%02d", hours, 0);
    
    

    // Crear la consulta SQL para actualizar los datos en la base de datos
    String sql = " UPDATE horarios " +
             "JOIN materias ON horarios.idMateriaPertenece = materias.idMateria " +
             "JOIN espacios ON horarios.idEspacioImparte = espacios.idEspacio " +
             "SET materias.IdResponsablePertenece = ?, horarios.IdMateriaPertenece = ?, espacios.idEspacio = ?, " +
             "horarios.Fecha_HoraInicio = CONCAT(DATE(Fecha_HoraInicio), ' ', ?), " +
             "horarios.Fecha_HoraFin = CONCAT(DATE(Fecha_HoraFin), ' ', ?) " +
             "WHERE DAYOFWEEK(Fecha_HoraInicio) = ?;";

    
    try (PreparedStatement ps = crudHorarios.getConnection().prepareStatement(sql)) {
         // Usamos la variable responsable en lugar de profesor
        ps.setInt(1, materia); // Usamos la variable materia
        ps.setInt(2, espacio);
        ps.setString(3, hora+":00"); // Concatenamos el día con la hora
        ps.setString(4, horaFin + ":00"); // Concatenamos la hora de fin, asumiendo que termina al final del minuto
        ps.setInt(5, numDia); // Día de la semana
        
        // Ejecutar la consulta
         ps.executeUpdate();
     
        
        JOptionPane.showMessageDialog(null, "Datos actualizados en la base de datos correctamente.");
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar los datos en la base de datos. ERROR: " + e.getMessage());
    }
}

private void actualizarBaseDeDatos(JTable jTable1, int row, int col, int idResponsable) {
    // Asumiendo que la primera columna es la hora
    String horaInicio = jTable1.getValueAt(row, 0).toString(); 
    String[] parts = horaInicio.split(":");
    int hours = Integer.parseInt(parts[0]);
    int minutes = Integer.parseInt(parts[1]);
    
    // Calcular la hora de finalización sumando una hora a la hora de inicio
    hours++;
    String horaFin = String.format("%02d:%02d:00", hours, minutes);

    // Obtener el día de la semana basado en el nombre de la columna
    String dia = jTable1.getColumnName(col);
    int numDia = 0;
    switch (dia) {
        case "Lunes": numDia = 2; break;
        case "Martes": numDia = 3; break;
        case "Miércoles": numDia = 4; break;
        case "Jueves": numDia = 5; break;
        case "Viernes": numDia = 6; break;
        case "Sábado": numDia = 7; break;
    }

    try (Connection connection = crudHorarios.getConnection()) {
        // Crear la consulta SQL para actualizar los datos en la base de datos
        String sql = "UPDATE horarios " +
                     "JOIN materias ON horarios.idMateriaPertenece = materias.idMateria " +
                     "JOIN espacios ON horarios.idEspacioImparte = espacios.idEspacio " +
                     "SET materias.idResponsablePertenece = ?, " +
                     "horarios.Fecha_HoraInicio = CONCAT(DATE(Fecha_HoraInicio), ' ', ?), " +
                     "horarios.Fecha_HoraFin = CONCAT(DATE(Fecha_HoraInicio), ' ', ?) " +
                     "WHERE DAYOFWEEK(Fecha_HoraInicio) = ? " +
                     "AND TIME(Fecha_HoraInicio) = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idResponsable);
            ps.setString(2, horaInicio + ":00"); // Concatenamos el día con la hora de inicio
            ps.setString(3, horaFin); // Concatenamos el día con la hora de fin
            ps.setInt(4, numDia); // Día de la semana
            ps.setString(5, horaInicio); // Hora de inicio
           
            // Ejecutar la consulta
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Datos actualizados en la base de datos correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el horario correspondiente para actualizar.");
            }
        }
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

        // Establecer color de fondo según el contenido de la celda
        if (value != null && !value.toString().isEmpty()) {
            textArea.setBackground(Color.RED);
        } else {
            textArea.setBackground(Color.GREEN);
        }

        if (isSelected) {
            textArea.setBackground(table.getSelectionBackground());
            textArea.setForeground(table.getSelectionForeground());
        } else {
            textArea.setForeground(table.getForeground());
        }
        
        return textArea;
    }
}



}


 
