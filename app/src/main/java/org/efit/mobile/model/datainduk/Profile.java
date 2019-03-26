package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */

public class Profile {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nip_lama")
    @Expose
    private String nipLama;
    @SerializedName("status_kepegawaian")
    @Expose
    private String statusKepegawaian;
    @SerializedName("jenis_kepegawaian")
    @Expose
    private String jenisKepegawaian;
    @SerializedName("tanggal_reg_data")
    @Expose
    private String tanggalRegData;
    @SerializedName("kedudukan_pegawai")
    @Expose
    private String kedudukanPegawai;
    @SerializedName("status_perkawinan")
    @Expose
    private String statusPerkawinan;
    @SerializedName("golongan_darah")
    @Expose
    private String golonganDarah;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("rt")
    @Expose
    private String rt;
    @SerializedName("rw")
    @Expose
    private String rw;
    @SerializedName("no_telepon")
    @Expose
    private String noTelepon;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("kabupaten_kota")
    @Expose
    private String kabupatenKota;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("kelurahan_desa")
    @Expose
    private String kelurahanDesa;
    @SerializedName("nomor_penduduk")
    @Expose
    private String nomorPenduduk;
    @SerializedName("nomor_karpeg")
    @Expose
    private String nomorKarpeg;
    @SerializedName("nomor_karis_su")
    @Expose
    private String nomorKarisSu;
    @SerializedName("nomor_askes")
    @Expose
    private String nomorAskes;
    @SerializedName("npwp")
    @Expose
    private String npwp;
    @SerializedName("nomor_taspen")
    @Expose
    private String nomorTaspen;
    @SerializedName("status_kepemilikan_kpe")
    @Expose
    private String statusKepemilikanKpe;
    @SerializedName("tanggal_kpe")
    @Expose
    private String tanggalKpe;
    @SerializedName("thn_pendataan")
    @Expose
    private String thnPendataan;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNipLama() {
        return nipLama;
    }

    public void setNipLama(String nipLama) {
        this.nipLama = nipLama;
    }

    public String getStatusKepegawaian() {
        return statusKepegawaian;
    }

    public void setStatusKepegawaian(String statusKepegawaian) {
        this.statusKepegawaian = statusKepegawaian;
    }

    public String getJenisKepegawaian() {
        return jenisKepegawaian;
    }

    public void setJenisKepegawaian(String jenisKepegawaian) {
        this.jenisKepegawaian = jenisKepegawaian;
    }

    public String getTanggalRegData() {
        return tanggalRegData;
    }

    public void setTanggalRegData(String tanggalRegData) {
        this.tanggalRegData = tanggalRegData;
    }

    public String getKedudukanPegawai() {
        return kedudukanPegawai;
    }

    public void setKedudukanPegawai(String kedudukanPegawai) {
        this.kedudukanPegawai = kedudukanPegawai;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKabupatenKota() {
        return kabupatenKota;
    }

    public void setKabupatenKota(String kabupatenKota) {
        this.kabupatenKota = kabupatenKota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahanDesa() {
        return kelurahanDesa;
    }

    public void setKelurahanDesa(String kelurahanDesa) {
        this.kelurahanDesa = kelurahanDesa;
    }

    public String getNomorPenduduk() {
        return nomorPenduduk;
    }

    public void setNomorPenduduk(String nomorPenduduk) {
        this.nomorPenduduk = nomorPenduduk;
    }

    public String getNomorKarpeg() {
        return nomorKarpeg;
    }

    public void setNomorKarpeg(String nomorKarpeg) {
        this.nomorKarpeg = nomorKarpeg;
    }

    public String getNomorKarisSu() {
        return nomorKarisSu;
    }

    public void setNomorKarisSu(String nomorKarisSu) {
        this.nomorKarisSu = nomorKarisSu;
    }

    public String getNomorAskes() {
        return nomorAskes;
    }

    public void setNomorAskes(String nomorAskes) {
        this.nomorAskes = nomorAskes;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getNomorTaspen() {
        return nomorTaspen;
    }

    public void setNomorTaspen(String nomorTaspen) {
        this.nomorTaspen = nomorTaspen;
    }

    public String getStatusKepemilikanKpe() {
        return statusKepemilikanKpe;
    }

    public void setStatusKepemilikanKpe(String statusKepemilikanKpe) {
        this.statusKepemilikanKpe = statusKepemilikanKpe;
    }

    public String getTanggalKpe() {
        return tanggalKpe;
    }

    public void setTanggalKpe(String tanggalKpe) {
        this.tanggalKpe = tanggalKpe;
    }

    public String getThnPendataan() {
        return thnPendataan;
    }

    public void setThnPendataan(String thnPendataan) {
        this.thnPendataan = thnPendataan;
    }
}
