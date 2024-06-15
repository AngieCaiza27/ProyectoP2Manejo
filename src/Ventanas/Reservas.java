/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventanas;

import BDD.CRUDEReservas;
import BDD.CRUDHorarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class Reservas extends javax.swing.JPanel {

    private  static JPanel instance = null;
    private CRUDEReservas crudReservas;
    private CRUDHorarios crudHorarios;

    public Reservas() {
        initComponents();
        crudReservas = new CRUDEReservas();
        llenarEdificios();
        //llenarTiposEspacios();
        //llenarEspacios();
    }


    public static JPanel getPanelReservas(){
        if (instance == null){
            instance = new Reservas();
        }

        return instance;


    }
    public void llenarTiposEspacios(){
    jComboTipoEspacio.removeAllItems();
        List<String> tipos = obtenerTiposPorEdificio(this.jComboEdificios.getSelectedItem().toString());
        for (String tipo : tipos) {
            jComboTipoEspacio.addItem(tipo);
        }
    }

    public void llenarEspacios(){
    jComboEspacio.removeAllItems();
        List<String> espacios = obtenerEspacios(this.jComboEdificios.getSelectedItem().toString(),
                this.jComboTipoEspacio.getSelectedItem().toString());
        for (String espacio : espacios) {
            jComboEspacio.addItem(espacio);
        }
    }
    
    public void llenarEdificios(){
    jComboEspacio.removeAllItems();
        List<String> edificios = obtenerEdificios();
        for (String edificio : edificios) {
            jComboEdificios.addItem(edificio);
        }
    }

    public List<String> obtenerTiposPorEdificio(String edificio) {
        List<String> tipos = new ArrayList<>();
        String sql = "SELECT DISTINCT te.nombreTipoEspacio " +
                     "FROM espacios e " +
                     "JOIN tipoespacio te ON e.idTipoEspacioPertenece = te.idTipoEspacio " +
                     "JOIN edificios ed ON e.idEdificioPertenece = ed.idEdificio " +
                     "WHERE ed.nombreEdificio = ?";

        ResultSet rs;


        try ( PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)){
            ps.setString(1, edificio);
            rs = ps.executeQuery();
            while (rs.next()) {
                tipos.add(rs.getString("nombreTipoEspacio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron obtener los tipos de espacios del edificio. ERROR: ");
            System.out.println(e.getMessage());

        }

        return tipos;
    }

    public List<String> obtenerEspacios(String edificio, String tipo) {
        List<String> espacios = new ArrayList<>();
        String sql = "SELECT e.idEspacio, e.nombreEspacio, e.capacidad, "
                + "te.nombreTipoEspacio, ed.nombreEdificio "
                + "FROM espacios e "
                + "JOIN tipoespacio te ON e.idTipoEspacioPertenece = te.idTipoEspacio "
                + "JOIN edificios ed ON e.idEdificioPertenece = ed.idEdificio "
                + "WHERE te.nombreTipoEspacio = ? "
                + "AND ed.nombreEdificio = ? ";
        ResultSet rs;
        try ( PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)){
            ps.setString(1, tipo);
            ps.setString(2, edificio);
            rs = ps.executeQuery();
            while (rs.next()) {
                espacios.add(rs.getString("nombreEspacio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron obtener los espacios. ERROR: ");
            System.out.println(e.getMessage());

        }

        return espacios;
    }

    public List<String> obtenerEdificios() {
        List<String> edificios = new ArrayList<>();
        String sql = "SELECT nombreEdificio FROM edificios";
        ResultSet rs;
        try (PreparedStatement ps = crudReservas.getConnection().prepareStatement(sql)) {

            rs = ps.executeQuery();
            while (rs.next()) {
                edificios.add(rs.getString("nombreEdificio"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron obtener los espacios. ERROR: ");
            System.out.println(e.getMessage());

        }

        return edificios;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboEdificios = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboTipoEspacio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboEspacio = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jLabel1.setText("Reservas");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendario.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado"
            }
        ));
        jTable1.setRowHeight(90);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboEdificios.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboEdificios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEdificiosActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel4.setText("Edificios: ");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel5.setText("Tipo de espacio:");

        jComboTipoEspacio.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboTipoEspacio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboTipoEspacioMouseClicked(evt);
            }
        });
        jComboTipoEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTipoEspacioActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel6.setText("Espacio:");

        jComboEspacio.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboEspacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEspacioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jComboEdificios, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboTipoEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jComboEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboEdificios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jComboTipoEspacio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1264, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 829, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked


    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboEdificiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEdificiosActionPerformed

        llenarTiposEspacios();
    }//GEN-LAST:event_jComboEdificiosActionPerformed

    private void jComboTipoEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTipoEspacioActionPerformed
        llenarEspacios();
    }//GEN-LAST:event_jComboTipoEspacioActionPerformed

    private void jComboEspacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEspacioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboEspacioActionPerformed

    private void jComboTipoEspacioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboTipoEspacioMouseClicked


    }//GEN-LAST:event_jComboTipoEspacioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboEdificios;
    private javax.swing.JComboBox<String> jComboEspacio;
    private javax.swing.JComboBox<String> jComboTipoEspacio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
