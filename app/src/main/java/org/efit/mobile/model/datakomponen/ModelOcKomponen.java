package org.efit.mobile.model.datakomponen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 01/01/2018.
 */

public class ModelOcKomponen {

        @SerializedName("kununit")
        public String kununit;
        @SerializedName("nunker")
        public String nunker;

    public String getKununit() {
        return kununit;
    }

    public void setKununit(String kununit) {
        this.kununit = kununit;
    }

    public String getNunker() {
        return nunker;
    }

    public void setNunker(String nunker) {
        this.nunker = nunker;
    }
}
