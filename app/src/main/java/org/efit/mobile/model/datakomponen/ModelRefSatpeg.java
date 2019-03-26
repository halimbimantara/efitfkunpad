package org.efit.mobile.model.datakomponen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 01/01/2018.
 */

public class ModelRefSatpeg {

    @SerializedName("kstatus")
    public String kstatus;
    @SerializedName("nstatus")
    public String nstatus;

    public String getKstatus() {
        return kstatus;
    }

    public void setKstatus(String kstatus) {
        this.kstatus = kstatus;
    }

    public String getNstatus() {
        return nstatus;
    }

    public void setNstatus(String nstatus) {
        this.nstatus = nstatus;
    }
}
