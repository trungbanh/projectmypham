package com.example.vuphu.app.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryDeposit {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("numberDeposit")
    @Expose
    private Integer numberDeposit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Integer getNumberDeposit() {
        return numberDeposit;
    }

    public void setNumberDeposit(Integer numberDeposit) {
        this.numberDeposit = numberDeposit;
    }

}