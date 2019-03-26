package org.efit.mobile.model.dataharian;

import com.google.gson.annotations.SerializedName;

public class ModelDetailOlahraga {
//    @SerializedName("id")
//    private int id;
    @SerializedName("tanggal_input")
    private String tanggal_input;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("jumlah_kalori")
    private String jumlah_kalori;
    @SerializedName("nama_latihan")
    private String nama_latihan;
    @SerializedName("kode_transaksi")
    private String kode_transaksi;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getTanggal_input() {
        return tanggal_input;
    }

    public void setTanggal_input(String tanggal_input) {
        this.tanggal_input = tanggal_input;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getJumlah_kalori() {
        return jumlah_kalori;
    }

    public void setJumlah_kalori(String jumlah_kalori) {
        this.jumlah_kalori = jumlah_kalori;
    }

    public String getNama_latihan() {
        return nama_latihan;
    }

    public void setNama_latihan(String nama_latihan) {
        this.nama_latihan = nama_latihan;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }
}
