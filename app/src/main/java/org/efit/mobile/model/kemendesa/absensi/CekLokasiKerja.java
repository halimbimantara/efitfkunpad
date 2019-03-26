package org.efit.mobile.model.kemendesa.absensi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class CekLokasiKerja {

    @SerializedName("result")
    private List<ResultEntity> result;
    @SerializedName("RC")
    private String RC;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private boolean status;

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
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

    public static class ResultEntity {
        @SerializedName("radius")
        private String radius;
        @SerializedName("long")
        private String longi;
        @SerializedName("lat")
        private String lat;
        @SerializedName("nama_lokasi")
        private String nama_lokasi;
        @SerializedName("id_lokasi_kerja")
        private String id_lokasi_kerja;

        public String getRadius() {
            return radius;
        }

        public void setRadius(String radius) {
            this.radius = radius;
        }

        public String getLongi() {
            return longi;
        }

        public void setLongi(String longi) {
            this.longi = longi;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getNama_lokasi() {
            return nama_lokasi;
        }

        public void setNama_lokasi(String nama_lokasi) {
            this.nama_lokasi = nama_lokasi;
        }

        public String getId_lokasi_kerja() {
            return id_lokasi_kerja;
        }

        public void setId_lokasi_kerja(String id_lokasi_kerja) {
            this.id_lokasi_kerja = id_lokasi_kerja;
        }
    }
}
