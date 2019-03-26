package org.efit.mobile.model.datainduk;

/**
 * Created by ThinkPad T430 on 19/12/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pns {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("pejabat_penetap")
    @Expose
    private String pejabatPenetap;
    @SerializedName("no_sk_pengangkatan")
    @Expose
    private String nomorSkPngangkatan;

    @SerializedName("tgl_sk_pengangkatan")
    @Expose
    private String tglSkPngangkatan;

    @SerializedName("tmt_pns")
    @Expose
    private String tmtPns;
    @SerializedName("pangkat_gol_ruang")
    @Expose
    private String pangkatGolRuang;

    @SerializedName("sumpah_pns")
    @Expose
    private String sumpahPns;

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

    public String getPejabatPenetap() {
        return pejabatPenetap;
    }

    public void setPejabatPenetap(String pejabatPenetap) {
        this.pejabatPenetap = pejabatPenetap;
    }

    public String getNomorSkPngangkatan() {
        return nomorSkPngangkatan;
    }

    public void setNomorSkPngangkatan(String nomorSkPngangkatan) {
        this.nomorSkPngangkatan = nomorSkPngangkatan;
    }

    public String getTglSkPngangkatan() {
        return tglSkPngangkatan;
    }

    public void setTglSkPngangkatan(String tglSkPngangkatan) {
        this.tglSkPngangkatan = tglSkPngangkatan;
    }

    public String getTmtPns() {
        return tmtPns;
    }

    public void setTmtPns(String tmtPns) {
        this.tmtPns = tmtPns;
    }

    public String getPangkatGolRuang() {
        return pangkatGolRuang;
    }

    public void setPangkatGolRuang(String pangkatGolRuang) {
        this.pangkatGolRuang = pangkatGolRuang;
    }

    public String getSumpahPns() {
        return sumpahPns;
    }

    public void setSumpahPns(String sumpahPns) {
        this.sumpahPns = sumpahPns;
    }

    public String getCopySkPns() {
        return copySkPns;
    }

    public void setCopySkPns(String copySkPns) {
        this.copySkPns = copySkPns;
    }
}
