package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelKunkerLu {

    @SerializedName("nip")
    public String nip;
    @SerializedName("negara_tujuan")
    public String negara_tujuan;
    @SerializedName("tujuan_penugasan")
    public String tujuan_penugasan;
    @SerializedName("masa_penugasan")
    public String masa_penugasan;
    @SerializedName("user_edit")
    public String user_edit;
    @SerializedName("tgl_mulai")
    public String tgl_mulai;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNegara_tujuan() {
        return negara_tujuan;
    }

    public void setNegara_tujuan(String negara_tujuan) {
        this.negara_tujuan = negara_tujuan;
    }

    public String getTujuan_penugasan() {
        return tujuan_penugasan;
    }

    public void setTujuan_penugasan(String tujuan_penugasan) {
        this.tujuan_penugasan = tujuan_penugasan;
    }

    public String getMasa_penugasan() {
        return masa_penugasan;
    }

    public void setMasa_penugasan(String masa_penugasan) {
        this.masa_penugasan = masa_penugasan;
    }

    public String getUser_edit() {
        return user_edit;
    }

    public void setUser_edit(String user_edit) {
        this.user_edit = user_edit;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }
}
