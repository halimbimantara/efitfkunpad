package org.efit.mobile.model.dataartikel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelArtikel {

    @Expose
    @SerializedName("tgl")
    private String tgl;
    @Expose
    @SerializedName("judul")
    private String judul;
    @Expose
    @SerializedName("id")
    private String id;

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
