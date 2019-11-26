/*
 * To change this license header, choose License Headeres in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author L E N O V O
 */
public class Transaksi extends javax.swing.JFrame {
private DefaultTableModel TabModel;
    
    private Connection con;
    private Statement stat;
    private ResultSet res;
    private String t;
    private void koneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/kedai_hp", "root", "");
            stat=(Statement) con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        setJam();
        SiapIsi(false);
        TombolNormal();
        
        Object header[]={"KODE HP","MERK HP","TIPE","HARGA","JUMLAH","SUBTOTAL"};
        TabModel=new DefaultTableModel(null, header);
        
    }
    
    public final void setJam(){
        ActionListener taskPerformer = new ActionListener() {

            @Override
        public void actionPerformed(ActionEvent evt) {
        String nol_jam = "", nol_menit = "",nol_detik = "";

        java.util.Date dateTime = new java.util.Date();
        int nilai_jam = dateTime.getHours();
        int nilai_menit = dateTime.getMinutes();
        int nilai_detik = dateTime.getSeconds();

        if(nilai_jam <= 9) nol_jam= "0";
        if(nilai_menit <= 9) nol_menit= "0";
        if(nilai_detik <= 9) nol_detik= "0";

        String jam = nol_jam + Integer.toString(nilai_jam);
        String menit = nol_menit + Integer.toString(nilai_menit);
        String detik = nol_detik + Integer.toString(nilai_detik);

        labeljam.setText(jam+":"+menit+"");
        }
        };
        new Timer(1000, taskPerformer).start();
    }
    
    private void SiapIsi(boolean a){
        notransaksi.setEnabled(a);
        kodeHp.setEnabled(a);
        merkHp.setEnabled(a);
        tipe.setEnabled(a);
        txharga.setEnabled(a);
        jumlah.setEnabled(a);
        subtotal.setEnabled(a);
        txtotal.setEnabled(a);
        txbayar.setEnabled(a);
        txkembalian.setEnabled(a);
        txstok.setEnabled(a);
        
    }
    
    private void TombolNormal(){
        btonoff.setEnabled(true);
        btadd.setEnabled(false);
        btproses.setEnabled(false);        
        btinventori.setEnabled(false);
    }
    
    private void beresih(){
        notransaksi.setText("");
        kodeHp.setText("");
        merkHp.setText("");
        tipe.setText("");
        txharga.setText("");
        jumlah.setText("");
        subtotal.setText("0");
        txtotal.setText("0");
        txbayar.setText("0");
        txkembalian.setText("");
        txstok.setText("");
        
    }
    
    private void notransaksi(){
       try{
            koneksi();
            String sql="select right(notransaksi,2)+1 from transaksi";
            res = stat.executeQuery(sql);
            if(res.next()){
                res.last();
                String no=res.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    notransaksi.setText("TR"+no);
                }
            } else {
                notransaksi.setText("TR001");
            }
        } catch (Exception e){
        }
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
                    +txtotal.getText()+"','"
                    +txbayar.getText()+"','"
                    +txkembalian.getText()+"')";
            
             stat.executeUpdate(sql);
             
            }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SIMPAN TRANSAKSI PENJUALAN GAGAL");
        }
        
    }
    
    private void perbaruistok(){
        try{
            koneksi();
            String sql="update inventori set stok='"+txstok.getText()
                    +"' where idbarang='"+kodeHp.getText()+"'";
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Stok Diperbarui","",JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(Exception e){
        }
        tabeltransaksi();
    }
    
    public void tabeltransaksi(){
        Object header[]={"KODE HP","TANGGAL","MERK HP","TIPE","HARGA","JUMLAH","SUBTOTAL","TOTAL","BAYAR","KEMBALIAN"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabeltransaksi.setModel(data);
        koneksi();
        String sql="select*from transaksi";
        try {
            res=stat.executeQuery(sql);
            while (res.next())
            {
                String kolom1=res.getString(1);
                String kolom2=res.getString(2);
                String kolom3=res.getString(3);
                String kolom4=res.getString(4);
                String kolom5=res.getString(5);
                String kolom6=res.getString(6);
                String kolom7=res.getString(7);
                String kolom8=res.getString(8);
                String kolom9=res.getString(9);
                String kolom10=res.getString(10);
                String kolom11=res.getString(11);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,kolom11};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }
    
    public void tabelinventory(){
//        Object header[]={"KODE HP","MERK HP","TIPE","HARGA","STOK"};
//        DefaultTableModel data=new DefaultTableModel(null,header);
//        tabelinventory.setModel(data);
//        koneksi();
//        String sql="select*from inventori";
//        try {
//            ResultSet res=stat.executeQuery(sql);
//            while (res.next())
//            {
//                String kolom1=res.getString(1);
//                String kolom2=res.getString(2);
//                String kolom3=res.getString(3);
//                String kolom4=res.getString(4);
//                String kolom5=res.getString(3);
//                
//                
//                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5};
//                data.addRow(kolom);
//            }
//        } catch (Exception e) {
//        }

        DefaultTableModel t= new DefaultTableModel();
        t.addColumn("Kode Hp"); 
        t.addColumn("Merk Hp"); 
        t.addColumn("Tipe");
        t.addColumn("Jumlah"); 
        t.addColumn("Tahun"); 
        t.addColumn("Harga");
        t.addColumn("Tanggal");
        tabelinventory.setModel(t); 
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
    
    public void hitungstok(){
        int jumlahbeli=Integer.parseInt(jumlah.getText());
        int stok=Integer.parseInt(txstok.getText());
        
        int total=jumlahbeli-stok;
        txstok.setText(Integer.toString(total));
    }
    
    public void ambildata() {
        try {
            tabelsementara.setModel(TabModel);
                String kolom1 = kodeHp.getText();
                String kolom2 = merkHp.getText();
                String kolom3 = tipe.getText();
                String kolom4 = txharga.getText();                
                String kolom5 = jumlah.getText();
                String kolom6 = subtotal.getText();
                String[] kolom = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6};
                TabModel.addRow(kolom);
          }
          catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Data gagal disimpan");
          }     
    }
    
    public void nota(){
        try {
            String NamaFile = "src/report/nota.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            java.sql.Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kedai_hp","root","");
            HashMap param = new HashMap();
            param.put("ptrans",notransaksi.getText());
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, con);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
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

        jDialogtabelinventory = new javax.swing.JDialog();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelinventory = new javax.swing.JTable();
        txpencarianinventory = new javax.swing.JTextField();
        jDialogtabeltransaksi = new javax.swing.JDialog();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabeltransaksi = new javax.swing.JTable();
        txpencariantransaksi = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtotal = new javax.swing.JTextField();
        txkembalian = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txbayar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelsementara = new javax.swing.JTable();
        btproses = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        notransaksi = new javax.swing.JTextField();
        btonoff = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        merkHp = new javax.swing.JTextField();
        kodeHp = new javax.swing.JTextField();
        tipe = new javax.swing.JTextField();
        txharga = new javax.swing.JTextField();
        jumlah = new javax.swing.JTextField();
        subtotal = new javax.swing.JTextField();
        btinventori = new javax.swing.JButton();
        bttransaksi = new javax.swing.JButton();
        btadd = new javax.swing.JButton();
        btminimize = new javax.swing.JButton();
        btexit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labeljam = new javax.swing.JLabel();
        txstok = new javax.swing.JTextField();

        jDialogtabelinventory.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogtabelinventory.setBackground(new java.awt.Color(0, 0, 51));
        jDialogtabelinventory.setMinimumSize(new java.awt.Dimension(694, 430));
        jDialogtabelinventory.setModal(true);
        jDialogtabelinventory.setResizable(false);

        jInternalFrame1.setTitle("TABEL INVENTORI");
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(694, 430));
        jInternalFrame1.setVisible(true);
        jInternalFrame1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInternalFrame1MouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(0, 0, 51));

        tabelinventory.setAutoCreateRowSorter(true);
        tabelinventory.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tabelinventory.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelinventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelinventoryMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelinventory);

        txpencarianinventory.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        txpencarianinventory.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpencarianinventory.setText("KOLOM PENCARIAN");
        txpencarianinventory.setPreferredSize(new java.awt.Dimension(87, 30));
        txpencarianinventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpencarianinventoryActionPerformed(evt);
            }
        });
        txpencarianinventory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpencarianinventoryKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txpencarianinventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txpencarianinventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialogtabelinventoryLayout = new javax.swing.GroupLayout(jDialogtabelinventory.getContentPane());
        jDialogtabelinventory.getContentPane().setLayout(jDialogtabelinventoryLayout);
        jDialogtabelinventoryLayout.setHorizontalGroup(
            jDialogtabelinventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogtabelinventoryLayout.setVerticalGroup(
            jDialogtabelinventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogtabeltransaksi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogtabeltransaksi.setBackground(new java.awt.Color(0, 0, 51));
        jDialogtabeltransaksi.setMinimumSize(new java.awt.Dimension(1079, 430));
        jDialogtabeltransaksi.setModal(true);
        jDialogtabeltransaksi.setResizable(false);

        jInternalFrame2.setTitle("TABEL TRANSAKSI");
        jInternalFrame2.setPreferredSize(new java.awt.Dimension(694, 430));
        jInternalFrame2.setVisible(true);
        jInternalFrame2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInternalFrame2MouseClicked(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(0, 0, 51));

        tabeltransaksi.setAutoCreateRowSorter(true);
        tabeltransaksi.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tabeltransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeltransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeltransaksiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabeltransaksi);

        txpencariantransaksi.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        txpencariantransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txpencariantransaksi.setText("KOLOM PENCARIAN");
        txpencariantransaksi.setPreferredSize(new java.awt.Dimension(87, 30));
        txpencariantransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpencariantransaksiKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txpencariantransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txpencariantransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialogtabeltransaksiLayout = new javax.swing.GroupLayout(jDialogtabeltransaksi.getContentPane());
        jDialogtabeltransaksi.getContentPane().setLayout(jDialogtabeltransaksiLayout);
        jDialogtabeltransaksiLayout.setHorizontalGroup(
            jDialogtabeltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame2, javax.swing.GroupLayout.DEFAULT_SIZE, 1079, Short.MAX_VALUE)
        );
        jDialogtabeltransaksiLayout.setVerticalGroup(
            jDialogtabeltransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setText("TOTAL");

        jLabel10.setText("KEMBALIAN");

        txbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txbayarActionPerformed(evt);
            }
        });
        txbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txbayarKeyPressed(evt);
            }
        });

        jLabel11.setText("BAYAR");

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
        jScrollPane1.setViewportView(tabelsementara);

        btproses.setText("PROSES");
        btproses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btprosesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(33, 33, 33)
                                .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btproses)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btproses))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("No Transaksi");

        notransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notransaksiActionPerformed(evt);
            }
        });

        btonoff.setText("ON");
        btonoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonoffActionPerformed(evt);
            }
        });

        jLabel3.setText("Kode HP");

        jLabel4.setText("Tipe");

        jLabel5.setText("Merk HP");

        jLabel6.setText("Harga");

        jLabel7.setText("Jumlah");

        jLabel8.setText("Sub Total");

        merkHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                merkHpActionPerformed(evt);
            }
        });

        txharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txhargaActionPerformed(evt);
            }
        });

        btinventori.setText("INVENTORI");
        btinventori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btinventoriActionPerformed(evt);
            }
        });

        bttransaksi.setText("TRANSAKSI");
        bttransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttransaksiActionPerformed(evt);
            }
        });

        btadd.setText("ADD TO >");
        btadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddActionPerformed(evt);
            }
        });

        btminimize.setText("MINIMIZE");
        btminimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btminimizeActionPerformed(evt);
            }
        });

        btexit.setText("EXIT");
        btexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(notransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btonoff, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tipe)
                            .addComponent(kodeHp)
                            .addComponent(txharga)
                            .addComponent(jumlah)
                            .addComponent(subtotal)
                            .addComponent(merkHp))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btinventori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bttransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                                    .addComponent(btexit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btadd, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(btminimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(notransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btonoff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodeHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(merkHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btinventori)
                    .addComponent(btadd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttransaksi)
                    .addComponent(btminimize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btexit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Transaksi");

        labeljam.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        labeljam.setForeground(new java.awt.Color(204, 204, 204));
        labeljam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeljam.setText("JAM");

        txstok.setText("STOK");
        txstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txstokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labeljam, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txstok, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labeljam, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void notransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notransaksiActionPerformed

    private void merkHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_merkHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_merkHpActionPerformed

    private void txbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txbayarActionPerformed

    private void txstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txstokActionPerformed

    private void btonoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonoffActionPerformed
        // TODO add your handling code here:
        if(btonoff.getText().equalsIgnoreCase("ON")){
            btonoff.setText("REFRESH");
            beresih();
            SiapIsi(true);
          
            notransaksi();
            btadd.setEnabled(true);
            btinventori.setEnabled(true);
            btonoff.setEnabled(true);
            btproses.setEnabled(true);
            
        } else{
            btonoff.setText("ON");
            beresih();
            SiapIsi(false);
            TombolNormal();
            tabelinventory();
            TabModel.getDataVector().removeAllElements();
            TabModel.fireTableDataChanged();
        }
    }//GEN-LAST:event_btonoffActionPerformed

    private void btaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddActionPerformed
        // TODO add your handling code here:
        int harga=Integer.parseInt(txharga.getText());
        int jml=Integer.parseInt(jumlah.getText());
        int stok=Integer.parseInt(txstok.getText());
        int total=Integer.parseInt(txtotal.getText());

        if(jml>stok){
            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
        }else{

            int subtot=harga*jml;
            subtotal.setText(Integer.toString(subtot));

            int hasilstok=stok-jml;
            txstok.setText(Integer.toString(hasilstok));

            int totbay=total+(harga*jml);
            txtotal.setText(Integer.toString(totbay));

            ambildata();
            
        }
    }//GEN-LAST:event_btaddActionPerformed

    private void btinventoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btinventoriActionPerformed
        // TODO add your handling code here:
        jDialogtabelinventory.setLocationRelativeTo(null);
        tabelinventory();
        jDialogtabelinventory.setVisible(true);
    }//GEN-LAST:event_btinventoriActionPerformed

    private void bttransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttransaksiActionPerformed
        // TODO add your handling code here:
        jDialogtabeltransaksi.setLocationRelativeTo(null);
        tabeltransaksi();
        jDialogtabeltransaksi.setVisible(true);
    }//GEN-LAST:event_bttransaksiActionPerformed

    private void btminimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btminimizeActionPerformed
        // TODO add your handling code here:
       setState(ICONIFIED);
    }//GEN-LAST:event_btminimizeActionPerformed

    private void btexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btexitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btexitActionPerformed

    private void btprosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btprosesActionPerformed
        // TODO add your handling code here:
        if(notransaksi.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi inputan penjualan barang");
        } else{
            proses();
            int pesan=JOptionPane.showConfirmDialog(null, "Print Out Nota?","Print",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(pesan==JOptionPane.YES_OPTION){
                nota();
            }else {
                JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
            }
            perbaruistok();
            beresih();    
        } 
    }//GEN-LAST:event_btprosesActionPerformed

    private void txbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int total=Integer.parseInt(txtotal.getText());
            int bayar=Integer.parseInt(txbayar.getText());
            if(bayar<total){
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                txbayar.requestFocus();
            } else{
                int kembali=bayar-total;
                txkembalian.setText(Integer.toString(kembali));
            }
        }
    }//GEN-LAST:event_txbayarKeyPressed

    private void tabelsementaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelsementaraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelsementaraMouseClicked

    private void tabelinventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelinventoryMouseClicked
        // TODO add your handling code here:
        int baris = tabelinventory.getSelectedRow();
        kodeHp.setText(tabelinventory.getModel().getValueAt(baris, 0).toString());
        merkHp.setText(tabelinventory.getModel().getValueAt(baris, 1).toString());
        tipe.setText(tabelinventory.getModel().getValueAt(baris, 2).toString());
        txharga.setText(tabelinventory.getModel().getValueAt(baris, 5).toString());
        txstok.setText(tabelinventory.getModel().getValueAt(baris, 4).toString());
        jDialogtabelinventory.dispose();
    }//GEN-LAST:event_tabelinventoryMouseClicked

    private void txpencarianinventoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpencarianinventoryKeyPressed
        // TODO add your handling code here:
        Object header[]={"KODE HP","MERK HP","TIPE","HARGA","STOK"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabelinventory.setModel(data);
        koneksi();
        String sql="Select * from inventori where kodeHp like '%" + txpencarianinventory.getText() + "%'" + "or nama like '%" + txpencarianinventory.getText()+"%'";
        try {
            res=stat.executeQuery(sql);
            while (res.next())
            {
                String kolom1=res.getString(1);
                String kolom2=res.getString(2);
                String kolom3=res.getString(3);
                String kolom4=res.getString(4);
                String kolom5=res.getString(5);
                

                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5};
                data.addRow(kolom);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txpencarianinventoryKeyPressed

    private void jInternalFrame1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrame1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame1MouseClicked

    private void tabeltransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeltransaksiMouseClicked
        // TODO add your handling code here:

        int baris = tabeltransaksi.getSelectedRow();
        notransaksi.setText(tabeltransaksi.getModel().getValueAt(baris, 0).toString());
        kodeHp.setText(tabeltransaksi.getModel().getValueAt(baris, 1).toString());
        merkHp.setText(tabeltransaksi.getModel().getValueAt(baris, 2).toString());
        tipe.setText(tabeltransaksi.getModel().getValueAt(baris, 3).toString());
        txharga.setText(tabeltransaksi.getModel().getValueAt(baris, 4).toString());
        jumlah.setText(tabeltransaksi.getModel().getValueAt(baris, 5).toString());
        subtotal.setText(tabeltransaksi.getModel().getValueAt(baris, 6).toString());
        txtotal.setText(tabeltransaksi.getModel().getValueAt(baris, 7).toString());
        txbayar.setText(tabeltransaksi.getModel().getValueAt(baris, 8).toString());
        txkembalian.setText(tabeltransaksi.getModel().getValueAt(baris, 9).toString());
        jDialogtabeltransaksi.dispose();
        nota();
    }//GEN-LAST:event_tabeltransaksiMouseClicked

    private void txpencariantransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpencariantransaksiKeyPressed
        // TODO add your handling code here:
        Object header[]={"IDTRANS","IDBARANG","TANGGAL","NAMA","HARGA","JUMLAH","SUBTOTAL","TOTAL","BAYAR","KEMBALIAN"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        tabeltransaksi.setModel(data);
        koneksi();
        String sql="Select * from tb_transaksi where notransaksi like '%" + txpencariantransaksi.getText() + "%'" + "or idbarang like '%" + txpencariantransaksi.getText()+"%'";
        try {
            ResultSet res=stat.executeQuery(sql);
            while (res.next())
            {
                String kolom1=res.getString(1);
                String kolom2=res.getString(2);
                String kolom3=res.getString(3);
                String kolom4=res.getString(4);
                String kolom5=res.getString(5);
                String kolom6=res.getString(6);
                String kolom7=res.getString(7);
                String kolom8=res.getString(8);
                String kolom9=res.getString(9);
                String kolom10=res.getString(10);

                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8,kolom9,kolom10,};
                data.addRow(kolom);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txpencariantransaksiKeyPressed

    private void jInternalFrame2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrame2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jInternalFrame2MouseClicked

    private void txpencarianinventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpencarianinventoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpencarianinventoryActionPerformed

    private void txhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txhargaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txhargaActionPerformed
       
    
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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btadd;
    private javax.swing.JButton btexit;
    private javax.swing.JButton btinventori;
    private javax.swing.JButton btminimize;
    private javax.swing.JButton btonoff;
    private javax.swing.JButton btproses;
    private javax.swing.JButton bttransaksi;
    private javax.swing.JDialog jDialogtabelinventory;
    private javax.swing.JDialog jDialogtabeltransaksi;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField kodeHp;
    private javax.swing.JLabel labeljam;
    private javax.swing.JTextField merkHp;
    private javax.swing.JTextField notransaksi;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tabelinventory;
    private javax.swing.JTable tabelsementara;
    private javax.swing.JTable tabeltransaksi;
    private javax.swing.JTextField tipe;
    private javax.swing.JTextField txbayar;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txkembalian;
    private javax.swing.JTextField txpencarianinventory;
    private javax.swing.JTextField txpencariantransaksi;
    private javax.swing.JTextField txstok;
    private javax.swing.JTextField txtotal;
    // End of variables declaration//GEN-END:variables
}
