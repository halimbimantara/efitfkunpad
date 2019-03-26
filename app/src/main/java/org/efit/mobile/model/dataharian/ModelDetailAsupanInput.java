package org.efit.mobile.model.dataharian;

public class ModelDetailAsupanInput {
    private int id_master_asupan;
    private int id_dkbm;
    private String kode_transaksi;
    private double energi;
    private double protein;
    private double vit_c;
    private double vit_a;
    private double calcium;
    private double zinc;
    private int jumlah;
    private String jam_makan;
    private int id_user;

    public double getVit_a() {
        return vit_a;
    }

    public void setVit_a(double vit_a) {
        this.vit_a = vit_a;
    }

    public int getId_master_asupan() {
        return id_master_asupan;
    }

    public void setId_master_asupan(int id_master_asupan) {
        this.id_master_asupan = id_master_asupan;
    }

    public int getId_dkbm() {
        return id_dkbm;
    }

    public void setId_dkbm(int id_dkbm) {
        this.id_dkbm = id_dkbm;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public double getEnergi() {
        return energi;
    }

    public void setEnergi(double energi) {
        this.energi = energi;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getVit_c() {
        return vit_c;
    }

    public void setVit_c(double vit_c) {
        this.vit_c = vit_c;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getZinc() {
        return zinc;
    }

    public void setZinc(double zinc) {
        this.zinc = zinc;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getJam_makan() {
        return jam_makan;
    }

    public void setJam_makan(String jam_makan) {
        this.jam_makan = jam_makan;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
