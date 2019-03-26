package org.efit.mobile.model.kemendesa.dosir;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelListDosir {

    @SerializedName("data")
    private List<ListDosir> data;
    @SerializedName("response_text")
    private String response_text;
    @SerializedName("status")
    private int status;

    public List<ListDosir> getData() {
        return data;
    }

    public void setData(List<ListDosir> data) {
        this.data = data;
    }

    public String getResponse_text() {
        return response_text;
    }

    public void setResponse_text(String response_text) {
        this.response_text = response_text;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ListDosir {
        @SerializedName("data_file")
        private String data_file;
        @SerializedName("nama_file")
        private String nama_file;
        @SerializedName("created")
        private String created;
        @SerializedName("nip")
        private String nip;

        public String getData_file() {
            return data_file;
        }

        public void setData_file(String data_file) {
            this.data_file = data_file;
        }

        public String getNama_file() {
            return nama_file;
        }

        public void setNama_file(String nama_file) {
            this.nama_file = nama_file;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getNip() {
            return nip;
        }

        public void setNip(String nip) {
            this.nip = nip;
        }
    }
}
