package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 20/12/2017.
 */

public class PangkatAkhir {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("nama_stlud")
    @Expose
    private String namaStlud;
    @SerializedName("id_stlud")
    @Expose
    private String idStlud;
    @SerializedName("no_stlud")
    @Expose
    private String noStlud;
    @SerializedName("tgl_stlud")
    @Expose
    private String tglStlud;
    @SerializedName("nota_bakn")
    @Expose
    private String notaBakn;
    @SerializedName("tgl_nota_bakn")
    @Expose
    private String tglNotaBakn;
    @SerializedName("angka_kredit")
    @Expose
    private String angkaKredit;
    @SerializedName("id_pejabat_penetap")
    @Expose
    private String idPejabatPenetap;
    @SerializedName("pejabat_penetap")
    @Expose
    private String pejabatPenetap;
    @SerializedName("no_sk_pangkat")
    @Expose
    private String noSkPangkat;
    @SerializedName("tgl_sk_pangkat")
    @Expose
    private String tglSkPangkat;
    @SerializedName("tmt_pangkat")
    @Expose
    private String tmtPangkat;
    @SerializedName("tmt_pangkat_lanjut")
    @Expose
    private String tmtPangkatLanjut;
    @SerializedName("masa_kerja_tahun")
    @Expose
    private String masaKerjaTahun;
    @SerializedName("masa_kerja_bulan")
    @Expose
    private String masaKerjaBulan;
    @SerializedName("kd_gol_ruang")
    @Expose
    private String kdGolRuang;
    @SerializedName("pangkat_gol_ruang")
    @Expose
    private String pangkatGolRuang;
    @SerializedName("kd_gol_ruang_lanjut")
    @Expose
    private String kdGolRuangLanjut;
    @SerializedName("pangkat_gol_ruang_lanjut")
    @Expose
    private Object pangkatGolRuangLanjut;
    @SerializedName("id_jenis_naik_pangkat")
    @Expose
    private String idJenisNaikPangkat;
    @SerializedName("jenis_naik_pangkat")
    @Expose
    private String jenisNaikPangkat;
    @SerializedName("copy_sttb")
    @Expose
    private String copySttb;

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

    public String getNamaStlud() {
        return namaStlud;
    }

    public void setNamaStlud(String namaStlud) {
        this.namaStlud = namaStlud;
    }

    public String getIdStlud() {
        return idStlud;
    }

    public void setIdStlud(String idStlud) {
        this.idStlud = idStlud;
    }

    public String getNoStlud() {
        return noStlud;
    }

    public void setNoStlud(String noStlud) {
        this.noStlud = noStlud;
    }

    public String getTglStlud() {
        return tglStlud;
    }

    public void setTglStlud(String tglStlud) {
        this.tglStlud = tglStlud;
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

    public String getAngkaKredit() {
        return angkaKredit;
    }

    public void setAngkaKredit(String angkaKredit) {
        this.angkaKredit = angkaKredit;
    }

    public String getIdPejabatPenetap() {
        return idPejabatPenetap;
    }

    public void setIdPejabatPenetap(String idPejabatPenetap) {
        this.idPejabatPenetap = idPejabatPenetap;
    }

    public String getPejabatPenetap() {
        return pejabatPenetap;
    }

    public void setPejabatPenetap(String pejabatPenetap) {
        this.pejabatPenetap = pejabatPenetap;
    }

    public String getNoSkPangkat() {
        return noSkPangkat;
    }

    public void setNoSkPangkat(String noSkPangkat) {
        this.noSkPangkat = noSkPangkat;
    }

    public String getTglSkPangkat() {
        return tglSkPangkat;
    }

    public void setTglSkPangkat(String tglSkPangkat) {
        this.tglSkPangkat = tglSkPangkat;
    }

    public String getTmtPangkat() {
        return tmtPangkat;
    }

    public void setTmtPangkat(String tmtPangkat) {
        this.tmtPangkat = tmtPangkat;
    }

    public String getTmtPangkatLanjut() {
        return tmtPangkatLanjut;
    }

    public void setTmtPangkatLanjut(String tmtPangkatLanjut) {
        this.tmtPangkatLanjut = tmtPangkatLanjut;
    }

    public String getMasaKerjaTahun() {
        return masaKerjaTahun;
    }

    public void setMasaKerjaTahun(String masaKerjaTahun) {
        this.masaKerjaTahun = masaKerjaTahun;
    }

    public String getMasaKerjaBulan() {
        return masaKerjaBulan;
    }

    public void setMasaKerjaBulan(String masaKerjaBulan) {
        this.masaKerjaBulan = masaKerjaBulan;
    }

    public String getKdGolRuang() {
        return kdGolRuang;
    }

    public void setKdGolRuang(String kdGolRuang) {
        this.kdGolRuang = kdGolRuang;
    }

    public String getPangkatGolRuang() {
        return pangkatGolRuang;
    }

    public void setPangkatGolRuang(String pangkatGolRuang) {
        this.pangkatGolRuang = pangkatGolRuang;
    }

    public String getKdGolRuangLanjut() {
        return kdGolRuangLanjut;
    }

    public void setKdGolRuangLanjut(String kdGolRuangLanjut) {
        this.kdGolRuangLanjut = kdGolRuangLanjut;
    }

    public Object getPangkatGolRuangLanjut() {
        return pangkatGolRuangLanjut;
    }

    public void setPangkatGolRuangLanjut(Object pangkatGolRuangLanjut) {
        this.pangkatGolRuangLanjut = pangkatGolRuangLanjut;
    }

    public String getIdJenisNaikPangkat() {
        return idJenisNaikPangkat;
    }

    public void setIdJenisNaikPangkat(String idJenisNaikPangkat) {
        this.idJenisNaikPangkat = idJenisNaikPangkat;
    }

    public String getJenisNaikPangkat() {
        return jenisNaikPangkat;
    }

    public void setJenisNaikPangkat(String jenisNaikPangkat) {
        this.jenisNaikPangkat = jenisNaikPangkat;
    }

    public String getCopySttb() {
        return copySttb;
    }

    public void setCopySttb(String copySttb) {
        this.copySttb = copySttb;
    }
}
