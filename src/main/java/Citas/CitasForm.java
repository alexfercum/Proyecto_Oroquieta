/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Citas;

import Database.Database;
import Principal.VentanaPrinc;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.awt.Color;
import static java.lang.Integer.getInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class CitasForm extends javax.swing.JPanel {

    /**
     * Creates new form CitasFor
     */
    VentanaPrinc padre;
    private List<QueryDocumentSnapshot> documents;
    private String dniCliente;
    private String nombreCliente;
    private long codigo;
    public CitasForm() {
        initComponents();
    }
    public CitasForm(VentanaPrinc frame) {
        initComponents();
        llenarCombo();
        padre=frame;
    }
    public CitasForm(VentanaPrinc frame,String nombre,String dni) {
        initComponents();
        dniCliente=dni;
        nombreCliente=nombre;
        jTextField3.setText(nombre);
        llenarCombo();
        padre=frame;
    }
    private boolean validarHora(String hora){
        boolean b;
        char[] a = hora.toString().toCharArray();
        String[] c = hora.split(":");
        if ((a[0] == ' ') || (a[1] == ' ') || (a[2] == ' ') || (a[3] == ' ') || (a[4] == ' ') || (getInteger(c[0]) > 24) || (getInteger(c[1]) > 59)) {
            b=false;
        }else{
            b=true;
        }
        return b;
    }

    public long conseguirCodigo() {
        documents = Database.accederDB("Citas");
        if (!documents.isEmpty()) {
            codigo = documents.size();
            return (codigo);
        }
        return (0);
    }
    public void llenarCombo(){
        try {
            Firestore db = Database.getDatabase();
            DocumentReference docRef = db.collection("Clientes").document(dniCliente);
            DocumentSnapshot documento = docRef.get().get();
            ArrayList<String> codMasc= new ArrayList<String>();
            codMasc=(ArrayList<String>) documento.getData().get("Mascotas");            
            ArrayList<String> nomMasc= new ArrayList<String>();
            for(String codigo:codMasc){
                DocumentReference docRefPet = db.collection("Mascotas").document(codigo);
                DocumentSnapshot pet = docRefPet.get().get();
                nomMasc.add((String) pet.getData().get("Nombre"));
            }
            nombreCliente = (String) documento.getData().get("Nombre");
            DefaultComboBoxModel dcm= new DefaultComboBoxModel();
            ;
            for(int i=0;i<nomMasc.size();i++){
                dcm.addElement(nomMasc.get(i));            
            }
            jComboBox1.setModel(dcm);
        } catch (Exception ex) {
            DefaultComboBoxModel dcm= new DefaultComboBoxModel();
        dcm.addElement("Seleccione Cliente");
        jComboBox1.setModel(dcm);
        }
        
    }
    public int getInteger(String valor){
        int integer = Integer.parseInt(valor);
        return integer;
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
    public void filtrarNombre() {
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
        String filtro = jTextField5.getText();
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
        String filtro = jTextField6.getText();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDialog1 = new javax.swing.JDialog();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jButton4 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        jCalendar1.setBackground(new java.awt.Color(204, 255, 204));
        jCalendar1.setForeground(new java.awt.Color(0, 0, 0));
        jCalendar1.setDecorationBackgroundColor(new java.awt.Color(153, 255, 153));
        jCalendar1.setSundayForeground(new java.awt.Color(0, 0, 0));
        jCalendar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendar1PropertyChange(evt);
            }
        });
        jDialog1.getContentPane().add(jCalendar1, java.awt.BorderLayout.CENTER);

        jButton4.setText("Aceptar");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jDialog1.getContentPane().add(jButton4, java.awt.BorderLayout.PAGE_END);

        jDialog2.setAutoRequestFocus(false);
        jDialog2.setBackground(new java.awt.Color(204, 255, 204));
        jDialog2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBackground(new java.awt.Color(204, 255, 204));
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
        jScrollPane2.setViewportView(jTable1);

        jDialog2.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 440, 200));

        jTextField2.setText("------");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jDialog2.getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Filtrar por nombre");
        jDialog2.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jLabel6.setForeground(new java.awt.Color(0, 153, 0));
        jLabel6.setText("Filtrar por DNI");
        jDialog2.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        jTextField4.setText("------");
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jDialog2.getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));

        jLabel8.setForeground(new java.awt.Color(0, 153, 0));
        jLabel8.setText("Filtrar por apellido");
        jDialog2.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jLabel9.setForeground(new java.awt.Color(0, 153, 0));
        jLabel9.setText("Filtrar por telefono");
        jDialog2.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        jTextField5.setText("------");
        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5MouseClicked(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jDialog2.getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, -1));

        jTextField6.setText("------");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jDialog2.getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, -1));

        jButton5.setBackground(new java.awt.Color(116, 116, 235));
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("Reiniciar Tabla");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jDialog2.getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        jButton6.setBackground(new java.awt.Color(116, 116, 235));
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("Seleccionar");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jDialog2.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, -1, -1));

        jButton7.setBackground(new java.awt.Color(116, 116, 235));
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("Cancelar");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jDialog2.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, -1, -1));

        setBackground(new java.awt.Color(204, 255, 204));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Mascota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 120, 0, 0);
        add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("Hora");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 120, 0, 0);
        add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 30, 0, 0);
        add(jLabel3, gridBagConstraints);

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField3.setText("-----");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 96;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 40, 0, 0);
        add(jTextField3, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setText("Observaciones");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 120, 0, 0);
        add(jLabel5, gridBagConstraints);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 194;
        gridBagConstraints.ipady = 94;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(60, 9, 0, 0);
        add(jScrollPane1, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(116, 116, 235));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Aceptar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 39;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 120, 106, 0);
        add(jButton1, gridBagConstraints);

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 122;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 27, 0, 0);
        add(jComboBox1, gridBagConstraints);

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 66;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 9, 0, 0);
        add(jFormattedTextField1, gridBagConstraints);

        jButton2.setBackground(new java.awt.Color(116, 116, 235));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Seleccionar Fecha");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 0, 0);
        add(jButton2, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 0));
        jLabel7.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 30, 0, 0);
        add(jLabel7, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(116, 116, 235));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Seleccionar Cliente");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 50, 0, 100);
        add(jButton3, gridBagConstraints);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField1.setText("-----");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 96;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 40, 0, 0);
        add(jTextField1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (!validarHora(jFormattedTextField1.getText())) {
            /*Salta error*/
        } else {
            String mascota = (String) jComboBox1.getSelectedItem();
            String hora = jFormattedTextField1.getText();
            String fecha = jTextField1.getText();
            String cliente = jTextField3.getText();
            String observaciones = jTextArea1.getText();
            Map<String, Object> data = new HashMap<>();
            data.put("Cliente", cliente);
            data.put("Mascota", mascota);
            data.put("Fecha", fecha);
            data.put("Hora", hora);
            data.put("Observacion", observaciones);
            

            Database.insertarDatos("Citas", String.valueOf(conseguirCodigo()+1), data);

            
            
        }
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jCalendar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendar1PropertyChange
        
    }//GEN-LAST:event_jCalendar1PropertyChange

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        jDialog1.setModal(true);
        jDialog1.setBounds(0,0,750,500);
        jDialog1.setVisible(true);
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        jTextField1.setText(sd.format(jCalendar1.getCalendar().getTime()));
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        filtrarApellido();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        crearTabla();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
       
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseClicked
        
    }//GEN-LAST:event_jTextField5MouseClicked

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
         filtrarNombre();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        filtrarDNI();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        filtrarTelefono();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        try{
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        dniCliente = tm.getValueAt(jTable1.getSelectedRow(), 2).toString();
        nombreCliente = tm.getValueAt(jTable1.getSelectedRow(), 0).toString();
        jTextField3.setText(nombreCliente);
        jDialog2.setVisible(false);
        llenarCombo();
        }catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(jDialog2,"Seleccione un campo de la tabla","",JOptionPane.WARNING_MESSAGE );
        }
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        jDialog2.setModal(true);
        jDialog2.setBounds(0,0,900,500);
        Color colorRosa=new Color(204,255,204);
        jDialog2.setBackground(colorRosa);
        crearTabla();
        jDialog2.setVisible(true);
        
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        jDialog2.setVisible(false);
    }//GEN-LAST:event_jButton7MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

     
}
