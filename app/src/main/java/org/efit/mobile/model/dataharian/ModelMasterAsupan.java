package org.efit.mobile.model.dataharian;

public class ModelMasterAsupan {
    private int id_master_asupan;
    private String kode_transaksi;
    private String tanggal;
    private String status;
    private int id_user;

    public int getId_master_asupan() {
        return id_master_asupan;
    }

    public void setId_master_asupan(int id_master_asupan) {
        this.id_master_asupan = id_master_asupan;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
