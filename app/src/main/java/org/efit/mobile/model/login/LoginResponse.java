package org.efit.mobile.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */

public class LoginResponse {


    @Expose
    @SerializedName("session_nama_lengkap")
    private String session_nama_lengkap;
    @Expose
    @SerializedName("session_username")
    private String session_username;
    @Expose
    @SerializedName("session_id_user_group")
    private String session_id_user_group;
    @Expose
    @SerializedName("session_id_users")
    private String session_id_users;

    public String getSession_nama_lengkap() {
        return session_nama_lengkap;
    }

    public void setSession_nama_lengkap(String session_nama_lengkap) {
        this.session_nama_lengkap = session_nama_lengkap;
    }

    public String getSession_username() {
        return session_username;
    }

    public void setSession_username(String session_username) {
        this.session_username = session_username;
    }

    public String getSession_id_user_group() {
        return session_id_user_group;
    }

    public void setSession_id_user_group(String session_id_user_group) {
        this.session_id_user_group = session_id_user_group;
    }

    public String getSession_id_users() {
        return session_id_users;
    }

    public void setSession_id_users(String session_id_users) {
        this.session_id_users = session_id_users;
    }
}
