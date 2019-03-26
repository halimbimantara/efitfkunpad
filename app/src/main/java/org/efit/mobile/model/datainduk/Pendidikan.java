package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */

public class Pendidikan {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("tingkat_pendidikan")
    @Expose
    private String tingkatPendidikan;
    @SerializedName("fakultas")
    @Expose
    private String fakultas;
    @SerializedName("nama_sekolah")
    @Expose
    private String namaSekolah;
    @SerializedName("tempat")
    @Expose
    private String tempat;
    @SerializedName("tahun_akademik")
    @Expose
    private String tahunAkademik;
    @SerializedName("no_sttb")
    @Expose
    private String noSttb;
    @SerializedName("tgl_sttb")
    @Expose
    private String tglSttb;
    @SerializedName("dalam_luar_negeri")
    @Expose
    private String dalamLuarNegeri;
    @SerializedName("negara")
    @Expose
    private String negara;
    @SerializedName("nama_kepsek")
    @Expose
    private String namaKepsek;
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

    public String getTingkatPendidikan() {
        return tingkatPendidikan;
    }

    public void setTingkatPendidikan(String tingkatPendidikan) {
        this.tingkatPendidikan = tingkatPendidikan;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTahunAkademik() {
        return tahunAkademik;
    }

    public void setTahunAkademik(String tahunAkademik) {
        this.tahunAkademik = tahunAkademik;
    }

    public String getNoSttb() {
        return noSttb;
    }

    public void setNoSttb(String noSttb) {
        this.noSttb = noSttb;
    }

    public String getTglSttb() {
        return tglSttb;
    }

    public void setTglSttb(String tglSttb) {
        this.tglSttb = tglSttb;
    }

    public String getDalamLuarNegeri() {
        return dalamLuarNegeri;
    }

    public void setDalamLuarNegeri(String dalamLuarNegeri) {
        this.dalamLuarNegeri = dalamLuarNegeri;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }

    public String getNamaKepsek() {
        return namaKepsek;
    }

    public void setNamaKepsek(String namaKepsek) {
        this.namaKepsek = namaKepsek;
    }

    public String getCopySttb() {
        return copySttb;
    }

    public void setCopySttb(String copySttb) {
        this.copySttb = copySttb;
    }
}
