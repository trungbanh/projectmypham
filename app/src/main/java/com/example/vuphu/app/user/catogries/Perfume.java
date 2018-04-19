package com.example.vuphu.app.user.catogries;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.ItemOffsetDecoration;
import com.example.vuphu.app.R;
import com.example.vuphu.app.object.Product;
import com.example.vuphu.app.user.adapter.ProductApdater;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class Perfume extends Fragment {

    private RecyclerView list_product;
    private ArrayList<Product> product;


    public Perfume() {
        // Required empty public constructor
    }

    public static Perfume newInstance() {
        Perfume fragment = new Perfume();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_perfume, container, false);

        list_product = v.findViewById(R.id.list_product_perfume);
        list_product.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        list_product.setLayoutManager(gridLayoutManager);

        product = new ArrayList<>();
        loafProduct();

        list_product.setNestedScrollingEnabled(false);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        list_product.addItemDecoration(itemDecoration);

        return  v;
    }
    public void loafProduct () {
        AsyncHttpApi.get(getActivity().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token",""),"/products/cator/perfume", null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Gson gson = new Gson();
                JSONArray jArray = response;
                if (jArray != null) {
                    for (int i=0;i<jArray.length();i++){
                        try {
                            product.add(gson.fromJson(jArray.get(i).toString(),Product.class));
                            Log.i("product",jArray.get(i).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ProductApdater.productAdap adap = new ProductApdater.productAdap(product, getContext());
                list_product.setAdapter(adap);
            }
        });
    }


}
