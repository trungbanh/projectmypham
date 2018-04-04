package com.example.vuphu.app.object;

import java.io.Serializable;

/**
 * Created by vuphu on 4/1/2018.
 */

public class order implements Serializable {

    private String ownerUid,product,quatityBuy,status, _id;

    public order() {
    }

    public order(String ownerUid, String product, String quatityBuy, String status) {
        this.ownerUid = ownerUid;
        this.product = product;
        this.quatityBuy = quatityBuy;
        this.status = status;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuatityBuy() {
        return quatityBuy;
    }

    public void setQuatityBuy(String quatityBuy) {
        this.quatityBuy = quatityBuy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
