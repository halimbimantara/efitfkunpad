package org.efit.mobile.model.datakomponen;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 01/01/2018.
 */

public class ModelOcSubKomponen {

    @SerializedName("kunsk")
    public String kunsk;
    @SerializedName("nunker")
    public String nunker;

    public String getKunsk() {
        return kunsk;
    }

    public void setKunsk(String kunsk) {
        this.kunsk = kunsk;
    }

    public String getNunker() {
        return nunker;
    }

    public void setNunker(String nunker) {
        this.nunker = nunker;
    }
}
