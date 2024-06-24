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

public class CRUDEAulas {

    int idEspacio;
    String nombreEspacio;
    int capacidadEspacio;
    int edificioEspacio;
    int tipoEspacio;

    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public int getCapacidadEspacio() {
        return capacidadEspacio;
    }

    public void setCapacidadEspacio(int capacidadEspacio) {
        this.capacidadEspacio = capacidadEspacio;
    }

    public int getEdificioEspacio() {
        return edificioEspacio;
    }

    public void setEdificioEspacio(int edificioEspacio) {
        this.edificioEspacio = edificioEspacio;
    }

    public int getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipospacio(int tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public void setNombreEspacio(String nombreEspacio) {
        this.nombreEspacio = nombreEspacio;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
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

    public void insertarAulas(JTextField paraNombre, JTextField paraCapacidad, JComboBox<String> paraEdificio, JComboBox<String> paraTipo) {

        setNombreEspacio(paraNombre.getText());
        setCapacidadEspacio(Integer.parseInt(paraCapacidad.getText()));
        String edificioSeleccionado = (String) paraEdificio.getSelectedItem();
        String tipoSeleccionado = (String) paraTipo.getSelectedItem();

        int idEdificio = getIdEdificios(edificioSeleccionado);
        setEdificioEspacio(idEdificio);

        int idTipo = getIdTipo(tipoSeleccionado);
        setTipospacio(idTipo);

        try {
            String sql = "INSERT INTO espacios (nombreEspacio, capacidad, idEdificioPertenece, idTipoEspacioPertenece) VALUES (?, ?, ?, ?);";
            this.ps = this.conexion.getConnection().prepareStatement(sql);

            this.ps.setString(1, getNombreEspacio());
            this.ps.setInt(2, getCapacidadEspacio());
            this.ps.setInt(3, getEdificioEspacio());
            this.ps.setInt(4, getTipoEspacio());
            this.ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se guardaron los datos");

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "No se guardaron los datos ERROR");
        }
    }

    public void mostrarAulas(JTable parametrosCompletosED, String buscar) {
        try {
            String sql = "SELECT espacios.idEspacio, espacios.nombreEspacio, espacios.capacidad, edificios.nombreEdificio, tipoespacio.nombreTipoEspacio "
                    + "FROM espacios "
                    + "JOIN edificios ON espacios.idEdificioPertenece = edificios.idEdificio "
                    + "JOIN tipoespacio ON espacios.idTipoEspacioPertenece = tipoespacio.idTipoEspacio "
                    + "WHERE (espacios.idTipoEspacioPertenece = 1 OR espacios.idTipoEspacioPertenece = 2 OR espacios.idTipoEspacioPertenece = 3) "
                    + "AND (espacios.nombreEspacio LIKE ?)";

            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setString(1, "%" + buscar + "%");
            this.rs = this.ps.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel();
            TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
            parametrosCompletosED.setRowSorter(ordenarTabla);

            modelo.addColumn("Id");
            modelo.addColumn("Nombre");
            modelo.addColumn("Capacidad");
            modelo.addColumn("Edificio Ubicado");
            modelo.addColumn("Tipo Espacio");

            parametrosCompletosED.setModel(modelo);

            while (this.rs.next()) {
                String[] datos = new String[5];
                datos[0] = String.valueOf(this.rs.getInt("idEspacio"));
                datos[1] = this.rs.getString("nombreEspacio");
                datos[2] = this.rs.getString("capacidad");
                datos[3] = this.rs.getString("nombreEdificio");
                datos[4] = this.rs.getString("nombreTipoEspacio");

                modelo.addRow(datos);
            }

            parametrosCompletosED.setModel(modelo);

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos ERROR");
        }
    }

    public void SeleccionarEspacios(JTable parametrosED, JTextField paraId, JTextField paraNombre, JTextField paraCapacidad, JComboBox<String> paraEdificio, JComboBox<String> paraTipo) {
        try {
            int fila = parametrosED.getSelectedRow();

            if (fila >= 0) {
                Object idObj = parametrosED.getValueAt(fila, 0);
                paraId.setText(idObj != null ? idObj.toString() : "");

                Object nombreObj = parametrosED.getValueAt(fila, 1);
                paraNombre.setText(nombreObj != null ? nombreObj.toString() : "");

                Object idCapObj = parametrosED.getValueAt(fila, 2);
                paraCapacidad.setText(idCapObj != null ? idCapObj.toString() : "");

                Object edificioObj = parametrosED.getValueAt(fila, 3);
                if (edificioObj != null) {
                    String edificio = edificioObj.toString();

                    for (int i = 0; i < paraEdificio.getItemCount(); i++) {
                        if (paraEdificio.getItemAt(i).equals(edificio)) {
                            paraEdificio.setSelectedIndex(i);
                            break;
                        }
                    }
                } else {
                    paraEdificio.setSelectedIndex(-1);
                }

                Object tipoObj = parametrosED.getValueAt(fila, 4);
                if (tipoObj != null) {
                    String tipo = tipoObj.toString();

                    for (int i = 0; i < paraTipo.getItemCount(); i++) {
                        if (paraTipo.getItemAt(i).equals(tipo)) {
                            paraTipo.setSelectedIndex(i);
                            break;
                        }
                    }
                } else {
                    paraTipo.setSelectedIndex(-1);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de selección");
        }
    }

    public void updateAulas(JTextField paraId, JTextField paraNombre, JTextField paraCapacidad, JComboBox<String> paraEdificio, JComboBox<String> paraTipo) {

        setIdEspacio(Integer.parseInt(paraId.getText()));
        setNombreEspacio(paraNombre.getText());
        setCapacidadEspacio(Integer.parseInt(paraCapacidad.getText()));
        String edificioSeleccionado = paraEdificio.getSelectedItem().toString();
        //String nombreEdificio = edificioSeleccionado.split(" ")[0];
        int idEdificio = getIdEdificios(edificioSeleccionado);
        setEdificioEspacio(idEdificio);
        
        System.out.println("Edificio:"+  idEdificio );

        String tipoSeleccionado = (String) paraTipo.getSelectedItem();
        int idTipo = getIdTipo(tipoSeleccionado);
        setTipospacio(idTipo);
        System.out.println("Espacio:"+  idTipo );

        try {
            String sql = "UPDATE espacios SET nombreEspacio = ?, capacidad = ?, idEdificioPertenece = ?, idTipoEspacioPertenece = ? WHERE idEspacio = ?;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);

            this.ps.setString(1, getNombreEspacio());
            this.ps.setInt(2, getCapacidadEspacio());
            this.ps.setInt(3, getEdificioEspacio());
            this.ps.setInt(4, getTipoEspacio());
            this.ps.setInt(5, getIdEspacio());
            this.ps.executeUpdate();

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

    public void deleteAulas(JTextField paraId) {
        setIdEspacio(Integer.parseInt(paraId.getText()));

        try {
            String sql = "DELETE FROM espacios WHERE idEspacio = ?;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setInt(1, getIdEspacio());
            this.ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se eliminó el registro correctamente");

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "No se eliminó el registro ERROR");
        }
    }

    private int getIdEdificios(String edificio) {
        String sql = "SELECT idEdificio FROM edificios WHERE nombreEdificio = ?";
        try ( PreparedStatement ps = this.conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, edificio);
            try ( ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idEdificio");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    private int getIdTipo(String tipo) {
        String sql = "SELECT idTipoEspacio FROM tipoespacio WHERE nombreTipoEspacio= ?";
        try ( PreparedStatement ps = this.conexion.getConnection().prepareStatement(sql)) {
            ps.setString(1, tipo);
            try ( ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idTipoEspacio");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void llenarComboBoxEdificios(JComboBox<String> comboBox) {
        try {
            comboBox.removeAllItems(); // Limpiar el JComboBox antes de llenarlo
            String sql = "SELECT nombreEdificio FROM edificios;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            try (ResultSet resultSet = this.ps.executeQuery()) {
                while (resultSet.next()) {
                    String item = resultSet.getString("nombreEdificio");
                    comboBox.addItem(item);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void llenarComboBoxTipo(JComboBox<String> comboBox) {
        try {
            comboBox.removeAllItems(); // Limpiar el JComboBox antes de llenarlo
            String sql = "SELECT nombreTipoEspacio FROM tipoespacio;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            try (ResultSet resultSet = this.ps.executeQuery()) {
                while (resultSet.next()) {
                    String item = resultSet.getString("nombreTipoEspacio");
                    comboBox.addItem(item);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean espacioExistente(JTextField paraNombre) {
        try {
            String sql = "SELECT nombreEspacio FROM espacios WHERE nombreEspacio = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);

            // Obtener la cédula del JTextField y establecerla en la consulta
            String nombre = paraNombre.getText();
            this.ps.setString(1, nombre);

            // Ejecutar la consulta
            try ( ResultSet resultSet = this.ps.executeQuery()) {
                // Si hay resultados, la cédula existe
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
    
    public boolean datosVacios(JTextField paraNombre,JTextField paraCapacidad) {
        if (paraNombre.getText().equals("")) {
            return true;
        }
        
        if (paraCapacidad.getText().equals("")) {
            return true;
        }
        
        return false;
    }
}
