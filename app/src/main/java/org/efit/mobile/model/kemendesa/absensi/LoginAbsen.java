package org.efit.mobile.model.kemendesa.absensi;

import com.google.gson.annotations.SerializedName;

public class LoginAbsen {

    @SerializedName("results")
    private ResultsEntity results;
    @SerializedName("RC")
    private String RC;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private boolean status;

    public ResultsEntity getResults() {
        return results;
    }

    public void setResults(ResultsEntity results) {
        this.results = results;
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

    public static class ResultsEntity {
        @SerializedName("Golongan")
        private String Golongan;
        @SerializedName("Lokasi_Kerja")
        private String Lokasi_Kerja;
        @SerializedName("NIP")
        private String NIP;
        @SerializedName("Nama")
        private String Nama;

        public String getNama() {
            return Nama;
        }

        public void setNama(String nama) {
            Nama = nama;
        }

        public String getGolongan() {
            return Golongan;
        }

        public void setGolongan(String golongan) {
            Golongan = golongan;
        }

        public String getLokasi_Kerja() {
            return Lokasi_Kerja;
        }

        public void setLokasi_Kerja(String lokasi_Kerja) {
            Lokasi_Kerja = lokasi_Kerja;
        }

        public String getNIP() {
            return NIP;
        }

        public void setNIP(String NIP) {
            this.NIP = NIP;
        }
    }
}
