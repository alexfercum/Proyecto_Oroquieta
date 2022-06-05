/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Principal;

import Articulos.ArticulosPrinc;
import Citas.CitasForm;
import Clientes.ClientesForm;
import Clientes.ClientesTabla;
import Database.Database;
import Facturacion.Confirmacion;
import Facturacion.FacturacionForm;

import Mascotas.MascotaFicha;
import Mascotas.MascotasTabla;

/**
 *
 * @author Alex
 */
public class VentanaPrinc extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrinc
     */
    public VentanaPrinc() {
        initComponents();
        init();
    }
    public void init(){
        
        try{
            Database.conexion();
        }catch(Exception e){
           
        }
        showSeleccionModulos();
        
    }
    public void showClientesTabla(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        ClientesTabla t= new ClientesTabla(this);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showClientesFormEditable(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        ClientesForm t= new ClientesForm(this,true);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showClientesFormNoEditable(Object[] fila){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        ClientesForm t= new ClientesForm(this,false,fila);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showMascotasFicha(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        MascotaFicha t= new MascotaFicha(this,true);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showMascotasFichaNoEditable(Object[] fila){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        MascotaFicha t= new MascotaFicha(this,false,fila);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
  
    public void showMascotasTabla(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        MascotasTabla t= new MascotasTabla(this);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showVentanaInicio(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        VentanaInicio t= new VentanaInicio(this);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showSeleccionModulos(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        SeleccionModulos t= new SeleccionModulos(this);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showCitasForm(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        CitasForm t= new CitasForm(this);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showArticulosPrinc(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        ArticulosPrinc t= new ArticulosPrinc(this);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showFacturacionForm(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        FacturacionForm t= new FacturacionForm(this);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    public void showFacturacionConf(){
        this.getContentPane().removeAll();
        this.getContentPane().setVisible(false);
        this.getContentPane().setVisible(true);
        Confirmacion t= new Confirmacion(this);
        t.setVisible(true);
        t.setBounds(0,0,750,500);
        this.getContentPane().add(t);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 450));

        jMenu1.setText("Principal");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Clientes");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Mascotas");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Citas");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });

        jMenu7.setText("Dar cita");
        jMenu4.add(jMenu7);

        jMenu8.setText("Calendario");
        jMenu4.add(jMenu8);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Facturacion");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu6.setText("Atriculos");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        showVentanaInicio();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        showClientesTabla();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        showMascotasTabla();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        showCitasForm();
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        showFacturacionForm();
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        showArticulosPrinc();
    }//GEN-LAST:event_jMenu6MouseClicked

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
            java.util.logging.Logger.getLogger(VentanaPrinc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrinc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrinc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrinc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrinc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
