/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author L E N O V O
 */
public class Coba {
    private int id;
    private String merk,tipe,harga,jumlah,subtotal;

    public Coba(int id, String merk, String tipe, String harga, String jumlah, String subtotal) {
        this.id = id;
        this.merk = merk;
        this.tipe = tipe;
        this.harga = harga;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public String getMerk() {
        return merk;
    }

    public String getTipe() {
        return tipe;
    }

    public String getHarga() {
        return harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getSubtotal() {
        return subtotal;
    }
    
    
    
}
