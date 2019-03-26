package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelCuti {

    @SerializedName("nip")
    public String nip;
    @SerializedName("jenis_cuti")
    public String jenis_cuti;
    @SerializedName("no_sk")
    public String no_sk;
    @SerializedName("tgl_sk")
    public String tgl_sk;
    @SerializedName("tgl_mulai")
    public String tgl_mulai;
    @SerializedName("tgl_akhir")
    public String tgl_akhir;
    @SerializedName("user_edit")
    public String user_edit;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getJenis_cuti() {
        return jenis_cuti;
    }

    public void setJenis_cuti(String jenis_cuti) {
        this.jenis_cuti = jenis_cuti;
    }

    public String getNo_sk() {
        return no_sk;
    }

    public void setNo_sk(String no_sk) {
        this.no_sk = no_sk;
    }

    public String getTgl_sk() {
        return tgl_sk;
    }

    public void setTgl_sk(String tgl_sk) {
        this.tgl_sk = tgl_sk;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public String getTgl_akhir() {
        return tgl_akhir;
    }

    public void setTgl_akhir(String tgl_akhir) {
        this.tgl_akhir = tgl_akhir;
    }

    public String getUser_edit() {
        return user_edit;
    }

    public void setUser_edit(String user_edit) {
        this.user_edit = user_edit;
    }
}
