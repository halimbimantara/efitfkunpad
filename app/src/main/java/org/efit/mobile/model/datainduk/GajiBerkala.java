package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 21/12/2017.
 */

public class GajiBerkala {

    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("nomor_skp")
    @Expose
    private String nomorSkp;
    @SerializedName("tgl_skp")
    @Expose
    private String tglSkp;
    @SerializedName("masa_kerja_thn")
    @Expose
    private String masaKerjaThn;
    @SerializedName("masa_kerja_bln")
    @Expose
    private String masaKerjaBln;
    @SerializedName("tmt_gaji_berkala")
    @Expose
    private String tmtGajiBerkala;
    @SerializedName("tmt_gaji_berkala_lanjut")
    @Expose
    private String tmtGajiBerkalaLanjut;
    @SerializedName("gaji_pokok")
    @Expose
    private String gajiPokok;
    @SerializedName("kunkom")
    @Expose
    private String kunkom;
    @SerializedName("komponen_pembayaran_gaji")
    @Expose
    private String komponenPembayaranGaji;
    @SerializedName("pejabat_penetap")
    @Expose
    private String pejabatPenetap;
    @SerializedName("kkantor")
    @Expose
    private String kkantor;
    @SerializedName("kantor_bayar")
    @Expose
    private String kantorBayar;
    @SerializedName("copy_sk_kgb")
    @Expose
    private String copySkKgb;

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

    public String getNomorSkp() {
        return nomorSkp;
    }

    public void setNomorSkp(String nomorSkp) {
        this.nomorSkp = nomorSkp;
    }

    public String getTglSkp() {
        return tglSkp;
    }

    public void setTglSkp(String tglSkp) {
        this.tglSkp = tglSkp;
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

    public String getTmtGajiBerkala() {
        return tmtGajiBerkala;
    }

    public void setTmtGajiBerkala(String tmtGajiBerkala) {
        this.tmtGajiBerkala = tmtGajiBerkala;
    }

    public String getTmtGajiBerkalaLanjut() {
        return tmtGajiBerkalaLanjut;
    }

    public void setTmtGajiBerkalaLanjut(String tmtGajiBerkalaLanjut) {
        this.tmtGajiBerkalaLanjut = tmtGajiBerkalaLanjut;
    }

    public String getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(String gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public String getKunkom() {
        return kunkom;
    }

    public void setKunkom(String kunkom) {
        this.kunkom = kunkom;
    }

    public String getKomponenPembayaranGaji() {
        return komponenPembayaranGaji;
    }

    public void setKomponenPembayaranGaji(String komponenPembayaranGaji) {
        this.komponenPembayaranGaji = komponenPembayaranGaji;
    }

    public String getPejabatPenetap() {
        return pejabatPenetap;
    }

    public void setPejabatPenetap(String pejabatPenetap) {
        this.pejabatPenetap = pejabatPenetap;
    }

    public String getKkantor() {
        return kkantor;
    }

    public void setKkantor(String kkantor) {
        this.kkantor = kkantor;
    }

    public String getKantorBayar() {
        return kantorBayar;
    }

    public void setKantorBayar(String kantorBayar) {
        this.kantorBayar = kantorBayar;
    }

    public String getCopySkKgb() {
        return copySkKgb;
    }

    public void setCopySkKgb(String copySkKgb) {
        this.copySkKgb = copySkKgb;
    }
}
