package org.efit.mobile.model.datakomponen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 01/01/2018.
 */

public class ModelKomponen {

        @SerializedName("kunkom")
        public String kunkom;
        @SerializedName("nunker")
        public String nunker;

    public String getKunkom() {
        return kunkom;
    }

    public void setKunkom(String kunkom) {
        this.kunkom = kunkom;
    }

    public String getNunker() {
        return nunker;
    }

    public void setNunker(String nunker) {
        this.nunker = nunker;
    }
}
