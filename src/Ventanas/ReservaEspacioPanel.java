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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReservaEspacioPanel extends JPanel {

    private static final String URL = "jdbc:mysql://localhost/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private JComboBox<Integer> espacioComboBox;
    private JComboBox<Integer> responsableComboBox;
    private JTable tablaHorario;
    private DefaultTableModel modeloTabla;

    public ReservaEspacioPanel() {
        setLayout(new BorderLayout());

        JPanel formularioPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        espacioComboBox = new JComboBox<>();
        responsableComboBox = new JComboBox<>();

        cargarDatosComboBox();

        formularioPanel.add(new JLabel("Seleccione el espacio a reservar:"));
        formularioPanel.add(espacioComboBox);
        formularioPanel.add(new JLabel("Seleccione el responsable de la reserva:"));
        formularioPanel.add(responsableComboBox);

        JButton reservarButton = new JButton("Reservar");
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEspacio = (int) espacioComboBox.getSelectedItem();
                int idResponsable = (int) responsableComboBox.getSelectedItem();
                realizarReserva(idEspacio, idResponsable);
                actualizarTabla();
            }
        });
        formularioPanel.add(reservarButton);

        add(formularioPanel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[]{"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaHorario = new JTable(modeloTabla);
        tablaHorario.setRowHeight(40);
        tablaHorario.getTableHeader().setReorderingAllowed(false);
        tablaHorario.getTableHeader().setResizingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tablaHorario);
        add(scrollPane, BorderLayout.CENTER);

        actualizarTabla();
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

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT Fecha_HoraInicio, Disponible FROM horarios";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Object[] fila = new Object[6];
                    Timestamp horaInicio = resultSet.getTimestamp("Fecha_HoraInicio");
                    fila[0] = horaInicio.toLocalDateTime().toLocalTime().toString();
                    for (int i = 1; i < fila.length; i++) {
                        fila[i] = resultSet.getBoolean("Disponible") ? "Disponible" : "Reservado";
                    }
                    modeloTabla.addRow(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(Color.GREEN);
        tablaHorario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String estado = table.getValueAt(row, column).toString();
                if ("Reservado".equals(estado)) {
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.GREEN);
                }
                return c;
            }
        });
        for (int i = 0; i < tablaHorario.getColumnCount(); i++) {
            tablaHorario.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reserva de Espacio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        ReservaEspacioPanel panel = new ReservaEspacioPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
