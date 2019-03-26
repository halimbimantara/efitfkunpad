package org.efit.mobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ModalAbout {

    @SerializedName("data")
    private List<Data> data;
    @SerializedName("total")
    private int total;
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public static class Data {
        @SerializedName("pemodif")
        private String pemodif;
        @SerializedName("pembuat")
        private String pembuat;
        @SerializedName("pendelete")
        private String pendelete;
        @SerializedName("deleted_by")
        private String deleted_by;
        @SerializedName("deleted")
        private String deleted;
        @SerializedName("modified_by")
        private String modified_by;
        @SerializedName("modified_on")
        private String modified_on;
        @SerializedName("created_by")
        private String created_by;
        @SerializedName("created_on")
        private String created_on;
        @SerializedName("post_type")
        private String post_type;
        @SerializedName("post_lang_parent")
        private String post_lang_parent;
        @SerializedName("post_lang")
        private String post_lang;
        @SerializedName("post_meta_optional")
        private String post_meta_optional;
        @SerializedName("post_template")
        private String post_template;
        @SerializedName("post_read")
        private String post_read;
        @SerializedName("post_comment_count")
        private String post_comment_count;
        @SerializedName("post_mime_type")
        private String post_mime_type;
        @SerializedName("post_menu_order")
        private String post_menu_order;
        @SerializedName("post_parent")
        private String post_parent;
        @SerializedName("post_name")
        private String post_name;
        @SerializedName("post_guid")
        private String post_guid;
        @SerializedName("post_attachment")
        private String post_attachment;
        @SerializedName("post_image")
        private String post_image;
        @SerializedName("post_password")
        private String post_password;
        @SerializedName("post_comment")
        private String post_comment;
        @SerializedName("post_status")
        private String post_status;
        @SerializedName("post_content")
        private String post_content;
        @SerializedName("post_title")
        private String post_title;
        @SerializedName("post_code_unique")
        private String post_code_unique;
        @SerializedName("id_post")
        private String id_post;

        public String getPemodif() {
            return pemodif;
        }

        public void setPemodif(String pemodif) {
            this.pemodif = pemodif;
        }

        public String getPembuat() {
            return pembuat;
        }

        public void setPembuat(String pembuat) {
            this.pembuat = pembuat;
        }

        public String getPendelete() {
            return pendelete;
        }

        public void setPendelete(String pendelete) {
            this.pendelete = pendelete;
        }

        public String getDeleted_by() {
            return deleted_by;
        }

        public void setDeleted_by(String deleted_by) {
            this.deleted_by = deleted_by;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getModified_by() {
            return modified_by;
        }

        public void setModified_by(String modified_by) {
            this.modified_by = modified_by;
        }

        public String getModified_on() {
            return modified_on;
        }

        public void setModified_on(String modified_on) {
            this.modified_on = modified_on;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getPost_type() {
            return post_type;
        }

        public void setPost_type(String post_type) {
            this.post_type = post_type;
        }

        public String getPost_lang_parent() {
            return post_lang_parent;
        }

        public void setPost_lang_parent(String post_lang_parent) {
            this.post_lang_parent = post_lang_parent;
        }

        public String getPost_lang() {
            return post_lang;
        }

        public void setPost_lang(String post_lang) {
            this.post_lang = post_lang;
        }

        public String getPost_meta_optional() {
            return post_meta_optional;
        }

        public void setPost_meta_optional(String post_meta_optional) {
            this.post_meta_optional = post_meta_optional;
        }

        public String getPost_template() {
            return post_template;
        }

        public void setPost_template(String post_template) {
            this.post_template = post_template;
        }

        public String getPost_read() {
            return post_read;
        }

        public void setPost_read(String post_read) {
            this.post_read = post_read;
        }

        public String getPost_comment_count() {
            return post_comment_count;
        }

        public void setPost_comment_count(String post_comment_count) {
            this.post_comment_count = post_comment_count;
        }

        public String getPost_mime_type() {
            return post_mime_type;
        }

        public void setPost_mime_type(String post_mime_type) {
            this.post_mime_type = post_mime_type;
        }

        public String getPost_menu_order() {
            return post_menu_order;
        }

        public void setPost_menu_order(String post_menu_order) {
            this.post_menu_order = post_menu_order;
        }

        public String getPost_parent() {
            return post_parent;
        }

        public void setPost_parent(String post_parent) {
            this.post_parent = post_parent;
        }

        public String getPost_name() {
            return post_name;
        }

        public void setPost_name(String post_name) {
            this.post_name = post_name;
        }

        public String getPost_guid() {
            return post_guid;
        }

        public void setPost_guid(String post_guid) {
            this.post_guid = post_guid;
        }

        public String getPost_attachment() {
            return post_attachment;
        }

        public void setPost_attachment(String post_attachment) {
            this.post_attachment = post_attachment;
        }

        public String getPost_image() {
            return post_image;
        }

        public void setPost_image(String post_image) {
            this.post_image = post_image;
        }

        public String getPost_password() {
            return post_password;
        }

        public void setPost_password(String post_password) {
            this.post_password = post_password;
        }

        public String getPost_comment() {
            return post_comment;
        }

        public void setPost_comment(String post_comment) {
            this.post_comment = post_comment;
        }

        public String getPost_status() {
            return post_status;
        }

        public void setPost_status(String post_status) {
            this.post_status = post_status;
        }

        public String getPost_content() {
            return post_content;
        }

        public void setPost_content(String post_content) {
            this.post_content = post_content;
        }

        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public String getPost_code_unique() {
            return post_code_unique;
        }

        public void setPost_code_unique(String post_code_unique) {
            this.post_code_unique = post_code_unique;
        }

        public String getId_post() {
            return id_post;
        }

        public void setId_post(String id_post) {
            this.id_post = id_post;
        }
    }
}
