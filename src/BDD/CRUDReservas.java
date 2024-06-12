package BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.Connection;

public class CRUDReservas {
    
    private Timestamp Fecha_HoraInicio;
    private Timestamp Fecha_HoraFin;
    private boolean Disponible;
    private String nombreEspacio;
    private String nombreMateria;
    private String nombrePeriodo;
    private String nombre1Responsable;
    private String apellido1Responsable;
    private String motivoReserva;

    // Getters y setters...

    public String getMotivoReserva() {
        return motivoReserva;
    }

    public void setMotivoReserva(String motivoReserva) {
        this.motivoReserva = motivoReserva;
    }

    
    public Timestamp getFechaHoraInicio() {
        return Fecha_HoraInicio;
    }

    public void setFecha_HoraInicio(Timestamp Fecha_HoraInicio) {
        this.Fecha_HoraInicio = Fecha_HoraInicio;
    }

    public Timestamp getFechaHoraFin() {
        return Fecha_HoraFin;
    }

    public void setFecha_HoraFin(Timestamp Fecha_HoraFin) {
        this.Fecha_HoraFin = Fecha_HoraFin;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean Disponible) {
        this.Disponible = Disponible;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public void setNombreEspacio(String nombreEspacio) {
        this.nombreEspacio = nombreEspacio;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public String getNombre1Responsable() {
        return nombre1Responsable;
    }

    public void setNombre1Responsable(String nombre1Responsable) {
        this.nombre1Responsable = nombre1Responsable;
    }

    public String getApellido1Responsable() {
        return apellido1Responsable;
    }

    public void setApellido1Responsable(String apellido1Responsable) {
        this.apellido1Responsable = apellido1Responsable;
    }

    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public CRUDReservas() {
        this.conexion = new Conexion();
    }
    
    public Connection getConnection() {
        return this.conexion.getConnection(); // Devuelve el objeto de conexión
    }

    public List<CRUDReservas> obtenerReservasDeLaBaseDeDatos(String buscar, String carrera, String nivel) {
    List<CRUDReservas> reservas = new ArrayList<>();
    try {
        String sql = "SELECT reservas.Fecha_HoraInicio, " +
                     "reservas.Fecha_HoraFin, " +
                     "reservas.MotivoReserva, " +
                     "espacios.nombreEspacio, " +
                     "materias.nombreMateria, " +
                     "carreras.nombreCarrera, " +  
                     "peridosacademicos.nombrePeriodo, " +
                     "responsables.nombre1Responsable, " +
                     "responsables.apellido1Responsable " + 
                     "FROM reservas " +
                     "JOIN horarios ON reservas.idHorarioReserva = horarios.idHorario " +
                     "JOIN espacios ON horarios.idEspacioImparte = espacios.idEspacio " +
                     "JOIN materias ON horarios.idMateriaPertenece = materias.idMateria " +
                     "JOIN peridosacademicos ON horarios.idPeriodoPertenece = peridosacademicos.idPeriodo " +
                     "JOIN responsables ON reservas.idResponsableReserva = responsables.idResponsable " +
                     "JOIN carreras ON materias.idCarreraPertenece = carreras.idCarrera " +
                     "JOIN niveles ON carreras.idNivelPertenece = niveles.idNivel " +
                     "WHERE materias.nombreMateria LIKE ? " +
                     "AND carreras.nombreCarrera = ? " +
                     "AND CONCAT(niveles.nombreNivel, ' - ', niveles.paralelo) = ?";
        
        this.ps = this.conexion.getConnection().prepareStatement(sql);
        this.ps.setString(1, "%" + buscar + "%");
        this.ps.setString(2, carrera);
        this.ps.setString(3, nivel);
        this.rs = this.ps.executeQuery();

        while (this.rs.next()) {
            CRUDReservas reserva = new CRUDReservas();
            reserva.setFecha_HoraInicio(this.rs.getTimestamp("Fecha_HoraInicio"));
            reserva.setFecha_HoraFin(this.rs.getTimestamp("Fecha_HoraFin"));
            reserva.setMotivoReserva(this.rs.getString("MotivoReserva"));
            reserva.setNombreEspacio(this.rs.getString("nombreEspacio"));
            reserva.setNombreMateria(this.rs.getString("nombreMateria"));
            reserva.setNombrePeriodo(this.rs.getString("nombrePeriodo"));
            reserva.setNombre1Responsable(this.rs.getString("nombre1Responsable"));
            reserva.setApellido1Responsable(this.rs.getString("apellido1Responsable"));

            reservas.add(reserva);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "No se pudieron obtener los datos de la base de datos. ERROR: " + e.getMessage());
    }

    return reservas;
}

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<CRUDReservas> obtenerReservasDeLaBaseDeDatos(String responsable, boolean disponible) {
    List<CRUDReservas> horarios = new ArrayList<>();
    try {
        String sql = "SELECT horarios.Fecha_HoraInicio, horarios.Fecha_HoraFin, horarios.Disponible, responsables.nombre1Responsable, responsables.apellido1Responsable " +
                     "FROM horarios " +
                     "JOIN responsables ON horarios.idResponsableImparte = responsables.idResponsable " +
                     "WHERE responsables.nombre1Responsable LIKE ? " +
                     "AND horarios.Disponible = ? ";

        this.ps = this.conexion.getConnection().prepareStatement(sql);
        this.ps.setString(1, "%" + responsable + "%");
        this.ps.setBoolean(2, disponible);
        this.rs = this.ps.executeQuery();

        while (this.rs.next()) {
            CRUDReservas horario = new CRUDReservas();
            horario.setFecha_HoraInicio(this.rs.getTimestamp("Fecha_HoraInicio"));
            horario.setFecha_HoraFin(this.rs.getTimestamp("Fecha_HoraFin"));
            horario.setDisponible(this.rs.getBoolean("Disponible"));
            horario.setNombre1Responsable(this.rs.getString("nombre1Responsable"));
            horario.setApellido1Responsable(this.rs.getString("apellido1Responsable"));

            horarios.add(horario);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "No se pudieron obtener los datos de la base de datos. ERROR: " + e.getMessage());
    }

    return horarios;
}


    public void mostrarReservasEnTabla(JTable tablaHorarios, String buscar, String carrera, String nivel) {
        List<CRUDReservas> horarios = obtenerReservasDeLaBaseDeDatos(buscar, carrera, nivel);

        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        tablaHorarios.setRowSorter(ordenarTabla);

        // Columnas para los días de la semana
        modelo.addColumn("Hora");
        modelo.addColumn("Lunes");
        modelo.addColumn("Martes");
        modelo.addColumn("Miércoles");
        modelo.addColumn("Jueves");
        modelo.addColumn("Viernes");
        modelo.addColumn("Sábado");

        // Agregar filas para cada hora del día (de 7 AM a 8 PM)
        for (int hora = 7; hora <= 20; hora++) {
            Object[] fila = new Object[7]; 
            fila[0] = hora + ":00";
            modelo.addRow(fila);
        }

        for (CRUDReservas horario : horarios) {
            // Calcular la fila y la columna donde se debe insertar el horario
            int columna = calcularDiaDeLaSemana(horario.getFechaHoraInicio());
            int filaInicio = horario.getFechaHoraInicio().toLocalDateTime().getHour() - 7;
            int filaFin = horario.getFechaHoraFin().toLocalDateTime().getHour() - 7;

            String nombreResponsable = horario.getNombre1Responsable() != null ? horario.getNombre1Responsable() : "";
            String apellidoResponsable = horario.getApellido1Responsable() != null ? horario.getApellido1Responsable() : "";
            String nombreCompletoResponsable = nombreResponsable + " " + apellidoResponsable;

            for (int i = filaInicio; i <= filaFin; i++) {
                modelo.setValueAt(nombreCompletoResponsable + "\n" + horario.getNombreMateria() + "\n" + horario.getNombreEspacio(), i, columna);
            }
        }

        tablaHorarios.setModel(modelo);

        // Establecer el renderizador personalizado para colorear las celdas y agregar borde negro
        TableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null && !value.toString().trim().isEmpty()) {
                    cell.setBackground(Color.RED);
                } else {
                    cell.setBackground(Color.GREEN);
                }
                ((JComponent) cell).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                return cell;
            }
        };

        // Aplicar el renderizador a todas las columnas excepto la de las horas
        for (int i = 1; i < modelo.getColumnCount(); i++) {
            tablaHorarios.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private int calcularDiaDeLaSemana(Timestamp timestamp) {
        // Calcula el día de la semana (Lunes = 1, Martes = 2, ..., Sábado = 6)
        // Aquí asumimos que los días están almacenados en el objeto Timestamp
        return timestamp.toLocalDateTime().getDayOfWeek().getValue();
    }

    public List<String> obtenerNivelesPorCarrera(String carrera) {
        List<String> niveles = new ArrayList<>();
        try {
            String sql = "SELECT CONCAT(niveles.nombreNivel, ' - ', niveles.paralelo) AS nivelCompleto " +
                         "FROM niveles " +
                         "JOIN carreras ON niveles.idNivel = carreras.idNivelPertenece " +
                         "WHERE carreras.nombreCarrera = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setString(1, carrera);
            this.rs = this.ps.executeQuery();

            while (this.rs.next()) {
                niveles.add(this.rs.getString("nivelCompleto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudieron obtener los niveles de la base de datos. ERROR: " + e.getMessage());
        } 
        return niveles;
    }
}
