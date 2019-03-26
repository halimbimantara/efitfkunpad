package org.efit.mobile.model.loker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lowongan {

    @Expose
    @SerializedName("created")
    private String created;
    @Expose
    @SerializedName("alamat_kantor")
    private String alamat_kantor;
    @Expose
    @SerializedName("tgl_berakhir")
    private String tgl_berakhir;
    @Expose
    @SerializedName("syarat")
    private String syarat;
    @Expose
    @SerializedName("posisi")
    private String posisi;
    @Expose
    @SerializedName("judul")
    private String judul;
    @Expose
    @SerializedName("id_loker")
    private String id_loker;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getAlamat_kantor() {
        return alamat_kantor;
    }

    public void setAlamat_kantor(String alamat_kantor) {
        this.alamat_kantor = alamat_kantor;
    }

    public String getTgl_berakhir() {
        return tgl_berakhir;
    }

    public void setTgl_berakhir(String tgl_berakhir) {
        this.tgl_berakhir = tgl_berakhir;
    }

    public String getSyarat() {
        return syarat;
    }

    public void setSyarat(String syarat) {
        this.syarat = syarat;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getId_loker() {
        return id_loker;
    }

    public void setId_loker(String id_loker) {
        this.id_loker = id_loker;
    }
}
