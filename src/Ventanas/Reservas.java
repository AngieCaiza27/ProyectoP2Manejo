/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventanas;

import BDD.CRUDEReservas;
import BDD.CRUDHorarios;
import Test.DatabaseHandler;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author Kevin
 */
public class Reservas extends javax.swing.JPanel {

    private  static JPanel instance = null;
    private CRUDEReservas crudReservas;
    private CRUDHorarios crudHorarios;
    private DatabaseHandler databaseHandler;

    public Reservas() {
        initComponents();
        databaseHandler = new DatabaseHandler();
        crudReservas = new CRUDEReservas();
        llenarEdificios();
        limitarCalendario();
        //llenarTiposEspacios();
        //llenarEspacios();
    }
    
    public void limitarCalendario() {
        Date fechaActual = new Date();        
        this.Calendario.setMinSelectableDate(fechaActual);
        this.Calendario.setMaxSelectableDate(obtenerFinPeriodo());
    }
    
    public Date obtenerFinPeriodo() {
        Date fechaFin = new Date();
        String sql = "SELECT fechaFin FROM mydb.peridosacademicos;";
        ResultSet rs;
        try ( PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)){
            rs = ps.executeQuery();
            while (rs.next()) {
                fechaFin = (rs.getDate("fechaFin"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo obtenere la fecha fin");
            System.out.println(e.getMessage());
        }
        return fechaFin;
    }


    public static JPanel getPanelReservas(){
        if (instance == null){
            instance = new Reservas();
        }

        return instance;


    }
    public void llenarTiposEspacios() {
    // Verificar que el elemento seleccionado no sea null
    Object selectedItem = jComboEdificios.getSelectedItem();
    if (selectedItem != null) {
        jComboTipoEspacio.removeAllItems();
        // Usar el valor seleccionado para obtener los tipos
        List<String> tipos = obtenerTiposPorEdificio(selectedItem.toString());
        for (String tipo : tipos) {
            jComboTipoEspacio.addItem(tipo);
        }
    }
}

    public void llenarEspacios() {
    // Verificar que los elementos seleccionados no sean null
    Object selectedEdificio = jComboEdificios.getSelectedItem();
    Object selectedTipoEspacio = jComboTipoEspacio.getSelectedItem();
    
    if (selectedEdificio != null && selectedTipoEspacio != null) {
        jComboEspacio.removeAllItems();
        List<String> espacios = obtenerEspacios(selectedEdificio.toString(), selectedTipoEspacio.toString());
        for (String espacio : espacios) {
            jComboEspacio.addItem(espacio);
        }
    }
}


    public void llenarEdificios() {
        //jComboEspacio.removeAllItems();
        List<String> edificios = obtenerEdificios();
        for (String edificio : edificios) {
            jComboEdificios.addItem(edificio);
        }
    }
    
    

    public List<String> obtenerTiposPorEdificio(String edificio) {
        List<String> tipos = new ArrayList<>();
        String sql = "SELECT DISTINCT te.nombreTipoEspacio " +
                     "FROM espacios e " +
                     "JOIN tipoespacio te ON e.idTipoEspacioPertenece = te.idTipoEspacio " +
                     "JOIN edificios ed ON e.idEdificioPertenece = ed.idEdificio " +
                     "WHERE ed.nombreEdificio = ?";

        ResultSet rs;


        try ( PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)){
            ps.setString(1, edificio);
            rs = ps.executeQuery();
            while (rs.next()) {
                tipos.add(rs.getString("nombreTipoEspacio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron obtener los tipos de espacios del edificio. ERROR: ");
            System.out.println(e.getMessage());

        }

        return tipos;
    }

    public List<String> obtenerEspacios(String edificio, String tipo) {
        List<String> espacios = new ArrayList<>();
        String sql = "SELECT e.idEspacio, e.nombreEspacio, e.capacidad, "
                + "te.nombreTipoEspacio, ed.nombreEdificio "
                + "FROM espacios e "
                + "JOIN tipoespacio te ON e.idTipoEspacioPertenece = te.idTipoEspacio "
                + "JOIN edificios ed ON e.idEdificioPertenece = ed.idEdificio "
                + "WHERE te.nombreTipoEspacio = ? "
                + "AND ed.nombreEdificio = ? ";
        ResultSet rs;
        try ( PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)){
            ps.setString(1, tipo);
            ps.setString(2, edificio);
            rs = ps.executeQuery();
            while (rs.next()) {
                espacios.add(rs.getString("nombreEspacio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron obtener los espacios. ERROR: ");
            System.out.println(e.getMessage());

        }

        return espacios;
    }

    public List<String> obtenerEdificios() {
        List<String> edificios = new ArrayList<>();
        String sql = "SELECT nombreEdificio FROM edificios";
        ResultSet rs;
        try (PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)) {

            rs = ps.executeQuery();
            while (rs.next()) {
                edificios.add(rs.getString("nombreEdificio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron obtener los espacios. ERROR: ");
            System.out.println(e.getMessage());

        }

        return edificios;
    }

    public List<CRUDHorarios> obtenerDatosDeLaBaseDeDatos(String nombreEspacio) {
    List<CRUDHorarios> horarios = new ArrayList<>();
    try {
        String sql = "SELECT horarios.Fecha_HoraInicio, "
                   + "horarios.Fecha_HoraFin, "
                   + "horarios.Disponible, "
                   + "espacios.nombreEspacio, "
                   + "materias.nombreMateria, "
                   + "carreras.nombreCarrera, "
                   + "peridosacademicos.nombrePeriodo, "
                   + "responsables.nombre1Responsable, "
                   + "responsables.apellido1Responsable "
                   + "FROM horarios "
                   + "JOIN espacios ON horarios.idEspacioImparte = espacios.idEspacio "
                   + "JOIN materias ON horarios.idMateriaPertenece = materias.idMateria "
                   + "JOIN peridosacademicos ON horarios.idPeriodoPertenece = peridosacademicos.idPeriodo "
                   + "JOIN responsables ON materias.idResponsablePertenece = responsables.idResponsable "
                   + "JOIN carreras ON materias.idCarreraPertenece = carreras.idCarrera "
                   + "JOIN niveles ON carreras.idNivelPertenece = niveles.idNivel "
                   + "WHERE espacios.nombreEspacio = ?";

        ResultSet rs;
        PreparedStatement ps;

        ps = crudReservas.getConnection().prepareStatement(sql);
        ps.setString(1, nombreEspacio);
        rs = ps.executeQuery();

        while (rs.next()) {
            CRUDHorarios horario = new CRUDHorarios();
            horario.setFecha_HoraInicio(rs.getTimestamp("Fecha_HoraInicio"));
            horario.setFecha_HoraFin(rs.getTimestamp("Fecha_HoraFin"));
            horario.setDisponible(rs.getBoolean("Disponible"));
            horario.setNombreEspacio(rs.getString("nombreEspacio"));
            horario.setNombreMateria(rs.getString("nombreMateria"));
            //horario.setNombreCarrera(rs.getString("nombreCarrera"));
            horario.setNombrePeriodo(rs.getString("nombrePeriodo"));
            horario.setNombre1Responsable(rs.getString("nombre1Responsable"));
            horario.setApellido1Responsable(rs.getString("apellido1Responsable"));

            horarios.add(horario);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "No se pudieron obtener los datos de la base de datos. ERROR: " + e.getMessage());
    }

    return horarios;
}

    
    private int calcularDiaDeLaSemana(Timestamp timestamp) {
        // Calcula el día de la semana (Lunes = 1, Martes = 2, ..., Sábado = 6)
        // Aquí asumimos que los días están almacenados en el objeto Timestamp
        return timestamp.toLocalDateTime().getDayOfWeek().getValue();
    }

    public void actualizarTabla(String nombreEspacio) {
    List<CRUDHorarios> horarios = obtenerDatosDeLaBaseDeDatos(nombreEspacio);

    DefaultTableModel modelo = new DefaultTableModel();
    TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
    this.jTableReservas.setRowSorter(ordenarTabla);

    // Columnas para los días de la semana
    modelo.addColumn("Hora");
    modelo.addColumn("Lunes");
    modelo.addColumn("Martes");
    modelo.addColumn("Miércoles");
    modelo.addColumn("Jueves");
    modelo.addColumn("Viernes");
    modelo.addColumn("Sábado");

    // Agregar filas para cada hora del día (de 7 AM a 8 PM)
    for (int hora = 7; hora <= 19; hora++) {
        Object[] fila = new Object[7];
        String ceroInicial = hora <= 9 ? "0" : "";
        fila[0] = ceroInicial + hora + ":00 - " + (hora + 1) + ":00";
        modelo.addRow(fila);
    }

    // Marcar descanso de 13:00 a 14:00
    int filaDescanso = 13 - 7;  // 13:00 - 7:00 = 6
    for (int columna = 1; columna <= 6; columna++) {
        modelo.setValueAt("DESCANSO", filaDescanso, columna);
    }

        for (CRUDHorarios horario : horarios) {
            // Calcular la fila y la columna donde se debe insertar el horario
            int columna = calcularDiaDeLaSemana(horario.getFechaHoraInicio());
            int filaInicio = horario.getFechaHoraInicio().toLocalDateTime().getHour() - 7;
            int filaFin = horario.getFechaHoraFin().toLocalDateTime().getHour() - 7 - 1;  // Ajustar filaFin para no ocupar una hora extra

            // Validar si filaInicio o filaFin están fuera de los límites de la tabla
            if (filaInicio < 0 || filaInicio > 13 || filaFin < 0 || filaFin > 13) {
                System.err.println("Horario fuera de los límites: " + horario);
                continue;
            }

            String nombreResponsable = horario.getNombre1Responsable() != null ? horario.getNombre1Responsable() : "";
            String apellidoResponsable = horario.getApellido1Responsable() != null ? horario.getApellido1Responsable() : "";
            String nombreCompletoResponsable = nombreResponsable + " " + apellidoResponsable;

            for (int i = filaInicio; i <= filaFin; i++) {
                // Asegurarse de no sobrescribir el descanso
                if (i != filaDescanso) {
                    modelo.setValueAt(nombreCompletoResponsable + "\n" + horario.getNombreMateria() + "\n" + horario.getNombreEspacio(), i, columna);
                }
            }
        }

        this.jTableReservas.setModel(modelo);

// Aplicar el renderer personalizado a cada columna
        CustomTableCellRenderer renderer = new CustomTableCellRenderer();
        for (int i = 0; i < jTableReservas.getColumnCount(); i++) {
            jTableReservas.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

    }



public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setText(value != null ? value.toString() : "");

        if (isSelected) {
            textArea.setBackground(table.getSelectionBackground());
            textArea.setForeground(table.getSelectionForeground());
        } else {
            if (column == 0) {
                textArea.setBackground(Color.LIGHT_GRAY);
            } else {
                if (value != null && value.toString().equals("DESCANSO")) {
                    textArea.setBackground(Color.YELLOW);
                } else if (value != null && !value.toString().isEmpty()) {
                    textArea.setBackground(Color.RED);
                } else {
                    textArea.setBackground(Color.GREEN);
                }
            }
            textArea.setForeground(table.getForeground());
        }

        textArea.setOpaque(true);

        // Dibujar bordes negros alrededor de la celda
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return textArea;
    }
}

 


private void mostrarDialogoDeReservas() {
        try {
            List<String> tiposResponsables = databaseHandler.getTiposResponsables();
            JComboBox<String> tipoComboBox = new JComboBox<>(tiposResponsables.toArray(new String[0]));
            JComboBox<String> responsablesComboBox = new JComboBox<>();
            JTextArea motivoArea = new JTextArea(5, 20);

            tipoComboBox.addActionListener(ev -> {
                try {
                    String selectedTipo = (String) tipoComboBox.getSelectedItem();
                    List<String> responsables = databaseHandler.getResponsablesPorTipo(selectedTipo);
                    responsablesComboBox.removeAllItems();
                    for (String responsable : responsables) {
                        responsablesComboBox.addItem(responsable);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            // Tipo de Responsable
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(new JLabel("Tipo de Responsable:"), gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(tipoComboBox, gbc);

            // Responsable
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(new JLabel("Responsable:"), gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(responsablesComboBox, gbc);

            // Motivo
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            panel.add(new JLabel("Motivo:"), gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            JScrollPane scrollPane = new JScrollPane(motivoArea);
            panel.add(scrollPane, gbc);

            int result = JOptionPane.showConfirmDialog(null, panel, "Seleccione Responsable y Motivo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String selectedTipo = (String) tipoComboBox.getSelectedItem();
                String selectedResponsable = (String) responsablesComboBox.getSelectedItem();
                String motivo = motivoArea.getText();
                System.out.println("Tipo seleccionado: " + selectedTipo);
                System.out.println("Responsable seleccionado: " + selectedResponsable);
                System.out.println("Motivo: " + motivo);
            } else {
                System.out.println("Diálogo cancelado");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReservas = new javax.swing.JTable();
        jComboEdificios = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboTipoEspacio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboEspacio = new javax.swing.JComboBox<>();
        Calendario = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jLabel1.setText("Reservas");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendario.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jTableReservas.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableReservas.setRowHeight(90);
        jTableReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableReservas);

        jComboEdificios.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboEdificios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboEdificiosItemStateChanged(evt);
            }
        });
        jComboEdificios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboEdificiosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboEdificiosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboEdificiosMouseReleased(evt);
            }
        });
        jComboEdificios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEdificiosActionPerformed(evt);
            }
        });
        jComboEdificios.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboEdificiosPropertyChange(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel4.setText("Edificios: ");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel5.setText("Tipo de espacio:");

        jComboTipoEspacio.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboTipoEspacio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboTipoEspacioMouseClicked(evt);
            }
        });
        jComboTipoEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTipoEspacioActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel6.setText("Espacio:");

        jComboEspacio.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboEspacio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboEspacioMouseClicked(evt);
            }
        });
        jComboEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEspacioActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar2.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboEdificios, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboTipoEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboTipoEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jComboEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboEdificios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1264, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReservasMouseClicked
        int row = jTableReservas.rowAtPoint(evt.getPoint());
        int col = jTableReservas.columnAtPoint(evt.getPoint());

        // Verificar si el clic se realizó en una celda válida
        if (row >= 0 && col >= 0) {
            // Obtener el valor de la celda
            Object value = jTableReservas.getValueAt(row, col);

            // Verificar si el valor de la celda no es nulo y es una cadena de texto
            if (value == null || "RESERVA".equals(value.toString())) {
                mostrarDialogoDeReservas();
            }
        }


    }//GEN-LAST:event_jTableReservasMouseClicked

    private void jComboEdificiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEdificiosActionPerformed
        
        if (jComboEdificios.getSelectedItem() != null) {
        llenarTiposEspacios();
    }    
        
    }//GEN-LAST:event_jComboEdificiosActionPerformed

    private void jComboTipoEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTipoEspacioActionPerformed
        llenarEspacios();
    }//GEN-LAST:event_jComboTipoEspacioActionPerformed

    private void jComboEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEspacioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboEspacioActionPerformed

    private void jComboTipoEspacioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboTipoEspacioMouseClicked


    }//GEN-LAST:event_jComboTipoEspacioMouseClicked

    private void jComboEspacioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboEspacioMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboEspacioMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        actualizarTabla(jComboEspacio.getSelectedItem().toString());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jComboEdificiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboEdificiosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboEdificiosMouseClicked

    private void jComboEdificiosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboEdificiosMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboEdificiosMouseReleased

    private void jComboEdificiosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboEdificiosItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboEdificiosItemStateChanged

    private void jComboEdificiosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboEdificiosMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboEdificiosMousePressed

    private void jComboEdificiosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboEdificiosPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboEdificiosPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Calendario;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> jComboEdificios;
    private javax.swing.JComboBox<String> jComboEspacio;
    private javax.swing.JComboBox<String> jComboTipoEspacio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReservas;
    // End of variables declaration//GEN-END:variables
}
