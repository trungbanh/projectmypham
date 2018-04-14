package com.example.vuphu.app.object;

import com.example.vuphu.app.object.AcountId;
import com.example.vuphu.app.object.HistoryDeposit;
import com.example.vuphu.app.object.HistoryOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class listOrder {

    @SerializedName("balanced")
    @Expose
    private Integer balanced;
    @SerializedName("historyDeposit")
    @Expose
    private List<HistoryDeposit> historyDeposit = null;
    @SerializedName("historyOrder")
    @Expose
    private List<HistoryOrder> historyOrder = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("accountId")
    @Expose
    private com.example.vuphu.app.object.AcountId accountId;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getBalanced() {
        return balanced;
    }

    public void setBalanced(Integer balanced) {
        this.balanced = balanced;
    }

    public List<com.example.vuphu.app.object.HistoryDeposit> getHistoryDeposit() {
        return historyDeposit;
    }

    public void setHistoryDeposit(List<com.example.vuphu.app.object.HistoryDeposit> historyDeposit) {
        this.historyDeposit = historyDeposit;
    }

    public List<HistoryOrder> getHistoryOrder() {
        return historyOrder;
    }

    public void setHistoryOrder(List<HistoryOrder> historyOrder) {
        this.historyOrder = historyOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AcountId getAccountId() {
        return accountId;
    }

    public void setAccountId(AcountId accountId) {
        this.accountId = accountId;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}