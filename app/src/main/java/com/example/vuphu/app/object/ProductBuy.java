package com.example.vuphu.app.object;

import com.loopj.android.http.RequestParams;

public class ProductBuy {
    private String product ;
    private int quatityBuy ;

    public ProductBuy (String productId ,int quantity) {
        this.product = productId;
        this.quatityBuy = quantity ;
    }

     public String getId() {
        return this.product;
     }
     public int getQuantity () {
        return this.quatityBuy;
     }

     public RequestParams getParam() {
        RequestParams params = new RequestParams() ;
        params.put("product",this.product);
        params.put("quatityBuy",this.quatityBuy);

        return params;
     }

}
