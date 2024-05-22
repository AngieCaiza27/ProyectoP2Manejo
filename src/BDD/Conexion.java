package BDD;

import java.sql.*;

public class Conexion {
    
    private Connection connection;
    
    public Conexion(){
        this.connect();
    }
    
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
         
            connection=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","");
            System.out.println("OK");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public Connection getConnection(){
        return this.connection;
    }
}
