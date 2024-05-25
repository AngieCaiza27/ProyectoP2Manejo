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
public class CRUDEAulas {
    
    int idEspacio;
    String nombreEspacio; 
    int capacidadEspacio;
    int edificioEspacio;
    int tipoEspacio;

    public int getCapacidadEspacio() {
        return capacidadEspacio;
    }

    public void setCapacidadEspacio(int capacidadEspacio) {
        this.capacidadEspacio = capacidadEspacio;
    }

    public int getEdificioEspacio() {
        return edificioEspacio;
    }

    public void setEdificioEspacio( int edificioEspacio) {
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
    
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public CRUDEAulas(){
        this.conexion = new Conexion();
    }
    
    public void insertarAulas( JTextField paraNombre,JTextField paraCapacidad, JTextField paraEdificio, JTextField paraTipo ){
        
        setNombreEspacio(paraNombre.getText());
        setCapacidadEspacio(Integer.parseInt(paraCapacidad.getText()));
        setEdificioEspacio(Integer.parseInt(paraEdificio.getText()));
        setTipospacio(Integer.parseInt(paraTipo.getText()));
        
        try{
            String sql = "INSERT INTO espacios (nombreEspacio, capacidad, idEdificioPertenece, idTipoEspacioPertenece) values (?, ?, ?, ?);";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
           
            this.ps.setString(1, getNombreEspacio());
            this.ps.setInt(2, getCapacidadEspacio());
            this.ps.setInt(3, getEdificioEspacio());
            this.ps.setInt(4, getTipoEspacio());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se guardaron los datos");
            
                        
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se guardaron los datos ERROR");
        }
    }
    public void mostrarAulas(JTable parametrosCompletosED,String buscar ){
        
       
        try{
            String sql = "SELECT espacios.idEspacio, espacios.nombreEspacio, espacios.capacidad, espacios.idEdificioPertenece, espacios.idTipoEspacioPertenece, edificios.nombreEdificio, tipoespacio.descripcionTipoEspacio " +
             "FROM espacios " +
             "JOIN edificios ON espacios.idEdificioPertenece = edificios.idEdificio " +
             "JOIN tipoespacio ON espacios.idTipoEspacioPertenece = tipoespacio.idTipoEspacio " +
             "WHERE (espacios.idTipoEspacioPertenece = 1 OR espacios.idTipoEspacioPertenece = 3) " +
             "AND (espacios.nombreEspacio LIKE '%" + buscar + "%')";


            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.rs = this.ps.executeQuery();
            
            DefaultTableModel modelo = new DefaultTableModel();
            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
            parametrosCompletosED.setRowSorter(OrdenarTabla);
            
            modelo.addColumn("Id");
            modelo.addColumn("Nombre");
            modelo.addColumn("Capacidad");
            modelo.addColumn("Edificio Ubicado");
            modelo.addColumn("Descripción");
            
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
    
    /*public void buscarAulas(String buscar){
        try{
            String sql = "select espacios.idEspacio, espacios.nombreEspacio, espacios.capacidad, espacios.idEdificioPertenece, espacios.idTipoEspacioPertenece, edificios.nombreEdificio, tipoespacio.descripcionTipoEspacio from espacios, edificios, tipoespacio where espacios.idEdificioPertenece = edificios.idEdificio and espacios.idTipoEspacioPertenece = tipoespacio.idTipoEspacio AND (espacios.idTipoEspacioPertenece = 1 OR espacios.idTipoEspacioPertenece = 3 )where idEspacio LIKE '%"+buscar+"%', OR nombreEspacio LIKE '%"+buscar+"% ";

            this.ps = this.conexion.getConnection().prepareStatement(sql);
            this.rs = this.ps.executeQuery();
            
            DefaultTableModel modelo = new DefaultTableModel();
           
            
            
            
            
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los datos ERROR");
        }
    }*/
    
    
    
    public void SelecionarAulas(JTable parametrosED, JTextField paraId, JTextField paraNombre,
                             JTextField paraCapacidad, JTextField paraEdificio, JTextField paraTipo) {
    try {
        int fila = parametrosED.getSelectedRow();

        if (fila >= 0) {
            paraId.setText((parametrosED.getValueAt(fila, 0).toString()));
            paraNombre.setText((parametrosED.getValueAt(fila, 1).toString()));
            paraCapacidad.setText((parametrosED.getValueAt(fila, 2).toString()));
            paraEdificio.setText((parametrosED.getValueAt(fila, 3).toString()));
            

            // Handle optional 'Descripcion' field (assuming it's the 5th column)
            String descripcion = (String) parametrosED.getValueAt(fila, 4);
            if (descripcion != null && !descripcion.isEmpty()) {
                paraTipo.setText(descripcion);
            } else {
                // If 'Descripcion' is empty or null, set it to an empty string
                paraTipo.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(null, "Error de seleccion");
    }
}

    
    public void updateAulas(JTextField paraId,JTextField paraNombre,JTextField paraCapacidad, JTextField paraEdificio, JTextField paraTipo ){
        
        setIdEspacio(Integer.parseInt(paraId.getText()));
        setNombreEspacio(paraNombre.getText());
        setCapacidadEspacio(Integer.parseInt(paraCapacidad.getText()));
        setEdificioEspacio(Integer.parseInt(paraEdificio.getText()));
        setTipospacio(Integer.parseInt(paraTipo.getText()));
        
        try{
            String sql = "UPDATE espacios SET nombreEspacio = ?, capacidad = ? ,idEdificioPertenece = ? , idTipoEspacioPertenece= ? WHERE idEspacio = ?;";
            this.ps = this.conexion.getConnection().prepareStatement(sql);
            
                       
            this.ps.setString(1, getNombreEspacio());
            this.ps.setInt(2, getCapacidadEspacio());
            this.ps.setInt(3, getEdificioEspacio());
            this.ps.setInt(4, getTipoEspacio());
            this.ps.setInt(5, getIdEspacio());
            this.ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos modificados");
            
        }catch(Exception e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "No se han modificado los datos");
        }
    }
    
    public void deleteEspacios(JTextField paraId){
        
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
    

