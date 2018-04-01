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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuphu.app.R;
import com.example.vuphu.app.admin.AdminEditProductActivity;
import com.example.vuphu.app.object.product;

import java.util.ArrayList;

/**
 * Created by vuphu on 3/31/2018.
 */

public class AdminProductApdater {


    private static class productViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_product;
        private TextView tv_name, tv_price;
        private ImageButton btn_edit, btn_delete;
        public productViewHolder(View itemView) {
            super(itemView);
            img_product = itemView.findViewById(R.id.img_admin_product);
            tv_name = itemView.findViewById(R.id.tv_admin_name_product);
            tv_price = itemView.findViewById(R.id.tv_admin_price);
            btn_edit = itemView.findViewById(R.id.btn_admin_edit);
            btn_delete = itemView.findViewById(R.id.btn_admin_delete);

        }
    }

    public static class productAdap extends RecyclerView.Adapter<productViewHolder>{

        ArrayList<product> list;
        Context context;
        public productAdap(ArrayList<product> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public productViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.admin_item_product, null);
            return new productViewHolder(v);
        }

        @Override
        public void onBindViewHolder(productViewHolder holder, final int position) {
            holder.tv_name.setText(list.get(position).getName());
            holder.tv_price.setText(list.get(position).getPrice());

            holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(context)
                            .setTitle("Xóa")
                            .setMessage("Bạn có chắc muốn xóa?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Toast.makeText(context, "Yaay", Toast.LENGTH_SHORT).show();
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
