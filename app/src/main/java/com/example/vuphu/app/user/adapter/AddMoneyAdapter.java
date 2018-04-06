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
import com.example.vuphu.app.object.HistoryDeposit;
import com.example.vuphu.app.object.addMoney;
import com.example.vuphu.app.object.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vuphu on 4/1/2018.
 */

public class AddMoneyAdapter {


    private static class addViewHolder extends RecyclerView.ViewHolder{

        TextView date ;
        TextView number ;
        TextView status ;

        public addViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tv_user_add_date);
            number = itemView.findViewById(R.id.tv_user_add_price);
            status = itemView.findViewById(R.id.tv_user_add_status);
        }
    }

    public static class orderAdap extends RecyclerView.Adapter<addViewHolder>{

        List<HistoryDeposit> list;
        Context context;
        public orderAdap(List<HistoryDeposit> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public addViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_user_add_money,parent, false);
            AddMoneyAdapter.addViewHolder vh = new addViewHolder(v);



            return vh ;
        }

        @Override
        public void onBindViewHolder(addViewHolder holder, final int position) {
            holder.status.setText(list.get(position).getId());
            holder.number.setText(list.get(position).getNumberDeposit().toString());
            holder.date.setText(list.get(position).getCreateAt());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }


    }
}
