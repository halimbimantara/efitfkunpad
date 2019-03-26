package org.efit.mobile.model.login;

import com.google.gson.annotations.SerializedName;

public class EfitLogin {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data_user")
    private Data_user data_user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data_user getData_user() {
        return data_user;
    }

    public void setData_user(Data_user data_user) {
        this.data_user = data_user;
    }

    public static class Data_user {
        @SerializedName("profile_image")
        private String profile_image;
        @SerializedName("gender")
        private String gender;
        @SerializedName("phone")
        private String phone;
        @SerializedName("bio")
        private String bio;
        @SerializedName("state")
        private String state;
        @SerializedName("role_name")
        private String role_name;
        @SerializedName("kode_pos")
        private String kode_pos;
        @SerializedName("id_departement")
        private String id_departement;
        @SerializedName("oauth_facebook")
        private String oauth_facebook;
        @SerializedName("oauth_google")
        private String oauth_google;
        @SerializedName("address")
        private String address;
        @SerializedName("city_code")
        private String city_code;
        @SerializedName("province_code")
        private String province_code;
        @SerializedName("country")
        private String country;
        @SerializedName("image_data")
        private String image_data;
        @SerializedName("meta_options")
        private String meta_options;
        @SerializedName("force_password_reset")
        private String force_password_reset;
        @SerializedName("activate_hash")
        private String activate_hash;
        @SerializedName("active")
        private String active;
        @SerializedName("language")
        private String language;
        @SerializedName("timezone")
        private String timezone;
        @SerializedName("display_name_changed")
        private String display_name_changed;
        @SerializedName("display_name")
        private String display_name;
        @SerializedName("ban_message")
        private String ban_message;
        @SerializedName("banned")
        private String banned;
        @SerializedName("reset_by")
        private String reset_by;
        @SerializedName("deleted")
        private String deleted;
        @SerializedName("created_on")
        private String created_on;
        @SerializedName("last_ip")
        private String last_ip;
        @SerializedName("last_login")
        private String last_login;
        @SerializedName("reset_hash")
        private String reset_hash;
        @SerializedName("password_hash")
        private String password_hash;
        @SerializedName("username")
        private String username;
        @SerializedName("email")
        private String email;
        @SerializedName("role_id")
        private String role_id;
        @SerializedName("id")
        private String id;

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }

        public String getKode_pos() {
            return kode_pos;
        }

        public void setKode_pos(String kode_pos) {
            this.kode_pos = kode_pos;
        }

        public String getId_departement() {
            return id_departement;
        }

        public void setId_departement(String id_departement) {
            this.id_departement = id_departement;
        }

        public String getOauth_facebook() {
            return oauth_facebook;
        }

        public void setOauth_facebook(String oauth_facebook) {
            this.oauth_facebook = oauth_facebook;
        }

        public String getOauth_google() {
            return oauth_google;
        }

        public void setOauth_google(String oauth_google) {
            this.oauth_google = oauth_google;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getImage_data() {
            return image_data;
        }

        public void setImage_data(String image_data) {
            this.image_data = image_data;
        }

        public String getMeta_options() {
            return meta_options;
        }

        public void setMeta_options(String meta_options) {
            this.meta_options = meta_options;
        }

        public String getForce_password_reset() {
            return force_password_reset;
        }

        public void setForce_password_reset(String force_password_reset) {
            this.force_password_reset = force_password_reset;
        }

        public String getActivate_hash() {
            return activate_hash;
        }

        public void setActivate_hash(String activate_hash) {
            this.activate_hash = activate_hash;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getDisplay_name_changed() {
            return display_name_changed;
        }

        public void setDisplay_name_changed(String display_name_changed) {
            this.display_name_changed = display_name_changed;
        }

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getBan_message() {
            return ban_message;
        }

        public void setBan_message(String ban_message) {
            this.ban_message = ban_message;
        }

        public String getBanned() {
            return banned;
        }

        public void setBanned(String banned) {
            this.banned = banned;
        }

        public String getReset_by() {
            return reset_by;
        }

        public void setReset_by(String reset_by) {
            this.reset_by = reset_by;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public String getReset_hash() {
            return reset_hash;
        }

        public void setReset_hash(String reset_hash) {
            this.reset_hash = reset_hash;
        }

        public String getPassword_hash() {
            return password_hash;
        }

        public void setPassword_hash(String password_hash) {
            this.password_hash = password_hash;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
