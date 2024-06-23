/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseHandler;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Kevin
 */
public class Test2 {
    
    private static DatabaseHandler dbHandler = new DatabaseHandler();

    public static void main(String[] args) {
        try {
            List<String> tiposResponsables = dbHandler.getTiposResponsables();
            JComboBox<String> tipoComboBox = new JComboBox<>(tiposResponsables.toArray(new String[0]));
            JComboBox<String> responsablesComboBox = new JComboBox<>();
            JTextArea motivoArea = new JTextArea(5, 20);

            tipoComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    try {
//                        String selectedTipo = (String) tipoComboBox.getSelectedItem();
//                        List<String> responsables = dbHandler.getResponsablesPorTipo(selectedTipo);
//                        responsablesComboBox.removeAllItems();
//                        for (String responsable : responsables) {
//                            responsablesComboBox.addItem(responsable);
//                        }
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
                }
            });

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            // Tipo de Responsable
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(new JLabel("Tipo de Responsable:"), gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(tipoComboBox, gbc);

            // Responsable
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(new JLabel("Responsable:"), gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(responsablesComboBox, gbc);

            // Motivo
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            panel.add(new JLabel("Motivo:"), gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            JScrollPane scrollPane = new JScrollPane(motivoArea);
            panel.add(scrollPane, gbc);

            int result = JOptionPane.showConfirmDialog(null, panel, "Seleccione Responsable y Motivo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String selectedTipo = (String) tipoComboBox.getSelectedItem();
                String selectedResponsable = (String) responsablesComboBox.getSelectedItem();
                String motivo = motivoArea.getText();
                System.out.println("Tipo seleccionado: " + selectedTipo);
                System.out.println("Responsable seleccionado: " + selectedResponsable);
                System.out.println("Motivo: " + motivo);
            } else {
                System.out.println("Diálogo cancelado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
