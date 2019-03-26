package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 21/12/2017.
 */

public class RekamMedis {

    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("surat_dokter")
    @Expose
    private String suratdok;

    @SerializedName("surat_kesehatan")
    @Expose
    private String suratkes;
    @SerializedName("keterangan")
    @Expose
    private String ket;

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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSuratdok() {
        return suratdok;
    }

    public void setSuratdok(String suratdok) {
        this.suratdok = suratdok;
    }

    public String getSuratkes() {
        return suratkes;
    }

    public void setSuratkes(String suratkes) {
        this.suratkes = suratkes;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
}
