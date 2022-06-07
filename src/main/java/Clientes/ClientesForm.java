/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Clientes;

import Citas.CitasForm;
import Facturacion.FacturacionForm;
import Mascotas.MascotaFicha;
import Mascotas.MascotasTabla;
import Principal.VentanaPrinc;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import Database.Database;
import static Principal.VentanaPrinc.articulos;
import static Principal.VentanaPrinc.citas;
import static Principal.VentanaPrinc.facturacion;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author Alex
 */
public class ClientesForm extends javax.swing.JPanel {

    /**
     * Creates new form ClientesForm
     */
    private List<QueryDocumentSnapshot> documents;
    private long codigoNuevo=0;
    private long codigoActual;
    private String boton4;
    private ArrayList<String> codMasc=new ArrayList<String>();
    VentanaPrinc padre;
    
    
    public ClientesForm(VentanaPrinc frame,boolean editable) {
        initComponents();
        habilitarBotones();
        jTextField5.setText(String.valueOf(conseguirCodigo()+1));
        reiniciarCampos();
        if(!editable){
            initModify(editable);
        }
        padre = frame;
    }
    public void habilitarBotones(){
        documents = Database.accederDB("Modulos");                
        boolean citas=(boolean) documents.get(0).getData().get("Citas");
        boolean facturacion=(boolean) documents.get(0).getData().get("Facturacion");  
        if(!citas){
            jButton2.setVisible(false);
        }
        if(!facturacion){
            jButton3.setVisible(false);
        }
        
    }
    public ClientesForm(VentanaPrinc frame,boolean editable,Object[] fila) {
        initComponents();
        jTextField5.setText(String.valueOf(conseguirCodigo()));
        if(!editable){
            initModify(editable);
            jTextField1.setText((String) fila[0]);
            jTextField2.setText((String) fila[1]);
            jTextField3.setText((String) fila[5]);
            jTextField4.setText((String) fila[2]);
            jTextField5.setText( String.valueOf(fila[4]));
            jTextField6.setText((String) fila[3]);
            DefaultComboBoxModel dcm= new DefaultComboBoxModel();
            ArrayList<String> mascotas= (ArrayList<String>) fila[6];
            for(int i=0;i<mascotas.size();i++){                
                dcm.addElement(mascotas.get(i));              
        }
            codMasc=(ArrayList<String>)fila[7];
            jComboBox1.setModel(dcm);
        }
        padre = frame;
    }
    public void reiniciarCampos(){
        jTextField1.setText("----");
        jTextField2.setText("----");
        jTextField3.setText("----");
        jTextField4.setText("----");
        jTextField6.setText("----");
    }
    public void habilitarEdicion(Boolean editable){
        jTextField1.setEditable(editable);
        jTextField2.setEditable(editable);
        jTextField3.setEditable(editable);
        jTextField4.setEditable(editable);
        jTextField6.setEditable(editable);        
    }
    public void initModify(boolean editable){
        habilitarEdicion(editable);
        jButton4.setText("Modificar");
    }
    public long conseguirCodigo() {
        documents = Database.accederDB("Clientes");
        if (!documents.isEmpty()) {
            codigoNuevo=documents.size();
            return (codigoNuevo);
        }
        return (0);
    }
    public ClientesForm(Boolean editable) {
        initComponents();
        jTextField5.setText(String.valueOf(conseguirCodigo()));
        
    }
    public void anadirCliente(Boolean add) {

        String nombre = jTextField1.getText();
        String Apellido = jTextField2.getText();
        String Telefono = jTextField3.getText();
        String E_mail = jTextField6.getText();
        String DNI = jTextField4.getText();
        String[] mascotas = new String[]{"Aun no se han asignado mascotas"};
        ArrayList<String> mascota= new ArrayList();
        Map<String, Object> data = new HashMap<>();
        data.put("Nombre", nombre);
        data.put("Apellidos", Apellido);
        data.put("Telefono", Telefono);
        if (add) {
            long Codigo = conseguirCodigo() + 1;
            data.put("Codigo", Codigo);
        }else{
            long Codigo = codigoActual;
            data.put("Codigo", Codigo);
        }
        data.put("E-mail", E_mail);
        data.put("DNI", DNI);
        data.put("Mascotas",Arrays.asList(mascotas));
        Database.insertarDatos("Clientes", DNI, data);
        jTextField5.setText(String.valueOf(conseguirCodigo()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 204));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 40, 0, 0);
        add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("Apellido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 40, 0, 0);
        add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("Telefono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 40, 0, 0);
        add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("E-mail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 40, 0, 0);
        add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setText("Codigo Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 40, 0, 0);
        add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 0));
        jLabel6.setText("DNI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 40, 0, 0);
        add(jLabel6, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField1.setText("Juan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 356;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 15, 0, 0);
        add(jTextField1, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField2.setText("Torcio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 356;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 0);
        add(jTextField2, gridBagConstraints);

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField3.setText("616161616");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 356;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 0);
        add(jTextField3, gridBagConstraints);

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField4.setText("12346578f");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 356;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 0);
        add(jTextField4, gridBagConstraints);

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField5.setText("1");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField5PropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 356;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 0);
        add(jTextField5, gridBagConstraints);

        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField6.setText("jt@gmail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 356;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 15, 0, 0);
        add(jTextField6, gridBagConstraints);

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 82;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 21, 0, 0);
        add(jComboBox1, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 0));
        jLabel7.setText("Mascota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 40, 0, 0);
        add(jLabel7, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(116, 116, 235));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Acceso Mascota");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 50, 0, 322);
        add(jButton1, gridBagConstraints);

        jButton2.setBackground(new java.awt.Color(116, 116, 235));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Citar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.ipadx = 41;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 90, 252, 0);
        add(jButton2, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(116, 116, 235));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Facturar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 55, 252, 0);
        add(jButton3, gridBagConstraints);

        jButton4.setBackground(new java.awt.Color(116, 116, 235));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Añadir");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jButton4PropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 55, 0, 0);
        add(jButton4, gridBagConstraints);

        jButton6.setBackground(new java.awt.Color(116, 116, 235));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("Volver");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.ipadx = 22;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 90, 0, 0);
        add(jButton6, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            int num=jComboBox1.getSelectedIndex();
            Firestore db = Database.getDatabase();
            DocumentReference docRef = db.collection("Mascotas").document(codMasc.get(num));

            DocumentSnapshot documento = docRef.get().get();

            Object[] fila = new Object[]{
                documento.getData().get("Nombre"),
                documento.getData().get("Edad"),
                documento.getData().get("Especie"),
                documento.getData().get("Chip"),
                documento.getData().get("Codigo"),
                documento.getData().get("Raza"),                
                documento.getData().get("Caracter"),
                documento.getData().get("Sexo"),
                documento.getData().get("Esterilizacion"),
                documento.getData().get("Cliente"),};
            padre.showMascotasFichaNoEditable(fila);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ClientesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(padre,"Este cliente aun no tiene mascotas asignadas","",JOptionPane.WARNING_MESSAGE );
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (jButton4.getText().equals("Modificar")) {
            padre.showCitasForm(jTextField1.getText(), jTextField4.getText());
        } else {
            if (jButton4.getText().equals("Confirmar")) {
                JOptionPane.showMessageDialog(padre,"Completa la modificacion antes de citar al cliente","",JOptionPane.WARNING_MESSAGE );
                
            } else {
                JOptionPane.showMessageDialog(padre,"Crea el cliente antes de citarlo", "",JOptionPane.WARNING_MESSAGE );
                
            }
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        padre.showFacturacionForm(jTextField1.getText());
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        if (jButton4.getText().equals("Modificar")) {
            jButton4.setText("Confirmar");
            habilitarEdicion(true);
            jTextField4.setEditable(false);
        } else {
            if (jButton4.getText().equals("Confirmar")) {
                anadirCliente(false);
            } else {
                anadirCliente(true);
                habilitarEdicion(false);
                jButton4.setText("Modificar");
            }
        }

    }//GEN-LAST:event_jButton4MouseClicked

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        padre.showClientesTabla();
    }//GEN-LAST:event_jButton6MouseClicked

    private void jTextField5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField5PropertyChange
        codigoActual=Long.parseLong(jTextField5.getText());
    }//GEN-LAST:event_jTextField5PropertyChange

    private void jButton4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jButton4PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4PropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
