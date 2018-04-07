package com.example.vuphu.app.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedProduct {

    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}