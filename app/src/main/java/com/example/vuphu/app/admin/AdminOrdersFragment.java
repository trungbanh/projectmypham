package com.example.vuphu.app.admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuphu.app.R;
import com.example.vuphu.app.admin.adapter.AdminOrdersAdapter;
import com.example.vuphu.app.object.order;

import java.util.ArrayList;


public class AdminOrdersFragment extends Fragment {


    private ArrayList<order> list = new ArrayList<>();
    private RecyclerView list_order;
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
        list.add(new order("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
        list.add(new order("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
        list.add(new order("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
        list.add(new order("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
        list.add(new order("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
        list.add(new order("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
        list.add(new order("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));
        list.add(new order("22/12/2012","20.000","Đang xử lí","Nguyễn Thị A"));


        list_order = v.findViewById(R.id.list_admin_orders);

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());

        list_order.setLayoutManager(gridLayoutManager);
        list_order.setHasFixedSize(true);

        AdminOrdersAdapter.orderAdap adap = new AdminOrdersAdapter.orderAdap(list, getContext());
        list_order.setAdapter(adap);
        return v;
    }

}
