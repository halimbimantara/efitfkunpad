package org.efit.mobile.model.datainduk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */

public class Biodata {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("tempat_tanggal_lahir")
    @Expose
    private String tempatTanggalLahir;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("agama")
    @Expose
    private String agama;
    @SerializedName("status_perkawinan")
    @Expose
    private String statusPerkawinan;
    @SerializedName("pangkat")
    @Expose
    private String pangkat;
    @SerializedName("tmt_pangkat")
    @Expose
    private String tmtPangkat;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;
    @SerializedName("tmt_jabatan")
    @Expose
    private String tmtJabatan;
    @SerializedName("pendidikan")
    @Expose
    private String pendidikan;
    @SerializedName("alamat")
    @Expose
    private String alamat;

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

    public String getTempatTanggalLahir() {
        return tempatTanggalLahir;
    }

    public void setTempatTanggalLahir(String tempatTanggalLahir) {
        this.tempatTanggalLahir = tempatTanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getTmtPangkat() {
        return tmtPangkat;
    }

    public void setTmtPangkat(String tmtPangkat) {
        this.tmtPangkat = tmtPangkat;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTmtJabatan() {
        return tmtJabatan;
    }

    public void setTmtJabatan(String tmtJabatan) {
        this.tmtJabatan = tmtJabatan;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
