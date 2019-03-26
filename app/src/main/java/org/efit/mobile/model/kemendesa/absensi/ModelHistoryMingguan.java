package org.efit.mobile.model.kemendesa.absensi;

import com.google.gson.annotations.SerializedName;

public class ModelHistoryMingguan {

    @SerializedName("pulang")
    private String pulang;
    @SerializedName("datang")
    private String datang;
    @SerializedName("tanggal")
    private String tanggal;

    public String getPulang() {
        return pulang;
    }

    public void setPulang(String pulang) {
        this.pulang = pulang;
    }

    public String getDatang() {
        return datang;
    }

    public void setDatang(String datang) {
        this.datang = datang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
