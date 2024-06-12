/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

/**
 *
 * @author Kevin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservaEspacioPanel extends JPanel {

    private static final String URL = "jdbc:mysql://localhost/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private JComboBox<Integer> espacioComboBox;
    private JComboBox<Integer> responsableComboBox;

    public ReservaEspacioPanel() {
        setLayout(new GridLayout(4, 2, 10, 10));

        espacioComboBox = new JComboBox<>();
        responsableComboBox = new JComboBox<>();

        cargarDatosComboBox();

        add(new JLabel("Seleccione el espacio a reservar:"));
        add(espacioComboBox);
        add(new JLabel("Seleccione el responsable de la reserva:"));
        add(responsableComboBox);

        JButton reservarButton = new JButton("Reservar");
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEspacio = (int) espacioComboBox.getSelectedItem();
                int idResponsable = (int) responsableComboBox.getSelectedItem();
                realizarReserva(idEspacio, idResponsable);
            }
        });
        add(reservarButton);
    }

    private void cargarDatosComboBox() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String espacioQuery = "SELECT idEspacio FROM espacios";
            String responsableQuery = "SELECT idResponsable FROM responsables";

            PreparedStatement espacioStatement = connection.prepareStatement(espacioQuery);
            PreparedStatement responsableStatement = connection.prepareStatement(responsableQuery);

            espacioComboBox.removeAllItems();
            responsableComboBox.removeAllItems();

            try (var espacioResultSet = espacioStatement.executeQuery();
                 var responsableResultSet = responsableStatement.executeQuery()) {
                while (espacioResultSet.next()) {
                    espacioComboBox.addItem(espacioResultSet.getInt("idEspacio"));
                }
                while (responsableResultSet.next()) {
                    responsableComboBox.addItem(responsableResultSet.getInt("idResponsable"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void realizarReserva(int idEspacio, int idResponsable) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO reservas (`Fecha/HoraInicio`, `Fecha/HoraFin`, `MotivoReserva`, `idResponsableReserva`, `idHorarioReserva`)\n"
                    + "VALUES (NOW(), NOW(), 'Reserva realizada desde Panel', ?, (SELECT idHorario FROM horarios WHERE idEspacioImparte = ? LIMIT 1))";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idResponsable);
                statement.setInt(2, idEspacio);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Reserva realizada con éxito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al realizar la reserva.");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reserva de Espacio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        ReservaEspacioPanel panel = new ReservaEspacioPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
