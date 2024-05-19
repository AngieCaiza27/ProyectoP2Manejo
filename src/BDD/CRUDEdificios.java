
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

public class CRUDEdificios {
    
    int idEdificio;
    String nombreEdificio; 

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
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
    
    public CRUDEdificios(){
        this.conexion = new Conexion();
    }
    
    public void insertarEdificio(JTextField parametrosNombre ){
        
        setNombreEdificio(parametrosNombre.getText());
        
        
        try{
            String sql = "insert into Edificios (nombre_Edificio) values (?);";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
           
            this.ps.setString(1, getNombreEdificio());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se guardaron los datos");
            
                        
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se guardaron los datos ERROR");
        }
    }
    
    public void mostrarEdificios(JTable parametrosCompletosED ){
        try{
            String sql = "select * from Edificios;";
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
    
    
    
    public void SelecionarEdificios(JTable parametrosED , JTextField paraId, JTextField paraNombre ){
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
    
    public void updateEdificios(JTextField paraId,JTextField paraNombre ){
        
        setIdEdificio(Integer.parseInt(paraId.getText()));
        setNombreEdificio(paraNombre.getText());
        
        try{
            String sql = "update Edificios set Edificios.nombre_Edificio = ? WHERE Edificios.id_Edificio = ?;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            
            
            
            this.ps.setString(1,getNombreEdificio());
            this.ps.setInt(2,getIdEdificio());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos modificados");
            
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han modificado los datos");
        }
    }
    
    public void deleteEdidicios(JTextField paraId){
        
        setIdEdificio(Integer.parseInt(paraId.getText()));
        try{
            String sql = "DELETE FROM  Edificios WHERE id_Edificio = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setInt(1,getIdEdificio());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos eliminados");
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han eliminar los datos");
        }
    }
    
    public void comprarProductosCarrito(int codigoProducto, int cantidad){
        try{
            String sql = "DELETE FROM CARRITO WHERE ID_PER_CAR= ? AND COD_PRO_CAR = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setInt(2,codigoProducto);
            this.ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void alterarCantidadProductos(int codigoProducto, int cantidad, char operacion){
        try{
            String sql = "UPDATE PRODUCTOS SET UNI_DIS_PRO = UNI_DIS_PRO " + operacion + " ? " +
                    "WHERE COD_PRO = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setInt(1,cantidad);
            this.ps.setInt(2,codigoProducto);
            this.ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
}
