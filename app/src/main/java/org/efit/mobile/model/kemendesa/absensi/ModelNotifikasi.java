package org.efit.mobile.model.kemendesa.absensi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ModelNotifikasi {

    @SerializedName("data")
    private List<DataEntity> data;
    @SerializedName("RC")
    private String RC;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private boolean status;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getRC() {
        return RC;
    }

    public void setRC(String RC) {
        this.RC = RC;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class DataEntity {
        @SerializedName("link")
        private String link;
        @SerializedName("tanggal")
        private String tanggal;
        @SerializedName("aplikasi")
        private String aplikasi;
        @SerializedName("tujuan")
        private String tujuan;
        @SerializedName("dari")
        private String dari;
        @SerializedName("info")
        private String info;
        @SerializedName("id_notif")
        private String id_notif;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getAplikasi() {
            return aplikasi;
        }

        public void setAplikasi(String aplikasi) {
            this.aplikasi = aplikasi;
        }

        public String getTujuan() {
            return tujuan;
        }

        public void setTujuan(String tujuan) {
            this.tujuan = tujuan;
        }

        public String getDari() {
            return dari;
        }

        public void setDari(String dari) {
            this.dari = dari;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getId_notif() {
            return id_notif;
        }

        public void setId_notif(String id_notif) {
            this.id_notif = id_notif;
        }
    }
}
