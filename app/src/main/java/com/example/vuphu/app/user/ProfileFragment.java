package com.example.vuphu.app.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.AcsynHttp.NetworkConst;
import com.example.vuphu.app.R;
import com.example.vuphu.app.object.listOrder;
import com.example.vuphu.app.object.AcountId;
import com.example.vuphu.app.user.adapter.TabPagerAdapter;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {

    private SharedPreferences pre ;
    private TextView name ;
    private CircleImageView avt ;
    private AcountId u ;
    private TextView money ;
    private listOrder order;


    public ProfileFragment() {

    }


    public static ProfileFragment newInstance(AcountId user) {
        ProfileFragment fragment = new ProfileFragment();
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        viewPager.setAdapter(new TabPagerAdapter(getActivity().getSupportFragmentManager(),
                getActivity()));
        pre = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        getToken(pre.getString(NetworkConst.token,""));
        name = v.findViewById(R.id.tv_user_name);
        avt = v.findViewById(R.id.img_user) ;
        money = v.findViewById(R.id.tv_user_money);

        return v;
    }


    void getToken (String token) {
        AsyncHttpApi.get(token,"/account",null,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                order = gson.fromJson(response.toString(),listOrder.class);
                u = order.getAccountId();

                Log.i("onsucess",order.getBalanced().toString());
                name.setText(u.getName());
                Picasso.get().load(NetworkConst.network+"/"+u.getAvatar().replace("\\","/")).error(R.drawable.ic_terrain_black_24dp).into(avt);
                money.setText(order.getBalanced().toString()+"0");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.i("fail",errorResponse.toString());
            }
        });
    }

}
