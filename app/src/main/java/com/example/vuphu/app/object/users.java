package com.example.vuphu.app.object;

import java.io.Serializable;

/**
 * Created by vuphu on 4/1/2018.
 */

public class users  implements Serializable {

    private String name,img;

    public users(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
