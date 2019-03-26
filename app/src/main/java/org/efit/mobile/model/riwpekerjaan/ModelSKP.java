package org.efit.mobile.model.riwpekerjaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 26/12/2017.
 */

public class ModelSKP {
    @SerializedName("tahun_penilaian")
    @Expose
    private String tahun_penilaian;

    @SerializedName("nilai_skp")
    @Expose
    private String nilaiskp;

    @SerializedName("orientasi")
    @Expose
    private String orientasi;

    @SerializedName("integritas")
    @Expose
    private String integritas;

    @SerializedName("komitmen")
    @Expose
    private String komitmen;

    @SerializedName("disiplin")
    @Expose
    private String disiplin;

    @SerializedName("kerjasama")
    @Expose
    private String kerjasama;

    @SerializedName("kepemimpinan")
    @Expose
    private String kepemimpinan;

    @SerializedName("prestasi_kerja")
    @Expose
    private String prestasi_kerja;

    public String getTahun_penilaian() {
        return tahun_penilaian;
    }

    public void setTahun_penilaian(String tahun_penilaian) {
        this.tahun_penilaian = tahun_penilaian;
    }

    public String getNilaiskp() {
        return nilaiskp;
    }

    public void setNilaiskp(String nilaiskp) {
        this.nilaiskp = nilaiskp;
    }

    public String getOrientasi() {
        return orientasi;
    }

    public void setOrientasi(String orientasi) {
        this.orientasi = orientasi;
    }

    public String getIntegritas() {
        return integritas;
    }

    public void setIntegritas(String integritas) {
        this.integritas = integritas;
    }

    public String getKomitmen() {
        return komitmen;
    }

    public void setKomitmen(String komitmen) {
        this.komitmen = komitmen;
    }

    public String getDisiplin() {
        return disiplin;
    }

    public void setDisiplin(String disiplin) {
        this.disiplin = disiplin;
    }

    public String getKerjasama() {
        return kerjasama;
    }

    public void setKerjasama(String kerjasama) {
        this.kerjasama = kerjasama;
    }

    public String getKepemimpinan() {
        return kepemimpinan;
    }

    public void setKepemimpinan(String kepemimpinan) {
        this.kepemimpinan = kepemimpinan;
    }

    public String getPrestasi_kerja() {
        return prestasi_kerja;
    }

    public void setPrestasi_kerja(String prestasi_kerja) {
        this.prestasi_kerja = prestasi_kerja;
    }
}
