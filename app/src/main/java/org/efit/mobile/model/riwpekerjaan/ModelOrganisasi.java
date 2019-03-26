package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelOrganisasi {

    @SerializedName("nip")
    public String nip;
    @SerializedName("jenis_organisasi")
    public String jenis_organisasi;
    @SerializedName("nama_organisasi")
    public String nama_organisasi;
    @SerializedName("jabatan")
    public String jabatan;
    @SerializedName("tempat")
    public String tempat;
    @SerializedName("tgl_mulai")
    public String tgl_mulai;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getJenis_organisasi() {
        return jenis_organisasi;
    }

    public void setJenis_organisasi(String jenis_organisasi) {
        this.jenis_organisasi = jenis_organisasi;
    }

    public String getNama_organisasi() {
        return nama_organisasi;
    }

    public void setNama_organisasi(String nama_organisasi) {
        this.nama_organisasi = nama_organisasi;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }
}
