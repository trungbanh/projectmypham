package com.example.vuphu.app.admin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.R;
import com.example.vuphu.app.admin.adapter.AdminOrdersAdapter;
import com.example.vuphu.app.admin.adapter.AdminProductApdater;
import com.example.vuphu.app.object.Product;
import com.example.vuphu.app.object.order;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.content.Context.MODE_PRIVATE;


public class AdminOrdersFragment extends Fragment {


    private ArrayList<order> list = new ArrayList<>();
    private RecyclerView list_order;
    private SharedPreferences pre;
    public AdminOrdersFragment() {

    }


    public static AdminOrdersFragment newInstance() {
        AdminOrdersFragment fragment = new AdminOrdersFragment();
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
        View v = inflater.inflate(R.layout.fragment_admin_orders, container, false);

        pre =getActivity().getSharedPreferences("data", MODE_PRIVATE);
        list_order = v.findViewById(R.id.list_admin_orders);

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        list_order.setLayoutManager(gridLayoutManager);
        list_order.setHasFixedSize(true);
        loadorder();
        return v;
    }

    private void loadorder () {
        AsyncHttpApi.get(pre.getString("token",""),"/orders/", null,
                new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Gson gson = new Gson();
                JSONArray jArray = response;
                if (jArray != null) {
                    for (int i=0;i<jArray.length();i++){
                        try {
                            list.add(gson.fromJson(jArray.get(i).toString(),order.class));
                            Log.i("order",jArray.get(i).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                AdminOrdersAdapter.orderAdap adap = new AdminOrdersAdapter.orderAdap(list,
                        getContext(),pre.getString("token",""));
                list_order.setAdapter(adap);
                adap.notifyDataSetChanged();
            }
        });
    }

}
