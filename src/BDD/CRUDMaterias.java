package BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CRUDMaterias {
    
    int idMateria;
    String nombreMateria;  
    int idCarrera;
    int idResponsable;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }
    
    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public void insertarMateria(JTextField paraNombreM, JComboBox<String> paraCarreras, JComboBox<String> paraResponsable) {
        setNombreMateria(paraNombreM.getText());
        String carreraSeleccionada = (String) paraCarreras.getSelectedItem();
        String responsableSeleccionado = (String) paraResponsable.getSelectedItem();
        
        String nombreCarrera = carreraSeleccionada.split(" - ")[0];
        //String nombreResponsable = carreraSeleccionada.split(" ")[0];

        int idCarrera = getIdCarreras(nombreCarrera);
        setIdCarrera(idCarrera);
        
        int idResponsable = getIdResponsables(responsableSeleccionado);
        setIdResponsable(idResponsable);

        try {
            String sql = "INSERT INTO materias (nombreMateria, idCarreraPertenece, idResponsablePertenece) VALUES (?, ?, ?);";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setString(1, getNombreMateria());
            this.ps.setInt(2, getIdCarrera());
            this.ps.setInt(3, getIdResponsable());
            this.ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se guardaron los datos");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se guardaron los datos. ERROR");
        }
    }

    public void mostrarMateria(JTable parametrosCompletosED, String buscar) {
        try {
                   String sql = "SELECT m.idMateria, m.nombreMateria, " +
                     "CONCAT(c.nombreCarrera, ' ', n.nombreNivel, ' ', n.paralelo) AS CarreraCompleta, " +
                     "CONCAT(r.nombre1Responsable, ' ', r.apellido1Responsable) AS nombreCompleto " +
                     "FROM materias m " +
                     "JOIN carreras c ON m.idCarreraPertenece = c.idCarrera " +
                     "JOIN niveles n ON c.idNivelPertenece = n.idNivel " +
                     "JOIN responsables r ON m.idResponsablePertenece = r.idResponsable " +
                     "WHERE m.nombreMateria LIKE ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setString(1, "%" + buscar + "%");
            this.rs = this.ps.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel();
            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);
            parametrosCompletosED.setRowSorter(OrdenarTabla);

            modelo.addColumn("Id");
            modelo.addColumn("Nombre");
            modelo.addColumn("Carrera");
            modelo.addColumn("Responsable");

            parametrosCompletosED.setModel(modelo);

            while (this.rs.next()) {
                String[] datos = new String[4];
                datos[0] = String.valueOf(this.rs.getInt("idMateria"));
                datos[1] = this.rs.getString("nombreMateria");
                datos[2] = this.rs.getString("CarreraCompleta");
                datos[3] = this.rs.getString("nombreCompleto");
                modelo.addRow(datos);
            }

            parametrosCompletosED.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos ERROR");
        }
    }
    
        public void SelecionarMaterias(JTable parametrosED, JTextField paraId, 
        JTextField paraNombre, JComboBox<String> paraCarreras, JComboBox<String> paraResponsable) {
        
        try {
        int fila = parametrosED.getSelectedRow();
        
        if (fila >= 0) {
            // Obteniendo los valores de la fila seleccionada y verificando si son null o vacíos
            Object idObj = parametrosED.getValueAt(fila, 0);
            paraId.setText(idObj != null ? idObj.toString() : "");

            Object nombreObj = parametrosED.getValueAt(fila, 1);
            paraNombre.setText(nombreObj != null ? nombreObj.toString() : "");

            
            Object carreraObj = parametrosED.getValueAt(fila, 2);
            if (carreraObj != null) {
                String carrera= carreraObj.toString();
                
                // Busca el índice en el JComboBox y selecciona el item correspondiente
                for (int i = 0; i < paraCarreras.getItemCount(); i++) {
                    if (paraCarreras.getItemAt(i).equals(carrera)) {
                        paraCarreras.setSelectedIndex(i);
                        break;
                    }
                }
            } else {
                paraCarreras.setSelectedIndex(-1); // Si el valor es null, selecciona ningún elemento
            }
            
            Object responsablesObj = parametrosED.getValueAt(fila, 3);
            if (responsablesObj != null) {
                String responsables= responsablesObj.toString();
                
                // Busca el índice en el JComboBox y selecciona el item correspondiente
                for (int i = 0; i < paraResponsable.getItemCount(); i++) {
                    if (paraResponsable.getItemAt(i).equals(responsables)) {
                        paraResponsable.setSelectedIndex(i);
                        break;
                    }
                }
            } else {
                paraResponsable.setSelectedIndex(-1); // Si el valor es null, selecciona ningún elemento
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Error de selección");
    }
}
        
    public void updateMaterias(JTextField paraId, JTextField paraNombreM, JComboBox<String> paraCarreras, JComboBox<String> paraResponsable) {
    // Establecer los valores de las variables de instancia
    setIdMateria(Integer.parseInt(paraId.getText()));
    setNombreMateria(paraNombreM.getText());
    
    // Obtener el id de la carrera seleccionada
    String carreraSeleccionada = (String) paraCarreras.getSelectedItem();
    String nombreCarrera = carreraSeleccionada.split(" - ")[0];
    int idCarrera = getIdCarreras(nombreCarrera);
    setIdCarrera(idCarrera);

    // Obtener el id del responsable seleccionado
    String responsableSeleccionado = (String) paraResponsable.getSelectedItem();
    int idResponsable = getIdResponsables(responsableSeleccionado);
    setIdResponsable(idResponsable);

    try {
        // Preparar la consulta SQL de actualización
        String sql = "UPDATE materias SET nombreMateria = ?, idCarreraPertenece = ?, idResponsablePertenece = ? WHERE idMateria = ?;";
        this.ps = this.conexion.getConnection().prepareStatement(sql);

        // Establecer los valores en el PreparedStatement
        this.ps.setString(1, getNombreMateria());
        this.ps.setInt(2, getIdCarrera());
        this.ps.setInt(3, getIdResponsable());
        this.ps.setInt(4, getIdMateria());

        // Ejecutar la actualización
        int rowsUpdated = this.ps.executeUpdate();

        // Verificar si la actualización fue exitosa
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Datos modificados");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron registros para modificar");
        }
    } catch (SQLException e) {
        // Manejo de excepciones y mensajes de error detallados
        System.out.println("Error al actualizar la materia: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "No se han modificado los datos. Error: " + e.getMessage());
    }
}

    
   public void deleteEspacios(JTextField paraId){
        
        setIdCarrera(Integer.parseInt(paraId.getText()));
        try{
            String sql = "DELETE FROM  materias WHERE idMateria = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setInt(1,getIdCarrera());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos eliminados");
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han eliminar los datos");
        }
    }

    public int getIdCarreras(String carrera) {
        String sql = "SELECT idCarrera FROM carreras WHERE nombreCarrera = ?";
        try (PreparedStatement ps = this.conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, carrera);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idCarrera");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public int getIdResponsables(String responsable) {
        String sql = "SELECT idResponsable FROM responsables WHERE CONCAT(nombre1Responsable, ' ', apellido1Responsable) = ?";
        try (PreparedStatement ps = this.conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, responsable);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idResponsable");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void llenarComboBoxCarreras(JComboBox<String> comboBox) {
    try {
        String sql = "SELECT c.nombreCarrera, n.nombreNivel, n.paralelo " +
                     "FROM carreras c " +
                     "JOIN niveles n ON c.idNivelPertenece = n.idNivel";  // Ajusta según los nombres de tus columnas y tablas
        this.ps = this.conexion.getConnection().prepareStatement(sql);
        try (ResultSet resultSet = this.ps.executeQuery()) {
            while (resultSet.next()) {
                String nombreCarrera = resultSet.getString("nombreCarrera");
                String nombreNivel = resultSet.getString("nombreNivel");
                String paralelo = resultSet.getString("paralelo");
                String item = nombreCarrera + " - " + nombreNivel + " - " + paralelo;
                comboBox.addItem(item);
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}


    public void llenarComboBoxResponsables(JComboBox<String> comboBox) {
        try {
            String sql = "SELECT CONCAT(nombre1Responsable, ' ', apellido1Responsable) AS nombreCompleto FROM responsables";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            try (ResultSet resultSet = this.ps.executeQuery()) {
                while (resultSet.next()) {
                    String item = resultSet.getString("nombreCompleto");
                    comboBox.addItem(item);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean materiaExistente(JTextField paraNombre) {
    try {
        String sql = "SELECT nombreMateria FROM materias WHERE nombreMateria = ?";
        this.ps = this.conexion.getConnection().prepareStatement(sql);
        
       
        String nombre = paraNombre.getText();
        this.ps.setString(1, nombre);
        
        // Ejecutar la consulta
        try (ResultSet resultSet = this.ps.executeQuery()) {
            
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
        return false; // En caso de error, retornar false (o puedes manejarlo de otra manera)
    }
}

    
    public boolean datosVacios(JTextField paraNombre) {
        if (paraNombre.getText().equals("")) {
            return true;
        }
        
        return false;
    }
    
    
}
