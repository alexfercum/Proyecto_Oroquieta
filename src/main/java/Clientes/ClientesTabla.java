/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Clientes;

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
public class ClientesTabla extends javax.swing.JPanel {

    /**
     * Creates new form ClientesTabla
     */
    private List<QueryDocumentSnapshot> documents;
    private VentanaPrinc padre;
    private int codigo=0;
    public ClientesTabla() {
        initComponents();
        init();
    }
    public ClientesTabla(VentanaPrinc frame) {
        initComponents();
        init();
         padre=frame;
    }
    public void init(){
        crearTabla();
    }
    public void filtrarNombre() {
        String filtro = jTextField1.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Apellidos");
        dtm.addColumn("DNI");
        dtm.addColumn("E-mail");
        dtm.addColumn("Codigo");
        dtm.addColumn("Telefono");

        documents = Database.accederDB("Clientes");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Nombre").equals(filtro)) {
                Object[] fila = new Object[]{
                    documents.get(i).getData().get("Nombre"),
                    documents.get(i).getData().get("Apellidos"),
                    documents.get(i).getData().get("DNI"),
                    documents.get(i).getData().get("E-mail"),
                    documents.get(i).getData().get("Codigo"),
                    documents.get(i).getData().get("Telefono"),};
                dtm.addRow(fila);
            }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarApellido() {
        String filtro = jTextField2.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Apellidos");
        dtm.addColumn("DNI");
        dtm.addColumn("E-mail");
        dtm.addColumn("Codigo");
        dtm.addColumn("Telefono");

        documents = Database.accederDB("Clientes");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Apellidos").equals(filtro)) {
                Object[] fila = new Object[]{
                    documents.get(i).getData().get("Nombre"),
                    documents.get(i).getData().get("Apellidos"),
                    documents.get(i).getData().get("DNI"),
                    documents.get(i).getData().get("E-mail"),
                    documents.get(i).getData().get("Codigo"),
                    documents.get(i).getData().get("Telefono"),};
                dtm.addRow(fila);
            }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarDNI() {
        String filtro = jTextField3.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Apellidos");
        dtm.addColumn("DNI");
        dtm.addColumn("E-mail");
        dtm.addColumn("Codigo");
        dtm.addColumn("Telefono");

        documents = Database.accederDB("Clientes");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("DNI").equals(filtro)) {
                Object[] fila = new Object[]{
                    documents.get(i).getData().get("Nombre"),
                    documents.get(i).getData().get("Apellidos"),
                    documents.get(i).getData().get("DNI"),
                    documents.get(i).getData().get("E-mail"),
                    documents.get(i).getData().get("Codigo"),
                    documents.get(i).getData().get("Telefono"),};
                dtm.addRow(fila);
            }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarTelefono() {
        String filtro = jTextField4.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Apellidos");
        dtm.addColumn("DNI");
        dtm.addColumn("E-mail");
        dtm.addColumn("Codigo");
        dtm.addColumn("Telefono");

        documents = Database.accederDB("Clientes");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Telefono").equals(filtro)) {
                Object[] fila = new Object[]{
                    documents.get(i).getData().get("Nombre"),
                    documents.get(i).getData().get("Apellidos"),
                    documents.get(i).getData().get("DNI"),
                    documents.get(i).getData().get("E-mail"),
                    documents.get(i).getData().get("Codigo"),
                    documents.get(i).getData().get("Telefono"),};
                dtm.addRow(fila);
            }
        }
        jTable1.setModel(dtm);
    }
    public int conseguirCodigo() {
        documents = Database.accederDB("Clientes");
        for (int i = 0; i < documents.size(); i++) {
            if ((Integer) documents.get(i).getData().get("Codigo") > codigo) {
                codigo = (Integer) documents.get(i).getData().get("Codigo");
            }

        }
        return (codigo);
    }
    public void crearTabla() {
        DefaultTableModel dtm = new DefaultTableModel();
        
        dtm.addColumn("Nombre");
        dtm.addColumn("Apellidos");
        dtm.addColumn("DNI");
        dtm.addColumn("E-mail");
        dtm.addColumn("Codigo");
        dtm.addColumn("Telefono");

        documents = Database.accederDB("Clientes");
        for (int i = 0; i < documents.size(); i++) {
            Object[] fila = new Object[]{
                documents.get(i).getData().get("Nombre"),
                documents.get(i).getData().get("Apellidos"),
                documents.get(i).getData().get("DNI"),
                documents.get(i).getData().get("E-mail"),
                documents.get(i).getData().get("Codigo"),
                documents.get(i).getData().get("Telefono"),};
            dtm.addRow(fila);
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

        choice1 = new java.awt.Choice();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 900));
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 460, 128));

        jLabel1.setText("Filtrar por nombre");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel2.setText("Filtrar por apellido");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel3.setText("Filtrar por DNI");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        jLabel4.setText("Filtrar por Telefono");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, -1));

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        jTextField3.setText("jTextField3");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, -1));

        jTextField4.setText("jTextField4");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

        jButton1.setText("Reiniciar ");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        jButton2.setText("Acceder seleccionado");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        jButton3.setText("Crear nuevo");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
                
        int i=jTable1.getSelectedRow();
        Object[] fila = new Object[]{
                documents.get(i).getData().get("Nombre"),
                documents.get(i).getData().get("Apellidos"),
                documents.get(i).getData().get("DNI"),
                documents.get(i).getData().get("E-mail"),
                documents.get(i).getData().get("Codigo"),
                documents.get(i).getData().get("Telefono"),};
        padre.showClientesFormNoEditable(fila);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        padre.showClientesFormEditable();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        filtrarNombre();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        crearTabla();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        filtrarApellido();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        filtrarDNI();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        filtrarTelefono();
    }//GEN-LAST:event_jTextField4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
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
