/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventanas;

import BDD.CRUDEReservas;
import BDD.CRUDHorarios;
import DatabaseHandler.DatabaseHandler;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int idEspacio;
    

    public Reservas() {
        initComponents();
        databaseHandler = new DatabaseHandler();
        crudReservas = new CRUDEReservas();
        llenarEdificios();
        limitarCalendario();
        this.jTableReservas.setVisible(false);
        //llenarTiposEspacios();
        //llenarEspacios();
    }
    
    
    
    public void limitarCalendario() {
        Date fechaActual = new Date();        
        this.calendario.setMinSelectableDate(fechaActual);
        this.calendario.setMaxSelectableDate(obtenerFinPeriodo());
        this.calendario.setDate(fechaActual);
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

    public List<CRUDHorarios> obtenerDatosDeLaBaseDeDatos(String nombreEspacio, String fechaInicio, String fechaFin) {
    List<CRUDHorarios> horarios = new ArrayList<>();
    try {
        String sql = "SELECT h.Fecha_HoraInicio, "
                   + "h.Fecha_HoraFin, "
                   + "h.Disponible, "
                   + "h.idEspacioImparte, "
                   + "h.idMateriaPertenece, "
                   + "h.idPeriodoPertenece, "
                   + "e.nombreEspacio, "
                   + "m.nombreMateria, "
                   + "COALESCE(res_r.nombre1Responsable, r.nombre1Responsable) AS nombre1Responsable, "
                   + "COALESCE(res_r.apellido1Responsable, r.apellido1Responsable) AS apellido1Responsable, "
                   + "res.idResponsableReserva, "
                   + "res.MotivoReserva "
                   + "FROM horarios h "
                   + "JOIN espacios e ON h.idEspacioImparte = e.idEspacio "
                   + "LEFT JOIN materias m ON h.idMateriaPertenece = m.idMateria "
                   + "LEFT JOIN reservas res ON h.idHorario = res.idHorarioReserva "
                   + "LEFT JOIN responsables r ON m.idResponsablePertenece = r.idResponsable "
                   + "LEFT JOIN responsables res_r ON res.idResponsableReserva = res_r.idResponsable "
                   + "WHERE e.nombreEspacio = ? AND h.Fecha_HoraInicio >= ? AND h.Fecha_HoraFin <= ?";

        ResultSet rs;
        PreparedStatement ps;

        ps = crudReservas.getConnection().prepareStatement(sql);
        ps.setString(1, nombreEspacio);
        ps.setString(2, fechaInicio);
        ps.setString(3, fechaFin);
        rs = ps.executeQuery();

        while (rs.next()) {
            CRUDHorarios horario = new CRUDHorarios();
            horario.setFecha_HoraInicio(rs.getTimestamp("Fecha_HoraInicio"));
            horario.setFecha_HoraFin(rs.getTimestamp("Fecha_HoraFin"));
            horario.setDisponible(rs.getBoolean("Disponible"));
            horario.setIdEspacio(rs.getInt("idEspacioImparte"));
            horario.setIdMateria(rs.getInt("idMateriaPertenece"));
            horario.setIdPeriodoPertenece(rs.getInt("idPeriodoPertenece"));
            horario.setNombreEspacio(rs.getString("nombreEspacio"));
            horario.setNombreMateria(rs.getString("nombreMateria"));
            horario.setNombre1Responsable(rs.getString("nombre1Responsable"));
            horario.setApellido1Responsable(rs.getString("apellido1Responsable"));
            horario.setIdResponsableReserva(rs.getInt("idResponsableReserva"));
            horario.setMotivoReserva(rs.getString("MotivoReserva"));
            this.idEspacio = rs.getInt("idEspacioImparte");

            horarios.add(horario);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "No se pudieron obtener los datos de la base de datos. ERROR: " + e.getMessage());
    }

    return horarios;
}


public static String[] getWeekBoundaries(Date date) {
        LocalDate localDate = LocalDate.ofInstant(date.toInstant(), java.time.ZoneId.systemDefault());
        LocalDate startOfWeek = localDate.with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = startOfWeek.plus(6, ChronoUnit.DAYS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startOfWeekString = startOfWeek.atStartOfDay().format(formatter);
        String endOfWeekString = endOfWeek.atStartOfDay().format(formatter);

        return new String[]{startOfWeekString, endOfWeekString};
    }


    
    

    
    private int calcularDiaDeLaSemana(Timestamp timestamp) {
        // Calcula el día de la semana (Lunes = 1, Martes = 2, ..., Sábado = 6)
        // Aquí asumimos que los días están almacenados en el objeto Timestamp
        return timestamp.toLocalDateTime().getDayOfWeek().getValue();
    }

    public void actualizarTabla(String nombreEspacio, String fechaInicio, String fechaFin) {
    List<CRUDHorarios> horarios = obtenerDatosDeLaBaseDeDatos(nombreEspacio, fechaInicio, fechaFin);

    DefaultTableModel tablaHorario = new DefaultTableModel();
    TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(tablaHorario);
    this.jTableReservas.setRowSorter(ordenarTabla);

    // Columnas para los días de la semana
    tablaHorario.addColumn("Hora");
    tablaHorario.addColumn("Lunes");
    tablaHorario.addColumn("Martes");
    tablaHorario.addColumn("Miércoles");
    tablaHorario.addColumn("Jueves");
    tablaHorario.addColumn("Viernes");
    tablaHorario.addColumn("Sábado");

    // Agregar filas para cada hora del día (de 7 AM a 8 PM)
    for (int hora = 7; hora <= 19; hora++) {
        Object[] fila = new Object[7];
        String ceroInicial = hora <= 9 ? "0" : "";
        fila[0] = ceroInicial + hora + ":00 - " + (hora + 1) + ":00";
        tablaHorario.addRow(fila);
    }

    // Marcar descanso de 13:00 a 14:00
    int filaDescanso = 13 - 7;  // 13:00 - 7:00 = 6
    for (int columna = 1; columna <= 6; columna++) {
        tablaHorario.setValueAt("DESCANSO", filaDescanso, columna);
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

        // Información de la reserva
        String idResponsableReserva = horario.getIdResponsableReserva() > 0 ? String.valueOf(horario.getIdResponsableReserva()) : "N/A";
        String motivoReserva = horario.getMotivoReserva() != null ? horario.getMotivoReserva() : "N/A";

        for (int i = filaInicio; i <= filaFin; i++) {
            // Asegurarse de no sobrescribir el descanso
            if (i != filaDescanso && horario.getNombreMateria()!=null) {
                
                String contenidoCelda = nombreCompletoResponsable + "\n" + horario.getNombreMateria() + "\n" + horario.getNombreEspacio();
                if (!idResponsableReserva.equals("N/A")) {
                    contenidoCelda += "\n" + idResponsableReserva + "\n" + motivoReserva;
                }
                tablaHorario.setValueAt(contenidoCelda, i, columna);
            }
            if (i != filaDescanso && horario.getNombreMateria()==null) {
                String contenidoCelda = "RESERVA \n"+nombreCompletoResponsable + "\n" + horario.getNombreEspacio();
                if (!idResponsableReserva.equals("N/A")) {
                    contenidoCelda += "\n" + motivoReserva;
                }
                tablaHorario.setValueAt(contenidoCelda, i, columna);
            }
        }
    }

    this.jTableReservas.setModel(tablaHorario);

    // Aplicar el renderer personalizado a cada columna
    CustomTableCellRenderer renderer = new CustomTableCellRenderer();
    for (int i = 0; i < jTableReservas.getColumnCount(); i++) {
        jTableReservas.getColumnModel().getColumn(i).setCellRenderer(renderer);
    }
}
    
    private String obtenerHoraInicio(int fila) {
        String fechaCelda;
        int horaInicio = fila + 7;
        fechaCelda = String.valueOf(horaInicio + ":00:00");
        return fechaCelda;
    }
    
    private String obtenerHoraFin(int fila) {
        String fechaCelda;
        int horaInicio = fila + 8;
        fechaCelda = String.valueOf(horaInicio + ":00:00");
        return fechaCelda;
    }
    
    private String obtenerFechaExactaInicio(int fila, int columna) {
        String fechaIni = (obtenerFecha(columna)+" "+obtenerHoraInicio(fila));
        return fechaIni;
    }
    
    private String obtenerFechaExactaFin(int fila, int columna) {
        String fechaFin = (obtenerFecha(columna)+" "+obtenerHoraFin(fila));
        return fechaFin;
    }
    
    private String obtenerFecha(int columna) {
        //System.out.println(getStartOfWeek(calendario.getDate()));
        Date nuevaFecha = getStartOfWeek(calendario.getDate());
        nuevaFecha.setDate(nuevaFecha.getDate()+columna-1);
        //JOptionPane.showConfirmDialog(null, nuevaFecha);
        String fechaCelda = darFormatoFecha(nuevaFecha);
        return fechaCelda;
    }
    public static String darFormatoFecha(Date date) {
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormato = localDate.format(formatter);
        return fechaFormato;
    }
    
    public static Date getStartOfWeek(Date date) {
    LocalDate localDate = LocalDate.ofInstant(date.toInstant(), java.time.ZoneId.systemDefault());
    LocalDate startOfWeek = localDate.with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));

    Instant instant = startOfWeek.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant();
    return Date.from(instant);
}
    
//    private String obtenerFechaHoraInicio(int fila, int columna) {
//        int horaInicio = fila + 7;
//        // Asegúrate de que el nombre de la columna corresponde a una fecha adecuada
//        String diaSemana = jTableReservas.getColumnName(columna);
//        // Aquí debes mapear el día de la semana a una fecha específica. Ejemplo:
//        String fecha = "2024-06-27"; // Reemplaza esto con la lógica adecuada
//        LocalDateTime fechaHoraInicio = LocalDateTime.parse(fecha + "T" + String.format("%02d", horaInicio) + ":00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        return fechaHoraInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
//
//    private String obtenerFechaHoraFin(int fila, int columna) {
//        int horaFin = fila + 8;
//        // Asegúrate de que el nombre de la columna corresponde a una fecha adecuada
//        String diaSemana = jTableReservas.getColumnName(columna);
//        // Aquí debes mapear el día de la semana a una fecha específica. Ejemplo:
//        String fecha = "2024-06-27"; // Reemplaza esto con la lógica adecuada
//        LocalDateTime fechaHoraFin = LocalDateTime.parse(fecha + "T" + String.format("%02d", horaFin) + ":00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        return fechaHoraFin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }



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
                }else if (value != null && value.toString().contains("RESERVA")){
                    textArea.setBackground(Color.CYAN);
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

    public void crearReserva(int fila, int columna) {
        String[] datos = new String[2];
        datos = mostrarDialogoDeReservas();
        String id, motivo;
        id = datos[0];
        motivo = datos[1];
        if (id != null && motivo != null) {
//        JOptionPane.showMessageDialog(null, obtenerFechaExactaInicio(fila, columna)+" --- "+obtenerFechaExactaFin(fila, columna));
//        JOptionPane.showMessageDialog(null, id + " " + motivo);
        boolean exito = insertarHorarioYReserva(obtenerFechaExactaInicio(fila, columna), obtenerFechaExactaFin(fila, columna), motivo, Integer.parseInt(id));
            if (exito) {
                JOptionPane.showMessageDialog(null, "Reserva creada exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Algo salió mal");
            }
        }
        
//        String[] horarioIniFin = obtenerFechaExactaCelda(columna);
//        
////        private String[] obtenerFechaCelda(int columna) {
////        String[] horarioIniFin = getWeekBoundaries(this.calendario.getDate());
////        return horarioIniFin;
////    }
//        
//                JOptionPane.showMessageDialog(null, obtenerHoraInicio(fila)+" Y "+obtenerHoraFin(fila));
//                //JOptionPane.showMessageDialog(null, columna +" col "+ horarioIniFin[0]+ "Y" +horarioIniFin[1]);
//                //JOptionPane.showMessageDialog(null, obtenerFecha(columna));
    }
    

    public String[] obtenerDatosDeCelda() {
    String[] datosDeCelda = new String[2];
    return datosDeCelda;
    }
    
    public boolean insertarHorarioYReserva(String fechaHoraInicio, String fechaHoraFin, String motivoReserva, int idResponsableReserva) {
    String sqlInsertHorario = "INSERT INTO horarios (Fecha_HoraInicio, Fecha_HoraFin, idEspacioImparte) VALUES (?, ?, ?)";
    String sqlGetLastHorarioId = "SELECT LAST_INSERT_ID()";
    String sqlInsertReserva = "INSERT INTO reservas (Fecha_HoraInicio, Fecha_HoraFin, MotivoReserva, idResponsableReserva, idHorarioReserva) VALUES (?, ?, ?, ?, ?)";
    ResultSet rs;
    PreparedStatement ps;
    boolean success = false;

    try {
        // Convertir Strings a Timestamps
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestampInicio = new Timestamp(dateFormat.parse(fechaHoraInicio).getTime());
        Timestamp timestampFin = new Timestamp(dateFormat.parse(fechaHoraFin).getTime());

        // Iniciar la transacción
        Connection conn = crudReservas.getConnection();
        conn.setAutoCommit(false);
        
        // Insertar nuevo horario
        ps = conn.prepareStatement(sqlInsertHorario);
        ps.setTimestamp(1, timestampInicio);
        ps.setTimestamp(2, timestampFin);
        ps.setInt(3, idEspacio);
        ps.executeUpdate();
        
        // Obtener el ID del último horario insertado
        ps = conn.prepareStatement(sqlGetLastHorarioId);
        rs = ps.executeQuery();
        
        int lastHorarioId = 0;
        if (rs.next()) {
            lastHorarioId = rs.getInt(1);
        }
        
        // Insertar nueva reserva vinculada al horario recién creado
        ps = conn.prepareStatement(sqlInsertReserva);
        ps.setTimestamp(1, timestampInicio);
        ps.setTimestamp(2, timestampFin);
        ps.setString(3, motivoReserva);
        ps.setInt(4, idResponsableReserva);
        ps.setInt(5, lastHorarioId);
        ps.executeUpdate();
        
        // Confirmar la transacción
        conn.commit();
        success = true;
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "No se pudieron insertar los datos. ERROR: " + e.getMessage());
        try {
            // Revertir la transacción en caso de error
            crudReservas.getConnection().rollback();
        } catch (Exception rollbackEx) {
            rollbackEx.printStackTrace();
        }
    } finally {
        try {
            crudReservas.getConnection().setAutoCommit(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    return success;
}
    
    public boolean eliminarHorarioYReserva(String fechaHoraInicio, String fechaHoraFin, int idEspacio) {
    String sqlDeleteReserva = "DELETE FROM reservas WHERE idHorarioReserva = (SELECT idHorario FROM horarios WHERE Fecha_HoraInicio = ? AND Fecha_HoraFin = ? AND idEspacioImparte = ?)";
    String sqlDeleteHorario = "DELETE FROM horarios WHERE Fecha_HoraInicio = ? AND Fecha_HoraFin = ? AND idEspacioImparte = ?";
    PreparedStatement ps;
    boolean success = false;

    try {
        // Convertir Strings a Timestamps
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestampInicio = new Timestamp(dateFormat.parse(fechaHoraInicio).getTime());
        Timestamp timestampFin = new Timestamp(dateFormat.parse(fechaHoraFin).getTime());

        // Iniciar la transacción
        Connection conn = crudReservas.getConnection();
        conn.setAutoCommit(false);
        
        // Eliminar la reserva
        ps = conn.prepareStatement(sqlDeleteReserva);
        ps.setTimestamp(1, timestampInicio);
        ps.setTimestamp(2, timestampFin);
        ps.setInt(3, idEspacio);
        ps.executeUpdate();

        // Eliminar el horario
        ps = conn.prepareStatement(sqlDeleteHorario);
        ps.setTimestamp(1, timestampInicio);
        ps.setTimestamp(2, timestampFin);
        ps.setInt(3, idEspacio);
        ps.executeUpdate();

        // Confirmar la transacción
        conn.commit();
        success = true;
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "No se pudieron eliminar los datos. ERROR: " + e.getMessage());
        try {
            // Revertir la transacción en caso de error
            crudReservas.getConnection().rollback();
        } catch (Exception rollbackEx) {
            rollbackEx.printStackTrace();
        }
    } finally {
        try {
            crudReservas.getConnection().setAutoCommit(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    return success;
}
    
    
//    public boolean celdaReservable(int fila, int columna){
//        Date fechaActual = new Date();
//        String fechaReserva = obtenerFechaExactaInicio(fila, columna);
//        if (fechaActual.getHours()<fechaActual) {
//            return true;
//        }
//        return false;
//    }
    
    public boolean esCeldaReservable(int fila, int columna) {
    Date fechaActual = new Date();
    String fechaReservaStr = obtenerFechaExactaInicio(fila, columna);
    
    // Parsear la fechaReservaStr a Date
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
        Date fechaReserva = dateFormat.parse(fechaReservaStr);
        
        // Comparar las fechas
        if (fechaReserva.after(fechaActual)) {
            return true; // La fecha de reserva está en el futuro
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al parsear la fecha de reserva. ERROR: " + e.getMessage());
    }
    
    return false; // La fecha de reserva ya pasó
}
    
    public boolean esFechaDisponible(int fila, int columna) {

        try {
            
            String fechaReservaStr = obtenerFechaExactaInicio(fila, columna);
            
            // Parsear la fechaReservaStr a Date
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaReserva = dateTimeFormat.parse(fechaReservaStr);
            String fechaReservaFormatoCorto = dateFormat.format(fechaReserva);
            
            String sql = "SELECT COUNT(*) FROM fechasnodisponibles WHERE fechaInicio <= ? AND fechaFin >= ?";
            ResultSet rs;
            
            try (PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)) {
                ps.setString(1, fechaReservaFormatoCorto);
                ps.setString(2, fechaReservaFormatoCorto);
                
                rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // La fecha no está disponible
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudieron obtener los datos. ERROR: ");
                System.out.println(e.getMessage());
                
            }
            
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE,null, ex);

        }
        return false;
    }








//    private void guardarReserva(){
//VALUES ('2024-06-22 19:00:00', '2024-06-22 20:00:00', 'Reunión de profesores', 1, 2);
//
//try {
//            String sql = "INSERT INTO reservas (Fecha_HoraInicio, "
//                    + "Fecha_HoraFin, MotivoReserva, idResponsableReserva, idHorarioReserva) "
//                    + "VALUES (?,?,?,?,?);";
//
//            this.ps = this.conexion.getConnection().prepareStatement(sql);
//
//            this.ps.setString(1, getNombreResponsable());
//            this.ps.setString(2, getApellidoResponsable());
//            this.ps.setString(3, getCedulaResponsable());
//            this.ps.setInt(4, getTipoResponsable());
//
//            this.ps.executeUpdate();
//
//            JOptionPane.showMessageDialog(null, "Se guardaron los datos");
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(null, "No se guardaron los datos. ERROR");
//        }
//    }

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
        calendario = new com.toedter.calendar.JDateChooser();
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
                                .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            if (value == null) {
                
                if (esCeldaReservable(row, col)) {
                    
                    if (esFechaDisponible(row, col)) {

                    crearReserva(row, col);

                    if (jComboEdificios.getSelectedItem() != null && jComboTipoEspacio.getSelectedItem() != null
                            && jComboEspacio.getSelectedItem() != null) {

                        String[] horarioIniFin = getWeekBoundaries(this.calendario.getDate());
                        //JOptionPane.showMessageDialog(null, "inicio: " + horaIniFin[0] + "fin: " + horaIniFin[1]);

                        //JOptionPane.showMessageDialog(null, this.calendario.getDate());
                        actualizarTabla(jComboEspacio.getSelectedItem().toString(), horarioIniFin[0], horarioIniFin[1]);

                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione todos los campos");
                    }
                    
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede reservar en esta fecha");
                    }
                    } else {
                    JOptionPane.showMessageDialog(null, "No se puede reservar en esta fecha, porque la hora ya pasó");
                }
                
            } else if (value.toString().contains("RESERVA")) {
                //String nombre = value.toString().
                //JOptionPane.showMessageDialog(null, value);
                //JOptionPane.showMessageDialog(null, obtenerFechaExactaInicio(row, col) + "---" + obtenerFechaExactaFin(row, col));
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta reserva?");

                if (respuesta == JOptionPane.YES_OPTION) {

                    eliminarHorarioYReserva(obtenerFechaExactaInicio(row, col), obtenerFechaExactaFin(row, col), idEspacio);
                    //JOptionPane.showMessageDialog(null, obtenerFechaExactaInicio(row, col)+" --- "+obtenerFechaExactaFin(row, col)+" --- "+idEspacio);
                    
                    String[] horarioIniFin = getWeekBoundaries(this.calendario.getDate());
                    actualizarTabla(jComboEspacio.getSelectedItem().toString(), horarioIniFin[0], horarioIniFin[1]);
                    JOptionPane.showMessageDialog(null, "Se elimió correctamente");

                }
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
        this.jTableReservas.setVisible(true);
        if (jComboEdificios.getSelectedItem() != null && jComboTipoEspacio.getSelectedItem() != null
                && jComboEspacio.getSelectedItem() != null) {

            String[] horarioIniFin = getWeekBoundaries(this.calendario.getDate());
            //JOptionPane.showMessageDialog(null, "inicio: " + horaIniFin[0] + "fin: " + horaIniFin[1]);

            //JOptionPane.showMessageDialog(null, this.calendario.getDate());
            actualizarTabla(jComboEspacio.getSelectedItem().toString(), horarioIniFin[0], horarioIniFin[1]);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione todos los campos");
        }
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
    private javax.swing.JButton btnBuscar;
    private com.toedter.calendar.JDateChooser calendario;
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

    private String[] mostrarDialogoDeReservas() {
    String[] idYMotivo = new String[2];
    try {
        List<String> tiposResponsables = databaseHandler.getTiposResponsables();
        JComboBox<String> tipoComboBox = new JComboBox<>(tiposResponsables.toArray(new String[0]));
        JComboBox<String> responsablesComboBox = new JComboBox<>();
        JTextArea motivoArea = new JTextArea(5, 20);

        tipoComboBox.addActionListener(ev -> {
            try {
                String selectedTipo = (String) tipoComboBox.getSelectedItem();
                Map<String, Integer> responsables = databaseHandler.getResponsablesPorTipo(selectedTipo);
                responsablesComboBox.removeAllItems();
                for (String responsable : responsables.keySet()) {
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

            if (selectedTipo == null || selectedTipo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de responsable.", "Error", JOptionPane.ERROR_MESSAGE);
                return mostrarDialogoDeReservas(); // Volver a mostrar el diálogo
            }

            if (selectedResponsable == null || selectedResponsable.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un responsable.", "Error", JOptionPane.ERROR_MESSAGE);
                return mostrarDialogoDeReservas(); // Volver a mostrar el diálogo
            }

            if (motivo == null || motivo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un motivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return mostrarDialogoDeReservas(); // Volver a mostrar el diálogo
            }

            System.out.println("Tipo seleccionado: " + selectedTipo);
            System.out.println("Responsable seleccionado: " + selectedResponsable);
            System.out.println("Motivo: " + motivo);
            idYMotivo[0] = String.valueOf(databaseHandler.getResponsablesPorTipo(selectedTipo).get(selectedResponsable));
            idYMotivo[1] = motivo;
            return idYMotivo;
        } else {
            System.out.println("Diálogo cancelado");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return idYMotivo;
}


    
}
