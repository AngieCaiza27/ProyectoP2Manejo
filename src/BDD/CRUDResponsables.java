
package BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    String tipoResponsable;

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

    public String getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(String tipoResponsable) {
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
    
    public void insertarResponsable(JTextField parametrosNombre ){
        
        setNombreResponsable(parametrosNombre.getText());
        
        
        try{
            String sql = "insert into Responsables (nombreResponsable) values (?);";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
           
            this.ps.setString(1, getNombreResponsable());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se guardaron los datos");
            
                        
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se guardaron los datos ERROR");
        }
    }
    
    public void mostrarResponsables(JTable parametrosCompletosED ){
        try{
            String sql = "select * from responsables;";
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
    
    
    
    public void SelecionarResponsables(JTable parametrosED , JTextField paraId, 
            JTextField paraNombre, JTextField paraApellido, JTextField paraCedula,
            JComboBox paraTipo){
        try{
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
                paraTipo.setSelectedItem(tipoObj.toString());
            } else {
                paraTipo.setSelectedItem("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
                
        }catch(Exception e){
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, "Error de seleccion");
        }
    }
    
    public void updateResponsables(JTextField paraId,JTextField paraNombre, JTextField paraApellido, JComboBox paraTipoResponsable){
        
        setIdResponsable(Integer.parseInt(paraId.getText()));
        setNombreResponsable(paraNombre.getText());
        setApellidoResponsable(paraApellido.getText());
        //setTipoResponsable(paraTipoResponsable.get);
        
        try {
    String sql = "UPDATE responsables SET nombre1Responsable = ?, "
            + "apellido1Responsable = ?, idTipoResponsablePer = ? WHERE idResponsable = ?;";
    this.ps = this.conexion.getConnection().prepareStatement(sql);

    this.ps.setString(1, getNombreResponsable());
    this.ps.setString(2, getApellidoResponsable());
    this.ps.setInt(3, 1); // Suponiendo que el tipo de responsable es siempre 1

    // Necesitas agregar el ID del responsable en el cuarto parámetro
    int idResponsable = getIdResponsable(); // Suponiendo que tienes un método para obtener el ID
    this.ps.setInt(4, idResponsable);

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
    
    
    
    
}
