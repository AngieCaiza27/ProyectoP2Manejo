/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jake
 */
public class CRUDLaboratorios {
     int idEspacio;
    String nombreEspacio; 
    
    
    
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
    
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public CRUDLaboratorios(){
        this.conexion = new Conexion();
    }
    
    public void insertarLaboratorio(JTextField parametrosNombre ){
        
        setNombreEspacio(parametrosNombre.getText());
        
        
        try{
            String sql = "insert into espacio (nombreEspacio) values (?);";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
           
            this.ps.setString(1, getNombreEspacio());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se guardaron los datos");
            
                        
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se guardaron los datos ERROR");
        }
    }
    public void mostrarLaboratorio(JTable parametrosCompletosED ){
        try{
            String sql = "select espacios.idEspacio, espacios.nombreEspacio, espacios.capacidad,espacios.idEdificioPertenece,espacios.idTipoEspacioPertenece, edificios.nombreEdificio,tipoespacio.nombreTipoEspacio from espacios,edificios,tipoespacio where espacios.idEdificioPertenece=edificios.idEdificio and espacios.idTipoEspacioPertenece=tipoespacio.idTipoEspacio AND espacios.idTipoEspacioPertenece = 2";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.rs = this.ps.executeQuery();
            
            DefaultTableModel modelo = new DefaultTableModel();
            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
            parametrosCompletosED.setRowSorter(OrdenarTabla);
            
            modelo.addColumn("IdEspacio");
            modelo.addColumn("nombreEspacio");
            modelo.addColumn("capacidad");
            modelo.addColumn("nombreEdificio");
            modelo.addColumn("nombreTipoEspacio");
            
            parametrosCompletosED.setModel(modelo);
            
            
            while(this.rs.next()){
                String[] datos = new String[5];
                datos[0] = String.valueOf(this.rs.getInt(1));
                datos[1] = this.rs.getString(2);
                datos[2] = this.rs.getString(3);
                datos[3] = this.rs.getString(6);
                datos[4] = this.rs.getString(7);
                
               modelo.addRow(datos);
            }
           
            parametrosCompletosED.setModel(modelo);
            
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos ERROR");
        }
    }
    
    
    
    public void SelecionarLaboratorio(JTable parametrosED , JTextField paraId, JTextField paraNombre ){
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
    
    public void updateLaboratorio(JTextField paraId,JTextField paraNombre ){
        
        setIdEspacio(Integer.parseInt(paraId.getText()));
        setNombreEspacio(paraNombre.getText());
        
        try{
            String sql = "update espacios set espacios.nombreEspacio = ? WHERE espacios.idEspacio = ?;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            
            
            
            this.ps.setString(1,getNombreEspacio());
            this.ps.setInt(2,getIdEspacio());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos modificados");
            
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han modificado los datos");
        }
    }
    
    public void deleteLaboratorio(JTextField paraId){
        
        setIdEspacio(Integer.parseInt(paraId.getText()));
        try{
            String sql = "DELETE FROM  espacios WHERE idEspacio = ?";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.ps.setInt(1,getIdEspacio());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos eliminados");
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han eliminar los datos");
        }
    }
    
    
    
    
}

