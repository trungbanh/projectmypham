package com.example.vuphu.app.user.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuphu.app.R;
import com.example.vuphu.app.object.addMoney;
import com.example.vuphu.app.object.order;

import java.util.ArrayList;

/**
 * Created by vuphu on 4/1/2018.
 */

public class AddMoneyAdapter {

    private static class addViewHolder extends RecyclerView.ViewHolder{


        private TextView tv_name, tv_price,tv_date,tv_status;

        public addViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_user_add_name);
            tv_date = itemView.findViewById(R.id.tv_user_add_date);
            tv_price = itemView.findViewById(R.id.tv_user_add_price);
            tv_status  = itemView.findViewById(R.id.tv_user_add_status);


        }
    }

    public static class orderAdap extends RecyclerView.Adapter<addViewHolder>{

        ArrayList<addMoney> list;
        Context context;
        public orderAdap(ArrayList<addMoney> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public addViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_user_add_money,parent, false);
            return new addViewHolder(v);
        }

        @Override
        public void onBindViewHolder(addViewHolder holder, final int position) {
            holder.tv_name.setText(list.get(position).getUser());
            holder.tv_price.setText(list.get(position).getPrice());
            holder.tv_date.setText(list.get(position).getDate());
            holder.tv_status.setText(list.get(position).getStatus());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
