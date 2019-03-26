package org.efit.mobile.model.datakomponen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 01/01/2018.
 */

public class ModelSatker {

        @SerializedName("kode")
        public String kode;
        @SerializedName("nunker")
        public String nunker;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNunker() {
        return nunker;
    }

    public void setNunker(String nunker) {
        this.nunker = nunker;
    }
}
