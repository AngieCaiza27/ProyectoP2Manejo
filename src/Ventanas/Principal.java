/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


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

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(this);
        btnEdificios.setIcon(setIcono("/images/edificios.png",btnEdificios));
        btnAulas.setIcon(setIcono("/images/aulas.png",btnAulas));
        btnLaboratorios.setIcon(setIcono("/images/laboratorio.png",btnLaboratorios));
        btnHorarios.setIcon(setIcono("/images/horarios.png",btnHorarios));
        btnReservas.setIcon(setIcono("/images/reservas.png",btnReservas));
        btnResponsables.setIcon(setIcono("/images/Responsable.png",btnResponsables));
        logo.setIcon(SetImageLabel("/images/REServation.png",logo));
        
       
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
        logo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnAulas = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnLaboratorios = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnHorarios = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnReservas = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnResponsables = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        btnEdificios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdificiosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Edificios");

        jpContent.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setText("Bienvenido ");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
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
                        .addGap(251, 251, 251)
                        .addComponent(jLabel9))
                    .addGroup(jpContentLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel7)))
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContentLayout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContentLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(350, 350, 350))))
        );
        jpContentLayout.setVerticalGroup(
            jpContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContentLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnAulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAulasActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Espacios");

        btnLaboratorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaboratoriosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Laboratorios");

        btnHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHorariosActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Horarios");

        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Reservas");

        btnResponsables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResponsablesActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Responsables");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnResponsables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAulas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLaboratorios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHorarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdificios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jpContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnResponsables, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(62, 62, 62)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEdificios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(48, 48, 48)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLaboratorios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(57, 57, 57)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5)))
                .addGap(59, 59, 59))
            .addComponent(jpContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnLaboratoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratoriosActionPerformed
        // TODO add your handling code here:
        cardLayout = new CardLayout();
        jpContent.setLayout(cardLayout);

        panelLaboratorios = new Laboratorios(); 
        
        JPanel laboratorios = Laboratorios.getPanelLaboratorios();
         cardLayout.show(jpContent, "Laboratorios");
        
        laboratorios.setSize(1000, 800);
        laboratorios.setLocation(0, 0);
        jpContent.removeAll();
        jpContent.add(laboratorios,BorderLayout.CENTER);
        jpContent.revalidate();
        jpContent.repaint();
    }//GEN-LAST:event_btnLaboratoriosActionPerformed

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

    private void btnAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAulasActionPerformed
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
    }//GEN-LAST:event_btnAulasActionPerformed

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









    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAulas;
    private javax.swing.JButton btnEdificios;
    private javax.swing.JButton btnHorarios;
    private javax.swing.JButton btnLaboratorios;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnResponsables;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jpContent;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}


