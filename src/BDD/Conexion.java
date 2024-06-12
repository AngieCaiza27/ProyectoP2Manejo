package BDD;

import java.sql.*;

public class Conexion {
    
    private static Connection connection;
    
  
    private static void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
         
            connection=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","");
            System.out.println("OK");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static Connection getConnection(){
        if (connection == null){
            connect();
        }
        return connection;
    }
}
