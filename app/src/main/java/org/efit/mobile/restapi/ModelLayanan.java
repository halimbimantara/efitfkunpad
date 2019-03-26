package org.efit.mobile.restapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelLayanan {
    @Expose
    @SerializedName("harga")
    private String harga;
    @Expose
    @SerializedName("nama_layanan")
    private String nama_layanan;
    @Expose
    @SerializedName("id_layanan")
    private String id_layanan;

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }

    public void setNama_layanan(String nama_layanan) {
        this.nama_layanan = nama_layanan;
    }

    public String getId_layanan() {
        return id_layanan;
    }

    public void setId_layanan(String id_layanan) {
        this.id_layanan = id_layanan;
    }
}
