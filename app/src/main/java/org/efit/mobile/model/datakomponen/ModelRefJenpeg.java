package org.efit.mobile.model.datakomponen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 01/01/2018.
 */

public class ModelRefJenpeg {

    @SerializedName("kjpeg")
    public String kjpeg;
    @SerializedName("njpeg")
    public String njpeg;

    public String getKjpeg() {
        return kjpeg;
    }

    public void setKjpeg(String kjpeg) {
        this.kjpeg = kjpeg;
    }

    public String getNjpeg() {
        return njpeg;
    }

    public void setNjpeg(String njpeg) {
        this.njpeg = njpeg;
    }
}
