/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author acer
 */
public class LoginForm extends javax.swing.JFrame {
    
    private Connection conn;
//    private ResultSet rs;
   
    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Log In");
        this.conn = Koneksi.connect();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bLogin = new javax.swing.JButton();
        tfUsername = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(680, 566));
        setSize(new java.awt.Dimension(680, 566));

        bLogin.setBackground(new java.awt.Color(255, 255, 255));
        bLogin.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        bLogin.setText("Login");
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });

        tfUsername.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        tfUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUsernameActionPerformed(evt);
            }
        });

        pfPassword.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        pfPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfPasswordActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sign In");

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel4.setText("Username");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/user (1).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pfPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tfUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(526, 489));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed
        // TODO add your handling code here:
//        String id = null;
        String user = tfUsername.getText();
        String pass = String.valueOf(pfPassword.getPassword());
       
        if(tfUsername.getText().equals("") || pfPassword.getPassword().toString().equals("")){
            JOptionPane.showMessageDialog(this, "Username Atau Password Tidak Boleh Kosong");
        }else {
            String query = "SELECT * FROM pegawai WHERE username = '"+user+"'";
            try {
                PreparedStatement ps = this.conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                boolean loged = false, akses = false;
                while(rs.next()){
                    if(rs.getString("username").equals(user) && rs.getString("password").equals(pass)){
                        loged = true;
                        if(rs.getString("akses").equals("admin")){
                            akses = true;
                        }
                        break;
                    }
                }
                if(loged == true && akses == true){
                    JOptionPane.showMessageDialog(this, "Login Berhasil");
                    new Admin().setVisible(true);
                    this.dispose();
                }
                else if(loged == true){
                    JOptionPane.showMessageDialog(this, "Login Berhasil");
                    new KedaiHp().setVisible(true);
                    this.dispose();
                }else {
                    JOptionPane.showMessageDialog(this, "Login Gagal");
                }
                rs.close();
                ps.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Terdapat Masalah Ke Database"+ex.toString());
            }
            
//        try{
//            Connection c = Koneksi.connect();
//            Statement s = c.createStatement();
//            String sql = "SELECT username, password FROM pegawai where username='"+tfUsername.getText() + "' and password='"+ pfPassword.getText() +"'";
//            ResultSet rs = s.executeQuery(sql);
//            String status = "SELECT status FROM pegawai where username='"+tfUsername.getText() + "' and password='"+ pfPassword.getText() +"'";
//                
//            int baris = 0;
//            while (rs.next()) {
//                baris = rs.getRow();
//            }
//            
//            
//            if (baris ==1) {
//                JOptionPane.showMessageDialog(null,"Berhasil Login");
//                if("admin".equals(status)){
//                    new Admin().setVisible(true);
//                }
//            }else {
//                JOptionPane.showMessageDialog(null,"Gagal Login");
//            }
//        }catch(SQLException e){
//           
//        }
        }
    }//GEN-LAST:event_bLoginActionPerformed

    private void pfPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfPasswordActionPerformed
        // TODO add your handling code here:
        bLogin.requestFocus();        
    }//GEN-LAST:event_pfPasswordActionPerformed

    private void tfUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUsernameActionPerformed
        // TODO add your handling code here:
        pfPassword.requestFocus();
    }//GEN-LAST:event_tfUsernameActionPerformed
    
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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
