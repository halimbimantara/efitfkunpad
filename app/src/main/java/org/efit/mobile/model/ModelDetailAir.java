package org.efit.mobile.model;

public class ModelDetailAir {
    private int id_user;
    private String status_update;
    private String kode_transaksi;
    private String tanggal;
    private int volume_air;

    public String getStatus_update() {
        return status_update;
    }

    public void setStatus_update(String status_update) {
        this.status_update = status_update;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public double getVolume_air() {
        return volume_air;
    }

    public void setVolume_air(int volume_air) {
        this.volume_air = volume_air;
    }
}
