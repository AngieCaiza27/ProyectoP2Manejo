package BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JComboBox;


public class CRUDHorarios {
    
    private Timestamp Fecha_HoraInicio;
    private Timestamp Fecha_HoraFin;
    private boolean Disponible;
    private String nombreEspacio;
    private String nombreMateria;
    private String nombrePeriodo;
    private String nombre1Responsable;
    private String apellido1Responsable;

    // Getters y setters...

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

    public CRUDHorarios() {
        this.conexion = new Conexion();
    }
    public Connection getConnection() {
    return this.conexion.getConnection(); // Devuelve el objeto de conexión
}

    
    

    public List<CRUDHorarios> obtenerDatosDeLaBaseDeDatos(String buscar, String carrera, String nivel) {
        List<CRUDHorarios> horarios = new ArrayList<>();
        try {
            String sql = "SELECT horarios.Fecha_HoraInicio, " +
                         "horarios.Fecha_HoraFin, " +
                         "horarios.Disponible, " +
                         "espacios.nombreEspacio, " +
                         "materias.nombreMateria, " +
                         "carreras.nombreCarrera, " +  
                         "peridosacademicos.nombrePeriodo, " +
                         "responsables.nombre1Responsable, " +
                         "responsables.apellido1Responsable " + 
                         "FROM horarios " +
                         "JOIN espacios ON horarios.idEspacioImparte = espacios.idEspacio " +
                         "JOIN materias ON horarios.idMateriaPertenece = materias.idMateria " +
                         "JOIN peridosacademicos ON horarios.idPeriodoPertenece = peridosacademicos.idPeriodo " +
                         "JOIN responsables ON materias.idResponsablePertenece = responsables.idResponsable " +
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
                CRUDHorarios horario = new CRUDHorarios();
                horario.setFecha_HoraInicio(this.rs.getTimestamp("Fecha_HoraInicio"));
                horario.setFecha_HoraFin(this.rs.getTimestamp("Fecha_HoraFin"));
                horario.setDisponible(this.rs.getBoolean("Disponible"));
                horario.setNombreEspacio(this.rs.getString("nombreEspacio"));
                horario.setNombreMateria(this.rs.getString("nombreMateria"));
                horario.setNombrePeriodo(this.rs.getString("nombrePeriodo"));
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

    public void mostrarHorariosEnTabla(JTable tablaHorarios, String buscar, String carrera, String nivel) {
        List<CRUDHorarios> horarios = obtenerDatosDeLaBaseDeDatos(buscar, carrera, nivel);

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
        String ceroInicial=hora<=9 ? "0":"";
        fila[0] = ceroInicial+hora + ":00";
        modelo.addRow(fila);
        }

        for (CRUDHorarios horario : horarios) {
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
    
   

       
    public class Espacio {
    private int id;
    private String nombre;

    public Espacio(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre; // Esto es lo que se mostrará en el JComboBox
    }
}

public void llenarComboBoxEspacios(JComboBox<CRUDHorarios.Espacio> comboBox) {
    try {
        String sql = "SELECT idEspacio, nombreEspacio FROM espacios"; // Ajusta la consulta según tus necesidades
        this.ps = this.conexion.getConnection().prepareStatement(sql);
        try (ResultSet resultSet = this.ps.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("idEspacio");
                String nombre = resultSet.getString("nombreEspacio");
                Espacio espacio = new Espacio(id, nombre);
                comboBox.addItem(espacio);
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}
public class Materia {
    private int id;
    private String nombre;

    public Materia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre; // Esto es lo que se mostrará en el JComboBox
    }
}

 public void llenarComboBoxMaterias(JComboBox<CRUDHorarios.Materia> comboBox) {
    try {
        String sql = "SELECT idMateria,nombreMateria FROM materias"; // Ajusta la consulta según tus necesidades
        this.ps = this.conexion.getConnection().prepareStatement(sql);
        try (ResultSet resultSet = this.ps.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("idMateria");
                String nombre = resultSet.getString("nombreMateria");
                Materia materia = new Materia(id, nombre);
                comboBox.addItem(materia);
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}
 
 public class Responsable {
    private int id;
    private String nombre;

    public Responsable(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre; // Esto es lo que se mostrará en el JComboBox
    }
}

 public void llenarComboBoxResponsables(JComboBox<CRUDHorarios.Responsable> comboBox) {
    try {
        String sql = "SELECT idResponsable,CONCAT(nombre1Responsable,' ', apellido1Responsable) AS nombreResponsable FROM responsables;"; // Ajusta la consulta según tus necesidades
        this.ps = this.conexion.getConnection().prepareStatement(sql);
        try (ResultSet resultSet = this.ps.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("idResponsable");
                String nombreResponsable = resultSet.getString("nombreResponsable");
                Responsable responsable = new Responsable(id, nombreResponsable);
                comboBox.addItem(responsable);
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}
 
 

public void deleteHorario(int idHorario) {
    try {
        String sql = "DELETE FROM horarios WHERE idHorario = ?";
        this.ps = this.conexion.getConnection().prepareStatement(sql);
        this.ps.setInt(1, idHorario);
        this.ps.executeUpdate();

        JOptionPane.showMessageDialog(null, "Datos eliminados");
    } catch (Exception e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(null, "No se han eliminado los datos");
    }
}

    
    
    
}
