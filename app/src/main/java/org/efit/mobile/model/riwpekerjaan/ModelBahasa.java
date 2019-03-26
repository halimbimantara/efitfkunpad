package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelBahasa {

    @SerializedName("nip")
    public String nip;
    @SerializedName("jenis_bahasa")
    public String jenis_bahasa;
    @SerializedName("bahasa")
    public String bahasa;
    @SerializedName("kemampuan")
    public String kemampuan;
    @SerializedName("nilai")
    public String nilai;
    @SerializedName("user_edit")
    public String user_edit;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getJenis_bahasa() {
        return jenis_bahasa;
    }

    public void setJenis_bahasa(String jenis_bahasa) {
        this.jenis_bahasa = jenis_bahasa;
    }

    public String getBahasa() {
        return bahasa;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public String getKemampuan() {
        return kemampuan;
    }

    public void setKemampuan(String kemampuan) {
        this.kemampuan = kemampuan;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getUser_edit() {
        return user_edit;
    }

    public void setUser_edit(String user_edit) {
        this.user_edit = user_edit;
    }
}
