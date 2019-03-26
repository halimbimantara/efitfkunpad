package org.efit.mobile.model.dataharian;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelInAsupanharian {

    @SerializedName("detail")
    private List<Detail> detail;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("status")
    private String status;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("kode_transaksi")
    private String kode_transaksi;
    @SerializedName("id_master_asupan")
    private String id_master_asupan;

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public String getId_master_asupan() {
        return id_master_asupan;
    }

    public void setId_master_asupan(String id_master_asupan) {
        this.id_master_asupan = id_master_asupan;
    }

    public static class Detail {
        @SerializedName("tanggal_input")
        private String tanggal_input;
        @SerializedName("jam_makan")
        private String jam_makan;
        @SerializedName("id_user")
        private String id_user;
        @SerializedName("jumlah")
        private String jumlah;
        @SerializedName("zinc")
        private String zinc;
        @SerializedName("calsium")
        private String calsium;
        @SerializedName("vit_c")
        private String vit_c;
        @SerializedName("vit_a")
        private String vit_a;
        @SerializedName("protein")
        private String protein;
        @SerializedName("energi")
        private String energi;
        @SerializedName("id_dkbm")
        private String id_dkbm;
        @SerializedName("kode_transaksi")
        private String kode_transaksi;

        public String getTanggal_input() {
            return tanggal_input;
        }

        public void setTanggal_input(String tanggal_input) {
            this.tanggal_input = tanggal_input;
        }

        public String getJam_makan() {
            return jam_makan;
        }

        public void setJam_makan(String jam_makan) {
            this.jam_makan = jam_makan;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getJumlah() {
            return jumlah;
        }

        public void setJumlah(String jumlah) {
            this.jumlah = jumlah;
        }

        public String getZinc() {
            return zinc;
        }

        public void setZinc(String zinc) {
            this.zinc = zinc;
        }

        public String getCalsium() {
            return calsium;
        }

        public void setCalsium(String calsium) {
            this.calsium = calsium;
        }

        public String getVit_c() {
            return vit_c;
        }

        public void setVit_c(String vit_c) {
            this.vit_c = vit_c;
        }

        public String getVit_a() {
            return vit_a;
        }

        public void setVit_a(String vit_a) {
            this.vit_a = vit_a;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getEnergi() {
            return energi;
        }

        public void setEnergi(String energi) {
            this.energi = energi;
        }

        public String getId_dkbm() {
            return id_dkbm;
        }

        public void setId_dkbm(String id_dkbm) {
            this.id_dkbm = id_dkbm;
        }

        public String getKode_transaksi() {
            return kode_transaksi;
        }

        public void setKode_transaksi(String kode_transaksi) {
            this.kode_transaksi = kode_transaksi;
        }
    }
}
