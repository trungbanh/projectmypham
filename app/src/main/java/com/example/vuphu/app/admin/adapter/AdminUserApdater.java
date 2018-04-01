package com.example.vuphu.app.admin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuphu.app.R;
import com.example.vuphu.app.object.users;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vuphu on 3/31/2018.
 */

public class AdminUserApdater {


    private static class userViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView img_user;
        private TextView tv_name;
        public userViewHolder(View itemView) {
            super(itemView);
            img_user = itemView.findViewById(R.id.img_admin_user);
            tv_name = itemView.findViewById(R.id.tv_admin_user_name);


        }
    }

    public static class userAdap extends RecyclerView.Adapter<userViewHolder>{

        ArrayList<users> list;
        Context context;
        public userAdap(ArrayList<users> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.admin_item_user, null);
            return new userViewHolder(v);
        }

        @Override
        public void onBindViewHolder(userViewHolder holder, final int position) {
            holder.tv_name.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context,.class);
//                    intent.putExtra("data", list.get(position));
//                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


}
