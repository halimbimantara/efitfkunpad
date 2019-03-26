package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelPangkat {
    @SerializedName("nip")
    public String nip;
    @SerializedName("pangkat_gol_ruang")
    public String pangkat_gol_ruang;
    @SerializedName("tmt_pangkat")
    public String tmt_pangkat;
    @SerializedName("no_sk")
    public String no_sk;
    @SerializedName("tanggal_sk")
    public String tanggal_sk;
    @SerializedName("jenis_naik_pangkat")
    public String jenis_naik_pangkat;
    @SerializedName("copy_sk")
    public String copy_sk;
    @SerializedName("user_edit")
    public String user_edit;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPangkat_gol_ruang() {
        return pangkat_gol_ruang;
    }

    public void setPangkat_gol_ruang(String pangkat_gol_ruang) {
        this.pangkat_gol_ruang = pangkat_gol_ruang;
    }

    public String getTmt_pangkat() {
        return tmt_pangkat;
    }

    public void setTmt_pangkat(String tmt_pangkat) {
        this.tmt_pangkat = tmt_pangkat;
    }

    public String getNo_sk() {
        return no_sk;
    }

    public void setNo_sk(String no_sk) {
        this.no_sk = no_sk;
    }

    public String getTanggal_sk() {
        return tanggal_sk;
    }

    public void setTanggal_sk(String tanggal_sk) {
        this.tanggal_sk = tanggal_sk;
    }

    public String getJenis_naik_pangkat() {
        return jenis_naik_pangkat;
    }

    public void setJenis_naik_pangkat(String jenis_naik_pangkat) {
        this.jenis_naik_pangkat = jenis_naik_pangkat;
    }

    public String getCopy_sk() {
        return copy_sk;
    }

    public void setCopy_sk(String copy_sk) {
        this.copy_sk = copy_sk;
    }

    public String getUser_edit() {
        return user_edit;
    }

    public void setUser_edit(String user_edit) {
        this.user_edit = user_edit;
    }
}
