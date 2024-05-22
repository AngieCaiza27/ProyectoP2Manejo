
package BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CRUDResponsables {
    
    int idResponsable;
    String nombreResponsable; 

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
            
            parametrosCompletosED.setModel(modelo);
            
            
            while(this.rs.next()){
                String[] datos = new String[2];
                datos[0] = String.valueOf(this.rs.getInt(1));
                datos[1] = this.rs.getString(2);
                
               modelo.addRow(datos);
            }
           
            parametrosCompletosED.setModel(modelo);
            
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos ERROR");
        }
    }
    
    
    
    public void SelecionarResponsables(JTable parametrosED , JTextField paraId, JTextField paraNombre ){
        try{
            int fila = parametrosED.getSelectedRow();
            
            if(fila >= 0 ){
                paraId.setText((parametrosED.getValueAt(fila, 0).toString()));
                paraNombre.setText((parametrosED.getValueAt(fila, 1).toString()));
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
                
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error de seleccion");
        }
    }
    
    public void updateResponsables(JTextField paraId,JTextField paraNombre ){
        
        setIdResponsable(Integer.parseInt(paraId.getText()));
        setNombreResponsable(paraNombre.getText());
        
        try{
            String sql = "update responsables set responsables.nombreResponsable = ? WHERE Responsables.idResponsable = ?;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            
            
            
            this.ps.setString(1,getNombreResponsable());
            this.ps.setInt(2,getIdResponsable());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos modificados");
            
        }catch(Exception e){
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
            
            JOptionPane.showMessageDialog(null, "No se han eliminar los datos");
        }
    }
    
    
    
    
}
