
package BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CRUDETipoResponsables {
    
    int idTipoResponsable;    
    String nombreTipoResponsable; 
    String descripcionTipoResponsable;

    public String getDescripcionTipoResponsable() {
        return descripcionTipoResponsable;
    }

    public void setDescripcionTipoResponsable(String descripcionTipoResponsable) {
        this.descripcionTipoResponsable = descripcionTipoResponsable;
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
    
    

    public int getIdTipoResponsable() {
        return idTipoResponsable;
    }

    public void setIdTipoResponsable(int idTipoResponsable) {
        this.idTipoResponsable = idTipoResponsable;
    }

    public String getNombreTipoResponsable() {
        return nombreTipoResponsable;
    }

    public void setNombreTipoResponsable(String nombreTipoResponsable) {
        this.nombreTipoResponsable = nombreTipoResponsable;
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
    
    public CRUDETipoResponsables(){
        this.conexion = new Conexion();
    }
    
    public void insertarResponsable(JTextField parametrosNombre ){
        
        setNombreTipoResponsable(parametrosNombre.getText());
        
        
        try{
            String sql = "insert into Responsables (nombreResponsable) values (?);";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
           
            this.ps.setString(1, getNombreTipoResponsable());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se guardaron los datos");
            
                        
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se guardaron los datos ERROR");
        }
    }
    
    public void mostrarTipoResponsables(JComboBox parametrosCompletosED ){
        
        try {
            String sql = "select * from tiporesponsables;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.rs = this.ps.executeQuery();
            
            while (rs.next()) {                
            parametrosCompletosED.addItem(rs.getString(2));   
            }
            
            
            
//            DefaultTableModel modelo = new DefaultTableModel();
//            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
//            parametrosCompletosED.setRowSorter(OrdenarTabla);
//            
//            modelo.addColumn("Id");
//            modelo.addColumn("Nombre");
//            modelo.addColumn("Apellido");
//            modelo.addColumn("Cédula");
//            modelo.addColumn("Tipo de Responsable");
//            
//            parametrosCompletosED.setModel(modelo);
//   
//                       while (this.rs.next()) {
//                String[] datos = new String[modelo.getColumnCount()];
//                datos[0] = String.valueOf(this.rs.getInt(1));
//                datos[1] = this.rs.getString(2);
//                datos[2] = this.rs.getString(4);
//                datos[3] = this.rs.getString(6);
//                // Asumiendo que idTipoResponsable está en la tabla responsables
//                int idTipoResponsable = rs.getInt(7);
//                String sql2 = "SELECT nombreTipoResponsable FROM tiporesponsables WHERE idTipoResponsables = ?;";
//                ps2 = this.conexion.getConnection().prepareStatement(sql2);
//                ps2.setInt(1, idTipoResponsable);
//                rs2 = ps2.executeQuery();
//
//                if (rs2.next()) {
//                    datos[4] = rs2.getString(1);
//                } else {
//                    datos[4] = "Desconocido";
//                }
//
//                modelo.addRow(datos);
//            }
//
//            parametrosCompletosED.setModel(modelo);
//
//        } catch (Exception e) {
//            System.out.println(e);
//
//            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos ERROR");
//        }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDETipoResponsables.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void updateResponsables(JTextField paraId,JTextField paraNombre ){
        
        setIdTipoResponsable(Integer.parseInt(paraId.getText()));
        setNombreTipoResponsable(paraNombre.getText());
        
        try{
            String sql = "update responsables set responsables.nombreResponsable = ? WHERE Responsables.idResponsable = ?;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            
            
            
            this.ps.setString(1,getNombreTipoResponsable());
            this.ps.setInt(2,getIdTipoResponsable());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos modificados");
            
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han modificado los datos");
        }
    }
    
    public void deleteEdidicios(JTextField paraId){
        
        setIdTipoResponsable(Integer.parseInt(paraId.getText()));
        try{
            String sql = "DELETE FROM  responsables WHERE idResponsable = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setInt(1,getIdTipoResponsable());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos eliminados");
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han eliminado los datos");
        }
    }
    
    
    
    
}
