package com.example.vuphu.app.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCallback {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("created_product")
    @Expose
    private CreatedProduct createdProduct;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CreatedProduct getCreatedProduct() {
        return createdProduct;
    }

    public void setCreatedProduct(CreatedProduct createdProduct) {
        this.createdProduct = createdProduct;
    }

}