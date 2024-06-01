
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

public class CRUDResponsables {
    
    int idResponsable;
    String nombreResponsable; 
    String apellidoResponsable;
    String cedulaResponsable;
    int tipoResponsable;

    public String getApellidoResponsable() {
        return apellidoResponsable;
    }

    public void setApellidoResponsable(String apellidoResponsable) {
        this.apellidoResponsable = apellidoResponsable;
    }

    public String getCedulaResponsable() {
        return cedulaResponsable;
    }

    public void setCedulaResponsable(String cedulaResponsable) {
        this.cedulaResponsable = cedulaResponsable;
    }

    public int getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(int tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

    public PreparedStatement getPs2() {
        return ps2;
    }

    public void setPs2(PreparedStatement ps2) {
        this.ps2 = ps2;
    }

    public ResultSet getRs2() {
        return rs2;
    }

    public void setRs2(ResultSet rs2) {
        this.rs2 = rs2;
    }
    
    

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
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
    
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private PreparedStatement ps2;
    private ResultSet rs2;
    
    public CRUDResponsables(){
        this.conexion = new Conexion();
    }

    public void insertarResponsable(JTextField paraNombre, JTextField paraApellido,
            JTextField paraCedula, JComboBox<String> paraTipo) {

        setNombreResponsable(paraNombre.getText());
        setApellidoResponsable(paraApellido.getText());
        setCedulaResponsable(paraCedula.getText());

        // Obtener el tipo seleccionado del JComboBox
        String tipoSeleccionado = (String) paraTipo.getSelectedItem();

        // Obtener el ID del tipo responsable correspondiente al tipo seleccionado
        int tipoResponsableId = getIdTipoResponsable(tipoSeleccionado);
        setTipoResponsable(tipoResponsableId);

        try {
            String sql = "INSERT INTO Responsables (nombre1Responsable, "
                    + "apellido1Responsable, cedulaResponsable, idTipoResponsablePer) "
                    + "VALUES (?,?,?,?);";

            this.ps = this.conexion.getConnection().prepareStatement(sql);

            this.ps.setString(1, getNombreResponsable());
            this.ps.setString(2, getApellidoResponsable());
            this.ps.setString(3, getCedulaResponsable());
            this.ps.setInt(4, getTipoResponsable());

            this.ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se guardaron los datos");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se guardaron los datos. ERROR");
        }
    }

    public void mostrarResponsables(JTable parametrosCompletosED, String buscar) {
        try {
            String sql = "select * from responsables WHERE nombre1Responsable LIKE '%" + buscar + "%'";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.rs = this.ps.executeQuery();
            
            
            
            DefaultTableModel modelo = new DefaultTableModel();
            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
            parametrosCompletosED.setRowSorter(OrdenarTabla);
            
            modelo.addColumn("Id");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Cédula");
            modelo.addColumn("Tipo de Responsable");
            
            parametrosCompletosED.setModel(modelo);
   
                       while (this.rs.next()) {
                String[] datos = new String[modelo.getColumnCount()];
                datos[0] = String.valueOf(this.rs.getInt(1));
                datos[1] = this.rs.getString(2);
                datos[2] = this.rs.getString(4);
                datos[3] = this.rs.getString(6);
                // Asumiendo que idTipoResponsable está en la tabla responsables
                int idTipoResponsable = rs.getInt(7);
                String sql2 = "SELECT nombreTipoResponsable FROM tiporesponsables WHERE idTipoResponsables = ?;";
                ps2 = this.conexion.getConnection().prepareStatement(sql2);
                ps2.setInt(1, idTipoResponsable);
                rs2 = ps2.executeQuery();

                if (rs2.next()) {
                    datos[4] = rs2.getString(1);
                } else {
                    datos[4] = "Desconocido";
                }

                modelo.addRow(datos);
            }

            parametrosCompletosED.setModel(modelo);

        } catch (Exception e) {
            System.out.println(e);

            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos ERROR");
        }
    }
    
    
    
    public void SelecionarResponsables(JTable parametrosED, JTextField paraId, 
        JTextField paraNombre, JTextField paraApellido, JTextField paraCedula,
        JComboBox<String> paraTipo) {
    try {
        int fila = parametrosED.getSelectedRow();
        
        if (fila >= 0) {
            // Obteniendo los valores de la fila seleccionada y verificando si son null o vacíos
            Object idObj = parametrosED.getValueAt(fila, 0);
            paraId.setText(idObj != null ? idObj.toString() : "");

            Object nombreObj = parametrosED.getValueAt(fila, 1);
            paraNombre.setText(nombreObj != null ? nombreObj.toString() : "");

            Object apellidoObj = parametrosED.getValueAt(fila, 2);
            paraApellido.setText(apellidoObj != null ? apellidoObj.toString() : "");

            Object cedulaObj = parametrosED.getValueAt(fila, 3);
            paraCedula.setText(cedulaObj != null ? cedulaObj.toString() : "");

            Object tipoObj = parametrosED.getValueAt(fila, 4);
            if (tipoObj != null) {
                String tipo = tipoObj.toString();
                
                // Busca el índice en el JComboBox y selecciona el item correspondiente
                for (int i = 0; i < paraTipo.getItemCount(); i++) {
                    if (paraTipo.getItemAt(i).equals(tipo)) {
                        paraTipo.setSelectedIndex(i);
                        break;
                    }
                }
            } else {
                paraTipo.setSelectedIndex(-1); // Si el valor es null, selecciona ningún elemento
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
        JOptionPane.showMessageDialog(null, "Error de selección");
    }
}

    
    public void updateResponsables(JTextField paraId,JTextField paraNombre, 
            JTextField paraApellido, JTextField paraCedula, JComboBox paraTipo){
        
        setIdResponsable(Integer.parseInt(paraId.getText()));
        setNombreResponsable(paraNombre.getText());
        setApellidoResponsable(paraApellido.getText());
        setCedulaResponsable(paraCedula.getText());
        String tipoSeleccionado = (String) paraTipo.getSelectedItem();
        int tipoResponsableId = getIdTipoResponsable(tipoSeleccionado);
        setTipoResponsable(tipoResponsableId);
        
        try {
    String sql = "UPDATE responsables SET nombre1Responsable = ?, "
            + "apellido1Responsable = ?, cedulaResponsable = ?, "
            + "idTipoResponsablePer = ? WHERE idResponsable = ?;";
    this.ps = this.conexion.getConnection().prepareStatement(sql);

    this.ps.setString(1, getNombreResponsable());
    this.ps.setString(2, getApellidoResponsable());
    this.ps.setString(3, getCedulaResponsable()); 
    this.ps.setInt(4, getTipoResponsable()); 
    this.ps.setInt(5, getIdResponsable());

    int rowsUpdated = this.ps.executeUpdate();

    if (rowsUpdated > 0) {
        JOptionPane.showMessageDialog(null, "Datos modificados");
    } else {
        JOptionPane.showMessageDialog(null, "No se han encontrado datos para modificar");
    }

} catch (Exception e) {
    System.out.println(e);
    JOptionPane.showMessageDialog(null, "No se han modificado los datos");
}
    }
    
    public void deleteEdidicios(JTextField paraId){
        
        setIdResponsable(Integer.parseInt(paraId.getText()));
        try{
            String sql = "DELETE FROM  responsables WHERE idResponsable = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setInt(1,getIdResponsable());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos eliminados");
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han eliminado los datos");
        }
    }
    
    public int getIdTipoResponsable(String tipo) {
    String sql = "SELECT idTipoResponsables FROM tiporesponsables WHERE nombreTipoResponsable = ?";
    try (PreparedStatement ps = this.conexion.getConnection().prepareStatement(sql)) {
        ps.setString(1, tipo);
        try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("idTipoResponsables");
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return -1;
}


    public void llenarComboBox(JComboBox<String> comboBox) {
        
        try {
            String sql = "SELECT nombreTipoResponsable FROM tiporesponsables"; // Ajusta la consulta según tus necesidades
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            try (ResultSet resultSet = this.ps.executeQuery(sql)) {
                while (resultSet.next()) {
                    String item = resultSet.getString("nombreTipoResponsable");
                    comboBox.addItem(item);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean datosVacios(JTextField paraNombre, 
            JTextField paraApellido, JTextField paraCedula) {
        if (paraNombre.getText().equals("")) {
            return true;
        }
        if (paraApellido.getText().equals("")) {
            return true;
        }
        if (paraCedula.getText().equals("")) {
            return true;
        }
        return false;
    }
    
}
