/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class Koneksi {
    //    static dapat diakses oleh kelas atau objek tanpa harus intantinasi
    private static Connection conn;
    private static Statement statement;
    private static PreparedStatement ps;
    private static final String DB_NAME = "kedai_hp";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME;
    private static final String DB_UNAME = "root";
    private static final String DB_PASS = "";
    
    public static Connection connect(){
        try{
//            buka koneksi
            conn = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PASS);
            statement =  conn.createStatement();
            return conn;
        }catch(SQLException e){
//            akan keluar jika koneksi ke database error
            JOptionPane.showMessageDialog(null, e.toString()+"\n\nKoneksi ke database gagal", "Database Error", 0);
            System.exit(0);
            return null;
        }
    }
    
    
}
