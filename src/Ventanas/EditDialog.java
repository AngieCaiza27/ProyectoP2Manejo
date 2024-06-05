package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDialog extends JDialog {
    private JTextField responsableField;
    private JTextField materiaField;
    private JTextField espacioField;
    private JButton okButton;
    private JButton cancelButton;
    private boolean confirmed = false;

    public EditDialog(Frame parent, String nombreResponsable, String materia, String espacio) {
        super(parent, "Editar Horario", true);
        initComponents(nombreResponsable, materia, espacio);
    }

    private void initComponents(String nombreResponsable, String materia, String espacio) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel responsableLabel = new JLabel("Responsable: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(responsableLabel, constraints);

        responsableField = new JTextField(nombreResponsable);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(responsableField, constraints);

        JLabel materiaLabel = new JLabel("Materia: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(materiaLabel, constraints);

        materiaField = new JTextField(materia);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(materiaField, constraints);

        JLabel espacioLabel = new JLabel("Espacio: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(espacioLabel, constraints);

        espacioField = new JTextField(espacio);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(espacioField, constraints);

        okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(null);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getResponsable() {
        return responsableField.getText().trim();
    }

    public String getMateria() {
        return materiaField.getText().trim();
    }

    public String getEspacio() {
        return espacioField.getText().trim();
    }
}
