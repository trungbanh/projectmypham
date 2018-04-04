package com.example.vuphu.app.object;

import java.io.Serializable;

/**
 * Created by vuphu on 4/1/2018.
 */

public class users  implements Serializable {

    private String name,avatar,_id, email;

    public users() {
    }

    public users(String name, String avatar, String _id, String email) {
        this.name = name;
        this.avatar = avatar;
        this._id = _id;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
