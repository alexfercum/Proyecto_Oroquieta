/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Facturacion;

import Database.Database;
import Principal.VentanaPrinc;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class FacturacionForm extends javax.swing.JPanel {

    /**
     * Creates new form FacturacionForm
     */
    VentanaPrinc padre;
    private List<QueryDocumentSnapshot> documents;
    public FacturacionForm() {
        initComponents();
        crearTabla();
        crearTabla2();
        llenarCombos();
    }
    public FacturacionForm(VentanaPrinc frame) {
        initComponents();
        crearTabla();
        crearTabla2();
        llenarCombos();
        padre=frame;
    }
    public void crearTabla() {
        DefaultTableModel dtm = new DefaultTableModel();
        
        dtm.addColumn("Nombre");
        dtm.addColumn("Tipo");
        dtm.addColumn("Precio");
        dtm.addColumn("Codigo");
        

        documents = Database.accederDB("Inventario");
        for (int i = 0; i < documents.size(); i++) {
            Object[] fila = new Object[]{
                documents.get(i).getData().get("Nombre"),
                documents.get(i).getData().get("Tipo"),
                documents.get(i).getData().get("Precio"),
                documents.get(i).getData().get("Codigo"),
                };
            dtm.addRow(fila);
        }
        jTable1.setModel(dtm);

    }
    public void crearTabla2(){
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Nombre");
        dtm.addColumn("Precio");
        dtm.addColumn("Cantidad");
        jTable2.setModel(dtm);
    }
    public void llenarCombos(){
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        
        dcm.addElement("Servicio");
        dcm.addElement("Tienda");
        dcm.addElement("Alimentacion");
        dcm.addElement("Farmacos");
       
        jComboBox1.setModel(dcm);
        
    }
     public void filtrarNombre() {
        String filtro = jTextField2.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Tipo");
        dtm.addColumn("Precio");
        dtm.addColumn("Codigo");

        documents = Database.accederDB("Inventario");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Nombre").equals(filtro)) {
            Object[] fila = new Object[]{
                documents.get(i).getData().get("Nombre"),
                documents.get(i).getData().get("Tipo"),
                documents.get(i).getData().get("Precio"),
                documents.get(i).getData().get("Codigo"),
                };
            dtm.addRow(fila);
        }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarPrecio() {
        String filtro = jTextField5.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Tipo");
        dtm.addColumn("Precio");
        dtm.addColumn("Codigo");

        documents = Database.accederDB("Inventario");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Precio").equals(filtro)) {
            Object[] fila = new Object[]{
                documents.get(i).getData().get("Nombre"),
                documents.get(i).getData().get("Tipo"),
                documents.get(i).getData().get("Precio"),
                documents.get(i).getData().get("Codigo"),
                };
            dtm.addRow(fila);
        }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarTipo() {
        String filtro = (String)jComboBox1.getSelectedItem();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Tipo");
        dtm.addColumn("Precio");
        dtm.addColumn("Codigo");

        documents = Database.accederDB("Inventario");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Tipo").equals(filtro)) {
            Object[] fila = new Object[]{
                documents.get(i).getData().get("Nombre"),
                documents.get(i).getData().get("Tipo"),
                documents.get(i).getData().get("Precio"),
                documents.get(i).getData().get("Codigo"),
                };
            dtm.addRow(fila);
        }
        }
        jTable1.setModel(dtm);
    }
    public void filtrarCodigo() {
         String filtro = jTextField1.getText();
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.addColumn("Nombre");
        dtm.addColumn("Tipo");
        dtm.addColumn("Precio");
        dtm.addColumn("Codigo");

        documents = Database.accederDB("Inventario");
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getData().get("Codigo").equals(filtro)) {
            Object[] fila = new Object[]{
                documents.get(i).getData().get("Nombre"),
                documents.get(i).getData().get("Tipo"),
                documents.get(i).getData().get("Precio"),
                documents.get(i).getData().get("Codigo"),
                };
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 50, 570, 100));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 580, 100));

        jLabel1.setText("Codigo");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jLabel2.setText("Nombre");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jLabel3.setText("Tipo");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel4.setText("Cantidad");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jTextField3.setText("jTextField3");
        jTextField3.setActionCommand("<Not Set>");
        jTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jButton1.setText("Añadir al carro");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, -1, -1));

        jButton2.setText("Aceptar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel5.setText("Precio");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jTextField5.setText("jTextField5");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jButton3.setText("Reiniciar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        DefaultTableModel dtm = new DefaultTableModel();
        dtm=(DefaultTableModel) jTable2.getModel();
        
        padre.showFacturacionConf(dtm);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        filtrarCodigo();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        filtrarNombre();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        filtrarPrecio();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        filtrarTipo();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        crearTabla();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        DefaultTableModel dtm1 = new DefaultTableModel();
        DefaultTableModel dtm2 = new DefaultTableModel();
        dtm1=(DefaultTableModel) jTable1.getModel();
        dtm2=(DefaultTableModel) jTable2.getModel();        
        String nombre = dtm1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String precio = dtm1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        String cantidad =  jTextField3.getText();
        Object[] fila = new Object[]{
                nombre,
                precio,
                cantidad,};
        dtm2.addRow(fila);
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        int key = evt.getKeyChar();

    boolean numeros = key >= 48 && key <= 57;
        
    if (!numeros)
    {
        evt.consume();
    }

    
    }//GEN-LAST:event_jTextField3KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
