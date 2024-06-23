/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Hola
 */
public class Principal extends javax.swing.JFrame {
    
    private CardLayout cardLayout;
    private JPanel panelEdificios;
    private JPanel panelAulas;
    private JPanel panelLaboratorios;
    private JPanel panelResponsables;
    private JPanel panelHorarios;
    private JPanel panelReservas;
    private JPanel panelMaterias;


    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(this);
        btnEdificios.setIcon(setIcono("/images/edificios.png",btnEdificios));
        btnEspacios.setIcon(setIcono("/images/aulas.png",btnEspacios));
        btnHorarios.setIcon(setIcono("/images/horarios.png",btnHorarios));
        btnReservas.setIcon(setIcono("/images/reservas.png",btnReservas));
        btnResponsables.setIcon(setIcono("/images/Responsable.png",btnResponsables));
        btnMaterias.setIcon(setIcono("/images/lib.png", btnEspacios));

        
       
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEdificios = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jpContent = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnEspacios = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnHorarios = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnReservas = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnResponsables = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnMaterias = new javax.swing.JButton();
        materias = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        btnEdificios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdificiosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        jLabel1.setText("Edificios");

        jpContent.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        jLabel8.setText("Bienvenido ");

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Sistema de Reservas");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/llegada.gif"))); // NOI18N

        javax.swing.GroupLayout jpContentLayout = new javax.swing.GroupLayout(jpContent);
        jpContent.setLayout(jpContentLayout);
        jpContentLayout.setHorizontalGroup(
            jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContentLayout.createSequentialGroup()
                .addGroup(jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContentLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel7))
                    .addGroup(jpContentLayout.createSequentialGroup()
                        .addGap(454, 454, 454)
                        .addComponent(jLabel8)))
                .addContainerGap(433, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContentLayout.createSequentialGroup()
                .addGap(0, 619, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(74, 74, 74))
        );
        jpContentLayout.setVerticalGroup(
            jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContentLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(86, 86, 86))
        );

        btnEspacios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEspaciosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        jLabel2.setText("Espacios");

        btnHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHorariosActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        jLabel4.setText("Horarios");

        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        jLabel5.setText("Reservas");

        btnResponsables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResponsablesActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        jLabel6.setText("Responsables");

        btnMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMateriasActionPerformed(evt);
            }
        });

        materias.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        materias.setText("Materias");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnResponsables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEspacios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHorarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdificios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMaterias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(materias, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(46, 46, 46)
                .addComponent(jpContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnResponsables, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnEdificios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEspacios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(materias)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(75, 75, 75)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8)))
                .addGap(84, 84, 84))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEdificiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdificiosActionPerformed
        cardLayout = new CardLayout();
        jpContent.setLayout(cardLayout);

        panelEdificios = new Edificios(); 
        
        JPanel edificios = Edificios.getPanelEdificios();
         cardLayout.show(jpContent, "Edificios");
        
        edificios.setSize(1000, 800);
        edificios.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(edificios,BorderLayout.CENTER);
        jpContent.revalidate();
        jpContent.repaint();
        
        
        
    }//GEN-LAST:event_btnEdificiosActionPerformed

    private void btnHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHorariosActionPerformed
        cardLayout = new CardLayout();
        jpContent.setLayout(cardLayout);

        panelHorarios = new Horarios(); 
        
        JPanel horarios = Horarios.getPanelHorarios();
        
         cardLayout.show(jpContent, "Horarios");
        
        horarios.setSize(1000, 800);
        horarios.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(horarios,BorderLayout.CENTER);
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnHorariosActionPerformed

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        // TODO add your handling code here:
        
        cardLayout = new CardLayout();
        jpContent.setLayout(cardLayout);

        panelReservas = new Reservas(); 
        
        JPanel reservas = Reservas.getPanelReservas();
         cardLayout.show(jpContent, "Reservas");
        
        reservas.setSize(1000, 800);
        reservas.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(reservas,BorderLayout.CENTER);
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnResponsablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResponsablesActionPerformed
        // TODO add your handling code here:
        cardLayout = new CardLayout();
        jpContent.setLayout(cardLayout);

        panelResponsables = new Responsables(); 
        
        JPanel responsables = Responsables.getPanelResponsables();
         cardLayout.show(jpContent, "Responsables");
        
        responsables.setSize(1000, 800);
        responsables.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(responsables,BorderLayout.CENTER);
        jpContent.revalidate();
        jpContent.repaint();
        
        
        
    }//GEN-LAST:event_btnResponsablesActionPerformed

    private void btnEspaciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEspaciosActionPerformed
        // TODO add your handling code here:
        cardLayout = new CardLayout();
        jpContent.setLayout(cardLayout);

        panelAulas = new Aulas(); 
        
        JPanel aulas = Aulas.getPanelAulas();
         cardLayout.show(jpContent, "Aulas");
        
        aulas.setSize(1000, 800);
        aulas.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(aulas,BorderLayout.CENTER);
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnEspaciosActionPerformed

    private void btnMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMateriasActionPerformed
       cardLayout = new CardLayout();
        jpContent.setLayout(cardLayout);

        panelMaterias = new Materias(); 
        
        JPanel materias = Materias.getPanelMaterias();
         cardLayout.show(jpContent, "Materias");
        
        materias.setSize(1000, 800);
        materias.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(materias,BorderLayout.CENTER);
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnMateriasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
         
       
        /* Set the Nimbus look and feel */
       //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
        
        
        
         
    }
    
    public Icon setIcono(String root,JButton btn){
        ImageIcon icon = new ImageIcon(getClass().getResource(root));
            int ancho = btn.getWidth();
            int alto = btn.getHeight();
            
            ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
            
            return icono;
    }
    
    public Icon SetImageLabel (String root,JLabel lbIcono){
            ImageIcon image = new ImageIcon(getClass().getResource(root));
            int ancho = lbIcono.getWidth();
            int alto = lbIcono.getHeight();
            ImageIcon img = new ImageIcon(image.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
            
            return img;
        }




public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value != null && value.toString().equals("Descanso")) {
            cell.setBackground(Color.YELLOW);
        } else if (value != null && !value.toString().isEmpty()) {
            cell.setBackground(Color.RED);
        } else {
            cell.setBackground(Color.GREEN);
        }

        return cell;
    }
}






    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdificios;
    private javax.swing.JButton btnEspacios;
    private javax.swing.JButton btnHorarios;
    private javax.swing.JButton btnMaterias;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnResponsables;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jpContent;
    private javax.swing.JLabel materias;
    // End of variables declaration//GEN-END:variables
}




