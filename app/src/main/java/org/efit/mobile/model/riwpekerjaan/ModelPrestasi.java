package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelPrestasi {

    @SerializedName("nip")
    public String nip;
    @SerializedName("tahun")
    public String tahun;
    @SerializedName("nama_prestasi")
    public String nama_prestasi;
    @SerializedName("keterangan")
    public String keterangan;
    @SerializedName("user_edit")
    public String user_edit;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNama_prestasi() {
        return nama_prestasi;
    }

    public void setNama_prestasi(String nama_prestasi) {
        this.nama_prestasi = nama_prestasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getUser_edit() {
        return user_edit;
    }

    public void setUser_edit(String user_edit) {
        this.user_edit = user_edit;
    }
}
