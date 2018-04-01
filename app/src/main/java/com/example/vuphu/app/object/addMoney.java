package com.example.vuphu.app.object;

/**
 * Created by vuphu on 4/1/2018.
 */

public class addMoney {
    private String date, price, status, user;

    public addMoney() {
    }

    public addMoney(String date, String price, String status, String user) {
        this.date = date;
        this.price = price;
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

    public void setPrice(String price) {
        this.price = price;
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
