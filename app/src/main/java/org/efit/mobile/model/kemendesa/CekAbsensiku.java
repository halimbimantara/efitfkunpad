package org.efit.mobile.model.kemendesa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CekAbsensiku {

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
        @SerializedName("pulang")
        private String pulang;
        @SerializedName("datang")
        private String datang;
        @SerializedName("tanggal")
        private String tanggal;
        @SerializedName("nip")
        private String nip;

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

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }
    }
}
