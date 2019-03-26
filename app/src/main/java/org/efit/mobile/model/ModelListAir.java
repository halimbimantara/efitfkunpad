package org.efit.mobile.model;

public class ModelListAir {
    private int id;
    private String kode_trx;
    private int volume_air;
    private String tanggal_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKode_trx() {
        return kode_trx;
    }

    public void setKode_trx(String kode_trx) {
        this.kode_trx = kode_trx;
    }

    public int getVolume_air() {
        return volume_air;
    }

    public void setVolume_air(int volume_air) {
        this.volume_air = volume_air;
    }

    public String getTanggal_time() {
        return tanggal_time;
    }

    public void setTanggal_time(String tanggal_time) {
        this.tanggal_time = tanggal_time;
    }
}
