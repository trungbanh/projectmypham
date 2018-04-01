package com.example.vuphu.app.object;

import java.io.Serializable;

/**
 * Created by vuphu on 4/1/2018.
 */

public class order implements Serializable {

    private String date, price, status, user;

    public order() {
    }

    public order(String date, String money, String status, String user) {
        this.date = date;
        this.price = money;
        this.status = status;
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String money) {
        this.price = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
