package org.efit.mobile.model.dataharian;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelMasterOlahraga {

    @SerializedName("id_user")
    private String id_user;
    @SerializedName("sumber")
    private String sumber;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("kode_transaksi")
    private String kode_transaksi;
    @SerializedName("detail")
    private List<ModelDetailOlahraga> detail;

    public List<ModelDetailOlahraga> getDetail() {
        return detail;
    }

    public void setDetail(List<ModelDetailOlahraga> detail) {
        this.detail = detail;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }
}
