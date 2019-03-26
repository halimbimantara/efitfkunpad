package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 21/12/2017.
 */

public class PengalamanKerja {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("instansi_perusahaan")
    @Expose
    private String instansiPerusahaan;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;
    @SerializedName("masa_kerja_thn")
    @Expose
    private String masaKerjaThn;
    @SerializedName("masa_kerja_bln")
    @Expose
    private String masaKerjaBln;

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

    public String getInstansiPerusahaan() {
        return instansiPerusahaan;
    }

    public void setInstansiPerusahaan(String instansiPerusahaan) {
        this.instansiPerusahaan = instansiPerusahaan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getMasaKerjaThn() {
        return masaKerjaThn;
    }

    public void setMasaKerjaThn(String masaKerjaThn) {
        this.masaKerjaThn = masaKerjaThn;
    }

    public String getMasaKerjaBln() {
        return masaKerjaBln;
    }

    public void setMasaKerjaBln(String masaKerjaBln) {
        this.masaKerjaBln = masaKerjaBln;
    }
}
