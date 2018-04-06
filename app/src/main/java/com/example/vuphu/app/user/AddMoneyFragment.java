package com.example.vuphu.app.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class AddMoneyFragment extends Fragment {


    private EditText numbercard ;

    private Button addmoney ;

    private SharedPreferences pre ;


    public AddMoneyFragment() {
        // Required empty public constructor
    }


    public static AddMoneyFragment newInstance() {
        AddMoneyFragment fragment = new AddMoneyFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_money, container, false);

        numbercard = view.findViewById(R.id.edt_card_number);
        addmoney = view.findViewById(R.id.btn_add_money);

        pre = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        addmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCast(pre.getString("token",""),numbercard.getText().toString());
            }
        });
        return view;
    }

    private void addCast (String token,String num) {
        RequestParams params = new RequestParams() ;
        params.put("balance",Integer.parseInt(num));

        AsyncHttpApi.post(token,"/account/deposit",params,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
