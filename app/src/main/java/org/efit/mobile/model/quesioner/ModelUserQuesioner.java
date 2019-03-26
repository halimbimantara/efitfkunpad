package org.efit.mobile.model.quesioner;

public class ModelUserQuesioner {
    private int id_user;
    private int id_quesioner;
    private String jawaban;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_quesioner() {
        return id_quesioner;
    }

    public void setId_quesioner(int id_quesioner) {
        this.id_quesioner = id_quesioner;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
