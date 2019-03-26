package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 21/12/2017.
 */

public class Lkhsn {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("tahun")
    @Expose
    private String tahun;
    @SerializedName("kekayaan")
    @Expose
    private String kekayaan;
    @SerializedName("penghasilan")
    @Expose
    private String penghasilan;
    @SerializedName("pengeluaran")
    @Expose
    private String pengeluaran;
    @SerializedName("penghasilan_bersih")
    @Expose
    private String penghasilanBersih;

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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKekayaan() {
        return kekayaan;
    }

    public void setKekayaan(String kekayaan) {
        this.kekayaan = kekayaan;
    }

    public String getPenghasilan() {
        return penghasilan;
    }

    public void setPenghasilan(String penghasilan) {
        this.penghasilan = penghasilan;
    }

    public String getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(String pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public String getPenghasilanBersih() {
        return penghasilanBersih;
    }

    public void setPenghasilanBersih(String penghasilanBersih) {
        this.penghasilanBersih = penghasilanBersih;
    }
}
