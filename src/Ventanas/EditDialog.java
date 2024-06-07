package Ventanas;

import BDD.CRUDHorarios;
import BDD.CRUDHorarios.Espacio;
import BDD.CRUDHorarios.Materia;
import BDD.CRUDHorarios.Responsable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BDD.CRUDResponsables;

public class EditDialog extends JDialog {
    private JComboBox<CRUDHorarios.Responsable> responsableBox;
    private JComboBox<CRUDHorarios.Materia> materiaBox;
    private JComboBox<CRUDHorarios.Espacio> espacioBox;
    private JButton okButton;
    private JButton cancelButton;
    private JButton eliminarButton;
    private boolean confirmed = false;

    private CRUDHorarios horarios;

    public EditDialog(Frame parent) {
        super(parent, "Editar Horario", true);
        horarios = new CRUDHorarios();
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel responsableLabel = new JLabel("Responsable: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(responsableLabel, constraints);

        responsableBox = new JComboBox<CRUDHorarios.Responsable>();
        horarios.llenarComboBoxResponsables(responsableBox);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(responsableBox, constraints);

        JLabel materiaLabel = new JLabel("Materia: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(materiaLabel, constraints);

        materiaBox = new JComboBox<CRUDHorarios.Materia>();
        horarios.llenarComboBoxMaterias(materiaBox);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(materiaBox, constraints);

        JLabel espacioLabel = new JLabel("Espacio: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(espacioLabel, constraints);

        espacioBox = new JComboBox<CRUDHorarios.Espacio>();
        horarios.llenarComboBoxEspacios(espacioBox);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(espacioBox, constraints);

        okButton = new JButton("Aceptar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });
        eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
//                    horarios.deleteHorario(idResponsable);
                    dispose();
                }
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

    public Responsable getResponsable() {
        return (Responsable) responsableBox.getSelectedItem();
    }

    public Materia getMateria() {
        return (Materia) materiaBox.getSelectedItem();
    }

    public Espacio getEspacio() {
        return (Espacio) espacioBox.getSelectedItem();
    }
}
