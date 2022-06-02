/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Mascotas;

import Database.Database;
import Principal.VentanaPrinc;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class MascotasTabla extends javax.swing.JPanel {

    /**
     * Creates new form MascotasTabla
     */
    private List<QueryDocumentSnapshot> documents;
    VentanaPrinc padre;
    public MascotasTabla() {
        initComponents();
    }
    public MascotasTabla(VentanaPrinc frame) {
        initComponents();
        crearTabla();
        padre=frame;
    }

    public void crearTabla(){
        
    DefaultTableModel dtm = new DefaultTableModel();
        
        dtm.addColumn("Nombre");
        dtm.addColumn("Edad");
        dtm.addColumn("Especie");
        dtm.addColumn("Raza");
        dtm.addColumn("Codigo");
        dtm.addColumn("Chip");

        documents = Database.accederDB("Mascotas");
        for (int i = 0; i < documents.size(); i++) {
            Object[] fila = new Object[]{
                documents.get(i).getData().get("Nombre"),
                documents.get(i).getData().get("Edad"),
                documents.get(i).getData().get("Especie"),
                documents.get(i).getData().get("Raza"),
                documents.get(i).getData().get("Codigo"),
                documents.get(i).getData().get("Chip"),};
            dtm.addRow(fila);
        }
        jTable1.setModel(dtm);
    }
    public void filtrarNombre() {
        String filtro = jTextField4.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Edad");
        dtm.addColumn("Especie");
        dtm.addColumn("Raza");
        dtm.addColumn("Codigo");
        dtm.addColumn("Chip");

        documents = Database.accederDB("Mascotas");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Telefono").equals(filtro)) {
                Object[] fila = new Object[]{
                    documents.get(i).getData().get("Nombre"),
                    documents.get(i).getData().get("Edad"),
                    documents.get(i).getData().get("Especie"),
                    documents.get(i).getData().get("Raza"),
                    documents.get(i).getData().get("Codigo"),
                    documents.get(i).getData().get("Chip"),};
                dtm.addRow(fila);
            }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarCodigo() {
        String filtro = jTextField3.getText();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Nombre");
        dtm.addColumn("Edad");
        dtm.addColumn("Especie");
        dtm.addColumn("Raza");
        dtm.addColumn("Codigo");
        dtm.addColumn("Chip");

        documents = Database.accederDB("Mascotas");
        for (int i = 0; i < documents.size(); i++) {
            if (filtro.equals(String.valueOf(documents.get(i).getData().get("Codigo")))) {
                Object[] fila = new Object[]{
                    documents.get(i).getData().get("Nombre"),
                    documents.get(i).getData().get("Edad"),
                    documents.get(i).getData().get("Especie"),
                    documents.get(i).getData().get("Raza"),
                    documents.get(i).getData().get("Codigo"),
                    documents.get(i).getData().get("Chip"),};
                dtm.addRow(fila);
            }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarRaza() {
        String filtro = jTextField4.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Edad");
        dtm.addColumn("Especie");
        dtm.addColumn("Raza");
        dtm.addColumn("Codigo");
        dtm.addColumn("Chip");

        documents = Database.accederDB("Mascotas");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Telefono").equals(filtro)) {
                Object[] fila = new Object[]{
                    documents.get(i).getData().get("Nombre"),
                    documents.get(i).getData().get("Edad"),
                    documents.get(i).getData().get("Especie"),
                    documents.get(i).getData().get("Raza"),
                    documents.get(i).getData().get("Codigo"),
                    documents.get(i).getData().get("Chip"),};
                dtm.addRow(fila);
            }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarChip() {
        String filtro = jTextField4.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Edad");
        dtm.addColumn("Especie");
        dtm.addColumn("Raza");
        dtm.addColumn("Codigo");
        dtm.addColumn("Chip");

        documents = Database.accederDB("Mascotas");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Telefono").equals(filtro)) {
                Object[] fila = new Object[]{
                    documents.get(i).getData().get("Nombre"),
                    documents.get(i).getData().get("Edad"),
                    documents.get(i).getData().get("Especie"),
                    documents.get(i).getData().get("Raza"),
                    documents.get(i).getData().get("Codigo"),
                    documents.get(i).getData().get("Chip"),};
                dtm.addRow(fila);
            }
        }
        jTable1.setModel(dtm);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 450, 178));

        jTextField1.setText("-----");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 90, -1));

        jTextField2.setText("-----");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 90, 20));

        jTextField3.setText("----");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 80, -1));

        jTextField4.setText("----");
        add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 90, -1));

        jButton1.setText("Reiniciar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, -1, -1));

        jButton2.setText("Acceder ficha");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        jButton3.setText("Nueva Mascota");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jLabel1.setText("Filtrar por nombre");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel2.setText("Filtrar por especie");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel3.setText("Filtrar por codigo");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        jLabel4.setText("Filtrar por cliente");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        padre.showMascotasFicha();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        padre.showMascotasFicha();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        crearTabla();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        filtrarCodigo();
    }//GEN-LAST:event_jTextField3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
