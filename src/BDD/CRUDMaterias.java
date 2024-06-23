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

        int idCarrera = getIdCarreras(carreraSeleccionada);
        setIdCarrera(idCarrera);
        
        int idResponsable = getIdResponsables(responsableSeleccionado);
        setIdResponsable(idResponsable);

        try {
            String sql = "INSERT INTO Materias (nombreMateria, idCarreraPertenece, idResponsablePertenece) VALUES (?, ?, ?);";
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
                     "WHERE c.nombreCarrera LIKE ?";
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
                String responsables= carreraObj.toString();
                
                // Busca el índice en el JComboBox y selecciona el item correspondiente
                for (int i = 0; i < paraResponsable.getItemCount(); i++) {
                    if (paraResponsable.getItemAt(i).equals(responsables)) {
                        paraResponsable.setSelectedIndex(i);
                        break;
                    }
                }
            } else {
                paraCarreras.setSelectedIndex(-1); // Si el valor es null, selecciona ningún elemento
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Error de selección");
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
        String sql = "SELECT idResponsable FROM responsables WHERE nombre1Responsable = ?";
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
    
    
}
