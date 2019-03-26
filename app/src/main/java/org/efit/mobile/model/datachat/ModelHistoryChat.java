package org.efit.mobile.model.datachat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 05/01/2018.
 */

public class ModelHistoryChat {
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("judul")
    private String judul;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("tgl")
    private String tanggal;
    @Expose
    @SerializedName("link")
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }


    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
