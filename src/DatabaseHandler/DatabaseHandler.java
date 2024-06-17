/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseHandler;

/**
 *
 * @author Kevin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection connect() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<String> getTiposResponsables() throws Exception {
        List<String> tipos = new ArrayList<>();
        String query = "SELECT nombreTipoResponsable FROM tiporesponsables";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tipos.add(rs.getString("nombreTipoResponsable"));
            }
        }
        return tipos;
    }

    public List<String> getResponsablesPorTipo(String tipo) throws Exception {
        List<String> responsables = new ArrayList<>();
        String query = "SELECT CONCAT(nombre1Responsable, ' ', apellido1Responsable) AS nombreCompleto " +
                       "FROM responsables r " +
                       "JOIN tiporesponsables t ON r.idTipoResponsablePer = t.idTipoResponsables " +
                       "WHERE t.nombreTipoResponsable = ? " +
                       "ORDER BY nombreCompleto";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    responsables.add(rs.getString("nombreCompleto"));
                }
            }
        }
        return responsables;
    }
}


