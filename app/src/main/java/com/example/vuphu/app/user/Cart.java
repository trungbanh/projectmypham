package com.example.vuphu.app.user;

import com.example.vuphu.app.object.ProductBuy;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class Cart {
    private ArrayList<ProductBuy> productBuys ;

    public Cart () {
        this.productBuys = new ArrayList<>();
    }

    private static Cart Instance;
    public static Cart getInstance() {
        if (Instance == null) {
            Instance = new Cart();

        }
        return Instance;
    }
    public void AddProduct (String id,int no){
        productBuys.add(new ProductBuy(id,no));
    }

    public String toParam () {

        StringBuilder param = new StringBuilder();
        param.append("{ \"products\": [" );
        for (int i =0 ; i< productBuys.size();i++) {
            param.append(productBuys.get(i).getParam());
            param.append(",");
        }
        param.append("] }");
        return param.toString();
    }


}
