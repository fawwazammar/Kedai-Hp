/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author L E N O V O
 */
public class TransaksiKedai extends javax.swing.JFrame {
    
    private DefaultTableModel TabModel;
    
    private Connection con;
    private Statement stat;
    private ResultSet res;
    private Coba coba;
    private void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/kedai_hp", "root", "");
            stat=(Statement) con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    /**
     * Creates new form TransaksiKedai
     */

    }
    
    public TransaksiKedai() {
        initComponents();
        setTitle("Entry Data Hp");
        koneksi();
        tabel();
        setLocationRelativeTo(null);
        
        SiapIsi(false);
        notransaksi();
        
        Object header[]={"KODE HP","MERK HP","TIPE","HARGA","JUMLAH","SUBTOTAL"};
        TabModel=new DefaultTableModel(null, header);
//        ambildatacoba();
//        tampil();
//        tabeltransaksi.setModel(t);
        

    }
    
    private void tabel(){
        t.addColumn("Kode Hp"); 
        t.addColumn("Merk Hp"); 
        t.addColumn("Tipe");
        t.addColumn("Jumlah"); 
        t.addColumn("Tahun"); 
        t.addColumn("Harga");
        t.addColumn("Tanggal");
        tabel.setModel(t); 
        try { 
            res=stat.executeQuery("select * from handphone"); 
                while (res.next()) { 
                    t.addRow(new Object[]{ res.getString("kode_hp"),
                    res.getString("merk_hp"), 
                    res.getString("tipe"), 
                    res.getString("jumlah"), 
                    res.getString("tahun"), 
                    res.getString("harga"),
                    res.getString("tgl_data")
                    }); 
                }
        }catch (Exception e) { 
            JOptionPane.showMessageDialog(rootPane, e); 
        }
    }
    
    private void SiapIsi(boolean a){
        notransaksi.setEnabled(a);
    }
    
    private void notransaksi(){
       try{
           koneksi();
           String sql="select right(notransaksi,2)+1 from transaksi";
           ResultSet rs=stat.executeQuery(sql);
           if(rs.next()){
           rs.last();
           String no=rs.getString(1);
           while (no.length()<3){
               no="0"+no;
               notransaksi.setText("TR"+no);}
       }else{
            notransaksi.setText("TR001");
       }
       } catch (Exception e){
       }
    }
    
    public void ambildata() {
        try {
            tabelsementara.setModel(TabModel);
                String kolom1 = kodeHp.getText();
                String kolom2 = merkHp.getText();
                String kolom3 = tipe.getText();
                String kolom4 = harga.getText();
                String kolom5 = jumlah.getText();
                String kolom6 = subtotal.getText();
                String[] kolom = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6};
                TabModel.addRow(kolom);
          }
          catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Data gagal disimpan");
          }     
    }
    
    public int getStokByKodeHp(String kode){
        int stok = 0;
        try{
            koneksi();
            String sql="SELECT jumlah FROM handphone WHERE kode_hp = "+kode;
            ResultSet rs = stat.executeQuery(sql);
            stok = rs.getInt("jumlah");
            
        } 
        catch(Exception e){
        }
        return stok;
    }
    
    public int hitungstok(){
        int total =0;
        int jumlahbeli=Integer.parseInt(jumlah.getText());
        int stok=getStokByKodeHp(kodeHp.getText());
        
        return total=jumlahbeli-stok;
        
    }
    
    private void perbaruistok(){
        try{
            koneksi();
            String sql="update handphone set jumlah='"+hitungstok()
                    +"' where kode_hp='"+kodeHp.getText()+"'";
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Stok Diperbarui","",JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(Exception e){
        }
        tabel();
        
    }
    
    private void proses(){
        try{
           Date skrg=new Date();
           SimpleDateFormat frm=new SimpleDateFormat("yyyy-MM-dd");
           String tanggal=frm.format(skrg); 
 
            int t = tabelsementara.getRowCount();
             for(int i=0;i<t;i++)    
            {
            String kodeHp=tabelsementara.getValueAt(i, 0).toString();
            String merkHp=tabelsementara.getValueAt(i, 1).toString();
            String tipe=tabelsementara.getValueAt(i, 2).toString();  
            int harga= Integer.parseInt(tabelsementara.getValueAt(i, 3).toString());
            int jml= Integer.parseInt(tabelsementara.getValueAt(i, 4).toString());            
            int subtot= Integer.parseInt(tabelsementara.getValueAt(i, 5).toString());
         
            String sql ="insert into transaksi values('"+notransaksi.getText()
                    +"','"+kodeHp+"','"
                    +tanggal+"','"
                    +merkHp+"','"
                    +tipe+"','"                    
                    +harga+"','"
                    +jml+"','"
                    +subtot+"','"
                    +total.getText()+"','"
                    +bayar.getText()+"','"
                    +kembalian.getText()+"')";
            
             stat.executeUpdate(sql);
             
            }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SIMPAN TRANSAKSI PENJUALAN GAGAL");
        }
    }
    
   
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        notransaksi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        merkHp = new javax.swing.JTextField();
        kodeHp = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        jumlah = new javax.swing.JTextField();
        subtotal = new javax.swing.JTextField();
        kembali = new javax.swing.JButton();
        btexit = new javax.swing.JButton();
        tipe = new javax.swing.JTextField();
        bttransaksi = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelsementara = new javax.swing.JTable();
        total = new javax.swing.JTextField();
        bayar = new javax.swing.JTextField();
        kembalian = new javax.swing.JTextField();
        proses = new javax.swing.JButton();
        cetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode HP", "Merk Hp", "Tipe", "Jumlah", "Tahun", "Harga", "Tanggal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Data Handphone");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("No Transaksi");

        notransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notransaksiActionPerformed(evt);
            }
        });

        jLabel4.setText("Kode HP");

        jLabel5.setText("Tipe");

        jLabel6.setText("Merk HP");

        jLabel7.setText("Harga");

        jLabel10.setText("Jumlah");

        jLabel11.setText("Sub Total");

        merkHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                merkHpActionPerformed(evt);
            }
        });

        kodeHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeHpActionPerformed(evt);
            }
        });

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });

        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });

        subtotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subtotalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                subtotalMouseEntered(evt);
            }
        });
        subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtotalActionPerformed(evt);
            }
        });

        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        btexit.setText("EXIT");
        btexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btexitActionPerformed(evt);
            }
        });

        bttransaksi.setText("TRANSAKSI");
        bttransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jumlah, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(harga, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(merkHp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(kodeHp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(notransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))
                            .addComponent(tipe)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btexit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bttransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kembali, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(notransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodeHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(merkHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(bttransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembali)
                    .addComponent(btexit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("TOTAL");

        jLabel13.setText("KEMBALIAN");

        jLabel14.setText("BAYAR");

        tabelsementara.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelsementara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelsementaraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelsementara);

        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });
        bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bayarKeyPressed(evt);
            }
        });

        proses.setText("PROSES");
        proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prosesActionPerformed(evt);
            }
        });

        cetak.setText("CETAK");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(total)
                            .addComponent(bayar)
                            .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(proses)
                        .addGap(18, 18, 18)
                        .addComponent(cetak)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proses)
                    .addComponent(cetak))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int dipilih = tabel.getSelectedRow();
        kodeHp.setText(t.getValueAt(dipilih, 0).toString());
        merkHp.setText(t.getValueAt(dipilih, 1).toString());
        tipe.setText(t.getValueAt(dipilih, 2).toString());
        harga.setText(t.getValueAt(dipilih, 5).toString());
//        jumlah.setText(t.getValueAt(dipilih, 3).toString());
    }//GEN-LAST:event_tabelMouseClicked

    private void notransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notransaksiActionPerformed

    private void merkHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_merkHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_merkHpActionPerformed

    private void kodeHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeHpActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        new KedaiHp().setVisible(true);
        //dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    private void btexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btexitActionPerformed
        // TODO add your handling code here:
        //dispose();
    }//GEN-LAST:event_btexitActionPerformed

    private void tabelsementaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelsementaraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelsementaraMouseClicked

    private void bttransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttransaksiActionPerformed
        // TODO add your handling code here:

//        int hargaa=Integer.parseInt(harga.getText());
//        int jml=Integer.parseInt(jumlah.getText());
//        int stok=Integer.parseInt(jumlah.getText());
//        int tot=Integer.parseInt(subtotal.getText());
//
//        if(jml>stok){
//            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
//        }else{
//
//            int subtot=hargaa*jml;
//            subtotal.setText(Integer.toString(subtot));
//
//            int hasilstok=stok-jml;
//            jumlah.setText(Integer.toString(hasilstok));
//
//            int totbay=tot+(hargaa*jml);
//            total.setText(Integer.toString(totbay));
//
//        }

            

            int hargaa=Integer.parseInt(harga.getText());
            int jml=Integer.parseInt(jumlah.getText());
            int tot=Integer.parseInt(subtotal.getText());

            int subtot=hargaa*jml;
            subtotal.setText(Integer.toString(subtot));
            total.setText(String.valueOf(subtot));

            ambildata();
            perbaruistok();
            
         

    }//GEN-LAST:event_bttransaksiActionPerformed

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarActionPerformed

    private void bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int totalBayar=Integer.parseInt(total.getText());
            int bayarUang=Integer.parseInt(bayar.getText());
            if(bayarUang<totalBayar){
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                bayar.requestFocus();
            } else{
                int kembali=bayarUang-totalBayar;
                kembalian.setText(Integer.toString(kembali));
            }
        }
    }//GEN-LAST:event_bayarKeyPressed

    private void prosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prosesActionPerformed
        // TODO add your handling code here:
        
        if(notransaksi.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi inputan penjualan barang");
        } else{
            proses();
            int pesan=JOptionPane.showConfirmDialog(null, "Print Out Nota?","Print",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(pesan==JOptionPane.YES_OPTION){
                //nota();
            }else {
                JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
            }    
        } 
    }//GEN-LAST:event_prosesActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtotalActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_subtotalActionPerformed

    private void subtotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subtotalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotalMouseClicked

    private void subtotalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subtotalMouseEntered
        // TODO add your handling code here:
        int Hrg=Integer.parseInt(harga.getText());
        int jml=Integer.parseInt(jumlah.getText());
        int subtot=Hrg*jml;
        subtotal.setText(String.valueOf(subtot));
    }//GEN-LAST:event_subtotalMouseEntered

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cetakActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiKedai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiKedai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiKedai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiKedai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiKedai().setVisible(true);
            }
        });
    }
    
    private DefaultTableModel t= new DefaultTableModel();
//    private ArrayList <Coba> transaksicoba;
//    
//    private void loadtransaksi(){
//        t.addColumn("kode hp");
//        t.addColumn("merk");
//        t.addColumn("tipe");
//        t.addColumn("harga");
//        t.addColumn("jumlah");
//        t.addColumn("subtotal");
//    }
//    private void ambildatacoba(){
//        if(con!=null){
//            transaksicoba=new ArrayList<>();
//            String kueri="SELECT * FROM coba";
//            try{
//                PreparedStatement ps=con.prepareStatement(kueri);
//                ResultSet rs=ps.executeQuery();
//                while(rs.next()){
//                    int id=rs.getInt("id");
//                    String merk=rs.getString("merk");
//                    String tipe =rs.getString("tipe");
//                    String harga =rs.getString("harga");
//                    String jumlah =rs.getString("jumlah");
//                    String subtotal =rs.getString("subtotal");
//                    coba =new Coba(id, merk, tipe,  harga,  jumlah, subtotal);
//                    transaksicoba.add(coba);
//                    
//                }
//                rs.close();
//                ps.close();
//            }catch(SQLException ex){
//                JOptionPane.showMessageDialog(null, "SIMPAN TRANSAKSI PENJUALAN GAGAL");
//            }
//        }
//    }
//    private void tampil(){
//        t.setRowCount(0);
//        for(Coba c:transaksicoba){
//            t.addRow(new Object[]{c.getMerk(),c.getTipe(),c.getHarga(),c.getJumlah(),c.getSubtotal()});
//        }
//    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bayar;
    private javax.swing.JButton btexit;
    private javax.swing.JButton bttransaksi;
    private javax.swing.JButton cetak;
    private javax.swing.JTextField harga;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jumlah;
    private javax.swing.JButton kembali;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField kodeHp;
    private javax.swing.JTextField merkHp;
    private javax.swing.JTextField notransaksi;
    private javax.swing.JButton proses;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tabel;
    private javax.swing.JTable tabelsementara;
    private javax.swing.JTextField tipe;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
