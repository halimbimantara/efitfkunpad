package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 19/12/2017.
 */

public class Cpns {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("nota_bakn")
    @Expose
    private String notaBakn;
    @SerializedName("tgl_nota_bakn")
    @Expose
    private String tglNotaBakn;
    @SerializedName("pejabat_penetap")
    @Expose
    private String pejabatPenetap;
    @SerializedName("nomor_sk_cpns")
    @Expose
    private String nomorSkCpns;
    @SerializedName("tgl_sk_cpns")
    @Expose
    private String tglSkCpns;
    @SerializedName("tmt_cpns")
    @Expose
    private String tmtCpns;
    @SerializedName("pangkat_gol_ruang")
    @Expose
    private String pangkatGolRuang;
    @SerializedName("no_nsttpp")
    @Expose
    private String noNsttpp;
    @SerializedName("tgl_sttpp")
    @Expose
    private String tglSttpp;
    @SerializedName("tgl_tugas")
    @Expose
    private String tglTugas;
    @SerializedName("masa_kerja_thn")
    @Expose
    private String masaKerjaThn;
    @SerializedName("masa_kerja_bulan")
    @Expose
    private String masaKerjaBulan;
    @SerializedName("copy_sk_pns")
    @Expose
    private String copySkPns;

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

    public String getNotaBakn() {
        return notaBakn;
    }

    public void setNotaBakn(String notaBakn) {
        this.notaBakn = notaBakn;
    }

    public String getTglNotaBakn() {
        return tglNotaBakn;
    }

    public void setTglNotaBakn(String tglNotaBakn) {
        this.tglNotaBakn = tglNotaBakn;
    }

    public String getPejabatPenetap() {
        return pejabatPenetap;
    }

    public void setPejabatPenetap(String pejabatPenetap) {
        this.pejabatPenetap = pejabatPenetap;
    }

    public String getNomorSkCpns() {
        return nomorSkCpns;
    }

    public void setNomorSkCpns(String nomorSkCpns) {
        this.nomorSkCpns = nomorSkCpns;
    }

    public String getTglSkCpns() {
        return tglSkCpns;
    }

    public void setTglSkCpns(String tglSkCpns) {
        this.tglSkCpns = tglSkCpns;
    }

    public String getTmtCpns() {
        return tmtCpns;
    }

    public void setTmtCpns(String tmtCpns) {
        this.tmtCpns = tmtCpns;
    }

    public String getPangkatGolRuang() {
        return pangkatGolRuang;
    }

    public void setPangkatGolRuang(String pangkatGolRuang) {
        this.pangkatGolRuang = pangkatGolRuang;
    }

    public String getNoNsttpp() {
        return noNsttpp;
    }

    public void setNoNsttpp(String noNsttpp) {
        this.noNsttpp = noNsttpp;
    }

    public String getTglSttpp() {
        return tglSttpp;
    }

    public void setTglSttpp(String tglSttpp) {
        this.tglSttpp = tglSttpp;
    }

    public String getTglTugas() {
        return tglTugas;
    }

    public void setTglTugas(String tglTugas) {
        this.tglTugas = tglTugas;
    }

    public String getMasaKerjaThn() {
        return masaKerjaThn;
    }

    public void setMasaKerjaThn(String masaKerjaThn) {
        this.masaKerjaThn = masaKerjaThn;
    }

    public String getMasaKerjaBulan() {
        return masaKerjaBulan;
    }

    public void setMasaKerjaBulan(String masaKerjaBulan) {
        this.masaKerjaBulan = masaKerjaBulan;
    }

    public String getCopySkPns() {
        return copySkPns;
    }

    public void setCopySkPns(String copySkPns) {
        this.copySkPns = copySkPns;
    }
}
