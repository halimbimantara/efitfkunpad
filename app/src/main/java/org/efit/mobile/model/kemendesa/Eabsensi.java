package org.efit.mobile.model.kemendesa;

import com.google.gson.annotations.SerializedName;

public class Eabsensi {

    @SerializedName("nama")
    private String nama;
    @SerializedName("radius")
    private String radius;
    @SerializedName("lng")
    private String lng;
    @SerializedName("lat")
    private String lat;
    @SerializedName("Message")
    private String Message;
    @SerializedName("code")
    private int code;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
