package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelJabatan {

    @SerializedName("nip")
    public String nip;
    @SerializedName("nama_jabatan")
    public String nama_jabatan;
    @SerializedName("eselon")
    public String eselon;
    @SerializedName("jenis")
    public String jenis;
    @SerializedName("tmt")
    public String tmt;
    @SerializedName("tmt_jabatan")
    public String tmt_jabatan;
    @SerializedName("user_edit")
    public String user_edit;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama_jabatan() {
        return nama_jabatan;
    }

    public void setNama_jabatan(String nama_jabatan) {
        this.nama_jabatan = nama_jabatan;
    }

    public String getEselon() {
        return eselon;
    }

    public void setEselon(String eselon) {
        this.eselon = eselon;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTmt() {
        return tmt;
    }

    public void setTmt(String tmt) {
        this.tmt = tmt;
    }

    public String getTmt_jabatan() {
        return tmt_jabatan;
    }

    public void setTmt_jabatan(String tmt_jabatan) {
        this.tmt_jabatan = tmt_jabatan;
    }

    public String getUser_edit() {
        return user_edit;
    }

    public void setUser_edit(String user_edit) {
        this.user_edit = user_edit;
    }
}
