package com.example.vuphu.app.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInCart {

    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("quatityBuy")
    @Expose
    private Integer quatityBuy;

    public String getproduct() {
        return product;
    }

    public void setproduct(String product) {
        this.product = product;
    }

    public Integer getQuatityBuy() {
        return quatityBuy;
    }

    public void setQuatityBuy(Integer quatityBuy) {
        this.quatityBuy = quatityBuy;
    }

}