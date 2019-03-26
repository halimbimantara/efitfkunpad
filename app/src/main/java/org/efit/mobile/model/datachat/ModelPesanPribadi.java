package org.efit.mobile.model.datachat;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 06/01/2018.
 */

public class ModelPesanPribadi {
    @SerializedName("id_chat")
    public String id_chat;
    @SerializedName("nip_sender")
    public String nip_sender;
    @SerializedName("pesan")
    public String pesan;
    @SerializedName("nip_penerima")
    public String nip_penerima;
    @SerializedName("tanggal")
    public String tanggal;
    @SerializedName("status")
    public String status;

    public String getId_chat() {
        return id_chat;
    }

    public void setId_chat(String id_chat) {
        this.id_chat = id_chat;
    }


    public String getNip_sender() {
        return nip_sender;
    }

    public void setNip_sender(String nip_sender) {
        this.nip_sender = nip_sender;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getNip_penerima() {
        return nip_penerima;
    }

    public void setNip_penerima(String nip_penerima) {
        this.nip_penerima = nip_penerima;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
