package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelGajiBerkala {

    @SerializedName("nip")
    public String nip;
    @SerializedName("no_pemberitahuan")
    public String no_pemberitahuan;
    @SerializedName("tgl_pemberitahuan")
    public String tgl_pemberitahuan;
    @SerializedName("tmt")
    public String tmt;
    @SerializedName("gaji_pokok")
    public String gaji_pokok;
    @SerializedName("masa_kerja")
    public String masa_kerja;
    @SerializedName("user_edit")
    public String user_edit;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNo_pemberitahuan() {
        return no_pemberitahuan;
    }

    public void setNo_pemberitahuan(String no_pemberitahuan) {
        this.no_pemberitahuan = no_pemberitahuan;
    }

    public String getTgl_pemberitahuan() {
        return tgl_pemberitahuan;
    }

    public void setTgl_pemberitahuan(String tgl_pemberitahuan) {
        this.tgl_pemberitahuan = tgl_pemberitahuan;
    }

    public String getTmt() {
        return tmt;
    }

    public void setTmt(String tmt) {
        this.tmt = tmt;
    }

    public String getGaji_pokok() {
        return gaji_pokok;
    }

    public void setGaji_pokok(String gaji_pokok) {
        this.gaji_pokok = gaji_pokok;
    }

    public String getMasa_kerja() {
        return masa_kerja;
    }

    public void setMasa_kerja(String masa_kerja) {
        this.masa_kerja = masa_kerja;
    }

    public String getUser_edit() {
        return user_edit;
    }

    public void setUser_edit(String user_edit) {
        this.user_edit = user_edit;
    }
}
