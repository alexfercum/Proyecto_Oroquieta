/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Mascotas;

import Clientes.ClientesForm;
import Database.Database;
import Principal.VentanaPrinc;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class MascotaFicha extends javax.swing.JPanel {

    /**
     * Creates new form MascotaFicha
     */
    VentanaPrinc padre;
    private List<QueryDocumentSnapshot> documents;
    private long codigo = 0;
    private long codigoActual;
    private String dniCliente;
    private String nombreCliente;

    public MascotaFicha() {
        initComponents();
    }

    public MascotaFicha(VentanaPrinc frame, boolean editable) {
        initComponents();
        reiniciarCampos();
        jTextField5.setText(String.valueOf(conseguirCodigo() + 1));
        dniCliente = "";
        nombreCliente = "";
        jButton3.setText("Seleccionar Cliente");
        init();
        habilitarEdicion(editable);
        padre = frame;
    }

    

    public MascotaFicha(VentanaPrinc frame, boolean editable, Object[] fila) {
        dniCliente = (String) fila[9];
        initComponents();
        init();
        habilitarEdicion(editable);
        jButton4.setText("Modificar");
        jTextField1.setText((String) fila[0]);
        jTextField2.setText((String) fila[1]);
        jTextField3.setText((String) fila[2]);
        jTextField4.setText((String) fila[3]);
        jTextField5.setText(String.valueOf(fila[4]));
        jTextField6.setText((String) fila[5]);
        getNombreCliente();
        jTextField7.setText(nombreCliente);

        dniCliente = (String) fila[9];

        if (jRadioButton1.getText().equals((String) fila[6])) {
            jRadioButton1.setSelected(true);
        }
        if (jRadioButton2.getText().equals((String) fila[6])) {
            jRadioButton2.setSelected(true);
        }
        if (jRadioButton3.getText().equals((String) fila[6])) {
            jRadioButton3.setSelected(true);
        }

        if (jRadioButton4.getText().equals((String) fila[7])) {
            jRadioButton4.setSelected(true);
        }
        if (jRadioButton5.getText().equals((String) fila[7])) {
            jRadioButton5.setSelected(true);
        }

        if (jRadioButton6.getText().equals((String) fila[8])) {
            jRadioButton6.setSelected(true);
        }
        if (jRadioButton7.getText().equals((String) fila[8])) {
            jRadioButton7.setSelected(true);
        }

        padre = frame;
    }

    public void getNombreCliente() {
        try {
            Firestore db = Database.getDatabase();
            DocumentReference docRef = db.collection("Clientes").document(dniCliente);

            DocumentSnapshot documento = docRef.get().get();

            nombreCliente = (String) documento.getData().get("Nombre");

        } catch (InterruptedException ex) {
            nombreCliente = "";
        } catch (ExecutionException ex) {
            nombreCliente = "";
        }

    }

    public void init() {
        jRadioButton1.setActionCommand("Tranquilo");
        jRadioButton2.setActionCommand("Agresivo");
        jRadioButton3.setActionCommand("Nervioso");
        jRadioButton4.setActionCommand("Macho");
        jRadioButton5.setActionCommand("Hembra");
        jRadioButton6.setActionCommand("Si");
        jRadioButton7.setActionCommand("No");

    }

    public void reiniciarCampos() {
        jTextField1.setText("----");
        jTextField2.setText("----");
        jTextField3.setText("----");
        jTextField4.setText("----");
        jTextField6.setText("----");
        Caracter.clearSelection();
        Sexo.clearSelection();
        Esterilizacion.clearSelection();

    }

    public void rellenarCampos(Object[] fila) {
        jTextField1.setText((String) fila[0]);
        jTextField2.setText((String) fila[1]);
        jTextField3.setText((String) fila[2]);
        jTextField4.setText((String) fila[3]);
        jTextField5.setText(String.valueOf(fila[4]));
        jTextField6.setText((String) fila[5]);
        if (fila[6].equals("Tranquilo")) {
            jRadioButton1.setSelected(true);
        }
        if (fila[6].equals("Agresivo")) {
            jRadioButton2.setSelected(true);
        }
        if (fila[6].equals("Nervioso")) {
            jRadioButton3.setSelected(true);
        }
        if (fila[7].equals("Macho")) {
            jRadioButton4.setSelected(true);
        }
        if (fila[7].equals("Hembra")) {
            jRadioButton5.setSelected(true);
        }
        if (fila[8].equals("Si")) {
            jRadioButton6.setSelected(true);
        }
        if (fila[8].equals("No")) {
            jRadioButton7.setSelected(true);
        }

    }

    public void habilitarEdicion(Boolean editable) {
        jTextField1.setEditable(editable);
        jTextField2.setEditable(editable);
        jTextField3.setEditable(editable);
        jTextField4.setEditable(editable);
        jTextField6.setEditable(editable);
        jRadioButton1.setEnabled(editable);
        jRadioButton2.setEnabled(editable);
        jRadioButton3.setEnabled(editable);
        jRadioButton4.setEnabled(editable);
        jRadioButton5.setEnabled(editable);
        jRadioButton6.setEnabled(editable);
        jRadioButton7.setEnabled(editable);
    }

    public void asociarCliente(String codigoMascota){
        try {
            Firestore db = Database.getDatabase();
            DocumentReference docRef = db.collection("Clientes").document(dniCliente);
            DocumentSnapshot documento = docRef.get().get();            
            ArrayList<String> mascotas= new ArrayList();
            
            mascotas=(ArrayList<String>)documento.getData().get("Mascotas");
            
            if(mascotas.get(0).equals("Aun no se han asignado mascotas")){
                mascotas.set(0, codigoMascota);
            }else{
                mascotas.add(codigoMascota);
            }
            
            docRef.update("Mascotas", mascotas);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MascotaFicha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(MascotaFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public long conseguirCodigo() {
        documents = Database.accederDB("Mascotas");
        if (!documents.isEmpty()) {
            codigo = documents.size();
            return (codigo);
        }
        return (0);
    }

    public void anadirMascota(boolean add) {
        String nombre = jTextField1.getText();
        String edad = jTextField2.getText();
        String especie = jTextField3.getText();
        String Chip = jTextField4.getText();
        String raza = jTextField6.getText();
        String caracter = Caracter.getSelection().getActionCommand();
        String sexo = Sexo.getSelection().getActionCommand();
        String esterilizacion = Esterilizacion.getSelection().getActionCommand();

        Map<String, Object> data = new HashMap<>();
        long Codigo;
        data.put("Nombre", nombre);
        data.put("Edad", edad);
        data.put("Especie", especie);
        data.put("Chip", Chip);
        if (add) {
            Codigo = conseguirCodigo() + 1;
            data.put("Codigo", Codigo);            
        } else {
            Codigo = codigoActual;
            data.put("Codigo", Codigo);
        }
        data.put("Raza", raza);
        data.put("Cliente", dniCliente);
        data.put("Caracter", caracter);
        data.put("Sexo", sexo);
        data.put("Esterilizacion", esterilizacion);

        Database.insertarDatos("Mascotas", String.valueOf(Codigo), data);
        
        jTextField5.setText(String.valueOf(conseguirCodigo()));
        if(add)
            asociarCliente(String.valueOf(Codigo));

    }

    /*-------------Metodos del dialog------------------*/
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
        String filtro = jTextField8.getText();
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
        String filtro = jTextField9.getText();
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
        String filtro = jTextField10.getText();
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
        String filtro = jTextField11.getText();
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

        Caracter = new javax.swing.ButtonGroup();
        Sexo = new javax.swing.ButtonGroup();
        Esterilizacion = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        Informes = new javax.swing.JDialog();
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        jDialog1.setBackground(new java.awt.Color(204, 255, 204));
        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane1.setViewportView(jTable1);

        jDialog1.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 410, 180));

        jLabel11.setText("Filtrar por nombre");
        jDialog1.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 20));

        jLabel12.setText("Filtrar por apellido");
        jDialog1.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, 20));

        jLabel13.setText("Filtrar por DNI");
        jDialog1.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, 20));

        jLabel14.setText("Filtrar por telefono");
        jDialog1.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, 20));

        jTextField8.setText("-----");
        jTextField8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField8MouseClicked(evt);
            }
        });
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, -1));

        jTextField9.setText("-----");
        jTextField9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField9MouseClicked(evt);
            }
        });
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, -1));

        jTextField10.setText("-----");
        jTextField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10MouseClicked(evt);
            }
        });
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, -1, -1));

        jTextField11.setText("-----");
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11MouseClicked(evt);
            }
        });
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, -1, -1));

        jLabel15.setText("Seleccione el cliente al que pertenece esta mascota");
        jDialog1.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jButton7.setBackground(new java.awt.Color(116, 116, 235));
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("Aceptar");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jDialog1.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jButton8.setBackground(new java.awt.Color(116, 116, 235));
        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setText("Volver");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jDialog1.getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, -1, -1));

        jButton9.setBackground(new java.awt.Color(116, 116, 235));
        jButton9.setForeground(new java.awt.Color(0, 0, 0));
        jButton9.setText("Reiniciar Tabla");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jDialog1.getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        setBackground(new java.awt.Color(204, 255, 204));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 290, 0, 0);
        add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("Edad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 290, 0, 0);
        add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("Especie");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 290, 0, 0);
        add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Raza");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 30, 0, 0);
        add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setText("Codigo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 30, 0, 0);
        add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 0));
        jLabel6.setText("Chip");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 30, 0, 0);
        add(jLabel6, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField1.setText("------");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 0, 0, 0);
        add(jTextField1, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField2.setText("------");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 0, 0, 0);
        add(jTextField2, gridBagConstraints);

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField3.setText("------");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 0, 0, 0);
        add(jTextField3, gridBagConstraints);

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField4.setText("------");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 0, 0, 0);
        add(jTextField4, gridBagConstraints);

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField5.setText("------");
        jTextField5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField5PropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 0, 0, 0);
        add(jTextField5, gridBagConstraints);

        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField6.setText("------");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 0, 0, 0);
        add(jTextField6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 0));
        jLabel7.setText("Caracter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 0));
        jLabel8.setText("Sexo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 60, 0, 0);
        add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 0));
        jLabel9.setText("Esterilizacion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 120, 0, 0);
        add(jLabel9, gridBagConstraints);

        Caracter.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton1.setText("Tranquilo");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        add(jRadioButton1, gridBagConstraints);

        Caracter.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton2.setText("Agresivo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 0, 0, 0);
        add(jRadioButton2, gridBagConstraints);

        Caracter.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton3.setText("Nervioso");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 0, 0, 0);
        add(jRadioButton3, gridBagConstraints);

        Sexo.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton4.setText("Macho");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 40, 0, 0);
        add(jRadioButton4, gridBagConstraints);

        Sexo.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton5.setText("Hembra");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 40, 0, 0);
        add(jRadioButton5, gridBagConstraints);

        Esterilizacion.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton6.setText("Si");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 170, 0, 0);
        add(jRadioButton6, gridBagConstraints);

        Esterilizacion.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jRadioButton7.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton7.setText("No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 170, 0, 0);
        add(jRadioButton7, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(116, 116, 235));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Acceder Cliente");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 35, 0, 278);
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 61;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(34, 60, 197, 0);
        add(jButton4, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 0));
        jLabel10.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 75, 0, 0);
        add(jLabel10, gridBagConstraints);

        jTextField7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField7.setText("------");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 86;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 65, 0, 0);
        add(jTextField7, gridBagConstraints);

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
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 62;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(34, 20, 197, 0);
        add(jButton6, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        if (jButton3.getText().equals("Seleccionar Cliente")) {
            crearTabla();
            jDialog1.setBounds(0, 0, 750, 500);
            jDialog1.setModal(true);

            jDialog1.setVisible(true);
        } else {
            try {
                Firestore db = Database.getDatabase();
                DocumentReference docRef = db.collection("Clientes").document(dniCliente);

                DocumentSnapshot documento = docRef.get().get();

                ArrayList<String> codMasc = new ArrayList<String>();
                codMasc = (ArrayList<String>) documento.getData().get("Mascotas");

                ArrayList<String> nomMasc = new ArrayList<String>();
                for (String codigo : codMasc) {
                    DocumentReference docRefPet = db.collection("Mascotas").document(codigo);
                    DocumentSnapshot pet = docRefPet.get().get();
                    nomMasc.add((String) pet.getData().get("Nombre"));
                }
                Object[] fila = new Object[]{
                    documento.getData().get("Nombre"),
                    documento.getData().get("Apellidos"),
                    documento.getData().get("DNI"),
                    documento.getData().get("E-mail"),
                    documento.getData().get("Codigo"),
                    documento.getData().get("Telefono"),
                    nomMasc,
                    codMasc};
                padre.showClientesFormNoEditable(fila);
            } catch (InterruptedException ex) {
                Logger.getLogger(MascotaFicha.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(MascotaFicha.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        if (jButton4.getText().equals("Modificar")) {
            /*Añadir option pane*/
            jButton4.setText("Confirmar");
            habilitarEdicion(true);

        } else {
            if (jButton4.getText().equals("Confirmar")) {
                anadirMascota(false);
            } else {
                
                anadirMascota(true);
                habilitarEdicion(false);
                jButton4.setText("Modificar");
                jButton3.setText("Acceder Cliente");
            }
        }
    }//GEN-LAST:event_jButton4MouseClicked

    private void jTextField5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField5PropertyChange
        codigoActual = Long.parseLong(jTextField5.getText());
    }//GEN-LAST:event_jTextField5PropertyChange

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        padre.showMascotasTabla();
    }//GEN-LAST:event_jButton6MouseClicked

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        filtrarNombre();
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8MouseClicked
        
    }//GEN-LAST:event_jTextField8MouseClicked

    private void jTextField9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9MouseClicked
        
    }//GEN-LAST:event_jTextField9MouseClicked

    private void jTextField10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseClicked
        
    }//GEN-LAST:event_jTextField10MouseClicked

    private void jTextField11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseClicked
        
    }//GEN-LAST:event_jTextField11MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        crearTabla();
    }//GEN-LAST:event_jButton9MouseClicked

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        filtrarApellido();
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        filtrarDNI();
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        filtrarTelefono();
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        dniCliente = tm.getValueAt(jTable1.getSelectedRow(), 2).toString();
        nombreCliente = tm.getValueAt(jTable1.getSelectedRow(), 0).toString();
        jTextField7.setText(nombreCliente);
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton7MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Caracter;
    private javax.swing.ButtonGroup Esterilizacion;
    private javax.swing.JDialog Informes;
    private javax.swing.ButtonGroup Sexo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
