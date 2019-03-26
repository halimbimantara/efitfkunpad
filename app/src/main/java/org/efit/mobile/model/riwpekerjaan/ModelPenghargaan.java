package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelPenghargaan {

    @SerializedName("nip")
    public String nip;
    @SerializedName("nama_penghargaan")
    public String nama_penghargaan;
    @SerializedName("asal_perolehan")
    public String asal_perolehan;
    @SerializedName("no_sk")
    public String no_sk;
    @SerializedName("tahun")
    public String tahun;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama_penghargaan() {
        return nama_penghargaan;
    }

    public void setNama_penghargaan(String nama_penghargaan) {
        this.nama_penghargaan = nama_penghargaan;
    }

    public String getAsal_perolehan() {
        return asal_perolehan;
    }

    public void setAsal_perolehan(String asal_perolehan) {
        this.asal_perolehan = asal_perolehan;
    }

    public String getNo_sk() {
        return no_sk;
    }

    public void setNo_sk(String no_sk) {
        this.no_sk = no_sk;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}
