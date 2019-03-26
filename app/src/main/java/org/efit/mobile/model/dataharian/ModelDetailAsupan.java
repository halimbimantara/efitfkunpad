package org.efit.mobile.model.dataharian;

public class ModelDetailAsupan {
    private int id_detail_asupan;
    private String bahan_makanan;
    private int jumlah;
    private String jam_makan;
    private String energi;

    public int getId_detail_asupan() {
        return id_detail_asupan;
    }

    public void setId_detail_asupan(int id_detail_asupan) {
        this.id_detail_asupan = id_detail_asupan;
    }

    public String getBahan_makanan() {
        return bahan_makanan;
    }

    public void setBahan_makanan(String bahan_makanan) {
        this.bahan_makanan = bahan_makanan;
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

    public String getEnergi() {
        return energi;
    }

    public void setEnergi(String energi) {
        this.energi = energi;
    }
}
