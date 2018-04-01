package com.example.vuphu.app.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuphu.app.ItemOffsetDecoration;
import com.example.vuphu.app.R;
import com.example.vuphu.app.object.product;
import com.example.vuphu.app.user.adapter.ProductApdater;
import com.example.vuphu.app.user.adapter.ViewPagerAdapter;

import java.util.ArrayList;


public class CatogriesFragment extends Fragment {


    private ViewPager viewPager;
    private ArrayList<product> list = new ArrayList<>();
    private RecyclerView list_product;

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
       list.add(new product("A","B","20.000"));
        list.add(new product("A","B","20.000"));
        list.add(new product("A","B","20.000"));
        list.add(new product("A","B","20.000"));
        list.add(new product("A","B","20.000"));
        list.add(new product("A","B","20.000"));
        list.add(new product("A","B","20.000"));
        list.add(new product("A","B","20.000"));
        list_product = v.findViewById(R.id.list_product);
        list_product.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        list_product.setLayoutManager(gridLayoutManager);

        list_product.setNestedScrollingEnabled(false);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        list_product.addItemDecoration(itemDecoration);
        ProductApdater.productAdap adap = new ProductApdater.productAdap(list, getContext());
        list_product.setAdapter(adap);

        viewPager = v.findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(adapter);



        return v;
    }


}


