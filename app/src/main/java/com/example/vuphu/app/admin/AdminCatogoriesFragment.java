package com.example.vuphu.app.admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.ItemOffsetDecoration;
import com.example.vuphu.app.R;
import com.example.vuphu.app.admin.adapter.AdminProductApdater;
import com.example.vuphu.app.object.Product;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class AdminCatogoriesFragment extends Fragment {

    private ArrayList<Product> list ;
    private RecyclerView list_product;
    public AdminCatogoriesFragment() {
        // Required empty public constructor
    }

    public static AdminCatogoriesFragment newInstance() {
        AdminCatogoriesFragment fragment = new AdminCatogoriesFragment();
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
        View v = inflater.inflate(R.layout.fragment_admin_catogories, container, false);
        /*list.add(new Product("A","B","20.000"));
        list.add(new Product("A","B","20.000"));
        list.add(new Product("A","B","20.000"));
        list.add(new Product("A","B","20.000"));
        list.add(new Product("A","B","20.000"));
        list.add(new Product("A","B","20.000"));
        list.add(new Product("A","B","20.000"));
        list.add(new Product("A","B","20.000"));*/

        list = new ArrayList<>();

        loafProduct();

        list_product = v.findViewById(R.id.list_admin_product);
        list_product.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        list_product.setLayoutManager(gridLayoutManager);

        list_product.setNestedScrollingEnabled(false);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        list_product.addItemDecoration(itemDecoration);
        AdminProductApdater.productAdap adap = new AdminProductApdater.productAdap(list, getContext());
        list_product.setAdapter(adap);
        return v;
    }

    private void loafProduct () {
        AsyncHttpApi.get("/products", null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                Gson gson = new Gson();
                JSONArray jArray = (JSONArray)response;
                if (jArray != null) {
                    for (int i=0;i<jArray.length();i++){
                        try {
                            list.add(gson.fromJson(jArray.get(i).toString(),Product.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }



}
