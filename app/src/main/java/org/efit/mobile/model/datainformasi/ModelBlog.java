package org.efit.mobile.model.datainformasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBlog {
    @Expose
    @SerializedName("link")
    private String link;
    @Expose
    @SerializedName("tgl")
    private String tgl;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("judul")
    private String judul;
    @Expose
    @SerializedName("id")
    private String id;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
