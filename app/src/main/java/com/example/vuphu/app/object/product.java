package com.example.vuphu.app.object;

import java.io.Serializable;

/**
 * Created by vuphu on 3/31/2018.
 */

public class product implements Serializable {

    private String name,img,price;


    public product(String name, String img, String price) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public product() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
