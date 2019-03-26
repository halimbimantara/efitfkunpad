package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 21/12/2017.
 */

public class Bapetarum {

    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("status_bapertarum")
    @Expose
    private String statbaper;

    @SerializedName("user_edit")
    @Expose
    private String useredit;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getStatbaper() {
        return statbaper;
    }

    public void setStatbaper(String statbaper) {
        this.statbaper = statbaper;
    }

    public String getUseredit() {
        return useredit;
    }

    public void setUseredit(String useredit) {
        this.useredit = useredit;
    }
}
