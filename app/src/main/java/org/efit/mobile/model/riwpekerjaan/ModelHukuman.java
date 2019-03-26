package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelHukuman {

    @SerializedName("nip")
    public String nip;
    @SerializedName("jenis_hukuman")
    public String jenis_hukuman;
    @SerializedName("uraian_hukuman")
    public String uraian_hukuman;
    @SerializedName("tanggal_mulai")
    public String tanggal_mulai;
    @SerializedName("tmt_hukuman")
    public String tmt_hukuman;
    @SerializedName("user_edit")
    public String user_edit;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getJenis_hukuman() {
        return jenis_hukuman;
    }

    public void setJenis_hukuman(String jenis_hukuman) {
        this.jenis_hukuman = jenis_hukuman;
    }

    public String getUraian_hukuman() {
        return uraian_hukuman;
    }

    public void setUraian_hukuman(String uraian_hukuman) {
        this.uraian_hukuman = uraian_hukuman;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(String tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public String getTmt_hukuman() {
        return tmt_hukuman;
    }

    public void setTmt_hukuman(String tmt_hukuman) {
        this.tmt_hukuman = tmt_hukuman;
    }

    public String getUser_edit() {
        return user_edit;
    }

    public void setUser_edit(String user_edit) {
        this.user_edit = user_edit;
    }
}
