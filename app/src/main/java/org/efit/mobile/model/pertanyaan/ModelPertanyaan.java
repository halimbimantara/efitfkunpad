package org.efit.mobile.model.pertanyaan;

public class ModelPertanyaan {
    private int ID;
    private String pertanyaan;
    private int tipe_soal;
    private String kategori;
    private String description;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public int getTipe_soal() {
        return tipe_soal;
    }

    public void setTipe_soal(int tipe_soal) {
        this.tipe_soal = tipe_soal;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
