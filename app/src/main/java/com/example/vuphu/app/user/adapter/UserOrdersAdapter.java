package com.example.vuphu.app.user.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuphu.app.R;
import com.example.vuphu.app.admin.AdminEditProductActivity;
import com.example.vuphu.app.object.order;

import java.util.ArrayList;

/**
 * Created by vuphu on 4/1/2018.
 */

public class UserOrdersAdapter {

    private static class orderViewHolder extends RecyclerView.ViewHolder{


        private TextView tv_name, tv_price,tv_date,tv_status;

        public orderViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_user_order_name);
            tv_date = itemView.findViewById(R.id.tv_user_order_date);
            tv_price = itemView.findViewById(R.id.tv_user_order_price);
            tv_status  = itemView.findViewById(R.id.tv_user_order_status);



        }
    }

    public static class orderAdap extends RecyclerView.Adapter<orderViewHolder>{

        ArrayList<order> list;
        Context context;
        public orderAdap(ArrayList<order> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public orderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_user_order,parent, false);
            return new orderViewHolder(v);
        }

        @Override
        public void onBindViewHolder(orderViewHolder holder, final int position) {
            holder.tv_name.setText(list.get(position).getUser());
            holder.tv_price.setText(list.get(position).getPrice());
            holder.tv_date.setText(list.get(position).getDate());
            holder.tv_status.setText(list.get(position).getStatus());

            // get <json> kem token

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
