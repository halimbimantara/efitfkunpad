package org.efit.mobile.model.kemendesa;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 04/11/2017.
 */

public class ApiResponse {
    @SerializedName("RC")
    private String RC;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private boolean status;

    public String getRC() {
        return RC;
    }

    public void setRC(String RC) {
        this.RC = RC;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
