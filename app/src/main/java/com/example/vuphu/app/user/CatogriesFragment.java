package com.example.vuphu.app.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.example.vuphu.app.user.adapter.ViewPagerAdapter;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class CatogriesFragment extends Fragment {


    private ViewPager viewPager;
    private RecyclerView list_product;
    private ArrayList<Product> product;

    public CatogriesFragment() {

    }
    public static CatogriesFragment newInstance() {
        CatogriesFragment fragment = new CatogriesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_catogries, container, false);

        list_product = v.findViewById(R.id.list_product);
        list_product.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        list_product.setLayoutManager(gridLayoutManager);

        product = new ArrayList<>();
        viewPager = v.findViewById(R.id.viewPager);

        loafProduct();

        list_product.setNestedScrollingEnabled(false);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        list_product.addItemDecoration(itemDecoration);


        return v;
    }


    private void loafProduct () {
        AsyncHttpApi.get("/products", null, new JsonHttpResponseHandler() {

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
                ViewPagerAdapter adapter = new ViewPagerAdapter(getContext());
                viewPager.setAdapter(adapter);


            }
        });
    }


}


