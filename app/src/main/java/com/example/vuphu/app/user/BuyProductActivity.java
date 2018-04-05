package com.example.vuphu.app.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.R;
import com.example.vuphu.app.object.Payment;
import com.example.vuphu.app.object.Product;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class BuyProductActivity extends AppCompatActivity {

    private TextView productsName ;
    private TextView price ;
    private TextView quantity ;
    private TextView sumary ;
    private ImageView sub ;
    private ImageView add ;
    private Button buy ;

    private SharedPreferences pre ;
    private SharedPreferences.Editor edit ;

    int no = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);
        // /order <productid,quantity>
        init() ;

        pre = getSharedPreferences("data",MODE_PRIVATE);
        edit = pre.edit();

        Intent intent = getIntent();

        final Product product = (Product) intent.getSerializableExtra("productId");

        if (product==null) {
            Log.i("null","fadfasf");
        }

        productsName.setText(product.getName());

        price.setText(product.getPrice()+"");

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (no==1) {
                    Toast.makeText(BuyProductActivity.this, "munber can't lower than 1", Toast.LENGTH_SHORT).show();
                }
                else {
                    no--;
                    quantity.setText(String.valueOf(no));
                    sumary.setText(String.valueOf(no*product.getPrice()));
                }

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no++;
                quantity.setText(String.valueOf(no));
                sumary.setText(String.valueOf(no*product.getPrice()));
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = Integer.parseInt(quantity.getText().toString()) * no ;
                if(false) {
                    Toast.makeText(BuyProductActivity.this, "no enough money", Toast.LENGTH_SHORT).show();
                }
                else {
                    postPostOrder(product.getId(),no);
                }
            }
        });


    }

    private void init () {
        productsName =findViewById(R.id.tv_buy_name);
        price =       findViewById(R.id.tv_buy_price);
        quantity =    findViewById(R.id.tv_buy_count);
        sumary =      findViewById(R.id.tv_sum_money);
        sub=          findViewById(R.id.usr_sub_order);
        add =         findViewById(R.id.usr_add_order);
        buy =         findViewById(R.id.btn_buy);
    }

    private void postPostOrder (String idProduct ,int num){
        RequestParams params = new RequestParams();
        params.put("productId",idProduct);
        params.put("quatityBuy",no);

        AsyncHttpApi.post(pre.getString("token",""),"/orders",params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("buy",response.toString());
                Gson gson = new Gson();

                if (response!=null) {

                    Payment payment = gson.fromJson(response.toString(),Payment.class);
                    paymentFuntion(pre.getString("token",""),payment.getCreatedOrder().getId().toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.i("buy",errorResponse.toString());
            }
        });
    }

    private void paymentFuntion (String token,String idOrder) {
        RequestParams params = new RequestParams();
        params.put("_id",idOrder);
        AsyncHttpApi.post(token,"/payment",params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (response != null) {
                    Toast.makeText(BuyProductActivity.this, "order complete !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
