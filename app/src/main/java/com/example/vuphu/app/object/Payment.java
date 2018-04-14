package com.example.vuphu.app.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("createdOrder")
    @Expose
    private com.example.vuphu.app.object.CreatedOrder createdOrder;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public com.example.vuphu.app.object.CreatedOrder getCreatedOrder() {
        return createdOrder;
    }

    public void setCreatedOrder(com.example.vuphu.app.object.CreatedOrder createdOrder) {
        this.createdOrder = createdOrder;
    }

}