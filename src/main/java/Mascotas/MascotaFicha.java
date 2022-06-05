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
            System.out.println("Step1");
            mascotas=(ArrayList<String>)documento.getData().get("Mascotas");
            System.out.println(mascotas.get(0));
            if(mascotas.get(0).equals("Aun no se han asignado mascotas")){
                mascotas.set(0, codigoMascota);
            }else{
                mascotas.add(codigoMascota);
            }
            System.out.println(mascotas.get(0));
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jDialog1.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, 20));

        jLabel13.setText("Filtrar por DNI");
        jDialog1.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, 20));

        jLabel14.setText("Filtrar por telefono");
        jDialog1.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, 20));

        jTextField8.setText("jTextField8");
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

        jTextField9.setText("jTextField9");
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

        jTextField10.setText("jTextField10");
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

        jTextField11.setText("jTextField11");
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

        jButton7.setText("Aceptar");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jDialog1.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jButton8.setText("Volver");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jDialog1.getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, -1, -1));

        jButton9.setText("Reiniciar Tabla");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jDialog1.getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 20));

        jLabel2.setText("Edad");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 40, 20));

        jLabel3.setText("Especie");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 40, 20));

        jLabel4.setText("Raza");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 40, 20));

        jLabel5.setText("Codigo");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 40, 20));

        jLabel6.setText("Chip");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 40, 20));

        jTextField1.setText("------");
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jTextField2.setText("------");
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        jTextField3.setText("------");
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jTextField4.setText("------");
        add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        jTextField5.setEditable(false);
        jTextField5.setText("------");
        jTextField5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField5PropertyChange(evt);
            }
        });
        add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jTextField6.setText("------");
        add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jButton1.setText("Historial");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jButton2.setText("Informes");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, -1, -1));

        jLabel7.setText("Caracter");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel8.setText("Sexo");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        jLabel9.setText("Esterilizacion");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        Caracter.add(jRadioButton1);
        jRadioButton1.setText("Tranquilo");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        Caracter.add(jRadioButton2);
        jRadioButton2.setText("Agresivo");
        add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        Caracter.add(jRadioButton3);
        jRadioButton3.setText("Nervioso");
        add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        Sexo.add(jRadioButton4);
        jRadioButton4.setText("Macho");
        add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, -1));

        Sexo.add(jRadioButton5);
        jRadioButton5.setText("Hembra");
        add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, -1));

        Esterilizacion.add(jRadioButton6);
        jRadioButton6.setText("Si");
        add(jRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        Esterilizacion.add(jRadioButton7);
        jRadioButton7.setText("No");
        add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));

        jButton3.setText("Acceder Cliente");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        jButton4.setText("Añadir");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        jButton5.setText("Eliminar");
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        jLabel10.setText("Cliente");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jTextField7.setText("------");
        add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jButton6.setText("Volver");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        Informes.setModal(true);
        Informes.setVisible(true);
        
    }//GEN-LAST:event_jButton2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Caracter;
    private javax.swing.ButtonGroup Esterilizacion;
    private javax.swing.JDialog Informes;
    private javax.swing.ButtonGroup Sexo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
