package com.example.vuphu.app.user.UserProfileTab;

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
import com.example.vuphu.app.object.HistoryDeposit;
import com.example.vuphu.app.object.HistoryOrder;
import com.example.vuphu.app.object.addMoney;
import com.example.vuphu.app.object.listOrder;
import com.example.vuphu.app.object.order;
import com.example.vuphu.app.user.adapter.AddMoneyAdapter;
import com.example.vuphu.app.user.adapter.UserOrdersAdapter;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class AddMoneyHistoryFragment extends Fragment {
    private List<HistoryDeposit> list ;
    private RecyclerView list_add;
    LinearLayoutManager gridLayoutManager ;

    listOrder order;

    public AddMoneyHistoryFragment() {
        // Required empty public constructor
    }

    public static AddMoneyHistoryFragment newInstance() {
        AddMoneyHistoryFragment fragment = new AddMoneyHistoryFragment();
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
        View v = inflater.inflate(R.layout.fragment_add_money_history, container, false);
//        list.add(new addMoney("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
//        list.add(new addMoney("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
//        list.add(new addMoney("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
//        list.add(new addMoney("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
//        list.add(new addMoney("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
//        list.add(new addMoney("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
//        list.add(new addMoney("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
//        list.add(new addMoney("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));


        list_add = v.findViewById(R.id.list_user_add_money);

        gridLayoutManager = new LinearLayoutManager(getContext());

        return v;
    }

    void getToken (String token) {
        AsyncHttpApi.get(token,"/account",null,new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("onsucess",response.toString());
                Gson gson = new Gson();
                order = gson.fromJson(response.toString(),listOrder.class);
                if (order.getHistoryOrder().size() == 0){

                } else {
                    list = order.getHistoryDeposit();
                    list_add.setLayoutManager(gridLayoutManager);

                    list_add.setLayoutManager(gridLayoutManager);
                    list_add.setHasFixedSize(true);
                    list_add.setNestedScrollingEnabled(true);
                    AddMoneyAdapter.orderAdap adap = new AddMoneyAdapter.orderAdap(list, getContext());
                    list_add.setAdapter(adap);

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.i("fail",errorResponse.toString());
            }
        });
    }



}
