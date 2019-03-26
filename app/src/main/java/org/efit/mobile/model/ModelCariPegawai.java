package org.efit.mobile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 03/01/2018.
 */

public class ModelCariPegawai {

    @SerializedName("nip")
    public String nip;
    @SerializedName("nama_pegawai")
    public String nama_pegawai;
    @SerializedName("nama_jabatan")
    public String nama_jabatan;
    @SerializedName("file_bmp")
    public String file_bmp;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getNama_jabatan() {
        return nama_jabatan;
    }

    public void setNama_jabatan(String nama_jabatan) {
        this.nama_jabatan = nama_jabatan;
    }

    public String getFile_bmp() {
        return file_bmp;
    }

    public void setFile_bmp(String file_bmp) {
        this.file_bmp = file_bmp;
    }
}
