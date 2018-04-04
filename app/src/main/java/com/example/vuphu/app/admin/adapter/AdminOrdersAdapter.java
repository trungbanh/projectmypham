package com.example.vuphu.app.admin.adapter;

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

import com.example.vuphu.app.AcsynHttp.AsyncHttpApi;
import com.example.vuphu.app.R;
import com.example.vuphu.app.admin.AdminEditProductActivity;
import com.example.vuphu.app.object.order;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by vuphu on 4/1/2018.
 */

public class AdminOrdersAdapter {

    private static class orderViewHolder extends RecyclerView.ViewHolder{


        private TextView tv_name, tv_price,tv_date,tv_status;
        private ImageButton btn_edit, btn_delete;
        public orderViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_admin_order_name);
            tv_date = itemView.findViewById(R.id.tv_admin_order_date);
            tv_price = itemView.findViewById(R.id.tv_admin_order_price);
            tv_status  = itemView.findViewById(R.id.tv_admin_order_status);
            btn_edit = itemView.findViewById(R.id.btn_admin_order_edit);
            btn_delete = itemView.findViewById(R.id.btn_admin_order_delete);

        }
    }

    public static class orderAdap extends RecyclerView.Adapter<orderViewHolder>{

        ArrayList<order> list;
        Context context;
        String token;
        public orderAdap(ArrayList<order> list, Context context, String token) {
            this.list = list;
            this.context = context;
            this.token = token;
        }

        @Override
        public orderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.admin_item_order,parent, false);
            return new orderViewHolder(v);
        }

        @Override
        public void onBindViewHolder(orderViewHolder holder, final int position) {


            holder.tv_name.setText(list.get(position).getOwnerUid());
            holder.tv_price.setText(list.get(position).getProduct());
            holder.tv_date.setText(list.get(position).getQuatityBuy());
            holder.tv_status.setText(list.get(position).getStatus());
            holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(context)
                            .setTitle("Xóa")
                            .setMessage("Bạn có chắc muốn xóa?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //Lap trinh o day
                                    //product/delete/<idproduct>
                                    AsyncHttpApi.delete(token,"/orders/"+list.get(position).get_id(),new JsonHttpResponseHandler(){
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                            super.onSuccess(statusCode, headers, response);
                                            list.remove(position);
                                            AdminOrdersAdapter.orderAdap.super.notifyDataSetChanged();
                                        }
                                    });
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });
            holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AdminEditProductActivity.class);
                    intent.putExtra("data", list.get(position));
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
